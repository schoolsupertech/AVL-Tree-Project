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
public class Dictionary implements Comparable<Dictionary> {
    private String E_Word, V_Word;
    
    public Dictionary() {}
    public Dictionary(String e, String v) {
        this.E_Word = e;
        this.V_Word = v;
    }
    
// Setters
    public void setE_Word(String E_Word) {
        this.E_Word = E_Word;
    }
    public void setV_Word(String V_Word) {
        this.V_Word = V_Word;
    }
    
// Getters
    public String getE_Word() {
        return E_Word;
    }
    public String getV_Word() {
        return V_Word;
    }
    
// Display
    @Override
    public String toString() {
        return "["+ this.E_Word+ ", "+ this.V_Word+ "]";
    }

    @Override
    public int compareTo(Dictionary o) {
        switch(this.getE_Word().compareTo(o.getE_Word())) {
            case -1:
                return -1;
            case 1:
                return 1;
            default:
                return 0;
        }
    }
}