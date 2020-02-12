package edu.jhuapl.nino.generator.java.control;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class CSVFileWriter {

   public void createFile(String name){
       try {

           String filename = name;
           //String workingDirectory = System.getProperty("user\\src\\main\\java");

           String absoluteFilePath = "/Users/ricemh1/Desktop";
           //absoluteFilePath = workingDirectory + File.separator + filename;

           System.out.println("Final file path : " + absoluteFilePath);

           File file = new File(absoluteFilePath);
           boolean mkdir = file.getParentFile().mkdir();

           if (file.createNewFile()) {
               System.out.println("File is created!");
           } else {
               System.out.println("File is already existed!");
           }

       } catch (IOException e) {
           e.printStackTrace();
       }
   }

   public File writer (ArrayList list) throws IOException{

       // If the file doesn't exists, create and write to it
       // If the file exists, truncate (remove all content) and write to it
       try (FileWriter writer = new FileWriter("CSVFileWriter");

            BufferedWriter bw = new BufferedWriter(writer)) {
            bw.write(String.valueOf(list));

       } catch (IOException e) {
           System.err.format("IOException", e);
       }

       return null;
   }

}
