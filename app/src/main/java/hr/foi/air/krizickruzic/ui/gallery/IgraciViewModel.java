package hr.foi.air.krizickruzic.ui.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import hr.foi.air.krizickruzic.ui.login.data.LoginDataSource;
import hr.foi.air.krizickruzic.ui.login.data.LoginRepository;

public class IgraciViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    LoginRepository loginRepository;

    public IgraciViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Izaberi protiv koga ćeš igrati, crveni igrači su zauzeti");
        loginRepository = LoginRepository.getInstance();
    }

    public LiveData<String> getText() {
        return mText;
    }
}