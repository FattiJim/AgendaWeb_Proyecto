package com.example.proyecto_pdm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class MostrarActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Button btnRegresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);

        btnRegresar = (Button) findViewById(R.id.btnRegresar);

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        mostrar();
    }

    public void mostrar(){
        String url = "https://serviciosdigitalesplus.com/agendaweb/?tipo=1&llave=201909321&r=" + new Random().nextInt();
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonResponse = null;
                try {
                    jsonResponse = new JSONObject(response);
                } catch (JSONException e){
                    throw new RuntimeException(e);
                }
                JSONArray json = jsonResponse.optJSONArray("lista");

                JSONObject obj = null;
                ArrayList<datos> listaDatos = null;
                listaDatos = new ArrayList<datos>();
                if(json != null){
                    //Iterar a traves de JSONArray
                    for(int i=0; i < json.length(); i++){
                        try{
                            //Obtener cada objeto json individual
                            JSONObject object = json.getJSONObject(i);

                            //Hacer algo con cada objeto obtenido
                            String nom = object.getString("nom");
                            String app = object.getString("app");
                            String tel = object.getString("tel");
                            String id = object.getString("id");

                            listaDatos.add(new datos(id, nom, app, tel));

                            //Aqui se hace lo necesario con los valores obtenidos
                        } catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                    adapterDatos adapter = new adapterDatos(listaDatos);
                    recyclerView.setAdapter(adapter);
                }
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