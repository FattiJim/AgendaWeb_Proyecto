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

public class ModificarActivity extends AppCompatActivity {

    EditText txtId;
    EditText txtNom;
    EditText txtApp;
    EditText txtTel;
    Button btnModificar;
    Button btnRegresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        txtId = (EditText) findViewById(R.id.txtId);
        txtNom = (EditText) findViewById(R.id.txtNom);
        txtApp = (EditText) findViewById(R.id.txtApp);
        txtTel = (EditText) findViewById(R.id.txtTel);

        btnModificar = (Button) findViewById(R.id.btnModificar);
        btnRegresar = (Button) findViewById(R.id.btnRegresar);

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificar(v);
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void modificar(View v){
        String id = txtId.getText().toString();
        String nom = txtNom.getText().toString();
        String app = txtApp.getText().toString();
        String tel = txtTel.getText().toString();
        String error = "";

        if(id.equals("")){
            error += "Ingresa el ID\n";
        }
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
            String url = "https://serviciosdigitalesplus.com/agendaweb/?tipo=3&id=" + id + "llave=201909321&nom=" + nom + "&app=" + app + "&tel=" + tel + "&r=" + new Random().nextInt();
            RequestQueue queue = Volley.newRequestQueue(this);

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    txtId.setText("");
                    txtNom.setText("");
                    txtApp.setText("");
                    txtTel.setText("");

                    alertaInfo("Se modificó el contacto exitosamente");
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