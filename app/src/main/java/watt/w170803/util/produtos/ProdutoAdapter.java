package watt.w170803.util.produtos;

import android.content.Context;
import android.content.Intent;
import android.icu.text.LocaleDisplayNames;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import watt.w170803.R;
import watt.w170803.TelaProdutosExibe;

/**
 * Created by Usuario on 08/09/2017.
 */

public class ProdutoAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<Produto> produtos;

    public ProdutoAdapter(Context context, ArrayList<Produto> produtos) {
        this.context = context;
        this.produtos = produtos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.linha_produto, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder hold = (ViewHolder) holder;
        Produto pro = produtos.get(position);

        hold.tvIdProduto.setText(String.valueOf(pro.getIdProduto()));
        hold.tvDescricao.setText(String.valueOf(pro.getDescricao()));
        hold.tvUndMedida.setText(String.valueOf(pro.getUndMedida()));
        //hold.tvPreco.setText(String.valueOf(pro.getPreco()));
        hold.tvPreco.setText(String.format(Locale.getDefault(),"%.2f", pro.getPreco()));
        hold.tvPrecoSugerido.setText(String.format(Locale.getDefault(), "%.2f", pro.getPrecoSugerido()));
        holder.itemView.setTag(pro.getIdProduto());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TelaProdutosExibe.class);
                Bundle args = new Bundle();
                args.putLong("clicado",(Long)view.getTag());
                intent.putExtras(args);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return produtos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final TextView tvIdProduto;
        final TextView tvDescricao;
        final TextView tvUndMedida;
        final TextView tvPreco;
        final TextView tvPrecoSugerido;

        public ViewHolder(View v) {
            super(v);
            tvIdProduto = (TextView) v.findViewById(R.id.tv_id_produto);
            tvDescricao = (TextView) v.findViewById(R.id.tv_descricao);
            tvUndMedida = (TextView) v.findViewById(R.id.tv_und_medida);
            tvPreco = (TextView) v.findViewById(R.id.tv_preco);
            tvPrecoSugerido = (TextView) v.findViewById(R.id.tv_preco_sugerido);
        }
    }
}
