package NguyenQuocGiaKhang.DoAnWeb.Model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "KhamBenh")
public class KhamBenh {

    @Id
    @Column(name = "MaKham", length = 10)
    private String maKham;

    @Column(name = "ChuanDoan", length = 500)
    private String chuanDoan;

    @Column(name = "HuongXuTri", length = 500)
    private String huongXuTri;

    @Column(name = "LyDoKham", length = 500)
    private String lyDoKham;

    @Column(name = "QuaTrinhBenhLy", length = 1000)
    private String quaTrinhBenhLy;

    @Column(name = "TienSuBenhNhan", length = 500)
    private String tienSuBenhNhan;

    @Column(name = "TienSuGiaDinh", length = 500)
    private String tienSuGiaDinh;

    @Column(name = "KhamBoPhan", length = 1000)
    private String khamBoPhan;

    @Column(name = "LoaiKham", length = 50)
    private String loaiKham;

    @Column(name = "XuTriKham", length = 100)
    private String xuTriKham;

    @Column(name = "NgayKham")
    private LocalDate ngayKham;

    @Column(name = "TrangThai", length = 50)
    private String trangThai;

    @ManyToOne
    @JoinColumn(name = "MaBs")
    private BacSi bacSi;

    @OneToOne
    @JoinColumn(name = "MaBn")
    private BenhNhan benhNhan;

    @ManyToOne
    @JoinColumn(name = "MaCd")
    private ChuanDoan chuanDoanEntity;

    @OneToOne(mappedBy = "khamBenh")
    private HoaDon hoaDon;

    @OneToMany(mappedBy = "khamBenh")
    private List<ToaThuoc> toaThuocs = new ArrayList<>();

    public KhamBenh() {
    }

    // Getter Setter
}