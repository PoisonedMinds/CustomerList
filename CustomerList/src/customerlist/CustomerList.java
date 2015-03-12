package customerlist;

import java.util.ArrayList;
import javax.swing.*;
import file.*;
import org.apache.commons.lang3.StringUtils;

/**
 * @title CustomerList
 * @author Steven Biro
 * @teacher Mr. J. Carron
 * @date 11-Mar-2015 10:51:46 AM
 * @purpose The purpose of this program is to
 */
public class CustomerList {

    public static void main(String[] args) {
        char code;
        String info = "", numofpeople, postalcode;
        int test, num = 0, count,x=0;
        ArrayList list, list2 = new ArrayList();
        list = fileRead.read("list.secure");
        for (Object list1 : list) {
            info += (list1 + "\n");
        }
        JOptionPane.showMessageDialog(null, info);
        test = JOptionPane.showConfirmDialog(null, "Do you want to add info to the list of customers?");
        if (test == 0) {
            numofpeople = JOptionPane.showInputDialog("How many people would you like to add to the list?");
            try {
                num = Integer.parseInt(numofpeople);
            } catch (NumberFormatException e) {
                System.out.println("that is not an acceptable number.");
                System.exit(1);
            }
            for (int i = 0; i < num; i++) {
                do {
                    info = JOptionPane.showInputDialog("Input the info in this format:\nfirst name,last name,address,city,province,postal code");
                    count = StringUtils.countMatches(info, ",");
                    if (info == null) {
                        test = 1;
                        System.out.println("please input information next time.");
                        break;
                    } else if (count == 5) {

                        postalcode = info.substring(info.length() - 6);
                        for (int n = 0; n < 6; n++) {
                            code = postalcode.charAt(n);
                            if (n % 2 == 0) {
                                
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
                        if (test != 1) {
                            test = 0;
                            list2.add(i, info);
                        }
                    } else {
                        test = 1;
                        System.out.println("please input the info in the correct format next time.");
                        break;
                    }

                } while (test == 1);
            }
            fileAdd.add(list2, "list.secure");
            list = fileRead.read("list.secure");
            info = "";
            for (Object list1 : list) {
                info += (list1 + "\n");
            }
            JOptionPane.showMessageDialog(null, info);
        } else {
            System.exit(0);
        }

    }
}
