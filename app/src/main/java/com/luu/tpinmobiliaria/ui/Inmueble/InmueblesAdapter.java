package com.luu.tpinmobiliaria.ui.Inmueble;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.luu.tpinmobiliaria.R;
import com.luu.tpinmobiliaria.models.Inmueble;
import com.luu.tpinmobiliaria.request.ApiClient;
import java.util.List;
import android.widget.Switch;

public class InmueblesAdapter extends RecyclerView.Adapter<InmueblesAdapter.ViewHolder> {

    private List<Inmueble> lista;
    private Context context;
    private LayoutInflater inflater;
    private SwitchCompat switchCompat;


    public InmueblesAdapter(List<Inmueble> lista, Context context, LayoutInflater inflater) {
        this.lista = lista;
        this.context = context;
        this.inflater = inflater;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_inmueble, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Inmueble inmueble = lista.get(position);

        holder.tvDireccion.setText(inmueble.getDireccion());
        holder.tvPrecio.setText("$ " + inmueble.getPrecio());

        holder.swDisponible.setOnCheckedChangeListener(null);
        holder.swDisponible.setChecked(inmueble.getDisponible());

        String imagen = ApiClient.BASE_URL + inmueble.getImagenUrl();
        Glide.with(context).load(imagen).into(holder.ivFotoInmueble);

        holder.itemView.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("inmueble", inmueble);
            Navigation.findNavController(v).navigate(R.id.action_nav_inmuebles_to_nav_detalle_inmueble, bundle);
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDireccion, tvPrecio;
        ImageView ivFotoInmueble;
        SwitchCompat swDisponible;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivFotoInmueble = itemView.findViewById(R.id.ivFotoInmueble);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            swDisponible = itemView.findViewById(R.id.swDisponible);
        }
    }
}