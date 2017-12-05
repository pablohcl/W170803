package watt.w170803.util.clientes;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import watt.w170803.R;
import watt.w170803.TelaClientesExibe;
import watt.w170803.util.clientes.Clientes;
import watt.w170803.util.pedidos.NovoPedido;

/**
 * Created by Usuario on 31/08/2017.
 */

public class ClientesAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<Clientes> clientes;

    public ClientesAdapter(Context context, ArrayList<Clientes> clientes) {
        this.context = context;
        this.clientes = clientes;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.linha_cliente, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder hold = (ViewHolder) holder;
        Clientes cli = clientes.get(position);

        hold.tvIdCliente.setText(String.valueOf(cli.getCodigoCliente()));
        hold.tvRazaoSocial.setText(String.valueOf(cli.getRazaoSocial()));
        hold.tvFantasia.setText(String.valueOf(cli.getFantasia()));
        hold.tvBairro.setText(String.valueOf(cli.getBairro()));
        hold.tvCidade.setText(String.valueOf(cli.getCidade()));
        holder.itemView.setTag(cli.getCodigoCliente());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder adOpcoesCliente = new AlertDialog.Builder(context);
                adOpcoesCliente.setTitle("O que desejas?");
                adOpcoesCliente.setItems(R.array.array_alert_opcoes_cliente, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch(i){
                            case 0:
                                exibirDadosCliente(view);
                                break;

                            case 1:
                                novoPedido(view);
                                break;
                        }
                    }
                });
                adOpcoesCliente.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return clientes.size();
    }

    private void exibirDadosCliente(View view){

        Intent intent = new Intent(context, TelaClientesExibe.class);
        Bundle args = new Bundle();
        args.putLong("clicado",(Long)view.getTag());
        intent.putExtras(args);
        context.startActivity(intent);
    }

    private void novoPedido(View view){
        Intent intent = new Intent(context, NovoPedido.class);
        Bundle args = new Bundle();
        args.putLong("cliente selecionado", (Long)view.getTag());
        args.putLong("pedido", 0);
        intent.putExtras(args);
        context.startActivity(intent);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final TextView tvRazaoSocial;
        final TextView tvIdCliente;
        final TextView tvFantasia;
        final TextView tvBairro;
        final TextView tvCidade;

        public ViewHolder(View v) {
            super(v);
            tvIdCliente = (TextView) v.findViewById(R.id.tv_id_cliente);
            tvRazaoSocial = (TextView) v.findViewById(R.id.tv_razao_social_clientes_exibe);
            tvFantasia = (TextView) v.findViewById(R.id.tv_fantasia);
            tvBairro = (TextView) v.findViewById(R.id.tv_bairro);
            tvCidade = (TextView) v.findViewById(R.id.tv_cidade);
        }
    }
}
