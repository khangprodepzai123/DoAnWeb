package NguyenQuocGiaKhang.DoAnWeb.Repository;

import NguyenQuocGiaKhang.DoAnWeb.Model.KhamBenh;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KhamBenhRepository extends JpaRepository<KhamBenh, String> {

    Optional<KhamBenh> findTopByOrderByMaKhamDesc();

    Optional<KhamBenh> findByBenhNhan_MaBn(String maBn);
}
