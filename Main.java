//Zain Jameeluddin
//Write to file

package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String sName="";
        String sYear="";
        double sGpa=-1;

        int badEntries=0;

        int missingQuestion = 0;
        Scanner myScanner = new Scanner(System.in);

        String selection="-1";

        do{
            mainMenu();
            System.out.print("Please Enter Your Numeric Selection: ");
            selection=myScanner.next();

            // Start if loops
            if(selection.equals("1")){
                System.out.print("Enter the Students Name: ");
                sName = myScanner.next();


            }else if(selection.equals("2")){
                System.out.print("Enter the Students Year: ");
                sYear = myScanner.next().toLowerCase();
                badEntries = validEntry(sYear,0);
                if(badEntries>0){
                    sYear = "";
                }
                myScanner.nextLine();
                System.out.println("Press Enter to Clear Terminal");
                myScanner.nextLine();

            }else if(selection.equals("3")){
                System.out.print("Enter the Students GPA: ");
                try {
                    sGpa = myScanner.nextDouble();
                    badEntries =validEntry("senior",sGpa);
                    if(badEntries>0){
                        sGpa = -1;
                    }
                    myScanner.nextLine();
                    System.out.println("Press Enter to clear terminal");
                    myScanner.nextLine();

                }catch (Exception ex){
                    System.out.println("Only a double value is acceptable. Please try again");
                    sGpa=-1;
                    myScanner.nextLine();
                    System.out.println("Press Enter to clear terminal");
                    myScanner.nextLine();

                }
            }else if(selection.equals("4")) {
                missingQuestion = missingData(sName, sYear, sGpa);
                if (missingQuestion == 0 && badEntries == 0) {
                    System.out.println("Students Name is: " + sName);
                    System.out.println("Students Year is: " + sYear);
                    System.out.println("Students GPA is: " + sGpa);
                    myScanner.nextLine();
                    System.out.println("Press Enter to Clear Terminal");
                    myScanner.nextLine();

                } else {
                    System.out.println("Some entries are invalid.");
                    myScanner.nextLine();
                    System.out.println("Press Enter to Clear Terminal");
                    myScanner.nextLine();
                }
                //new selection5 write file
            }else if(selection.equals("5")) {
                missingQuestion = missingData(sName, sYear, sGpa);
                if (missingQuestion == 0 && badEntries == 0) {
                    writeToFile(sName, sYear, sGpa);
                    myScanner.nextLine();
                    System.out.println("Successfully Written to File");

                }
                //new selection 6
            }else if(selection.equals("6")){
                missingQuestion= missingData(sName, sYear, sGpa);
                if( missingQuestion==0 && badEntries==0){
                    readFromFile(sName,sYear,sGpa);
                    myScanner.nextLine();
                    System.out.println("Successfully Read From File"+ "\n");
                }

            // new selection 7
            }else if (selection.equals("7")) {

            }else{
                System.out.println("Please enter a numeric selection between 1 and 5");
                myScanner.nextLine();
                System.out.println("Press Enter to Clear Terminal");
                myScanner.nextLine();

            }

        }while(!selection.equals("7"));

    }

    public static void readFromFile(String name, String ay,double gpa){
        try{
            BufferedReader myReader = new BufferedReader(new FileReader("myTest.txt"));

            String line;
            while((line = myReader.readLine()) != null)
            {
                System.out.println(line);
            }
            myReader.close();

        }catch (Exception ex){
            System.out.println("An error occurred");
        }
    }

    public static void writeToFile(String name, String ay, double gpa){
        try{
            FileWriter myWriter= new FileWriter("myTest.txt");
            myWriter.write(name+ ",");
            myWriter.write(ay+ ",");
            myWriter.write(String.valueOf(gpa)+"\n");
            myWriter.close();
        }catch (Exception ex){
            System.out.println("Error!");
        }
    }

    //check if anything is missing. Report back what is Extra Credit 2 and 3
    public static int missingData(String name, String acYear, double gpa) {
        String[] questions = {"Students Name", "Students Academic Year", "Students GPA"};
        int countOfMissing = 0;
        if (name.isEmpty()) {
            System.out.println("It looks like you left " + questions[0] + " blank");
            countOfMissing++;
        }
        if (acYear.isEmpty()) {
            System.out.println("It looks like you left " + questions[1] + " blank");
            countOfMissing++;
        }
        if (gpa < 0){
            System.out.println("It looks like you left " + questions[2] + " blank");
            countOfMissing++;
        }
        return countOfMissing;
    }

    //required method 3 - check that entries for AY and GPA are valid
    public static int validEntry(String year, double gpa){
        ArrayList<String> validAns = new ArrayList<String>();
        validAns.add("freshmen");
        validAns.add("sophomore");
        validAns.add("junior");
        validAns.add("senior");

        double lower = 0.0;
        double upper = 4.0;

        if(!validAns.contains(year) && (gpa< lower || gpa > upper)){
            System.out.println("The Year provided is invalid. The GPA appears to be outside the bounds of 0.0 and 4.0");
            return 1;
        }else if(!validAns.contains(year)){
            System.out.println("The Year provided is invalid.");
            return 1;
        }else if(gpa< lower || gpa > upper){
            System.out.println("The GPA appears to be outside the bounds of 0.0 and 4.0");
            return 1;
        }
        return 0;
    }




    //required method 2
    private static void mainMenu(){
        System.out.println("1. Enter Students Name");
        System.out.println("2. Enter Students Academic Year");
        System.out.println("3. Enter Students GPA");
        System.out.println("4. Display Students Info");
        System.out.println("5. Write Data to File");
        System.out.println("6. Read Data from File");
        System.out.println("7. Exit");


    }
}
