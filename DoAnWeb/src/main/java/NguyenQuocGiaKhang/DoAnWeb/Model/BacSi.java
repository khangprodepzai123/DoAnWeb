package NguyenQuocGiaKhang.DoAnWeb.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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
}
