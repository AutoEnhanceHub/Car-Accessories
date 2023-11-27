package car_accessories;
import java.util.List;

import java.time.LocalDate;
import java.util.ArrayList;

public class Product {
    protected String name;
    int quantity;
    int price;
    float rateAvg;
    LocalDate manufactureDate;
    LocalDate expirationDate;
    protected List<Integer>rates= new ArrayList<>();
    protected List<String> reviews= new ArrayList<>();
    public Product(String n, int q , int p, int year){
        name=n;
        quantity=q;
        price=p;
        rateAvg =0;
        manufactureDate=LocalDate.now();

        expirationDate=LocalDate.of(year,
                manufactureDate.getMonthValue(), manufactureDate.getDayOfMonth());
        
        
    }

}
