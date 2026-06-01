package NguyenQuocGiaKhang.DoAnWeb.Service;

import NguyenQuocGiaKhang.DoAnWeb.Model.NhanVien;
import NguyenQuocGiaKhang.DoAnWeb.Repository.NhanVienRepository;
import NguyenQuocGiaKhang.DoAnWeb.dto.NhanVienDto;
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
public class NhanVienService {

    private static final String MA_PREFIX = "NV";

    private final NhanVienRepository repository;
    private final DtoMapper dtoMapper;

    public NhanVienService(NhanVienRepository repository, DtoMapper dtoMapper) {
        this.repository = repository;
        this.dtoMapper = dtoMapper;
    }

    @Transactional(readOnly = true)
    public List<NhanVienDto> getAllDtos() {
        return repository.findAll().stream().map(dtoMapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public NhanVienDto getDtoById(String maNv) {
        return dtoMapper.toDto(getEntityById(maNv));
    }

    @Transactional(readOnly = true)
    public NhanVien getEntityById(String maNv) {
        return repository.findById(maNv)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy nhân viên: " + maNv));
    }

    public NhanVienDto saveDto(NhanVienDto dto) {
        NhanVien entity = dtoMapper.toEntity(dto);
        if (entity.getHoTenNv() == null || entity.getHoTenNv().isBlank()) {
            throw new BusinessException("Họ tên nhân viên không được để trống");
        }
        if (entity.getMaNv() == null || entity.getMaNv().isBlank()) {
            String last = repository.findTopByOrderByMaNvDesc().map(NhanVien::getMaNv).orElse(null);
            entity.setMaNv(MaIdGenerator.nextMa(MA_PREFIX, last));
        }
        return dtoMapper.toDto(repository.save(entity));
    }

    public void delete(String maNv) {
        if (!repository.existsById(maNv)) {
            throw new ResourceNotFoundException("Không tìm thấy nhân viên để xóa: " + maNv);
        }
        repository.deleteById(maNv);
    }
}
