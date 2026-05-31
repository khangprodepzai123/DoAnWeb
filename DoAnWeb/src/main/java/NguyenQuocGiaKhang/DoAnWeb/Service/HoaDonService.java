package NguyenQuocGiaKhang.DoAnWeb.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import NguyenQuocGiaKhang.DoAnWeb.Model.HoaDon;
import NguyenQuocGiaKhang.DoAnWeb.Repository.HoaDonRepository;

@Service
public class HoaDonService {

    private final HoaDonRepository hoaDonRepository;

    public HoaDonService(HoaDonRepository hoaDonRepository) {
        this.hoaDonRepository = hoaDonRepository;
    }

    public List<HoaDon> getAll() {
        return hoaDonRepository.findAll();
    }

    public HoaDon getById(String maHd) {
        return hoaDonRepository.findById(maHd).orElse(null);
    }

    public HoaDon save(HoaDon hoaDon) {
        return hoaDonRepository.save(hoaDon);
    }

    public void delete(String maHd) {
        hoaDonRepository.deleteById(maHd);
    }
}