package MasterPackge;

import java.io.File;

/**
 *
 * @author GMT
 */
public class Manager extends SBT<Dictionary> {
    
    public Manager() {}
    
    public int menu(){
        System.out.println("\n================ Dictionary program ================");
        System.out.println("1. Add Word");
        System.out.println("2. Delete Word");
        System.out.println("3. Search Word");
        System.out.println("4. In the road go between 2 node any node on tree");
        System.out.println("5. Display result");
        System.out.println("6. Exit");
        System.out.println("====================================================");
        System.out.print("Enter your choice: ");
        int choice = Validation.checkInputIntLimit(1, 6);
        return choice;
    }
    
    public void loadFile(String filename){
        File f = new File(filename);
        
    }
    
    public void saveFile(String filename){
        File f = new File(filename);
        
    }
    
    public void addWord(){
        String english = Validation.getString("Enter new english word: "
                , "Error: Please try again");
        String vietnam = Validation.getString("Enter word meaning in VietNam: "
                , "Error: Please try again");
        this.add(new Dictionary(english, vietnam));
    }
    
    public void deleteWord(){
//        if(this.isEmpty()) {
//            System.err.println("Empty List");
//        }
//        else {
//            
            this.add(new Dictionary("Dog", "cho"));
            this.add(new Dictionary("Cat", "meo"));
            this.add(new Dictionary("Bird", "chim"));
            this.add(new Dictionary("Chicken", "ga"));
            this.bftRecursion();
            this.remove(new Dictionary("Chicken", "ga"));
            this.bftRecursion();
//        }
    }
    
    public void searchWord(){
//        if(this.isEmpty()) {
//            System.err.println("Empty List");
//        }
//        else {
//            
//        }
//            this.add(new Dictionary("cat", "meo"));
//            this.add(new Dictionary("dog", "cho"));
//            this.search(new Dictionary("dog", "cho"));
    }
    
    public void printWayNode(){
        
    }
    
    public void showAll() {
        if(this.isEmpty()) {
            System.err.println("Empty List");
        }
        else {
            System.err.println("The result is");
            this.bftRecursion();
        }
    }
}
