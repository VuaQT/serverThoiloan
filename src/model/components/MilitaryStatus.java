package model.components;

import java.util.HashMap;
import java.util.Vector;

/**
 * Created by CPU11630_LOCAL on 12/8/2018.
 */
public class MilitaryStatus {
    private HashMap<Integer, Integer> amount = null; // hashmap from soldier/sorcery type to soldier/sorcery amount
    public void MillitaryStatus(){
        amount = new HashMap<Integer, Integer>();
    }
    public int getAmount(int type){
        return amount.getOrDefault(type,0);
    }
    public void changeAmount(int type, int number){
        if(number == 0) return;
        int newAmount = this.getAmount(type) + number;
        if(newAmount == 0){
            amount.remove(type);
        }   else    {
            if(amount.containsKey(type)){
                amount.replace(type,newAmount);
            }   else    {
                amount.put(type,newAmount);
            }
        }
    }
}
