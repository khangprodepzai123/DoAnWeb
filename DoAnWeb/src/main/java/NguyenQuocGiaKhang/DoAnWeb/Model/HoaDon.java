package NguyenQuocGiaKhang.DoAnWeb.Model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "HoaDon")
public class HoaDon {

    @Id
    @Column(name = "MaHd", length = 10)
    private String maHd;

    @Column(name = "ThanhTien")
    private BigDecimal thanhTien;

    @Column(name = "NgayLap")
    private LocalDate ngayLap;

    @Column(name = "DiemTichLuySuDung")
    private Integer diemTichLuySuDung;

    @Column(name = "TrangThaiThanhToan", length = 30)
    private String trangThaiThanhToan = TrangThaiThanhToan.CHUA_THANH_TOAN;

    @OneToOne
    @JoinColumn(name = "MaKham")
    private KhamBenh khamBenh;

    @ManyToOne
    @JoinColumn(name = "MaNv")
    private NhanVien nhanVien;

    @OneToMany(mappedBy = "hoaDon")
    private List<ChiTietHoaDon> chiTietHoaDons = new ArrayList<>();


    public String getMaHd() {
        return maHd;
    }

    public void setMaHd(String maHd) {
        this.maHd = maHd;
    }

    public BigDecimal getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(BigDecimal thanhTien) {
        this.thanhTien = thanhTien;
    }

    public LocalDate getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(LocalDate ngayLap) {
        this.ngayLap = ngayLap;
    }

    public Integer getDiemTichLuySuDung() {
        return diemTichLuySuDung;
    }

    public void setDiemTichLuySuDung(Integer diemTichLuySuDung) {
        this.diemTichLuySuDung = diemTichLuySuDung;
    }

    public String getTrangThaiThanhToan() {
        return trangThaiThanhToan;
    }

    public void setTrangThaiThanhToan(String trangThaiThanhToan) {
        this.trangThaiThanhToan = trangThaiThanhToan;
    }

    public KhamBenh getKhamBenh() {
        return khamBenh;
    }

    public void setKhamBenh(KhamBenh khamBenh) {
        this.khamBenh = khamBenh;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public List<ChiTietHoaDon> getChiTietHoaDons() {
        return chiTietHoaDons;
    }

    public void setChiTietHoaDons(List<ChiTietHoaDon> chiTietHoaDons) {
        this.chiTietHoaDons = chiTietHoaDons;
    }
}
