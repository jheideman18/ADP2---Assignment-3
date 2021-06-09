/*
 * ADP2 - Assignent 3
 * @author Jody Heideman (219307725)
 * Last updated - 09/06/2021
 */
package za.ac.cput;

import java.io.*;
import java.text.*;
import java.time.*;
import java.util.*;

/**
 *
 * @author 21930
 */
public class ReadSerializedFileCustomer {
     private ObjectInputStream input;
     private FileWriter fw;
     private BufferedWriter bw;
     private BufferedReader br;
     boolean rent;
     int totalRent = 0  , totalNoRent = 0;

    //ArrayList to store stakeholer and customer objects
   List<Stakeholder> stakeholder = new ArrayList<>();
   List<Customer> customerList = new ArrayList<>();
   
   Customer customer;
   
   
   int[] dobArray = new int[6];
   String[] reformattedDate = new String[6];
   
    public void openFile(){
        
        try{
            input = new ObjectInputStream( new FileInputStream("stakeholder.ser"));
            System.out.println("*** ser file opened for reading");
        }catch(IOException ioe){
            System.out.println("Error opening ser file" + ioe.getMessage());
            
        }
    }
    
    public void closeFile(){
        try{
            input.close();
        }
        catch(IOException ioe){
             System.out.println("Error closing ser file" + ioe.getMessage());
        }
    }
    
    public void sortList(){
        
         Collections.sort(stakeholder , new Comparator<Stakeholder>()
           {
               @Override
               public int compare(Stakeholder s1 , Stakeholder s2){
                   
                   return String.valueOf(s1.getStHolderId()).compareTo(s2.getStHolderId());
               }
           }
           );
    }
    
    public void readFromFile(){
        try{
            
           for (int i = 0; i < 11; i++) {

                 stakeholder.add((Stakeholder)input.readObject());

            }  
          //Sorts the stakeholder array
           sortList();
           
            for (int i = 0; i < stakeholder.size(); i++) {
                
                 if(stakeholder.get(i) instanceof Customer)
                 {
                        customerList.add((Customer) stakeholder.get(i));
                } 
            }

        }
       
        catch(EOFException eofe){
            
             System.out.println("End-of-file reached");
        }
        catch(ClassNotFoundException ioe){
            
             System.out.println("Class error reading sr fle" + ioe);
            
        }
        catch(IOException ioe){
            
            System.out.println("Error reading sr fle" + ioe);
        }
    }

    public void display(){
            System.out.println("========================= Customers =============================================");
            System.out.println("ID      	" + "Name      	" + "Surname      	" + "Date of birth    " + "      Age");
            System.out.println("==================================================================================");
            
            for (int i = 0; i < customerList.size(); i++) {
                
                String id = customerList.get(i).getStHolderId();
                String name = customerList.get(i).getFirstName();
                String surname = customerList.get(i).getSurName();
                int Age = dobArray[i];
                String rDate = reformattedDate[i];
                
                 System.out.format("%-10s\t%-10s\t%-10s\t%-20s\t%-10s\n",id, name, surname, rDate, Age);
                
        }
    }
    
         public void fileWriter(){
          try{

          fw = new FileWriter("customerOutFile.txt");
          bw = new BufferedWriter(fw);
          
            bw.write("========================= Customers ============================================================\n");
            bw.write("ID      \tName      \tSurname      \tDate of birth          Age\n");
            bw.write("=================================================================================================\n");

             age();
             rent();
             formatDate();
             
              for(int i = 0; i < customerList.size(); i++){
                String id = customerList.get(i).getStHolderId();
                String name = customerList.get(i).getFirstName();
                String surname = customerList.get(i).getSurName();
                String rDate = reformattedDate[i];
                int Age = dobArray[i];

                String output = String.format("%-10s\t%-10s\t%-10s\t%-20s\t%-10s\n",id, name, surname, rDate, Age);
                bw.write(output); 
              }
              
              bw.write("\n");
              
              bw.write("Number of people who can rent: " + totalRent + "\n");
              bw.write("Number of people who cannot rent: " + totalNoRent);

              bw.close();
              System.out.println("Success");
      }
          catch(IOException ioe){
              System.out.println("**File has been closed**");
              
          }
          finally{
               closeFile();
            System.out.println("**File has been closed**");
          }
}

          public void rent(){

              for (int i = 0; i < customerList.size(); i++) {
                  
                    rent = customerList.get(i).getCanRent();  
                    
                  if(rent != true){
                      
                   totalNoRent +=1;
                      
              }else{
                      
                     totalRent +=1;
                  }
                        
              }
 
              }
          
          public void formatDate(){
             
             DateFormat outputFormat = new SimpleDateFormat("dd MMM YYY");
             
             DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
             
             
                 for (int i = 0; i < customerList.size(); i++) {
                     
                      try{
                 String inputText = customerList.get(i).getDateOfBirth();
                          
                 Date date = inputFormat.parse(inputText);
                 String outputText = outputFormat.format(date);
                 
                 reformattedDate[i] = outputText;
                 
             }catch(ParseException e){
                 
                 System.out.println("Error closing ser file" + e.getMessage());
             }
         }
}
              
             public void age(){
            
             LocalDate currDate = LocalDate.now();
             
             
             for (int i = 0; i < dobArray.length; i++) {
                 
                 customer = customerList.get(i);
                 
                 LocalDate birthDate = LocalDate.parse(customer.getDateOfBirth());
                 
                 int getAge = Period.between(birthDate, currDate).getYears();
                 
                 dobArray[i] = getAge;
                  
             }
         }
    

    
    
}

