package kyonggi.cspop.domain.users.enums;

import lombok.Getter;

@Getter
public enum Classification {

    UNDERGRADUATE_STUDENT("학부생"), POSTGRADUATE_STUDENT("대학원생"), PROFESSOR("교수"), DOUBLE_MAJOR("복수전공");

    private String classificationToString;

    Classification(String classificationToString) {
        this.classificationToString = classificationToString;
    }

    public static Classification toClassification(String classification) {

        switch (classification) {
            case "학부생":
                return UNDERGRADUATE_STUDENT;

            case "대학원생":
                return POSTGRADUATE_STUDENT;

            case "교수":
                return PROFESSOR;

            case "복수전공":
                return DOUBLE_MAJOR;
        }
        return UNDERGRADUATE_STUDENT;
    }
}
