package NguyenQuocGiaKhang.DoAnWeb.Service;

import NguyenQuocGiaKhang.DoAnWeb.Model.BacSi;
import NguyenQuocGiaKhang.DoAnWeb.Repository.BacSiRepository;
import NguyenQuocGiaKhang.DoAnWeb.dto.BacSiDto;
import NguyenQuocGiaKhang.DoAnWeb.exception.BusinessException;
import NguyenQuocGiaKhang.DoAnWeb.exception.ResourceNotFoundException;
import NguyenQuocGiaKhang.DoAnWeb.mapper.DtoMapper;
import NguyenQuocGiaKhang.DoAnWeb.util.FileStorageService;
import NguyenQuocGiaKhang.DoAnWeb.util.MaIdGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BacSiService {

    private static final String MA_PREFIX = "BS";

    private final BacSiRepository repository;
    private final DtoMapper dtoMapper;
    private final FileStorageService fileStorageService;

    public BacSiService(
            BacSiRepository repository,
            DtoMapper dtoMapper,
            FileStorageService fileStorageService) {
        this.repository = repository;
        this.dtoMapper = dtoMapper;
        this.fileStorageService = fileStorageService;
    }

    @Transactional(readOnly = true)
    public List<BacSiDto> getAllDtos() {
        return repository.findAll().stream().map(dtoMapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BacSiDto getDtoById(String maBs) {
        return dtoMapper.toDto(getEntityById(maBs));
    }

    @Transactional(readOnly = true)
    public BacSi getEntityById(String maBs) {
        return repository.findById(maBs)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy bác sĩ: " + maBs));
    }

    public BacSiDto saveDto(BacSiDto dto, MultipartFile anhFile) {
        BacSi entity = dtoMapper.toEntity(dto);
        if (entity.getHoTenBs() == null || entity.getHoTenBs().isBlank()) {
            throw new BusinessException("Họ tên bác sĩ không được để trống");
        }

        boolean isUpdate = entity.getMaBs() != null && !entity.getMaBs().isBlank()
                && repository.existsById(entity.getMaBs());
        BacSi existing = null;
        if (isUpdate) {
            existing = getEntityById(entity.getMaBs());
            entity.setAnhBs(existing.getAnhBs());
        }

        if (entity.getMaBs() == null || entity.getMaBs().isBlank()) {
            String last = repository.findTopByOrderByMaBsDesc().map(BacSi::getMaBs).orElse(null);
            entity.setMaBs(MaIdGenerator.nextMa(MA_PREFIX, last));
        }

        String newFileName = fileStorageService.storeBacSiImage(anhFile, entity.getMaBs());
        if (newFileName != null) {
            if (existing != null && existing.getAnhBs() != null
                    && !existing.getAnhBs().equals(newFileName)) {
                fileStorageService.deleteBacSiImage(existing.getAnhBs());
            }
            entity.setAnhBs(newFileName);
        }

        return dtoMapper.toDto(repository.save(entity));
    }

    public void delete(String maBs) {
        BacSi entity = getEntityById(maBs);
        fileStorageService.deleteBacSiImage(entity.getAnhBs());
        repository.deleteById(maBs);
    }
}
