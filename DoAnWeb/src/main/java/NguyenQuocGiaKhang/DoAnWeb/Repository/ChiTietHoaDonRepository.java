package NguyenQuocGiaKhang.DoAnWeb.Repository;

import NguyenQuocGiaKhang.DoAnWeb.Model.ChiTietHoaDon;
import NguyenQuocGiaKhang.DoAnWeb.Model.ChiTietHoaDonId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChiTietHoaDonRepository extends JpaRepository<ChiTietHoaDon, ChiTietHoaDonId> {

    List<ChiTietHoaDon> findByHoaDon_MaHd(String maHd);

    void deleteByHoaDon_MaHd(String maHd);
}
