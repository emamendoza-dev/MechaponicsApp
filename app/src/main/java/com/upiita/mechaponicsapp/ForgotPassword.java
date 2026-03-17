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
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import cn.pedant.SweetAlert.SweetAlertDialog;
import android.graphics.Color;

public class ForgotPassword extends AppCompatActivity {

    // Variables de trabajo
    Button btnRecuperar;
    EditText etCorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        // Recuperacion de valores de la ventana
        btnRecuperar = findViewById(R.id.btnRecuperar);
        etCorreo = findViewById(R.id.edtUsuarioCR);
        // Función de botón presionado
        btnRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });
    }

    // Función para validar datos
    public void validate(){
        String email = etCorreo.getText().toString().trim();

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            // Error de correo invalido
            etCorreo.setError("Correo invalido");
            return;
        }
        sendEmail(email);
    }

    // Función para regresar a login con los botones de retroceso del teléfono
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(ForgotPassword.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    // Función para enviar un correo de recuperación al usuario con la dirección ingresada
    public void sendEmail(String email){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String emailAddress = email;

        auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            // Notificación al usuario de correo enviado
                            Toast.makeText(getApplicationContext(), "Correo enviado.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ForgotPassword.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            // Notificación de error al enviar correo
                            Toast.makeText(getApplicationContext(), "Correo invalido.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}