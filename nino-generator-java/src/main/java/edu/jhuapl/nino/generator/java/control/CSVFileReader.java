package edu.jhuapl.nino.generator.java.control;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVFileReader {
    public static List<String> readFile(String fileName)throws IOException
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
    }


    /**
     *
     *
     * Below: attempt to implement arraylists
     *
    public static ArrayList<ArrayList<Object> >readFile1(String fileName)throws IOException
    {
        ArrayList<ArrayList<Object>> list = new ArrayList();
        Scanner input = new Scanner(new FileReader(fileName));
        int i=0;
        int l = 0;
        String line;
        while (input.hasNextLine())
        {
            if (l==0){


            }
            else {
                line = input.nextLine();
                String[] data = line.split(",");
                for (ArrayList<Object> m:list) {
                    for (Object r : list.get(m)) {
                        list.set(i, r);
                        i++;
                    }
                }
            }
        }
        input.close();
        return list;
     }
     **/

}

