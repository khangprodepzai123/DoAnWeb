package NguyenQuocGiaKhang.DoAnWeb.Controller;

import NguyenQuocGiaKhang.DoAnWeb.Service.BacSiService;
import NguyenQuocGiaKhang.DoAnWeb.dto.BacSiDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/bacsi")
public class BacSiController {

    private final BacSiService bacSiService;

    public BacSiController(BacSiService bacSiService) {
        this.bacSiService = bacSiService;
    }

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("items", bacSiService.getAllDtos());
        return "bacsi/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("item", new BacSiDto());
        return "bacsi/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("item") BacSiDto dto, BindingResult result, RedirectAttributes ra) {
        if (result.hasErrors()) return "bacsi/form";
        var saved = bacSiService.saveDto(dto);
        ra.addFlashAttribute("success", "Đã lưu bác sĩ " + saved.getHoTenBs());
        return "redirect:/bacsi";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable String id, Model model) {
        model.addAttribute("item", bacSiService.getDtoById(id));
        return "bacsi/form";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("item") BacSiDto dto, BindingResult result, RedirectAttributes ra) {
        if (result.hasErrors()) return "bacsi/form";
        var saved = bacSiService.saveDto(dto);
        ra.addFlashAttribute("success", "Đã cập nhật bác sĩ " + saved.getHoTenBs());
        return "redirect:/bacsi";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id, RedirectAttributes ra) {
        bacSiService.delete(id);
        ra.addFlashAttribute("success", "Đã xóa bác sĩ " + id);
        return "redirect:/bacsi";
    }
}
