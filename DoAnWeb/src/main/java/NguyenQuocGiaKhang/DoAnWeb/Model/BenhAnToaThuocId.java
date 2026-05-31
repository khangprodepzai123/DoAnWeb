package NguyenQuocGiaKhang.DoAnWeb.Model;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class BenhAnToaThuocId implements Serializable {

    private String maBenhAn;

    private String maThuoc;
}