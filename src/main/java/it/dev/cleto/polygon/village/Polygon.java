package it.dev.cleto.polygon.village;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Polygon {

    public static final String SQUARE = "SQUARE";
    public static final String RECTANGLE = "RECTANGLE";

    private int base;
    private int hight;
    private String name = "";

    public Polygon(int side) {
        this.base = side;
        this.hight = side;
        this.name = giveMeAName();
    }

    public Polygon(int low, int hight) {
        this.base = low;
        this.hight = hight;
        this.name = giveMeAName();
    }

    public int calculateArea() {
        return base * hight;
    }

    public String whoIAm() {
        return name + " " + calculateArea();
    }

    public int getMinSide() {
        return base > hight ? hight : base;
    }

    private String giveMeAName() {
        return "Sig. " + getType() + "_" + Utils.getRandomName();
    }

    private String getType() {
        return isASquare() ? SQUARE : RECTANGLE;
    }

    private boolean isASquare() {
        return base == hight;
    }

}
