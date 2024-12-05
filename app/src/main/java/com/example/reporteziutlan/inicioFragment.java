package com.example.reporteziutlan;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class inicioFragment extends Fragment {

    private ListView listView;
    private reporteAdapter adapter;
    private ArrayList<Reporte> reporteList;

    public inicioFragment() {
        // Constructor vacío
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el layout para este fragmento
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        // Inicializar componentes
        listView = view.findViewById(R.id.listViewReportes);
        reporteList = new ArrayList<>();
        adapter = new reporteAdapter(getContext(), reporteList);
        listView.setAdapter(adapter);

        // Configurar el FloatingActionButton
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), subirrepoActivity.class);
                startActivity(intent);
            }
        });

        // Obtener los datos de los reportes desde el DataRepository
        fetchReportes();

        return view;
    }

    private void fetchReportes() {
        dataRepository repository = new dataRepository(getContext());
        repository.getReportes(new dataRepository.VolleyCallback() {
            @Override
            public void onSuccess(Object response) {
                // Aquí, 'response' es un Object, así que lo convertimos a ArrayList<Reporte>
                if (response instanceof ArrayList<?>) {
                    ArrayList<Reporte> reportes = (ArrayList<Reporte>) response;
                    reporteList.clear();
                    reporteList.addAll(reportes);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(getContext(), "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
