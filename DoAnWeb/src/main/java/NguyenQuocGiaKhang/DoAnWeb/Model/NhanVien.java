package NguyenQuocGiaKhang.DoAnWeb.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "NhanVien")
public class NhanVien {

    @Id
    @Column(name = "MaNv", length = 10)
    private String maNv;

    @Column(name = "HoTenNv", length = 100)
    private String hoTenNv;

    @OneToMany(mappedBy = "nhanVien")
    private List<HoaDon> hoaDons = new ArrayList<>();
}