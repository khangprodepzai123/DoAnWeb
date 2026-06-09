package NguyenQuocGiaKhang.DoAnWeb.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ChiTietHoaDonId implements Serializable {

    @Column(name = "ma_hd", length = 10)
    private String maHd;

    @Column(name = "ma_thuoc", length = 10)
    private String maThuoc;


    public String getMaHd() {
        return maHd;
    }

    public void setMaHd(String maHd) {
        this.maHd = maHd;
    }

    public String getMaThuoc() {
        return maThuoc;
    }

    public void setMaThuoc(String maThuoc) {
        this.maThuoc = maThuoc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChiTietHoaDonId that = (ChiTietHoaDonId) o;
        return java.util.Objects.equals(maHd, that.maHd) && java.util.Objects.equals(maThuoc, that.maThuoc);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(maHd, maThuoc);
    }
}
