package NguyenQuocGiaKhang.DoAnWeb.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "HoaDon")
public class HoaDon {

    @Id
    @Column(name = "MaHd", length = 10)
    private String maHd;

    @Column(name = "ThanhTien")
    private BigDecimal thanhTien;

    @Column(name = "NgayLap")
    private LocalDate ngayLap;

    @Column(name = "DiemTichLuySuDung")
    private Integer diemTichLuySuDung;

    @OneToOne
    @JoinColumn(name = "MaKham")
    private KhamBenh khamBenh;

    @ManyToOne
    @JoinColumn(name = "MaNv")
    private NhanVien nhanVien;

    @OneToMany(mappedBy = "hoaDon")
    private List<ChiTietHoaDon> chiTietHoaDons = new ArrayList<>();
}