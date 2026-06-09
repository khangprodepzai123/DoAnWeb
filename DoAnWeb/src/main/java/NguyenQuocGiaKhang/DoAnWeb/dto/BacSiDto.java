package NguyenQuocGiaKhang.DoAnWeb.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

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

    private String anhBs;


    public String getMaBs() {
        return maBs;
    }

    public void setMaBs(String maBs) {
        this.maBs = maBs;
    }

    public String getHoTenBs() {
        return hoTenBs;
    }

    public void setHoTenBs(String hoTenBs) {
        this.hoTenBs = hoTenBs;
    }

    public String getTrinhDoHocVan() {
        return trinhDoHocVan;
    }

    public void setTrinhDoHocVan(String trinhDoHocVan) {
        this.trinhDoHocVan = trinhDoHocVan;
    }

    public String getChuyenKhoa() {
        return chuyenKhoa;
    }

    public void setChuyenKhoa(String chuyenKhoa) {
        this.chuyenKhoa = chuyenKhoa;
    }

    public Integer getTuoi() {
        return tuoi;
    }

    public void setTuoi(Integer tuoi) {
        this.tuoi = tuoi;
    }

    public Integer getKinhNghiem() {
        return kinhNghiem;
    }

    public void setKinhNghiem(Integer kinhNghiem) {
        this.kinhNghiem = kinhNghiem;
    }

    public String getChungChiHanhNghe() {
        return chungChiHanhNghe;
    }

    public void setChungChiHanhNghe(String chungChiHanhNghe) {
        this.chungChiHanhNghe = chungChiHanhNghe;
    }

    public String getAnhBs() {
        return anhBs;
    }

    public void setAnhBs(String anhBs) {
        this.anhBs = anhBs;
    }
}
