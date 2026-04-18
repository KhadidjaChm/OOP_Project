package models;
import java.util.ArrayList;
import java.util.List;

public class Company {
    private int id;
    private String name;
    private List<InternshipOffer> offers = new ArrayList<>();
    public Company(int id, String name){
        this.id = id;
        this.name = name;
    }
    public void addOffer(InternshipOffer offer){
        offers.add(offer);
    }
    public List<InternshipOffer> getOffers(){
        return offers;
    }
    public String getName(){
        return name;
    }
}
