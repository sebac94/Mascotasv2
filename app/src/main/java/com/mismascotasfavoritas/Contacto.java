package com.mismascotasfavoritas;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Contacto extends AppCompatActivity {
    public TextView nombre, email, mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frm_contacto);
        Toolbar customActionBar = findViewById(R.id.custom_action_bar);
        setSupportActionBar(customActionBar);
        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setDisplayHomeAsUpEnabled(true);
        nombre= findViewById(R.id.txt_nombreContacto);
        email= findViewById(R.id.txt_mailContacto);
        mensaje= findViewById(R.id.txt_descripcion);
        Button enviar=findViewById(R.id.btn_enviar);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail(nombre,email,mensaje);
            }
        });

    }


    public void sendEmail(TextView nombre, TextView mail, TextView texto) {

        String email = mail.getText().toString().trim();
        String subject = nombre.getText().toString().trim();
        String message = texto.getText().toString().trim();
            }

}
