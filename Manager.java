package MasterPackge;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 *
 * @author GMT
 */
public class Manager extends SBT<Dictionary> {
    
    public Manager() {}
    
    private int seekE(String value) {
        for(int i = 0; i < this.size(); i++) {
            if(this.get(i).getE_Word().equals(value)) {
                return i;
            }
        }
        
        return -1;
    }
    
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
    
    public void loadFile(String filename) throws IOException{
        File f = new File(filename);
        
        if(!f.exists()) {
            f.createNewFile();
        }
        else {
            try {
                BufferedReader br;
                try(FileReader fr = new FileReader(f)) {
                     br = new BufferedReader(fr);
                     String details;
                     while((details = br.readLine()) != null) {
                         StringTokenizer stk = new StringTokenizer(details, ";");
                         while(stk.hasMoreTokens()) {
                             String english = stk.nextToken();
                             String vietnam = stk.nextToken();
                             this.add(new Dictionary(english, vietnam));
                         }
                     }
                }
                br.close();
            }
            catch(IOException ex) {
                System.out.println(ex);
            }
        }
    }
    
    public void saveFile(String filename) throws IOException{
        File f = new File(filename);
        if(!f.exists()) {
            f.createNewFile();
        }
        else {
            try {
                PrintWriter pw;
                try(FileWriter fw = new FileWriter(f)) {
                    pw = new PrintWriter(fw);
                    for(int i = 0; i < this.size(); i++) {
                        pw.println(this.get(i).getE_Word()+ ";"
                                + this.get(i).getV_Word());
                    }
                }
            }
            catch(IOException ex) {
                System.out.println(ex);
            }
        }
    }
    
    public void addWord(){
        String english = Validation.getString("Enter new english word: "
                , "Error: Please try again");
        String vietnam = Validation.getString("Enter word meaning in VietNam: "
                , "Error: Please try again");
        this.add(new Dictionary(english, vietnam));
    }
    
    public void deleteWord(){
        if(this.isEmpty()) {
            System.err.println("Empty List");
        }
        else {
            int pos;
            
            String english = Validation.getString("Enter new english word: "
                , "Error: Please try again");
            pos = seekE(english);
            
            if(pos < 0) {
                System.err.println("Not Found");
            }
            else {
                this.remove(this.get(pos));
                System.err.println("Done");
            }
        }
    }
    
    public void searchWord(){
        if(this.isEmpty()) {
            System.err.println("Empty List");
        }
        else {
            int pos;
            
            String english = Validation.getString("Enter english word need to find: "
                    , "Error: Please try again");
            pos = seekE(english);
            
            if(pos < 0) {
                System.err.println("Not found");
            }
            else {
                this.search(this.get(pos));
            }
        }
    }
    
    public void printWayNode(){
        int pos1, pos2;
        String e1 = Validation.getString("Enter english word: "
                , "Error: Please try again");
        String e2 = Validation.getString("Enter english word: "
                , "Error: Please try again");
        
        pos1 = seekE(e1);
        pos2 = seekE(e2);
        
        if(pos1 < 0 || pos2 < 0) {
            System.out.println("Not found");
        }
        else {
            this.print2Node(this.get(pos1), this.get(pos2));
        }
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
