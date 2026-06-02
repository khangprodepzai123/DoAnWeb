package NguyenQuocGiaKhang.DoAnWeb.Service;

import NguyenQuocGiaKhang.DoAnWeb.Model.BenhNhan;
import NguyenQuocGiaKhang.DoAnWeb.Model.NhanVien;
import NguyenQuocGiaKhang.DoAnWeb.Model.TaiKhoanBenhNhan;
import NguyenQuocGiaKhang.DoAnWeb.Model.VaiTro;
import NguyenQuocGiaKhang.DoAnWeb.Repository.TaiKhoanBenhNhanRepository;
import NguyenQuocGiaKhang.DoAnWeb.dto.TaiKhoanDto;
import NguyenQuocGiaKhang.DoAnWeb.exception.BusinessException;
import NguyenQuocGiaKhang.DoAnWeb.exception.ResourceNotFoundException;
import NguyenQuocGiaKhang.DoAnWeb.mapper.DtoMapper;
import NguyenQuocGiaKhang.DoAnWeb.util.MaIdGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TaiKhoanBenhNhanService {

    private static final String MA_PREFIX = "TK";

    private final TaiKhoanBenhNhanRepository repository;
    private final BenhNhanService benhNhanService;
    private final NhanVienService nhanVienService;
    private final DtoMapper dtoMapper;
    private final PasswordEncoder passwordEncoder;

    public TaiKhoanBenhNhanService(
            TaiKhoanBenhNhanRepository repository,
            BenhNhanService benhNhanService,
            NhanVienService nhanVienService,
            DtoMapper dtoMapper,
            PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.benhNhanService = benhNhanService;
        this.nhanVienService = nhanVienService;
        this.dtoMapper = dtoMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public List<TaiKhoanDto> getAllDtos() {
        return repository.findAll().stream().map(dtoMapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TaiKhoanDto getDtoById(String maTk) {
        return dtoMapper.toDto(getEntityById(maTk));
    }

    @Transactional(readOnly = true)
    public TaiKhoanBenhNhan getEntityById(String maTk) {
        return repository.findById(maTk)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy tài khoản: " + maTk));
    }

    public TaiKhoanDto saveDto(TaiKhoanDto dto) {
        if (dto.getTenDangNhap() == null || dto.getTenDangNhap().isBlank()) {
            throw new BusinessException("Tên đăng nhập không được để trống");
        }
        TaiKhoanBenhNhan entity = dtoMapper.toEntity(dto);
        if (entity.getMaTk() == null || entity.getMaTk().isBlank()) {
            String last = repository.findTopByOrderByMaTkDesc().map(TaiKhoanBenhNhan::getMaTk).orElse(null);
            entity.setMaTk(MaIdGenerator.nextMa(MA_PREFIX, last));
            if (repository.existsByTenDangNhap(dto.getTenDangNhap())) {
                throw new BusinessException("Tên đăng nhập đã tồn tại");
            }

            if (dto.getMatKhau() == null || dto.getMatKhau().isBlank()) {
                throw new BusinessException("Mật khẩu không được để trống");
            }
            entity.setMatKhau(passwordEncoder.encode(dto.getMatKhau()));
        } else {
            repository.findByTenDangNhap(dto.getTenDangNhap()).ifPresent(existing -> {
                if (!existing.getMaTk().equals(entity.getMaTk())) {
                    throw new BusinessException("Tên đăng nhập đã tồn tại");
                }
            });

            // Update password nếu người dùng nhập mật khẩu mới
            if (dto.getMatKhau() == null || dto.getMatKhau().isBlank()) {
                entity.setMatKhau(getEntityById(entity.getMaTk()).getMatKhau());
            } else {
                entity.setMatKhau(passwordEncoder.encode(dto.getMatKhau()));
            }
        }

        if (entity.getVaiTro() == null) {
            entity.setVaiTro(VaiTro.BENH_NHAN);
        }

        if (entity.getVaiTro() == VaiTro.BENH_NHAN && dto.getMaBn() != null && !dto.getMaBn().isBlank()) {
            BenhNhan bn = benhNhanService.getEntityById(dto.getMaBn());
            entity.setBenhNhan(bn);
            if (entity.getHoTenBn() == null) {
                entity.setHoTenBn(bn.getHoTenBn());
            }
        }

        if (entity.getVaiTro() == VaiTro.NHAN_VIEN && dto.getMaNv() != null && !dto.getMaNv().isBlank()) {
            NhanVien nv = nhanVienService.getEntityById(dto.getMaNv());
            entity.setNhanVien(nv);
        }

        if (entity.getDiemTichLuy() == null) {
            entity.setDiemTichLuy(0);
        }

        return dtoMapper.toDto(repository.save(entity));
    }

    public void delete(String maTk) {
        if (!repository.existsById(maTk)) {
            throw new ResourceNotFoundException("Không tìm thấy tài khoản để xóa: " + maTk);
        }
        repository.deleteById(maTk);
    }
}
