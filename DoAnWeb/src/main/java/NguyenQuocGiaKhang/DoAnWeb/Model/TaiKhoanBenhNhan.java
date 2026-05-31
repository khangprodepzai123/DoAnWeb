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

    @Column(name = "TenDangNhap", unique = true, length = 50)
    private String tenDangNhap;

    @Column(name = "MatKhau", length = 255)
    private String matKhau;

    @Column(name = "DiemTichLuy")
    private Integer diemTichLuy;

    @Column(name = "HoTenBn", length = 255)
    private String hoTenBn;

    @OneToOne
    @JoinColumn(name = "MaBn")
    private BenhNhan benhNhan;
}