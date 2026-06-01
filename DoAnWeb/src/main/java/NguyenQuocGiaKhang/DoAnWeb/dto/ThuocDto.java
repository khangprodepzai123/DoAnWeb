package NguyenQuocGiaKhang.DoAnWeb.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ThuocDto {

    @Size(max = 10)
    private String maThuoc;

    @NotBlank(message = "Tên thuốc không được để trống")
    @Size(max = 100)
    private String tenThuoc;

    @NotNull(message = "Giá bán không được để trống")
    @Min(value = 0, message = "Giá bán phải >= 0")
    private BigDecimal giaBan;

    @NotNull(message = "Số lượng không được để trống")
    @Min(value = 0, message = "Số lượng phải >= 0")
    private Integer soLuong;

    @Size(max = 500)
    private String hdsd;
}
