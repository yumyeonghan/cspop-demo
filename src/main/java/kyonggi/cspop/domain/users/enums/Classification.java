package kyonggi.cspop.domain.users.enums;

public enum Classification {

    UNDERGRADUATE_STUDENT("학부생"), POSTGRADUATE_STUDENT("대학원생"), PROFESSOR("교수"), DOUBLE_MAJOR("복수전공");

    private String classificationToString;

    Classification(String classificationToString) {
        this.classificationToString = classificationToString;
    }
}
