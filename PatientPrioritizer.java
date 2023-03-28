// Prioritizing Patients

// This class is an output program of prioritizing patients.
// from user's input(age,zipcode,insurance,painlevel,temperature), it computes priority
// constant values:
//      int HOSPITAL_ZIP: hospital zipcode

// Our main method consists of 3 parts.
//      1. Intro : line31
//      2. Asks Name : line32 & line42
//      3. Requires Patient Info : line36
//      4. Computing Priority Score : line36
//      5. Patient Priority Result : line41
//      6. (2)~(5) will be repeated until the output of (2) is "quit". (line35~line43)
//      7. Outro(Summary) : line44

import java.util.*;

public class PatientPrioritizer {
    public static final int HOSPITAL_ZIP = 12345;

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        intro();
        String name = name(console);
        int numPatients = 0;
        int maxScore = 0;
        while(!name.equals("quit")) {
            int priorityScore = patientInfo(console);
            numPatients++;
            if(priorityScore > maxScore) {
                maxScore = priorityScore;
            }
            priorityResult(name, priorityScore);
            name = name(console);
        }
        statistics(numPatients, maxScore);
    }

    // Prints the intro of a prioritizing program
    // This method is called once for the beginning of the program.
    public static void intro() {
        System.out.println("Hello! We value you and your time, so we will help");
        System.out.println("you prioritize which patients to see next!");
        System.out.println("Please answer the following questions about the next patient so");
        System.out.println("we can help you do your best work :)");
    }

    // Asks and catches patient's name as a single token
    // Method will be repeated unless user's input equals "quit"
    // Parameters:
    //      Scanner console: user's input
    // Returns the name of the patient
    public static String name(Scanner console) {
        System.out.println();
        System.out.println("Please enter the next patient's name" +
                             " or \"quit\" to end the program.");
        System.out.print("Patient's name: ");
        String name = console.next();
        return name;
    }

    // Asks patient's info, Delivers it to PriorityScore method, Catches computed score
    // User will be entering age,zipcode,insurance,painlevel,temperature of the patient
    // Incorrect zipcode & painlevel will result in repeated questions
    // Parameters:
    //      Scanner console: user's input
    // Returns computed score based on its input(patient's info)
    public static int patientInfo(Scanner console) {
        System.out.print("Patient age: "); 
        int age = console.nextInt();
        System.out.print("Patient zip code: ");
        int zipcode = console.nextInt();
        while(!fiveDigits(zipcode)) {
            System.out.print("Invalid zip code, enter valid zip code: ");
            zipcode = console.nextInt();
        }
        System.out.print("Is our hospital \"in network\" for the patient's insurance? ");
        String insurance = console.next();
        System.out.print("Patient pain level (1-10): ");
        int painLevel = console.nextInt();
        while(painLevel < 1 || painLevel > 10) {
            System.out.print("Invalid pain level, enter valid pain level (1-10): ");
            painLevel = console.nextInt();
        }
        System.out.print("Patient temperature (in degrees Fahrenheit): ");
        double temperature = console.nextDouble();

        int priorityScore = priorityScore(age, zipcode, insurance, painLevel, temperature);
        return priorityScore;
    }

    // Computes patient's prioritizing score
    // With a base of 100, few points will be added respectively 
    //              according to its age,zipcode,insurance,painlevel,temperature
    // Parameters:
    //      int age: age of the patient
    //      int zipcode: zipcode of the patient
    //      String insurance: answer to an insurance question
    //      int painLevel: pain level of the patient
    //      double temperature: temperature of the patient in Fahrenheit
    // Returns computed score based on its input(patient's info)
    public static int priorityScore(int age, int zipcode, String insurance, 
                                    int painLevel, double temperature) {
        int priorityScore = 100;
        if(age < 12 || age >= 75) {
            priorityScore += 50;
        }
        if((zipcode / 1000) == (HOSPITAL_ZIP / 1000)) {
            priorityScore += 15 + 25;
        } else if((zipcode / 10000) == (HOSPITAL_ZIP / 10000)) {
            priorityScore += 25;
        }
        if(insurance.equals("y") || insurance.equals("yes")) {
            priorityScore += 50;
        }
        priorityScore += painLevel * 10;
        if(temperature > 99.5) {
            priorityScore += 8;
        }
        return priorityScore;
    }

    // Prints the result of patient's priority
    // Based on its priority score, method prints different results
    // Parameters:
    //      String name: the name of the patient
    //      int priorityScore: the computed score of patient's priority
    public static void priorityResult(String name, int priorityScore) {
        System.out.println();
        System.out.println("We have found patient " + name + " to have a priority score of: "
                             + priorityScore);
        if(priorityScore >= 333) {
            System.out.println("We have determined this patient is high priority,");
            System.out.println("and it is advised to call an appropriate medical provider ASAP.");
        } else if(priorityScore >= 168) {
            System.out.println("We have determined this patient is medium priority.");
            System.out.println("Please assign an appropriate medical provider to their case");
            System.out.println("and check back in with the patient's condition" +
                                " in a little while.");
        } else {
            System.out.println("We have determined this patient is low priority.");
            System.out.println("Please put them on the waitlist for " + 
                                "when a medical provider becomes available.");
        }
        System.out.println();
        System.out.println("Thank you for using our system!");
        System.out.println("We hope we have helped you do your best!");
    }

    // Prints the summary(outro) of scoring patients
    // Prints the sum of patients, the max score it computed
    // Parameters:
    //      int numPatients: number of patients console had as an input
    //      int maxScore: max score of patient's priority after all the patients
    public static void statistics(int numPatients, int maxScore) {
        System.out.println("Statistics for the day:");
        System.out.println("..." + numPatients + " patients were helped");
        System.out.println("...the highest priority patient we saw had a score of " + maxScore);
        System.out.println("Good job today!");
    }
    
    // Checks if the value of the zipcode contains five digits
    // Parameters:
    //      int val: the value of the zipcode
    // Returns true if the integer has 5 digits, and false otherwise.
    public static boolean fiveDigits(int val) {
        val = val / 10000; // get first digit
        if (val == 0) { // has less than 5 digits
            return false;
        } else if (val / 10 == 0) { // has 5 digits
            return true;
        } else { // has more than 5 digits
            return false; 
        }
        // NOTE: the above can be written with improved "boolean zen" as follows: 
        // return val != 0 && val / 10 == 0;
    }
}
