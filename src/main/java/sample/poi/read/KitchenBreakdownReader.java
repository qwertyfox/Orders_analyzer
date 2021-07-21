package sample.poi.read;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import sample.poi.classification.BreadType;
import sample.poi.print.WriteToDoc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;


public class KitchenBreakdownReader {

    public void analyseDocument () throws NullPointerException {

        System.out.println("KitchenBreakdownReader class called");

        try{
            XWPFDocument document = new XWPFDocument(new FileInputStream(new File("Combined document.docx")));
            Iterator<XWPFTable> tableIterator = document.getTablesIterator();
            String clientName = "";

            while(tableIterator.hasNext()){
                XWPFTable table = tableIterator.next();
                int rowNumbers = table.getNumberOfRows();

                try{
                    String [] rawData = table.getRow(0).getCell(0).getText().split("–");
                    String breadType = rawData [0];
                    String productName = rawData [1];

                    int orderNumbers = Integer.parseInt(table.getRow(0).getCell(1).getText());
                    BreadType.getInstance().addToFinalList(breadType, productName, orderNumbers);

                }catch (NullPointerException | IndexOutOfBoundsException e){
                    clientName = table.getRow(0).getCell(0).getText();
                }

                for(int row = 1; row < rowNumbers-1; row++){
                    String [] rawData = table.getRow(row).getCell(0).getText().split("–");
                    String breadType = rawData [0];
                    String productName = rawData [1];

                    int orderNumbers = Integer.parseInt(table.getRow(row).getCell(1).getText());
                    if (productName.contains("Special")) {
                        String clientAndProduct = productName +" = "+ clientName;
                        BreadType.getInstance().addToFinalList(breadType, clientAndProduct, orderNumbers);
                    }else {
                        BreadType.getInstance().addToFinalList(breadType, productName, orderNumbers);
                    }
                }
            }
        }catch (NullPointerException | IndexOutOfBoundsException | FileNotFoundException e) {
            System.out.println("Error reading file");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        printFinalList();
        printDoc();
    }


    private static void printFinalList() {
        BreadType.getInstance().printFinalList();
    }

    private static void printDoc() {
        try{
            WriteToDoc.getInstance().createTable();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
