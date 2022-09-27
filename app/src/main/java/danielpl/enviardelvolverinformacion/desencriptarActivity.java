package danielpl.enviardelvolverinformacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import danielpl.enviardelvolverinformacion.MODELOS.usuario;

public class desencriptarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desencriptar);

        Intent intentReceptor = getIntent();

        Bundle bundleReceptor = intentReceptor.getExtras();

        if (bundleReceptor != null){
            //String password = bundleReceptor.getString("PASS");
            //String email = bundleReceptor.getString("EMAIL");
            //usuario user = new usuario(email,password);

            usuario userSegundo = (usuario) bundleReceptor.getSerializable("USER");
            Toast.makeText(this, userSegundo.toString(),Toast.LENGTH_SHORT).show();
        }
    }
}