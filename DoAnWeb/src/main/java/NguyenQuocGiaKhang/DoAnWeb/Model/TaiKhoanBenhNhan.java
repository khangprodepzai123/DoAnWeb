package NguyenQuocGiaKhang.DoAnWeb.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "TaiKhoanBenhNhan")
public class TaiKhoanBenhNhan {

    @Id
    @Column(name = "MaTk", length = 10)
    private String maTk;

    @Column(name = "TenDangNhap", unique = true, nullable = false, length = 50)
    private String tenDangNhap;

    @Column(name = "MatKhau", nullable = false, length = 255)
    private String matKhau;

    @Column(name = "DiemTichLuy")
    private Integer diemTichLuy = 0;

    @Column(name = "HoTenBn", length = 255)
    private String hoTenBn;

    @Enumerated(EnumType.STRING)
    @Column(name = "VaiTro", nullable = false, length = 20)
    private VaiTro vaiTro = VaiTro.BENH_NHAN;

    @OneToOne
    @JoinColumn(name = "MaBn", unique = true)
    private BenhNhan benhNhan;

    @ManyToOne
    @JoinColumn(name = "MaNv")
    private NhanVien nhanVien;


    public String getMaTk() {
        return maTk;
    }

    public void setMaTk(String maTk) {
        this.maTk = maTk;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public Integer getDiemTichLuy() {
        return diemTichLuy;
    }

    public void setDiemTichLuy(Integer diemTichLuy) {
        this.diemTichLuy = diemTichLuy;
    }

    public String getHoTenBn() {
        return hoTenBn;
    }

    public void setHoTenBn(String hoTenBn) {
        this.hoTenBn = hoTenBn;
    }

    public VaiTro getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(VaiTro vaiTro) {
        this.vaiTro = vaiTro;
    }

    public BenhNhan getBenhNhan() {
        return benhNhan;
    }

    public void setBenhNhan(BenhNhan benhNhan) {
        this.benhNhan = benhNhan;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }
}
