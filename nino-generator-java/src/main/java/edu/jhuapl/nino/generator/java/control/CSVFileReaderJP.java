package edu.jhuapl.nino.generator.java.control;

import com.squareup.javapoet.*;

import javax.lang.model.element.Modifier;
import java.io.IOException;

public class CSVFileReaderJP {
    CodeBlock imports = CodeBlock
            .builder()
            .addStatement("import java.io.FileReader")
            .addStatement("import java.io.IOException")
            .addStatement("import java.util.ArrayList")
            .addStatement("import java.util.List")
            .addStatement("import java.util.Scanner")
            .build();

    CodeBlock parse = CodeBlock
            .builder()
            .beginControlFlow("for(String r: data)")
            .addStatement("list.set(i,r)")
            .addStatement("i++")
            .endControlFlow()
            .build();

    CodeBlock csvParser = CodeBlock
            .builder()
            .beginControlFlow("while (input.hasNextLine())")
            .addStatement("line=input.nextLine()")
            .addStatement("String[] data = line.split(\",\")")
            .add(parse)
            .endControlFlow()
            .build();

    MethodSpec readFile = MethodSpec
            .methodBuilder("readFile")
            .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
            .returns(java.util.List.class)
            .addParameter(java.lang.String.class, "fileName")
            .addException(IOException.class)

            .addStatement("List<String> list = new ArrayList<String>()")
            .addStatement("Scanner input = new Scanner(new FileReader(fileName))")
            .addStatement("int i=0")
            .addStatement("String line")
            .addCode(csvParser)
            .addStatement("input.close()")
            .addStatement("return list")
            .build();

    TypeSpec CSVReaderFileFinal = TypeSpec
            .classBuilder("NinoCsvReaderWriter")
            .addModifiers(Modifier.PUBLIC)
            .addMethod(readFile)
            .build();

    JavaFile javaFile = JavaFile
            .builder("edu.jhuapl.nino.csv.example", CSVReaderFileFinal)
            .indent("    ")
            .build();
}
