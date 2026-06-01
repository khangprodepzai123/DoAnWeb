package NguyenQuocGiaKhang.DoAnWeb.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class ToaThuocId implements Serializable {

    @Column(name = "MaKham", length = 10)
    private String maKham;

    @Column(name = "MaThuoc", length = 10)
    private String maThuoc;
}
