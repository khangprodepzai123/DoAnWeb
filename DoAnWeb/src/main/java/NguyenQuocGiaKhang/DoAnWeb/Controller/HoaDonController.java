package NguyenQuocGiaKhang.DoAnWeb.Controller;

import NguyenQuocGiaKhang.DoAnWeb.Service.HoaDonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/hoadon")
public class HoaDonController {

    private final HoaDonService hoaDonService;

    public HoaDonController(HoaDonService hoaDonService) {
        this.hoaDonService = hoaDonService;
    }

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("items", hoaDonService.getAllDtos());
        return "hoadon/index";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable String id, Model model) {
        model.addAttribute("item", hoaDonService.getDtoById(id));
        return "hoadon/detail";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id, RedirectAttributes ra) {
        hoaDonService.delete(id);
        ra.addFlashAttribute("success", "Đã xóa hóa đơn " + id);
        return "redirect:/hoadon";
    }
}
