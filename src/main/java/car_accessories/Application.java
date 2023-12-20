package car_accessories;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.*;


public  class Application {
    private static final String CATEGORY ="the Category ";
    private static final String NO_INFORMATIONS ="There is no informations";
    private static final String ADMIN ="Admin";
    private static final String NEXT_TIME ="Enter a valid value in the next time\n";
    private static final String TABS ="     ";
private final SecureRandom random;
    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());
    private static final String INVALID_INPUT_MESSAGE = "Invalid Input";
    String carname;
    boolean loggedIn;
 
    Login login;
    static ArrayList<Sales> sales=new ArrayList<>();
    static ArrayList<Category> categories;
    User inU;
    SignUp signUp;
static int[] indexes=new int[2];
    Scanner scanner = new Scanner(System.in);

public Application(){
    categories=new ArrayList<>();
random=new SecureRandom();
  MainClass.initializeLogger();
    carname="";
    this.loggedIn = false;
    login=new Login(inU);

    categories.add(new Category("Interior"));
    categories.get(0).products.add((new Product("Vacuum Cleaner",15,50,2027)));
    categories.add(new Category("Exterior"));
    categories.add(new Category("Electronics"));
    categories.get(2).products.add(new Product("car lights",13,50,2025));
    inU =new User("ibrahim.sadi.asad@gmail.com","147852","Customer");

}

protected Application(User inU){
    categories=new ArrayList<>();
    random=new SecureRandom();
    MainClass.initializeLogger();
    carname="";
    this.loggedIn = false;
    login=new Login(inU);

    categories.add(new Category("Interior"));
    categories.get(0).products.add((new Product("Vacuum Cleaner",15,50,2027)));
    categories.add(new Category("Exterior"));
    categories.add(new Category("Electronics"));
    categories.get(2).products.add(new Product("car lights",13,50,2025));
}





    public void signUp(){
        signUp=new SignUp(inU,login);
}

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
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

    inU =new User(email,pass,type);
}
public void addcat(String name){
    categories.add(new Category(name));
}
    public void addnewCategoryConfirmation(String m){
try {


        boolean response=false;

        LOGGER.info("Are you sure you want to continue?\n1. yes ");
        int answer = scanner.nextInt();
        scanner.nextLine();

        if (answer == 1)response = true;





if(response){
        String ygy1="You added the Category "+m;
        LOGGER.info(ygy1);
       addcat(m);}


}catch (NullPointerException e){
    String ygy1= CATEGORY +m+" is not added";
    LOGGER.info(ygy1);
} }
    public void newCatogry() {
    if(inU.type.equals(ADMIN)){
        LOGGER.info("What is the name of the Category?");
        String m = scanner.nextLine();
        if (foundc(m)) {
            String ygy1= CATEGORY + m + " is really exist";
            LOGGER.info(ygy1);

        } else addnewCategoryConfirmation(m);
    }else{
        LOGGER.info("Only admins can delete Categories");
       }

    }
    public void edtcatogry(String oldn,String newn){
        for (Category category : categories) {
            if (category.name.equals(oldn)) {
                category.name = newn;
                break;
            }
        }
    }

