package NguyenQuocGiaKhang.DoAnWeb.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BacSi")
public class BacSi {

    @Id
    @Column(name = "MaBs", length = 10)
    private String maBs;

    @Column(name = "HoTenBs", nullable = false, length = 100)
    private String hoTenBs;

    @Column(name = "TrinhDoHocVan", length = 200)
    private String trinhDoHocVan;

    @Column(name = "ChuyenKhoa", length = 200)
    private String chuyenKhoa;

    @Column(name = "Tuoi")
    private Integer tuoi;

    @Column(name = "KinhNghiem")
    private Integer kinhNghiem;

    @Column(name = "ChungChiHanhNghe", length = 200)
    private String chungChiHanhNghe;

    @OneToMany(mappedBy = "bacSi")
    private List<KhamBenh> khamBenhs = new ArrayList<>();


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

    public List<KhamBenh> getKhamBenhs() {
        return khamBenhs;
    }

    public void setKhamBenhs(List<KhamBenh> khamBenhs) {
        this.khamBenhs = khamBenhs;
    }
}
