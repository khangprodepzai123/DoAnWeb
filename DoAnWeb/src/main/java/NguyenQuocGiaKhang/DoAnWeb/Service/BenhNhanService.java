package NguyenQuocGiaKhang.DoAnWeb.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import NguyenQuocGiaKhang.DoAnWeb.Model.BenhNhan;
import NguyenQuocGiaKhang.DoAnWeb.Repository.BenhNhanRepository;

@Service
public class BenhNhanService {

    private final BenhNhanRepository benhNhanRepository;

    public BenhNhanService(BenhNhanRepository benhNhanRepository) {
        this.benhNhanRepository = benhNhanRepository;
    }

    public List<BenhNhan> getAll() {
        return benhNhanRepository.findAll();
    }

    public BenhNhan getById(String maBn) {
        return benhNhanRepository.findById(maBn).orElse(null);
    }

    public BenhNhan save(BenhNhan benhNhan) {
        return benhNhanRepository.save(benhNhan);
    }

    public void delete(String maBn) {
        benhNhanRepository.deleteById(maBn);
    }
}