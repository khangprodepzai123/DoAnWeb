package NguyenQuocGiaKhang.DoAnWeb.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import NguyenQuocGiaKhang.DoAnWeb.Model.TaiKhoanBenhNhan;
import NguyenQuocGiaKhang.DoAnWeb.Repository.TaiKhoanBenhNhanRepository;

@Service
public class TaiKhoanBenhNhanService {

    private final TaiKhoanBenhNhanRepository repository;

    public TaiKhoanBenhNhanService(TaiKhoanBenhNhanRepository repository) {
        this.repository = repository;
    }

    public List<TaiKhoanBenhNhan> getAll() {
        return repository.findAll();
    }

    public TaiKhoanBenhNhan getById(String maTk) {
        return repository.findById(maTk).orElse(null);
    }

    public TaiKhoanBenhNhan save(TaiKhoanBenhNhan taiKhoan) {
        return repository.save(taiKhoan);
    }

    public void delete(String maTk) {
        repository.deleteById(maTk);
    }
}