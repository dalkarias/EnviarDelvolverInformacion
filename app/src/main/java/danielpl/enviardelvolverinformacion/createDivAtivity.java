package danielpl.enviardelvolverinformacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import danielpl.enviardelvolverinformacion.MODELOS.direccion;

public class createDivAtivity extends AppCompatActivity {

    private EditText txtCalle;
    private EditText txtNumber;
    private EditText txtCiudad;
    private Button btnGuardar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_div_ativity);

        inicializaVistas();

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                direccion dir = new direccion(txtCalle.getText().toString(),Integer.parseInt(txtNumber.getText().toString()),txtCiudad.getText().toString());

                Bundle mochila = new Bundle();
                mochila.putSerializable("DIR",dir);

                // TENEMOS QUE MANDAR ESA INFORMACION
                // al main activity principal
                Intent intentVacio = new Intent();
                intentVacio.putExtras(mochila);
                setResult(RESULT_OK,intentVacio);
                finish();
            }
        });
    }

    private void inicializaVistas() {
        txtCalle = findViewById(R.id.txt_calle);
        txtNumber = findViewById(R.id.txt_numero);
        txtCiudad = findViewById(R.id.txt_ciudad);
        btnGuardar = findViewById(R.id.btn_guardar);
    }
}