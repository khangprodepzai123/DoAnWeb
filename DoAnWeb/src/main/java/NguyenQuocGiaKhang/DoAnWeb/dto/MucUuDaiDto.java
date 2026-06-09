package NguyenQuocGiaKhang.DoAnWeb.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class MucUuDaiDto {

    @Size(max = 10)
    private String maMuc;

    @NotBlank(message = "Tên mức ưu đãi không được để trống")
    @Size(max = 50)
    private String tenMuc;

    @Min(value = 0, message = "Điểm tối thiểu phải >= 0")
    private Integer diemToiThieu;

    @Size(max = 200)
    private String moTa;


    public String getMaMuc() {
        return maMuc;
    }

    public void setMaMuc(String maMuc) {
        this.maMuc = maMuc;
    }

    public String getTenMuc() {
        return tenMuc;
    }

    public void setTenMuc(String tenMuc) {
        this.tenMuc = tenMuc;
    }

    public Integer getDiemToiThieu() {
        return diemToiThieu;
    }

    public void setDiemToiThieu(Integer diemToiThieu) {
        this.diemToiThieu = diemToiThieu;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
