package kyonggi.cspop.application.controller.form.interimForm;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class InterimFormDto {

    private String title;

    private String division;

    private String text;

    private String plan;

    private MultipartFile interimFormUploadFile;
}
