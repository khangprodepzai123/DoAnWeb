package NguyenQuocGiaKhang.DoAnWeb.Model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "BenhNhan")
public class BenhNhan {

    @Id
    @Column(name = "MaBn", length = 10)
    private String maBn;

    @Column(name = "HoTenBn", nullable = false, length = 100)
    private String hoTenBn;

    @Column(name = "SDT", length = 15)
    private String sdt;

    @Column(name = "NgaySinh")
    private LocalDate ngaySinh;

    @Column(name = "GT", length = 10)
    private String gt;

    @Column(name = "DoiTuong", length = 50)
    private String doiTuong;

    @Column(name = "DiaChi", length = 200)
    private String diaChi;

    @Column(name = "BHYT", length = 20)
    private String bhyt;

    @OneToOne(mappedBy = "benhNhan")
    private KhamBenh khamBenh;

    @OneToOne(mappedBy = "benhNhan")
    private TaiKhoanBenhNhan taiKhoanBenhNhan;

    public BenhNhan() {
    }

    // Getter Setter
}