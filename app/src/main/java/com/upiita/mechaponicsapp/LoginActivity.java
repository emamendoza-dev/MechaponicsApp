package com.upiita.mechaponicsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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

import java.util.regex.Pattern;
import cn.pedant.SweetAlert.SweetAlertDialog;
import android.graphics.Color;

public class LoginActivity extends AppCompatActivity {

    //Variables para autenticacion
    private FirebaseAuth mAuth;
    private EditText correo;
    private EditText contrasena;
    Button btnIniciarSes;
    TextView contrasenaOlvi;

    // Función onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Instancia para autenticación de Firebase
        mAuth = FirebaseAuth.getInstance();
        // Campos para autenticación
        correo = findViewById(R.id.edtEmail);
        contrasena = findViewById(R.id.edtContrasena);
        btnIniciarSes = findViewById(R.id.btnInciarS);
        contrasenaOlvi = findViewById(R.id.tvContrasenaOlvidada);
        // Función de boton presionado
        btnIniciarSes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });
        // Funcion para contraseña olvidada
        contrasenaOlvi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, ForgotPassword.class);
                startActivity(i);
                finish();
            }
        });
    }

    // Función onStart
    @Override
    public void onStart() {
        super.onStart();
        // Comprobar si el usuario ha iniciado sesión y actualice la interfaz de usuario
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    // Función para validar datos
    public void validate(){
        String email = correo.getText().toString().trim();
        String pass = contrasena.getText().toString().trim();

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            // Error de campo de correo vacío o dirección invalida
            correo.setError("Correo invalido");
            return;
        }else{
            correo.setError(null);
        }
        if (pass.isEmpty() || pass.length() < 8){
            // Error de contraseña incorrecta
            contrasena.setError("Contraseña incorrecta");
            return;
        }else{
            // Inciar sesión en la aplicación
            iniciarSesion(email, pass);
        }
    }

    // Funcion de iniciar sesión con las credenciales
    public void iniciarSesion(String email, String pass){
        // Se autentica con el Email y contraseña del usuario
        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    // Funcion para autenticar las credenciales
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // Comprobación de la autenticacion
                        if (task.isSuccessful()) {
                            // Inicio de sesión correcto
                            // Notificacion al usuario de proceso exitoso
                            Toast.makeText(getApplicationContext(), "Inicio de sesion exitoso.", Toast.LENGTH_SHORT).show();
                            // Informacion de usuario
                            FirebaseUser user = mAuth.getCurrentUser();
                            // Cambio de ventana
                            Intent i = new Intent(getApplicationContext(), InitActivity.class);
                            startActivity(i);
                            finish();
                        } else {
                            // Inicio de sesión incorrecto
                            // Notificación al usuario de proceso fallido
                            Toast.makeText(getApplicationContext(), "Autenticación fallida, reintente", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    // Función para registrarse
    public void irRegistarse(View view){
        // Cambio a ventana de registro
        Intent i = new Intent(this, RegistroActivity.class);
        startActivity(i);
        finish();
    }

}