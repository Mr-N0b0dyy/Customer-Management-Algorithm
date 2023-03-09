import java.util.ArrayList;

public class Customerinfo {
    private String adres; // Address of the customer.
    private String adSoyad; // Name and surname of the customer.
    ArrayList<String> phoneNumber = new ArrayList<String>(); // Phone number or numbers of the customer.
    public Customerinfo (String adres, String adSoyad, ArrayList<String> phoneNumber ) { // Constructor
    setAdres(adres);
    setAdSoyad(adSoyad);
    setPhoneNumber(phoneNumber);
    }
    //Mutator Accessor methods
    public String getAdres() {
        return adres;
    }
    public String getAdSoyad() {
        return adSoyad;
    }
    public ArrayList<String> getPhoneNumber() {
        return phoneNumber;
    }
    public void setAdres(String adres) {
        this.adres = adres;
    }
    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }
    public void setPhoneNumber(ArrayList<String> phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getSurname(String option) {
    if (option.equals("a")){
        // Creates a new list for customer names and gets the last string out of it.
        String[] forSurname = this.getAdSoyad().split(" "); //(Which is surname)
        return forSurname[forSurname.length - 1];
    }
    else {return this.getFirstName();} //In the case of option b changes meyhod to getFirstName
    }
    // Gets first name without surname
    //(This is needed because of the possibility that there could be 2 people that have same first names and surnames
    // but one could have a middle name. For example "Ali Can Kara" and "Ali Kara")
    public String getFirstName(){
        String[] names = this.getAdSoyad().split(" ");
        names[names.length-1] = "";
        StringBuilder firstNames = new StringBuilder();
        for(String name : names){
            firstNames.append(name).append(" ");
        }
        return firstNames.toString();
    }
    @Override
    public String toString() {
        //Converts all numbers into one line of string, than coverts every sting in one paragraph.
        StringBuilder phone_numbers = new StringBuilder();
        for(String a : phoneNumber) {
            phone_numbers.append(" ").append(a).append("\n");
        }
        return  " Name and surname of the customer: " + adSoyad + "\n" +
                " Address of the customer: " + adres + "\n" +
                " Phone number or numbers of the customer: " + phone_numbers;
    }
}