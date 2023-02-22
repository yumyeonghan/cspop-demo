package kyonggi.cspop.application.excel;

import kyonggi.cspop.domain.board.ExcelBoard;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 엑셀 다온르드 서비스 구현
 */
public class ExcelServiceImpl extends ExcelService{

    @Autowired
    private ExcelRepository repository;
    @Override
    public void getExcelDown(HttpServletResponse response) {
        List<ExcelBoard> dataList = repository.findAll();
        try{
            //Excel Down 시작
            Workbook workbook = new XSSFWorkbook();

            //시트생성
            Sheet sheet = workbook.createSheet("졸업 대상자 조회");

            //행, 열, 열번호
            Row row;
            Cell cell;
            int rowNo = 0;
            // 테이블 헤더용 스타일
            CellStyle headStyle = workbook.createCellStyle();


            //경계선
            headStyle.setBorderTop(BorderStyle.THIN);
            headStyle.setBorderBottom(BorderStyle.THIN);
            headStyle.setBorderLeft(BorderStyle.THIN);
            headStyle.setBorderRight(BorderStyle.THIN);

            // 배경색
            headStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.YELLOW.getIndex());
            headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            //데이터 정렬
            headStyle.setAlignment(HorizontalAlignment.CENTER);

            //데이터용 경계 스타일 테두리만 지정
            CellStyle bodyStyle = workbook.createCellStyle();
            bodyStyle.setBorderTop(BorderStyle.THIN);
            bodyStyle.setBorderBottom(BorderStyle.THIN);
            bodyStyle.setBorderLeft(BorderStyle.THIN);
            bodyStyle.setBorderRight(BorderStyle.THIN);

            //헤더 생성
            row = sheet.createRow(rowNo++);

            cell = row.createCell(0);
            cell.setCellStyle(headStyle);
            cell.setCellValue("학번");

            cell = row.createCell(1);
            cell.setCellStyle(headStyle);
            cell.setCellValue("이름");

            cell = row.createCell(2);
            cell.setCellStyle(headStyle);
            cell.setCellValue("지도교수");

            cell = row.createCell(3);
            cell.setCellStyle(headStyle);
            cell.setCellValue("졸업 날짜");

            cell = row.createCell(4);
            cell.setCellStyle(headStyle);
            cell.setCellValue("단계");

            cell = row.createCell(5);
            cell.setCellStyle(headStyle);
            cell.setCellValue("상태");

            cell = row.createCell(6);
            cell.setCellStyle(headStyle);
            cell.setCellValue("기타자격");

            cell = row.createCell(7);
            cell.setCellStyle(headStyle);
            cell.setCellValue("캡스톤 이수");

            // Body
            for (int i = 0; i < 8; i++) {
                row = sheet.createRow(i + 1);
                ExcelBoard excelBoard = dataList.get(i);

                cell = row.createCell(0);
                cell.setCellValue(excelBoard.getStudentId());

                cell = row.createCell(1);
                cell.setCellValue(excelBoard.getStudentName());

                cell = row.createCell(2);
                cell.setCellValue(excelBoard.getProfessorName());

                cell = row.createCell(3);
                cell.setCellValue(excelBoard.getGraduationDate());

                cell = row.createCell(4);
                cell.setCellValue(excelBoard.getStep());

                cell = row.createCell(5);
                cell.setCellValue(excelBoard.getState());

                cell = row.createCell(6);
                cell.setCellValue(excelBoard.getOtherQualifications());

                cell = row.createCell(7);
                cell.setCellValue(excelBoard.getCapstoneCompletion());
            }

            // 컨텐츠 타입과 파일명 지정
            response.setContentType("ms-vnd/excel");
            response.setHeader("Content-Disposition", "attachment;filename=test.xlsx");

            //엑셀 출력
            workbook.write(response.getOutputStream());
            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
