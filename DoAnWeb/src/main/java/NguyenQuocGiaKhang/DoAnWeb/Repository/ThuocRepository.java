package NguyenQuocGiaKhang.DoAnWeb.Repository;

import NguyenQuocGiaKhang.DoAnWeb.Model.Thuoc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ThuocRepository extends JpaRepository<Thuoc, String> {

    Optional<Thuoc> findTopByOrderByMaThuocDesc();
}
