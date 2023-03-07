package kyonggi.cspop.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CsPopErrorCode {

    //Users
    DUPLICATE_STUDENT_ID(HttpStatus.CONFLICT, "이미 해당 학번이 등록되어 있습니다."),

    //ExcelBoard
    INVAILD_UPLOAD_FILE_EXTENSION(HttpStatus.NOT_ACCEPTABLE, "엑셀파일을 업로드 해주세요.");

    private HttpStatus httpStatus;
    private String errorMessage;

    CsPopErrorCode(HttpStatus httpStatus, String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }
}