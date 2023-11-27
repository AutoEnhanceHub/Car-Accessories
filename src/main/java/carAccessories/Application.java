package carAccessories;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.*;
import java.security.SecureRandom;


public  class Application {
    private final String valids="Enter a valid value in the next time\n";
    private final String tabs="     ";
    private final String adminstring="Admin";
    private final static String categ="the Category ";
    private final static String inv="invalid input";
private SecureRandom random;
    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());
    String carname;
    boolean logged_in;
 
    Login login;
    static ArrayList<Sales> sales=new ArrayList<>();
    static ArrayList<Category> categories;
    User newUser;
    SignUp signUp;
static int[] indexes=new int[2];
    Scanner scanner = new Scanner(System.in);

public Application(){
    categories=new ArrayList<>();
random=new SecureRandom();
    LOGGER.setUseParentHandlers(false);

    Handler[] handlers = LOGGER.getHandlers();
    for (Handler handler : handlers) {
        LOGGER.removeHandler(handler);
    }

    ConsoleHandler consoleHandler = new ConsoleHandler();
    consoleHandler.setLevel(Level.ALL);
    consoleHandler.setFormatter(new SimpleFormatter() {
        @Override
public synchronized String format(java.util.logging.LogRecord logRecord) {
    return logRecord.getMessage() + "\n";
}


    });
    LOGGER.addHandler(consoleHandler);

    carname="";
  
    this.logged_in = false;
    login=new Login(newUser);

    categories.add(new Category("Interior"));
    categories.get(0).products.add((new product("Vacuum Cleaner",15,50,2027)));
    categories.add(new Category("Exterior"));
    categories.add(new Category("Electronics"));
    categories.get(2).products.add(new product("car lights",13,50,2025));
    newUser=new User("ibrahim.sadi.asad@gmail.com","147852","Customer");

}



    public void SignUp(){
        signUp=new SignUp(newUser,login);
}

    public boolean isLogged_in() {
        return logged_in;
    }

    public void setLogged_in(boolean logged_in) {
        this.logged_in = logged_in;
    }
    


public String showallcatogries(){
    StringBuilder f= new StringBuilder();
    for(int i=0;i<categories.size();i++){
        f.append(i + 1).append(". ").append(categories.get(i).name).append("\n");
    }
//    JOptionPane.showMessageDialog(null,f);
    return f.toString();
}

public boolean foundc(String name){


    for(int i=0;i<categories.size();i++){
        if(name.equals(categories.get(i).name)){
            indexes[0]=i; return true;
        }
    }
    return false;
}
public void setuser(String email,String pass ,String type){

    newUser=new User(email,pass,type);
}
public void addcat(String name){
    categories.add(new Category(name));
}
    public void addnewCategory_confirmation(String m){
try {


        int response=9;
        while(true){
        LOGGER.info("Are you sure you want to continue?\n1.yes / 2.no");
        int answer=scanner.nextInt();
            scanner.nextLine();
        if(answer==1){
          response=1;  break;

        }else if(answer==2){
          break;
        }
        }


        if(response==1){
            String y="You added the Category "+m;
            LOGGER.info(y);
           addcat(m); }
        else {
              String y=categ+m+" is not added";
            LOGGER.info(y);
        }

}catch (NullPointerException e){
      String y=categ+m+" is not added";
    LOGGER.info(y);
} }
    public void newCatogry() {
    if(newUser.type.equals(adminstring)){
        LOGGER.info("What is the name of the Category?");
        String m = scanner.nextLine();
        if (foundc(m)) {
String y=categ + m + " is really exist";
            LOGGER.info(y);

        } else addnewCategory_confirmation(m);
    }else{
        String y="Only admins can delete Categories";
        LOGGER.info(y);
       }

    }
    public void edtcatogry(String oldn,String newn){
    for(int i=0;i<categories.size();i++){
        if(categories.get(i).name.equals(oldn)){
            categories.get(i).name= newn;break;
        }
    }
    }

