package NguyenQuocGiaKhang.DoAnWeb.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ToaThuocId implements Serializable {

    @Column(name = "ma_kham", length = 10)
    private String maKham;

    @Column(name = "ma_thuoc", length = 10)
    private String maThuoc;

    public String getMaKham() {
        return maKham;
    }

    public void setMaKham(String maKham) {
        this.maKham = maKham;
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
        ToaThuocId that = (ToaThuocId) o;
        return java.util.Objects.equals(maKham, that.maKham) && java.util.Objects.equals(maThuoc, that.maThuoc);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(maKham, maThuoc);
    }
}
