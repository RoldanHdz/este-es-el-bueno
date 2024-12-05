package com.example.reporteziutlan;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.Manifest;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;


public class mapaFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.fragment_mapa, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //verificar si los permisos de ubicación están encendidos
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //Solicitar permisos si no están concedidos
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1);

            return;
        }

        mMap.setMyLocationEnabled(true);


        fusedLocationProviderClient.getLastLocation()
                .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());

                            mMap.addMarker(new MarkerOptions().position(currentLocation).title("Tu ubicación"));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation,15));
                        }
                    }

                });
        addPointsToMap();
    }

    private void addPointsToMap() {
        // Acceder al repositorio de datos
        dataRepository repository = new dataRepository(getContext());
        // Usamos el callback para obtener los reportes desde el repositorio
        repository.getReportes(new dataRepository.VolleyCallback() {
            @Override
            public void onSuccess(Object response) {
                // Convertir la respuesta en una lista de Reporte
                ArrayList<Reporte> reportes = (ArrayList<Reporte>) response;
                // Agregar marcadores al mapa usando la latitud y longitud de los reportes
                for (Reporte reporte : reportes) {
                    double lat = reporte.getUbicacionLatitud();
                    double lng = reporte.getUbicacionLongitud();
                    // Crear un objeto LatLng con la latitud y longitud
                    LatLng puntoReporte = new LatLng(lat, lng);
                    // Agregar un marcador en el mapa con el tipo de reporte como título
                    mMap.addMarker(new MarkerOptions().position(puntoReporte).title(reporte.getTipo()));
                }
                // Centrar el mapa en el primer reporte (opcional)
                if (!reportes.isEmpty()) {
                    LatLng firstPoint = new LatLng(reportes.get(0).getUbicacionLatitud(), reportes.get(0).getUbicacionLongitud());
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(firstPoint, 15));
                }
            }
            @Override
            public void onError(String error) {
                // Mostrar error en caso de problemas con la clase de reportes
                Log.e("Error", error);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                onMapReady(mMap);
            }
        }
    }
}

