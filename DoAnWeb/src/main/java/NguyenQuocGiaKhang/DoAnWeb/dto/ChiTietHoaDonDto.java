package NguyenQuocGiaKhang.DoAnWeb.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ChiTietHoaDonDto {

    @NotBlank(message = "Mã hóa đơn không được để trống")
    private String maHd;

    @NotBlank(message = "Mã thuốc không được để trống")
    private String maThuoc;

    private String tenThuoc;

    @NotNull(message = "Số lượng không được để trống")
    @Min(value = 1, message = "Số lượng phải >= 1")
    private Integer soLuong;

    private BigDecimal donGia;

    private BigDecimal thanhTien;
}
