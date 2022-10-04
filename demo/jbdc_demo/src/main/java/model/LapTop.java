package model;

public class LapTop {
    private int id;
    private String name;
    private String producer;
    double price;
    private String color;

    public LapTop() {
    }

    public LapTop(String name, String producer, double price, String color) {
        this.name = name;
        this.producer = producer;
        this.price = price;
        this.color = color;
    }

    public LapTop(int id, String name, String producer, double price, String color) {
        this.id = id;
        this.name = name;
        this.producer = producer;
        this.price = price;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
