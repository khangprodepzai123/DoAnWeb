package NguyenQuocGiaKhang.DoAnWeb.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class NhanVienDto {

    @Size(max = 10)
    private String maNv;

    @NotBlank(message = "Họ tên nhân viên không được để trống")
    @Size(max = 100)
    private String hoTenNv;


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
}
