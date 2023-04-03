package kyonggi.cspop.application.controller.form.finalForm;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FinalFormDto {

    private String title;

    private String division;

    private String qualification;

    @NumberFormat(style = NumberFormat.Style.NUMBER )
    private Integer pageNumber;

    private MultipartFile finalFormUploadFile;
}
