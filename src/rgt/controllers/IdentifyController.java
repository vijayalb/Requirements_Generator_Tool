package rgt.controllers;

import java.io.File;

import rgt.interfaces.ImportInterface;
import rgt.strategies.DocRead;
import rgt.strategies.DocxRead;
import rgt.strategies.TxtRead;

public class IdentifyController {
    ImportInterface txt = new TxtRead();
    ImportInterface docx = new DocxRead();
    ImportInterface doc = new DocRead();
    public String readtxtfile(File f){
        String str = txt.readfile(f);
        return str;
    }
    public String readdocxfile(File f){
        String str = docx.readfile(f);
        return str;
    }
    public String readdocfile(File f){
        String str = doc.readfile(f);
        return str;
    }

}
