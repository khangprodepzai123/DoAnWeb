package NguyenQuocGiaKhang.DoAnWeb.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ToaThuocSchemaFix implements ApplicationRunner {

    private final JdbcTemplate jdbcTemplate;

    public ToaThuocSchemaFix(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(ApplicationArguments args) {
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
                .anyMatch(c -> "MaKham".equals(c) || "MaThuoc".equals(c));

        if (hasLegacyColumns) {
            recreateTable(tableName);
        }
    }

    private String resolveTableName() {
        List<String> tables = jdbcTemplate.queryForList(
                "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES " +
                        "WHERE TABLE_SCHEMA = DATABASE() AND LOWER(TABLE_NAME) = 'toa_thuoc'",
                String.class);
        return tables.isEmpty() ? null : tables.get(0);
    }

    private void recreateTable(String tableName) {
        jdbcTemplate.execute("DROP TABLE IF EXISTS `" + tableName + "`");
        createTable();
    }

    private void createTable() {
        jdbcTemplate.execute("""
                CREATE TABLE IF NOT EXISTS toa_thuoc (
                    ma_kham VARCHAR(10) NOT NULL,
                    ma_thuoc VARCHAR(10) NOT NULL,
                    so_luong INT,
                    lieu_dung VARCHAR(200),
                    cach_dung VARCHAR(300),
                    PRIMARY KEY (ma_kham, ma_thuoc)
                )
                """);
    }
}
