package NguyenQuocGiaKhang.DoAnWeb.dto;

import jakarta.validation.constraints.Min;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ThanhToanDto {

    private String maHd;
    private String maKham;
    private String hoTenBn;

    private BigDecimal tongTien;

    private Integer diemTichLuyHienCo;

    @Min(value = 0, message = "Điểm sử dụng phải >= 0")
    private Integer diemTichLuySuDung;

    private List<ChiTietHoaDonDto> chiTiets = new ArrayList<>();


    public String getMaHd() {
        return maHd;
    }

    public void setMaHd(String maHd) {
        this.maHd = maHd;
    }

    public String getMaKham() {
        return maKham;
    }

    public void setMaKham(String maKham) {
        this.maKham = maKham;
    }

    public String getHoTenBn() {
        return hoTenBn;
    }

    public void setHoTenBn(String hoTenBn) {
        this.hoTenBn = hoTenBn;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    public Integer getDiemTichLuyHienCo() {
        return diemTichLuyHienCo;
    }

    public void setDiemTichLuyHienCo(Integer diemTichLuyHienCo) {
        this.diemTichLuyHienCo = diemTichLuyHienCo;
    }

    public Integer getDiemTichLuySuDung() {
        return diemTichLuySuDung;
    }

    public void setDiemTichLuySuDung(Integer diemTichLuySuDung) {
        this.diemTichLuySuDung = diemTichLuySuDung;
    }

    public List<ChiTietHoaDonDto> getChiTiets() {
        return chiTiets;
    }

    public void setChiTiets(List<ChiTietHoaDonDto> chiTiets) {
        this.chiTiets = chiTiets;
    }
}
