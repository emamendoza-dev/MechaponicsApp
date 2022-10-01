package com.upiita.mechaponicsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistroActivity extends AppCompatActivity {

    //Variables para autenticacion
    private FirebaseAuth mAuth;
    private EditText correo;
    private EditText contrasena;
    private EditText confContrasena;

    // Función onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        // Instancia para autenticación de Firebase
        mAuth = FirebaseAuth.getInstance();
        // Campos para autenticación
        correo = findViewById(R.id.edtUsuarioR);
        contrasena = findViewById(R.id.edtContrasenaR);
        confContrasena = findViewById(R.id.edtContrasenaCR);
    }

    // Función onStart
    @Override
    public void onStart() {
        super.onStart();
        // Comprobar si el usuario ha iniciado sesión y actualice la interfaz de usuario
        FirebaseUser currentUser = mAuth.getCurrentUser();
        /*if(currentUser != null){
            reload();
        }*/
    }

    // Funcion para registrar un usuario
    public void registrarUsuario(View view){
        // Verificacion de campos de contraseña llenados correctamente
        if(contrasena.getText().toString().equals(confContrasena.getText().toString())){
            mAuth.createUserWithEmailAndPassword(correo.getText().toString(), contrasena.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        // Funcion para autenticar las credenciales
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Usuario registrado correctamente
                                // Notificacion al usuario de proceso exitoso
                                Toast.makeText(getApplicationContext(), "Usuario creado exitosamente.", Toast.LENGTH_SHORT).show();
                                // Informacion de usuario
                                FirebaseUser user = mAuth.getCurrentUser();
                                // Cambio de ventana
                                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(i);
                            } else {
                                // Registro fallido
                                // Notificación al usuario de proceso fallido
                                Toast.makeText(getApplicationContext(), "Registro fallido, reintente.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }else{
            // Notificación al usuario de contraseñas no son iguales
            Toast.makeText(this, "Las contraseñas no corresponden, verifique", Toast.LENGTH_SHORT).show();
        }
    }
}