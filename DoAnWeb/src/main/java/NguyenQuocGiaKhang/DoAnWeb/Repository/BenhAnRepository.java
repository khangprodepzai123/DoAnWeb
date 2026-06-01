package NguyenQuocGiaKhang.DoAnWeb.Repository;

import NguyenQuocGiaKhang.DoAnWeb.Model.BenhAn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BenhAnRepository extends JpaRepository<BenhAn, String> {

    Optional<BenhAn> findTopByOrderByMaBenhAnDesc();
}
