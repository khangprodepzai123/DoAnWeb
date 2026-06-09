package NguyenQuocGiaKhang.DoAnWeb.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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


    public String getMaNv() {
        return maNv;
    }

    public void setMaNv(String maNv) {
        this.maNv = maNv;
    }

    public String getHoTenNv() {
        return hoTenNv;
    }

    public void setHoTenNv(String hoTenNv) {
        this.hoTenNv = hoTenNv;
    }

    public List<HoaDon> getHoaDons() {
        return hoaDons;
    }

    public void setHoaDons(List<HoaDon> hoaDons) {
        this.hoaDons = hoaDons;
    }
}
