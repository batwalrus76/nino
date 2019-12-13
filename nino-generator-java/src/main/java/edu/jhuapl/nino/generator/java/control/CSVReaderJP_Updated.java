package edu.jhuapl.nino.generator.java.control;

import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReaderJP_Updated {
    public static void main(String[] args) {
        CodeBlock imports = CodeBlock
                .builder()
                .addStatement("import java.io.FileReader")
                .addStatement("import java.io.IOException")
                .addStatement("import java.util.ArrayList")
                .addStatement("import java.util.Scanner")
                .build();

        CodeBlock parse = CodeBlock
                .builder()
                .beginControlFlow("for(String r: data)")
                .addStatement("traverse.add(r)")
                .endControlFlow()
                .build();

        CodeBlock csvParser = CodeBlock
                .builder()
                .beginControlFlow("while (input.hasNextLine())")
                .addStatement("line=input.nextLine()")
                .addStatement("String[] data = line.split(\",\")")
                .addStatement("ArrayList traverse = new ArrayList()")
                .add(parse)
                .addStatement("list.add(traverse)")
                .endControlFlow()
                .build();

        MethodSpec readFile = MethodSpec
                .methodBuilder("readFile")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(java.util.List.class)
                .addParameter(java.lang.String.class, "fileName")
                .addException(IOException.class)

                .addStatement("ArrayList<ArrayList<Object>> list = new ArrayList()")
                .addStatement("Scanner input = new Scanner(new FileReader(fileName))")
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

        //make static function "build" which returns java file
        JavaFile javaFile = JavaFile
                .builder("edu.jhuapl.nino.csv.example", CSVReaderFileFinal)
                .indent("    ")
                .build(); //cant change at this point
    }
}
