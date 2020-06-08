package javalang.korean.classapplication;

public enum Weekday {
    MONDAY('월'),
    TUESDAY('화'),
    WEDNESDAY('수'),
    THURSDAY('목'),
    FRIDAY('금');

    private final char value;

    Weekday(char value) {
        this.value = value;
    }

    static int ordinalOf(char value) {
        switch (value) {
            case '월' -> {
                return MONDAY.ordinal();
            }
            case '화' -> {
                return TUESDAY.ordinal();
            }
            case '수' -> {
                return WEDNESDAY.ordinal();
            }
            case '목' -> {
                return THURSDAY.ordinal();
            }
            case '금' -> {
                return FRIDAY.ordinal();
            }
            default -> {
                return -5;
            }
        }
    }

    char getValue() {
        return value;
    }
}
