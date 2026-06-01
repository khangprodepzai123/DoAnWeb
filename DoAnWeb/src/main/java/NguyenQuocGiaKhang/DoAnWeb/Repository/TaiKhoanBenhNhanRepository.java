package NguyenQuocGiaKhang.DoAnWeb.Repository;

import NguyenQuocGiaKhang.DoAnWeb.Model.TaiKhoanBenhNhan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaiKhoanBenhNhanRepository extends JpaRepository<TaiKhoanBenhNhan, String> {

    Optional<TaiKhoanBenhNhan> findTopByOrderByMaTkDesc();

    Optional<TaiKhoanBenhNhan> findByTenDangNhap(String tenDangNhap);

    boolean existsByTenDangNhap(String tenDangNhap);
}
