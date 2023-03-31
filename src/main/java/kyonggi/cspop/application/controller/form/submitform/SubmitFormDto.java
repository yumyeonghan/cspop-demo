package kyonggi.cspop.application.controller.form.submitform;

import kyonggi.cspop.domain.form.submitform.enums.GraduationRequirements;
import lombok.Data;


@Data
public class SubmitFormDto {

    private String studentId;
    private String studentName;
    private String department;
    private boolean approval;
    private String qualification;
}
