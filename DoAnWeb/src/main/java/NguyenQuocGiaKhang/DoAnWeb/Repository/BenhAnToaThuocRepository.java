package NguyenQuocGiaKhang.DoAnWeb.Repository;

import NguyenQuocGiaKhang.DoAnWeb.Model.BenhAnToaThuoc;
import NguyenQuocGiaKhang.DoAnWeb.Model.BenhAnToaThuocId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BenhAnToaThuocRepository
        extends JpaRepository<BenhAnToaThuoc, BenhAnToaThuocId> {
}