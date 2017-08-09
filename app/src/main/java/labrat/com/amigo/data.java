package labrat.com.amigo;

/**
 * Created by garvi on 5/6/2017.
 */
public class data {
    String name;
    String address;
    String des;
    String ph;


    public data(String name, String des, String address, String ph) {
        this.name = name;

        this.address = address;
        this.des = des;
        this.ph = ph;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getDes() {
        return des;
    }

    public String getPh() {
        return ph;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }
}

