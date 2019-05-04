package it.dev.cleto.polygon.village;


import lombok.Data;
import lombok.extern.log4j.Log4j;

import java.util.ArrayList;

@Data
@Log4j
public class PolygonVillage {
    final int QMIN = 2;
    final int QMAX = 10;

    int meetInADay;
    int numberOfPopulation;
    ArrayList<Polygon> polygons;

    public PolygonVillage(int meetInADay, int numberOfPopulation) {
        this.meetInADay = meetInADay;
        this.numberOfPopulation = numberOfPopulation;
        polygons = new ArrayList<>();
        createPopulation(numberOfPopulation);
    }

    private void createPopulation(int numberOfPopulation) {
        for (int i = 0; i < numberOfPopulation; i++) {
            polygons.add(new Square(Utils.getRandomNumberInRange(QMIN, QMAX)));
        }
    }

    private void printPopulation() {
        log.info("\n\nPolygonVillage: " + polygons.size());
        for (Polygon polygon : polygons) {
            log.info(polygon.whoIAm());
        }
    }

    private void letsMeet() {
        log.info("letsMeet");
        for (int i = 0; i < meetInADay; i++) {
            Square squareOne = pickUpOne();
            Square squareTwo = pickUpOne();
            int lowSide = getMinSide(squareOne, squareTwo);
            int heightSide = squareOne.getSide() + squareTwo.getSide();
            Rectangle rectangle = Utils.createRectangle(lowSide, heightSide);
            log.info(rectangle.whoIAm());
            polygons.add(rectangle);
        }
    }

    private int getMinSide(Square square1, Square square2) {
        return square1.getSide() > square2.getSide() ? square2.getSide() : square1.getSide();
    }

    private Square pickUpOne() {
        Polygon polygon = null;
        boolean squareNotFound = true;
        while (squareNotFound) {
            int randomNumber = Utils.getRandomNumberInRange(0, polygons.size() - 1);
            polygon = polygons.get(randomNumber);
            if (polygon.isASquare())
                squareNotFound = false;
        }
        return (Square) polygon; // assunzione che i 2 quadrati che si incontrato non "muoiono" per dare vita ad un rettangolo.
    }

    // versione ricorsiva
    private Square pickUpOneRicor() {
        int randomNumber = Utils.getRandomNumberInRange(0, polygons.size() - 1);
        Polygon polygon = polygons.get(randomNumber);
        if (polygon.isASquare())
            return (Square) polygon; // assunzione che i 2 quadrati che si incontrato non "muoiono" per dare vita ad un rettangolo.
        return pickUpOne();
    }

    public void execute(int numberOfDay) {
        printPopulation();
        for (int i = 0; i < numberOfDay; i++) {
            log.info("\n\n --> day: " + (i + 1) + " population: " + polygons.size());
            letsMeet();
        }
        printPopulation();
    }

    public static void main(String[] args) {

        final int MEET_IN_A_DAY = 1;
        final int NUMBER_OF_POPULATION = 18;

        PolygonVillage polygonVillage = new PolygonVillage(MEET_IN_A_DAY, NUMBER_OF_POPULATION);
        polygonVillage.execute(4);
    }

}
