package NguyenQuocGiaKhang.DoAnWeb.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class KhamBenhDto {

    @Size(max = 10)
    private String maKham;

    @NotBlank(message = "Mã bệnh nhân không được để trống")
    private String maBn;

    @NotBlank(message = "Mã bác sĩ không được để trống")
    private String maBs;

    private String maCd;

    private LocalDate ngayKham;

    @Size(max = 500)
    private String lyDoKham;

    @Size(max = 1000)
    private String quaTrinhBenhLy;

    @Size(max = 500)
    private String tienSuBenhNhan;

    @Size(max = 500)
    private String tienSuGiaDinh;

    @Size(max = 1000)
    private String khamBoPhan;

    @Size(max = 500)
    private String chuanDoan;

    @Size(max = 500)
    private String huongXuTri;

    @Size(max = 50)
    private String loaiKham;

    @Size(max = 100)
    private String xuTriKham;

    @Size(max = 50)
    private String trangThai;

    /** Hiển thị trên form */
    private String hoTenBn;

    private String hoTenBs;

    /** Thông tin hóa đơn (hiển thị danh sách) */
    private String maHd;
    private String trangThaiThanhToan;
    private boolean daCoBenhAn;


    public String getMaKham() {
        return maKham;
    }

    public void setMaKham(String maKham) {
        this.maKham = maKham;
    }

    public String getMaBn() {
        return maBn;
    }

    public void setMaBn(String maBn) {
        this.maBn = maBn;
    }

    public String getMaBs() {
        return maBs;
    }

    public void setMaBs(String maBs) {
        this.maBs = maBs;
    }

    public String getMaCd() {
        return maCd;
    }

    public void setMaCd(String maCd) {
        this.maCd = maCd;
    }

    public LocalDate getNgayKham() {
        return ngayKham;
    }

    public void setNgayKham(LocalDate ngayKham) {
        this.ngayKham = ngayKham;
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

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getHoTenBn() {
        return hoTenBn;
    }

    public void setHoTenBn(String hoTenBn) {
        this.hoTenBn = hoTenBn;
    }

    public String getHoTenBs() {
        return hoTenBs;
    }

    public void setHoTenBs(String hoTenBs) {
        this.hoTenBs = hoTenBs;
    }

    public String getMaHd() {
        return maHd;
    }

    public void setMaHd(String maHd) {
        this.maHd = maHd;
    }

    public String getTrangThaiThanhToan() {
        return trangThaiThanhToan;
    }

    public void setTrangThaiThanhToan(String trangThaiThanhToan) {
        this.trangThaiThanhToan = trangThaiThanhToan;
    }

    public boolean isDaCoBenhAn() {
        return daCoBenhAn;
    }

    public void setDaCoBenhAn(boolean daCoBenhAn) {
        this.daCoBenhAn = daCoBenhAn;
    }
}
