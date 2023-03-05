package db.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aut2_03.R;
import com.example.aut2_03.VerActivity;

import java.util.ArrayList;

import db.entidades.Travel;

public class ListaTravelAdapter extends RecyclerView.Adapter<ListaTravelAdapter.TravelViewHolder> {

    private ArrayList<Travel> listaTravel;

    public ListaTravelAdapter (ArrayList<Travel> listaTravel){
        this.listaTravel = listaTravel;
    }

    @NonNull
    @Override
    //CARGAR LA VISTA
    public TravelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item,null,false);
        return new TravelViewHolder(view);
    }

    @Override
    //OBTENER LOS DATOS DE LA VISTA
    public void onBindViewHolder(@NonNull TravelViewHolder holder, int position) {
        holder.viewId.setText(listaTravel.get(position).getId());
        holder.viewNombre.setText(listaTravel.get(position).getNombre());
        holder.viewCapital.setText(listaTravel.get(position).getCapital());
        holder.viewIdioma.setText(listaTravel.get(position).getIdioma());
    }

    @Override
    //TAMAÑO DE LA LISTA
    public int getItemCount() {
        return listaTravel.size();
    }

    //CLASE EMBEBIDA QUE VINCULA LAS VARIABLES LOCALES CON LOS TEXTVIEW DE LA VISTA
    public class TravelViewHolder extends RecyclerView.ViewHolder {
        TextView viewId,viewNombre,viewCapital,viewIdioma;

        public TravelViewHolder(@NonNull View itemView) {
            super(itemView);

            viewId = itemView.findViewById(R.id.viewId);
            viewNombre = itemView.findViewById(R.id.viewPais);
            viewCapital = itemView.findViewById(R.id.viewCapital);
            viewIdioma = itemView.findViewById(R.id.viewIdioma);
        }
    }
}
