package carAccessories;

import java.util.ArrayList;

public class product {
    public String name;
    int quantity;
    int price;
    int rate_avg;
    public ArrayList<Integer> rates;
    public product(String n, int q , int p){
        name=new String(n);
        quantity=q;
        price=p;
        rate_avg=0;
        rates=new ArrayList<Integer>();
    }

}
