package NguyenQuocGiaKhang.DoAnWeb.Repository;

import NguyenQuocGiaKhang.DoAnWeb.Model.ToaThuoc;
import NguyenQuocGiaKhang.DoAnWeb.Model.ToaThuocId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToaThuocRepository extends JpaRepository<ToaThuoc, ToaThuocId> {

    List<ToaThuoc> findByKhamBenh_MaKham(String maKham);
}
