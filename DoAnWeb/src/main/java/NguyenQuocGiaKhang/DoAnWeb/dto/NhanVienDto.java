package NguyenQuocGiaKhang.DoAnWeb.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NhanVienDto {

    @Size(max = 10)
    private String maNv;

    @NotBlank(message = "Họ tên nhân viên không được để trống")
    @Size(max = 100)
    private String hoTenNv;
}
