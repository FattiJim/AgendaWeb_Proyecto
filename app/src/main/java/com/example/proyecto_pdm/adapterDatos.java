package com.example.proyecto_pdm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class adapterDatos extends RecyclerView.Adapter<adapterDatos.ViewHolderDatos> {

    ArrayList<datos> listaDatos;

    public adapterDatos(ArrayList<datos> listaDatos) {
        this.listaDatos = listaDatos;
    }

    @NonNull
    @Override
    public adapterDatos.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null, false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterDatos.ViewHolderDatos holder, int position) {
    holder.asignarDatos(listaDatos.get(position));
    }

    @Override
    public int getItemCount() {
        return listaDatos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView txtId;
        TextView txtNom;
        TextView txtApp;
        TextView txtTel;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            txtId = (TextView) itemView.findViewById(R.id.txtId);
            txtNom = (TextView) itemView.findViewById(R.id.txtNom);
            txtApp = (TextView) itemView.findViewById(R.id.txtApp);
            txtTel = (TextView) itemView.findViewById(R.id.txtTel);
        }

        public void asignarDatos(datos d) {
            txtId.setText("ID: " + d.id);
            txtNom.setText("Nombre: " + d.nom);
            txtApp.setText("Apellido: " + d.app);
            txtTel.setText("Telefono: " + d.tel);
        }
    }
}
