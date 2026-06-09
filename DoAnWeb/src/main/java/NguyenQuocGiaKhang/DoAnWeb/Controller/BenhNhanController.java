package NguyenQuocGiaKhang.DoAnWeb.Controller;

import NguyenQuocGiaKhang.DoAnWeb.Service.BenhNhanService;
import NguyenQuocGiaKhang.DoAnWeb.dto.BenhNhanDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/benhnhan")
public class BenhNhanController {

    private final BenhNhanService benhNhanService;

    public BenhNhanController(BenhNhanService benhNhanService) {
        this.benhNhanService = benhNhanService;
    }

    @GetMapping("")
    public String index(
            @RequestParam(value = "keyword", required = false) String keyword,
            Model model) {
        model.addAttribute("benhNhans", benhNhanService.searchByHoTen(keyword));
        model.addAttribute("keyword", keyword != null ? keyword : "");
        return "benhnhan/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("benhNhan", new BenhNhanDto());
        return "benhnhan/form";
    }

    @PostMapping("/save")
    public String save(
            @Valid @ModelAttribute("benhNhan") BenhNhanDto benhNhanDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "benhnhan/form";
        }
        BenhNhanDto saved = benhNhanService.saveDto(benhNhanDto);
        redirectAttributes.addFlashAttribute("success",
                "Đã thêm bệnh nhân " + saved.getHoTenBn() + " (" + saved.getMaBn() + ")");
        return "redirect:/benhnhan";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable String id, Model model) {
        model.addAttribute("benhNhan", benhNhanService.getDtoById(id));
        return "benhnhan/form";
    }

    @PostMapping("/update")
    public String update(
            @Valid @ModelAttribute("benhNhan") BenhNhanDto benhNhanDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "benhnhan/form";
        }
        BenhNhanDto saved = benhNhanService.saveDto(benhNhanDto);
        redirectAttributes.addFlashAttribute("success",
                "Đã cập nhật bệnh nhân " + saved.getHoTenBn());
        return "redirect:/benhnhan";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id, RedirectAttributes redirectAttributes) {
        benhNhanService.delete(id);
        redirectAttributes.addFlashAttribute("success", "Đã xóa bệnh nhân " + id);
        return "redirect:/benhnhan";
    }
}
