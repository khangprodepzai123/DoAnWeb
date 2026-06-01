package NguyenQuocGiaKhang.DoAnWeb.Controller;

import NguyenQuocGiaKhang.DoAnWeb.Service.ThuocService;
import NguyenQuocGiaKhang.DoAnWeb.dto.ThuocDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/thuoc")
public class ThuocController {

    private final ThuocService thuocService;

    public ThuocController(ThuocService thuocService) {
        this.thuocService = thuocService;
    }

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("items", thuocService.getAllDtos());
        return "thuoc/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("item", new ThuocDto());
        return "thuoc/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("item") ThuocDto dto, BindingResult result, RedirectAttributes ra) {
        if (result.hasErrors()) return "thuoc/form";
        var saved = thuocService.saveDto(dto);
        ra.addFlashAttribute("success", "Đã lưu thuốc " + saved.getTenThuoc());
        return "redirect:/thuoc";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable String id, Model model) {
        model.addAttribute("item", thuocService.getDtoById(id));
        return "thuoc/form";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("item") ThuocDto dto, BindingResult result, RedirectAttributes ra) {
        if (result.hasErrors()) return "thuoc/form";
        var saved = thuocService.saveDto(dto);
        ra.addFlashAttribute("success", "Đã cập nhật thuốc " + saved.getTenThuoc());
        return "redirect:/thuoc";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id, RedirectAttributes ra) {
        thuocService.delete(id);
        ra.addFlashAttribute("success", "Đã xóa thuốc " + id);
        return "redirect:/thuoc";
    }
}
