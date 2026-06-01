package NguyenQuocGiaKhang.DoAnWeb.Controller;

import NguyenQuocGiaKhang.DoAnWeb.Model.VaiTro;
import NguyenQuocGiaKhang.DoAnWeb.Service.BenhNhanService;
import NguyenQuocGiaKhang.DoAnWeb.Service.NhanVienService;
import NguyenQuocGiaKhang.DoAnWeb.Service.TaiKhoanBenhNhanService;
import NguyenQuocGiaKhang.DoAnWeb.dto.TaiKhoanDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/taikhoan")
public class TaiKhoanController {

    private final TaiKhoanBenhNhanService taiKhoanService;
    private final BenhNhanService benhNhanService;
    private final NhanVienService nhanVienService;

    public TaiKhoanController(
            TaiKhoanBenhNhanService taiKhoanService,
            BenhNhanService benhNhanService,
            NhanVienService nhanVienService) {
        this.taiKhoanService = taiKhoanService;
        this.benhNhanService = benhNhanService;
        this.nhanVienService = nhanVienService;
    }

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("items", taiKhoanService.getAllDtos());
        return "taikhoan/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        prepareForm(model, new TaiKhoanDto());
        return "taikhoan/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("item") TaiKhoanDto dto, BindingResult result, Model model, RedirectAttributes ra) {
        if (result.hasErrors()) {
            prepareForm(model, dto);
            return "taikhoan/form";
        }
        var saved = taiKhoanService.saveDto(dto);
        ra.addFlashAttribute("success", "Đã tạo tài khoản " + saved.getTenDangNhap());
        return "redirect:/taikhoan";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable String id, Model model) {
        prepareForm(model, taiKhoanService.getDtoById(id));
        return "taikhoan/form";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("item") TaiKhoanDto dto, BindingResult result, Model model, RedirectAttributes ra) {
        if (result.hasErrors()) {
            prepareForm(model, dto);
            return "taikhoan/form";
        }
        if (dto.getMatKhau() == null || dto.getMatKhau().isBlank()) {
            var existing = taiKhoanService.getEntityById(dto.getMaTk());
            dto.setMatKhau(existing.getMatKhau());
        }
        var saved = taiKhoanService.saveDto(dto);
        ra.addFlashAttribute("success", "Đã cập nhật tài khoản " + saved.getTenDangNhap());
        return "redirect:/taikhoan";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id, RedirectAttributes ra) {
        taiKhoanService.delete(id);
        ra.addFlashAttribute("success", "Đã xóa tài khoản " + id);
        return "redirect:/taikhoan";
    }

    private void prepareForm(Model model, TaiKhoanDto dto) {
        model.addAttribute("item", dto);
        model.addAttribute("benhNhans", benhNhanService.getAllDtos());
        model.addAttribute("nhanViens", nhanVienService.getAllDtos());
        model.addAttribute("vaiTros", VaiTro.values());
    }
}
