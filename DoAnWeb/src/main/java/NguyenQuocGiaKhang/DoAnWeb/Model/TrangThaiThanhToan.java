package NguyenQuocGiaKhang.DoAnWeb.Model;

public final class TrangThaiThanhToan {

    public static final String CHUA_THANH_TOAN = "Chưa thanh toán";
    public static final String DA_THANH_TOAN = "Đã thanh toán";

    private TrangThaiThanhToan() {
    }

    public static boolean isDaThanhToan(String trangThai) {
        return trangThai == null || DA_THANH_TOAN.equals(trangThai);
    }
}
