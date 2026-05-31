package NguyenQuocGiaKhang.DoAnWeb.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import NguyenQuocGiaKhang.DoAnWeb.Model.BenhAn;
import NguyenQuocGiaKhang.DoAnWeb.Repository.BenhAnRepository;

@Service
public class BenhAnService {

    private final BenhAnRepository repository;

    public BenhAnService(BenhAnRepository repository) {
        this.repository = repository;
    }

    public List<BenhAn> getAll() {
        return repository.findAll();
    }

    public BenhAn getById(String maBenhAn) {
        return repository.findById(maBenhAn).orElse(null);
    }

    public BenhAn save(BenhAn benhAn) {
        return repository.save(benhAn);
    }

    public void delete(String maBenhAn) {
        repository.deleteById(maBenhAn);
    }
}