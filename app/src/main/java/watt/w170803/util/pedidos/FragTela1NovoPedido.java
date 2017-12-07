package watt.w170803.util.pedidos;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import watt.w170803.R;
import watt.w170803.util.clientes.Clientes;
import watt.w170803.util.clientes.ClientesDB;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragTela1NovoPedido.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragTela1NovoPedido#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragTela1NovoPedido extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "pedido";
    private static final String ARG_PARAM2 = "cliente selecionado";

    // TODO: Rename and change types of parameters
    private String paramPedido;

    // Views do layout
    private TextView tvRazaoSocial;
    private TextView tvFantasia;
    private TextView tvEndereco;
    private TextView tvNumero;
    private TextView tvBairro;
    private TextView tvCidade;

    private OnFragmentInteractionListener mListener;

    public FragTela1NovoPedido() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FragTela1NovoPedido.
     */
    // TODO: Rename and change types and number of parameters
    public static FragTela1NovoPedido newInstance(String pedido, String cliente) {
        FragTela1NovoPedido fragment = new FragTela1NovoPedido();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, pedido);
        args.putString(ARG_PARAM2, cliente);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_frag_tela1_novo_pedido, container, false);

        // Views do layout
        tvRazaoSocial = (TextView) view.findViewById(R.id.tv_razao_social_activity_novo_pedido);
        tvFantasia = (TextView) view.findViewById(R.id.tv_fantasia_activity_novo_pedido);
        tvEndereco = (TextView) view.findViewById(R.id.tv_endereco_activity_novo_pedido);
        tvNumero = (TextView) view.findViewById(R.id.tv_numero_activity_novo_pedido);
        tvBairro = (TextView) view.findViewById(R.id.tv_bairro_activity_novo_pedido);
        tvCidade = (TextView) view.findViewById(R.id.tv_cidade_activity_novo_pedido);

        if (getArguments().getString(ARG_PARAM1) != null) {
            setClienteByPedido(getArguments().getString(ARG_PARAM1));
            Log.d("log", "arg1 veio");
        }
        if(getArguments().getString(ARG_PARAM2) != null){
            setClienteById(getArguments().getString(ARG_PARAM2));
            Log.d("log", "arg2 veio");
        }

        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void setClienteById(String idCliente){
        ClientesDB cliDb = new ClientesDB(getContext());
        cliDb.abrirBanco();
        Clientes cli;

        if(cliDb.consultarTotal(idCliente) != null) {
            cli = cliDb.consultarTotal(idCliente);

            // Setando as views do layout
            tvRazaoSocial.setText(cli.getRazaoSocial());
            tvFantasia.setText(cli.getFantasia());
            tvEndereco.setText(cli.getEndereco());
            tvNumero.setText(cli.getNumero());
            tvBairro.setText(cli.getBairro());
            tvCidade.setText(cli.getCidade());
        }else{
            Log.d("LOG", "CONSULTA PARA PEGAR OS DADOS DO CLIENTE RETORNOU NULA");
        }
        cliDb.fecharBanco();
    }

    private void setClienteByPedido(String idPedido){
        PedidosDB pedDB = new PedidosDB(getContext());
        pedDB.abrirBanco();
        Clientes cli;
        ClientesDB cliDB = new ClientesDB(getContext());

        if(pedDB.descobrirClientePeloPedido(idPedido) != "0") {
            String idCliente = pedDB.descobrirClientePeloPedido(idPedido);

            cli = cliDB.consultarTotal(idCliente);

            // Setando as views do layout
            tvRazaoSocial.setText(cli.getRazaoSocial());
            tvFantasia.setText(cli.getFantasia());
            tvEndereco.setText(cli.getEndereco());
            tvNumero.setText(cli.getNumero());
            tvBairro.setText(cli.getBairro());
            tvCidade.setText(cli.getCidade());
        }else{
            Log.d("LOG", "CONSULTA PARA PEGAR OS DADOS DO CLIENTE RETORNOU NULA");
        }
        cliDB.fecharBanco();
    }
}
