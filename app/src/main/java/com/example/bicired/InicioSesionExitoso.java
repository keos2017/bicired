package com.example.bicired;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.Extension;

import static java.lang.Thread.sleep;

public class InicioSesionExitoso extends AppCompatActivity implements View.OnClickListener {

    private Button btn_cerrar;
    //Firebase
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion_exitoso);
        Log.e("paso por aqui", "oncreate");


        ImageView imageView = (ImageView) findViewById(R.id.image_descarga);
        imageView.setOnClickListener(this);


        btn_cerrar = (Button) findViewById(R.id.btn_cerrar_sesion);

        btn_cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(InicioSesionExitoso.this, MainActivity.class));
            }
        });
    }

    @Override
    public void onClick(View view) {
        Log.e("paso por aqui", "onclick");
        switch (view.getId()) {
            case R.id.image_descarga:
            descargaPDF();
            break;
        }


    }

    void descargaPDF() {
        String urlADescargar = "https://img1.wsimg.com/blobby/go/8d6c24da-4875-44c5-a47b-0068c8284fef/downloads/1c72ant1v_634770.pdf?ver=1580344866546";

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMessage("Descargando PDF... ");

        new DescargarPDFAsyncTack(progressDialog).execute(urlADescargar);


    }

    class DescargarPDFAsyncTack extends AsyncTask<String,Integer,String>{
        ProgressDialog progressDialog;
        DescargarPDFAsyncTack(ProgressDialog progressDialog){
            this.progressDialog = progressDialog;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... urlPDF) {
            String urlADescargar = urlPDF[0];

            HttpURLConnection conexion = null;
            InputStream input = null;
            OutputStream output = null;


            try {
                URL url = new URL(urlADescargar);
                conexion = (HttpURLConnection ) url.openConnection();
                conexion.connect();

                if (conexion.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return "Conexion no realizada correctamente..";
            }

            input =conexion.getInputStream();
            String rutaFicheroGuardado = getFilesDir() + "RutasMTBCundimarca.pdf";//data/data/com.example.bicired/RutasMTBCundimarca.pdf
            output = new FileOutputStream(rutaFicheroGuardado);

            int tamanoFichero = conexion.getContentLength();

            byte[] data  = new byte[1024];
            int total = 0;
            int count;

            while ((count = input.read(data)) !=-1){
                sleep(100);
                output.write(data, 0, count);
                total += count;
                publishProgress((int) (total * 100  / tamanoFichero) );

                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Error: " + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Error: " + e.getMessage();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (input!=null) input.close();
                    if (output!=null) output.close();
                    if (conexion!=null) conexion.disconnect();


                } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            return "Se realizo la descarga correctamente";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressDialog.setIndeterminate(false);
            progressDialog.setMax(100);
            progressDialog.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String mensaje) {
            super.onPostExecute(mensaje);
            progressDialog.dismiss();
            Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
        }
    }

}

