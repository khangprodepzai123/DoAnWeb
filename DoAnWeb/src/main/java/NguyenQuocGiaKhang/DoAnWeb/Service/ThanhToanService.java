package NguyenQuocGiaKhang.DoAnWeb.Service;

import NguyenQuocGiaKhang.DoAnWeb.Model.*;
import NguyenQuocGiaKhang.DoAnWeb.Repository.HoaDonRepository;
import NguyenQuocGiaKhang.DoAnWeb.Repository.KhamBenhRepository;
import NguyenQuocGiaKhang.DoAnWeb.Repository.TaiKhoanBenhNhanRepository;
import NguyenQuocGiaKhang.DoAnWeb.Repository.ThuocRepository;
import NguyenQuocGiaKhang.DoAnWeb.Repository.ToaThuocRepository;
import NguyenQuocGiaKhang.DoAnWeb.dto.ChiTietHoaDonDto;
import NguyenQuocGiaKhang.DoAnWeb.dto.ThanhToanDto;
import NguyenQuocGiaKhang.DoAnWeb.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ThanhToanService {

    private static final BigDecimal TIEN_KHAM = new BigDecimal("100000");

    private final BenhNhanService benhNhanService;
    private final KhamBenhService khamBenhService;
    private final KhamBenhRepository khamBenhRepository;
    private final HoaDonService hoaDonService;
    private final ChiTietHoaDonService chiTietHoaDonService;
    private final ToaThuocRepository toaThuocRepository;
    private final ThuocRepository thuocRepository;
    private final HoaDonRepository hoaDonRepository;
    private final TaiKhoanBenhNhanRepository taiKhoanBenhNhanRepository;

    public ThanhToanService(
            BenhNhanService benhNhanService,
            KhamBenhService khamBenhService,
            KhamBenhRepository khamBenhRepository,
            HoaDonService hoaDonService,
            ChiTietHoaDonService chiTietHoaDonService,
            ToaThuocRepository toaThuocRepository,
            ThuocRepository thuocRepository,
            HoaDonRepository hoaDonRepository,
            TaiKhoanBenhNhanRepository taiKhoanBenhNhanRepository) {
        this.benhNhanService = benhNhanService;
        this.khamBenhService = khamBenhService;
        this.khamBenhRepository = khamBenhRepository;
        this.hoaDonService = hoaDonService;
        this.chiTietHoaDonService = chiTietHoaDonService;
        this.toaThuocRepository = toaThuocRepository;
        this.thuocRepository = thuocRepository;
        this.hoaDonRepository = hoaDonRepository;
        this.taiKhoanBenhNhanRepository = taiKhoanBenhNhanRepository;
    }

    @Transactional(readOnly = true)
    public ThanhToanDto buildPreview(String maBn) {
        BenhNhan benhNhan = benhNhanService.getEntityById(maBn);
        KhamBenh khamBenh = khamBenhRepository.findByBenhNhan_MaBn(maBn)
                .orElseThrow(() -> new BusinessException("Bệnh nhân chưa có phiếu khám"));

        if (hoaDonRepository.findByKhamBenh_MaKham(khamBenh.getMaKham()).isPresent()) {
            throw new BusinessException("Phiếu khám đã được thanh toán");
        }

        ThanhToanDto dto = new ThanhToanDto();
        dto.setMaKham(khamBenh.getMaKham());
        dto.setHoTenBn(benhNhan.getHoTenBn());

        List<ChiTietHoaDonDto> chiTiets = new ArrayList<>();
        BigDecimal tongThuoc = BigDecimal.ZERO;

        for (ToaThuoc tt : toaThuocRepository.findByKhamBenh_MaKham(khamBenh.getMaKham())) {
            Thuoc thuoc = tt.getThuoc();
            ChiTietHoaDonDto line = new ChiTietHoaDonDto();
            line.setMaThuoc(thuoc.getMaThuoc());
            line.setTenThuoc(thuoc.getTenThuoc());
            line.setSoLuong(tt.getSoLuong());
            line.setDonGia(thuoc.getGiaBan());
            line.setThanhTien(thuoc.getGiaBan().multiply(BigDecimal.valueOf(tt.getSoLuong())));
            chiTiets.add(line);
            tongThuoc = tongThuoc.add(line.getThanhTien());
        }

        dto.setChiTiets(chiTiets);
        BigDecimal tong = TIEN_KHAM.add(tongThuoc);

        boolean coBhyt = benhNhan.getBhyt() != null && !benhNhan.getBhyt().isBlank();
        if (coBhyt) {
            tong = tong.multiply(new BigDecimal("0.2"));
        }

        int diem = taiKhoanBenhNhanRepository.findAll().stream()
                .filter(t -> t.getBenhNhan() != null && maBn.equals(t.getBenhNhan().getMaBn()))
                .map(TaiKhoanBenhNhan::getDiemTichLuy)
                .findFirst()
                .orElse(0);

        dto.setDiemTichLuyHienCo(diem);
        dto.setDiemTichLuySuDung(0);
        dto.setTongTien(tong.setScale(0, RoundingMode.HALF_UP));
        return dto;
    }

    public String processPayment(String maBn, int diemSuDung) {
        ThanhToanDto preview = buildPreview(maBn);
        KhamBenh khamBenh = khamBenhRepository.findByBenhNhan_MaBn(maBn).orElseThrow();

        BigDecimal thanhTien = preview.getTongTien();
        if (diemSuDung > 0 && preview.getDiemTichLuyHienCo() != null && preview.getDiemTichLuyHienCo() >= 1) {
            thanhTien = thanhTien.multiply(new BigDecimal("0.9")).setScale(0, RoundingMode.HALF_UP);
        }

        var hoaDonDto = hoaDonService.createForKhamBenh(khamBenh.getMaKham(), thanhTien, diemSuDung);

        for (ChiTietHoaDonDto line : preview.getChiTiets()) {
            chiTietHoaDonService.addToHoaDon(hoaDonDto.getMaHd(), line.getMaThuoc(), line.getSoLuong());
            Thuoc thuoc = thuocRepository.findById(line.getMaThuoc()).orElseThrow();
            thuoc.setSoLuong(thuoc.getSoLuong() - line.getSoLuong());
            thuocRepository.save(thuoc);
        }

        var kbDto = khamBenhService.getDtoById(khamBenh.getMaKham());
        kbDto.setTrangThai(TrangThaiKham.DA_KHAM);
        khamBenhService.saveDto(kbDto);

        taiKhoanBenhNhanRepository.findAll().stream()
                .filter(t -> t.getBenhNhan() != null && maBn.equals(t.getBenhNhan().getMaBn()))
                .findFirst()
                .ifPresent(tk -> {
                    int diem = tk.getDiemTichLuy() != null ? tk.getDiemTichLuy() : 0;
                    tk.setDiemTichLuy(Math.max(0, diem - diemSuDung) + 10);
                    taiKhoanBenhNhanRepository.save(tk);
                });

        return hoaDonDto.getMaHd();
    }
}
