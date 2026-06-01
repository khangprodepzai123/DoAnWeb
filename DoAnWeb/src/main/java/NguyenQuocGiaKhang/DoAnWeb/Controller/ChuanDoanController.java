package NguyenQuocGiaKhang.DoAnWeb.Controller;

import NguyenQuocGiaKhang.DoAnWeb.Service.ChuanDoanService;
import NguyenQuocGiaKhang.DoAnWeb.dto.ChuanDoanDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/chuandoan")
public class ChuanDoanController {

    private final ChuanDoanService chuanDoanService;

    public ChuanDoanController(ChuanDoanService chuanDoanService) {
        this.chuanDoanService = chuanDoanService;
    }

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("items", chuanDoanService.getAllDtos());
        return "chuandoan/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("item", new ChuanDoanDto());
        return "chuandoan/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("item") ChuanDoanDto dto, BindingResult result, RedirectAttributes ra) {
        if (result.hasErrors()) return "chuandoan/form";
        var saved = chuanDoanService.saveDto(dto);
        ra.addFlashAttribute("success", "Đã lưu chuẩn đoán " + saved.getTenCd());
        return "redirect:/chuandoan";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable String id, Model model) {
        model.addAttribute("item", chuanDoanService.getDtoById(id));
        return "chuandoan/form";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("item") ChuanDoanDto dto, BindingResult result, RedirectAttributes ra) {
        if (result.hasErrors()) return "chuandoan/form";
        var saved = chuanDoanService.saveDto(dto);
        ra.addFlashAttribute("success", "Đã cập nhật chuẩn đoán " + saved.getTenCd());
        return "redirect:/chuandoan";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id, RedirectAttributes ra) {
        chuanDoanService.delete(id);
        ra.addFlashAttribute("success", "Đã xóa chuẩn đoán " + id);
        return "redirect:/chuandoan";
    }
}
