package hr.foi.air.core;

import java.util.List;

import hr.foi.air.core.entities.Igrac;

//ovo sučelje implementiraju oni koji su poslali zahtijev za podacima
public interface DataLoadedListener {
    void onIgraciLoaded(List<Igrac> igraci);

}
