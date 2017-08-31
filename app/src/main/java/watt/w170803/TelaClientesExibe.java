package watt.w170803;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class TelaClientesExibe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_clientes_exibe);

        Bundle args = getIntent().getExtras();

        Toast.makeText(this, args.getString("item clicado"), Toast.LENGTH_SHORT).show();
    }
}
