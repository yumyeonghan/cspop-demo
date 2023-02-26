package kyonggi.cspop.application.excel;

import kyonggi.cspop.domain.board.ExcelBoard;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
@Transactional
public class ExcelController{

    @Autowired
    private ExcelRepository repository;

    //엑셀 업로드 화면(임시)
    /**
     * @param model
     * @return
     */
    @GetMapping("/excel")
    public String excel(Model model) {
        List<ExcelBoard> dataList = repository.findAll();

        /**
         * 업로드 전 기존 데이터 출력
         */
        for (ExcelBoard excelBoard : dataList) {
            excelBoard.getId();
            excelBoard.getStudentId();
            excelBoard.getStudentName();
            excelBoard.getProfessorName();
            excelBoard.getStep();
            excelBoard.getState();
            excelBoard.getOtherQualifications();
            excelBoard.getCapstoneCompletion();
        }

        model.addAttribute("dataL", dataList);
        return "excel";
    }

    /**
     * Excel file upload method
     * @param file
     * @param model
     * @return
     * @throws IOException
     */
    @PostMapping("/excel.read")
    public String upload(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        //저장된 속성 값 미리 삭제
        repository.deleteAllInBatch();

        List<ExcelBoard> dataList = new ArrayList<>();

        //파일 확장자명->엑셀 파일 확장자인지 확인
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        //그외 확장자는 예외 던짐
        /**
         * 확장자에 맞지 않는 파일 + null값 입력 시 오류 페이지 이동
         */
        try {
            if (!extension.equals("xlsx") && !extension.equals("xls")) {
                throw new IOException("엑셀파일만 업로드 해주세요.");
            }
        }catch (IOException e) {
            System.out.println(e.getClass().getName());
        }
        finally {

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
            //db 레포에 dataList 값 저장
            model.addAttribute("dataL", dataList);
            repository.saveAll(dataList);
        }
        return "excel";
    }

    /**
     * Excel file download method
     * @param response
     * @return
     */
    @SneakyThrows
    @GetMapping( "/excel.download")
    public ResponseEntity<InputStreamResource> downloadExcel(HttpServletResponse response) {

        //Excel Down 시작
        try (Workbook workbook = new XSSFWorkbook()){
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
            List<ExcelBoard> dataList = repository.findAll();
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
}