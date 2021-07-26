package sample.poi.extraction;

import sample.poi.print.WriteToDoc;
import sample.poi.read.ClientOrderReader;
import sample.poi.read.KitchenBreakdownReader;

import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

public class ReadDirectoryFiles {

    private static final List<String> fileNames = new LinkedList<>();

    public void readDirectoryFiles(String dirLoc, String dirContents) {
        Path dirPath = FileSystems.getDefault().getPath(dirLoc);

        try (DirectoryStream<Path> contents = Files.newDirectoryStream(dirPath, "*.docx")) {

            // Adding content names with extension ".docx" in the fileNames
            for (Path file : contents) {
                fileNames.add(file.getFileName().toString());
            }
            MergeFiles mergeFiles = new MergeFiles();
            mergeFiles.createFileForAnalysis(fileNames, dirLoc);

            // Print documents inside directory
            WriteToDoc.getInstance().printAnalysedClients(fileNames);

            if(dirContents.equals("Kitchen")) {
                KitchenBreakdownReader reader = new KitchenBreakdownReader();
                reader.analyseDocument();
            }else if(dirContents.equals("Client")){
                ClientOrderReader reader = new ClientOrderReader();
                reader.analyseDocument();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void clearList () {
        fileNames.clear();
    }
}
