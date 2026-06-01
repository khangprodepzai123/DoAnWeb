package NguyenQuocGiaKhang.DoAnWeb.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class MaIdGenerator {

    private static final Pattern NUMERIC_SUFFIX = Pattern.compile("^(\\D*)(\\d+)$");

    private MaIdGenerator() {
    }

    public static String nextMa(String prefix, String lastMa) {
        if (lastMa == null || lastMa.isBlank()) {
            return prefix + "001";
        }
        String trimmed = lastMa.trim();
        Matcher matcher = NUMERIC_SUFFIX.matcher(trimmed);
        if (!matcher.matches()) {
            return prefix + "001";
        }
        String numberPart = matcher.group(2);
        int next = Integer.parseInt(numberPart) + 1;
        int width = Math.max(3, numberPart.length());
        return prefix + String.format("%0" + width + "d", next);
    }
}
