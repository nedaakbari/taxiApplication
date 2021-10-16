package enumration;

public enum DriverStatus {
    ONTRIP("onTrip"),//در حال انجام سفر
    WAITING("waiting");// در انتظار سفر هشست
    private String abbr;

    DriverStatus(String abbr) {
        this.abbr = abbr;
    }

    public String getAbbr() {
        return abbr;
    }
}
