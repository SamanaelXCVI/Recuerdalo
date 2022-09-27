package app.recuerdalo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> nombres;
    private ArrayAdapter<String> adaptador1;
    private ListView lvN;
    private EditText et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombres = new ArrayList();
        adaptador1 = new ArrayAdapter(this,android.R.layout.simple_list_item_1,nombres);
        lvN = findViewById(R.id.lvNombres);
        lvN.setAdapter(adaptador1);
        et1 = findViewById(R.id.etNombres);

    }

    public void Agregar (View view){
        nombres.add(et1.getText().toString());
        adaptador1.notifyDataSetChanged();
        et1.setText("");

    }

    public void Borrar(View view){
        nombres.remove(et1.getText().toString());
        adaptador1.notifyDataSetChanged();
        et1.setText("");

    }

    public void Hints (View view){
        Intent hints = new Intent(this, Recycler.class);
        startActivity(hints);
    }


}