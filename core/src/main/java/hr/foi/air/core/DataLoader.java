package hr.foi.air.core;

//sučelje koje implementira WsDataLoader i u kojem trebaju biti sve metode za
//traženje ili slanje podataka
public interface DataLoader {
    void logiranjeData(LogiranjeDataLoadedListener listener, String username, String password); //Async

    void loadData(DataLoadedListener listener); //Async

    void slanjeZahtijevaZaIgrom(DataLoadedListener listener); //Async





    boolean isDataLoaded();
}
