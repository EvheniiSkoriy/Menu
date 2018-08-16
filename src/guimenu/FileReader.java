/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guimenu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Evghenii Skoriy
 */
public class FileReader {

    public static List<Food> readFile(String filename) {
        BufferedReader reader = null;
        String line;
        List<Food> list = new ArrayList();
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
            while ((line = reader.readLine()) != null) {
                String[] array = line.split(",");
                Food f = new Food(array[0], array[1], Double.parseDouble(array[2]));
                list.add(f);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
        }
        return list;
    }

}
