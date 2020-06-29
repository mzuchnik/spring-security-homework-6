package pl.mzuchnik.springsecurityhomework6.model;

public class Car {

    private String name;
    private CarColor carColor;

    public Car() {
    }

    public Car(String name, CarColor carColor) {
        this.name = name;
        this.carColor = carColor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CarColor getCarColor() {
        return carColor;
    }

    public void setCarColor(CarColor carColor) {
        this.carColor = carColor;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", carColor=" + carColor +
                '}';
    }
}


