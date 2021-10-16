package enumration;

public enum PassengerStatus {
    ONTRIP,
    ABSENCE;

    public static PassengerStatus getValue(String status) {
        switch (status.toLowerCase()) {
            case "ontrip":
                return ONTRIP;
            case "absence":
                return ABSENCE;
            default:
                return ABSENCE;
        }
    }

    public static String getValuetoString(PassengerStatus status) {
        switch (status) {
            case ONTRIP:
                return "ONTRIP";
            case ABSENCE:
                return "ABSENCE";
            default:
                return "ABSENCE";
        }
    }


}
