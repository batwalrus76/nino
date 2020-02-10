package edu.jhuapl.nino.generator.java.control;

import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.MethodSpec;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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

        CodeBlock testFileCreate = CodeBlock
                .builder()
                .beginControlFlow("if (file.createNewFile())")
                .addStatement("System.out.println(\"File is created!\")")
                .nextControlFlow("else")
                .addStatement("System.out.println(\"File is already existed!\")")
                .endControlFlow()
                .build();

        CodeBlock tryCatchCreate = CodeBlock .builder()
                .beginControlFlow("try")
                .addStatement("String filename = name")
                .addStatement("String workingDirectory = System.getProperty(\"user\\\\src\\\\main\\\\java\")")
                .addStatement("String absoluteFilePath = \"\"")
                .addStatement("absoluteFilePath = workingDirectory + File.separator + filename")
                .addStatement("System.out.println(\"Final file path : \" + absoluteFilePath)")
                .addStatement("File file = new File(absoluteFilePath)")
                .addStatement("file.getParentFile().mkdir()")
                .add(testFileCreate)

                .nextControlFlow("catch ($T e)", IOException.class)
                .addStatement("e.printStackTrace()")
                .endControlFlow()
                .build();

        MethodSpec createFile = MethodSpec
                .methodBuilder("createFile")
                .addModifiers(Modifier.PUBLIC)
                .returns(void.class)
                .addParameter(java.lang.String.class, "name")
                .addCode(tryCatchCreate)
                .build();

        CodeBlock tryCatchWrite = CodeBlock .builder()
                .beginControlFlow("try (FileWriter writer = new FileWriter(\"CSVFileWriter\")")
                .addStatement("")
                .addStatement("")


                .nextControlFlow("catch (IOException e)")
                .addStatement("System.err.format(\"IOException\", e)")
                .endControlFlow()
                .build();

        MethodSpec writer = MethodSpec
                .methodBuilder("createFile")
                .addModifiers(Modifier.PUBLIC)
                .returns(File.class)
                .addParameter(ArrayList.class, "list")
                .addCode(tryCatchWrite)
                .addStatement("return null")
                .build();

    }
}
