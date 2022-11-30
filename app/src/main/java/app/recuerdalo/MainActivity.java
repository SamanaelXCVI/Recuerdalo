package app.recuerdalo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private EditText etR ,etO;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        etR = findViewById(R.id.etRecuerdos);
        etO = findViewById(R.id.etOlvidar);
        Button bt_Agregar = findViewById(R.id.btnAgregar);
        Button bt_Recuerdos = findViewById(R.id.btnRecuerdos);
        Button bt_Eliminar = findViewById(R.id.btnEliminar);
        Button bt_Mapa = findViewById(R.id.btnMaps);
        Button bt_Consejos = findViewById(R.id.btnHints);
        Button bt_Mensajes = findViewById(R.id.btnMensajes);
        Button bt_CSesión = findViewById(R.id.btnCerrarS);

        bt_Agregar.setOnClickListener(v -> AgregarRecuerdo());
        bt_Recuerdos.setOnClickListener(v -> {
            Intent lrecuerdos = new Intent(getApplicationContext(), ListaRecuerdos.class);
            startActivity(lrecuerdos);

        });
        bt_Eliminar.setOnClickListener(view -> OlvidarRecuerdo());
        bt_Mapa.setOnClickListener(view -> {
            Intent hints = new Intent(getApplicationContext(), MapsActivity.class);
            startActivity(hints);
        });
        bt_Consejos.setOnClickListener(view -> {
            Intent maps = new Intent(getApplicationContext(), Recycler.class);
            startActivity(maps);
        });
        bt_Mensajes.setOnClickListener(view -> {
            Intent mensajes = new Intent(getApplicationContext(), Sensores.class);
            startActivity(mensajes);
        });
        bt_CSesión.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                finish();
                startActivity(new Intent(MainActivity.this, Login.class));
            }
        });
    }





    public void AgregarRecuerdo(){
        try {
            String recuerdos = etR.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("BD_Recuerdalo", Context.MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS recuerdos(id INTEGER PRIMARY KEY AUTOINCREMENT,recuerdos VARCHAR)");

            String sql = "insert into recuerdos(recuerdos)values(?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,recuerdos);
            statement.execute();
            Toast.makeText(this,"Recuerdo agregado satisfactoriamente en la memoria.",Toast.LENGTH_LONG).show();
            etR.setText("");
        }
        catch (Exception ex)
        {
            Toast.makeText(this,"Error no se pudo guardar el recuerdo.",Toast.LENGTH_LONG).show();
        }
    }

    public void OlvidarRecuerdo()
    {
        try
        {
            String id = etO.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("BD_Recuerdalo", Context.MODE_PRIVATE,null);


            String sql = "delete from recuerdos where id = ?";
            SQLiteStatement statement = db.compileStatement(sql);

            statement.bindString(1,id);
            statement.execute();
            Toast.makeText(this,"El recuerdo ha sido olvidado.",Toast.LENGTH_LONG).show();

            etO.setText("");
            etO.requestFocus();

        }
        catch (Exception ex)
        {
            Toast.makeText(this,"El recuerdo no pudo ser olvidado esta vez.",Toast.LENGTH_LONG).show();
        }
    }
}