package NguyenQuocGiaKhang.DoAnWeb.Repository;

import NguyenQuocGiaKhang.DoAnWeb.Model.BacSi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BacSiRepository extends JpaRepository<BacSi, String> {

    Optional<BacSi> findTopByOrderByMaBsDesc();
}
