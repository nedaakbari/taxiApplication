package enumration;

public enum Gender {
    FEMALE, MALE;

    public static Gender getValue(String gender) {
        switch (gender.toLowerCase()) {
            case "female":
                return FEMALE;
            case "male":
                return MALE;
            default:
                return MALE;
        }
    }

    public static String getValuetoString(Gender gender) {
        switch (gender) {
            case FEMALE:
                return "FEMALE";
            case MALE:
                return "MALE";
            default:
                return "MALE";
        }
    }
}