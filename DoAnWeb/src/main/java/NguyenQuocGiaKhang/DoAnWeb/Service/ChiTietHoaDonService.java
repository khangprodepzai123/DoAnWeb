package NguyenQuocGiaKhang.DoAnWeb.Service;

import NguyenQuocGiaKhang.DoAnWeb.Model.ChiTietHoaDon;
import NguyenQuocGiaKhang.DoAnWeb.Model.ChiTietHoaDonId;
import NguyenQuocGiaKhang.DoAnWeb.Model.HoaDon;
import NguyenQuocGiaKhang.DoAnWeb.Model.Thuoc;
import NguyenQuocGiaKhang.DoAnWeb.Repository.ChiTietHoaDonRepository;
import NguyenQuocGiaKhang.DoAnWeb.Repository.HoaDonRepository;
import NguyenQuocGiaKhang.DoAnWeb.Repository.ThuocRepository;
import NguyenQuocGiaKhang.DoAnWeb.exception.BusinessException;
import NguyenQuocGiaKhang.DoAnWeb.exception.ResourceNotFoundException;
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

    public ChiTietHoaDonService(
            ChiTietHoaDonRepository chiTietHoaDonRepository,
            HoaDonRepository hoaDonRepository,
            ThuocRepository thuocRepository) {
        this.chiTietHoaDonRepository = chiTietHoaDonRepository;
        this.hoaDonRepository = hoaDonRepository;
        this.thuocRepository = thuocRepository;
    }

    @Transactional(readOnly = true)
    public List<ChiTietHoaDon> findAll() {
        return chiTietHoaDonRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<ChiTietHoaDon> findByMaHd(String maHd) {
        return chiTietHoaDonRepository.findByHoaDon_MaHd(maHd);
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

    public ChiTietHoaDon save(ChiTietHoaDon chiTiet) {
        syncCompositeKey(chiTiet);
        validateChiTiet(chiTiet);
        return chiTietHoaDonRepository.save(chiTiet);
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

    private void syncCompositeKey(ChiTietHoaDon chiTiet) {
        if (chiTiet.getId() == null) {
            chiTiet.setId(new ChiTietHoaDonId());
        }
        if (chiTiet.getHoaDon() != null) {
            chiTiet.getId().setMaHd(chiTiet.getHoaDon().getMaHd());
        }
        if (chiTiet.getThuoc() != null) {
            chiTiet.getId().setMaThuoc(chiTiet.getThuoc().getMaThuoc());
        }
    }

    private void validateChiTiet(ChiTietHoaDon chiTiet) {
        if (chiTiet.getHoaDon() == null || chiTiet.getHoaDon().getMaHd() == null) {
            throw new BusinessException("Chi tiết hóa đơn phải gắn với hóa đơn");
        }
        if (chiTiet.getThuoc() == null || chiTiet.getThuoc().getMaThuoc() == null) {
            throw new BusinessException("Chi tiết hóa đơn phải gắn với thuốc");
        }
        if (chiTiet.getSoLuong() == null || chiTiet.getSoLuong() <= 0) {
            throw new BusinessException("Số lượng phải lớn hơn 0");
        }
        if (chiTiet.getDonGia() == null || chiTiet.getDonGia().signum() < 0) {
            throw new BusinessException("Đơn giá không hợp lệ");
        }
    }
}