public void editCategory(){

if(inU.type.equals(ADMIN)){
        if(categories.isEmpty()){
            LOGGER.info("There is no categories in the system");

        }else{   StringBuilder f= new StringBuilder();
            for(int i=0;i<categories.size();i++){
                f.append(i + 1).append(". ").append(categories.get(i).name).append("\n");
            }



            try {
                String ygy1="Choose a Category\n"+f;
                LOGGER.info(ygy1);

                int select=scanner.nextInt();
                scanner.nextLine();
                if(select<1){
                    LOGGER.info(INVALID_INPUT_MESSAGE);

                } else if (select>categories.size()) {
                    LOGGER.info(INVALID_INPUT_MESSAGE);
                }else{
                    select--;
                    LOGGER.info("What is the new name of the Category?");
String rename=scanner.nextLine();
                if(foundc(rename)){
                    LOGGER.info("This new name is for another Category");
                }
                else{

                    boolean response=false;

                        LOGGER.info("Are you sure you want to continue?\n1. yes");
                        int answer=scanner.nextInt();
                        scanner.nextLine();
                        if(answer==1)
                            response=true;



  if(response){
      edtcatogry(categories.get(select).name,rename);
      LOGGER.info("The Name is edited successfully\n");

}
 else {
      LOGGER.info("the Category is not Edited\n");
   }

                }
                }
            }catch (NumberFormatException e){
                LOGGER.info(INVALID_INPUT_MESSAGE);
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
    if(inU.type.equals(ADMIN)){
    if(categories.isEmpty()){
        LOGGER.info("There is no categories in the system\n");

    }else{    StringBuilder f= new StringBuilder();
        for(int i=0;i<categories.size();i++){
            f.append(i + 1).append(". ").append(categories.get(i).name).append("\n");
        }



        try {
            String ygh="Choose a Category\n"+f;
            LOGGER.info(ygh);

            int select=scanner.nextInt();
            scanner.nextLine();
            if(select<1){
                LOGGER.info(INVALID_INPUT_MESSAGE);
            } else if (select>categories.size()) {
                LOGGER.info(INVALID_INPUT_MESSAGE);
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
            LOGGER.info(INVALID_INPUT_MESSAGE);

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
             f.append(c).append(". ").append(categories.get(indexes[0]).products.get(i).name).append(TABS).append(categories.get(indexes[0]).products.get(i).quantity).append(TABS).append(categories.get(indexes[0]).products.get(i).price).append(TABS).append(categories.get(indexes[0]).products.get(i).rateAvg);break;
         }
  f.append(c).append(". ").append(categories.get(indexes[0]).products.get(i).name).append(TABS).append(categories.get(indexes[0]).products.get(i).quantity).append(TABS).append(categories.get(indexes[0]).products.get(i).price).append(TABS).append(categories.get(indexes[0]).products.get(i).rateAvg).append("\n");
     }
    }
   else{
        f.append("The Category is empty");
    }
    return f.toString();
}
public void showproducts(){
String ygh="Choose a Category to see its products\n"+showallcatogries();

  try {LOGGER.info(ygh);

      int x=scanner.nextInt();
      scanner.nextLine();
      x--;
      String pop=getallproducts(categories.get(x).name);
      LOGGER.info(pop);

  }catch (Exception e){
      LOGGER.info(NEXT_TIME);
  }

}
public void addnewproduct(String catname,String pname,int quantity,int price,int year){
    if(foundp(catname,pname)){
        categories.get(indexes[0]).products.get(indexes[1]).quantity+=quantity;
    }else{
     if(foundc(catname)){
         categories.get(indexes[0]).products.add(new Product(pname,quantity,price,year));
     }
    }
}
public void newproduct(){
    if(!inU.type.equals(ADMIN)){
        LOGGER.info("Only admins can add products\n");
        return;}
    try{
        String ygy1="Choose a Category to add a new product\n"+showallcatogries();
        LOGGER.info(ygy1);

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
            String ygy2="The quantity is added to the exist product "+pname+"\n";
            LOGGER.info(ygy2);
            return;
        }
        LOGGER.info("what is the price of this new product?\n");

        int price= scanner.nextInt();  scanner.nextLine();
        if(price<1){
            LOGGER.info(INVALID_INPUT_MESSAGE);
            return;
        }LOGGER.info("Which year this product will be expired?\n");

        int year= scanner.nextInt();  scanner.nextLine();
        if(year<=LocalDate.now().getYear()){
            LOGGER.info(INVALID_INPUT_MESSAGE);
            return;
        }
        addnewproduct(catname,pname,quantity,price,year);
        LOGGER.info("The product is added Successfully\n");



}catch (Exception e){
        LOGGER.info(INVALID_INPUT_MESSAGE);

    }
}
public void editproduct(String catname,String pname,String newname,int newprice){
    if(foundp(catname,pname)){
        categories.get(indexes[0]).products.get(indexes[1]).name=newname;
        categories.get(indexes[0]).products.get(indexes[1]).price = newprice;
    }
}
public void editproduct(){
    if(!inU.type.equals(ADMIN)){
        LOGGER.info("Only admins can edit products\n");

        return;}
    try{
        String ygy1="Choose a Category to edit\n"+showallcatogries();
        LOGGER.info(ygy1);

        int cselect= scanner.nextInt();  scanner.nextLine();
        cselect--;
        if(categories.get(cselect).products.isEmpty()){
            LOGGER.info("There is no products to edit\n");

            return;
        }
        String catname=categories.get(cselect).name;
        String ygy3="Choose a product to edit\n"+getallproducts(catname);
        LOGGER.info(ygy3);

        int pselect= scanner.nextInt();  scanner.nextLine();
        pselect--;
        String old=categories.get(cselect).products.get(pselect).name;
        String ygy2="What is the new name of the product "+old+"?\n";
        LOGGER.info(ygy2);
        String newname= scanner.nextLine();

for(int i=0;i<categories.get(cselect).products.size();i++){
    if(i==pselect)continue;
    if(newname.equals(categories.get(cselect).products.get(i).name)){
        LOGGER.info(INVALID_INPUT_MESSAGE);
        return;
    }
}
        String ygy4="What is the new price of the product "+old+"?\n";
        LOGGER.info(ygy4);

       int newprice=scanner.nextInt();  scanner.nextLine();
       if(newprice<1){
           LOGGER.info(INVALID_INPUT_MESSAGE);
           return;
       }
       if(newname.isEmpty()){
            editproduct(catname,old,old,newprice);
        }
       else{
            editproduct(catname,old,newname,newprice);
        }
        LOGGER.info("The product is updated successfully\n");
    }
   catch (Exception e){
       LOGGER.info(INVALID_INPUT_MESSAGE);
    }
}
public void deleteproduct(){
    if(!inU.type.equals(ADMIN)){
        LOGGER.info("Only admins can delete products\n");

        return;}
    try{
        String ygy1="Choose a Category to delete a product\n"+showallcatogries();
        LOGGER.info(ygy1);

        int cselect= scanner.nextInt();  scanner.nextLine();
        cselect--;
        if(categories.get(cselect).products.isEmpty()){
            LOGGER.info("There is no products to remove\n");
            return;
        }
        String catname=categories.get(cselect).name;
        String ygy4="Choose a product to delete\n"+getallproducts(catname);
        LOGGER.info(ygy4);

        int pselect= scanner.nextInt();  scanner.nextLine();
        pselect--;

        categories.get(cselect).products.remove(pselect);

    }
    catch (Exception e){
        LOGGER.info(INVALID_INPUT_MESSAGE);
    }
}
public void dltp(String catname,String pname){
    if(foundp(catname,pname)){
        categories.get(indexes[0]).products.remove(indexes[1]);
    }
}

public boolean installrequest(String catname,String pname,int quantity,String carname){
    if(foundp(catname,pname)&&(quantity<=categories.get(indexes[0]).products.get(indexes[1]).quantity)){

            categories.get(indexes[0]).products.get(indexes[1]).quantity-=quantity;
            this.carname=carname;
            return true;

    }
    return false;
}
public void installproduct(){
    if(!inU.type.equals("Installer")){
        LOGGER.info("Only customers can make an installation request");

        return;}
    try{
        String ygy1="Choose a Category to request a product\n"+showallcatogries();
        LOGGER.info(ygy1);

        int cselect= scanner.nextInt();  scanner.nextLine();
        cselect--;
        if(categories.get(cselect).products.isEmpty()){
            LOGGER.info("There is no products to request");
            return;
        }
        String catname=categories.get(cselect).name;
        String ygy5="Choose a product to request\n"+getallproducts(catname);
        LOGGER.info(ygy5);

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
            String ygy9="The request will be installed successfully\n" +
                    "Your FEE is "+qu*categories.get(cselect).products.get(pselect).price+"\n";
            LOGGER.info(ygy9);

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



            String recipientEmail = inU.getEmail(); // Replace with the recipient's email
            String subject = "Installation Request";


            Mailing m1=new Mailing(recipientEmail);
            m1.sendEmail(subject,message);
        }

}catch (Exception e){
        LOGGER.info(NEXT_TIME);

    }}

 public void rateReview(String catname, String pname, int rate, String review){
    if(foundp(catname,pname)&&rate>0&&rate<6){

categories.get(indexes[0]).products.get(indexes[1]).rates.add(rate);
int sum=0;
for(int i:categories.get(indexes[0]).products.get(indexes[1]).rates){
   sum +=i;
}
categories.get(indexes[0]).products.get(indexes[1]).rateAvg =(float)sum/categories.get(indexes[0]).products.get(indexes[1]).rates.size();
            categories.get(indexes[0]).products.get(indexes[1]).reviews.add(review);

    }

 }
 public void newrate(){
     if(!inU.type.equals("Customer")){
         LOGGER.info("Only customers can rate and review\n");
         return;}
     try{
         String ygy1="Choose a Category to rate and review a product\n"+showallcatogries();
         LOGGER.info(ygy1);

         int cselect=scanner.nextInt();  scanner.nextLine();
         cselect--;
         if(categories.get(cselect).products.isEmpty()){
             LOGGER.info("There is no products to rate or to review in this Category");
             return;
         }
         String catname=categories.get(cselect).name;
         String ygy2="Choose a product to rate and review\n"+getallproducts(catname);
         LOGGER.info(ygy2);

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
         if (!review.isEmpty()) {
             rateReview(catname, pname, rate, review);
             LOGGER.info("The new rate is added,The new review is added\n");
         } else {
             LOGGER.info(NEXT_TIME);

         }
     }
     catch (Exception e){
         LOGGER.info(NEXT_TIME);

     }
 }
 public String reviews(String catname,String pname){
    if(foundp(catname,pname)){
     StringBuilder f= new StringBuilder();
     int c;
     for (int i=0;i<categories.get(indexes[0]).products.get(indexes[1]).reviews.size();i++){
        c=i+1;
        f.append("Rate number ").append(c).append(" :").append(categories.get(indexes[0]).products.get(indexes[1]).rates.get(i));
        f.append("\nReview number ").append(c).append(" :").append(categories.get(indexes[0]).products.get(indexes[1]).reviews.get(i)).append("\n\n\n");
     }
     f.append(" the Average Rate is :").append(categories.get(indexes[0]).products.get(indexes[1]).rateAvg);
     return f.toString();
    }
    return "";
 }
 public void showreviews(){
     if(!inU.type.equals(ADMIN)){
         LOGGER.info("Only Admins can get informations\n");
         return;}
     try{
         String ygy1="Choose a Category to get informations about a product\n"+showallcatogries();
         LOGGER.info(ygy1);

         int cselect= scanner.nextInt();  scanner.nextLine();
         cselect--;
         if(categories.get(cselect).products.isEmpty()){
             LOGGER.info("There is no products to get informations\n");
             return;
         }
         String catname=categories.get(cselect).name;
         String ygy2="Choose a product to get informations\n"+getallproducts(catname);
         LOGGER.info(ygy2);

         int pselect= scanner.nextInt();  scanner.nextLine();
         pselect--;
         String pname=categories.get(cselect).products.get(pselect).name;
         String message= reviews(catname,pname)+"\n";
         if(message.isEmpty()){
             LOGGER.info("The Choosed product doesnt have any rate or review\n");
             return;
         }

         LOGGER.info(message);
     }
     catch (Exception e){
         LOGGER.info(NEXT_TIME);
     }
 }

    public static boolean printTextToFile(String fileName, String text) {
        try(FileWriter writer = new FileWriter(fileName)) {
            
            writer.write(text);

            return true;

        } catch (IOException ignored) {
            return false;
        }
    }

    public String salesreport() {
        StringBuilder f= new StringBuilder();
        String g="#. Category\tProduct\tQuantity\tFee\tCar\tSent-date\tShipped-date\n";
        f.append(g);
        for(int i=0;i<sales.size();i++){
            int c=i+1;
            f.append(c).append(". ").append(sales.get(i).catname).append("\t").append(sales.get(i).pname).append("\t").append(sales.get(i).quantity).append("\t").append(sales.get(i).fee).append("\t").append(sales.get(i).carname).append("\t").append(sales.get(i).sent).append("\t").append(sales.get(i).shipped).append("\t").append("\n");
        }
        if(f.toString().equals(g))return NO_INFORMATIONS;
        return f.toString();
    }
    public String ratesReport(){
        StringBuilder f= new StringBuilder();
        String g="#. product\tRate\n";
        int c=1;
        f.append(g);
        for (Category category : categories) {

            for (int j = 0; j < category.products.size(); j++) {

                f.append(c).append(". ").append(category.products.get(j).name).append("\t").append(category.products.get(j).rateAvg).append("\n");

                c++;
            }
        }
        if(f.toString().equals(g))return NO_INFORMATIONS;
        return f.toString();
    }
    public String productreport(){
        StringBuilder f= new StringBuilder();
        int c;
        int g;

        for(int i=0;i<categories.size();i++){g=i+1;
            f.append("Category ").append(g).append(" : ").append(categories.get(i).name).append("\n");
            if(categories.get(i).products.isEmpty()){
                f.append("There is no products in this Category\n");
                continue;
            }
            for (int j=0;j<categories.get(i).products.size();j++){
                c=j+1;
                f.append("#. Name     Price     Quantity\n");
                f.append(c).append(". ").append(categories.get(i).products.get(j).name).append("\t").append(categories.get(i).products.get(j).price).append("\t").append(categories.get(i).products.get(j).quantity).append("\n");
            }
        }
        if(f.isEmpty())return NO_INFORMATIONS;
        return f.toString();
    }
    public String ratesReviewsReport(){
        StringBuilder f= new StringBuilder();
        int c;
        int g;
        int b;
        g=0;
        for (Category category : categories) {

            if (category.products.isEmpty()) {
                continue;
            }

            g++;
            f.append("Category ").append(g).append(" : ").append(category.name).append("\n");
            c = 0;

            for (int j = 0; j < category.products.size(); j++) {
                if (category.products.get(j).rates.isEmpty()) {
                    c--;
                    continue;
                }
                c++;
                f.append("Product ").append(c).append(" : ").append(category.products.get(j).name).append("\n");
                for (int z = 0; z < category.products.get(j).rates.size(); z++) {
                    b = z + 1;
                    f.append("Rate number ").append(b).append(" : ").append(category.products.get(j).rates.get(z)).append("\n");
                    f.append("Review number ").append(b).append(" :\n").append(category.products.get(j).reviews.get(z)).append("\n");
                }
            }
        }
        if(f.isEmpty())return NO_INFORMATIONS;
        return f.toString();
    }
public boolean report(String report, String filename) {
    return switch (report) {
        case "Sales" -> printTextToFile(filename, salesreport());
        case "Product rates" -> printTextToFile(filename, ratesReport());
        case "Category products" -> printTextToFile(filename, productreport());
        case "rates and reviews" -> printTextToFile(filename, ratesReviewsReport());
        default -> false;
    };
}

public void makereport() {
    if (inU.getType().equals(ADMIN)) {
        try {
            LOGGER.info("What is the name of the file?");
            String file = scanner.next();

            LOGGER.info("""
        Choose a report
        1. Sales report
        2. Product rates report
        3. Category products report
        4. Rates and reviews report""");

            int c = scanner.nextInt();
            scanner.nextLine();

            switch (c) {
                case 1 -> report("Sales", file);
                case 2 -> report("Product rates", file);
                case 3 -> report("Category products", file);
                case 4 -> report("rates and reviews", file);
                default -> throw new Exception();
            }
        } catch (Exception e) {
            LOGGER.info(NEXT_TIME);
        }
    }
}


    }


