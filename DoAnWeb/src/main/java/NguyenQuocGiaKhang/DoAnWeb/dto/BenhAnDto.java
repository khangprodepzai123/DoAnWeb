package NguyenQuocGiaKhang.DoAnWeb.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class BenhAnDto {

    private String maBenhAn;
    private String maKham;
    private String maBn;
    private String maBs;

    private String lyDoKham;
    private String quaTrinhBenhLy;
    private String tienSuBenhNhan;
    private String tienSuGiaDinh;
    private String khamBoPhan;
    private String chuanDoan;
    private String huongXuTri;
    private String loaiKham;
    private String xuTriKham;

    private LocalDate ngayKham;
    private LocalDateTime ngayLuu;

    private String hoTenBn;
    private String hoTenBs;
}
