package kyonggi.cspop.exception;

import lombok.Getter;

@Getter
public class ErrorResponse {

    private int status; //404
    private String code; //BAD_REQUEST
    private String errorMessage;

    public ErrorResponse(CsPopErrorCode csPopErrorCode) {
        status = csPopErrorCode.getHttpStatus().value();
        code = csPopErrorCode.getHttpStatus().name();
        errorMessage = csPopErrorCode.getErrorMessage();
    }
}
