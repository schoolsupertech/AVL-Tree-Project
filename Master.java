/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MasterPackge;

/**
 *
 * @author EternityShiningJewel
 */
public class Master {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Manager manager = new Manager();
        
        while(true) {
            int choice = manager.menu();
            String filename = "dict.txt";
//            manager.loadFile(filename);
            
            switch(choice){
                case 1:
                    System.out.println("Add Word");
                    manager.addWord();
                    System.err.println("Done");
                    break;
                case 2:
                    System.out.println("Delete Word");
                    manager.deleteWord();
                    System.err.println("Done");
                    break;
                case 3:
                    System.out.println("Search Word");
                    manager.searchWord();
                    break;
                case 4:
                    System.out.println("In the road go between 2 node any node on tree");
                    manager.printWayNode();
                    System.err.println("Done");
                    break;
                case 5:
                    manager.showAll();
                    break;
                case 6:
                    return;
            }
        }
    }
}