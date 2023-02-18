package kyonggi.cspop.domain.schedule.enums;

import lombok.Getter;

@Getter
public enum Step {

    RECEIVED("신청접수"), PROPOSAL("제안서"), INTERIM_REPORT("중간보고서"),
    FINAL_REPORT("최종보고서"), FINAL_PASS("최종통과"), OTHER_QUALIFICATIONS("기타자격");

    private String stepToString;

    Step(String stepToString) {
        this.stepToString = stepToString;
    }
}
