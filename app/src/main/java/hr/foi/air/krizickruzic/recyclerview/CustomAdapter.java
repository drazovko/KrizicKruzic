package hr.foi.air.krizickruzic.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hr.foi.air.core.DataLoadedListener;
import hr.foi.air.core.DataLoader;
import hr.foi.air.core.entities.Igrac;
import hr.foi.air.krizickruzic.R;
import hr.foi.air.krizickruzic.loaders.WsDataLoader;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>  {

    private List<Igrac> listaIgraca;


    public static class ViewHolder extends RecyclerView.ViewHolder implements DataLoadedListener{

        private final Button igracButton;
        DataLoader dataLoader;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Define click listener for the ViewHolder's View


            igracButton = itemView.findViewById(R.id.igracButton);
            igracButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    igracButton.setText("nekaj");
                    posaljiImeIgracaNaServer();
                }


            });

        }

        private void posaljiImeIgracaNaServer() {
            dataLoader = new WsDataLoader();
            dataLoader.slanjeZahtijevaZaIgrom(this);
        };

        public Button getIgracButton() {return igracButton; }

        @Override
        public void onIgraciLoaded(List<Igrac> igraci) {

        }
    }

    public CustomAdapter(List<Igrac> igraci){
        listaIgraca = igraci;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.text_row_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.getTextView().setText(localDataSet[position]);
//        holder.getTextView().setText(listaIgraca.get(position).getEmail());
        holder.getIgracButton().setText(listaIgraca.get(position).getEmail());
        if (listaIgraca.get(position).isZauzet()){
            holder.getIgracButton().setText("zauzet");
        }


    }

    @Override
    public int getItemCount() {
        return listaIgraca.size();
    }


}
