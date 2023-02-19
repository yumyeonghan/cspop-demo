package kyonggi.cspop.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CsPopErrorCode {

    //Users
    DUPLICATE_STUDENT_ID(HttpStatus.CONFLICT, "이미 해당 학번이 등록되어 있습니다.");

    private HttpStatus httpStatus;
    private String errorMessage;

    CsPopErrorCode(HttpStatus httpStatus, String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }
}
