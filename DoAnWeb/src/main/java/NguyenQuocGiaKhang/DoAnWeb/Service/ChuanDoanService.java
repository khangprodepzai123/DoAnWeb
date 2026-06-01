package NguyenQuocGiaKhang.DoAnWeb.Service;

import NguyenQuocGiaKhang.DoAnWeb.Model.ChuanDoan;
import NguyenQuocGiaKhang.DoAnWeb.Repository.ChuanDoanRepository;
import NguyenQuocGiaKhang.DoAnWeb.dto.ChuanDoanDto;
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
public class ChuanDoanService {

    private static final String MA_PREFIX = "CD";

    private final ChuanDoanRepository repository;
    private final DtoMapper dtoMapper;

    public ChuanDoanService(ChuanDoanRepository repository, DtoMapper dtoMapper) {
        this.repository = repository;
        this.dtoMapper = dtoMapper;
    }

    @Transactional(readOnly = true)
    public List<ChuanDoanDto> getAllDtos() {
        return repository.findAll().stream().map(dtoMapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ChuanDoanDto getDtoById(String maCd) {
        return dtoMapper.toDto(getEntityById(maCd));
    }

    @Transactional(readOnly = true)
    public ChuanDoan getEntityById(String maCd) {
        return repository.findById(maCd)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy chuẩn đoán: " + maCd));
    }

    public ChuanDoanDto saveDto(ChuanDoanDto dto) {
        ChuanDoan entity = dtoMapper.toEntity(dto);
        if (entity.getTenCd() == null || entity.getTenCd().isBlank()) {
            throw new BusinessException("Tên chuẩn đoán không được để trống");
        }
        if (entity.getMaCd() == null || entity.getMaCd().isBlank()) {
            String last = repository.findTopByOrderByMaCdDesc().map(ChuanDoan::getMaCd).orElse(null);
            entity.setMaCd(MaIdGenerator.nextMa(MA_PREFIX, last));
        }
        return dtoMapper.toDto(repository.save(entity));
    }

    public void delete(String maCd) {
        if (!repository.existsById(maCd)) {
            throw new ResourceNotFoundException("Không tìm thấy chuẩn đoán để xóa: " + maCd);
        }
        repository.deleteById(maCd);
    }
}
