package NguyenQuocGiaKhang.DoAnWeb.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChiTietHoaDonSchemaFix implements ApplicationRunner {

    private final JdbcTemplate jdbcTemplate;

    public ChiTietHoaDonSchemaFix(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(ApplicationArguments args) {
        dropLegacyTable("ChiTietHoaDon");

        String tableName = resolveTableName();
        if (tableName == null) {
            createTable();
            return;
        }

        List<String> columns = jdbcTemplate.queryForList(
                "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS " +
                        "WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = ?",
                String.class,
                tableName);

        boolean hasLegacyColumns = columns.stream()
                .anyMatch(c -> "MaHd".equals(c) || "MaThuoc".equals(c)
                        || "SoLuong".equals(c) || "DonGia".equals(c));

        boolean missingSnakePk = !columns.contains("ma_hd") || !columns.contains("ma_thuoc");

        if (hasLegacyColumns || missingSnakePk) {
            recreateTable(tableName);
        }
    }

    private void dropLegacyTable(String legacyName) {
        List<String> tables = jdbcTemplate.queryForList(
                "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES " +
                        "WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = ?",
                String.class,
                legacyName);
        if (!tables.isEmpty()) {
            jdbcTemplate.execute("DROP TABLE IF EXISTS `" + legacyName + "`");
        }
    }

    private String resolveTableName() {
        List<String> tables = jdbcTemplate.queryForList(
                "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES " +
                        "WHERE TABLE_SCHEMA = DATABASE() AND LOWER(TABLE_NAME) = 'chi_tiet_hoa_don'",
                String.class);
        return tables.isEmpty() ? null : tables.get(0);
    }

    private void recreateTable(String tableName) {
        jdbcTemplate.execute("DROP TABLE IF EXISTS `" + tableName + "`");
        createTable();
    }

    private void createTable() {
        jdbcTemplate.execute("""
                CREATE TABLE IF NOT EXISTS chi_tiet_hoa_don (
                    ma_hd VARCHAR(10) NOT NULL,
                    ma_thuoc VARCHAR(10) NOT NULL,
                    so_luong INT NOT NULL,
                    don_gia DECIMAL(19,2) NOT NULL,
                    PRIMARY KEY (ma_hd, ma_thuoc)
                )
                """);
    }
}
