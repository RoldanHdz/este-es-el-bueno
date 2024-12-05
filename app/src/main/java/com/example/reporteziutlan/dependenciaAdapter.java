package com.example.reporteziutlan;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class dependenciaAdapter extends ArrayAdapter<EntidadOficial> {
    private Context context;

    public dependenciaAdapter(Context context, List<EntidadOficial> dependencias) {
        super(context, 0, dependencias);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EntidadOficial dependencia = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.dependencia_list_item, parent, false);
        }

        TextView textNombre = convertView.findViewById(R.id.item_nombre);
        TextView textCategoria = convertView.findViewById(R.id.item_categoria);
        Button textBoton = convertView.findViewById(R.id.item_boton);

        textNombre.setText("Dependencia: \n" + dependencia.getNombre());
        textCategoria.setText("Categoría: \n" + dependencia.getCategoria());

        textBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = dependencia.getUrlOficial();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}
