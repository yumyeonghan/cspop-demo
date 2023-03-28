package kyonggi.cspop.application.controller.board.excel.graduate;

import kyonggi.cspop.domain.board.ExcelBoard;
import kyonggi.cspop.domain.board.dto.ExcelBoardResponseDto;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//졸업자 조회 리스트 게시판 컨트롤러
@Controller
@RequiredArgsConstructor
@RequestMapping("api/graduation")
public class GraduateCheckController {

    private final ExcelBoardService excelBoardService;


    @GetMapping("/graduate_management")
    public String graduateForm(Pageable pageable, Model model) {
        Page<ExcelBoardResponseDto> allExcelBoard = excelBoardService.findAllExcelBoard(pageable);

        int pageNumber = allExcelBoard.getPageable().getPageNumber();
        int totalPages = allExcelBoard.getTotalPages();
        int pageBlock = 10;
        int startBlockPage = ((pageNumber) / pageBlock) * pageBlock + 1;
        int endBlockPage = startBlockPage + pageBlock - 1;
        endBlockPage = totalPages < endBlockPage ? totalPages : endBlockPage;

        model.addAttribute("startBlockPage", startBlockPage);
        model.addAttribute("endBlockPage", endBlockPage);
        model.addAttribute("graduator", allExcelBoard);
        return "graduation/graduateManagement/graduation_list";
    }

    @PostMapping("/graduate_management.read")
    public String upload(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        //액셀 파일인지 검사
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        checkUploadFileExtension(extension);
        //업로드 된 Excel 파일의 데이터를 ExcelBoard 객체 리스트 형태로 저장 (액셀 파일의 문자만 받고, 숫자는 못받는 버그 수정해야함)
        Sheet worksheet = getWorksheet(file, extension);
        List<ExcelBoard> graduationList = getExcelBoardList(worksheet);

        excelBoardService.deleteExcelListAndUploadExcelList(graduationList);

        model.addAttribute("graduator", graduationList);
        return "redirect:./graduate_management?page=0&size=10";
    }

    @SneakyThrows
    @GetMapping("/graduate_management.download")
    public ResponseEntity<InputStreamResource> downloadExcel(HttpServletResponse response) {

        File tmpFile = getTmpFile();
        InputStream excelFile = getExcelFile(tmpFile);

        return ResponseEntity.ok() //
                .contentLength(tmpFile.length()) //
                .contentType(MediaType.APPLICATION_OCTET_STREAM) //
                .header("Content-Disposition", "attachment;filename=graduation.xlsx") //
                .body(new InputStreamResource(excelFile));
    }

    /**
     * 프론트 작업자는 이 밑으로 로직 안봐도 됩니다.
     * 위의 public 접근 제어자 메서드만 확인해 주세요.
     */

    private File getTmpFile() throws IOException {
        Workbook workbook = new XSSFWorkbook();
        File tmpFile = getFile(workbook);
        OutputStream fos = new FileOutputStream(tmpFile);
        workbook.write(fos);
        return tmpFile;
    }

    private static InputStream getExcelFile(File tmpFile) throws FileNotFoundException {
        InputStream res = new FileInputStream(tmpFile) {
            @Override
            public void close() throws IOException {
                super.close();
            }
        };
        return res;
    }

    private File getFile(Workbook workbook) throws IOException {
        Sheet sheet = workbook.createSheet("졸업 대상자 조회");
        int rowNo = 0;
        CellStyle headStyle = getHeadStyle(workbook);
        rowNo = createHeader(sheet, rowNo, headStyle);
        createBody(sheet, rowNo);
        setColumnSize(sheet);
        File tmpFile = File.createTempFile("TMP~", ".xlsx");
        return tmpFile;
    }

    private static void setColumnSize(Sheet sheet) {
        sheet.setColumnWidth(0, 3000);
        sheet.setColumnWidth(1, 3000);
        sheet.setColumnWidth(2, 3000);
        sheet.setColumnWidth(3, 3000);
        sheet.setColumnWidth(4, 3000);
        sheet.setColumnWidth(5, 3000);
        sheet.setColumnWidth(6, 3000);
        sheet.setColumnWidth(7, 3500);
    }

    private void createBody(Sheet sheet, int rowNo) {
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
    }

    private static int createHeader(Sheet sheet, int rowNo, CellStyle headStyle) {
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
        return rowNo;
    }

    private static CellStyle getHeadStyle(Workbook workbook) {
        CellStyle headStyle = workbook.createCellStyle();
        headStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.LIGHT_ORANGE.getIndex());
        headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font font = workbook.createFont();
        font.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
        font.setFontHeightInPoints((short) 13);
        headStyle.setFont(font);
        return headStyle;
    }

    private static List<ExcelBoard> getExcelBoardList(Sheet worksheet) {
        List<ExcelBoard> graduationList = new ArrayList<>();
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
            Row row = worksheet.getRow(i);
            ExcelBoard data = ExcelBoard.createExcelBoard(row);
            graduationList.add(data);
        }
        return graduationList;
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

        if (extension.equals("")) {
            throw new CsPopException(CsPopErrorCode.NO_UPLOAD_FILE_EXTENSION);
        }
        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            throw new CsPopException(CsPopErrorCode.INVALID_UPLOAD_FILE_EXTENSION);
        }
    }
}