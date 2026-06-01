package NguyenQuocGiaKhang.DoAnWeb.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BenhNhanDto {

    @Size(max = 10, message = "Mã bệnh nhân tối đa 10 ký tự")
    private String maBn;

    @NotBlank(message = "Họ tên không được để trống")
    @Size(max = 100, message = "Họ tên tối đa 100 ký tự")
    private String hoTenBn;

    @Size(max = 15, message = "SĐT tối đa 15 ký tự")
    private String sdt;

    private LocalDate ngaySinh;

    @Size(max = 10)
    private String gt;

    @Size(max = 50)
    private String doiTuong;

    @Size(max = 200)
    private String diaChi;

    @Size(max = 20)
    private String bhyt;
}
