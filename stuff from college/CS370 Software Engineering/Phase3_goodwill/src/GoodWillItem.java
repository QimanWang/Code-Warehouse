import java.io.Serializable;
import java.lang.String;

//this class hold the values for the goodwill object
public class GoodWillItem implements Serializable{

    String number;
    String name;
    String desc;
    double price;
    String seller;
    String date;

    //empty constructor
    public GoodWillItem(){

    }
    //constructor
    public GoodWillItem(String number, String name, String desc, double price, String seller, String date) {
        this.number = number;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.seller = seller;
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "GoodWillItem{" +
                "number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", price=" + price +
                ", seller='" + seller + '\'' +
                ", date=" + date +
                '}';
    }
}//class
