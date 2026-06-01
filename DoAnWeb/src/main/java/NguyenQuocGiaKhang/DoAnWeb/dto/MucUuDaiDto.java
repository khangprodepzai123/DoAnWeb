package NguyenQuocGiaKhang.DoAnWeb.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
}