public void editCategory(){

if(newUser.type.equals(adminstring)){
        if(categories.isEmpty()){
            LOGGER.info("There is no categories in the system");

        }else{   StringBuilder f= new StringBuilder();
            for(int i=0;i<categories.size();i++){
                f.append(i + 1).append(". ").append(categories.get(i).name).append("\n");
            }



            try {
                String y="Choose a Category\n"+f;
                LOGGER.info(y);

                int select=scanner.nextInt();
                scanner.nextLine();
                if(select<1){
                    LOGGER.info(inv);

                } else if (select>categories.size()) {
                    LOGGER.info(inv);
                }else{
                    select--;
                    LOGGER.info("What is the new name of the Category?");
String rename=scanner.nextLine();
                if(foundc(rename)){
                    LOGGER.info("This new name is for another Category");
                }
                else{

                    int response=9;
                    while(true){
                        LOGGER.info("Are you sure you want to continue?\n1. yes / 2. no");
                        int answer=scanner.nextInt();
                        scanner.nextLine();
                        if(answer==1){
                            response=1;  break;

                        } else if (answer==2) {
                            break;
                        }
                    }
  if(response==1){
      edtcatogry(categories.get(select).name,rename);
      LOGGER.info("The Name is edited successfully\n");
 
}
 else {
      LOGGER.info("the Category is not Edited\n");
   }

                }
                }
            }catch (NumberFormatException e){
                LOGGER.info(inv);
            }

        }
}else{
    LOGGER.info("Only admins can Edit Categories\n");}

}

public void dltcat(String name){
   for(int i=0;i<categories.size();i++){
       if(name.equals(categories.get(i).name)){
           categories.remove(i);break;
       }
   }
}
public void deleteCategory(){
    if(newUser.type.equals(adminstring)){
    if(categories.isEmpty()){
        LOGGER.info("There is no categories in the system\n");

    }else{   StringBuilder f= new StringBuilder();
        for(int i=0;i<categories.size();i++){
             f.append(i + 1).append(". ").append(categories.get(i).name).append("\n");
        }



        try {
            String y="Choose a Category\n"+f;
            LOGGER.info(y);

            int select=scanner.nextInt();
            scanner.nextLine();
            if(select<1){
                LOGGER.info(inv);
            } else if (select>categories.size()) {
                LOGGER.info(inv);
            }else{
                select--;

                int response=9;
                while(true){
                    LOGGER.info("Are you sure you want to continue?\n1. yes / 2. no\n");
                    int answer=scanner.nextInt();
                    scanner.nextLine();
                    if(answer==1){
                        response=1;  break;

                    } else if (answer==2) {
                      break;
                    }
                }
                if(response==1){
                    dltcat(categories.get(select).name);
                    LOGGER.info("the Category is Deleted\n");
                }
                else {
                    LOGGER.info("the Category is not Deleted\n");
                }

            }
        }catch (NumberFormatException e){
            LOGGER.info(inv);

        }
}}

else{
        LOGGER.info("Only admins can delete Categories\n");

}
}




