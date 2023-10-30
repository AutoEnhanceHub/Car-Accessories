package carAccessories;



import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;


public  class Application {

String carname;
    boolean logged_in;
     User user;
    Login login;
    static ArrayList<Sales> sales;

    User newUser;
    SignUp signUp;
static int[] indexes;

public Application(User user){
    newUser=user;
    login=new Login(newUser);
}

public Application(String email,String password){
    user=new User(email,password);
    login=new Login(user);
}
    public Application() {
        carname="";
        sales=new ArrayList<Sales>();
        user=new User("s12116027@stu.najah.edu","123","Admin");
        indexes=new int[2];
        this.logged_in = false;
        login=new Login(user);
        categories=new ArrayList<Category>();
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
    public static ArrayList<Category> categories;


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
public void addcat(String name){
    Category n=new Category(name);    categories.add(n);
}
    public void addnewCategory_confirmation(String m){
try {

        int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to continue?", "Confirmation",
                JOptionPane.YES_NO_OPTION // Option type (Yes and No buttons)
        );
        if(response==JOptionPane.YES_OPTION){
            JOptionPane.showMessageDialog(null,"You added the Category "+m,"Adding Category",JOptionPane.INFORMATION_MESSAGE);
           addcat(m); }
        else {
            JOptionPane.showMessageDialog(null,"the Category "+m+" is not added","Adding Category",JOptionPane.INFORMATION_MESSAGE);
        }

}catch (NullPointerException e){
    JOptionPane.showMessageDialog(null,"You cancelled the adding");
} }
    public void newCatogry() {
    if(user.type.equals("Admin")){
        String m = JOptionPane.showInputDialog("What is the name of the Category");
        if (foundc(m)) {

            JOptionPane.showMessageDialog(null, "the Category " + m + " is really exist", "Adding Category", JOptionPane.ERROR_MESSAGE);

        } else addnewCategory_confirmation(m);
    }else{
            JOptionPane.showMessageDialog(null,"Only admins can delete Categories");}

    }
    public void edtcatogry(String oldn,String newn){
    for(int i=0;i<categories.size();i++){
        if(categories.get(i).name.equals(oldn)){
            categories.get(i).name= newn;break;
        }
    }
    }

public void editCategory(){

if(user.type.equals("Admin")){
        if(categories.isEmpty()){
            JOptionPane.showMessageDialog(null,"There is no categories in the system");
        }else{   StringBuilder f= new StringBuilder();
            for(int i=0;i<categories.size();i++){
                f.append(i + 1).append(". ").append(categories.get(i).name).append("\n");
            }



            try {
                String m=JOptionPane.showInputDialog("Choose a Category\n"+f);
                int select=Integer.parseInt(m);
                if(select<1){
JOptionPane.showMessageDialog(null,"Selection error","ERROR",JOptionPane.ERROR_MESSAGE);
                } else if (select>categories.size()) {
JOptionPane.showMessageDialog(null,"Selection error","ERROR",JOptionPane.ERROR_MESSAGE);
                }else{
                    select--;
String rename=JOptionPane.showInputDialog("What is the new name of the Category?");

                if(foundc(rename)){
JOptionPane.showMessageDialog(null,"This new name is for another Category","ERROR",JOptionPane.ERROR_MESSAGE);
                }
                else{
 int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to continue?", "Confirmation",
 JOptionPane.YES_NO_OPTION );
  if(response==JOptionPane.YES_OPTION){
      edtcatogry(categories.get(select).name,rename);

 JOptionPane.showMessageDialog(null,"The Name is edited successfully");
}
 else {
  JOptionPane.showMessageDialog(null,"the Category "+m+" is not Edited","Editing Category",JOptionPane.INFORMATION_MESSAGE);
   }

                }
                }
            }catch (NumberFormatException e){
JOptionPane.showMessageDialog(null,"Selection error","ERROR",JOptionPane.ERROR_MESSAGE);
            }

        }
}else{
        JOptionPane.showMessageDialog(null,"Only admins can Edit Categories");}

}

