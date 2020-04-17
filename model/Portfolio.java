package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Portfolio extends Observable implements IPortfolio{

    List<Folio> allFolio;
    public Portfolio(){

        allFolio = new ArrayList<>();


    }

    public List<Folio> getAllFolio(){
        return allFolio;
    }

    public void addFolio(Folio folio) {
        allFolio.add(folio);
        setChanged();
        notifyObservers();
    }

}
