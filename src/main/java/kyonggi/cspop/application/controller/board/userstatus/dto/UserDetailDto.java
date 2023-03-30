package kyonggi.cspop.application.controller.board.userstatus.dto;

import kyonggi.cspop.domain.form.submitform.SubmitForm;
import kyonggi.cspop.domain.form.submitform.enums.GraduationRequirements;
import lombok.Data;

@Data
public class UserDetailDto {

    /**
     * 유저별 진행상황 뷰 공통 속성
     */
    private String studentId;
    private String graduationDate;
    private String studentName;
    private String department;
    private String advisor;
    private boolean capstoneCompletionStatus;
    private String qualification;

    /**
     * 유저별 진행상황 뷰 분리될 속성
     */
    private boolean otherQualifications;
    private boolean thesis;


    public UserDetailDto(String studentId,
                         String studentName,
                         String department,
                         String advisor,
                         boolean capstoneCompletionStatus,
                         SubmitForm submitForm,
                         String graduationDate) {

        this.studentId = studentId;
        this.graduationDate = graduationDate;
        this.studentName = studentName;
        this.department = department;
        this.advisor = advisor;
        this.capstoneCompletionStatus = capstoneCompletionStatus;

        if (submitForm.getGraduationRequirements().equals(GraduationRequirements.THESIS)) {
            this.otherQualifications = false;
            this.thesis = true;
        }

        if (submitForm.getGraduationRequirements().equals(GraduationRequirements.Other_Qualifications)) {
            this.otherQualifications = true;
            this.thesis = false;
        }
    }

    public UserDetailDto(String studentId, String studentName, String department) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.department = department;
    }

    public UserDetailDto(String studentId, String graduationDate, String studentName, String department, String advisor, SubmitForm submitForm) {
        this.studentId = studentId;
        this.graduationDate = graduationDate;
        this.studentName = studentName;
        this.department = department;
        this.advisor = advisor;

        if (submitForm.getGraduationRequirements().equals(GraduationRequirements.THESIS)) {
            this.qualification = "논문";
        } else {
            this.qualification = "기타자격";
        }
    }
}
