package it.dev.cleto.polygon.village;


import lombok.Data;
import lombok.extern.log4j.Log4j;

import java.util.ArrayList;

@Data
@Log4j
public class PolygonVillage {
    final int QMIN = 2;
    final int QMAX = 10;

    int numberOfPopulation;
    ArrayList<Polygon> polygons;

    public PolygonVillage(int numberOfPopulation) {
        this.numberOfPopulation = numberOfPopulation;
        polygons = new ArrayList<>();
        createPopulation(numberOfPopulation);
    }

    private void createPopulation(int numberOfPopulation) {
        for (int i = 0; i < numberOfPopulation; i++) {
            polygons.add(new Polygon(Utils.getRandomNumberInRange(QMIN, QMAX)));
        }
    }

    private void printPopulation() {
        log.info("\n\nPolygonVillage: " + polygons.size());
        for (Polygon polygon : polygons) {
            log.info(polygon.whoIAm());
        }
    }

    private void letsMeet(int meetInADay) {
        log.info("letsMeet");
        for (int i = 0; i < meetInADay; i++) {
            Polygon first = pickUpOne();
            Polygon second = pickUpOne();
            log.info("first:  " + first.whoIAm());
            log.info("second: " + second.whoIAm());
            Polygon child = createChild(first, second);
            log.info("child:  " + child.whoIAm());
            polygons.add(first);
            polygons.add(second);
            polygons.add(child);
            log.info("---------------");
        }
    }

    private Polygon createChild(Polygon first, Polygon second) {
        int sideOne = getAbsoluteMinSide(first, second);
        int sideTwo = Utils.getSumSide(first, second);
        if (Utils.getRandomNumberInRange(0, 1) == 1)
            return new Polygon(sideOne, sideTwo);
        return new Polygon(sideTwo, sideOne);
    }

    private int getAbsoluteMinSide(Polygon man, Polygon woman) {
        return man.getMinSide() > woman.getMinSide() ? woman.getMinSide() : man.getMinSide();
    }

    private Polygon pickUpOne() {
        return polygons.remove(Utils.getRandomNumberInRange(0, polygons.size() - 1));
    }

    public void execute(int numberOfDay, int childMin, int childMax) {
        printPopulation();
        for (int i = 0; i < numberOfDay; i++) {
            log.info("\n\n --> day: " + (i + 1) + " population: " + polygons.size());
            letsMeet(Utils.getRandomNumberInRange(childMin, childMax));
        }
        printPopulation();
    }

    public static void main(String[] args) {

        final int NUMBER_OF_POPULATION = 20;
        final int CHILD_MIN = 1;
        final int CHILD_MAX = 4;

        PolygonVillage polygonVillage = new PolygonVillage(NUMBER_OF_POPULATION);
        polygonVillage.execute(3, CHILD_MIN, CHILD_MAX);
    }

}
