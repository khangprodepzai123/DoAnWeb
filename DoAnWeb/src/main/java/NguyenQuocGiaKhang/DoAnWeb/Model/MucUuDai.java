package NguyenQuocGiaKhang.DoAnWeb.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
}