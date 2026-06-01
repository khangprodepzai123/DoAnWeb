package NguyenQuocGiaKhang.DoAnWeb.dto;

import NguyenQuocGiaKhang.DoAnWeb.Model.VaiTro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaiKhoanDto {

    @Size(max = 10)
    private String maTk;

    @NotBlank(message = "Tên đăng nhập không được để trống")
    @Size(max = 50)
    private String tenDangNhap;

    @Size(max = 255)
    private String matKhau;

    /** Dùng khi tạo / đổi mật khẩu */
    private String matKhauXacNhan;

    private Integer diemTichLuy;

    @Size(max = 255)
    private String hoTenBn;

    private VaiTro vaiTro;

    private String maBn;

    private String maNv;
}
