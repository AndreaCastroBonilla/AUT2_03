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
    public TravelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item,null,false);
        return new TravelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TravelViewHolder holder, int position) {
        holder.viewId.setText(listaTravel.get(position).getId());
        holder.viewNombre.setText(listaTravel.get(position).getNombre());
        holder.viewCapital.setText(listaTravel.get(position).getCapital());
        holder.viewIdioma.setText(listaTravel.get(position).getIdioma());
    }

    @Override
    public int getItemCount() {
        return listaTravel.size();
    }

    public class TravelViewHolder extends RecyclerView.ViewHolder {

        TextView viewId,viewNombre,viewCapital,viewIdioma;

        public TravelViewHolder(@NonNull View itemView) {
            super(itemView);

            viewId = itemView.findViewById(R.id.viewId);
            viewNombre = itemView.findViewById(R.id.viewPais);
            viewCapital = itemView.findViewById(R.id.viewCapital);
            viewIdioma = itemView.findViewById(R.id.viewIdioma);

            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerActivity.class);
                    intent.putExtra("ID",listaTravel.get(getAdapterPosition()).getId());
                    Toast.makeText(context, ":(", Toast.LENGTH_SHORT).show();
                    context.startActivity(intent);
                }
            });

             */
        }
    }
}
