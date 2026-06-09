package NguyenQuocGiaKhang.DoAnWeb.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "BenhAnToaThuoc")
public class BenhAnToaThuoc {

    @EmbeddedId
    private BenhAnToaThuocId id;

    @Column(name = "SoLuong")
    private Integer soLuong;

    @Column(name = "LieuDung", length = 200)
    private String lieuDung;

    @Column(name = "CachDung", length = 300)
    private String cachDung;

    @ManyToOne
    @MapsId("maBenhAn")
    @JoinColumn(name = "MaBenhAn")
    private BenhAn benhAn;

    @ManyToOne
    @MapsId("maThuoc")
    @JoinColumn(name = "MaThuoc")
    private Thuoc thuoc;


    public BenhAnToaThuocId getId() {
        return id;
    }

    public void setId(BenhAnToaThuocId id) {
        this.id = id;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public String getLieuDung() {
        return lieuDung;
    }

    public void setLieuDung(String lieuDung) {
        this.lieuDung = lieuDung;
    }

    public String getCachDung() {
        return cachDung;
    }

    public void setCachDung(String cachDung) {
        this.cachDung = cachDung;
    }

    public BenhAn getBenhAn() {
        return benhAn;
    }

    public void setBenhAn(BenhAn benhAn) {
        this.benhAn = benhAn;
    }

    public Thuoc getThuoc() {
        return thuoc;
    }

    public void setThuoc(Thuoc thuoc) {
        this.thuoc = thuoc;
    }
}
