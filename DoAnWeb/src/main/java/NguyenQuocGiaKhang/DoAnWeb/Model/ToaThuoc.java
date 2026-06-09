package NguyenQuocGiaKhang.DoAnWeb.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "toa_thuoc")
public class ToaThuoc {

    @EmbeddedId
    private ToaThuocId id = new ToaThuocId();

    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "lieu_dung", length = 200)
    private String lieuDung;

    @Column(name = "cach_dung", length = 300)
    private String cachDung;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_kham", insertable = false, updatable = false)
    private KhamBenh khamBenh;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_thuoc", insertable = false, updatable = false)
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
