package watt.w170803.util.clientes.contatos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import watt.w170803.R;

/**
 * Created by Usuario on 13/10/2017.
 */

public class ContatosAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<ContatosClientes> contatos;

    public ContatosAdapter(Context context, ArrayList<ContatosClientes> contatos) {
        this.context = context;
        this.contatos = contatos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.linha_contatos, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder hold = (ViewHolder) holder;
        ContatosClientes con = contatos.get(position);

        hold.tvContatoAdapter.setText(String.valueOf(con.getContatoContatos()));
        hold.tvDdd1ContatoAdapter.setText(String.valueOf(con.getDdd1Contato()));
        hold.tvFone1ContatoAdapter.setText(String.valueOf(con.getFone1Contato()));
        hold.tvDdd2ContatoAdapter.setText(String.valueOf(con.getDdd2Contato()));
        hold.tvFone2ContatoAdapter.setText(String.valueOf(con.getFone2Contato()));
        hold.tvEmailContatoAdapter.setText(String.valueOf(con.getEmailContato()));

    }

    @Override
    public int getItemCount() {
        return contatos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final TextView tvContatoAdapter;
        final TextView tvDdd1ContatoAdapter;
        final TextView tvFone1ContatoAdapter;
        final TextView tvDdd2ContatoAdapter;
        final TextView tvFone2ContatoAdapter;
        final TextView tvEmailContatoAdapter;

        public ViewHolder(View v) {
            super(v);
            tvContatoAdapter = (TextView) v.findViewById(R.id.tv_contato_contatos_linha);
            tvDdd1ContatoAdapter = (TextView) v.findViewById(R.id.tv_ddd1_contatos_linha);
            tvFone1ContatoAdapter = (TextView) v.findViewById(R.id.tv_fone1_contatos_linha);
            tvDdd2ContatoAdapter = (TextView) v.findViewById(R.id.tv_ddd2_contatos_linha);
            tvFone2ContatoAdapter = (TextView) v.findViewById(R.id.tv_fone2_contatos_linha);
            tvEmailContatoAdapter= (TextView) v.findViewById(R.id.tv_email_contatos_linha);
        }
    }
}
