package edu.jhuapl.nino.generator.java.control;

import java.io.IOException;
import java.util.ArrayList;

public class CSVDriver {

    public static void main(String[] args) throws IOException {
        System.out.println("hello");
        CSVFileReader example = new CSVFileReader();
        example.readFile1("testReader");
        ArrayList traverse = CSVFileReader.getList();
        for (Object r: traverse){
            System.out.println(r);
        }
    }
}
