package NguyenQuocGiaKhang.DoAnWeb.security;

import NguyenQuocGiaKhang.DoAnWeb.Repository.TaiKhoanBenhNhanRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TaiKhoanUserDetailsService implements UserDetailsService {

    private final TaiKhoanBenhNhanRepository repository;

    public TaiKhoanUserDetailsService(TaiKhoanBenhNhanRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByTenDangNhap(username)
                .map(AppUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy tài khoản: " + username));
    }
}

