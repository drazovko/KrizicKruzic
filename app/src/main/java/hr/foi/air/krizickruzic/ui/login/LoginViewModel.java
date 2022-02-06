package hr.foi.air.krizickruzic.ui.login;

import android.util.Patterns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import hr.foi.air.core.DataLoader;
import hr.foi.air.core.LogiranjeDataLoadedListener;
import hr.foi.air.core.entities.Igrac;
import hr.foi.air.krizickruzic.R;
import hr.foi.air.krizickruzic.loaders.WsDataLoader;
import hr.foi.air.krizickruzic.ui.login.data.LoginRepository;
import hr.foi.air.krizickruzic.ui.login.data.Result;
import hr.foi.air.krizickruzic.ui.login.data.model.LoggedInUser;

public class LoginViewModel extends ViewModel implements LogiranjeDataLoadedListener {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private MutableLiveData<String> textSaObavjesnika = new MutableLiveData<>();
    private LoginRepository loginRepository;
    DataLoader dataLoader;

    //neki argument treba ići konstruktoru
/*    public LoginViewModel() {
        //mText = new MutableLiveData<>();
        //mText.setValue("Registrirajte se unosom mail adrese!");
    }
*/
    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<LoginFormState> getLoginFormState() { return loginFormState;    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    LiveData<String> getTextSaObavjesnika() {
        return textSaObavjesnika;
    }

    public void login(String username, String password){
        //can be launched in a separate asynchronous job

        dataLoader = new WsDataLoader();
        dataLoader.logiranjeData(this, username, password);


    }



    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }

    @Override
    public void onIgracLogiran(Igrac igrac) {

        Result<LoggedInUser> result = loginRepository.login(igrac);

        if (result instanceof Result.Success){
            LoggedInUser data = ((Result.Success<LoggedInUser>) result).getData();
            loginResult.setValue(new LoginResult(new LoggedInUserView(data.getDisplayName())));
        } else {
            loginResult.setValue(new LoginResult(R.string.login_failed));
        }
    }

    @Override
    public void onZauzetoKorisIme(String poruka) {
        textSaObavjesnika.setValue(poruka);
    }
}