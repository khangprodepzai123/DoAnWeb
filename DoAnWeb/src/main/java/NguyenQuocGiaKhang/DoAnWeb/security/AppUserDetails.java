package NguyenQuocGiaKhang.DoAnWeb.security;

import NguyenQuocGiaKhang.DoAnWeb.Model.TaiKhoanBenhNhan;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class AppUserDetails implements UserDetails {

    private final TaiKhoanBenhNhan taiKhoan;

    public AppUserDetails(TaiKhoanBenhNhan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public TaiKhoanBenhNhan getTaiKhoan() {
        return taiKhoan;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = taiKhoan.getVaiTro() != null ? taiKhoan.getVaiTro().name() : "BENH_NHAN";
        return List.of(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public String getPassword() {
        return taiKhoan.getMatKhau();
    }

    @Override
    public String getUsername() {
        return taiKhoan.getTenDangNhap();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

