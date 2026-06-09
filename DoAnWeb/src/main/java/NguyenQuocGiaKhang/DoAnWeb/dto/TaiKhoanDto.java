package NguyenQuocGiaKhang.DoAnWeb.dto;

import NguyenQuocGiaKhang.DoAnWeb.Model.VaiTro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TaiKhoanDto {

    @Size(max = 10)
    private String maTk;

    @NotBlank(message = "Tên đăng nhập không được để trống")
    @Size(max = 50)
    private String tenDangNhap;

    @Size(max = 255)
    private String matKhau;

    /** Dùng khi tạo / đổi mật khẩu */
    private String matKhauXacNhan;

    private Integer diemTichLuy;

    @Size(max = 255)
    private String hoTenBn;

    private VaiTro vaiTro;

    private String maBn;

    private String maNv;


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

    public String getMatKhauXacNhan() {
        return matKhauXacNhan;
    }

    public void setMatKhauXacNhan(String matKhauXacNhan) {
        this.matKhauXacNhan = matKhauXacNhan;
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

    public String getMaBn() {
        return maBn;
    }

    public void setMaBn(String maBn) {
        this.maBn = maBn;
    }

    public String getMaNv() {
        return maNv;
    }

    public void setMaNv(String maNv) {
        this.maNv = maNv;
    }
}
