package sample;

public class car {

    private String Model;
    private int year;
    private int mileage;
    private String color;

    public car(String model, int year, int mileage, String color) {
        Model = model;
        this.year = year;
        this.mileage = mileage;
        this.color = color;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
