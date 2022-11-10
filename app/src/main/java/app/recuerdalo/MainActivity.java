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

public class MainActivity extends AppCompatActivity {
    private Button bt_Agregar, bt_Recuerdos, bt_Eliminar,bt_Mapa, bt_Consejos;
    private EditText etR ,etO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etR = findViewById(R.id.etRecuerdos);
        etO = findViewById(R.id.etOlvidar);
        bt_Agregar = findViewById(R.id.btnAgregar);
        bt_Recuerdos = findViewById(R.id.btnRecuerdos);
        bt_Eliminar = findViewById(R.id.btnEliminar);
        bt_Mapa = findViewById(R.id.btnMaps);
        bt_Consejos = findViewById(R.id.btnHints);

        bt_Agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AgregarRecuerdo();
            }
        });
        bt_Recuerdos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lrecuerdos = new Intent(getApplicationContext(), ListaRecuerdos.class);
                startActivity(lrecuerdos);

            }
        });
        bt_Eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OlvidarRecuerdo();
            }
        });
        bt_Mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hints = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(hints);
            }
        });
        bt_Consejos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent maps = new Intent(getApplicationContext(), Recycler.class);
                startActivity(maps);
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