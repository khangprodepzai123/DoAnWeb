package NguyenQuocGiaKhang.DoAnWeb.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class ChiTietHoaDonId implements Serializable {

    @Column(name = "MaHd", length = 10)
    private String maHd;

    @Column(name = "MaThuoc", length = 10)
    private String maThuoc;
}
