package NguyenQuocGiaKhang.DoAnWeb.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TaiKhoanBenhNhan")
public class TaiKhoanBenhNhan {

    @Id
    @Column(name = "MaTk", length = 10)
    private String maTk;

    @Column(name = "TenDangNhap", unique = true, nullable = false, length = 50)
    private String tenDangNhap;

    @Column(name = "MatKhau", nullable = false, length = 255)
    private String matKhau;

    @Column(name = "DiemTichLuy")
    private Integer diemTichLuy = 0;

    @Column(name = "HoTenBn", length = 255)
    private String hoTenBn;

    @Enumerated(EnumType.STRING)
    @Column(name = "VaiTro", nullable = false, length = 20)
    private VaiTro vaiTro = VaiTro.BENH_NHAN;

    @OneToOne
    @JoinColumn(name = "MaBn", unique = true)
    private BenhNhan benhNhan;

    @ManyToOne
    @JoinColumn(name = "MaNv")
    private NhanVien nhanVien;
}