public void dltcat(String name){
   for(int i=0;i<categories.size();i++){
       if(name.equals(categories.get(i).name)){
           categories.remove(i);break;
       }
   }
}
public void deleteCategory(){
    if(user.type.equals("Admin")){
    if(categories.isEmpty()){
        JOptionPane.showMessageDialog(null,"There is no categories in the system");
    }else{   String f="";
        for(int i=0;i<categories.size();i++){
            f+=i+1+". "+categories.get(i).name+"\n";
        }



        try {
            String m=JOptionPane.showInputDialog("Choose a Category\n"+f);
            int select=Integer.parseInt(m);
            if(select<1){
                JOptionPane.showMessageDialog(null,"Selection error","ERROR",JOptionPane.ERROR_MESSAGE);
            } else if (select>categories.size()) {
                JOptionPane.showMessageDialog(null,"Selection error","ERROR",JOptionPane.ERROR_MESSAGE);
            }else{
                select--;
                int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to continue?", "Confirmation",
                        JOptionPane.YES_NO_OPTION );
                if(response==JOptionPane.YES_OPTION){
                    dltcat(categories.get(select).name);

                }
                else {
                    JOptionPane.showMessageDialog(null,"the Category "+m+" is not Deleted","Deleting Category",JOptionPane.INFORMATION_MESSAGE);
                }

            }
        }catch (NumberFormatException e){
    JOptionPane.showMessageDialog(null,"Selection error","ERROR",JOptionPane.ERROR_MESSAGE);

        }
}}

