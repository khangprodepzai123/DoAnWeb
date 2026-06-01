package NguyenQuocGiaKhang.DoAnWeb.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class KhamBenhDto {

    @Size(max = 10)
    private String maKham;

    @NotBlank(message = "Mã bệnh nhân không được để trống")
    private String maBn;

    @NotBlank(message = "Mã bác sĩ không được để trống")
    private String maBs;

    private String maCd;

    private LocalDate ngayKham;

    @Size(max = 500)
    private String lyDoKham;

    @Size(max = 1000)
    private String quaTrinhBenhLy;

    @Size(max = 500)
    private String tienSuBenhNhan;

    @Size(max = 500)
    private String tienSuGiaDinh;

    @Size(max = 1000)
    private String khamBoPhan;

    @Size(max = 500)
    private String chuanDoan;

    @Size(max = 500)
    private String huongXuTri;

    @Size(max = 50)
    private String loaiKham;

    @Size(max = 100)
    private String xuTriKham;

    @Size(max = 50)
    private String trangThai;

    /** Hiển thị trên form */
    private String hoTenBn;

    private String hoTenBs;
}
