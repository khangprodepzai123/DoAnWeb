package NguyenQuocGiaKhang.DoAnWeb.util;

import NguyenQuocGiaKhang.DoAnWeb.exception.BusinessException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Set;

@Service
public class FileStorageService {

    private static final Set<String> ALLOWED_TYPES = Set.of(
            "image/jpeg", "image/png", "image/webp", "image/gif"
    );
    private static final long MAX_SIZE = 2 * 1024 * 1024;

    private final Path uploadDir;

    public FileStorageService(@Value("${app.upload.bacsi-dir:uploads/bacsi}") String uploadDir) {
        this.uploadDir = Paths.get(uploadDir).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.uploadDir);
        } catch (IOException e) {
            throw new BusinessException("Không tạo được thư mục lưu ảnh bác sĩ");
        }
    }

    public String storeBacSiImage(MultipartFile file, String maBs) {
        if (file == null || file.isEmpty()) {
            return null;
        }
        if (maBs == null || maBs.isBlank()) {
            throw new BusinessException("Mã bác sĩ không hợp lệ để lưu ảnh");
        }
        String contentType = file.getContentType();
        if (contentType == null || !ALLOWED_TYPES.contains(contentType)) {
            throw new BusinessException("Chỉ chấp nhận ảnh JPG, PNG, WEBP hoặc GIF");
        }
        if (file.getSize() > MAX_SIZE) {
            throw new BusinessException("Ảnh không được vượt quá 2MB");
        }

        String extension = resolveExtension(contentType);
        String fileName = maBs + extension;
        Path target = uploadDir.resolve(fileName).normalize();
        if (!target.startsWith(uploadDir)) {
            throw new BusinessException("Tên file không hợp lệ");
        }

        try {
            Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException e) {
            throw new BusinessException("Không thể lưu ảnh bác sĩ");
        }
    }

    public void deleteBacSiImage(String fileName) {
        if (fileName == null || fileName.isBlank()) {
            return;
        }
        try {
            Files.deleteIfExists(uploadDir.resolve(fileName).normalize());
        } catch (IOException ignored) {
            // Không chặn luồng xóa bác sĩ nếu xóa file thất bại
        }
    }

    private String resolveExtension(String contentType) {
        return switch (contentType) {
            case "image/png" -> ".png";
            case "image/webp" -> ".webp";
            case "image/gif" -> ".gif";
            default -> ".jpg";
        };
    }
}
