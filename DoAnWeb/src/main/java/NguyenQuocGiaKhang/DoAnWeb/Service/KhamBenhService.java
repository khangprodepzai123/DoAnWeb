package NguyenQuocGiaKhang.DoAnWeb.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import NguyenQuocGiaKhang.DoAnWeb.Model.KhamBenh;
import NguyenQuocGiaKhang.DoAnWeb.Repository.KhamBenhRepository;

@Service
public class KhamBenhService {

    private final KhamBenhRepository khamBenhRepository;

    public KhamBenhService(KhamBenhRepository khamBenhRepository) {
        this.khamBenhRepository = khamBenhRepository;
    }

    public List<KhamBenh> getAll() {
        return khamBenhRepository.findAll();
    }

    public KhamBenh getById(String maKham) {
        return khamBenhRepository.findById(maKham).orElse(null);
    }

    public KhamBenh save(KhamBenh khamBenh) {
        return khamBenhRepository.save(khamBenh);
    }

    public void delete(String maKham) {
        khamBenhRepository.deleteById(maKham);
    }
}