package NguyenQuocGiaKhang.DoAnWeb.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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
}
