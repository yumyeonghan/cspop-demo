package kyonggi.cspop.application.excel;

import kyonggi.cspop.domain.board.ExcelBoard;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 엑셀 파일의 업로드와 다운로드만을 위한 컨트롤러
 */
@Controller
@RequiredArgsConstructor
public class ExcelController{

    @Autowired
    private ExcelRepository repository;

    Logger logger = LoggerFactory.getLogger(ExcelController.class);


    //엑셀 업로드 화면(임시)
    @GetMapping("/excel")
    public String excel() {
        return "excel";
    }

    /**
     * excel file upload method
     * @param file
     * @param model
     * @return
     * @throws IOException
     */
    //업로드한 엑셀 저장
    @PostMapping("/excel.read")
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

        return "excel";
    }

    /**
     * Excel file download method
     * --> upload db 저장 구현 미완성으로 임시로 db 값 삽입 후 실행
     * @param response
     * @return
     */
    @SneakyThrows
    @GetMapping( "/excel.download")
    public ResponseEntity<InputStreamResource> downloadExcel(HttpServletResponse response) {
        try (Workbook workbook = new XSSFWorkbook()){
            //Excel Down 시작

            //시트생성
            Sheet sheet = workbook.createSheet("졸업 대상자 조회");

            //행 번호
            int rowNo = 0;

            // 테이블 헤더용 스타일
            CellStyle headStyle = workbook.createCellStyle();
            headStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.LIGHT_ORANGE.getIndex());
            headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            Font font = workbook.createFont();
            font.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
            font.setFontHeightInPoints((short) 13);
            headStyle.setFont(font);

            //헤더 생성
            Row headerRow = sheet.createRow(rowNo++);
            headerRow.createCell(0).setCellValue("학번");
            headerRow.createCell(1).setCellValue("이름");
            headerRow.createCell(2).setCellValue("지도교수");
            headerRow.createCell(3).setCellValue("졸업날짜");
            headerRow.createCell(4).setCellValue("단계");
            headerRow.createCell(5).setCellValue("상태");
            headerRow.createCell(6).setCellValue("기타 자격");
            headerRow.createCell(7).setCellValue("캡스톤 이수");

            for (int i = 0; i <= 7; i++) {
                headerRow.getCell(i).setCellStyle(headStyle);
            }

            List<ExcelBoard> dataList = repository.findAll();
            // Body
            for (ExcelBoard excelBoard : dataList) {
                Row row = sheet.createRow(rowNo++);
                row.createCell(0).setCellValue(excelBoard.getStudentId());
                row.createCell(1).setCellValue(excelBoard.getStudentName());
                row.createCell(2).setCellValue(excelBoard.getProfessorName());
                row.createCell(3).setCellValue(excelBoard.getGraduationDate());
                row.createCell(4).setCellValue(excelBoard.getStep());
                row.createCell(5).setCellValue(excelBoard.getState());
                row.createCell(6).setCellValue(excelBoard.getOtherQualifications());
                row.createCell(7).setCellValue(excelBoard.getCapstoneCompletion());
            }

            sheet.setColumnWidth(0, 3000);
            sheet.setColumnWidth(1, 3000);
            sheet.setColumnWidth(2, 3000);
            sheet.setColumnWidth(3, 4000);
            sheet.setColumnWidth(4, 3000);
            sheet.setColumnWidth(5, 3000);
            sheet.setColumnWidth(6, 4000);
            sheet.setColumnWidth(7, 4000);

            // 컨텐츠 타입과 파일명 지정
            File tmpFile = File.createTempFile("TMP~", ".xlsx");
            try (OutputStream fos = new FileOutputStream(tmpFile);) {
                workbook.write(fos);
            }
            InputStream res = new FileInputStream(tmpFile) {
                @Override
                public void close() throws IOException {
                    super.close();
                }
            };

            return ResponseEntity.ok() //
                    .contentLength(tmpFile.length()) //
                    .contentType(MediaType.APPLICATION_OCTET_STREAM) //
                    .header("Content-Disposition", "attachment;filename=대상자 관리.xlsx") //
                    .body(new InputStreamResource(res));
        }
    }
}
