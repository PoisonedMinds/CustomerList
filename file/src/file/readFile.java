/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Steven Biro
 */
public class readFile {
        ArrayList readFile (String path) {
        int num = 0;
        ArrayList contents = new ArrayList();
        String line;
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(path));

            while ((line = br.readLine()) != null) {
                contents.set(num, line);
                
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
            System.exit(0);

        } catch (IOException ex) {
            System.out.println("An error has occured");
            System.exit(1);
        }
            return contents;
    }
}
