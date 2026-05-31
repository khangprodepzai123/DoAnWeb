package NguyenQuocGiaKhang.DoAnWeb.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import NguyenQuocGiaKhang.DoAnWeb.Model.ChuanDoan;
import NguyenQuocGiaKhang.DoAnWeb.Repository.ChuanDoanRepository;

@Service
public class ChuanDoanService {

    private final ChuanDoanRepository chuanDoanRepository;

    public ChuanDoanService(ChuanDoanRepository chuanDoanRepository) {
        this.chuanDoanRepository = chuanDoanRepository;
    }

    public List<ChuanDoan> getAll() {
        return chuanDoanRepository.findAll();
    }

    public ChuanDoan getById(String maCd) {
        return chuanDoanRepository.findById(maCd).orElse(null);
    }

    public ChuanDoan save(ChuanDoan chuanDoan) {
        return chuanDoanRepository.save(chuanDoan);
    }

    public void delete(String maCd) {
        chuanDoanRepository.deleteById(maCd);
    }
}