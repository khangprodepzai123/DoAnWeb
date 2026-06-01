package NguyenQuocGiaKhang.DoAnWeb.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BacSiDto {

    @Size(max = 10)
    private String maBs;

    @NotBlank(message = "Họ tên bác sĩ không được để trống")
    @Size(max = 100)
    private String hoTenBs;

    @Size(max = 200)
    private String trinhDoHocVan;

    @Size(max = 200)
    private String chuyenKhoa;

    private Integer tuoi;

    private Integer kinhNghiem;

    @Size(max = 200)
    private String chungChiHanhNghe;
}
