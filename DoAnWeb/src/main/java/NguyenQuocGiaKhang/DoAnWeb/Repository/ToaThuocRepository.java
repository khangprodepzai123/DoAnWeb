package NguyenQuocGiaKhang.DoAnWeb.Repository;

import NguyenQuocGiaKhang.DoAnWeb.Model.ToaThuoc;
import NguyenQuocGiaKhang.DoAnWeb.Model.ToaThuocId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToaThuocRepository
        extends JpaRepository<ToaThuoc, ToaThuocId> {
}