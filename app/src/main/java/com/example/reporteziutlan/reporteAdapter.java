package com.example.reporteziutlan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.util.List;

public class reporteAdapter extends ArrayAdapter<Reporte> {

    private Context context;

    public reporteAdapter(Context context, List<Reporte> reportes) {
        super(context, 0, reportes);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtener el reporte actual
        Reporte reporte = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.reporte_list_item, parent, false);
        }

        // Referencias a los elementos del diseño
        ImageView userImage = convertView.findViewById(R.id.userimage);
        TextView textUsuario = convertView.findViewById(R.id.textUsuario);
        TextView textFecha = convertView.findViewById(R.id.textfecha);
        TextView textDescripcion = convertView.findViewById(R.id.textDescripcion);
        ImageView imageReporte1 = convertView.findViewById(R.id.reporte_image1);
        ImageView imageReporte2 = convertView.findViewById(R.id.reporte_image2);
        TextView textLatitud = convertView.findViewById(R.id.textLatitud);
        TextView textLongitud = convertView.findViewById(R.id.textLongitud);
        TextView textEstado = convertView.findViewById(R.id.textEstado);
        TextView textEntidad = convertView.findViewById(R.id.textEntidad);
        MapView mapView = convertView.findViewById(R.id.map);

        // Asignar valores
        userImage.setImageResource(R.mipmap.usuario_foreground);
        textUsuario.setText(reporte.getNombreUsuario());
        textDescripcion.setText(reporte.getDescripcion());
        textFecha.setText("Fecha: " + reporte.getFechaReporte());
        textEstado.setText("Estado: " + reporte.getEstadoReporte());
        textLatitud.setText("Lat: " + reporte.getUbicacionLatitud());
        textLongitud.setText("Lon: " + reporte.getUbicacionLongitud());
        textEntidad.setText("Entidad: " + reporte.getNombreEntidad());

        // Cargar imágenes
        if (reporte.getFotoReporteUrl() != null && !reporte.getFotoReporteUrl().isEmpty()) {
            Picasso.get().load(reporte.getFotoReporteUrl()).into(imageReporte1);
        } else {
            imageReporte1.setImageResource(R.mipmap.fondologin_foreground); // Imagen por defecto
        }

        if (reporte.getFotoReferenciaUrl() != null && !reporte.getFotoReferenciaUrl().isEmpty()) {
            Picasso.get().load(reporte.getFotoReferenciaUrl()).into(imageReporte2);
        } else {
            imageReporte2.setImageResource(R.mipmap.fondologin_foreground); // Imagen por defecto
        }

        // Configurar el mapa
        mapView.onCreate(null);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                googleMap.getUiSettings().setScrollGesturesEnabled(false);
                googleMap.getUiSettings().setZoomGesturesEnabled(false);

                LatLng location = new LatLng(reporte.getUbicacionLatitud(), reporte.getUbicacionLongitud());
                googleMap.addMarker(new MarkerOptions().position(location).title(reporte.getTipo()));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
            }
        });

        return convertView;
    }
}
