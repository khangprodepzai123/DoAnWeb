package NguyenQuocGiaKhang.DoAnWeb.Repository;

import NguyenQuocGiaKhang.DoAnWeb.Model.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HoaDonRepository extends JpaRepository<HoaDon, String> {

    Optional<HoaDon> findTopByOrderByMaHdDesc();

    Optional<HoaDon> findByKhamBenh_MaKham(String maKham);
}
