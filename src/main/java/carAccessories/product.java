package carAccessories;

import io.cucumber.java.bs.A;

import java.time.LocalDate;
import java.util.ArrayList;

public class product {
    public String name;
    int quantity;
    int price;
    float rate_avg;
    LocalDate manufactureDate;
    LocalDate expirationDate;
    public ArrayList<Integer> rates;
    public ArrayList<String> reviews;
    public product(String n, int q , int p,int year){
        name=new String(n);
        quantity=q;
        price=p;
        rate_avg=0;
        manufactureDate=LocalDate.now();

        expirationDate=LocalDate.of(year,
                manufactureDate.getMonthValue(), manufactureDate.getDayOfMonth());
        rates=new ArrayList<Integer>();
        reviews=new ArrayList<String>();
    }

}
