package car_accessories;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;
import java.util.logging.*;



public  class Application {

    private static final String NO_INFORMATIONS ="There is no informations";


    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

    String carname;
    boolean loggedIn;

    Login login;
 static List<Sales> sales=new ArrayList<>();
    static ArrayList<Category> categories;
    User newUser;
    static int[] indexes=new int[2];
    Scanner scanner = new Scanner(System.in);

    public Application(){
        categories=new ArrayList<>();

        try {
            LOGGER.setUseParentHandlers(false);

            Handler[] handlers = LOGGER.getHandlers();
            for (Handler handler : handlers) {
                LOGGER.removeHandler(handler);
            }

            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.INFO);
            consoleHandler.setFormatter(new SimpleFormatter() {
                @Override
                public synchronized String format(java.util.logging.LogRecord logRecord) {
                    return logRecord.getMessage() + "\n";
                }
            });

            LOGGER.addHandler(consoleHandler);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An unexpected error occurred during logger configuration", e);
        }


        carname="";

        this.loggedIn = false;
        login=new Login(newUser);

        categories.add(new Category("Interior"));
        categories.get(0).products.add((new Product("Vacuum Cleaner",15,50,2027)));
        categories.add(new Category("Exterior"));
        categories.add(new Category("Electronics"));
        categories.get(2).products.add(new Product("car lights",13,50,2025));
        newUser=new User("ibrahim.sadi.asad@gmail.com","147852","Customer");
        categories.add(new Category("any"));

    }








    public boolean foundc(String name){

        int zero =0;
        for(int i=0;i<categories.size();i++){
            if(name.equals(categories.get(i).name)){
                indexes[zero]=i; return true;
            }
        }
        return false;
    }
    public void setuser(String email,String pass ,String type){

        newUser=new User(email,pass,type);
        login.setUser(newUser);
    }
    public void addcat(String name){
        categories.add(new Category(name));
    }


    public void edtcatogry(String oldn,String newn){
        for (Category category : categories) {
            if (category.name.equals(oldn)) {
                category.name = newn;
                break;
            }
        }
    }



    public void dltcat(String name){
        for(int i=0;i<categories.size();i++){
            if(name.equals(categories.get(i).name)){
                categories.remove(i);break;
            }
        }
    }





    public boolean foundp(String catname,String pname){
        for(int i=0;i<categories.size();i++){
            if(catname.equals(categories.get(i).name)){
                for(int j=0;j< categories.get(i).products.size();j++){
                    if(pname.equals(categories.get(i).products.get(j).name))
                    {
                        set(i,j);return  true;}
                }
                return false;
            }
        }
        return false;
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

    public void editproduct(String catname,String pname,String newname,int newprice){
        if(foundp(catname,pname)){
            categories.get(indexes[0]).products.get(indexes[1]).name=newname;
            categories.get(indexes[0]).products.get(indexes[1]).price = newprice;
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



public int ratesqu(String catname, String pname){
     foundp(catname, pname)   ;
     return categories.get(indexes[0]).products.get(indexes[1]).rates.size();
}
private static void set(int x1,int x2){
        indexes[0]=x1;
        indexes[1]=x2;
}
}


