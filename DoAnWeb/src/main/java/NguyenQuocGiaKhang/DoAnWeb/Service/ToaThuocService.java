package NguyenQuocGiaKhang.DoAnWeb.Service;

import NguyenQuocGiaKhang.DoAnWeb.Model.KhamBenh;
import NguyenQuocGiaKhang.DoAnWeb.Model.Thuoc;
import NguyenQuocGiaKhang.DoAnWeb.Model.ToaThuoc;
import NguyenQuocGiaKhang.DoAnWeb.Model.ToaThuocId;
import NguyenQuocGiaKhang.DoAnWeb.Repository.ToaThuocRepository;
import NguyenQuocGiaKhang.DoAnWeb.dto.ToaThuocDto;
import NguyenQuocGiaKhang.DoAnWeb.exception.BusinessException;
import NguyenQuocGiaKhang.DoAnWeb.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ToaThuocService {

    private final ToaThuocRepository toaThuocRepository;
    private final KhamBenhService khamBenhService;
    private final ThuocService thuocService;

    public ToaThuocService(
            ToaThuocRepository toaThuocRepository,
            KhamBenhService khamBenhService,
            ThuocService thuocService) {
        this.toaThuocRepository = toaThuocRepository;
        this.khamBenhService = khamBenhService;
        this.thuocService = thuocService;
    }

    @Transactional(readOnly = true)
    public List<ToaThuocDto> getDtosByMaKham(String maKham) {
        return toaThuocRepository.findByKhamBenh_MaKham(maKham).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public void addThuoc(String maKham, String maThuoc, Integer soLuong, String lieuDung, String cachDung) {
        if (maThuoc == null || maThuoc.isBlank()) {
            throw new BusinessException("Vui lòng chọn thuốc");
        }
        if (soLuong == null || soLuong <= 0) {
            throw new BusinessException("Số lượng phải lớn hơn 0");
        }

        KhamBenh khamBenh = khamBenhService.getEntityById(maKham);
        Thuoc thuoc = thuocService.getEntityById(maThuoc);

        ToaThuocId id = new ToaThuocId();
        id.setMaKham(maKham);
        id.setMaThuoc(maThuoc);
        if (toaThuocRepository.existsById(id)) {
            throw new BusinessException("Thuốc này đã có trong toa");
        }

        ToaThuoc entity = new ToaThuoc();
        entity.getId().setMaKham(maKham);
        entity.getId().setMaThuoc(maThuoc);
        entity.setKhamBenh(khamBenh);
        entity.setThuoc(thuoc);
        entity.setSoLuong(soLuong);
        entity.setLieuDung(lieuDung);
        entity.setCachDung(cachDung);
        toaThuocRepository.save(entity);
    }

    public void deleteThuoc(String maKham, String maThuoc) {
        ToaThuocId id = new ToaThuocId();
        id.setMaKham(maKham);
        id.setMaThuoc(maThuoc);
        if (!toaThuocRepository.existsById(id)) {
            throw new ResourceNotFoundException("Không tìm thấy thuốc trong toa");
        }
        toaThuocRepository.deleteById(id);
    }

    private ToaThuocDto toDto(ToaThuoc entity) {
        ToaThuocDto dto = new ToaThuocDto();
        if (entity.getId() != null) {
            dto.setMaKham(entity.getId().getMaKham());
            dto.setMaThuoc(entity.getId().getMaThuoc());
        }
        if (entity.getThuoc() != null) {
            dto.setTenThuoc(entity.getThuoc().getTenThuoc());
        }
        dto.setSoLuong(entity.getSoLuong());
        dto.setLieuDung(entity.getLieuDung());
        dto.setCachDung(entity.getCachDung());
        return dto;
    }
}
