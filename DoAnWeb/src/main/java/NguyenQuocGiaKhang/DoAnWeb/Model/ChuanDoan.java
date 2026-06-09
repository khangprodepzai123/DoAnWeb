package NguyenQuocGiaKhang.DoAnWeb.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ChuanDoan")
public class ChuanDoan {

    @Id
    @Column(name = "MaCd", length = 10)
    private String maCd;

    @Column(name = "TenCd", nullable = false, length = 200)
    private String tenCd;

    @Column(name = "MoTa", length = 500)
    private String moTa;

    @OneToMany(mappedBy = "chuanDoanEntity")
    private List<KhamBenh> khamBenhs = new ArrayList<>();


    public String getMaCd() {
        return maCd;
    }

    public void setMaCd(String maCd) {
        this.maCd = maCd;
    }

    public String getTenCd() {
        return tenCd;
    }

    public void setTenCd(String tenCd) {
        this.tenCd = tenCd;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public List<KhamBenh> getKhamBenhs() {
        return khamBenhs;
    }

    public void setKhamBenhs(List<KhamBenh> khamBenhs) {
        this.khamBenhs = khamBenhs;
    }
}
