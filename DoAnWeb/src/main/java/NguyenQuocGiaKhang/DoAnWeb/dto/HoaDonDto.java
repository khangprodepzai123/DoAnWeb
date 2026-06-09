package NguyenQuocGiaKhang.DoAnWeb.dto;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public String getMaNv() {
        return maNv;
    }

    public void setMaNv(String maNv) {
        this.maNv = maNv;
    }

    public LocalDate getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(LocalDate ngayLap) {
        this.ngayLap = ngayLap;
    }

    public BigDecimal getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(BigDecimal thanhTien) {
        this.thanhTien = thanhTien;
    }

    public Integer getDiemTichLuySuDung() {
        return diemTichLuySuDung;
    }

    public void setDiemTichLuySuDung(Integer diemTichLuySuDung) {
        this.diemTichLuySuDung = diemTichLuySuDung;
    }

    public String getHoTenBn() {
        return hoTenBn;
    }

    public void setHoTenBn(String hoTenBn) {
        this.hoTenBn = hoTenBn;
    }

    public String getHoTenNv() {
        return hoTenNv;
    }

    public void setHoTenNv(String hoTenNv) {
        this.hoTenNv = hoTenNv;
    }

    public String getTrangThaiKham() {
        return trangThaiKham;
    }

    public void setTrangThaiKham(String trangThaiKham) {
        this.trangThaiKham = trangThaiKham;
    }

    public List<ChiTietHoaDonDto> getChiTiets() {
        return chiTiets;
    }

    public void setChiTiets(List<ChiTietHoaDonDto> chiTiets) {
        this.chiTiets = chiTiets;
    }
}
