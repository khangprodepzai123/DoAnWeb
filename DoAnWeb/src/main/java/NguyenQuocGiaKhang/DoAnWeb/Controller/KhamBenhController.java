package NguyenQuocGiaKhang.DoAnWeb.Controller;

import NguyenQuocGiaKhang.DoAnWeb.Model.TrangThaiKham;
import NguyenQuocGiaKhang.DoAnWeb.Service.*;
import NguyenQuocGiaKhang.DoAnWeb.dto.KhamBenhDto;
import NguyenQuocGiaKhang.DoAnWeb.exception.BusinessException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/khambenh")
public class KhamBenhController {

    private final KhamBenhService khamBenhService;
    private final HoaDonService hoaDonService;
    private final BenhNhanService benhNhanService;
    private final BacSiService bacSiService;
    private final ChuanDoanService chuanDoanService;
    private final ThuocService thuocService;
    private final ToaThuocService toaThuocService;

    public KhamBenhController(
            KhamBenhService khamBenhService,
            HoaDonService hoaDonService,
            BenhNhanService benhNhanService,
            BacSiService bacSiService,
            ChuanDoanService chuanDoanService,
            ThuocService thuocService,
            ToaThuocService toaThuocService) {
        this.khamBenhService = khamBenhService;
        this.hoaDonService = hoaDonService;
        this.benhNhanService = benhNhanService;
        this.bacSiService = bacSiService;
        this.chuanDoanService = chuanDoanService;
        this.thuocService = thuocService;
        this.toaThuocService = toaThuocService;
    }

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("items", khamBenhService.getAllDtosForIndex());
        return "khambenh/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        prepareForm(model, new KhamBenhDto());
        return "khambenh/form";
    }

    @GetMapping("/dangky/{maBn}")
    public String dangKy(@PathVariable String maBn, RedirectAttributes ra) {
        var saved = khamBenhService.dangKyKham(maBn, "BS001");
        ra.addFlashAttribute("success", "Đã đăng ký khám " + saved.getMaKham() + " cho bệnh nhân");
        return "redirect:/khambenh/edit/" + saved.getMaKham();
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("item") KhamBenhDto dto, BindingResult result, Model model, RedirectAttributes ra) {
        if (result.hasErrors()) {
            prepareForm(model, dto);
            return "khambenh/form";
        }
        var saved = khamBenhService.saveDto(dto);
        ra.addFlashAttribute("success", "Đã lưu phiếu khám " + saved.getMaKham());
        return "redirect:/khambenh/edit/" + saved.getMaKham();
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable String id, Model model) {
        prepareForm(model, khamBenhService.getDtoById(id));
        model.addAttribute("toaThuocs", toaThuocService.getDtosByMaKham(id));
        model.addAttribute("thuocs", thuocService.getAllDtos());
        model.addAttribute("hoaDon", hoaDonService.getDtoByMaKham(id));
        return "khambenh/form";
    }

    @GetMapping("/{maKham}/tao-hoadon")
    public String taoHoaDon(@PathVariable String maKham, RedirectAttributes ra) {
        try {
            var saved = hoaDonService.createDraftFromKhamBenh(maKham);
            ra.addFlashAttribute("success", "Đã lưu hóa đơn " + saved.getMaHd());
            return "redirect:/hoadon/detail/" + saved.getMaHd();
        } catch (BusinessException ex) {
            ra.addFlashAttribute("error", ex.getMessage());
            return "redirect:/khambenh/edit/" + maKham;
        }
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("item") KhamBenhDto dto, BindingResult result, Model model, RedirectAttributes ra) {
        if (result.hasErrors()) {
            prepareForm(model, dto);
            if (dto.getMaKham() != null) {
                model.addAttribute("toaThuocs", toaThuocService.getDtosByMaKham(dto.getMaKham()));
                model.addAttribute("thuocs", thuocService.getAllDtos());
            }
            return "khambenh/form";
        }
        var saved = khamBenhService.saveDto(dto);
        ra.addFlashAttribute("success", "Đã cập nhật phiếu khám " + saved.getMaKham());
        return "redirect:/khambenh/edit/" + saved.getMaKham();
    }

    @PostMapping("/{maKham}/them-thuoc")
    public String themThuoc(
            @PathVariable String maKham,
            @RequestParam String maThuoc,
            @RequestParam Integer soLuong,
            @RequestParam(required = false) String lieuDung,
            @RequestParam(required = false) String cachDung,
            RedirectAttributes ra) {
        try {
            toaThuocService.addThuoc(maKham, maThuoc, soLuong, lieuDung, cachDung);
            ra.addFlashAttribute("success", "Đã thêm thuốc vào toa");
        } catch (BusinessException ex) {
            ra.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/khambenh/edit/" + maKham;
    }

    @GetMapping("/{maKham}/xoa-thuoc/{maThuoc}")
    public String xoaThuoc(
            @PathVariable String maKham,
            @PathVariable String maThuoc,
            RedirectAttributes ra) {
        toaThuocService.deleteThuoc(maKham, maThuoc);
        ra.addFlashAttribute("success", "Đã xóa thuốc khỏi toa");
        return "redirect:/khambenh/edit/" + maKham;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id, RedirectAttributes ra) {
        khamBenhService.delete(id);
        ra.addFlashAttribute("success", "Đã xóa phiếu khám " + id);
        return "redirect:/khambenh";
    }

    private void prepareForm(Model model, KhamBenhDto dto) {
        model.addAttribute("item", dto);
        model.addAttribute("benhNhans", benhNhanService.getAllDtos());
        model.addAttribute("bacSis", bacSiService.getAllDtos());
        model.addAttribute("chuanDoans", chuanDoanService.getAllDtos());
        model.addAttribute("trangThais", new String[]{TrangThaiKham.CHO_KHAM, TrangThaiKham.DA_KHAM});
    }
}
