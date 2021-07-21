module TSG.Document.Analysis {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires org.apache.commons.collections4;
//    requires poi.ooxml.schemas;
    requires poi.ooxml;
    requires poi.ooxml.schemas;
    requires poi;


    opens sample.poi.print;
    opens sample.poi.classification;
    opens sample.poi.extraction;
    opens sample.poi.read;
    opens sample.controller;



    exports sample;
}