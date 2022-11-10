package app.recuerdalo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button bt_Agregar, bt_Recuerdos, bt_Eliminar;
    private EditText et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = findViewById(R.id.etNombres);
        bt_Agregar = findViewById(R.id.btnAgregar);
        bt_Recuerdos = findViewById(R.id.btnRecuerdos);
        bt_Eliminar = findViewById(R.id.btnEliminar);

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
                eliminar();
            }
        });
    }





    public void Borrar(View view){
        /*recuerdos.remove(et1.getText().toString());
        adapter.notifyDataSetChanged();*/
        et1.setText("");

    }

    public void Hints (View view){
        Intent hints = new Intent(this, Recycler.class);
        startActivity(hints);
    }

    public void Maps (View view){
        Intent maps = new Intent(this, MapsActivity.class);
        startActivity(maps);
    }

    public void AgregarRecuerdo(){
        try {
            String recuerdos = et1.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("BD_Recuerdalo", Context.MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS recuerdos(id INTEGER PRIMARY KEY AUTOINCREMENT,recuerdos VARCHAR)");

            String sql = "insert into recuerdos(recuerdos)values(?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,recuerdos);
            statement.execute();
            Toast.makeText(this,"Recuerdo agregado satisfactoriamente en la memoria.",Toast.LENGTH_LONG).show();
            et1.setText("");
        }
        catch (Exception ex)
        {
            Toast.makeText(this,"Error no se pudo guardar el recuerdo.",Toast.LENGTH_LONG).show();
        }
    }

    public void eliminar()
    {
        try
        {
            String recuerdos = et1.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("BD_Recuerdalo", Context.MODE_PRIVATE,null);


            String sql = "delete from recuerdos where recuerdos = ?";
            SQLiteStatement statement = db.compileStatement(sql);

            statement.bindString(1,recuerdos);
            statement.execute();
            Toast.makeText(this,"El recuerdo ha sido olvidado.",Toast.LENGTH_LONG).show();

            et1.setText("");
            et1.requestFocus();

        }
        catch (Exception ex)
        {
            Toast.makeText(this,"El recuerdo no pudo ser olvidado esta vez.",Toast.LENGTH_LONG).show();
        }
    }
}