public boolean foundp(String catname,String pname){
    for(int i=0;i<categories.size();i++){
        if(catname.equals(categories.get(i).name)){
            for(int j=0;j< categories.get(i).products.size();j++){
                if(pname.equals(categories.get(i).products.get(j).name))
                { indexes[0]=i;indexes[1]=j; return  true;}
            }
            return false;
        }
    }
    return false;
}
public String getallproducts(String catname){
   StringBuilder f= new StringBuilder();
    if(foundc(catname)){
         if(categories.get(indexes[0]).products.isEmpty()){
            return "There is no products";
        }
         f.append("#. name     quantity     price     rate\n");
     for(int i=0;i<categories.get(indexes[0]).products.size();i++){
         int c=i+1;
         if(i==categories.get(indexes[0]).products.size()-1){
         f.append(c).append(". ").append(categories.get(indexes[0]).products.get(i).name).append("     ").append(categories.get(indexes[0]).products.get(i).quantity).append("     ").append(categories.get(indexes[0]).products.get(i).price).append("     ").append(categories.get(indexes[0]).products.get(i).rate_avg);
      break;
         }
      f.append(c).append(". ").append(categories.get(indexes[0]).products.get(i).name).append("     ").append(categories.get(indexes[0]).products.get(i).quantity).append("     ").append(categories.get(indexes[0]).products.get(i).price).append("     ").append(categories.get(indexes[0]).products.get(i).rate_avg).append("\n");
 
     }
    }
   else{
      f.append("The Category is empty");
    }
    return f.toString();
}
public void showproducts(){


  try {
      String yu="Choose a Category to see its products\n"+showallcatogries();
      LOGGER.info(yu);

      int x=scanner.nextInt();
      scanner.nextLine();
      x--;
      String uuuu=getallproducts(categories.get(x).name);
      LOGGER.info(uuuu);

  }catch (Exception e){
      LOGGER.info("Enter a valid value in the next time\n");
  }

}
public void addnewproduct(String catname,String pname,int quantity,int price,int year){
    if(foundp(catname,pname)){
        categories.get(indexes[0]).products.get(indexes[1]).quantity+=quantity;
    }else{
     if(foundc(catname)){
         categories.get(indexes[0]).products.add(new product(pname,quantity,price,year));
     }
    }
}
public void newproduct(){
    if(!newUser.type.equals(adminstring)){
        LOGGER.info("Only admins can add products\n");
        return;}
    try{
        String yu="Choose a Category to add a new product\n"+showallcatogries();
        LOGGER.info(yu);

    int select=scanner.nextInt();  scanner.nextLine();
    select--;
    String catname=categories.get(select).name;
        LOGGER.info("What is the name of the new product?\n");
        String pname=scanner.nextLine();
        LOGGER.info("What is the quantity of the new product?\n");

        int quantity=scanner.nextInt();  scanner.nextLine();
        if(quantity<1){
            throw new InputMismatchException();
        }
        if(foundp(catname,pname)){
            addnewproduct(catname,pname,quantity,0,0);
            String yupp="The quantity is added to the exist product "+pname+"\n";
            LOGGER.info(yupp);
            return;
        }
        LOGGER.info("what is the price of this new product?\n");

        int price= scanner.nextInt();  scanner.nextLine();
        if(price<1){
            throw new InputMismatchException();
        }LOGGER.info("Which year this product will be expired?\n");

        int year= scanner.nextInt();  scanner.nextLine();
        if(year<=LocalDate.now().getYear()){
            throw new InputMismatchException();
        }
        addnewproduct(catname,pname,quantity,price,year);
        LOGGER.info("The product is added Successfully\n");



}catch (Exception e){
        LOGGER.info(inv);

    }
}
public void editproduct(String catname,String pname,String newname,int newprice){
    if(foundp(catname,pname)){
        categories.get(indexes[0]).products.get(indexes[1]).name=newname;
        categories.get(indexes[0]).products.get(indexes[1]).price = newprice;
    }
}
public void editproduct(){
    if(!newUser.type.equals(adminstring)){
        LOGGER.info("Only admins can edit products\n");

        return;}
    try{
String y1y="Choose a Category to edit\n"+showallcatogries();
        LOGGER.info(y1y);

        int cselect= scanner.nextInt();  scanner.nextLine();
        cselect--;
        if(categories.get(cselect).products.isEmpty()){
            LOGGER.info("There is no products to edit\n");

            return;
        }
        String catname=categories.get(cselect).name;
        String y2y="Choose a product to edit\n"+getallproducts(catname);
        LOGGER.info(y2y);

        int pselect= scanner.nextInt();  scanner.nextLine();
        pselect--;
        String old=categories.get(cselect).products.get(pselect).name;
        LOGGER.info("What is the new name of the product "+old+"?\n");
        String newname= scanner.nextLine();

for(int i=0;i<categories.get(cselect).products.size();i++){
    if(i==pselect)continue;
    if(newname.equals(categories.get(cselect).products.get(i).name)){
        throw new Exception();
    }
}
 String y99y="What is the new price of the product "+old+"?\n";      LOGGER.info(y99y);

     int newprice = scanner.nextInt();
scanner.nextLine();

if (newprice < 1) {
    throw new Exception();
}

if (newname.isEmpty()) {
    editproduct(catname, old, old, newprice);
} else {
    editproduct(catname, old, newname, newprice);
}

LOGGER.info("The product is updated successfully\n");
    }
   catch (Exception e){
       LOGGER.info(inv);
    }
}
public void deleteproduct(){
    if(!newUser.type.equals(adminstring)){
        LOGGER.info("Only admins can delete products\n");

        return;}
    try{
        String y3y="Choose a Category to delete a product\n"+showallcatogries();
        LOGGER.info(y3y);

        int cselect= scanner.nextInt();  scanner.nextLine();
        cselect--;
        if(categories.get(cselect).products.isEmpty()){
            LOGGER.info("There is no products to remove\n");
            return;
        }
        String catname=categories.get(cselect).name;
        String y4y="Choose a product to delete\n"+getallproducts(catname);
        LOGGER.info(y4y);

        int pselect= scanner.nextInt();  scanner.nextLine();
        pselect--;

        categories.get(cselect).products.remove(pselect);

    }
    catch (Exception e){
        LOGGER.info(inv);
    }
}
public void dltp(String catname,String pname){
    if(foundp(catname,pname)){
        categories.get(indexes[0]).products.remove(indexes[1]);
    }
}

