package NguyenQuocGiaKhang.DoAnWeb.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class BenhNhanDto {

    @Size(max = 10, message = "Mã bệnh nhân tối đa 10 ký tự")
    private String maBn;

    @NotBlank(message = "Họ tên không được để trống")
    @Size(max = 100, message = "Họ tên tối đa 100 ký tự")
    private String hoTenBn;

    @Size(max = 15, message = "SĐT tối đa 15 ký tự")
    private String sdt;

    private LocalDate ngaySinh;

    @Size(max = 10)
    private String gt;

    @Size(max = 50)
    private String doiTuong;

    @Size(max = 200)
    private String diaChi;

    @Size(max = 20)
    private String bhyt;


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
}
