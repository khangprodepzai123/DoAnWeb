package NguyenQuocGiaKhang.DoAnWeb.mapper;

import NguyenQuocGiaKhang.DoAnWeb.Model.*;
import NguyenQuocGiaKhang.DoAnWeb.dto.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DtoMapper {

    // --- BenhNhan ---
    public BenhNhanDto toDto(BenhNhan entity) {
        if (entity == null) {
            return null;
        }
        BenhNhanDto dto = new BenhNhanDto();
        dto.setMaBn(entity.getMaBn());
        dto.setHoTenBn(entity.getHoTenBn());
        dto.setSdt(entity.getSdt());
        dto.setNgaySinh(entity.getNgaySinh());
        dto.setGt(entity.getGt());
        dto.setDoiTuong(entity.getDoiTuong());
        dto.setDiaChi(entity.getDiaChi());
        dto.setBhyt(entity.getBhyt());
        return dto;
    }

    public BenhNhan toEntity(BenhNhanDto dto) {
        if (dto == null) {
            return null;
        }
        BenhNhan entity = new BenhNhan();
        entity.setMaBn(trimToNull(dto.getMaBn()));
        entity.setHoTenBn(dto.getHoTenBn());
        entity.setSdt(dto.getSdt());
        entity.setNgaySinh(dto.getNgaySinh());
        entity.setGt(dto.getGt());
        entity.setDoiTuong(dto.getDoiTuong());
        entity.setDiaChi(dto.getDiaChi());
        entity.setBhyt(dto.getBhyt());
        return entity;
    }

    public List<BenhNhanDto> toBenhNhanDtoList(List<BenhNhan> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    // --- BacSi ---
    public BacSiDto toDto(BacSi entity) {
        if (entity == null) {
            return null;
        }
        BacSiDto dto = new BacSiDto();
        dto.setMaBs(entity.getMaBs());
        dto.setHoTenBs(entity.getHoTenBs());
        dto.setTrinhDoHocVan(entity.getTrinhDoHocVan());
        dto.setChuyenKhoa(entity.getChuyenKhoa());
        dto.setTuoi(entity.getTuoi());
        dto.setKinhNghiem(entity.getKinhNghiem());
        dto.setChungChiHanhNghe(entity.getChungChiHanhNghe());
        return dto;
    }

    public BacSi toEntity(BacSiDto dto) {
        if (dto == null) {
            return null;
        }
        BacSi entity = new BacSi();
        entity.setMaBs(trimToNull(dto.getMaBs()));
        entity.setHoTenBs(dto.getHoTenBs());
        entity.setTrinhDoHocVan(dto.getTrinhDoHocVan());
        entity.setChuyenKhoa(dto.getChuyenKhoa());
        entity.setTuoi(dto.getTuoi());
        entity.setKinhNghiem(dto.getKinhNghiem());
        entity.setChungChiHanhNghe(dto.getChungChiHanhNghe());
        return entity;
    }

    // --- Thuoc ---
    public ThuocDto toDto(Thuoc entity) {
        if (entity == null) {
            return null;
        }
        ThuocDto dto = new ThuocDto();
        dto.setMaThuoc(entity.getMaThuoc());
        dto.setTenThuoc(entity.getTenThuoc());
        dto.setGiaBan(entity.getGiaBan());
        dto.setSoLuong(entity.getSoLuong());
        dto.setHdsd(entity.getHdsd());
        return dto;
    }

    public Thuoc toEntity(ThuocDto dto) {
        if (dto == null) {
            return null;
        }
        Thuoc entity = new Thuoc();
        entity.setMaThuoc(trimToNull(dto.getMaThuoc()));
        entity.setTenThuoc(dto.getTenThuoc());
        entity.setGiaBan(dto.getGiaBan());
        entity.setSoLuong(dto.getSoLuong());
        entity.setHdsd(dto.getHdsd());
        return entity;
    }

    // --- NhanVien ---
    public NhanVienDto toDto(NhanVien entity) {
        if (entity == null) {
            return null;
        }
        NhanVienDto dto = new NhanVienDto();
        dto.setMaNv(entity.getMaNv());
        dto.setHoTenNv(entity.getHoTenNv());
        return dto;
    }

    public NhanVien toEntity(NhanVienDto dto) {
        if (dto == null) {
            return null;
        }
        NhanVien entity = new NhanVien();
        entity.setMaNv(trimToNull(dto.getMaNv()));
        entity.setHoTenNv(dto.getHoTenNv());
        return entity;
    }

    // --- ChuanDoan ---
    public ChuanDoanDto toDto(ChuanDoan entity) {
        if (entity == null) {
            return null;
        }
        ChuanDoanDto dto = new ChuanDoanDto();
        dto.setMaCd(entity.getMaCd());
        dto.setTenCd(entity.getTenCd());
        dto.setMoTa(entity.getMoTa());
        return dto;
    }

    public ChuanDoan toEntity(ChuanDoanDto dto) {
        if (dto == null) {
            return null;
        }
        ChuanDoan entity = new ChuanDoan();
        entity.setMaCd(trimToNull(dto.getMaCd()));
        entity.setTenCd(dto.getTenCd());
        entity.setMoTa(dto.getMoTa());
        return entity;
    }

    // --- KhamBenh ---
    public KhamBenhDto toDto(KhamBenh entity) {
        if (entity == null) {
            return null;
        }
        KhamBenhDto dto = new KhamBenhDto();
        dto.setMaKham(entity.getMaKham());
        dto.setMaBn(entity.getBenhNhan() != null ? entity.getBenhNhan().getMaBn() : null);
        dto.setMaBs(entity.getBacSi() != null ? entity.getBacSi().getMaBs() : null);
        dto.setMaCd(entity.getChuanDoanEntity() != null ? entity.getChuanDoanEntity().getMaCd() : null);
        dto.setNgayKham(entity.getNgayKham());
        dto.setLyDoKham(entity.getLyDoKham());
        dto.setQuaTrinhBenhLy(entity.getQuaTrinhBenhLy());
        dto.setTienSuBenhNhan(entity.getTienSuBenhNhan());
        dto.setTienSuGiaDinh(entity.getTienSuGiaDinh());
        dto.setKhamBoPhan(entity.getKhamBoPhan());
        dto.setChuanDoan(entity.getChuanDoan());
        dto.setHuongXuTri(entity.getHuongXuTri());
        dto.setLoaiKham(entity.getLoaiKham());
        dto.setXuTriKham(entity.getXuTriKham());
        dto.setTrangThai(entity.getTrangThai());
        if (entity.getBenhNhan() != null) {
            dto.setHoTenBn(entity.getBenhNhan().getHoTenBn());
        }
        if (entity.getBacSi() != null) {
            dto.setHoTenBs(entity.getBacSi().getHoTenBs());
        }
        return dto;
    }

    // --- HoaDon ---
    public HoaDonDto toDto(HoaDon entity) {
        if (entity == null) {
            return null;
        }
        HoaDonDto dto = new HoaDonDto();
        dto.setMaHd(entity.getMaHd());
        dto.setMaKham(entity.getKhamBenh() != null ? entity.getKhamBenh().getMaKham() : null);
        dto.setMaNv(entity.getNhanVien() != null ? entity.getNhanVien().getMaNv() : null);
        dto.setNgayLap(entity.getNgayLap());
        dto.setThanhTien(entity.getThanhTien());
        dto.setDiemTichLuySuDung(entity.getDiemTichLuySuDung());
        if (entity.getKhamBenh() != null && entity.getKhamBenh().getBenhNhan() != null) {
            dto.setHoTenBn(entity.getKhamBenh().getBenhNhan().getHoTenBn());
            dto.setTrangThaiKham(entity.getKhamBenh().getTrangThai());
        }
        if (entity.getNhanVien() != null) {
            dto.setHoTenNv(entity.getNhanVien().getHoTenNv());
        }
        if (entity.getChiTietHoaDons() != null) {
            dto.setChiTiets(entity.getChiTietHoaDons().stream()
                    .map(this::toDto)
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    // --- ChiTietHoaDon ---
    public ChiTietHoaDonDto toDto(ChiTietHoaDon entity) {
        if (entity == null) {
            return null;
        }
        ChiTietHoaDonDto dto = new ChiTietHoaDonDto();
        if (entity.getId() != null) {
            dto.setMaHd(entity.getId().getMaHd());
            dto.setMaThuoc(entity.getId().getMaThuoc());
        }
        dto.setSoLuong(entity.getSoLuong());
        dto.setDonGia(entity.getDonGia());
        dto.setThanhTien(entity.getThanhTienDong());
        if (entity.getThuoc() != null) {
            dto.setTenThuoc(entity.getThuoc().getTenThuoc());
        }
        return dto;
    }

    public List<ChiTietHoaDonDto> toChiTietHoaDonDtoList(List<ChiTietHoaDon> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    // --- BenhAn ---
    public BenhAnDto toDto(BenhAn entity) {
        if (entity == null) {
            return null;
        }
        BenhAnDto dto = new BenhAnDto();
        dto.setMaBenhAn(entity.getMaBenhAn());
        dto.setMaKham(entity.getMaKham());
        dto.setMaBn(entity.getBenhNhan() != null ? entity.getBenhNhan().getMaBn() : null);
        dto.setMaBs(entity.getBacSi() != null ? entity.getBacSi().getMaBs() : null);
        dto.setLyDoKham(entity.getLyDoKham());
        dto.setQuaTrinhBenhLy(entity.getQuaTrinhBenhLy());
        dto.setTienSuBenhNhan(entity.getTienSuBenhNhan());
        dto.setTienSuGiaDinh(entity.getTienSuGiaDinh());
        dto.setKhamBoPhan(entity.getKhamBoPhan());
        dto.setChuanDoan(entity.getChuanDoan());
        dto.setHuongXuTri(entity.getHuongXuTri());
        dto.setLoaiKham(entity.getLoaiKham());
        dto.setXuTriKham(entity.getXuTriKham());
        dto.setNgayKham(entity.getNgayKham());
        dto.setNgayLuu(entity.getNgayLuu());
        if (entity.getBenhNhan() != null) {
            dto.setHoTenBn(entity.getBenhNhan().getHoTenBn());
        }
        if (entity.getBacSi() != null) {
            dto.setHoTenBs(entity.getBacSi().getHoTenBs());
        }
        return dto;
    }

    // --- TaiKhoan ---
    public TaiKhoanDto toDto(TaiKhoanBenhNhan entity) {
        if (entity == null) {
            return null;
        }
        TaiKhoanDto dto = new TaiKhoanDto();
        dto.setMaTk(entity.getMaTk());
        dto.setTenDangNhap(entity.getTenDangNhap());
        dto.setDiemTichLuy(entity.getDiemTichLuy());
        dto.setHoTenBn(entity.getHoTenBn());
        dto.setVaiTro(entity.getVaiTro());
        dto.setMaBn(entity.getBenhNhan() != null ? entity.getBenhNhan().getMaBn() : null);
        dto.setMaNv(entity.getNhanVien() != null ? entity.getNhanVien().getMaNv() : null);
        return dto;
    }

    public TaiKhoanBenhNhan toEntity(TaiKhoanDto dto) {
        if (dto == null) {
            return null;
        }
        TaiKhoanBenhNhan entity = new TaiKhoanBenhNhan();
        entity.setMaTk(trimToNull(dto.getMaTk()));
        entity.setTenDangNhap(dto.getTenDangNhap());
        entity.setMatKhau(dto.getMatKhau());
        entity.setDiemTichLuy(dto.getDiemTichLuy() != null ? dto.getDiemTichLuy() : 0);
        entity.setHoTenBn(dto.getHoTenBn());
        entity.setVaiTro(dto.getVaiTro() != null ? dto.getVaiTro() : VaiTro.BENH_NHAN);
        return entity;
    }

    // --- MucUuDai ---
    public MucUuDaiDto toDto(MucUuDai entity) {
        if (entity == null) {
            return null;
        }
        MucUuDaiDto dto = new MucUuDaiDto();
        dto.setMaMuc(entity.getMaMuc());
        dto.setTenMuc(entity.getTenMuc());
        dto.setDiemToiThieu(entity.getDiemToiThieu());
        dto.setMoTa(entity.getMoTa());
        return dto;
    }

    public MucUuDai toEntity(MucUuDaiDto dto) {
        if (dto == null) {
            return null;
        }
        MucUuDai entity = new MucUuDai();
        entity.setMaMuc(trimToNull(dto.getMaMuc()));
        entity.setTenMuc(dto.getTenMuc());
        entity.setDiemToiThieu(dto.getDiemToiThieu());
        entity.setMoTa(dto.getMoTa());
        return entity;
    }

    private String trimToNull(String value) {
        if (value == null) {
            return null;
        }
        String trimmed = value.trim();
        return trimmed.isEmpty() ? null : trimmed;
    }
}
