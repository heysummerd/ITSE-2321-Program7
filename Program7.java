//********************************************************************
//
//  Author:        Summer Davis
//
//  Program #:     Seven
//
//  File Name:     Program7.java
//
//  Course:        ITSE 2321 Object-Oriented Programming
//
//  Description:   This program will calculate raises for college faculty members
//				   based on each current salary, and display the projected cost
//			       of these raises for college trustees to easily review.

//				   Raise Guidelines:
//				   - If a faculty member's salary is > $70,000
//					 --> they receive a 4% raise
//				   - Otherwise, if a faculty member's salary is > $50,000
//				     --> they receive a 7% raise
//				   - All other faculty members receive a 5.5% raise
//
//				   Final report displays:
//				   (5 Column Format with Headings)
//				   - faculty number (automated starting from 1)
//				   - old salary
//				   - raise percentage
//				   - pay raise
//				   - new salary
//				   (Summary)
//				   - total amount of raises
//				   - average of raises
//				   - total of faculty payroll before raises
//				   - average of faculty payroll before raises
//				   - total of faculty payroll after raises
//				   - average of faculty payroll after raises
//
//				   Other notes:
//				   - End-of-file is used as the sentinel value
//				   - Input data pulled from Program7.txt
//				   - Output data for report written to Program7-output.txt
//
//********************************************************************

// imports
import java.util.Scanner;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.io.*;
import java.nio.file.Paths;

// class Program7
public class Program7
{
   private Scanner input;
   
   //***************************************************************
   //
   //  Method:       main
   // 
   //  Description:  The main method of the program
   //
   //  Parameters:   A String Array
   //
   //  Returns:      N/A 
   //
   //**************************************************************
   public static void main(String[] args)
   {   
	   Program7 myObject = new Program7();
	    
	   // display developer info
	   developerInfo();
	   
	   // open data file (Program7.txt)
       myObject.openFile();
       
       // pull salary data from the file & assign to oldSalaries
       ArrayList<Double> oldSalaries = myObject.getOldSalaries();
       
       // use oldSalaries to calculate raisePercentages
       ArrayList<Double> raisePercentages = myObject.processRaisePercentages(oldSalaries);
       
       // use oldSalaries & raisePercentages to calculate payRaises
       ArrayList<Double> payRaises = myObject.processPayRaises(oldSalaries, raisePercentages);
       
       // use oldSalaries & payRaises to calculate newSalaries
       ArrayList<Double> newSalaries = myObject.processNewSalaries(oldSalaries, payRaises);
       
       // calculate total amount of the raises
       double totalRaises = myObject.sumRaises(payRaises);
       
       // calculate the average of the raises
       double averageRaise = myObject.averageRaises(totalRaises, payRaises);
       
       // calculate total of faculty payroll before raises
       double totalOldSalaries = myObject.sumOldSalaries(oldSalaries);
       
       // calculate average of faculty payroll before raises
       double averageOldSalary = myObject.averageOldSalaries(totalOldSalaries, oldSalaries);
       
       // calculate total of faculty payroll after raises
       double totalNewSalaries = myObject.sumNewSalaries(newSalaries);
       
       // calculate average of faculty payroll after raises
       double averageNewSalary = myObject.averageNewSalaries(totalNewSalaries, newSalaries);
       
       // write raise report into a new data file (Program7-output.txt)
       myObject.writeReport(oldSalaries, raisePercentages, payRaises, newSalaries, totalRaises,
    		   				averageRaise, totalOldSalaries, averageOldSalary, totalNewSalaries,
    		   				averageNewSalary);
	  
   } 

   //***************************************************************
   //
   //  Method:       openFile
   // 
   //  Description:  opens an existing file to scan for input
   //
   //  Parameters:   None
   //
   //  Returns:      N/A 
   //
   //**************************************************************
   public void openFile()
   {
      try
      {
         input = new Scanner(Paths.get("Program7.txt")); 
      } 
      catch (IOException ioException)
      {
         System.err.println("Error opening file. Terminating.");
         System.exit(1);
      }
   }
   
   
   //***************************************************************
   //
   //  Method:       getOldSalaries
   // 
   //  Description:  Collects salary data into an ArrayList
   //
   //  Parameters:   None
   //
   //  Returns:      ArrayList<Double>
   //
   //**************************************************************
   public ArrayList<Double> getOldSalaries()
   {
	  // create a salary ArrayList
  	  ArrayList<Double> oldSalaries = new ArrayList<Double>();
  	  
      try 
      {
    	// while there is more to read
         while (input.hasNext()) 
         {   // add each salary to oldSalaries
        	 oldSalaries.add(input.nextDouble());
         }
         
      } 
      // check for exceptions
      catch (NoSuchElementException elementException)
      {
         System.err.println("File improperly formed. Terminating.");
		 System.exit(1); // terminate the program
      } 
      catch (IllegalStateException stateException)
      {
         System.err.println("Error reading from file. Terminating.");
		 System.exit(1); // terminate the program
      }
      
    // return salary data
	return oldSalaries; 
      
   }
   
