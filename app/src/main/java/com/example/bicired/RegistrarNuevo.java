package com.example.bicired;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrarNuevo extends AppCompatActivity {

    private static final String TAG = " ";
    TextView mIniciarsesion;
    EditText mCorreo, mClave;
    Button mRegister;
    //Firebase
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_nuevo);

        //Firebase
        mAuth = FirebaseAuth.getInstance();

        mIniciarsesion = (TextView) findViewById(R.id.txt_inicio_sesion);
        mCorreo        = (EditText) findViewById(R.id.txt_correo);
        mClave         = (EditText) findViewById(R.id.txt_password);
        mRegister      = (Button) findViewById(R.id.btn_register);


        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password;
                email = mCorreo.getText().toString();
                password = mClave.getText().toString();
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegistrarNuevo.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Intent intent = new Intent(RegistrarNuevo.this, InicioSesionExitoso.class);
                                    startActivity(intent);
                                    Toast.makeText(RegistrarNuevo.this, "Registrado Satisfactoriamente.",
                                            Toast.LENGTH_SHORT).show();
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(RegistrarNuevo.this, "Error al Registrar.",
                                            Toast.LENGTH_SHORT).show();
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(RegistrarNuevo.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    updateUI(null);
                                }

                            }
                        });

            }
        });

        mIniciarsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrarNuevo.this, MainActivity.class));
            }
        });

    }
    private void updateUI(FirebaseUser user) {
        if(user != null)
        {
            Intent intent = new Intent(RegistrarNuevo.this, InicioSesionExitoso.class);
            startActivity(intent);
            finish();
        }
    }
}