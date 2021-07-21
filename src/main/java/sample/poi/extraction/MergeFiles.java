package sample.poi.extraction;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

public class MergeFiles {

    public void createFileForAnalysis (List<String> fileNames, String dirLoc) throws Exception {

        FileOutputStream out = new FileOutputStream("Combined document.docx");
        WordMerge wm = new WordMerge(out);

        for (String fileName : fileNames) {
            wm.add(new FileInputStream(dirLoc + fileName));
        }

        wm.doMerge();
        wm.close();

    }
}