package edu.jhuapl.nino.generator.java.control;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVFileReader {
   /* public static List<String> readFile(String fileName)throws IOException
    {
        List<String> list = new ArrayList<String>();
        Scanner input = new Scanner(new FileReader(fileName));
        int i=0;
        String line;
        while (input.hasNextLine())
        {
            line=input.nextLine();
            String[] data = line.split(",");
            for(String r: data){
                list.set(i,r);
                i++;
            }
        }
        input.close();
        return list;
    }*/


    public static ArrayList<ArrayList<Object> >readFile1(String fileName)throws IOException
    {
        ArrayList<ArrayList<Object>> list = new ArrayList();
        Scanner input = new Scanner(new FileReader(fileName));
        String line;
        while (input.hasNextLine())
        {
                line = input.nextLine();
                String[] data = line.split(",");
                ArrayList traverse = new ArrayList();
                for (String r : data) { //loop through data, each time gets a value --> push into traverse
                        //might make inner functions based on type (find code for this) --> ask user about types
                        traverse.add(r);
                }

                //push traverse into list
                list.add(traverse);
        }
        input.close();
        return list;
     }


}

