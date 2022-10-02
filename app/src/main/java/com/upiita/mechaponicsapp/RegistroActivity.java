package com.upiita.mechaponicsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class RegistroActivity extends AppCompatActivity {

    //Variables para autenticacion
    private FirebaseAuth mAuth;
    private EditText correo;
    private EditText contrasena;
    private EditText confContrasena;
    Button btnRegistrar;

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
        btnRegistrar = findViewById(R.id.btnRecuperar);
        // Función del boton de registro
        btnRegistrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                validate();
            }
        });
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

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    // Función para validar datos
    public void validate(){
        String email = correo.getText().toString().trim();
        String pass = contrasena.getText().toString().trim();
        String passconf = confContrasena.getText().toString().trim();

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            // Error mostrado ante un campo vacio o correo invalido
            correo.setError("Correo invalido");
            return;
        }else{
            correo.setError(null);
        }
        if (pass.isEmpty() || pass.length() < 8){
            // Error de contraseña menor a 8 caracteres
            contrasena.setError("Se necesitan más de 8 caracteres");
            return;
        }else if(!Pattern.compile("[0-9]").matcher(pass).find()){
            // Error de contraseña vulnerable
            contrasena.setError("La contraseña debe tener al menos un número");
            return;
        }else{
            contrasena.setError(null);
        }
        if (!passconf.equals(pass)){
            // Error de contraseñas no coinciden
            confContrasena.setError("Las contraseñas no coinciden");
        }else{
            registrarUsuario(email, pass);
        }
    }

    // Funcion para registrar un usuario
    public void registrarUsuario(String email, String password){
        // Verificacion de campos de contraseña llenados correctamente
        mAuth.createUserWithEmailAndPassword(email, password)
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
    }
}