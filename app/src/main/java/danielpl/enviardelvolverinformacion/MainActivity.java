package danielpl.enviardelvolverinformacion;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import danielpl.enviardelvolverinformacion.MODELOS.direccion;
import danielpl.enviardelvolverinformacion.MODELOS.usuario;

public class MainActivity extends AppCompatActivity {

    private EditText txtEmail;
    private EditText txtpassword;
    private Button btnDesencriptar;
    private Button btnCrearDir;

    // constantes
    private final int DIRECCIONES = 0;
    private final int TRACTORES = 1;

    // launcher
    private ActivityResultLauncher<Intent> launcherDirecciones;
    private ActivityResultLauncher<Intent> launcherTractores;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializaVariables();
        inicializarLunchers();

        // String password = txtpassword.getText().toString();//recoge los datos de la caja  (((ERROR DE PROGRAMACION)))

        btnDesencriptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtEmail.getText().toString();
                String password = txtpassword.getText().toString();//DEBE DE ESTAR EN ESTE LUGAR

                Intent intent = new Intent(MainActivity.this, desencriptarActivity.class);
                Bundle bundle = new Bundle(); // mochila de información

               // bundle.putString("PASS",password);//contraseña para abrir la mochila y el contenido que nos llevamos
               // bundle.putString("EMAIL",email);

                bundle.putSerializable("USER", new usuario(email,password)); // esto lo usaremos para pasar un objeto completo a la mochila
                //  ES NECESARIO PONER EN EL USUARIO IMPLEMENTS SERIALIZABLE

                /*
                ------- TIPOS DE DATOS PARA EL BUNDLE -------


                 */
                intent.putExtras(bundle); // cargamos los datos para mandarlos

                startActivity(intent); // mandamos los datos
            }
        });

        btnCrearDir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, createDivAtivity.class);
                startActivityForResult(intent,DIRECCIONES);
            }
        });
    }

    private void inicializarLunchers() {
        // registrar una actividad de retorno
        // 1. ¿Como lanzo la actividad hija?
        // 2. ¿que hago cuando mi hija termine?
        launcherDirecciones = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode()==RESULT_OK){
                            if(result.getData() != null){
                                Bundle bundle = result.getData().getExtras();
                                direccion dir = (direccion) bundle.getSerializable("DIR");
                                Toast.makeText(MainActivity.this, dir.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
        );
    }

    private void inicializaVariables() {
        txtEmail = findViewById(R.id.txt_email_main);
        txtpassword = findViewById(R.id.txt_password_main);
        btnDesencriptar = findViewById(R.id.btn_desencriptar_main);
        btnCrearDir = findViewById(R.id.btn_crear_Direccion_main);
    }

    /**
     * Esta funcion se activa al retornar de un startActivityForResult y dispara las acciones necesarias
     * @param requestCode -> identificador de la ventana que se ha cerrado (tipo de dato que retorna)
     * @param resultCode -> el modo en que se ha cerrado la ventana
     * @param data -> son los datos retornados (intent con un bundle dentro)
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == DIRECCIONES){
            if(resultCode == RESULT_OK){
                if(data != null){
                    Bundle bundle = data.getExtras();
                    direccion dir = (direccion) bundle.getSerializable("DIR");
                    Toast.makeText(this, dir.toString(), Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "No hay datos", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(this, "Ventana Cancelada", Toast.LENGTH_SHORT).show();
            }
        }
    }
}