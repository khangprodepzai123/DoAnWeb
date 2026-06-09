package NguyenQuocGiaKhang.DoAnWeb.Repository;

import NguyenQuocGiaKhang.DoAnWeb.Model.BenhNhan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BenhNhanRepository extends JpaRepository<BenhNhan, String> {

    Optional<BenhNhan> findTopByOrderByMaBnDesc();

    List<BenhNhan> findByHoTenBnContainingIgnoreCase(String hoTenBn);
}
