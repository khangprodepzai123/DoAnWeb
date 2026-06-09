package NguyenQuocGiaKhang.DoAnWeb.Model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Thuoc")
public class Thuoc {

    @Id
    @Column(name = "MaThuoc", length = 10)
    private String maThuoc;

    @Column(name = "TenThuoc", nullable = false, length = 100)
    private String tenThuoc;

    @Column(name = "GiaBan")
    private BigDecimal giaBan;

    @Column(name = "SoLuong")
    private Integer soLuong;

    @Column(name = "HDSD", length = 500)
    private String hdsd;

    @OneToMany(mappedBy = "thuoc")
    private List<ChiTietHoaDon> chiTietHoaDons = new ArrayList<>();

    @OneToMany(mappedBy = "thuoc")
    private List<ToaThuoc> toaThuocs = new ArrayList<>();


    public String getMaThuoc() {
        return maThuoc;
    }

    public void setMaThuoc(String maThuoc) {
        this.maThuoc = maThuoc;
    }

    public String getTenThuoc() {
        return tenThuoc;
    }

    public void setTenThuoc(String tenThuoc) {
        this.tenThuoc = tenThuoc;
    }

    public BigDecimal getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(BigDecimal giaBan) {
        this.giaBan = giaBan;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public String getHdsd() {
        return hdsd;
    }

    public void setHdsd(String hdsd) {
        this.hdsd = hdsd;
    }

    public List<ChiTietHoaDon> getChiTietHoaDons() {
        return chiTietHoaDons;
    }

    public void setChiTietHoaDons(List<ChiTietHoaDon> chiTietHoaDons) {
        this.chiTietHoaDons = chiTietHoaDons;
    }

    public List<ToaThuoc> getToaThuocs() {
        return toaThuocs;
    }

    public void setToaThuocs(List<ToaThuoc> toaThuocs) {
        this.toaThuocs = toaThuocs;
    }
}
