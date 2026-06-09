package NguyenQuocGiaKhang.DoAnWeb.Model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "chi_tiet_hoa_don")
public class ChiTietHoaDon {

    @EmbeddedId
    private ChiTietHoaDonId id = new ChiTietHoaDonId();

    @Column(name = "so_luong", nullable = false)
    private Integer soLuong;

    @Column(name = "don_gia", nullable = false)
    private BigDecimal donGia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_hd", insertable = false, updatable = false)
    private HoaDon hoaDon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_thuoc", insertable = false, updatable = false)
    private Thuoc thuoc;

    public BigDecimal getThanhTienDong() {
        if (soLuong == null || donGia == null) {
            return BigDecimal.ZERO;
        }
        return donGia.multiply(BigDecimal.valueOf(soLuong));
    }


    public ChiTietHoaDonId getId() {
        return id;
    }

    public void setId(ChiTietHoaDonId id) {
        this.id = id;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public BigDecimal getDonGia() {
        return donGia;
    }

    public void setDonGia(BigDecimal donGia) {
        this.donGia = donGia;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public Thuoc getThuoc() {
        return thuoc;
    }

    public void setThuoc(Thuoc thuoc) {
        this.thuoc = thuoc;
    }
}
