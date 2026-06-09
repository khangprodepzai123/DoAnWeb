package NguyenQuocGiaKhang.DoAnWeb.Controller;

import NguyenQuocGiaKhang.DoAnWeb.Service.BacSiService;
import NguyenQuocGiaKhang.DoAnWeb.dto.BacSiDto;
import NguyenQuocGiaKhang.DoAnWeb.exception.BusinessException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
    public String save(
            @Valid @ModelAttribute("item") BacSiDto dto,
            @RequestParam(value = "anhFile", required = false) MultipartFile anhFile,
            BindingResult result,
            RedirectAttributes ra) {
        if (result.hasErrors()) {
            return "bacsi/form";
        }
        try {
            var saved = bacSiService.saveDto(dto, anhFile);
            ra.addFlashAttribute("success", "Đã lưu bác sĩ " + saved.getHoTenBs());
            return "redirect:/bacsi";
        } catch (BusinessException ex) {
            ra.addFlashAttribute("error", ex.getMessage());
            return "redirect:/bacsi/create";
        }
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable String id, Model model) {
        model.addAttribute("item", bacSiService.getDtoById(id));
        return "bacsi/form";
    }

    @PostMapping("/update")
    public String update(
            @Valid @ModelAttribute("item") BacSiDto dto,
            @RequestParam(value = "anhFile", required = false) MultipartFile anhFile,
            BindingResult result,
            RedirectAttributes ra) {
        if (result.hasErrors()) {
            return "bacsi/form";
        }
        try {
            var saved = bacSiService.saveDto(dto, anhFile);
            ra.addFlashAttribute("success", "Đã cập nhật bác sĩ " + saved.getHoTenBs());
            return "redirect:/bacsi";
        } catch (BusinessException ex) {
            ra.addFlashAttribute("error", ex.getMessage());
            return "redirect:/bacsi/edit/" + dto.getMaBs();
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id, RedirectAttributes ra) {
        bacSiService.delete(id);
        ra.addFlashAttribute("success", "Đã xóa bác sĩ " + id);
        return "redirect:/bacsi";
    }
}
