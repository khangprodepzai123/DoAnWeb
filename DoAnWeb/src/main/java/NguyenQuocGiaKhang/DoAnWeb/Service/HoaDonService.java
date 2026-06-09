package NguyenQuocGiaKhang.DoAnWeb.Service;



import NguyenQuocGiaKhang.DoAnWeb.Model.HoaDon;

import NguyenQuocGiaKhang.DoAnWeb.Model.KhamBenh;

import NguyenQuocGiaKhang.DoAnWeb.Model.NhanVien;

import NguyenQuocGiaKhang.DoAnWeb.Model.ToaThuoc;

import NguyenQuocGiaKhang.DoAnWeb.Model.TrangThaiThanhToan;

import NguyenQuocGiaKhang.DoAnWeb.Repository.BenhAnRepository;

import NguyenQuocGiaKhang.DoAnWeb.Repository.HoaDonRepository;

import NguyenQuocGiaKhang.DoAnWeb.Repository.ToaThuocRepository;

import NguyenQuocGiaKhang.DoAnWeb.dto.HoaDonDto;

import NguyenQuocGiaKhang.DoAnWeb.exception.BusinessException;

import NguyenQuocGiaKhang.DoAnWeb.exception.ResourceNotFoundException;

import NguyenQuocGiaKhang.DoAnWeb.mapper.DtoMapper;

import NguyenQuocGiaKhang.DoAnWeb.util.MaIdGenerator;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;



import java.math.BigDecimal;

import java.time.LocalDate;

import java.util.List;

import java.util.stream.Collectors;



@Service

@Transactional

public class HoaDonService {



    private static final String MA_PREFIX = "HD";

    private static final String DEFAULT_MA_NV = "NV001";

    public static final BigDecimal TIEN_KHAM = new BigDecimal("100000");



    private final HoaDonRepository hoaDonRepository;

    private final KhamBenhService khamBenhService;

    private final NhanVienService nhanVienService;

    private final ChiTietHoaDonService chiTietHoaDonService;

    private final ToaThuocRepository toaThuocRepository;

    private final BenhAnRepository benhAnRepository;

    private final DtoMapper dtoMapper;



    public HoaDonService(

            HoaDonRepository hoaDonRepository,

            KhamBenhService khamBenhService,

            NhanVienService nhanVienService,

            ChiTietHoaDonService chiTietHoaDonService,

            ToaThuocRepository toaThuocRepository,

            BenhAnRepository benhAnRepository,

            DtoMapper dtoMapper) {

        this.hoaDonRepository = hoaDonRepository;

        this.khamBenhService = khamBenhService;

        this.nhanVienService = nhanVienService;

        this.chiTietHoaDonService = chiTietHoaDonService;

        this.toaThuocRepository = toaThuocRepository;

        this.benhAnRepository = benhAnRepository;

        this.dtoMapper = dtoMapper;

    }



    @Transactional(readOnly = true)

    public List<HoaDonDto> getAllDtos() {

        return hoaDonRepository.findAll().stream().map(this::toDtoWithChiTiet).collect(Collectors.toList());

    }



    @Transactional(readOnly = true)

    public HoaDonDto getDtoById(String maHd) {

        return toDtoWithChiTiet(getEntityById(maHd));

    }



    @Transactional(readOnly = true)

    public HoaDon getEntityById(String maHd) {

        return hoaDonRepository.findById(maHd)

                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy hóa đơn: " + maHd));

    }



    @Transactional(readOnly = true)

    public HoaDonDto getDtoByMaKham(String maKham) {

        return hoaDonRepository.findByKhamBenh_MaKham(maKham)

                .map(this::toDtoWithChiTiet)

                .orElse(null);

    }



    public HoaDonDto createDraftFromKhamBenh(String maKham) {

        KhamBenh khamBenh = khamBenhService.getEntityById(maKham);

        HoaDon hoaDon = hoaDonRepository.findByKhamBenh_MaKham(maKham).orElse(null);

        if (hoaDon != null) {

            if (!TrangThaiThanhToan.CHUA_THANH_TOAN.equals(hoaDon.getTrangThaiThanhToan())) {

                throw new BusinessException("Hóa đơn đã thanh toán, không thể cập nhật");

            }

            chiTietHoaDonService.deleteAllByMaHd(hoaDon.getMaHd());

        } else {

            NhanVien nhanVien = nhanVienService.getEntityById(DEFAULT_MA_NV);

            hoaDon = new HoaDon();

            String last = hoaDonRepository.findTopByOrderByMaHdDesc().map(HoaDon::getMaHd).orElse(null);

            hoaDon.setMaHd(MaIdGenerator.nextMa(MA_PREFIX, last));

            hoaDon.setKhamBenh(khamBenh);

            hoaDon.setNhanVien(nhanVien);

            hoaDon.setNgayLap(LocalDate.now());

            hoaDon.setTrangThaiThanhToan(TrangThaiThanhToan.CHUA_THANH_TOAN);

            hoaDon.setDiemTichLuySuDung(0);

            hoaDon.setThanhTien(TIEN_KHAM);

            hoaDonRepository.save(hoaDon);

        }



        for (ToaThuoc tt : toaThuocRepository.findByKhamBenh_MaKham(maKham)) {

            String maThuoc = tt.getThuoc().getMaThuoc();

            chiTietHoaDonService.addToHoaDon(hoaDon.getMaHd(), maThuoc, tt.getSoLuong(), false);

        }



        BigDecimal tongThuoc = chiTietHoaDonService.tinhTongTien(hoaDon.getMaHd());

        hoaDon.setThanhTien(TIEN_KHAM.add(tongThuoc));

        return toDtoWithChiTiet(hoaDonRepository.save(hoaDon));

    }



    public HoaDonDto markAsPaid(String maHd, BigDecimal thanhTien, int diemTichLuySuDung) {

        HoaDon hoaDon = getEntityById(maHd);

        if (!TrangThaiThanhToan.CHUA_THANH_TOAN.equals(hoaDon.getTrangThaiThanhToan())) {

            throw new BusinessException("Hóa đơn không thể thanh toán (đã thanh toán hoặc không hợp lệ)");

        }



        hoaDon.setThanhTien(thanhTien);

        hoaDon.setDiemTichLuySuDung(diemTichLuySuDung);

        hoaDon.setTrangThaiThanhToan(TrangThaiThanhToan.DA_THANH_TOAN);

        return toDtoWithChiTiet(hoaDonRepository.save(hoaDon));

    }



    public void delete(String maHd) {

        HoaDon hoaDon = getEntityById(maHd);

        if (!TrangThaiThanhToan.CHUA_THANH_TOAN.equals(hoaDon.getTrangThaiThanhToan())) {

            throw new BusinessException("Chỉ xóa được hóa đơn chưa thanh toán");

        }

        chiTietHoaDonService.deleteAllByMaHd(maHd);

        hoaDonRepository.deleteById(maHd);

    }



    private HoaDonDto toDtoWithChiTiet(HoaDon entity) {

        HoaDonDto dto = dtoMapper.toDto(entity);

        dto.setChiTiets(chiTietHoaDonService.findDtosByMaHd(entity.getMaHd()));

        if (dto.getThanhTien() == null) {

            dto.setThanhTien(TIEN_KHAM.add(chiTietHoaDonService.tinhTongTien(entity.getMaHd())));

        }

        if (dto.getMaKham() != null) {

            dto.setDaCoBenhAn(benhAnRepository.findByMaKham(dto.getMaKham()).isPresent());

        }

        return dto;

    }

}


