package it.dev.cleto.polygon.village;

public abstract class Polygon {
    abstract int calculateArea();

    abstract String whoIAm();

    public boolean isASquare() {
        return (this instanceof Square);
    }
}
