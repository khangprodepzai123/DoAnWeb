package NguyenQuocGiaKhang.DoAnWeb.Model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class BenhAnToaThuocId implements Serializable {

    private String maBenhAn;

    private String maThuoc;


    public String getMaBenhAn() {
        return maBenhAn;
    }

    public void setMaBenhAn(String maBenhAn) {
        this.maBenhAn = maBenhAn;
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
        BenhAnToaThuocId that = (BenhAnToaThuocId) o;
        return java.util.Objects.equals(maBenhAn, that.maBenhAn) && java.util.Objects.equals(maThuoc, that.maThuoc);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(maBenhAn, maThuoc);
    }
}
