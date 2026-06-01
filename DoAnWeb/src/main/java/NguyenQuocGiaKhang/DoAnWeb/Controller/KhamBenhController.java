package NguyenQuocGiaKhang.DoAnWeb.Controller;

import NguyenQuocGiaKhang.DoAnWeb.Model.TrangThaiKham;
import NguyenQuocGiaKhang.DoAnWeb.Service.*;
import NguyenQuocGiaKhang.DoAnWeb.dto.KhamBenhDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/khambenh")
public class KhamBenhController {

    private final KhamBenhService khamBenhService;
    private final BenhNhanService benhNhanService;
    private final BacSiService bacSiService;
    private final ChuanDoanService chuanDoanService;

    public KhamBenhController(
            KhamBenhService khamBenhService,
            BenhNhanService benhNhanService,
            BacSiService bacSiService,
            ChuanDoanService chuanDoanService) {
        this.khamBenhService = khamBenhService;
        this.benhNhanService = benhNhanService;
        this.bacSiService = bacSiService;
        this.chuanDoanService = chuanDoanService;
    }

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("items", khamBenhService.getAllDtos());
        return "khambenh/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        prepareForm(model, new KhamBenhDto());
        return "khambenh/form";
    }

    @GetMapping("/dangky/{maBn}")
    public String dangKy(@PathVariable String maBn, RedirectAttributes ra) {
        var saved = khamBenhService.dangKyKham(maBn, "BS001");
        ra.addFlashAttribute("success", "Đã đăng ký khám " + saved.getMaKham() + " cho bệnh nhân");
        return "redirect:/khambenh/edit/" + saved.getMaKham();
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("item") KhamBenhDto dto, BindingResult result, Model model, RedirectAttributes ra) {
        if (result.hasErrors()) {
            prepareForm(model, dto);
            return "khambenh/form";
        }
        var saved = khamBenhService.saveDto(dto);
        ra.addFlashAttribute("success", "Đã lưu phiếu khám " + saved.getMaKham());
        return "redirect:/khambenh";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable String id, Model model) {
        prepareForm(model, khamBenhService.getDtoById(id));
        return "khambenh/form";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("item") KhamBenhDto dto, BindingResult result, Model model, RedirectAttributes ra) {
        if (result.hasErrors()) {
            prepareForm(model, dto);
            return "khambenh/form";
        }
        var saved = khamBenhService.saveDto(dto);
        ra.addFlashAttribute("success", "Đã cập nhật phiếu khám " + saved.getMaKham());
        return "redirect:/khambenh";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id, RedirectAttributes ra) {
        khamBenhService.delete(id);
        ra.addFlashAttribute("success", "Đã xóa phiếu khám " + id);
        return "redirect:/khambenh";
    }

    private void prepareForm(Model model, KhamBenhDto dto) {
        model.addAttribute("item", dto);
        model.addAttribute("benhNhans", benhNhanService.getAllDtos());
        model.addAttribute("bacSis", bacSiService.getAllDtos());
        model.addAttribute("chuanDoans", chuanDoanService.getAllDtos());
        model.addAttribute("trangThais", new String[]{TrangThaiKham.CHO_KHAM, TrangThaiKham.DA_KHAM});
    }
}
