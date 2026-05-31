package NguyenQuocGiaKhang.DoAnWeb.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import NguyenQuocGiaKhang.DoAnWeb.Model.BacSi;
import NguyenQuocGiaKhang.DoAnWeb.Repository.BacSiRepository;

@Service
public class BacSiService {

    private final BacSiRepository bacSiRepository;

    public BacSiService(BacSiRepository bacSiRepository) {
        this.bacSiRepository = bacSiRepository;
    }

    public List<BacSi> getAll() {
        return bacSiRepository.findAll();
    }

    public BacSi getById(String maBs) {
        return bacSiRepository.findById(maBs).orElse(null);
    }

    public BacSi save(BacSi bacSi) {
        return bacSiRepository.save(bacSi);
    }

    public void delete(String maBs) {
        bacSiRepository.deleteById(maBs);
    }
}