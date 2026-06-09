package NguyenQuocGiaKhang.DoAnWeb.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "ToaThuoc")
public class ToaThuoc {

    @EmbeddedId
    private ToaThuocId id;

    @Column(name = "SoLuong")
    private Integer soLuong;

    @Column(name = "LieuDung", length = 200)
    private String lieuDung;

    @Column(name = "CachDung", length = 300)
    private String cachDung;

    @ManyToOne
    @MapsId("maKham")
    @JoinColumn(name = "MaKham")
    private KhamBenh khamBenh;

    @ManyToOne
    @MapsId("maThuoc")
    @JoinColumn(name = "MaThuoc")
    private Thuoc thuoc;


    public ToaThuocId getId() {
        return id;
    }

    public void setId(ToaThuocId id) {
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

    public KhamBenh getKhamBenh() {
        return khamBenh;
    }

    public void setKhamBenh(KhamBenh khamBenh) {
        this.khamBenh = khamBenh;
    }

    public Thuoc getThuoc() {
        return thuoc;
    }

    public void setThuoc(Thuoc thuoc) {
        this.thuoc = thuoc;
    }
}
