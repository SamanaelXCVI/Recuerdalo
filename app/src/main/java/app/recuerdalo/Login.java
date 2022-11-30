package app.recuerdalo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    Button btn_ingresar;
    EditText correo, pass;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        correo = findViewById(R.id.et_Email);
        pass = findViewById(R.id.et_contraseña);
        btn_ingresar = findViewById(R.id.btn_ingresar);
        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correoUsu = correo.getText().toString().trim();
                String pwrdUsu = pass.getText().toString().trim();

                if (correoUsu.isEmpty() && pwrdUsu.isEmpty()){
                    Toast.makeText(Login.this, "Ingresar los datos", Toast.LENGTH_SHORT).show();
                }else{
                    loginUser(correoUsu, pwrdUsu);
                }


            }
        });
    }

    private void loginUser(String correoUsu, String pwrdUsu)  {
        mAuth.signInWithEmailAndPassword(correoUsu, pwrdUsu).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    finish();
                    startActivity(new Intent(Login.this, Bienvenida.class));
                    Toast.makeText(Login.this,"Bienvenido", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Login.this, "Error", Toast.LENGTH_SHORT).show();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Login.this, "Error al inciar sesión", Toast.LENGTH_SHORT).show();
            }
        });
    }


}