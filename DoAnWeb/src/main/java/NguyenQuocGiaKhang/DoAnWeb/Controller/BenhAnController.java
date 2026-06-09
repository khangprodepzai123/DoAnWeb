package NguyenQuocGiaKhang.DoAnWeb.Controller;

import NguyenQuocGiaKhang.DoAnWeb.Service.BenhAnService;
import NguyenQuocGiaKhang.DoAnWeb.Service.HoaDonService;
import NguyenQuocGiaKhang.DoAnWeb.Service.KhamBenhService;
import NguyenQuocGiaKhang.DoAnWeb.dto.BenhAnDto;
import NguyenQuocGiaKhang.DoAnWeb.exception.BusinessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/benhan")
public class BenhAnController {

    private final BenhAnService benhAnService;
    private final KhamBenhService khamBenhService;
    private final HoaDonService hoaDonService;

    public BenhAnController(BenhAnService benhAnService, KhamBenhService khamBenhService, HoaDonService hoaDonService) {
        this.benhAnService = benhAnService;
        this.khamBenhService = khamBenhService;
        this.hoaDonService = hoaDonService;
    }

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("items", benhAnService.getAllDtos());
        return "benhan/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("item", new BenhAnDto());
        model.addAttribute("khamBenhs", khamBenhService.getAllDtos());
        return "benhan/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("item") BenhAnDto dto, RedirectAttributes ra) {
        var saved = benhAnService.saveDto(dto);
        ra.addFlashAttribute("success", "Đã lưu bệnh án " + saved.getMaBenhAn());
        return "redirect:/benhan";
    }

    @GetMapping("/from-kham/{maKham}")
    public String fromKham(@PathVariable String maKham, RedirectAttributes ra) {
        try {
            var saved = benhAnService.saveFromKhamBenh(maKham);
            ra.addFlashAttribute("success", "Đã tạo bệnh án " + saved.getMaBenhAn() + " từ phiếu khám");
            return "redirect:/benhan/detail/" + saved.getMaBenhAn();
        } catch (BusinessException ex) {
            ra.addFlashAttribute("error", ex.getMessage());
            var hd = hoaDonService.getDtoByMaKham(maKham);
            if (hd != null) {
                return "redirect:/hoadon/detail/" + hd.getMaHd();
            }
            return "redirect:/khambenh";
        }
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable String id, Model model) {
        model.addAttribute("item", benhAnService.getDtoById(id));
        return "benhan/detail";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id, RedirectAttributes ra) {
        benhAnService.delete(id);
        ra.addFlashAttribute("success", "Đã xóa bệnh án " + id);
        return "redirect:/benhan";
    }
}
