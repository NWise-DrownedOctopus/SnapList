package model;

public class User {
    private double id;
    private String name;

    public User(){};

    public double getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(double id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
