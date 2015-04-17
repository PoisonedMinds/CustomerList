package customerlist;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.*;

/**
 * @title CustomerList
 * @author Steven Biro
 * @teacher Mr. J. Carron
 * @date 11-Mar-2015 10:51:46 AM
 * @purpose The purpose of this program is to store information for a company
 */
public class CustomerList {

    public static void main(String[] args) {
        char code;
        String info = "", numofpeople, postalcode;
        int test, num = 0, count = 0;
        ArrayList list, list2 = new ArrayList();//read list and store it in a list array
        list = read("list.secure");//use my fileRead class to read the file
        for (Object list1 : list) {//add all the info to a single varible formated to have lines
            info += (list1 + "\n");
        }
        JOptionPane.showMessageDialog(null, info);//display all the current info
        test = JOptionPane.showConfirmDialog(null, "Do you want to add info to the list of customers?");
        if (test == 0) {
            numofpeople = JOptionPane.showInputDialog("How many people would you like to add to the list?");//ask how many people the user would like to add to the list
            try {
                num = Integer.parseInt(numofpeople);
            } catch (NumberFormatException e) {
                System.out.println("that is not an acceptable number.");
                System.exit(1);
            }
            for (int i = 0; i < num; i++) {
                do {
                    test = 0;
                    info = JOptionPane.showInputDialog("Input the info in this format:\nfirst name,last name,address,city,province,postal code");
                    for (int x = 0; i < info.length(); i++) {
                        if (info.charAt(i)==',') {
                            count++;
                        }
                    }
                   if (count == 5) {

                        postalcode = info.substring(info.length() - 6);
                        for (int n = 0; n < 6; n++) {//check postal code
                            code = postalcode.charAt(n);
                            if (n % 2 == 0) {//make sure even spots are letters and odd are numbers 

                                if (!Character.isLetter(code)) {
                                    test = 1;
                                    System.out.println("please input the postal code in the correct format");
                                    break;
                                }
                            } else {
                                if (!Character.isDigit(code)) {
                                    test = 1;
                                    System.out.println("please input the postal code in the correct format");
                                    break;
                                }
                            }
                        }
                        if (test != 1) {//if the given info passes all tests, then add it to the second list
                            list2.add(i, info);
                        }
                    } else {
                        test = 1;// if the given info is in the incorrect format let the user know
                        System.out.println("please input the info in the correct format next time.");
                    }

                } while (test == 1);//retry if there is a problem with the given info
            }
            add(list2, "list.secure");// use my fileAdd class to add the second list to the end of te file
            list = read("list.secure");//use my fileRead to refresh the contents of the first list
            info = "";
            for (Object list1 : list) {
                info += (list1 + "\n");
            }
            JOptionPane.showMessageDialog(null, info);//output all the info, including the newly added stuff
        } else {
            System.exit(0);
        }

    }

    public static ArrayList read(String path) {
        int num = 0;
        ArrayList contents = new ArrayList();
        String line;
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(path));

            while ((line = br.readLine()) != null) {
                contents.add(num, line);
                num++;
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
    public static void add(ArrayList towrite, String path) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(path,true));
            for (Object towrite1 : towrite) {
                writer.println(towrite1);
            }
            writer.close();
        } catch (IOException ex) {
            System.out.println("An error has occured.");
            System.exit(0);
        }
    }
}

