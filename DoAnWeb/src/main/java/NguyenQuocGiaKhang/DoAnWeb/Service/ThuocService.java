package NguyenQuocGiaKhang.DoAnWeb.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import NguyenQuocGiaKhang.DoAnWeb.Model.Thuoc;
import NguyenQuocGiaKhang.DoAnWeb.Repository.ThuocRepository;

@Service
public class ThuocService {

    private final ThuocRepository thuocRepository;

    public ThuocService(ThuocRepository thuocRepository) {
        this.thuocRepository = thuocRepository;
    }

    public List<Thuoc> getAll() {
        return thuocRepository.findAll();
    }

    public Thuoc getById(String maThuoc) {
        return thuocRepository.findById(maThuoc).orElse(null);
    }

    public Thuoc save(Thuoc thuoc) {
        return thuocRepository.save(thuoc);
    }

    public void delete(String maThuoc) {
        thuocRepository.deleteById(maThuoc);
    }
}