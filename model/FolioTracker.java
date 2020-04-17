package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class FolioTracker extends Observable implements IFolioTracker {

    List<Folio> allFolio;
    public FolioTracker(){

        allFolio = new ArrayList<>();


    }

    public List<Folio> getAllFolio(){
        return allFolio;
    }

    public boolean addFolio(Folio folio) {


        for (Folio f : allFolio) {
            if (f.getFolioName().equals(folio.getFolioName())) {

                return false;
            }
        }

            allFolio.add(folio);
            setChanged();
            notifyObservers();
            assert allFolio.contains(folio): "folio not added";
            return true;
        }


    public void removeFolio(int index){

    allFolio.remove(index);


    }

    public void deleteFolio(Folio f) {

        allFolio.remove(f);
        assert !allFolio.contains(f): "folio not deleted";
    }
}


