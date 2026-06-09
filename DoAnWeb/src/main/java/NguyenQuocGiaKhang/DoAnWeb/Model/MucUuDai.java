package NguyenQuocGiaKhang.DoAnWeb.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "MucUuDai")
public class MucUuDai {

    @Id
    @Column(name = "MaMuc", length = 10)
    private String maMuc;

    @Column(name = "TenMuc", length = 50)
    private String tenMuc;

    @Column(name = "DiemToiThieu")
    private Integer diemToiThieu;

    @Column(name = "MoTa", length = 200)
    private String moTa;


    public String getMaMuc() {
        return maMuc;
    }

    public void setMaMuc(String maMuc) {
        this.maMuc = maMuc;
    }

    public String getTenMuc() {
        return tenMuc;
    }

    public void setTenMuc(String tenMuc) {
        this.tenMuc = tenMuc;
    }

    public Integer getDiemToiThieu() {
        return diemToiThieu;
    }

    public void setDiemToiThieu(Integer diemToiThieu) {
        this.diemToiThieu = diemToiThieu;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
