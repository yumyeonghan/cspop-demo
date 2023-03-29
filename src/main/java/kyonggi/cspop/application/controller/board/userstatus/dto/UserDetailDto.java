package kyonggi.cspop.application.controller.board.userstatus.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDetailDto {

    /**
     * 유저별 진행상황 뷰 공통 속성
     */
    private String studentId;
    private LocalDate graduationDate;
    private String studentName;
    private String department;
    private String advisor;
    private boolean capstoneCompletionStatus;
    //private String delayNumber;

    /**
     * 유저별 진행상황 뷰 분리될 속성
     */
    private boolean otherQualifications;
    private boolean thesis;

    public UserDetailDto(String studentId,
                         String studentName,
                         String department,
                         String advisor,
                         boolean capstoneCompletionStatus) {

        this.studentId = studentId;
        this.graduationDate = LocalDate.now();
        this.studentName = studentName;
        this.department = department;
        this.advisor = advisor;
        this.capstoneCompletionStatus = capstoneCompletionStatus;

        //신청서 만들면 거기서 꺼내와야함
        this.otherQualifications = true;
        this.thesis = false;
    }




}
