package NguyenQuocGiaKhang.DoAnWeb.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
}