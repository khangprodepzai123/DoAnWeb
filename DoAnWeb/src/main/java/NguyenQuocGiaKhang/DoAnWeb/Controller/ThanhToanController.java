package NguyenQuocGiaKhang.DoAnWeb.Controller;

import NguyenQuocGiaKhang.DoAnWeb.Service.ThanhToanService;
import NguyenQuocGiaKhang.DoAnWeb.exception.BusinessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/thanhtoan")
public class ThanhToanController {

    private final ThanhToanService thanhToanService;

    public ThanhToanController(ThanhToanService thanhToanService) {
        this.thanhToanService = thanhToanService;
    }

    @GetMapping("/{maBn}")
    public String form(@PathVariable String maBn, Model model, RedirectAttributes ra) {
        try {
            model.addAttribute("thanhToan", thanhToanService.buildPreview(maBn));
            model.addAttribute("maBn", maBn);
            return "thanhtoan/form";
        } catch (BusinessException ex) {
            ra.addFlashAttribute("error", ex.getMessage());
            return "redirect:/khambenh";
        }
    }

    @PostMapping("/{maBn}")
    public String pay(
            @PathVariable String maBn,
            @RequestParam(required = false) String dungDiem,
            RedirectAttributes ra) {
        try {
            int diemTichLuySuDung = "true".equals(dungDiem) ? 1 : 0;
            String maHd = thanhToanService.processPayment(maBn, diemTichLuySuDung);
            ra.addFlashAttribute("success", "Thanh toán thành công. Mã hóa đơn: " + maHd);
            return "redirect:/hoadon/detail/" + maHd;
        } catch (BusinessException ex) {
            ra.addFlashAttribute("error", ex.getMessage());
            return "redirect:/thanhtoan/" + maBn;
        }
    }
}
