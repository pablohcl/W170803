package watt.w170803.util.pedidos;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import watt.w170803.ActivityProdutos;
import watt.w170803.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragTela2ProdutosNovoPedido.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragTela2ProdutosNovoPedido#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragTela2ProdutosNovoPedido extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "pedido";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParamPedido;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragTela2ProdutosNovoPedido() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FragTela2ProdutosNovoPedido newInstance(String param1) {
        FragTela2ProdutosNovoPedido fragment = new FragTela2ProdutosNovoPedido();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    private Pedido pedido;

    private Button btnAddItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParamPedido = getArguments().getString(ARG_PARAM1);
            if(mParamPedido != null)
                pedido = getPedido(mParamPedido);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frag_tela2_produtos_novo_pedido, container, false);

        btnAddItem = (Button) view.findViewById(R.id.btn_add_item_frag2_novo_pedido);
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("pedido", mParamPedido);
                Intent intent = new Intent(getContext(), ActivityProdutos.class);
                intent.putExtras(args);
                startActivity(intent);
            }
        });

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

    private Pedido getPedido(String pedido){
        PedidosDB pedDB = new PedidosDB(getContext());
        if(pedDB.getPedido(pedido) != null){
            return pedDB.getPedido(pedido);
        }else{
            AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
            alert.setTitle("Atenção!");
            alert.setMessage("Pedido não encontrado");
            alert.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            alert.show();
            return null;
        }
    }
}