   //***************************************************************
   //
   //  Method:       processRaisePercentages
   // 
   //  Description:  Use oldSalaries data to calculate raisePercentages
   //
   //  Parameters:   ArrayList<Double>
   //
   //  Returns:      ArrayList<Double>
   //
   //**************************************************************
   public ArrayList<Double> processRaisePercentages(ArrayList<Double> oldSalaries) {
	   
	   // create ArrayList raisePercentages
	   ArrayList<Double> raisePercentages = new ArrayList<Double>();
	   
	   // for each faculty member salary, find the raise percentage
	   for (double salary : oldSalaries) {
		   
		   // create placeholder raise percentage
		   double raisePercentage = 0;
		   
		   // check which raisePercentage the salary qualifies for
		   if (salary > 70_000) {
			   raisePercentage = 0.04;
		   }
		   else if (salary > 50_000) {
			   raisePercentage = 0.07;
		   }
		   else {
			   raisePercentage = 0.055;
		   }
		   
		   // add raisePercentage to array list
		   raisePercentages.add(raisePercentage);
		   
	   }
	   
	   return raisePercentages;
	   
   }
   
   //***************************************************************
   //
   //  Method:       processPayRaises
   // 
   //  Description:  Use oldSalaries & raisePercentages to calculate payRaises
   //
   //  Parameters:   ArrayList<Double> (2)
   //
   //  Returns:      ArrayList<Double>
   //
   //**************************************************************
   public ArrayList<Double> processPayRaises(ArrayList<Double> oldSalaries, ArrayList<Double> raisePercentages) {
	   
	   
	// create ArrayList payRaises
	   ArrayList<Double> payRaises = new ArrayList<Double>();
	   
	   // for each faculty member salary, find the pay raise
	   for (int index = 0; index < oldSalaries.size(); index++) {
		   
		   // calculate the pay raise for this faculty member salary
		   double payRaise = oldSalaries.get(index) * raisePercentages.get(index);
		   
		   // add to payRaises
		   payRaises.add(payRaise);
		   
	   }
	   
	   return payRaises;
	   
   }
   
   //***************************************************************
   //
   //  Method:       processNewSalaries
   // 
   //  Description:  Use oldSalaries & payRaises to calculate newSalaries
   //
   //  Parameters:   ArrayList<Double> (2)
   //
   //  Returns:      ArrayList<Double>
   //
   //**************************************************************
   public ArrayList<Double> processNewSalaries(ArrayList<Double> oldSalaries, ArrayList<Double> payRaises) {
	   
	   
	// create ArrayList newSalaries
	   ArrayList<Double> newSalaries = new ArrayList<Double>();
	   
	   // for each faculty member salary, find the new salary
	   for (int index = 0; index < oldSalaries.size(); index++) {
		   
		   // calculate the new salary for this faculty member
		   double newSalary = oldSalaries.get(index) + payRaises.get(index);
		   
		   // add to newSalaries
		   newSalaries.add(newSalary);
		   
	   }
	   
	   // return newSalaries array list
	   return newSalaries;
	   
   }
   
   //***************************************************************
   //
   //  Method:       sumRaises
   // 
   //  Description:  sum the payRaises
   //
   //  Parameters:   ArrayList<Double>
   //
   //  Returns:      double
   //
   //**************************************************************
   public double sumRaises(ArrayList<Double> payRaises) {
	   
	   // create variable totalRaises
	   double totalRaises = 0;
	   
	   // sum the total of payRaises
	   for (double raise : payRaises) {
		   
		   totalRaises += raise;
	   }
	   
	   return totalRaises;
	   
   }
   
