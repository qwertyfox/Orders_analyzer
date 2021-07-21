package sample.poi.print;


import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import sample.poi.classification.BreadType;
import sample.poi.extraction.ReadDirectoryFiles;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class WriteToDoc {

    private static final WriteToDoc writeToDoc = new WriteToDoc();
    private static final String currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
    private static final String timeForNamingDocs = currentTime.replace(":","-");

    public static WriteToDoc getInstance() {
        return writeToDoc;
    }

    public void createTable() {

        String finalDoc = "Final analysis "+ timeForNamingDocs;

        Map<String, TreeMap<String, Integer>> finalMap = BreadType.getInstance().getFinalMapOfProducts();
        XWPFDocument document = new XWPFDocument();

        try(FileOutputStream out = new FileOutputStream(new File(finalDoc+".docx"))) {

            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun xwpfRun = paragraph.createRun();
            paragraph.setAlignment(ParagraphAlignment.CENTER);
            xwpfRun.setBold(true);
            xwpfRun.setText("Analysed on: " + LocalDate.now()+ " at: "+ currentTime);

            for(String breadType : finalMap.keySet()){
                int breadTypeTotalOrders = 0;

                //Bread type as a paragraph
                paragraph = document.createParagraph();
                paragraph.setSpacingAfter(1);
                xwpfRun = paragraph.createRun();
                xwpfRun.setBold(true);
                xwpfRun.setText(breadType);

                XWPFTable table = document.createTable();
                table.setWidth(9000);

                Map<String, Integer> orderList = finalMap.get(breadType);

                for(String s : orderList.keySet()){
                    XWPFTableRow tableRow = table.createRow();

                    if(s.contains("Special")){
                        String replacedName1 = s.replace("Special","for ");
                        String replacedName2 = replacedName1.replace(" = ","");

                        tableRow.getCell(0).setText(breadType+" - "+replacedName2);
                    }else{
                        tableRow.getCell(0).setText(breadType+" - "+ s);
                    }

                    int orderNumber = orderList.get(s);
                    tableRow.addNewTableCell().setText(String.valueOf(orderNumber));
                    breadTypeTotalOrders += orderNumber;

                    setRowHeight(tableRow);
                    centerCell1Text(tableRow);
                }

                // blank row
                XWPFTableRow blankRow = table.createRow();
                blankRow.createCell();
                blankRow.getCell(0).setText("----------------------------------");
                blankRow.getCell(1).setText("---");
                centerCell1Text(blankRow);
                setRowHeight(blankRow);

                //total row
                XWPFTableRow totalRow = table.createRow();

                xwpfRun = totalRow.getCell(0).addParagraph().createRun();
                xwpfRun.setText("Total");

                paragraph = document.createParagraph();
                paragraph.setAlignment(ParagraphAlignment.CENTER);


                totalRow.addNewTableCell();
                xwpfRun = totalRow.getCell(1).addParagraph().createRun();
                xwpfRun.setText(""+breadTypeTotalOrders);

                table.removeRow(0);


                //setting width of the table
                totalRow.getCell(0).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(6000));
                totalRow.getCell(1).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(1000));


                centerCell1Text(totalRow); // Custom Method
                setRowHeight(totalRow); // Custom Method


                //space after the table
                paragraph = document.createParagraph();
                xwpfRun = paragraph.createRun();
                xwpfRun.setText("\n");
            }

            // To bold the row containing "Total"
            for(XWPFTable table : document.getTables()){
                for(XWPFTableRow row : table.getRows()){
                    for(XWPFTableCell cell : row.getTableCells()){
                        for(XWPFParagraph paragraph2 : cell.getParagraphs()){
                            for(XWPFRun run : paragraph2.getRuns()){
                                String check = run.getText(0);
                                if(check.equals("Total")){
                                    run.setBold(true);
                                    run = cell.getParagraphs().get(1).getRuns().get(0);
                                    run.setBold(true);
                                }
                            }
                        }
                    }
                }
            }

            document.write(out);

            // clearing lists
            ReadDirectoryFiles readDirectoryFiles = new ReadDirectoryFiles();
            readDirectoryFiles.clearList();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to center the order numbers in final document
    private void centerCell1Text(XWPFTableRow tableRow) {
        CTP ctp = tableRow.getCell(1).getCTTc().getPList().get(0);
        CTPPr ctppr = ctp.getPPr();
        if (ctppr == null) {
            ctppr = ctp.addNewPPr();
        }
        CTJc ctjc = ctppr.getJc();
        if (ctjc == null) {
            ctjc = ctppr.addNewJc();
        }
        ctjc.setVal(STJc.CENTER);
    }

    private void setRowHeight (XWPFTableRow tableRow){
        tableRow.setHeight(12*20);
        tableRow.getCtRow().getTrPr().getTrHeightArray(0).setHRule(STHeightRule.EXACT);
    }


    // Method to write the document containing client's names
    public void printAnalysedClients(List<String> clients) {

        String doc = "Analysed clients " +timeForNamingDocs;

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(doc+".doc"))){
            bufferedWriter.write("Analysed on: "+LocalDate.now()+ " at: " +currentTime);
            bufferedWriter.newLine();

            int count = 1;
            for(String client : clients){
                bufferedWriter.write("\t"+count+". "+client);
                count++;
                bufferedWriter.newLine();
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }


}
