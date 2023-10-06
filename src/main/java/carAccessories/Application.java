package carAccessories;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public  class Application {
Scanner input=new Scanner(System.in);
    boolean logged_in;
    User user;
    Login login;
static int[] indexes;
    public Application() {
        indexes=new int[2];
        this.logged_in = false;
        login=new Login(user);
        categories=new ArrayList<Category>();
        categories.add(new Category("Interior"));
        categories.get(0).products.add((new product("Vacuum Cleaner",15,50,2027)));
        categories.add(new Category("Exterior"));
        categories.add(new Category("Electronics"));
        categories.get(2).products.add(new product("car lights",13,50,2025));

    }

    public boolean isLogged_in() {
        return logged_in;
    }

    public void setLogged_in(boolean logged_in) {
        this.logged_in = logged_in;
    }
    public static ArrayList<Category> categories;
    public static String email,pass;

public String showallcatogries(){
    String f="";
    for(int i=0;i<categories.size();i++){
        f+=i+1+". "+categories.get(i).name+"\n";
    }
//    JOptionPane.showMessageDialog(null,f);
    return f;
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
            categories.get(i).name=new String(newn);break;
        }
    }
    }

public void editCategory(){

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
            f=f+"There is no products";
        }
     for(int i=0;i<categories.get(indexes[0]).products.size();i++){
         int c=i+1;
         f=f+c+". "+categories.get(indexes[0]).products.get(i).name+"     "+
                 categories.get(indexes[0]).products.get(i).quantity+"      "+
                 categories.get(indexes[0]).products.get(i).price+"       "+
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
}
