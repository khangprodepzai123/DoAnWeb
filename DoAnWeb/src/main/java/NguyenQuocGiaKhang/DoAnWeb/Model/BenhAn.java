package NguyenQuocGiaKhang.DoAnWeb.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "BenhAn")
public class BenhAn {

    @Id
    @Column(name = "MaBenhAn", length = 10)
    private String maBenhAn;

    @Column(name = "MaKham", length = 10)
    private String maKham;

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

    @Column(name = "ChuanDoan", length = 500)
    private String chuanDoan;

    @Column(name = "HuongXuTri", length = 500)
    private String huongXuTri;

    @Column(name = "LoaiKham", length = 50)
    private String loaiKham;

    @Column(name = "XuTriKham", length = 100)
    private String xuTriKham;

    @Column(name = "NgayKham")
    private LocalDate ngayKham;

    @Column(name = "NgayLuu")
    private LocalDateTime ngayLuu;

    @ManyToOne
    @JoinColumn(name = "MaBn")
    private BenhNhan benhNhan;

    @ManyToOne
    @JoinColumn(name = "MaBs")
    private BacSi bacSi;

    @OneToMany(mappedBy = "benhAn")
    private List<BenhAnToaThuoc> benhAnToaThuocs = new ArrayList<>();
}