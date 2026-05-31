package NguyenQuocGiaKhang.DoAnWeb.Controller;

import NguyenQuocGiaKhang.DoAnWeb.Model.BenhNhan;
import NguyenQuocGiaKhang.DoAnWeb.Service.BenhNhanService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/benhnhan")
public class BenhNhanController {

    private final BenhNhanService benhNhanService;

    public BenhNhanController(BenhNhanService benhNhanService) {
        this.benhNhanService = benhNhanService;
    }

    // Danh sách bệnh nhân
    @GetMapping("")
    public String index(Model model) {

        model.addAttribute(
                "benhNhans",
                benhNhanService.getAll());

        return "benhnhan/index";
    }

    // Form thêm
    @GetMapping("/create")
    public String createForm(Model model) {

        model.addAttribute(
                "benhNhan",
                new BenhNhan());

        return "benhnhan/create";
    }

    // Lưu
    @PostMapping("/save")
    public String save(@ModelAttribute BenhNhan benhNhan) {

        benhNhanService.save(benhNhan);

        return "redirect:/benhnhan";
    }

    // Form sửa
    @GetMapping("/edit/{id}")
    public String editForm(
            @PathVariable String id,
            Model model) {

        model.addAttribute(
                "benhNhan",
                benhNhanService.getById(id));

        return "benhnhan/edit";
    }

    // Update
    @PostMapping("/update")
    public String update(
            @ModelAttribute BenhNhan benhNhan) {

        benhNhanService.save(benhNhan);

        return "redirect:/benhnhan";
    }

    // Xóa
    @GetMapping("/delete/{id}")
    public String delete(
            @PathVariable String id) {

        benhNhanService.delete(id);

        return "redirect:/benhnhan";
    }
}