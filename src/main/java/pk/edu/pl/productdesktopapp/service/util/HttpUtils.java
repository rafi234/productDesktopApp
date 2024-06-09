package pk.edu.pl.productdesktopapp.service.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpUtils {

    public static Long getLong(Object value) {
        return Long.parseLong(String.valueOf(value));
    }

    public static Boolean getBoolean(Object value) {
        return Boolean.parseBoolean(String.valueOf(value));
    }

    public static BigDecimal getBigDecimal(Object value) {
        return BigDecimal.valueOf(Double.parseDouble(String.valueOf(value)));
    }
}
