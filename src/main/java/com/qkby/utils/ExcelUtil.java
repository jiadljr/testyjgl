package com.qkby.utils;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFDataValidationConstraint;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation;

import com.qkby.exception.BusinessException;

public class ExcelUtil {
    private final static String excel2003L =".xls";    //2003- 版本的excel
    private final static String excel2007U =".xlsx";   //2007+ 版本的excel
    /**
     * Excel导入
     */
    public static  List<List<String>> getBankListByExcel(InputStream in, String fileName) throws Exception{
        List<List<String>> list = null;
        //创建Excel工作薄
        Workbook work = getWorkbook(in,fileName);
        if(null == work){
            throw new BusinessException("","创建Excel工作薄为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;
        list = new ArrayList<List<String>>();
        //遍历Excel中所有的sheet
        sheet = work.getSheetAt(0);
        if (sheet == null) {
        	throw new BusinessException("","Excel工作薄为空！");
		}
        //遍历当前sheet中的所有行
        //包涵头部，所以要小于等于最后一列数,这里也可以在初始值加上头部行数，以便跳过头部
        String cellValue = (String)getCellValue(sheet.getRow(0).getCell(0));
        if (!"资产编码".equals(cellValue.trim())) {
			throw new BusinessException("500", "Excel文件内容不符！请重新选择！");
		}
        List<String> li = null;
        for (int j = sheet.getFirstRowNum()+1; j <= sheet.getLastRowNum(); j++) {
            //读取一行
            row = sheet.getRow(j);
            //判断行是否空
            if (row == null) 
            	continue;
			
            //遍历所有的列
            Cell assetsName = row.getCell(1);
            if(assetsName == null)
            	continue;
            if("".equals(assetsName.toString().trim()))
            	continue;
            li = new ArrayList<String>();
            short firstCellNum = row.getFirstCellNum();
            for (int y = firstCellNum; y < 11; y++) {
            	cell = row.getCell(y);
                if (cell != null){
                	li.add((String)getCellValue(cell));
				}else{
					li.add("");
				}
            }
            list.add(li);
        }
        return list;
    }
    public static boolean isRowEmpty(Row row) {
	   for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
	       Cell cell = row.getCell(c);
	       if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK)
	       return false;
	   }
	   return true;
    }
    /**
     * 描述：根据文件后缀，自适应上传文件的版本
     */
    public static  Workbook getWorkbook(InputStream inStr,String fileName) throws Exception{
        Workbook wb = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if(excel2003L.equals(fileType)){
            wb = new HSSFWorkbook(inStr);  //2003-
        }else if(excel2007U.equals(fileType)){
            wb = new XSSFWorkbook(inStr);  //2007+
        }else{
            throw new Exception("解析的文件格式有误！");
        }
        return wb;
    }
    /**
     * 描述：对表格中数值进行格式化
     */
    public static  String getCellValue(Cell cell){
        String value = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  //日期格式化
        String v = cell.getCellStyle().getDataFormatString();
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                value = cell.getRichStringCellValue().getString();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if("yyyy\\-mm\\-dd".equals(cell.getCellStyle().getDataFormatString())){
                    value = sdf.format(cell.getDateCellValue());
                }else{
                    value = "";
                }
                break;
            /*case Cell.CELL_TYPE_BOOLEAN:
                value = cell.getBooleanCellValue();
                break;*/
            case Cell.CELL_TYPE_BLANK:
                value = "";
                break;
            case Cell.CELL_TYPE_ERROR:
            	value = "";
            	break;
            default:
                break;
        }
        return value;
    }
    /**
     * 获取批量导入的项目Excel
     */
    public static  Map<String, List<List<String>>> getbatchImportProjExcelData(InputStream in, String fileName) throws Exception{
    	List<List<String>> taskList = null;
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;
        Workbook work = getWorkbook(in,fileName);//创建Excel工作薄
        List<String> li = null;
        Map<String, List<List<String>>> projMap = new HashMap<String, List<List<String>>>();
        for (int i = 0; i < work.getNumberOfSheets(); i++) {// 获取每个Sheet表
        	taskList = new ArrayList<List<String>>();
        	sheet = work.getSheetAt(i);
        	String projectName = sheet.getSheetName();
        	for (int j = sheet.getFirstRowNum()+1; j <= sheet.getLastRowNum(); j++) {// getLastRowNum，获取最后一行的行标
                row = sheet.getRow(j);
                if (row == null) 
                	continue;
                li = new ArrayList<String>();
                short firstCellNum = row.getFirstCellNum();
                for (int y = firstCellNum; y < 6; y++) {//遍历所有的列
                	cell = row.getCell(y);
                    if (cell != null){
                    	li.add(getCellValue(cell));
    				}
                }
                taskList.add(li);
            }
        	projMap.put(projectName, taskList);
        }
        return projMap;
    }
    /** 
     * 设置sheet行中的单元格下拉
     */  
    public static void setSheetCellSelect(XSSFSheet sheet)
            throws Exception {
    	String[] datas = new String[] {"是","否"};
        XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper(sheet);
        XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) dvHelper
                .createExplicitListConstraint(datas);
        CellRangeAddressList addressList = null;
        XSSFDataValidation validation = null;
        addressList = new CellRangeAddressList(1, 1, 3, 3);
        validation = (XSSFDataValidation) dvHelper.createValidation(
                dvConstraint, addressList);
        // 07默认setSuppressDropDownArrow(true);
        // validation.setSuppressDropDownArrow(true);
        // validation.setShowErrorBox(true);
        sheet.addValidationData(validation);
    }
  
    /** 
     * 设置单元格上提示 
     * @param sheet  要设置的sheet. 
     * @param promptTitle 标题 
     * @param promptContent 内容 
     * @param firstRow 开始行 
     * @param endRow  结束行 
     * @param firstCol  开始列 
     * @param endCol  结束列 
     * @return 设置好的sheet. 
     */  
    public static HSSFSheet setHSSFPrompt(HSSFSheet sheet, String promptTitle,  
            String promptContent, int firstRow, int endRow ,int firstCol,int endCol) {  
        // 构造constraint对象  
        DVConstraint constraint = DVConstraint.createCustomFormulaConstraint("BB1");  
        // 四个参数分别是：起始行、终止行、起始列、终止列  
        CellRangeAddressList regions = new CellRangeAddressList(firstRow,endRow,firstCol, endCol);  
        // 数据有效性对象  
        HSSFDataValidation data_validation_view = new HSSFDataValidation(regions,constraint);  
        data_validation_view.createPromptBox(promptTitle, promptContent);  
        sheet.addValidationData(data_validation_view);  
        return sheet;  
    }  
}