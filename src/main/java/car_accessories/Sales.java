package car_accessories;

import java.time.LocalDate;

public class Sales {
   String pname;
   String catname;
   String carname;
   int fee;
   int quantity;
    LocalDate sent;
  LocalDate shipped;

    public Sales(String cname, String pname, int fee, int quantity, LocalDate sent, LocalDate shipped, String carname){
        this.catname=cname;this.pname=pname;
        this.fee=fee;
        this.quantity=quantity;
        this.sent=sent;
        this.shipped=shipped;
        this.carname=carname;
    }

}
