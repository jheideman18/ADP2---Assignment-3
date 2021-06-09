/*
 * ADP2 - Assignent 3
 * @author Jody Heideman (219307725)
 * Last updated - 09/06/2021
 */
package za.ac.cput;


public class runSerializedFile {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ReadSerializedFileCustomer customerFile = new ReadSerializedFileCustomer();
        ReadSerializedFileSupplier supplierFile = new ReadSerializedFileSupplier();
        
        //=======Customer File=======//
        customerFile.openFile();
        customerFile.readFromFile(); 
        customerFile.fileWriter();
        customerFile.display();
         
        //=======Supplier File========//
        supplierFile.openFile();
        supplierFile.readFromFile();
        supplierFile.fileWriter();
      
    }
    
}
