/*
 * ADP2 - Assignent 3
 * @author Jody Heideman (219307725)
 * Last updated - 09/06/2021
 */
package za.ac.cput;


import java.io.*;
import java.util.*;

public class ReadSerializedFileSupplier {
     private ObjectInputStream input;
     private FileWriter fw;
     private BufferedWriter bw;

    Supplier supply;
    List<Supplier> supplyList = new ArrayList<>();
    List<Stakeholder> stakeholder = new ArrayList<>();
    
       public void openFile(){
        
        try
        {
            input = new ObjectInputStream( new FileInputStream("stakeholder.ser"));
            System.out.println("ser file opened for reading");
            
        }
        catch(IOException ioe)
        {
            System.out.println("Error opening ser file" + ioe.getMessage());
            
        }
    }
       
        public void closeFile(){
        try
        {
            input.close();
        }
        catch(IOException ioe)
        {
             System.out.println("Error closing ser file" + ioe.getMessage());
        }
    }
       
        public void sortList(){
            Collections.sort(supplyList , new Comparator<Supplier>()
           {
               @Override
               public int compare(Supplier s1 , Supplier s2)
               {
               return String.valueOf(s1.getName()).compareTo(s2.getName());
               }
            }
           );
          }               
        
    public void readFromFile(){
        try{
           for (int i = 0; i < 11; i++) 
           {
                 stakeholder.add((Stakeholder)input.readObject());
 
           }
            for (int i = 0; i < stakeholder.size(); i++)
            {
                 if(stakeholder.get(i) instanceof Supplier)
                 {
                   supplyList.add((Supplier) stakeholder.get(i));
                 } 
                 
            }
            
            //sorts the supplyList array
            sortList();
            System.out.println("========================= Suppliers =============================");
            System.out.println("ID      	" + "Name      	" + "Prod Type      	" + "Description");
            System.out.println("=================================================================");
            
            supplyList.stream().filter(value -> value != null).forEach(System.out::println);
    
            }
       
        catch(EOFException eofe)
        {
             System.out.println("End-of-file reached");
        }
        catch(ClassNotFoundException ioe)
        {
             System.out.println("Class error reading sr fle" + ioe);
            
        }
        catch(IOException ioe)
        {
            System.out.println("Error reading sr fle" + ioe);
        }
    }
    
             public void fileWriter(){
          try
          {
            fw = new FileWriter("supplierOutFile.txt");
            bw = new BufferedWriter(fw);
          
            bw.write("===============================Supplier ===========================================\n");
            bw.write("ID      Name      \t        Prod Type      \tDescription\n");
            bw.write("==================================================================================\n");
            
            
            
              for(int i = 0; i < supplyList.size(); i++)
              {
                  String id = supplyList.get(i).getStHolderId();
                  String name = supplyList.get(i).getName();
                  String prodType = supplyList.get(i).getProductType();
                  String description = supplyList.get(i).getProductDescription();
                  
                String output = String.format("%-1s\t%-20s\t%-15s\t%-15s\n",id, name, prodType, description);
                bw.write(output); 
             
              }
              
              bw.write("\n");
              bw.close();
              System.out.println("Success");
      }
          
          catch(IOException ioe)
          {
              System.out.println("**File has been closed**");
              
          }
          finally
          {
            closeFile();
            System.out.println("**File has been closed**");
          }
    
}
             

    
}
