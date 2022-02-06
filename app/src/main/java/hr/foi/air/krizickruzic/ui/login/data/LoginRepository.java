package hr.foi.air.krizickruzic.ui.login.data;

import java.io.IOException;

import hr.foi.air.core.entities.Igrac;
import hr.foi.air.krizickruzic.ui.login.data.model.LoggedInUser;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class LoginRepository {

    private static volatile LoginRepository instance;

    //private LoginDataSource dataSource;

    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
    private LoggedInUser user = null;

    // private constructor : singleton access
    private LoginRepository() {

    }

    public static LoginRepository getInstance() {
        if (instance == null) {
            instance = new LoginRepository();
        }
        return instance;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public void logout() {
        user = null;

    }

    private void setLoggedInUser(LoggedInUser user) {
        this.user = user;
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }

    public Result<LoggedInUser> login(Igrac igrac) {
        // handle login
        LoggedInUser logiraniKorisnik = new LoggedInUser(java.util.UUID.randomUUID().toString(), igrac.getEmail());

        if (logiraniKorisnik == null) {
            Result<LoggedInUser> result = new Result.Error(new IOException("Pogreška pri logiranju"));
            return result;
        } else {
            Result<LoggedInUser> result = new Result.Success<>(logiraniKorisnik);
            return result;
        }
    }
}