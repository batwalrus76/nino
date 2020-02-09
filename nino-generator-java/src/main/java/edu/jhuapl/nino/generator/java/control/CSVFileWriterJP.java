package edu.jhuapl.nino.generator.java.control;

import com.squareup.javapoet.CodeBlock;

public class CSVFileWriterJP {
    public static void main(String[] args) {
        CodeBlock imports = CodeBlock
                .builder()
                .addStatement("import java.io.FileReader")
                .addStatement("import java.io.IOException")
                .addStatement("import java.util.ArrayList")
                .addStatement("import java.io.BufferedWriter")
                .addStatement("import java.io.FileWriter;")
                .build();
    }
}
