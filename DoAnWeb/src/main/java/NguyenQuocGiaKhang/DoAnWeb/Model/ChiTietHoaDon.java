package NguyenQuocGiaKhang.DoAnWeb.Model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "ChiTietHoaDon")
public class ChiTietHoaDon {

    @EmbeddedId
    private ChiTietHoaDonId id = new ChiTietHoaDonId();

    @Column(name = "SoLuong", nullable = false)
    private Integer soLuong;

    @Column(name = "DonGia", nullable = false)
    private BigDecimal donGia;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("maHd")
    @JoinColumn(name = "MaHd")
    private HoaDon hoaDon;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("maThuoc")
    @JoinColumn(name = "MaThuoc")
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
