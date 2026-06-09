package NguyenQuocGiaKhang.DoAnWeb.Service;

import NguyenQuocGiaKhang.DoAnWeb.Model.*;
import NguyenQuocGiaKhang.DoAnWeb.Repository.BenhAnRepository;
import NguyenQuocGiaKhang.DoAnWeb.Repository.HoaDonRepository;
import NguyenQuocGiaKhang.DoAnWeb.Repository.KhamBenhRepository;
import NguyenQuocGiaKhang.DoAnWeb.dto.KhamBenhDto;
import NguyenQuocGiaKhang.DoAnWeb.exception.BusinessException;
import NguyenQuocGiaKhang.DoAnWeb.exception.ResourceNotFoundException;
import NguyenQuocGiaKhang.DoAnWeb.mapper.DtoMapper;
import NguyenQuocGiaKhang.DoAnWeb.util.MaIdGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class KhamBenhService {

    private static final String MA_PREFIX = "KB";
    private static final String DEFAULT_MA_CD = "CD001";

    private final KhamBenhRepository khamBenhRepository;
    private final HoaDonRepository hoaDonRepository;
    private final BenhAnRepository benhAnRepository;
    private final BenhNhanService benhNhanService;
    private final BacSiService bacSiService;
    private final ChuanDoanService chuanDoanService;
    private final DtoMapper dtoMapper;

    public KhamBenhService(
            KhamBenhRepository khamBenhRepository,
            HoaDonRepository hoaDonRepository,
            BenhAnRepository benhAnRepository,
            BenhNhanService benhNhanService,
            BacSiService bacSiService,
            ChuanDoanService chuanDoanService,
            DtoMapper dtoMapper) {
        this.khamBenhRepository = khamBenhRepository;
        this.hoaDonRepository = hoaDonRepository;
        this.benhAnRepository = benhAnRepository;
        this.benhNhanService = benhNhanService;
        this.bacSiService = bacSiService;
        this.chuanDoanService = chuanDoanService;
        this.dtoMapper = dtoMapper;
    }

    @Transactional(readOnly = true)
    public List<KhamBenhDto> getAllDtos() {
        return khamBenhRepository.findAll().stream().map(dtoMapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<KhamBenhDto> getAllDtosForIndex() {
        return getAllDtos().stream().map(dto -> {
            hoaDonRepository.findByKhamBenh_MaKham(dto.getMaKham()).ifPresent(hd -> {
                dto.setMaHd(hd.getMaHd());
                dto.setTrangThaiThanhToan(hd.getTrangThaiThanhToan());
            });
            dto.setDaCoBenhAn(benhAnRepository.findByMaKham(dto.getMaKham()).isPresent());
            return dto;
        }).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public KhamBenhDto getDtoById(String maKham) {
        return dtoMapper.toDto(getEntityById(maKham));
    }

    @Transactional(readOnly = true)
    public KhamBenh getEntityById(String maKham) {
        return khamBenhRepository.findById(maKham)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy phiếu khám: " + maKham));
    }

    @Transactional(readOnly = true)
    public KhamBenhDto getDtoByMaBn(String maBn) {
        return khamBenhRepository.findByBenhNhan_MaBn(maBn)
                .map(dtoMapper::toDto)
                .orElse(null);
    }

    public KhamBenhDto saveDto(KhamBenhDto dto) {
        KhamBenh entity = buildEntityFromDto(dto);
        if (entity.getMaKham() == null || entity.getMaKham().isBlank()) {
            String last = khamBenhRepository.findTopByOrderByMaKhamDesc().map(KhamBenh::getMaKham).orElse(null);
            entity.setMaKham(MaIdGenerator.nextMa(MA_PREFIX, last));
        }
        if (entity.getNgayKham() == null) {
            entity.setNgayKham(LocalDate.now());
        }
        if (entity.getTrangThai() == null || entity.getTrangThai().isBlank()) {
            entity.setTrangThai(TrangThaiKham.CHO_KHAM);
        }
        return dtoMapper.toDto(khamBenhRepository.save(entity));
    }

    public KhamBenhDto dangKyKham(String maBn, String maBs) {
        if (khamBenhRepository.findByBenhNhan_MaBn(maBn).isPresent()) {
            throw new BusinessException("Bệnh nhân đã có phiếu khám");
        }
        KhamBenhDto dto = new KhamBenhDto();
        dto.setMaBn(maBn);
        dto.setMaBs(maBs);
        dto.setMaCd(DEFAULT_MA_CD);
        dto.setTrangThai(TrangThaiKham.CHO_KHAM);
        dto.setNgayKham(LocalDate.now());
        return saveDto(dto);
    }

    public void delete(String maKham) {
        if (!khamBenhRepository.existsById(maKham)) {
            throw new ResourceNotFoundException("Không tìm thấy phiếu khám để xóa: " + maKham);
        }
        khamBenhRepository.deleteById(maKham);
    }

    private KhamBenh buildEntityFromDto(KhamBenhDto dto) {
        if (dto.getMaBn() == null || dto.getMaBn().isBlank()) {
            throw new BusinessException("Mã bệnh nhân không được để trống");
        }
        if (dto.getMaBs() == null || dto.getMaBs().isBlank()) {
            throw new BusinessException("Mã bác sĩ không được để trống");
        }

        BenhNhan benhNhan = benhNhanService.getEntityById(dto.getMaBn());
        BacSi bacSi = bacSiService.getEntityById(dto.getMaBs());

        khamBenhRepository.findByBenhNhan_MaBn(dto.getMaBn()).ifPresent(existing -> {
            if (dto.getMaKham() == null || !existing.getMaKham().equals(dto.getMaKham().trim())) {
                throw new BusinessException("Mỗi bệnh nhân chỉ được một phiếu khám");
            }
        });

        String maCd = (dto.getMaCd() == null || dto.getMaCd().isBlank()) ? DEFAULT_MA_CD : dto.getMaCd();
        ChuanDoan chuanDoan = chuanDoanService.getEntityById(maCd);

        KhamBenh entity = new KhamBenh();
        if (dto.getMaKham() != null && !dto.getMaKham().isBlank()) {
            entity.setMaKham(dto.getMaKham().trim());
        }
        entity.setBenhNhan(benhNhan);
        entity.setBacSi(bacSi);
        entity.setChuanDoanEntity(chuanDoan);
        entity.setNgayKham(dto.getNgayKham());
        entity.setLyDoKham(dto.getLyDoKham());
        entity.setQuaTrinhBenhLy(dto.getQuaTrinhBenhLy());
        entity.setTienSuBenhNhan(dto.getTienSuBenhNhan());
        entity.setTienSuGiaDinh(dto.getTienSuGiaDinh());
        entity.setKhamBoPhan(dto.getKhamBoPhan());
        entity.setChuanDoan(dto.getChuanDoan());
        entity.setHuongXuTri(dto.getHuongXuTri());
        entity.setLoaiKham(dto.getLoaiKham());
        entity.setXuTriKham(dto.getXuTriKham());
        entity.setTrangThai(dto.getTrangThai());
        return entity;
    }
}
