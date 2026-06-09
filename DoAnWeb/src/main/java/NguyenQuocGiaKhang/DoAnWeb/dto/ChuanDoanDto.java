package NguyenQuocGiaKhang.DoAnWeb.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ChuanDoanDto {

    @Size(max = 10)
    private String maCd;

    @NotBlank(message = "Tên chuẩn đoán không được để trống")
    @Size(max = 200)
    private String tenCd;

    @Size(max = 500)
    private String moTa;


    public String getMaCd() {
        return maCd;
    }

    public void setMaCd(String maCd) {
        this.maCd = maCd;
    }

    public String getTenCd() {
        return tenCd;
    }

    public void setTenCd(String tenCd) {
        this.tenCd = tenCd;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
