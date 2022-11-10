package app.recuerdalo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListaRecuerdos extends AppCompatActivity {
    private ListView lst1;
    private ArrayList<String> arreglo = new ArrayList<String>();
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_recuerdos);
        try{
            SQLiteDatabase db = openOrCreateDatabase("BD_Recuerdalo", Context.MODE_PRIVATE,null);
            lst1 = findViewById(R.id.lst1);
            final Cursor c = db.rawQuery("select * from recuerdos",null);
            int id = c.getColumnIndex("id");
            int recuerdos = c.getColumnIndex("recuerdos");
            arreglo.clear();

            arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,arreglo);

            lst1.setAdapter(arrayAdapter);

            final  ArrayList<Recuerdo> lista = new ArrayList<Recuerdo>();


            if(c.moveToFirst())
            {
                do{
                    Recuerdo recuerdo = new Recuerdo();
                    recuerdo.id = c.getString(id);
                    recuerdo.recuerdos = c.getString(recuerdos);

                    lista.add(recuerdo);

                    arreglo.add(c.getString(id) + " \t " + c.getString(recuerdos));

                } while(c.moveToNext());
                arrayAdapter.notifyDataSetChanged();
                lst1.invalidateViews();
            }

            /*lst1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, android.view.View view, int position, long l) {
                    Recuerdo recuerdo = lista.get(position);
                    Intent i = new Intent(getApplicationContext(), Editar.class);
                    i.putExtra("id",recuerdo.id);
                    i.putExtra("recuerdos",recuerdo.recuerdos);
                    startActivity(i);
                }
            });*/
        }
        catch (Exception e){
            Toast.makeText(this, "Ha ocurrido un error, intentalo nuevamente.", Toast.LENGTH_SHORT).show();
        }
    }
}