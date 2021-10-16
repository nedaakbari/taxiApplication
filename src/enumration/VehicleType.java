package enumration;

public enum VehicleType {
    CAR,
    MOTORCYCLE,
    VAN;


    public static VehicleType getValue(String name) {
        switch (name.toLowerCase()) {
            case "car":
                return CAR;
            case "motorcycle":
                return MOTORCYCLE;
            case "van":
                return VAN;
            default:
                return CAR;
        }
    }

    public static String getValuetoString(VehicleType type) {
        switch (type) {
            case CAR:
                return "CAR";
            case MOTORCYCLE:
                return "MOTORCYCLE";
            case VAN:
                return "VAN";
            default:
                return "CAR";
        }
    }
}


