package hr.foi.air.krizickruzic.loaders;

import java.util.List;

import hr.foi.air.core.DataLoadedListener;
import hr.foi.air.core.DataLoader;
import hr.foi.air.core.LogiranjeDataLoadedListener;
import hr.foi.air.core.entities.Igrac;
import hr.foi.air.webservice.MyWebserviceCaller;
import hr.foi.air.webservice.handlers.MyWebserviceHandler;

public class WsDataLoader implements DataLoader {
    private DataLoadedListener listener;
    private LogiranjeDataLoadedListener logiranjeListener;
    private List<Igrac> igraci;


    @Override
    public void slanjeZahtijevaZaIgrom(DataLoadedListener listener) {
        this.listener = listener;

        MyWebserviceCaller zahtjevZaIgrom = new MyWebserviceCaller(zahtjevZaIgromHandler);
        zahtjevZaIgrom.slanjeZahtjevaZaIgrom();
    }

    @Override
    public void logiranjeData(LogiranjeDataLoadedListener logiranjeListener, String username, String password) {
        this.logiranjeListener = logiranjeListener;

        MyWebserviceCaller logiranjeNaServer = new MyWebserviceCaller(logiranjeNaServerHandler);
        logiranjeNaServer.logiranjeNaServer(username, password);
    }

    @Override
    public void loadData(DataLoadedListener listener) {
        this.listener = listener;

        MyWebserviceCaller igraciCaller = new MyWebserviceCaller(igraciHandler);
        igraciCaller.getAllPlayers("getAll", Igrac.class);
    }

    @Override
    public boolean isDataLoaded() {
        return false;
    }

    private MyWebserviceHandler igraciHandler = new MyWebserviceHandler() {
        @Override
        public void onDataArrived(Object result, int respondeID, boolean ok, long timeStamp) {
            if (ok){
                igraci = (List<Igrac>) result;
                listener.onIgraciLoaded(igraci);
            }
        }
    };

    private MyWebserviceHandler logiranjeNaServerHandler = new MyWebserviceHandler() {
        @Override
        public void onDataArrived(Object result, int respondeID, boolean ok, long timeStamp) {
            if (ok){
                if (respondeID == 103){
                    Igrac igrac = (Igrac) result;
                    logiranjeListener.onIgracLogiran(igrac);
                }
                if (respondeID == 101){
                    Igrac igrac = (Igrac) result;
                    String poruka = "Email " + igrac.getEmail() + " je zauzet!";
                    logiranjeListener.onZauzetoKorisIme(poruka);
                }
                if (respondeID == 102){
                    Igrac igrac = (Igrac) result;
                    String poruka = "email " + igrac.getEmail() + " nije dobar format email adrese!";
                    logiranjeListener.onZauzetoKorisIme(poruka);
                }

            }
        }
    };

    private MyWebserviceHandler zahtjevZaIgromHandler = new MyWebserviceHandler() {
        @Override
        public void onDataArrived(Object result, int respondeID, boolean ok, long timeStamp) {
            if (ok) {
                //TODO: ovdje ulazne podatke ubaci u objekt
            }
        }
    };
}
