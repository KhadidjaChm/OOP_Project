package models;

public class Application {
    public enum Status {PENDING, ACCEPTED,REJECTED}
    private int id;
    private Student student;
    private InternshipOffer offer;
    private Status status;
    public Application(int id, Student student, InternshipOffer offer){
        this.id = id;
        this.student = student;
        this.offer = offer;
        this.status = Status.PENDING;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status newStatus){
        this.status = newStatus;
    }
    public Student getStudent(){
        return student;
    }
    public InternshipOffer getOffer(){
        return offer;
    }
}
