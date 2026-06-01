package NguyenQuocGiaKhang.DoAnWeb.Service;

import NguyenQuocGiaKhang.DoAnWeb.Model.Thuoc;
import NguyenQuocGiaKhang.DoAnWeb.Repository.ThuocRepository;
import NguyenQuocGiaKhang.DoAnWeb.dto.ThuocDto;
import NguyenQuocGiaKhang.DoAnWeb.exception.BusinessException;
import NguyenQuocGiaKhang.DoAnWeb.exception.ResourceNotFoundException;
import NguyenQuocGiaKhang.DoAnWeb.mapper.DtoMapper;
import NguyenQuocGiaKhang.DoAnWeb.util.MaIdGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ThuocService {

    private static final String MA_PREFIX = "T";

    private final ThuocRepository repository;
    private final DtoMapper dtoMapper;

    public ThuocService(ThuocRepository repository, DtoMapper dtoMapper) {
        this.repository = repository;
        this.dtoMapper = dtoMapper;
    }

    @Transactional(readOnly = true)
    public List<ThuocDto> getAllDtos() {
        return repository.findAll().stream().map(dtoMapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ThuocDto getDtoById(String maThuoc) {
        return dtoMapper.toDto(getEntityById(maThuoc));
    }

    @Transactional(readOnly = true)
    public Thuoc getEntityById(String maThuoc) {
        return repository.findById(maThuoc)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thuốc: " + maThuoc));
    }

    public ThuocDto saveDto(ThuocDto dto) {
        Thuoc entity = dtoMapper.toEntity(dto);
        if (entity.getTenThuoc() == null || entity.getTenThuoc().isBlank()) {
            throw new BusinessException("Tên thuốc không được để trống");
        }
        if (entity.getMaThuoc() == null || entity.getMaThuoc().isBlank()) {
            String last = repository.findTopByOrderByMaThuocDesc().map(Thuoc::getMaThuoc).orElse(null);
            entity.setMaThuoc(MaIdGenerator.nextMa(MA_PREFIX, last));
        }
        if (entity.getGiaBan() == null) {
            throw new BusinessException("Giá bán không được để trống");
        }
        if (entity.getSoLuong() == null) {
            entity.setSoLuong(0);
        }
        return dtoMapper.toDto(repository.save(entity));
    }

    public void delete(String maThuoc) {
        if (!repository.existsById(maThuoc)) {
            throw new ResourceNotFoundException("Không tìm thấy thuốc để xóa: " + maThuoc);
        }
        repository.deleteById(maThuoc);
    }
}