else{
JOptionPane.showMessageDialog(null,"Only admins can delete Categories");}
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
    String f="";
    if(foundc(catname)){
         if(categories.get(indexes[0]).products.isEmpty()){
            return "There is no products";
        }
         f=f+"#. name     quantity     price     rate\n";
     for(int i=0;i<categories.get(indexes[0]).products.size();i++){
         int c=i+1;
         f=f+c+". "+categories.get(indexes[0]).products.get(i).name+"     "+
                 categories.get(indexes[0]).products.get(i).quantity+"     "+
                 categories.get(indexes[0]).products.get(i).price+"     "+
                 categories.get(indexes[0]).products.get(i).rate_avg+"\n";
     }
    }
   else{
        f=f+"The Category is empty";
    }
    return f;
}
public void showproducts(){


  try {
      int x=Integer.parseInt(JOptionPane.showInputDialog("Choose a Category to see its products\n"+showallcatogries())); x--;

      JOptionPane.showMessageDialog(null,getallproducts(categories.get(x).name));
  }catch (Exception e){
      JOptionPane.showMessageDialog(null,"Enter a valid value in the next time");
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
    if(!user.type.equals("Admin")){
        JOptionPane.showMessageDialog(null,"Only admins can add products");
        return;}
    try{

    int select=Integer.parseInt(  JOptionPane.showInputDialog("Choose a Category to add a new product\n"+showallcatogries()));
    select--;
    String catname=categories.get(select).name;

        String pname=JOptionPane.showInputDialog("What is the name of the new product?");

        int quantity=Integer.parseInt(JOptionPane.showInputDialog("What is the quantity of the new product?"));
        if(quantity<1){
            throw new InputMismatchException();
        }
        if(foundp(catname,pname)){
            addnewproduct(catname,pname,quantity,0,0);
            JOptionPane.showMessageDialog(null,"Thr quantity is added to the exist product "+pname);

            return;
        }

        int price=Integer.parseInt(JOptionPane.showInputDialog("what is the price of this new product?"));
        if(price<1){
            throw new InputMismatchException();
        }
        int year=Integer.parseInt(JOptionPane.showInputDialog("Which year this product will be expired?"));
        if(year<=LocalDate.now().getYear()){
            throw new InputMismatchException();
        }
        addnewproduct(catname,pname,quantity,price,year);
        JOptionPane.showMessageDialog(null,"The product is added Successfully");


}catch (Exception e){
        JOptionPane.showMessageDialog(null,"Enter a valid value in the next time");
    }
}
public void editproduct(String catname,String pname,String newname,int newprice){
    if(foundp(catname,pname)){
        categories.get(indexes[0]).products.get(indexes[1]).name=newname;
        categories.get(indexes[0]).products.get(indexes[1]).price = newprice;
    }
}
public void editproduct(){
    if(!user.type.equals("Admin")){
        JOptionPane.showMessageDialog(null,"Only admins can edit products");
        return;}
    try{

        int cselect=Integer.parseInt(  JOptionPane.showInputDialog("Choose a Category to edit\n"+showallcatogries()));
        cselect--;
        if(categories.get(cselect).products.isEmpty()){
            JOptionPane.showMessageDialog(null,"There is no products to edit");
            return;
        }
        String catname=categories.get(cselect).name;

        int pselect=Integer.parseInt(JOptionPane.showInputDialog("Choose a product to edit\n"+getallproducts(catname)));
        pselect--;
        String old=categories.get(cselect).products.get(pselect).name;
        String newname=JOptionPane.showInputDialog("What is the new name of the product "+old+"?");

for(int i=0;i<categories.get(cselect).products.size();i++){
    if(i==pselect)continue;
    if(newname.equals(categories.get(cselect).products.get(i).name)){
        throw new Exception();
    }
}
       int newprice=Integer.parseInt(JOptionPane.showInputDialog("What is the new price of the product "+old+"?"));
       if(newprice<1){
           throw new Exception();
       }if(newname.isEmpty()){
            editproduct(catname,old,old,newprice);
        }else{
            editproduct(catname,old,newname,newprice);
        }

       JOptionPane.showMessageDialog(null,"The product is updated successfully");
    }
   catch (Exception e){
        JOptionPane.showMessageDialog(null,"Enter a valid value in the next time");
    }
}
public void deleteproduct(){
    if(!user.type.equals("Admin")){
        JOptionPane.showMessageDialog(null,"Only admins can delete products");
        return;}
    try{

        int cselect=Integer.parseInt(  JOptionPane.showInputDialog("Choose a Category to delete a product\n"+showallcatogries()));
        cselect--;
        if(categories.get(cselect).products.isEmpty()){
            JOptionPane.showMessageDialog(null,"There is no products to remove");
            return;
        }
        String catname=categories.get(cselect).name;

        int pselect=Integer.parseInt(JOptionPane.showInputDialog("Choose a product to delete\n"+getallproducts(catname)));
        pselect--;

        categories.get(cselect).products.remove(pselect);

    }
    catch (Exception e){
        JOptionPane.showMessageDialog(null,"Enter a valid value in the next time");
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
    if(!user.type.equals("Customer")){
        JOptionPane.showMessageDialog(null,"Only customers can make an installation request");
        return;}
    try{

        int cselect=Integer.parseInt(  JOptionPane.showInputDialog("Choose a Category to request a product\n"+showallcatogries()));
        cselect--;
        if(categories.get(cselect).products.isEmpty()){
            JOptionPane.showMessageDialog(null,"There is no products to request");
            return;
        }
        String catname=categories.get(cselect).name;

        int pselect=Integer.parseInt(JOptionPane.showInputDialog("Choose a product to request\n"+getallproducts(catname)));
        pselect--;
        String pname=categories.get(cselect).products.get(pselect).name;
        if(categories.get(cselect).products.get(pselect).quantity==0){
            JOptionPane.showMessageDialog(null,"The product is not enough to buy!");
            return;
        }
        int qu=Integer.parseInt(JOptionPane.showInputDialog("Select the quantity"));
        String car=JOptionPane.showInputDialog("What is the car name?");
        if(installrequest(catname,pname,qu,carname)){
            int fee=qu*categories.get(cselect).products.get(pselect).price;
            JOptionPane.showMessageDialog(null,"The request will be installed successfully\n" +
                    "Your FEE is "+qu*categories.get(cselect).products.get(pselect).price);
            if(categories.get(cselect).products.get(pselect).quantity==0){
                categories.get(cselect).products.remove(pselect);
            }
            Random random = new Random();


            int randomNumber = random.nextInt(5) + 1;
            LocalDate ship=LocalDate.now().plusDays(randomNumber);
            String message="Your order has been received and is currently being processed. " +
                    " The order is going to be shipped after ." +ship+
                    ". Thank you for shopping with us!\n" +
                    "Best regards,";
            sales.add(new Sales(catname,pname,fee,qu,LocalDate.now(),ship,car));



            String recipientEmail = user.getEmail(); // Replace with the recipient's email
            String subject = "Installation Request";


            Mailing m1=new Mailing(recipientEmail);
            m1.sendEmail(subject,message);
        }

}catch (Exception e){
        JOptionPane.showMessageDialog(null,"Enter a valid value in the next time");
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
     if(!user.type.equals("Customer")){
         JOptionPane.showMessageDialog(null,"Only customers can rate and review");
         return;}
     try{

         int cselect=Integer.parseInt(  JOptionPane.showInputDialog("Choose a Category to rate and review a product\n"+showallcatogries()));
         cselect--;
         if(categories.get(cselect).products.isEmpty()){
             JOptionPane.showMessageDialog(null,"There is no products to rate or to review in this Category");
             return;
         }
         String catname=categories.get(cselect).name;

         int pselect=Integer.parseInt(JOptionPane.showInputDialog("Choose a product to rate and review\n"+getallproducts(catname)));
         pselect--;
         String pname=categories.get(cselect).products.get(pselect).name;
         int rate= Integer.parseInt(JOptionPane.showInputDialog("How much is the adding rate?  1-5\n"));
         if(rate<1||rate>5){
             throw new Exception();
         }
         String review=JOptionPane.showInputDialog("Write a new review");
         if(review.isEmpty())throw new Exception();
         rate_review(catname,pname,rate,review);
         JOptionPane.showMessageDialog(null,"The new rate is added,The new review is added");
     }
     catch (Exception e){
         JOptionPane.showMessageDialog(null,"Enter a valid value in the next time");
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
     if(!user.type.equals("Admin")){
         JOptionPane.showMessageDialog(null,"Only Admins can get informations");
         return;}
     try{

         int cselect=Integer.parseInt(  JOptionPane.showInputDialog("Choose a Category to get informations about a product\n"+showallcatogries()));
         cselect--;
         if(categories.get(cselect).products.isEmpty()){
             JOptionPane.showMessageDialog(null,"There is no products to get informations");
             return;
         }
         String catname=categories.get(cselect).name;

         int pselect=Integer.parseInt(JOptionPane.showInputDialog("Choose a product to get informations\n"+getallproducts(catname)));
         pselect--;
         String pname=categories.get(cselect).products.get(pselect).name;
         String message= reviews(catname,pname);
         if(message.isEmpty()){
             JOptionPane.showMessageDialog(null,"The Choosed product doesnt have any rate or review");
             return;
         }
         JOptionPane.showMessageDialog(null,message);
     }
     catch (Exception e){
         JOptionPane.showMessageDialog(null,"Enter a valid value in the next time");
     }
 }

    public static boolean printTextToFile(String fileName, String text) {
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(text);
            writer.close();
            return true;

        } catch (IOException ignored) {

        }
        return false;
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
    public boolean report(String report,String filename){

        switch (report) {
            case "Sales" -> {
                printTextToFile(filename, Salesreport());
                return true;
            }
            case "Product rates" -> {
                printTextToFile(filename, Ratesreport());
                return true;
            }
            case "Category products" -> {
                printTextToFile(filename, productreport());
                return true;
            }
            case "rates and reviews" -> {
                printTextToFile(filename, Ratesreviewsreport());
                return true;
            }
        }
        return false;
    }
    public void makereport(){
        try {
            String file=JOptionPane.showInputDialog("What is the name of the file?");
            int c;
            c=Integer.parseInt(JOptionPane.showInputDialog("Choose a report\n1. Sales report\n2. Product rates report\n" +
                    "3. Category products report\n4. rates and reviews report"));

            switch (c){
                case 1:report("Sales",file);break;
                case 2:report("Product rates",file);break;
                case 3:report("Category products",file);break;
                case 4:report("rates and reviews",file);break;
                default:throw new Exception();
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Enter a valid value in the next time");
        }


    }

}
