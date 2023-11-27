 package com.example.proyecto_pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

 public class MainActivity extends AppCompatActivity {

    Button btnCrear;
    Button btnModificar;
    Button btnEliminar;
    Button btnMostrar;

     @Override
     protected void onCreate(Bundle savedInstanceState) {


         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);


         btnCrear = (Button) findViewById(R.id.button);
         btnModificar = (Button) findViewById(R.id.button2);
         btnEliminar = (Button) findViewById(R.id.button3);
         btnMostrar = (Button) findViewById(R.id.button4);

         btnCrear.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View view){
                 Intent i = new Intent(getApplicationContext(), CrearActivity.class);
                 startActivity(i);
             }
         });

         btnModificar.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View view){
                 Intent i = new Intent(getApplicationContext(), ModificarActivity.class);
                 startActivity(i);
             }
         });

         btnEliminar.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View view){
                 Intent i = new Intent(getApplicationContext(), EliminarActivity.class);
                 startActivity(i);
             }
         });

         btnMostrar.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View view){
                 Intent i = new Intent(getApplicationContext(), MostrarActivity.class);
                 startActivity(i);
             }
         });
     }
}