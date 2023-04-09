package backend;



public class Controller {

    //this will be our main function. This will speak to everything else.
    //this class will class on the others in order to do stuff.

    public static void main(String[] args) {
        System.out.println("hello world");
        //declaration of the AccountDatabase class
        AccountDatabase accountDatabase = new AccountDatabase();
        //this calls the xml function from Account database to load the csv
        accountDatabase.load();




    }
}
