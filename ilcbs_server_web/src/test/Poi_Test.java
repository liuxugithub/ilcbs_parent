/*
import godday.xin.utils.DownloadUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Poi_Test {
    @Test
    public void Poi_Test() throws IOException {
        Workbook book=new HSSFWorkbook();
        Sheet sheet = book.createSheet();
        Row row=sheet.createRow(0);
        Cell cell=row.createCell(1);

        cell.setCellValue("20191209出货表");
        CellStyle cellStyle=book.createCellStyle();
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        Font font =  book.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 16);
        cellStyle.setFont(font );

        font.setFontName("微软雅黑");
        cell.setCellStyle(cellStyle);

        sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, 8));
        System.out.println(this.getClass().getClassLoader().getResource(""));
        System.out.println(this.getClass().getResource("").getPath());
        System.out.println(System.getProperty("user.dir") );
        book.write(new FileOutputStream(new File(this.getClass().getResource("").getPath()+"123.xls")));




    }
}
*/
