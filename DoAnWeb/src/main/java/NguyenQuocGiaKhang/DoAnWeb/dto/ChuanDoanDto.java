package NguyenQuocGiaKhang.DoAnWeb.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChuanDoanDto {

    @Size(max = 10)
    private String maCd;

    @NotBlank(message = "Tên chuẩn đoán không được để trống")
    @Size(max = 200)
    private String tenCd;

    @Size(max = 500)
    private String moTa;
}
