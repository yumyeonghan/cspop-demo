package kyonggi.cspop.application.excel;

import kyonggi.cspop.domain.board.ExcelBoard;
import kyonggi.cspop.domain.board.repository.ExcelBoardRepository;
import kyonggi.cspop.domain.board.service.ExcelBoardService;
import kyonggi.cspop.exception.CsPopErrorCode;
import kyonggi.cspop.exception.CsPopException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
public class ExcelBoardController {

    private final ExcelBoardService excelBoardService;

    @GetMapping("/excel")
    public String excel(Model model) {
        List<ExcelBoard> dataList = excelBoardService.findExcelList();
        model.addAttribute("dataL", dataList);
        return "excel";
    }

    @PostMapping("/excel.read")
    public String upload(@RequestParam("file") MultipartFile file, Model model) throws IOException {

        //액셀 파일인지 검사
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        checkUploadFileExtension(extension);
        //업로드 된 Excel 파일의 데이터를 ExcelBoard 객체 리스트 형태로 저장 (액셀 파일의 문자만 받고, 숫자는 못받는 버그 수정해야함)
        Sheet worksheet = getWorksheet(file, extension);
        List<ExcelBoard> dataList = getExcelBoardList(worksheet);

        model.addAttribute("dataL", dataList);
        return "excel";
    }

    @SneakyThrows
    @GetMapping("/excel.download")
    public ResponseEntity<InputStreamResource> downloadExcel(HttpServletResponse response) {

        //Excel Down 시작
        try (Workbook workbook = new XSSFWorkbook()) {
            //시트생성
            Sheet sheet = workbook.createSheet("졸업 대상자 조회");

            //행 번호
            int rowNo = 0;

            /**
             * 엑셀 디자인
             */
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
            headerRow.createCell(1).setCellValue("학생 이름");
            headerRow.createCell(2).setCellValue("교수 이름");
            headerRow.createCell(3).setCellValue("졸업 날짜");
            headerRow.createCell(4).setCellValue("단계");
            headerRow.createCell(5).setCellValue("상태");
            headerRow.createCell(6).setCellValue("기타 자격");
            headerRow.createCell(7).setCellValue("캡스톤 이수");

            for (int i = 0; i <= 7; i++) {
                headerRow.getCell(i).setCellStyle(headStyle);
            }

            /**
             * 엑셀 내 db 데이터 조회
             */
            List<ExcelBoard> dataList = excelBoardService.findExcelList();
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

            /**
             * 엑셀 컬럼 사이즈 설정
             */
            sheet.setColumnWidth(0, 3000);
            sheet.setColumnWidth(1, 3000);
            sheet.setColumnWidth(2, 3000);
            sheet.setColumnWidth(3, 3000);
            sheet.setColumnWidth(4, 3000);
            sheet.setColumnWidth(5, 3000);
            sheet.setColumnWidth(6, 3000);
            sheet.setColumnWidth(7, 3500);

            /**
             * 컨텐츠 타입과 파일명(확장자) 지정
             */
            File tmpFile = File.createTempFile("TMP~", ".xlsx");
            try (OutputStream fos = new FileOutputStream(tmpFile)) {
                workbook.write(fos);
            }
            InputStream res = new FileInputStream(tmpFile) {
                @Override
                public void close() throws IOException {
                    super.close();
                }
            };

            /**
             * file 이름 영어로 설정 필요! ex) attachment;filename=abc.xlsx
             */

            return ResponseEntity.ok() //
                    .contentLength(tmpFile.length()) //
                    .contentType(MediaType.APPLICATION_OCTET_STREAM) //
                    .header("Content-Disposition", "attachment;filename=graduation.xlsx") //
                    .body(new InputStreamResource(res));
        }
    }

    private static List<ExcelBoard> getExcelBoardList(Sheet worksheet) {
        List<ExcelBoard> dataList = new ArrayList<>();
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
            Row row = worksheet.getRow(i);
            ExcelBoard data = ExcelBoard.createExcelBoard(row);
            dataList.add(data);
        }
        return dataList;
    }

    private static Sheet getWorksheet(MultipartFile file, String extension) throws IOException {
        Workbook workbook = getWorkbook(file, extension);
        Sheet worksheet = Objects.requireNonNull(workbook).getSheetAt(0);
        return worksheet;
    }

    private static Workbook getWorkbook(MultipartFile file, String extension) throws IOException {
        Workbook workbook = null;

        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }
        return workbook;
    }

    private static void checkUploadFileExtension(String extension) {
        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            throw new CsPopException(CsPopErrorCode.INVAILD_UPLOAD_FILE_EXTENSION);
        }
    }
}