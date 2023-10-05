package carAccessories;

import java.util.ArrayList;

public class Category {
public String name;
public ArrayList<product> products;
 public Category(String name){
     this.name=name;
     products=new ArrayList<product>();
 }
}
