package carAccessories;

import javax.swing.*;
import java.util.ArrayList;

public  class Application {

    boolean logged_in;

    public Application() {
        this.logged_in = false;
        categories=new ArrayList<Category>();
        categories.add(new Category("Interior"));
        categories.add(new Category("Exterior"));
        categories.add(new Category("Electronics"));

    }

    public boolean isLogged_in() {
        return logged_in;
    }

    public void setLogged_in(boolean logged_in) {
        this.logged_in = logged_in;
    }
    public static ArrayList<Category> categories;
    public static String email,pass;

public void showallcatogries(){
    String f="";
    for(int i=0;i<categories.size();i++){
        f+=i+1+". "+categories.get(i).name+"\n";
    }
    JOptionPane.showMessageDialog(null,f);
}

public boolean foundc(String name){


    for(int i=0;i<categories.size();i++){
        if(name.equals(categories.get(i).name)){
           return true;
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
//    if(User.type.equals("Admin")){
        String m = JOptionPane.showInputDialog("What is the name of the Category");
        if (foundc(m)) {

            JOptionPane.showMessageDialog(null, "the Category " + m + " is really exist", "Adding Category", JOptionPane.ERROR_MESSAGE);

        } else addnewCategory_confirmation(m);
//    }else{
//            JOptionPane.showMessageDialog(null,"Only admins can delete Categories");}
//    }
    }
    public void edtcatogry(String oldn,String newn){
    for(int i=0;i<categories.size();i++){
        if(categories.get(i).name.equals(oldn)){
            categories.get(i).name=new String(newn);break;
        }
    }
    }

public void editCategory(){

//if(User.type.equals("Admin")){
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
//}else{
//        JOptionPane.showMessageDialog(null,"Only admins can Edit Categories");}
//}
}

public void dltcat(String name){
   for(int i=0;i<categories.size();i++){
       if(name.equals(categories.get(i).name)){
           categories.remove(i);break;
       }
   }
}
public void deleteCategory(){
  //  if(User.type.equals("Admin")){
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
//}
//else{
//JOptionPane.showMessageDialog(null,"Only admins can delete Categories");}
//}
}
