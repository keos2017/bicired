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

public class MainActivity extends AppCompatActivity {

    private static final String TAG = " ";
    TextView mRegister;
    EditText mCorreo, mClave;
    Button   mInicioSesion;
    //Variable FireBase
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRegister = (TextView) findViewById(R.id.txt_inicio_sesion);
        mCorreo = (EditText) findViewById(R.id.txt_correo);
        mClave = (EditText) findViewById(R.id.txt_password);
        mInicioSesion = (Button) findViewById(R.id.btn_password);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        //
        mInicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password;
                email = mCorreo.getText().toString();
                password = mClave.getText().toString();
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    Intent intent = new Intent(MainActivity.this, InicioSesionExitoso.class);
                                    startActivity(intent);
                                    Toast.makeText(MainActivity.this, "Bienvenido.",
                                            Toast.LENGTH_SHORT).show();
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(MainActivity.this, "Error al iniciar sesión.",
                                            Toast.LENGTH_SHORT).show();
                                    updateUI(null);
                                }
                            }

                            // ...

                        });
            }

        });


        //Escuchador
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegistrarNuevo.class));
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser user) {
        if(user != null)
        {
            Intent intent = new Intent(MainActivity.this, InicioSesionExitoso.class);
            startActivity(intent);
            finish();
        }
    }
}
