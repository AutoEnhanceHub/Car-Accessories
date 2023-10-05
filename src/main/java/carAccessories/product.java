package carAccessories;

import java.time.LocalDate;
import java.util.ArrayList;

public class product {
    public String name;
    int quantity;
    int price;
    int rate_avg;
    LocalDate manufactureDate;
    LocalDate expirationDate;
    public ArrayList<Integer> rates;
    public product(String n, int q , int p,int year){
        name=new String(n);
        quantity=q;
        price=p;
        rate_avg=0;
        manufactureDate=LocalDate.now();

        expirationDate=LocalDate.of(year,
                manufactureDate.getMonthValue(), manufactureDate.getDayOfMonth());
        rates=new ArrayList<Integer>();
    }

}
