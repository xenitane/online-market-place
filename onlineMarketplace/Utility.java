package onlineMarketplace;

public class Utility {
    public static Boolean isNull(Object o) {
        return o == null;
    }

    public static Boolean isNullOrEmpty(String str) {
        return isNull(str) || str.trim().isEmpty();
    }

    public static Boolean isNullOrEmpty(Double d) {
        return isNull(d) || d <= 0;
    }

    public static Boolean isNullOrEmpty(Integer i) {
        return isNull(i) || i <= 0;
    }
}