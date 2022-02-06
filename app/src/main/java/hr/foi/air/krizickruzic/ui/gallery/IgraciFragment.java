package hr.foi.air.krizickruzic.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hr.foi.air.core.DataLoadedListener;
import hr.foi.air.core.DataLoader;
import hr.foi.air.core.entities.Igrac;
import hr.foi.air.krizickruzic.R;
import hr.foi.air.krizickruzic.loaders.WsDataLoader;
import hr.foi.air.krizickruzic.recyclerview.CustomAdapter;

public class IgraciFragment extends Fragment implements DataLoadedListener {

    private IgraciViewModel igraciViewModel;
    RecyclerView recyclerView;
    DataLoader dataLoader;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        igraciViewModel =
                new ViewModelProvider(this).get(IgraciViewModel.class);
        View root = inflater.inflate(R.layout.fragment_igraci, container, false);

        recyclerView = root.findViewById(R.id.main_recycler);
        final TextView textView = (TextView) root.findViewById(R.id.igracTextView);

        igraciViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getData();
    }

    private void getData() {
        dataLoader = new WsDataLoader();
        dataLoader.loadData(this);
    }

    @Override
    public void onIgraciLoaded(List<Igrac> igraci) {
        recyclerView.setAdapter(new CustomAdapter(igraci));
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }
}