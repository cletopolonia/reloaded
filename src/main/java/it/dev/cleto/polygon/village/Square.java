package it.dev.cleto.polygon.village;

import lombok.Data;

@Data
public class Square extends Rectangle {

    private int side;

    public Square(int side) {
        super(side, side);
        this.side = side;
    }

}
