package kyonggi.cspop.exception;

import lombok.Getter;

@Getter
public class CsPopException extends RuntimeException{

    private CsPopErrorCode csPopErrorCode;

    public CsPopException(CsPopErrorCode csPopErrorCode) {
        super(csPopErrorCode.getErrorMessage());
        this.csPopErrorCode = csPopErrorCode;
    }
}