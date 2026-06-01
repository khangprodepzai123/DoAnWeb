package NguyenQuocGiaKhang.DoAnWeb.dto;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ThanhToanDto {

    private String maHd;
    private String maKham;
    private String hoTenBn;

    private BigDecimal tongTien;

    private Integer diemTichLuyHienCo;

    @Min(value = 0, message = "Điểm sử dụng phải >= 0")
    private Integer diemTichLuySuDung;

    private List<ChiTietHoaDonDto> chiTiets = new ArrayList<>();
}
