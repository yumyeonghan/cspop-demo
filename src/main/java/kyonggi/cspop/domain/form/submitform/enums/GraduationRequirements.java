package kyonggi.cspop.domain.form.submitform.enums;

import lombok.Getter;

@Getter
public enum GraduationRequirements {
    THESIS("논문"), Other_Qualifications("기타자격");

    private final String graduationRequirements;

    GraduationRequirements(String graduationRequirements) {
        this.graduationRequirements = graduationRequirements;
    }
}
