package com.luu.tpinmobiliaria.ui.Pagos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.luu.tpinmobiliaria.R;
import com.luu.tpinmobiliaria.models.Pago;
import java.util.List;

public class PagosAdapter extends RecyclerView.Adapter<PagosAdapter.PagoViewHolder> {

    private List<Pago> listaPagos;

    public PagosAdapter(List<Pago> listaPagos) {
        this.listaPagos = listaPagos;
    }

    @NonNull
    @Override
    public PagoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pago, parent, false);
        return new PagoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PagoViewHolder holder, int position) {
        Pago pago = listaPagos.get(position);
        holder.tvNumPago.setText("Pago N°: " + pago.getIdPago());
        holder.tvFechaPago.setText("Fecha: " + pago.getFechaPago());
        holder.tvImportePago.setText("Importe: $" + String.format("%.0f", pago.getMonto()));
    }

    @Override
    public int getItemCount() {
        return listaPagos != null ? listaPagos.size() : 0;
    }

    public static class PagoViewHolder extends RecyclerView.ViewHolder {
        TextView tvNumPago, tvFechaPago, tvImportePago;

        public PagoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNumPago = itemView.findViewById(R.id.tvNumeroPago);
            tvFechaPago = itemView.findViewById(R.id.tvFechaPago);
            tvImportePago = itemView.findViewById(R.id.tvImportePago);
        }
    }
}