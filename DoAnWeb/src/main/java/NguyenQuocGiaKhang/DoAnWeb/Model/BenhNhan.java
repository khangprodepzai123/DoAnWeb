package NguyenQuocGiaKhang.DoAnWeb.Model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "BenhNhan")
public class BenhNhan {

    @Id
    @Column(name = "MaBn", length = 10)
    private String maBn;

    @Column(name = "HoTenBn", nullable = false, length = 100)
    private String hoTenBn;

    @Column(name = "SDT", length = 15)
    private String sdt;

    @Column(name = "NgaySinh")
    private LocalDate ngaySinh;

    @Column(name = "GT", length = 10)
    private String gt;

    @Column(name = "DoiTuong", length = 50)
    private String doiTuong;

    @Column(name = "DiaChi", length = 200)
    private String diaChi;

    @Column(name = "BHYT", length = 20)
    private String bhyt;

    @OneToOne(mappedBy = "benhNhan")
    private KhamBenh khamBenh;

    @OneToOne(mappedBy = "benhNhan")
    private TaiKhoanBenhNhan taiKhoanBenhNhan;


    public String getMaBn() {
        return maBn;
    }

    public void setMaBn(String maBn) {
        this.maBn = maBn;
    }

    public String getHoTenBn() {
        return hoTenBn;
    }

    public void setHoTenBn(String hoTenBn) {
        this.hoTenBn = hoTenBn;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGt() {
        return gt;
    }

    public void setGt(String gt) {
        this.gt = gt;
    }

    public String getDoiTuong() {
        return doiTuong;
    }

    public void setDoiTuong(String doiTuong) {
        this.doiTuong = doiTuong;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getBhyt() {
        return bhyt;
    }

    public void setBhyt(String bhyt) {
        this.bhyt = bhyt;
    }

    public KhamBenh getKhamBenh() {
        return khamBenh;
    }

    public void setKhamBenh(KhamBenh khamBenh) {
        this.khamBenh = khamBenh;
    }

    public TaiKhoanBenhNhan getTaiKhoanBenhNhan() {
        return taiKhoanBenhNhan;
    }

    public void setTaiKhoanBenhNhan(TaiKhoanBenhNhan taiKhoanBenhNhan) {
        this.taiKhoanBenhNhan = taiKhoanBenhNhan;
    }
}
