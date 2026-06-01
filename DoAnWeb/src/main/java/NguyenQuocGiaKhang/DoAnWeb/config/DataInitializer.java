package NguyenQuocGiaKhang.DoAnWeb.config;

import NguyenQuocGiaKhang.DoAnWeb.Model.*;
import NguyenQuocGiaKhang.DoAnWeb.Repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner seedData(
            ChuanDoanRepository chuanDoanRepository,
            NhanVienRepository nhanVienRepository,
            BacSiRepository bacSiRepository,
            ThuocRepository thuocRepository) {
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
        };
    }
}
