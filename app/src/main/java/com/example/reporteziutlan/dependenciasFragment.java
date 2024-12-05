package com.example.reporteziutlan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class dependenciasFragment extends Fragment {

    private ListView listView;
    private dependenciaAdapter adapter;
    private ArrayList<EntidadOficial> entidadesList;

    public dependenciasFragment() {
        // Constructor vacío requerido por Fragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el layout para este fragmento
        View view = inflater.inflate(R.layout.fragment_dependencias, container, false);

        // Inicializar componentes
        listView = view.findViewById(R.id.listViewDependencias);
        entidadesList = new ArrayList<>();
        adapter = new dependenciaAdapter(getContext(), entidadesList);
        listView.setAdapter(adapter);

        // Obtener los datos de los reportes desde el DataRepository
        fetchEntidadesOficiales();

        return view;
    }

    private void fetchEntidadesOficiales() {
        dataRepository repository = new dataRepository(getContext());
        repository.getEntidadesOficiales(new dataRepository.VolleyCallback() {
            @Override
            public void onSuccess(Object response) {
                // Aquí, 'response' es un Object, así que lo convertimos a ArrayList<EntidadOficial>
                if (response instanceof ArrayList<?>) {
                    ArrayList<EntidadOficial> entidades = (ArrayList<EntidadOficial>) response;
                    entidadesList.clear();
                    entidadesList.addAll(entidades);
                    adapter.notifyDataSetChanged();
                }
            }


            @Override
            public void onError(String error) {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}