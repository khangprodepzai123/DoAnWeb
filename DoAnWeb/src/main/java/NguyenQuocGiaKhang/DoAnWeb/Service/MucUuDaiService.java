package NguyenQuocGiaKhang.DoAnWeb.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import NguyenQuocGiaKhang.DoAnWeb.Model.MucUuDai;
import NguyenQuocGiaKhang.DoAnWeb.Repository.MucUuDaiRepository;

@Service
public class MucUuDaiService {

    private final MucUuDaiRepository repository;

    public MucUuDaiService(MucUuDaiRepository repository) {
        this.repository = repository;
    }

    public List<MucUuDai> getAll() {
        return repository.findAll();
    }

    public MucUuDai getById(String maMuc) {
        return repository.findById(maMuc).orElse(null);
    }

    public MucUuDai save(MucUuDai mucUuDai) {
        return repository.save(mucUuDai);
    }

    public void delete(String maMuc) {
        repository.deleteById(maMuc);
    }
}