public boolean installrequest(String catname,String pname,int quantity,String carname){
    if(foundp(catname,pname)){
        if(quantity<=categories.get(indexes[0]).products.get(indexes[1]).quantity){
            categories.get(indexes[0]).products.get(indexes[1]).quantity-=quantity;
            this.carname=carname;
            return true;
        }
    }
    return false;
}
public void installproduct(){
    if(!newUser.type.equals("Installer")){
        LOGGER.info("Only customers can make an installation request");

        return;}
    try{
        String y5y="Choose a Category to request a product\n"+showallcatogries();
        LOGGER.info(y5y);

        int cselect= scanner.nextInt();  scanner.nextLine();
        cselect--;
        if(categories.get(cselect).products.isEmpty()){
            LOGGER.info("There is no products to request");
            return;
        }
        String catname=categories.get(cselect).name;
        String y6y="Choose a product to request\n"+getallproducts(catname);
        LOGGER.info(y6y);

        int pselect= scanner.nextInt();  scanner.nextLine();
        pselect--;
        String pname=categories.get(cselect).products.get(pselect).name;
        if(categories.get(cselect).products.get(pselect).quantity==0){
            LOGGER.info("The product is not enough to buy!\n");
            return;
        }
        LOGGER.info("Select the quantity");

        int qu= scanner.nextInt();  scanner.nextLine();
        LOGGER.info("What is the car name?");
        String car=scanner.nextLine();
        if(installrequest(catname,pname,qu,carname)){
            int fee=qu*categories.get(cselect).products.get(pselect).price;
            String y7y="The request will be installed successfully\n" +
                    "Your FEE is "+qu*categories.get(cselect).products.get(pselect).price+"\n";
            LOGGER.info(y7y);

            if(categories.get(cselect).products.get(pselect).quantity==0){
                categories.get(cselect).products.remove(pselect);
            }
            


            int rValue = random.nextInt(5) + 1;
            LocalDate ship=LocalDate.now().plusDays(rValue);
            String message="Your order has been received and is currently being processed. " +
                    " The order is going to be shipped after ." +ship+
                    ". Thank you for shopping with us!\n" +
                    "Best regards,";
            sales.add(new Sales(catname,pname,fee,qu,LocalDate.now(),ship,car));



            String recipientEmail = newUser.getEmail(); // Replace with the recipient's email
            String subject = "Installation Request";


            Mailing m1=new Mailing(recipientEmail);
            m1.sendEmail(subject,message);
        }

}catch (Exception e){
        LOGGER.info(valids);

    }}

 public void rate_review(String catname,String pname,int rate,String review){
    if(foundp(catname,pname)){
        if(rate>0&&rate<6){
categories.get(indexes[0]).products.get(indexes[1]).rates.add(rate);
int sum=0;
for(int i:categories.get(indexes[0]).products.get(indexes[1]).rates){
   sum +=i;
}
categories.get(indexes[0]).products.get(indexes[1]).rate_avg=(float)sum/categories.get(indexes[0]).products.get(indexes[1]).rates.size();
            categories.get(indexes[0]).products.get(indexes[1]).reviews.add(review);
        }
    }

 }
 public void newrate(){
     if(!newUser.type.equals("Customer")){
         LOGGER.info("Only customers can rate and review\n");
         return;}
     try{
String y8y="Choose a Category to rate and review a product\n"+showallcatogries();
         LOGGER.info(y8y);

         int cselect=scanner.nextInt();  scanner.nextLine();
         cselect--;
         if(categories.get(cselect).products.isEmpty()){
             LOGGER.info("There is no products to rate or to review in this Category");
             return;
         }
         String catname=categories.get(cselect).name;
         String y9y="Choose a product to rate and review\n"+getallproducts(catname);
         LOGGER.info(y9y);

         int pselect= scanner.nextInt();  scanner.nextLine();
         pselect--;
         String pname=categories.get(cselect).products.get(pselect).name;

         LOGGER.info("How much is the adding rate?  1-5");

         int rate= scanner.nextInt();  scanner.nextLine();
         if(rate<1||rate>5){
             throw new Exception();
         }
         LOGGER.info("Write a new review");
         String review=scanner.nextLine();
         if(review.isEmpty())throw new Exception();
         rate_review(catname,pname,rate,review);
         LOGGER.info("The new rate is added,The new review is added\n");
     }
     catch (Exception e){
         LOGGER.info(valids);

     }
 }
 public String reviews(String catname,String pname){
    if(foundp(catname,pname)){
     String f="";int c;
     for (int i=0;i<categories.get(indexes[0]).products.get(indexes[1]).reviews.size();i++){
        c=i+1;
        f=f+"Rate number "+c+" :"+categories.get(indexes[0]).products.get(indexes[1]).rates.get(i);
        f=f+"\nReview number "+c+" :"+categories.get(indexes[0]).products.get(indexes[1]).reviews.get(i)+"\n\n\n";
     }
     f=f+" the Average Rate is :"+categories.get(indexes[0]).products.get(indexes[1]).rate_avg;
     return f;
    }
    return "";
 }
 public void showreviews(){
     if(!newUser.type.equals(adminstring)){
         LOGGER.info("Only Admins can get informations\n");
         return;}
     try{
String y1y="Choose a Category to get informations about a product\n"+showallcatogries();
         LOGGER.info(y1y);

         int cselect= scanner.nextInt();  scanner.nextLine();
         cselect--;
         if(categories.get(cselect).products.isEmpty()){
             LOGGER.info("There is no products to get informations\n");
             return;
         }
         String catname=categories.get(cselect).name;
         String y2y="Choose a product to get informations\n"+getallproducts(catname);
         LOGGER.info(y2y);

         int pselect= scanner.nextInt();  scanner.nextLine();
         pselect--;
         String pname=categories.get(cselect).products.get(pselect).name;
         String message= reviews(catname,pname);
         if(message.isEmpty()){
             LOGGER.info("The Choosed product doesnt have any rate or review\n");
             return;
         }
         LOGGER.info(message+"\n");
     }
     catch (Exception e){
         LOGGER.info(valids);
     }
 }

    public static boolean printTextToFile(String fileName, String text) {
        try(FileWriter writer = new FileWriter(fileName)) {
            
            writer.write(text);
            writer.close();
            return true;

        } catch (IOException ignored) {
            return false;
        }
    }

    public String Salesreport() {
        String f="";
        String g="#. Category\tProduct\tQuantity\tFee\tCar\tSent-date\tShipped-date\n";
        f=f+g;
        for(int i=0;i<sales.size();i++){
            int c=i+1;
            f=f+c+". "+sales.get(i).catname+"\t"+
                    sales.get(i).pname+"\t"+
                    sales.get(i).quantity+"\t"+
                    sales.get(i).fee+"\t"+
                    sales.get(i).carname+"\t"+
                    sales.get(i).sent+"\t"+
                    sales.get(i).shipped+"\t"+"\n";
        }
        if(f.equals(g))return "There is no informations";
        return f;
    }
    public String Ratesreport(){
        String f="";String g="#. product\tRate\n";
        int c=1;
        f=f+g;
        for(int i=0;i<categories.size();i++){

            for (int j=0;j<categories.get(i).products.size();j++){

                f=f+c+". "+categories.get(i).products.get(j).name+"\t"+categories.get(i).products.get(j).rate_avg+"\n";

                c++;
            }
        }
        if(f.equals(g))return "There is no informations";
        return f;
    }
    public String productreport(){
        String f="";
        int c,g;

        for(int i=0;i<categories.size();i++){g=i+1;
            f=f+"Category "+g+" : "+categories.get(i).name+"\n";
            if(categories.get(i).products.isEmpty()){
                f=f+"There is no products in this Category\n";
                continue;
            }
            for (int j=0;j<categories.get(i).products.size();j++){
                c=j+1;
                f=f+"#. Name     Price     Quantity\n";
                f=f+c+". "+categories.get(i).products.get(j).name+"\t"+
                        categories.get(i).products.get(j).price+"\t"+
                        categories.get(i).products.get(j).quantity+"\n";
            }
        }
        if(f.isEmpty())return "There is no informations";
        return f;
    }
    public String Ratesreviewsreport(){
        String f="";
        int c,g,b;
        g=0;
        for(int i=0;i<categories.size();i++){

            if(categories.get(i).products.isEmpty()){continue;}

            g++;
            f=f+"Category "+g+" : "+categories.get(i).name+"\n";
            c=0;

            for (int j=0;j<categories.get(i).products.size();j++){
                if(categories.get(i).products.get(j).rates.isEmpty()){
                    c--;continue;
                }
                c++;
                f=f+"Product "+c+" : "+categories.get(i).products.get(j).name+"\n";
                for(int z=0;z<categories.get(i).products.get(j).rates.size();z++){
                    b=z+1;
                    f=f+"Rate number "+b+" : "+categories.get(i).products.get(j).rates.get(z)+"\n";
                    f= f + "Review number " + b + " :\n" + categories.get(i).products.get(j).reviews.get(z) + "\n";
                }
            }
        }
        if(f.isEmpty())return "There is no informations";
        return f;
    }
public boolean report(String report, String filename) {
    switch (report) {
        case "Sales":
            return printTextToFile(filename, Salesreport());
        case "Product rates":
            return printTextToFile(filename, Ratesreport());
        case "Category products":
            return printTextToFile(filename, productreport());
        case "rates and reviews":
            return printTextToFile(filename, Ratesreviewsreport());
        default:
            return false;
    }
}

public void makereport() {
    if (newUser.getType().equals(adminstring)) {
        try {
            LOGGER.info("What is the name of the file?");
            String file = scanner.next();
String y3y="Choose a report\n1. Sales report\n2. Product rates report\n" +
                    "3. Category products report\n4. rates and reviews report";
            LOGGER.info(y3y);

            int c = scanner.nextInt();
            scanner.nextLine();

            switch (c) {
                case 1:
                    report("Sales", file);
                    break;
                case 2:
                    report("Product rates", file);
                    break;
                case 3:
                    report("Category products", file);
                    break;
                case 4:
                    report("rates and reviews", file);
                    break;
                default:
                    throw new Exception();
            }
        } catch (Exception e) {
            LOGGER.info(valids);
        }
    }
}


    }


