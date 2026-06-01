package NguyenQuocGiaKhang.DoAnWeb.Controller;

import NguyenQuocGiaKhang.DoAnWeb.Model.BenhNhan;
import NguyenQuocGiaKhang.DoAnWeb.Service.BenhNhanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String index(Model model) {
        model.addAttribute("benhNhans", benhNhanService.getAll());
        return "benhnhan/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("benhNhan", new BenhNhan());
        return "benhnhan/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute BenhNhan benhNhan, RedirectAttributes redirectAttributes) {
        BenhNhan saved = benhNhanService.save(benhNhan);
        redirectAttributes.addFlashAttribute("success",
                "Đã thêm bệnh nhân " + saved.getHoTenBn() + " (" + saved.getMaBn() + ")");
        return "redirect:/benhnhan";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable String id, Model model) {
        model.addAttribute("benhNhan", benhNhanService.getById(id));
        return "benhnhan/form";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute BenhNhan benhNhan, RedirectAttributes redirectAttributes) {
        BenhNhan saved = benhNhanService.save(benhNhan);
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
