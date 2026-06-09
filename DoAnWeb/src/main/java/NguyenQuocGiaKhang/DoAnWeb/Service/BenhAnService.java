package NguyenQuocGiaKhang.DoAnWeb.Service;

import NguyenQuocGiaKhang.DoAnWeb.Model.BenhAn;
import NguyenQuocGiaKhang.DoAnWeb.Model.HoaDon;
import NguyenQuocGiaKhang.DoAnWeb.Model.KhamBenh;
import NguyenQuocGiaKhang.DoAnWeb.Model.TrangThaiThanhToan;
import NguyenQuocGiaKhang.DoAnWeb.Repository.BenhAnRepository;
import NguyenQuocGiaKhang.DoAnWeb.Repository.HoaDonRepository;
import NguyenQuocGiaKhang.DoAnWeb.dto.BenhAnDto;
import NguyenQuocGiaKhang.DoAnWeb.exception.BusinessException;
import NguyenQuocGiaKhang.DoAnWeb.exception.ResourceNotFoundException;
import NguyenQuocGiaKhang.DoAnWeb.mapper.DtoMapper;
import NguyenQuocGiaKhang.DoAnWeb.util.MaIdGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BenhAnService {

    private static final String MA_PREFIX = "BA";

    private final BenhAnRepository repository;
    private final KhamBenhService khamBenhService;
    private final HoaDonRepository hoaDonRepository;
    private final DtoMapper dtoMapper;

    public BenhAnService(
            BenhAnRepository repository,
            KhamBenhService khamBenhService,
            HoaDonRepository hoaDonRepository,
            DtoMapper dtoMapper) {
        this.repository = repository;
        this.khamBenhService = khamBenhService;
        this.hoaDonRepository = hoaDonRepository;
        this.dtoMapper = dtoMapper;
    }

    @Transactional(readOnly = true)
    public List<BenhAnDto> getAllDtos() {
        return repository.findAll().stream().map(dtoMapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BenhAnDto getDtoById(String maBenhAn) {
        return dtoMapper.toDto(getEntityById(maBenhAn));
    }

    @Transactional(readOnly = true)
    public BenhAn getEntityById(String maBenhAn) {
        return repository.findById(maBenhAn)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy bệnh án: " + maBenhAn));
    }

    public BenhAnDto saveFromKhamBenh(String maKham) {
        if (repository.findByMaKham(maKham).isPresent()) {
            throw new BusinessException("Phiếu khám đã có bệnh án");
        }

        HoaDon hoaDon = hoaDonRepository.findByKhamBenh_MaKham(maKham)
                .orElseThrow(() -> new BusinessException("Chưa có hóa đơn cho phiếu khám này"));

        if (!TrangThaiThanhToan.isDaThanhToan(hoaDon.getTrangThaiThanhToan())) {
            throw new BusinessException("Hóa đơn chưa thanh toán, không thể lưu bệnh án");
        }

        KhamBenh kb = khamBenhService.getEntityById(maKham);
        BenhAn benhAn = new BenhAn();
        String last = repository.findTopByOrderByMaBenhAnDesc().map(BenhAn::getMaBenhAn).orElse(null);
        benhAn.setMaBenhAn(MaIdGenerator.nextMa(MA_PREFIX, last));
        benhAn.setMaKham(kb.getMaKham());
        benhAn.setBenhNhan(kb.getBenhNhan());
        benhAn.setBacSi(kb.getBacSi());
        benhAn.setNgayKham(kb.getNgayKham());
        benhAn.setNgayLuu(LocalDateTime.now());
        benhAn.setLyDoKham(kb.getLyDoKham());
        benhAn.setQuaTrinhBenhLy(kb.getQuaTrinhBenhLy());
        benhAn.setTienSuBenhNhan(kb.getTienSuBenhNhan());
        benhAn.setTienSuGiaDinh(kb.getTienSuGiaDinh());
        benhAn.setKhamBoPhan(kb.getKhamBoPhan());
        benhAn.setChuanDoan(kb.getChuanDoan());
        benhAn.setHuongXuTri(kb.getHuongXuTri());
        benhAn.setLoaiKham(kb.getLoaiKham());
        benhAn.setXuTriKham(kb.getXuTriKham());
        return dtoMapper.toDto(repository.save(benhAn));
    }

    public BenhAnDto saveDto(BenhAnDto dto) {
        if (dto.getMaKham() == null || dto.getMaKham().isBlank()) {
            throw new BusinessException("Mã khám không được để trống");
        }
        return saveFromKhamBenh(dto.getMaKham());
    }

    public void delete(String maBenhAn) {
        if (!repository.existsById(maBenhAn)) {
            throw new ResourceNotFoundException("Không tìm thấy bệnh án để xóa: " + maBenhAn);
        }
        repository.deleteById(maBenhAn);
    }
}
