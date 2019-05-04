package it.dev.cleto.polygon.village;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Rectangle extends Polygon {

    private int base;
    private int height;

    @Override
    public int calculateArea() {
        return base * height;
    }

    @Override
    public String whoIAm() {
        return "I'am a " + toString() + " " + calculateArea();
    }

}
