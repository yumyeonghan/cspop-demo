package kyonggi.cspop.application.excel;

import kyonggi.cspop.domain.board.ExcelBoard;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 엑셀 파일의 업로드와 다운로드만을 위한 컨트롤러
 */
@Controller
@RequiredArgsConstructor
public class ExcelController{

    @Resource(name="excelService")
    private final ExcelService excelService;

    //엑셀 업로드 화면(임시)
    @GetMapping("/excel")
    public String excel() {
        return "excel";
    }

    //업로드한 엑셀 저장
    @RequestMapping(value = "/excel.read", method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        List<ExcelBoard> dataList = new ArrayList<>();

        //파일 확장자명->엑셀 파일 확장자인지 확인
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        //그외 확장자는 예외 던짐
        if (!Objects.equals(extension, "xlsx") &&
                !Objects.equals(extension, "xls")) {
            throw new IOException("엑셀파일만 업로드 해주세요!");
        }

        Workbook workbook = null;

        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        Sheet worksheet = Objects.requireNonNull(workbook).getSheetAt(0);

        //ExcelBoard 객체 리스트 형태로 저장
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {

            Row row = worksheet.getRow(i);

            ExcelBoard data = new ExcelBoard();

            data.setStudentId(row.getCell(0).getStringCellValue());
            data.setStudentName(row.getCell(1).getStringCellValue());
            data.setProfessorName(row.getCell(2).getStringCellValue());
            data.setGraduationDate(row.getCell(3).getStringCellValue());
            data.setStep(row.getCell(4).getStringCellValue());
            data.setState(row.getCell(5).getStringCellValue());
            data.setOtherQualifications(row.getCell(6).getStringCellValue());
            data.setCapstoneCompletion(row.getCell(7).getStringCellValue());
            dataList.add(data);
        }
        model.addAttribute("dataL", dataList);

        return "excelList";
    }

    /**
     * 엑셀 파일 download method
     */
    @RequestMapping(value = "/excelDown.do",method = RequestMethod.POST)
    public String ExcelDown(HttpServletResponse response) {
        excelService.getExcelDown(response);

        return "excel";
    }
}
