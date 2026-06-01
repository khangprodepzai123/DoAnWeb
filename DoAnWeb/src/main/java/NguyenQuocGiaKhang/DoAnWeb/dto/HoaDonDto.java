package NguyenQuocGiaKhang.DoAnWeb.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class HoaDonDto {

    private String maHd;
    private String maKham;
    private String maNv;
    private LocalDate ngayLap;
    private BigDecimal thanhTien;
    private Integer diemTichLuySuDung;

    private String hoTenBn;
    private String hoTenNv;
    private String trangThaiKham;

    private List<ChiTietHoaDonDto> chiTiets = new ArrayList<>();
}
