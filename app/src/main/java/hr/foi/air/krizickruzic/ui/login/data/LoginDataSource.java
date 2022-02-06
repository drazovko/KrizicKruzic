package hr.foi.air.krizickruzic.ui.login.data;

import android.os.AsyncTask;
import android.os.SystemClock;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import hr.foi.air.core.DataLoadedListener;
import hr.foi.air.core.DataLoader;
import hr.foi.air.core.LogiranjeDataLoadedListener;
import hr.foi.air.core.entities.Igrac;
import hr.foi.air.krizickruzic.loaders.WsDataLoader;
import hr.foi.air.krizickruzic.ui.login.data.model.LoggedInUser;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {
    DataLoader dataLoader;
    LoggedInUser logiraniKorisnik;

    public Result<LoggedInUser> login(String username, String password) {
        dataLoader = new WsDataLoader();
        //dataLoader.logiranjeData(this, username, password);

//        SystemClock.sleep(5000);

            // TODO: handle loggedInUser authentication
            if (logiraniKorisnik == null) {
                return new Result.Error(new IOException("Pogreška pri logiranju"));
            } else {
                return new Result.Success<>(logiraniKorisnik);
            }
    }

    public void logout() {
        // TODO: revoke authentication
    }


    public void onIgracLogiran(Igrac igrac) {
        // TODO: ovdje stiže potvrda logiranja
        logiraniKorisnik = new LoggedInUser
                (java.util.UUID.randomUUID().toString(), igrac.getEmail());
    }
}