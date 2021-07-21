package sample.poi.extraction;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class WordMerge {

    private final OutputStream outputStream;
    private final List<InputStream> inputStreamList;
    private XWPFDocument document;

    public WordMerge(OutputStream outputStream) {
        this.outputStream = outputStream;
        inputStreamList = new ArrayList<>();
    }

    public void add(InputStream stream) throws Exception {
        inputStreamList.add(stream);
        OPCPackage srcPackage = OPCPackage.open(stream);
        XWPFDocument src1Document = new XWPFDocument(srcPackage);
        if (inputStreamList.size() == 1) {
            document = src1Document;
        } else {
            CTBody srcBody = src1Document.getDocument().getBody();
            document.getDocument().addNewBody().set(srcBody);
        }
    }

    public void doMerge() throws Exception {
        document.write(outputStream);
    }

    public void close() throws Exception {
        outputStream.flush();
        outputStream.close();
        for (InputStream input : inputStreamList) {
            input.close();
        }
    }

}