package NguyenQuocGiaKhang.DoAnWeb.Repository;

import NguyenQuocGiaKhang.DoAnWeb.Model.ChiTietHoaDon;
import NguyenQuocGiaKhang.DoAnWeb.Model.ChiTietHoaDonId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChiTietHoaDonRepository
        extends JpaRepository<ChiTietHoaDon, ChiTietHoaDonId> {
}