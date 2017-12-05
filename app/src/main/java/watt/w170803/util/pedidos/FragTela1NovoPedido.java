package watt.w170803.util.pedidos;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
    private static final String ARG_PARAM1 = "cliente selecionado";

    // TODO: Rename and change types of parameters
    private String paramClienteSelecionado;

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
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragTela1NovoPedido.
     */
    // TODO: Rename and change types and number of parameters
    public static FragTela1NovoPedido newInstance(String param1, String param2) {
        FragTela1NovoPedido fragment = new FragTela1NovoPedido();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            paramClienteSelecionado = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Views do layout
        tvRazaoSocial = (TextView) getView().findViewById(R.id.tv_razao_social_activity_novo_pedido);
        tvFantasia = (TextView) getView().findViewById(R.id.tv_fantasia_activity_novo_pedido);
        tvEndereco = (TextView) getView().findViewById(R.id.tv_endereco_activity_novo_pedido);
        tvNumero = (TextView) getView().findViewById(R.id.tv_numero_activity_novo_pedido);
        tvBairro = (TextView) getView().findViewById(R.id.tv_bairro_activity_novo_pedido);
        tvCidade = (TextView) getView().findViewById(R.id.tv_cidade_activity_novo_pedido);

        getCliente(Long.parseLong(paramClienteSelecionado));

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frag_tela1_novo_pedido, container, false);
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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
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

    private void getCliente(long idCliente){
        ClientesDB cliDb = new ClientesDB(getContext());
        cliDb.abrirBanco();
        Clientes cli = cliDb.consultarTotal(idCliente);
        cliDb.fecharBanco();

        // Setando as views do layout
        tvRazaoSocial.setText(cli.getRazaoSocial());
        tvFantasia.setText(cli.getFantasia());
        tvEndereco.setText(cli.getEndereco());
        tvNumero.setText(cli.getNumero());
        tvBairro.setText(cli.getBairro());
        tvCidade.setText(cli.getCidade());
    }
}
