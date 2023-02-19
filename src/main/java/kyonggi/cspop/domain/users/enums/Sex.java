package kyonggi.cspop.domain.users.enums;

public enum Sex {

    MALE("남자"), FEMALE("여자");

    private String SexToString;

    Sex(String sexToString) {
        SexToString = sexToString;
    }

    public static Sex toSex(String sex) {
        if (sex.equals("남자")) {
            return MALE;
        }
        return FEMALE;
    }
}
