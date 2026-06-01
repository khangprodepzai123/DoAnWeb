package NguyenQuocGiaKhang.DoAnWeb.Service;

import NguyenQuocGiaKhang.DoAnWeb.Model.BenhNhan;
import NguyenQuocGiaKhang.DoAnWeb.Repository.BenhNhanRepository;
import NguyenQuocGiaKhang.DoAnWeb.exception.BusinessException;
import NguyenQuocGiaKhang.DoAnWeb.exception.ResourceNotFoundException;
import NguyenQuocGiaKhang.DoAnWeb.util.MaIdGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BenhNhanService {

    private static final String MA_PREFIX = "BN";

    private final BenhNhanRepository benhNhanRepository;

    public BenhNhanService(BenhNhanRepository benhNhanRepository) {
        this.benhNhanRepository = benhNhanRepository;
    }

    @Transactional(readOnly = true)
    public List<BenhNhan> getAll() {
        return benhNhanRepository.findAll();
    }

    @Transactional(readOnly = true)
    public BenhNhan getById(String maBn) {
        return benhNhanRepository.findById(maBn)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy bệnh nhân: " + maBn));
    }

    public BenhNhan save(BenhNhan benhNhan) {
        if (benhNhan.getHoTenBn() == null || benhNhan.getHoTenBn().isBlank()) {
            throw new BusinessException("Họ tên bệnh nhân không được để trống");
        }
        if (benhNhan.getMaBn() == null || benhNhan.getMaBn().isBlank()) {
            String lastMa = benhNhanRepository.findTopByOrderByMaBnDesc()
                    .map(BenhNhan::getMaBn)
                    .orElse(null);
            benhNhan.setMaBn(MaIdGenerator.nextMa(MA_PREFIX, lastMa));
        } else {
            benhNhan.setMaBn(benhNhan.getMaBn().trim());
        }
        return benhNhanRepository.save(benhNhan);
    }

    public void delete(String maBn) {
        if (!benhNhanRepository.existsById(maBn)) {
            throw new ResourceNotFoundException("Không tìm thấy bệnh nhân để xóa: " + maBn);
        }
        benhNhanRepository.deleteById(maBn);
    }
}
