package models;

public class InternshipOffer {
    private int id;
    private String title;
    private String description;
    private Company company;
    public InternshipOffer(int id, String title,String description, Company company){
        this.id = id;
        this.title = title;
        this.description = description;
        this.company= company;

    }
    public Company getCompany(){
        return company;
    }
    public String getTitle(){
        return title;
    }
    public String getDescription(){
        return description;
    }
}
