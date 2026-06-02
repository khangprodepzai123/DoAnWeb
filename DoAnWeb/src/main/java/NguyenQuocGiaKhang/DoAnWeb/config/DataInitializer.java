package NguyenQuocGiaKhang.DoAnWeb.config;

import NguyenQuocGiaKhang.DoAnWeb.Model.*;
import NguyenQuocGiaKhang.DoAnWeb.Repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner seedData(
            ChuanDoanRepository chuanDoanRepository,
            NhanVienRepository nhanVienRepository,
            BacSiRepository bacSiRepository,
            ThuocRepository thuocRepository,
            BenhNhanRepository benhNhanRepository,
            TaiKhoanBenhNhanRepository taiKhoanBenhNhanRepository,
            PasswordEncoder passwordEncoder) {
        return args -> {
            if (chuanDoanRepository.count() == 0) {
                ChuanDoan cd = new ChuanDoan();
                cd.setMaCd("CD001");
                cd.setTenCd("Chưa xác định");
                cd.setMoTa("Mặc định");
                chuanDoanRepository.save(cd);
            }
            if (nhanVienRepository.count() == 0) {
                NhanVien nv = new NhanVien();
                nv.setMaNv("NV001");
                nv.setHoTenNv("Nguyễn Văn Thu ngân");
                nhanVienRepository.save(nv);
            }
            if (bacSiRepository.count() == 0) {
                BacSi bs = new BacSi();
                bs.setMaBs("BS001");
                bs.setHoTenBs("Nguyễn Thành Tâm");
                bs.setChuyenKhoa("Nội khoa");
                bs.setTrinhDoHocVan("BSCKII");
                bacSiRepository.save(bs);
            }
            if (thuocRepository.count() == 0) {
                Thuoc t = new Thuoc();
                t.setMaThuoc("T001");
                t.setTenThuoc("Paracetamol");
                t.setGiaBan(new BigDecimal("5000"));
                t.setSoLuong(100);
                t.setHdsd("Uống sau ăn");
                thuocRepository.save(t);
            }

            if (benhNhanRepository.count() == 0) {
                BenhNhan bn = new BenhNhan();
                bn.setMaBn("BN001");
                bn.setHoTenBn("Bệnh nhân demo");
                bn.setSdt("0900000000");
                benhNhanRepository.save(bn);
            }

            // Seed tài khoản demo để đăng nhập
            if (!taiKhoanBenhNhanRepository.existsByTenDangNhap("nhanvien")) {
                TaiKhoanBenhNhan tk = new TaiKhoanBenhNhan();
                tk.setMaTk("TK001");
                tk.setTenDangNhap("nhanvien");
                tk.setMatKhau(passwordEncoder.encode("123456"));
                tk.setVaiTro(VaiTro.NHAN_VIEN);
                tk.setNhanVien(nhanVienRepository.findById("NV001").orElse(null));
                taiKhoanBenhNhanRepository.save(tk);
            }

            if (!taiKhoanBenhNhanRepository.existsByTenDangNhap("benhnhan")) {
                TaiKhoanBenhNhan tk = new TaiKhoanBenhNhan();
                tk.setMaTk("TK002");
                tk.setTenDangNhap("benhnhan");
                tk.setMatKhau(passwordEncoder.encode("123456"));
                tk.setVaiTro(VaiTro.BENH_NHAN);
                tk.setBenhNhan(benhNhanRepository.findById("BN001").orElse(null));
                tk.setHoTenBn("Bệnh nhân demo");
                taiKhoanBenhNhanRepository.save(tk);
            }
        };
    }
}
