package watt.w170803;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Button;

public class ActivityPedidos extends AppCompatActivity {

    private Button btnNovoPedido;
    private RecyclerView rvPedidos;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish(); // Finaliza a Activity atual
                break;
            default:break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnNovoPedido = (Button) findViewById(R.id.btn_novo_pedido);
        rvPedidos = (RecyclerView) findViewById(R.id.rv_pedidos);


    }
}
