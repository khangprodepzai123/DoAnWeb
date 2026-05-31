package NguyenQuocGiaKhang.DoAnWeb.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
}