package NguyenQuocGiaKhang.DoAnWeb.Model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BenhAn")
public class BenhAn {

    @Id
    @Column(name = "MaBenhAn", length = 10)
    private String maBenhAn;

    @Column(name = "MaKham", length = 10)
    private String maKham;

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

    @Column(name = "ChuanDoan", length = 500)
    private String chuanDoan;

    @Column(name = "HuongXuTri", length = 500)
    private String huongXuTri;

    @Column(name = "LoaiKham", length = 50)
    private String loaiKham;

    @Column(name = "XuTriKham", length = 100)
    private String xuTriKham;

    @Column(name = "NgayKham")
    private LocalDate ngayKham;

    @Column(name = "NgayLuu")
    private LocalDateTime ngayLuu;

    @ManyToOne
    @JoinColumn(name = "MaBn")
    private BenhNhan benhNhan;

    @ManyToOne
    @JoinColumn(name = "MaBs")
    private BacSi bacSi;

    @OneToMany(mappedBy = "benhAn")
    private List<BenhAnToaThuoc> benhAnToaThuocs = new ArrayList<>();


    public String getMaBenhAn() {
        return maBenhAn;
    }

    public void setMaBenhAn(String maBenhAn) {
        this.maBenhAn = maBenhAn;
    }

    public String getMaKham() {
        return maKham;
    }

    public void setMaKham(String maKham) {
        this.maKham = maKham;
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

    public LocalDateTime getNgayLuu() {
        return ngayLuu;
    }

    public void setNgayLuu(LocalDateTime ngayLuu) {
        this.ngayLuu = ngayLuu;
    }

    public BenhNhan getBenhNhan() {
        return benhNhan;
    }

    public void setBenhNhan(BenhNhan benhNhan) {
        this.benhNhan = benhNhan;
    }

    public BacSi getBacSi() {
        return bacSi;
    }

    public void setBacSi(BacSi bacSi) {
        this.bacSi = bacSi;
    }

    public List<BenhAnToaThuoc> getBenhAnToaThuocs() {
        return benhAnToaThuocs;
    }

    public void setBenhAnToaThuocs(List<BenhAnToaThuoc> benhAnToaThuocs) {
        this.benhAnToaThuocs = benhAnToaThuocs;
    }
}
