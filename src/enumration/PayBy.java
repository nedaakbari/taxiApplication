package enumration;

public enum PayBy {
    BYCASH,
    BYACCCOUNTBALANCE;
    public static String getValuetoString(PayBy payBy) {
        switch (payBy) {
            case BYCASH:
                return "BYCASH";
            case BYACCCOUNTBALANCE:
                return "BYACCCOUNTBALANCE";
            default:
                return "BYCASH";
        }
    }
}
