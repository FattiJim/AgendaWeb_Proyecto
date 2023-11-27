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

public class EliminarActivity extends AppCompatActivity {

    EditText txtId;
    Button btnEliminar;
    Button btnRegresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);

        txtId = (EditText) findViewById(R.id.txtId);

        btnEliminar = (Button) findViewById(R.id.btnEliminar);
        btnRegresar = (Button) findViewById(R.id.btnRegresar);

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { eliminar(v); }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void eliminar(View v){
        String id = txtId.getText().toString();
        String error = "";

        if(id.equals("")){
            error += "Ingresa el ID\n";
        }
        if(error.equals("")){
            String url = "https://serviciosdigitalesplus.com/agendaweb/?tipo=4&llave=201909321&id= " + id + "&r=" + new Random().nextInt();
            RequestQueue queue = Volley.newRequestQueue(this);

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    txtId.setText("");

                    alertaInfo("Se eliminó el contacto exitosamente");
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