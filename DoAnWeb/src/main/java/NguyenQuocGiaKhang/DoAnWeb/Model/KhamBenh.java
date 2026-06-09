package NguyenQuocGiaKhang.DoAnWeb.Model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "KhamBenh")
public class KhamBenh {

    @Id
    @Column(name = "MaKham", length = 10)
    private String maKham;

    @Column(name = "ChuanDoan", length = 500)
    private String chuanDoan;

    @Column(name = "HuongXuTri", length = 500)
    private String huongXuTri;

    @Column(name = "LyDoKham", length = 500)
    private String lyDoKham;

    @Column(name = "QuaTrinhBenhLy", length = 1000)
    private String quaTrinhBenhLy;

    @Column(name = "TienSuBenhNhan", length = 500)
    private String tienSuBenhNhan;

    @Column(name = "TienSuGiaDinh", length = 500)
    private String tienSuGiaDinh;

    @Column(name = "KhamBoPhan", length = 1000)
    private String khamBoPhan;

    @Column(name = "LoaiKham", length = 50)
    private String loaiKham;

    @Column(name = "XuTriKham", length = 100)
    private String xuTriKham;

    @Column(name = "NgayKham")
    private LocalDate ngayKham;

    @Column(name = "TrangThai", length = 50)
    private String trangThai;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaBs")
    private BacSi bacSi;

    @OneToOne
    @JoinColumn(name = "MaBn", unique = true)
    private BenhNhan benhNhan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaCd")
    private ChuanDoan chuanDoanEntity;

    @OneToOne(mappedBy = "khamBenh")
    private HoaDon hoaDon;

    @OneToMany(mappedBy = "khamBenh", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ToaThuoc> toaThuocs = new ArrayList<>();


    public String getMaKham() {
        return maKham;
    }

    public void setMaKham(String maKham) {
        this.maKham = maKham;
    }

    public String getChuanDoan() {
        return chuanDoan;
    }

    public void setChuanDoan(String chuanDoan) {
        this.chuanDoan = chuanDoan;
    }

    public String getHuongXuTri() {
        return huongXuTri;
    }

    public void setHuongXuTri(String huongXuTri) {
        this.huongXuTri = huongXuTri;
    }

    public String getLyDoKham() {
        return lyDoKham;
    }

    public void setLyDoKham(String lyDoKham) {
        this.lyDoKham = lyDoKham;
    }

    public String getQuaTrinhBenhLy() {
        return quaTrinhBenhLy;
    }

    public void setQuaTrinhBenhLy(String quaTrinhBenhLy) {
        this.quaTrinhBenhLy = quaTrinhBenhLy;
    }

    public String getTienSuBenhNhan() {
        return tienSuBenhNhan;
    }

    public void setTienSuBenhNhan(String tienSuBenhNhan) {
        this.tienSuBenhNhan = tienSuBenhNhan;
    }

    public String getTienSuGiaDinh() {
        return tienSuGiaDinh;
    }

    public void setTienSuGiaDinh(String tienSuGiaDinh) {
        this.tienSuGiaDinh = tienSuGiaDinh;
    }

    public String getKhamBoPhan() {
        return khamBoPhan;
    }

    public void setKhamBoPhan(String khamBoPhan) {
        this.khamBoPhan = khamBoPhan;
    }

    public String getLoaiKham() {
        return loaiKham;
    }

    public void setLoaiKham(String loaiKham) {
        this.loaiKham = loaiKham;
    }

    public String getXuTriKham() {
        return xuTriKham;
    }

    public void setXuTriKham(String xuTriKham) {
        this.xuTriKham = xuTriKham;
    }

    public LocalDate getNgayKham() {
        return ngayKham;
    }

    public void setNgayKham(LocalDate ngayKham) {
        this.ngayKham = ngayKham;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public BacSi getBacSi() {
        return bacSi;
    }

    public void setBacSi(BacSi bacSi) {
        this.bacSi = bacSi;
    }

    public BenhNhan getBenhNhan() {
        return benhNhan;
    }

    public void setBenhNhan(BenhNhan benhNhan) {
        this.benhNhan = benhNhan;
    }

    public ChuanDoan getChuanDoanEntity() {
        return chuanDoanEntity;
    }

    public void setChuanDoanEntity(ChuanDoan chuanDoanEntity) {
        this.chuanDoanEntity = chuanDoanEntity;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public List<ToaThuoc> getToaThuocs() {
        return toaThuocs;
    }

    public void setToaThuocs(List<ToaThuoc> toaThuocs) {
        this.toaThuocs = toaThuocs;
    }
}
