package hr.foi.air.core;

import hr.foi.air.core.entities.Igrac;

public interface LogiranjeDataLoadedListener {

    void onIgracLogiran(Igrac igrac);

    void onZauzetoKorisIme(String poruka);
}