   //***************************************************************
   //
   //  Method:       averageRaises
   // 
   //  Description:  find the average of the payRaises
   //
   //  Parameters:   double, ArrayList<Double>
   //
   //  Returns:      double
   //
   //**************************************************************
   public double averageRaises(double totalRaises, ArrayList<Double> payRaises) {
	   
	   // create variable averageRaise
	   double averageRaise = 0;
	   
	   // find the average raise
	   averageRaise = totalRaises / payRaises.size();
	   
	   return averageRaise;
	   
   }
   
   
   //***************************************************************
   //
   //  Method:       sumOldSalaries
   // 
   //  Description:  total of faculty payroll before raises
   //
   //  Parameters:   ArrayList<Double>
   //
   //  Returns:      double
   //
   //**************************************************************
   public double sumOldSalaries(ArrayList<Double> oldSalaries) {
	   
	   // create variable totalOldSalaries
	   double totalOldSalaries = 0;
	   
	   // sum the total of the old salaries
	   for (double salary : oldSalaries) {
		   totalOldSalaries += salary;
	   }
	   
	   return totalOldSalaries;   
	   
   }
   
   
   //***************************************************************
   //
   //  Method:       averageOldSalaries
   // 
   //  Description:  average of faculty payroll before raises
   //
   //  Parameters:   double, ArrayList<Double>
   //
   //  Returns:      double
   //
   //**************************************************************
   public double averageOldSalaries(double totalOldSalaries, ArrayList<Double> oldSalaries) {
	   
	   // create variable averageOldSalary
	   double averageOldSalary = 0;
	   
	   // find average old salary
	   averageOldSalary = totalOldSalaries / oldSalaries.size();
	   
	   return averageOldSalary;
	   
	   
   }
   
   
   //***************************************************************
   //
   //  Method:       sumNewSalaries
   // 
   //  Description:  total of faculty payroll after raises
   //
   //  Parameters:   ArrayList<Double>
   //
   //  Returns:      double
   //
   //**************************************************************
   public double sumNewSalaries(ArrayList<Double> newSalaries) {
	   
	   // create variable totalNewSalaries
	   double totalNewSalaries = 0;
	   
	   // sum the new salaries
	   for (double salary : newSalaries) {
		   totalNewSalaries += salary;
	   }
	
	   return totalNewSalaries;
	   
   }
   
   
   //***************************************************************
   //
   //  Method:       averageNewSalaries
   // 
   //  Description:  average of faculty payroll after raises
   //
   //  Parameters:   double, ArrayList<Double>
   //
   //  Returns:      double
   //
   //**************************************************************
   public double averageNewSalaries(double totalNewSalaries, ArrayList<Double> newSalaries) {
	   
	   // create variable averageNewSalary
	   double averageNewSalary = 0;
	   
	   // calculate average
	   averageNewSalary = totalNewSalaries / newSalaries.size();
	   
	   return averageNewSalary;
	   
   }
   
   
   //***************************************************************
   //
   //  Method:       writeReport
   // 
   //  Description:  Generate salary & raises report
   //				  - first section includes a five column heading:
   //				  ---> Faculty Number
   //				  ---> Old Salary
   //				  ---> Raise Percentage
   //				  ---> Pay Raise
   //       		  ---> New Salary
   //				  - second section includes a summary of:
   //			      ---> Total Amount of Raises
   //			      ---> Average of the Raises
   //				  ---> Total of Faculty Payroll Before Raises
   //			      ---> Average of Faculty Payroll Before Raises
   //				  ---> Total of Faculty Payroll After Raises
   //				  ---> Average of Faculty Payroll After Raises
   //
   //  Parameters:   None
   //
   //  Returns:      N/A 
   //
   //**************************************************************
   public void writeReport(ArrayList<Double> oldSalaries, ArrayList<Double> raisePercentages,
		   				   ArrayList<Double> payRaises, ArrayList<Double> newSalaries, 
		   				   double totalRaises, double averageRaise, double totalOldSalaries, 
		   				   double averageOldSalary, double totalNewSalaries, double averageNewSalary) {
	   
	   // create Program7-output.txt - output will be sent here
	   try 
	   {
		   File file = new File("Program7-output.txt");
		   FileWriter fw = new FileWriter(file);
		   PrintWriter pw = new PrintWriter(fw);
		   
		   // display five column header
		   pw.printf("%n   %-15s%-15s%-15s %-15s %-15s%n%n",
				     "Faculty #",
				     "Old Salary",
				     "Raise %",
				     "Pay Raise",
				     "New Salary");
		   
		   // calculate each row of data
		   for (int index = 0; index < oldSalaries.size(); index++) {
			   pw.printf("   %-15d$%,-15.2f%-15.1f$%,-15.2f$%,-15.2f%n",
					     index + 1,
					     oldSalaries.get(index),
					     raisePercentages.get(index) * 100,
					     payRaises.get(index),
					     newSalaries.get(index));
		   }
		   
		   // print summary information
		   pw.printf("%n%n   Total Amount of Raises: $%,.2f%n", totalRaises);
		   pw.printf("   Average Raise:          $%,.2f%n%n", averageRaise);
		   pw.printf("   Total Faculty Payroll%n");
		   pw.printf("   ----------------------%n");
		   pw.printf("   Before Raises: $%,.2f%n", totalOldSalaries);
		   pw.printf("   After Raises:  $%,.2f%n%n", totalNewSalaries);
		   pw.printf("   Average Faculty Salary%n");
		   pw.printf("   ----------------------%n");
		   pw.printf("   Before Raises: $%,.2f%n", averageOldSalary);
		   pw.printf("   After Raises:  $%,.2f%n%n", averageNewSalary);
		   
		   
		   
		   // close file
		   pw.close();

	   }
	   catch (IOException ioException)
	   {
	         System.err.println("Error generating output. Terminating.");
	         System.exit(1);
	   }
   }
   
   //***************************************************************
   //
   //  Method:       developerInfo
   // 
   //  Description:  The developer information method of the program
   //
   //  Parameters:   None
   //
   //  Returns:      N/A 
   //
   //**************************************************************
   public static void developerInfo()
   {
      System.out.println("Name:    Summer Davis");
      System.out.println("Course:  ITSE 2321 Object-Oriented Programming");
      System.out.println("Program: Seven \n");
	  
   } // End of developerInfo
}

