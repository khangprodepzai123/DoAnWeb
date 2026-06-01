package NguyenQuocGiaKhang.DoAnWeb.Service;

import NguyenQuocGiaKhang.DoAnWeb.Model.ChiTietHoaDon;
import NguyenQuocGiaKhang.DoAnWeb.Model.ChiTietHoaDonId;
import NguyenQuocGiaKhang.DoAnWeb.Model.HoaDon;
import NguyenQuocGiaKhang.DoAnWeb.Model.Thuoc;
import NguyenQuocGiaKhang.DoAnWeb.Repository.ChiTietHoaDonRepository;
import NguyenQuocGiaKhang.DoAnWeb.Repository.HoaDonRepository;
import NguyenQuocGiaKhang.DoAnWeb.Repository.ThuocRepository;
import NguyenQuocGiaKhang.DoAnWeb.dto.ChiTietHoaDonDto;
import NguyenQuocGiaKhang.DoAnWeb.exception.BusinessException;
import NguyenQuocGiaKhang.DoAnWeb.exception.ResourceNotFoundException;
import NguyenQuocGiaKhang.DoAnWeb.mapper.DtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class ChiTietHoaDonService {

    private final ChiTietHoaDonRepository chiTietHoaDonRepository;
    private final HoaDonRepository hoaDonRepository;
    private final ThuocRepository thuocRepository;
    private final DtoMapper dtoMapper;

    public ChiTietHoaDonService(
            ChiTietHoaDonRepository chiTietHoaDonRepository,
            HoaDonRepository hoaDonRepository,
            ThuocRepository thuocRepository,
            DtoMapper dtoMapper) {
        this.chiTietHoaDonRepository = chiTietHoaDonRepository;
        this.hoaDonRepository = hoaDonRepository;
        this.thuocRepository = thuocRepository;
        this.dtoMapper = dtoMapper;
    }

    @Transactional(readOnly = true)
    public List<ChiTietHoaDonDto> findDtosByMaHd(String maHd) {
        return dtoMapper.toChiTietHoaDonDtoList(findByMaHd(maHd));
    }

    @Transactional(readOnly = true)
    public List<ChiTietHoaDon> findByMaHd(String maHd) {
        return chiTietHoaDonRepository.findByHoaDon_MaHd(maHd);
    }

    @Transactional(readOnly = true)
    public ChiTietHoaDonDto getDtoById(String maHd, String maThuoc) {
        return dtoMapper.toDto(getById(maHd, maThuoc));
    }

    @Transactional(readOnly = true)
    public ChiTietHoaDon getById(String maHd, String maThuoc) {
        ChiTietHoaDonId id = new ChiTietHoaDonId();
        id.setMaHd(maHd);
        id.setMaThuoc(maThuoc);
        return chiTietHoaDonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy chi tiết hóa đơn: " + maHd + " / " + maThuoc));
    }

    public ChiTietHoaDonDto saveFromDto(ChiTietHoaDonDto dto) {
        ChiTietHoaDon saved = addToHoaDon(dto.getMaHd(), dto.getMaThuoc(), dto.getSoLuong());
        return dtoMapper.toDto(saved);
    }

    public ChiTietHoaDon addToHoaDon(String maHd, String maThuoc, int soLuong) {
        if (soLuong <= 0) {
            throw new BusinessException("Số lượng phải lớn hơn 0");
        }

        HoaDon hoaDon = hoaDonRepository.findById(maHd)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy hóa đơn: " + maHd));

        Thuoc thuoc = thuocRepository.findById(maThuoc)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thuốc: " + maThuoc));

        if (thuoc.getSoLuong() != null && thuoc.getSoLuong() < soLuong) {
            throw new BusinessException("Thuốc " + thuoc.getTenThuoc() + " không đủ tồn kho");
        }

        ChiTietHoaDonId id = new ChiTietHoaDonId();
        id.setMaHd(maHd);
        id.setMaThuoc(maThuoc);

        ChiTietHoaDon chiTiet = chiTietHoaDonRepository.findById(id).orElse(new ChiTietHoaDon());
        chiTiet.setId(id);
        chiTiet.setHoaDon(hoaDon);
        chiTiet.setThuoc(thuoc);
        chiTiet.setSoLuong(soLuong);
        chiTiet.setDonGia(thuoc.getGiaBan());

        return chiTietHoaDonRepository.save(chiTiet);
    }

    public void delete(String maHd, String maThuoc) {
        ChiTietHoaDonId id = new ChiTietHoaDonId();
        id.setMaHd(maHd);
        id.setMaThuoc(maThuoc);
        if (!chiTietHoaDonRepository.existsById(id)) {
            throw new ResourceNotFoundException("Không tìm thấy chi tiết hóa đơn để xóa");
        }
        chiTietHoaDonRepository.deleteById(id);
    }

    public void deleteAllByMaHd(String maHd) {
        chiTietHoaDonRepository.deleteByHoaDon_MaHd(maHd);
    }

    @Transactional(readOnly = true)
    public BigDecimal tinhTongTien(String maHd) {
        return findByMaHd(maHd).stream()
                .map(ChiTietHoaDon::getThanhTienDong)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
