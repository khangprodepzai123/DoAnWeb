package NguyenQuocGiaKhang.DoAnWeb.Repository;

import NguyenQuocGiaKhang.DoAnWeb.Model.ChuanDoan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChuanDoanRepository extends JpaRepository<ChuanDoan, String> {

    Optional<ChuanDoan> findTopByOrderByMaCdDesc();
}
