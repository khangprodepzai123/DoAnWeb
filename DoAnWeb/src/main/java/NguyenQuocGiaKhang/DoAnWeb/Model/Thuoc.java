package NguyenQuocGiaKhang.DoAnWeb.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Thuoc")
public class Thuoc {

    @Id
    @Column(name = "MaThuoc", length = 10)
    private String maThuoc;

    @Column(name = "TenThuoc", nullable = false, length = 100)
    private String tenThuoc;

    @Column(name = "GiaBan")
    private BigDecimal giaBan;

    @Column(name = "SoLuong")
    private Integer soLuong;

    @Column(name = "HDSD", length = 500)
    private String hdsd;

    @OneToMany(mappedBy = "thuoc")
    private List<ChiTietHoaDon> chiTietHoaDons = new ArrayList<>();

    @OneToMany(mappedBy = "thuoc")
    private List<ToaThuoc> toaThuocs = new ArrayList<>();
}