package com.example.reporteziutlan;

import android.content.Context;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class dataRepository {

    private static final String TAG = "DataRepository";
    private RequestQueue requestQueue;
    private Context context;

    public dataRepository(Context context) {
        this.context = context;
        this.requestQueue = Volley.newRequestQueue(context);
    }

    // Obtener usuarios desde la API
    public void getUsuarios(final VolleyCallback callback) {
        String url = "https://roldanhdz.pythonanywhere.com/api/usuarios/";

        // Usamos JsonArrayRequest porque la respuesta es un JSONArray.
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            ArrayList<Usuario> usuariosList = new ArrayList<>();
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject usuarioObj = response.getJSONObject(i);
                                int userId = usuarioObj.getInt("user_id");
                                String nombre = usuarioObj.getString("nombre");
                                String correo = usuarioObj.getString("correo");
                                String contraseña = usuarioObj.getString("contraseña");
                                String fotoPerfilUrl = usuarioObj.optString("foto_perfil");  // URL de la foto de perfil
                                String fechaRegistro = usuarioObj.getString("fecha_registro");
                                String estado = usuarioObj.getString("estado");

                                Usuario usuario = new Usuario(userId, nombre, correo, contraseña, fotoPerfilUrl, fechaRegistro, estado);
                                usuariosList.add(usuario);
                            }
                            callback.onSuccess(usuariosList);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            callback.onError("Error al obtener usuarios");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onError("Error en la conexión");
            }
        });
        requestQueue.add(request);
    }

    // Obtener perfiles de usuario desde la API
    public void getPerfilesDeUsuario(final VolleyCallback callback) {
        String url = "https://roldanhdz.pythonanywhere.com/api/perfiles/";

        // Usamos JsonArrayRequest porque la respuesta es un JSONArray.
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            ArrayList<PerfilDeUsuario> perfilesList = new ArrayList<>();
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject perfilObj = response.getJSONObject(i);
                                int perfilId = perfilObj.getInt("perfil_id");
                                int usuarioId = perfilObj.getInt("usuario");
                                int totalReportes = perfilObj.getInt("total_reportes");
                                int totalApoyos = perfilObj.getInt("total_apoyos");

                                PerfilDeUsuario perfil = new PerfilDeUsuario(perfilId, usuarioId, totalReportes, totalApoyos);
                                perfilesList.add(perfil);
                            }
                            callback.onSuccess(perfilesList);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            callback.onError("Error al obtener perfiles de usuario");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onError("Error en la conexión");
            }
        });
        requestQueue.add(request);
    }

    // Obtener entidades oficiales desde la API
    public void getEntidadesOficiales(final VolleyCallback callback) {
        String url = "https://roldanhdz.pythonanywhere.com/api/entidades/";

        // Usamos JsonArrayRequest porque la respuesta es un JSONArray.
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            ArrayList<EntidadOficial> entidadesList = new ArrayList<>();
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject entidadObj = response.getJSONObject(i);
                                int entidadId = entidadObj.getInt("entidad_id");
                                String nombre = entidadObj.getString("nombre");
                                String categoria = entidadObj.getString("categoria");
                                String urlOficial = entidadObj.getString("url_oficial");

                                EntidadOficial entidad = new EntidadOficial(entidadId, nombre, categoria, urlOficial);
                                entidadesList.add(entidad);
                            }
                            callback.onSuccess(entidadesList);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            callback.onError("Error al obtener entidades oficiales");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onError("Error en la conexión");
            }
        });
        requestQueue.add(request);
    }

    // Obtener reportes desde la API
    public void getReportes(final VolleyCallback callback) {
        String url = "https://roldanhdz.pythonanywhere.com/api/reportes/";

        // Usamos JsonArrayRequest porque la respuesta es un JSONArray.
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            ArrayList<Reporte> reportesList = new ArrayList<>();
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject reporteObj = response.getJSONObject(i);
                                int reporteId = reporteObj.getInt("reporte_id");
                                int usuarioId = reporteObj.getInt("usuario");
                                String fechaReporte = reporteObj.getString("fecha_reporte");
                                double ubicacionLatitud = reporteObj.getDouble("ubicacion_latitud");
                                double ubicacionLongitud = reporteObj.getDouble("ubicacion_longitud");
                                String tipo = reporteObj.getString("tipo");
                                String descripcion = reporteObj.getString("descripcion");
                                String fotoReporteUrl = reporteObj.optString("foto_reporte");
                                String fotoReferenciaUrl = reporteObj.optString("foto_referencia");
                                String estadoReporte = reporteObj.getString("estado_reporte");
                                int entidadId = reporteObj.getInt("entidad");
                                // Recuperar el nombre del usuario usando getUsuarios
                                getUsuarios(new VolleyCallback() {
                                    @Override
                                    public void onSuccess(Object response) {
                                        ArrayList<Usuario> usuarios = (ArrayList<Usuario>) response;
                                        String nombreUsuario = "";
                                        for (Usuario usuario : usuarios) {
                                            if (usuario.getUserId() == usuarioId) {
                                                nombreUsuario = usuario.getNombre();
                                                break;
                                            }
                                        }
                                        // Recuperar el nombre de la entidad usando getEntidadesOficiales
                                        String finalNombreUsuario = nombreUsuario;
                                        getEntidadesOficiales(new VolleyCallback() {
                                            @Override
                                            public void onSuccess(Object response) {
                                                ArrayList<EntidadOficial> entidades = (ArrayList<EntidadOficial>) response;
                                                String nombreEntidad = "";
                                                for (EntidadOficial entidad : entidades) {
                                                    if (entidad.getEntidadId() == entidadId) {
                                                        nombreEntidad = entidad.getNombre();
                                                        break;
                                                    }
                                                }
                                                // Crear el reporte con los nombres de usuario y entidad
                                                Reporte reporte = new Reporte(reporteId, finalNombreUsuario, fechaReporte, ubicacionLatitud, ubicacionLongitud,
                                                        tipo, descripcion, fotoReporteUrl, fotoReferenciaUrl, estadoReporte, nombreEntidad);
                                                reportesList.add(reporte);
                                                // Una vez que se procesan todos los reportes, devolvemos la lista
                                                if (reportesList.size() == ((ArrayList<?>) response).size()) {
                                                    callback.onSuccess(reportesList);
                                                }
                                            }
                                            @Override
                                            public void onError(String error) {
                                                callback.onError("Error al obtener entidades oficiales");
                                            }
                                        });
                                    }
                                    @Override
                                    public void onError(String error) {
                                        callback.onError("Error al obtener usuarios");
                                    }
                                });
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            callback.onError("Error al obtener reportes");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onError("Error en la conexión");
            }
        });
        requestQueue.add(request);
    }


    // Obtener apoyos en reportes desde la API
    public void getApoyosEnReportes(final VolleyCallback callback) {
        String url = "https://roldanhdz.pythonanywhere.com/api/apoyos/";

        // Usamos JsonArrayRequest porque la respuesta es un JSONArray.
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            ArrayList<ApoyoEnReporte> apoyosList = new ArrayList<>();
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject apoyoObj = response.getJSONObject(i);
                                int apoyoId = apoyoObj.getInt("apoyo_id");
                                int usuarioId = apoyoObj.getInt("usuario");
                                int reporteId = apoyoObj.getInt("reporte");
                                String fechaApoyo = apoyoObj.getString("fecha_apoyo");

                                ApoyoEnReporte apoyo = new ApoyoEnReporte(apoyoId, usuarioId, reporteId, fechaApoyo);
                                apoyosList.add(apoyo);
                            }
                            callback.onSuccess(apoyosList);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            callback.onError("Error al obtener apoyos");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onError("Error en la conexión");
            }
        });
        requestQueue.add(request);
    }

    // Obtener comentarios en reportes desde la API
    public void getComentariosEnReportes(final VolleyCallback callback) {
        String url = "https://roldanhdz.pythonanywhere.com/api/comentarios/";

        // Usamos JsonArrayRequest porque la respuesta es un JSONArray.
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            ArrayList<ComentarioEnReporte> comentariosList = new ArrayList<>();
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject comentarioObj = response.getJSONObject(i);
                                int comentarioId = comentarioObj.getInt("comentario_id");
                                int usuarioId = comentarioObj.getInt("usuario");
                                int reporteId = comentarioObj.getInt("reporte");
                                String contenido = comentarioObj.getString("contenido");
                                String fechaComentario = comentarioObj.getString("fecha_comentario");

                                ComentarioEnReporte comentario = new ComentarioEnReporte(comentarioId, usuarioId, reporteId, contenido, fechaComentario);
                                comentariosList.add(comentario);
                            }
                            callback.onSuccess(comentariosList);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            callback.onError("Error al obtener comentarios");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onError("Error en la conexión");
            }
        });
        requestQueue.add(request);
    }

    // Callback para manejar las respuestas de la API
    public interface VolleyCallback {
        void onSuccess(Object response);
        void onError(String error);
    }
}
