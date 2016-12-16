package com.leoni.data.manager;

import com.leoni.data.models.*;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: hrmi1005
 * Date: 20.8.2014
 * Time: 8:50
 * To change this template use File | Settings | File Templates.
 */
@Service("excelDocumentManager")
public class ExcelDocumentManagerImpl implements ExcelDocumentManager{

    @Autowired
    @WireVariable
    private ModulsManager modulsManager;

    public Set<String> getSachNrBest(Media media) {
        Set<String> modulsList = new HashSet<String>();
        try {

            //Create Workbook instance holding reference to .xlsx file
            Workbook workbook = WorkbookFactory.create(media.getStreamData());

            //Get first/desired sheet from the workbook
            Sheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();


            while (rowIterator.hasNext())
            {
            Row row = rowIterator.next();
            if(String.valueOf(row.getCell(3))!=null&&(
                    String.valueOf(row.getCell(3)).trim().startsWith("991")||
                    String.valueOf(row.getCell(3)).trim().startsWith("981")||
                    String.valueOf(row.getCell(3)).trim().startsWith("9P"))
                    )
                {modulsList.add(String.valueOf(row.getCell(3)).trim());}
            }

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InvalidFormatException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    return modulsList;
    }

    public String getProdNr(Media media) {
        String prodNr = "";
        try {

            //Create Workbook instance holding reference to .xlsx file
            Workbook workbook = WorkbookFactory.create(media.getStreamData());

            //Get first/desired sheet from the workbook
            Sheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows one by one
            Row row = sheet.getRow(1);
            Cell cell = row.getCell(1);
            prodNr = cell.getStringCellValue().trim();

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InvalidFormatException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return prodNr;
    }

    /*@Override
    public List<VsModulyWrm> getVsModulsFromExcel(Media media) {
        List<VsModulyWrm> vsModulsList = new ArrayList<>();
        try {

            //Create Workbook instance holding reference to .xlsx file
            FileInputStream fis = new FileInputStream("c://vsModuly.xlsx");
            InputStream is = fis;

            Workbook workbook = WorkbookFactory.create(is);

            //Get first/desired sheet from the workbook
            Sheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();

            rowIterator.next();
            List<String> nezalozeneModuly = new ArrayList<>();
            List<String> zalozeneModuly = new ArrayList<>();
            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                Moduls moduls = null;

                //System.out.println(row.getCell(1));
                if(!modulsManager.findBySachNrLieferant(String.valueOf(row.getCell(1))).isEmpty())
                  {moduls = modulsManager.findBySachNrLieferant(String.valueOf(row.getCell(1))).get(0);
                      zalozeneModuly.add(String.valueOf(row.getCell(1)));
                VsModulyWrm vsModul = new VsModulyWrm();
                vsModul.setModuls(moduls);
                vsModul.setVodic(String.valueOf(row.getCell(4)).trim());
                vsModul.setPrierez(Double.valueOf(String.valueOf(row.getCell(5))));
                String popis = String.valueOf(row.getCell(6)).trim();
                vsModul.setPopis(popis);
                String id_vsString="";
                Integer id_vs = null;
                if(popis!=null&&popis.startsWith("Y")){
                    id_vsString="10"+popis.substring(1,4);
                }
                if(popis!=null&&popis.startsWith("YW")){
                    id_vsString="20"+popis.substring(2,5);
                }
                if(popis!=null){id_vs= Integer.valueOf(id_vsString);}
                vsModul.setId_vs(id_vs);
                vsModulsList.add(vsModul);}
                else{nezalozeneModuly.add(String.valueOf(row.getCell(1)));}

            }
            System.out.println("pocet: " + nezalozeneModuly.size() + "nezalozene: " + nezalozeneModuly);
            System.out.println("pocet: " + zalozeneModuly.size() + "zalozene: " + zalozeneModuly);
            fis.close();

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InvalidFormatException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return vsModulsList;
    }    */

    @Override
    public List<VersandModul> getVersandModuls(Media media) {
        List<VersandModul> versandModulList = new ArrayList<>();
        try {

            //FileInputStream fis = new FileInputStream(/*"c://export.xls"*/media.getStreamData());
            InputStream is = media.getStreamData();//fis;
            //Create Workbook instance holding reference to .xlsx file
            Workbook workbook = WorkbookFactory.create(is);

            //Get first/desired sheet from the workbook
            Sheet sheet = workbook.getSheetAt(3);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();

            //rowIterator.next();
            //System.out.print("CISLAAAAAA ");
            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                //System.out.print("CISLAAAAAA "+row.getCell(1));
                if(row.getCell(1)!=null&&row.getCell(2)!=null&&row.getCell(3)!=null&&row.getCell(4)!=null&&
                        (
                        row.getCell(1).getStringCellValue().trim().startsWith("991")||
                        row.getCell(1).getStringCellValue().trim().startsWith("981")||
                        row.getCell(1).getStringCellValue().trim().startsWith("9P")||
                        row.getCell(1).getStringCellValue().trim().startsWith("9A"))
                        &&row.getCell(4).getNumericCellValue()>0
                        )
                {
                System.out.println("toto citam:   " + row.getCell(1).getStringCellValue().trim());
                VersandModul versandModul = new VersandModul();
                versandModul.setSachNrBest(row.getCell(1).getStringCellValue());
                versandModul.setSachNrLieferant(row.getCell(2).getStringCellValue());
                versandModul.setAuftragNr((int) row.getCell(3).getNumericCellValue());
                versandModul.setPieces((int) row.getCell(4).getNumericCellValue());
                versandModul.setCount(0);
                SimpleDateFormat df = new SimpleDateFormat("HH:mm");
                if (row.getCell(6)!=null&&isInteger(row.getCell(6))) {versandModul.setTime(df.format(row.getCell(6).getDateCellValue()));}
                else {versandModul.setTime(row.getCell(6).getStringCellValue());}
                if (isInteger(row.getCell(7))) {versandModul.setLadungPcs(String.valueOf(row.getCell(7).getNumericCellValue()));}
                else {versandModul.setLadungPcs(row.getCell(7).getStringCellValue());}
                versandModul.setLadungName(row.getCell(8).getStringCellValue());
                if (isInteger(row.getCell(9))) {versandModul.setPalettePcs(String.valueOf(row.getCell(9).getNumericCellValue()));}
                else {versandModul.setPalettePcs(row.getCell(9).getStringCellValue());}
                versandModul.setPaletteName(row.getCell(10).getStringCellValue());
                if (isInteger(row.getCell(11))) {versandModul.setDeckelPcs(String.valueOf(row.getCell(11).getNumericCellValue()));}
                else {versandModul.setDeckelPcs(row.getCell(11).getStringCellValue());}
                versandModul.setDeckelName(row.getCell(12).getStringCellValue());
                versandModul.setMj((int) row.getCell(13).getNumericCellValue());
                versandModul.setStatus(10);
                versandModul.setScan(true);
                versandModulList.add(versandModul);
                }

            }


        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InvalidFormatException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return versandModulList;
    }

    @Override
    public File printVersandExport(VersandExport versandExport) {
        File file = new File("export-result.xls");
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Export");
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        //cellStyle.setFont(font);
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle.setWrapText(true);
        cellStyle.setBorderBottom(CellStyle.BORDER_MEDIUM);
        cellStyle.setBorderLeft(CellStyle.BORDER_MEDIUM);
        cellStyle.setBorderRight(CellStyle.BORDER_MEDIUM);
        cellStyle.setBorderTop(CellStyle.BORDER_MEDIUM);
        HSSFCellStyle cellStyleData = workbook.createCellStyle();
        cellStyleData.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyleData.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyleData.setBorderRight(CellStyle.BORDER_THIN);
        cellStyleData.setBorderTop(CellStyle.BORDER_THIN);
        sheet.setColumnWidth(0,1000);
        sheet.setColumnWidth(1,4000);
        sheet.setColumnWidth(2,4000);
        sheet.setColumnWidth(3,4000);
        sheet.setColumnWidth(4,2000);
        sheet.setColumnWidth(5,2000);
        sheet.setColumnWidth(6,3000);
        sheet.setColumnWidth(7,2000);
        sheet.setColumnWidth(8,4000);
        sheet.setColumnWidth(9,2000);
        sheet.setColumnWidth(10,4000);
        sheet.setColumnWidth(11,2000);
        sheet.setColumnWidth(12,4000);
        sheet.setColumnWidth(13,2000);
        Row row0 = sheet.createRow(0);
        row0.setHeight((short) 500.0);
        Cell cell = row0.createCell(0);
        cell.setCellValue("PAG");
        Row row1 = sheet.createRow(1);
        row1.setHeight((short) 500.0);
        Cell cell0 = row1.createCell(0);
        cell0.setCellValue("9X1");
        cell0.setCellStyle(cellStyle);
        Cell cell1 = row1.createCell(1);
        cell1.setCellValue("Sach. nr.");
        cell1.setCellStyle(cellStyle);
        Cell cell2 = row1.createCell(2);
        cell2.setCellValue("Teilnr.-Index");
        cell2.setCellStyle(cellStyle);
        Cell cell3 = row1.createCell(3);
        cell3.setCellValue("Auftr. nr.");
        cell3.setCellStyle(cellStyle);
        Cell cell4 = row1.createCell(4);
        cell4.setCellValue("Pcs");
        cell4.setCellStyle(cellStyle);
        Cell cell5 = row1.createCell(5);
        cell5.setCellValue("Send. nr.");
        cell5.setCellStyle(cellStyle);
        Cell cell6 = row1.createCell(6);
        cell6.setCellValue("Zeit");
        cell6.setCellStyle(cellStyle);
        Cell cell7 = row1.createCell(7);
        cell7.setCellValue("Pcs");
        cell7.setCellStyle(cellStyle);
        Cell cell8 = row1.createCell(8);
        cell8.setCellValue("Ladung");
        cell8.setCellStyle(cellStyle);
        Cell cell9 = row1.createCell(9);
        cell9.setCellValue("Pcs");
        cell9.setCellStyle(cellStyle);
        Cell cell10 = row1.createCell(10);
        cell10.setCellValue("Palette");
        cell10.setCellStyle(cellStyle);
        Cell cell11 = row1.createCell(11);
        cell11.setCellValue("Pcs");
        cell11.setCellStyle(cellStyle);
        Cell cell12 = row1.createCell(12);
        cell12.setCellValue("Deckel");
        cell12.setCellStyle(cellStyle);
        Cell cell13 = row1.createCell(13);
        cell13.setCellValue("MJ");
        cell13.setCellStyle(cellStyle);

        /*row1.createCell(1).setCellValue("Sachnr");
        row1.createCell(2).setCellValue("Teilnr.-Index");
        row1.createCell(3).setCellValue("Auftr. nr.");
        row1.createCell(4).setCellValue("Pcs");
        row1.createCell(5).setCellValue("Sendnr.");
        row1.createCell(6).setCellValue("Zeit");
        row1.createCell(7).setCellValue("Pcs");
        row1.createCell(8).setCellValue("Ladung");
        row1.createCell(9).setCellValue("Pcs");
        row1.createCell(10).setCellValue("Palette");
        row1.createCell(11).setCellValue("Pcs");
        row1.createCell(12).setCellValue("Deckel");
        row1.createCell(13).setCellValue("MJ");*/

        List<VersandModul> versandModulList = versandExport.getModulsList();

        for (int i = 0;i<versandModulList.size();i++){
            Row row = sheet.createRow(i+2);
            row.setHeight((short) 500.0);

            Cell cellData0 = row.createCell(0);
            cellData0.setCellValue("");
            cellData0.setCellStyle(cellStyleData);
            Cell cellData1 = row.createCell(1);
            cellData1.setCellValue(versandModulList.get(i).getSachNrBest());
            cellData1.setCellStyle(cellStyleData);
            Cell cellData2 = row.createCell(2);
            cellData2.setCellValue(versandModulList.get(i).getSachNrLieferant());
            cellData2.setCellStyle(cellStyleData);
            Cell cellData3 = row.createCell(3);
            cellData3.setCellValue(versandModulList.get(i).getAuftragNr());
            cellData3.setCellStyle(cellStyleData);
            Cell cellData4 = row.createCell(4);
            cellData4.setCellValue(versandModulList.get(i).getPieces());
            cellData4.setCellStyle(cellStyleData);
            Cell cellData5 = row.createCell(5);
            cellData5.setCellValue("");
            cellData5.setCellStyle(cellStyleData);
            Cell cellData6 = row.createCell(6);
            cellData6.setCellValue(versandModulList.get(i).getTime());
            cellData6.setCellStyle(cellStyleData);
            Cell cellData7 = row.createCell(7);
            cellData7.setCellValue(versandModulList.get(i).getLadungPcs());
            cellData7.setCellStyle(cellStyleData);
            Cell cellData8 = row.createCell(8);
            cellData8.setCellValue(versandModulList.get(i).getLadungName());
            cellData8.setCellStyle(cellStyleData);
            Cell cellData9 = row.createCell(9);
            cellData9.setCellValue(versandModulList.get(i).getPalettePcs());
            cellData9.setCellStyle(cellStyleData);
            Cell cellData10 = row.createCell(10);
            cellData10.setCellValue(versandModulList.get(i).getPaletteName());
            cellData10.setCellStyle(cellStyleData);
            Cell cellData11 = row.createCell(11);
            cellData11.setCellValue(versandModulList.get(i).getDeckelPcs());
            cellData11.setCellStyle(cellStyleData);
            Cell cellData12 = row.createCell(12);
            cellData12.setCellValue(versandModulList.get(i).getDeckelName());
            cellData12.setCellStyle(cellStyleData);
            Cell cellData13 = row.createCell(13);
            cellData13.setCellValue(versandModulList.get(i).getMj());
            cellData13.setCellStyle(cellStyleData);


            /*row.createCell(0).setCellValue("");
            row.createCell(1).setCellValue(versandModulList.get(i).getSachNrBest());
            row.createCell(2).setCellValue(versandModulList.get(i).getSachNrLieferant());
            row.createCell(3).setCellValue(versandModulList.get(i).getAuftragNr());
            row.createCell(4).setCellValue(versandModulList.get(i).getPieces());
            row.createCell(5).setCellValue("");
            row.createCell(6).setCellValue(versandModulList.get(i).getTime());
            row.createCell(7).setCellValue(versandModulList.get(i).getLadungPcs());
            row.createCell(8).setCellValue(versandModulList.get(i).getLadungName());
            row.createCell(9).setCellValue(versandModulList.get(i).getPalettePcs());
            row.createCell(10).setCellValue(versandModulList.get(i).getPaletteName());
            row.createCell(11).setCellValue(versandModulList.get(i).getDeckelPcs());
            row.createCell(12).setCellValue(versandModulList.get(i).getDeckelName());
            row.createCell(13).setCellValue(versandModulList.get(i).getMj());*/
        }

        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(file);
            workbook.write(fileOut);
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return file;

    }

    public Set<String> getAllColorsFromExcel(){
        Set<String> colorNames = new HashSet<>();


        FileInputStream fis = null;
        try {
            fis = new FileInputStream("c://letovanie.xlsx");
            InputStream is = fis;
            //Create Workbook instance holding reference to .xlsx file
            Workbook workbook = WorkbookFactory.create(is);


        //Get first/desired sheet from the workbook
            Sheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();

            rowIterator.next();
            //System.out.print("CISLAAAAAA ");
            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                String colorName = row.getCell(7).getStringCellValue().trim();
                if(!colorNames.contains(colorName)){colorNames.add(colorName);}
            }
            System.out.println("AAAAAAAAABBBBBB "+colorNames);

        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InvalidFormatException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return colorNames;
    }

    @Override
    public Map<String, String> getAllVsFromExcel() {
        Map<String,String> colorNames = new HashMap<>();


        FileInputStream fis = null;
        try {
            fis = new FileInputStream("c://letovanie.xlsx");
            InputStream is = fis;
            //Create Workbook instance holding reference to .xlsx file
            Workbook workbook = WorkbookFactory.create(is);


            //Get first/desired sheet from the workbook
            Sheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();

            rowIterator.next();
            //System.out.print("CISLAAAAAA ");
            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                String vsName = row.getCell(6).getStringCellValue().trim();
                if(!colorNames.containsKey(vsName)){
                    String vsColor = row.getCell(7).getStringCellValue().trim();
                    colorNames.put(vsName,vsColor);}
            }
            System.out.println("AAAAAAAAABBBBBB "+colorNames);

        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InvalidFormatException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return colorNames;
    }

    /*@Override
    public List<VsModulyWrmNew> getAllVsModulsFromExcel() {
        List<VsModulyWrmNew> vsModuls = new ArrayList<>();


        FileInputStream fis = null;
        try {
            fis = new FileInputStream("c://letovanie.xlsx");
            InputStream is = fis;
            //Create Workbook instance holding reference to .xlsx file
            Workbook workbook = WorkbookFactory.create(is);


            //Get first/desired sheet from the workbook
            Sheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();

            rowIterator.next();
            //System.out.print("CISLAAAAAA ");
            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                VsModulyWrmNew vsModulyWrmNew = new VsModulyWrmNew();
                vsModulyWrmNew.setPopisTemp(row.getCell(6).getStringCellValue());
                vsModulyWrmNew.setPrierez(row.getCell(5).getNumericCellValue());
                Double vodic = row.getCell(4).getNumericCellValue();
                Integer vodicInt = vodic.intValue();
                vsModulyWrmNew.setVodic(String.valueOf(vodicInt));
                vsModulyWrmNew.setSachNrLief(row.getCell(1).getStringCellValue());
                vsModuls.add(vsModulyWrmNew);
            }
            //System.out.println("AAAAAAAAABBBBBB "+vsModuls);

        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InvalidFormatException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return vsModuls;
    } */

    @Override
    public List<ModulClipListWrm> getModulsClipFromExcel(Media media) {
        List<ModulClipListWrm> modulClipList = new ArrayList<>();
        try {

            //Create Workbook instance holding reference to .xlsx file
            FileInputStream fis = new FileInputStream("c://klipy.xlsx");
            InputStream is = fis;

            Workbook workbook = WorkbookFactory.create(is);

            //Get first/desired sheet from the workbook
            Sheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();

            rowIterator.next();
            rowIterator.next();
            rowIterator.next();
            rowIterator.next();
            rowIterator.next();
            rowIterator.next();
            List<String> nezalozeneModuly = new ArrayList<>();
            List<String> zalozeneModuly = new ArrayList<>();
            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                if(row.getCell(1)!=null&&!row.getCell(1).toString().trim().equals("")){
                Moduls moduls = null;

                //System.out.println(row.getCell(1));
                if(!modulsManager.findBySachNrLieferant(String.valueOf(row.getCell(1))).isEmpty())
                  {moduls = modulsManager.findBySachNrLieferant(String.valueOf(row.getCell(1))).get(0);
                      zalozeneModuly.add(String.valueOf(row.getCell(1)));
                ModulClipListWrm modulClipListWrm = new ModulClipListWrm();
                modulClipListWrm.setModuls(moduls);
                modulClipListWrm.setChangedBy("miso");
                modulClipListWrm.setChangedDate(new Date());
                modulClipListWrm.setIsBrett(true);
                modulClipListWrm.setNote("F99FLL");
                String codeClip = String.valueOf(row.getCell(2)).substring(3);
                System.out.println("substring "+codeClip);
                modulClipListWrm.setCodeClip(codeClip);

                modulClipList.add(modulClipListWrm);}
                else{nezalozeneModuly.add(String.valueOf(row.getCell(1)));}
            }

            }
            System.out.println("pocet: " + nezalozeneModuly.size() + "nezalozene: " + nezalozeneModuly);
            System.out.println("pocet: " + zalozeneModuly.size() + "zalozene: " + zalozeneModuly);
            fis.close();

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InvalidFormatException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return modulClipList;
    }

    public static boolean isInteger(Cell cell) {
        try {
            cell.getNumericCellValue();
        } catch(IllegalStateException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
}
