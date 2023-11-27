package com.example.proyecto_pdm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Random;

public class CrearActivity extends AppCompatActivity {

    EditText txtNom;
    EditText txtApp;
    EditText txtTel;
    Button btnAceptar;
    Button btnRegresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear);

        txtNom = (EditText) findViewById(R.id.txtNom);
        txtApp = (EditText) findViewById(R.id.txtApp);
        txtTel = (EditText) findViewById(R.id.txtTel);

        btnAceptar = (Button) findViewById(R.id.btnCrear);
        btnRegresar = (Button) findViewById(R.id.btnRegresar);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviar(v);
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void enviar(View v){
        String nom = txtNom.getText().toString();
        String app = txtApp.getText().toString();
        String tel = txtTel.getText().toString();
        String error = "";

        if(nom.equals("")){
            error += "Ingresa el nombre\n";
        }
        if(app.equals("")){
            error += "Ingresa el apellido\n";
        }
        if(tel.equals("")){
            error += "Ingresa el telefono\n";
        }
        if(error.equals("")){
            String url = "https://serviciosdigitalesplus.com/agendaweb/?tipo=2&llave=201938614&nom=" + nom + "&app=" + app + "&tel=" + tel + "&r=" + new Random().nextInt();
            RequestQueue queue = Volley.newRequestQueue(this);

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    txtNom.setText("");
                    txtApp.setText("");
                    txtTel.setText("");

                    alertaInfo("Se agregó el contacto exitosamente");
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    alertaInfo(error.getMessage().toString());
                }
            }
            );
            queue.add(stringRequest);
        }
        else {
            alertaInfo(error);
        }
    }

    public void alertaInfo(String line){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Información")
                .setMessage(line)
                .setCancelable(true)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                    }
                }).show();
    }
}