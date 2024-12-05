package com.example.reporteziutlan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class cargarUsuario extends AppCompatActivity {

    private EditText nombreEditText, correoEditText, contrasenaEditText;
    private Button regisButton;
    private RequestQueue requestQueue;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargar_usuario);

        nombreEditText = findViewById(R.id.nombre);
        correoEditText = findViewById(R.id.correo);
        contrasenaEditText = findViewById(R.id.contraseña);
        regisButton = findViewById(R.id.regis_button);

        requestQueue = Volley.newRequestQueue(this);

        regisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuario();
            }
        });
    }

    private void registrarUsuario() {
        String nombre = nombreEditText.getText().toString().trim();
        String correo = correoEditText.getText().toString().trim();
        String contrasena = contrasenaEditText.getText().toString().trim();
        String fechaRegistro = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        if (nombre.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Registrando usuario...");
        progressDialog.show();

        Map<String, String> params = new HashMap<>();
        params.put("nombre", nombre);
        params.put("correo", correo);
        params.put("contraseña", contrasena);
        params.put("fecha_registro", fechaRegistro);
        params.put("estado", "activo");  // Campo agregado con valor fijo

        JSONObject jsonObject = new JSONObject(params);

        String url = "https://roldanhdz.pythonanywhere.com/api/usuarios/";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        Toast.makeText(cargarUsuario.this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show();
                        finish(); // Finaliza la actividad actual y regresa a la actividad anterior
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(cargarUsuario.this, "Error en el registro", Toast.LENGTH_SHORT).show();
                Log.e("Volley", error.toString());
            }
        });

        requestQueue.add(jsonObjectRequest);
    }
}
