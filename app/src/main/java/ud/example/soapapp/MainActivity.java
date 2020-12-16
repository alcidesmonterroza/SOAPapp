package ud.example.soapapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import ud.example.soapapp.Clases.WebService;

public class MainActivity extends AppCompatActivity {

    private Button miboton;
    private TextView salida;
    private TextView valoreuro;
    private EditText entrada;
    private RadioButton cambioP;
    private String editText;
    private String displayText;
    private String displayEuro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    cambioP = findViewById(R.id.radioButton);
    cambioP.setChecked(true);
    entrada = findViewById(R.id.editTextNumber);
    valoreuro = findViewById(R.id.textView2);
    salida = findViewById(R.id.textView3);
    miboton = findViewById(R.id.button);
    miboton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(entrada.getText().length() != 0 && entrada.getText().toString() != ""){
                editText = entrada.getText().toString();
               // AsyncCallWS task1 = new AsyncCallWS();
                CambioEuroWS task = new CambioEuroWS();
                task.execute();
                salida.setVisibility(view.VISIBLE);

            }
        }
    });

    LLamadoInicialWS capini = new LLamadoInicialWS();
    capini.execute();
    }

    private class LLamadoInicialWS extends AsyncTask<String,Void,Void> {

        @Override
        protected Void doInBackground(String... strings) {
            double temp = WebService.CapturaEuroWs("getEuro");
            DecimalFormat format = new DecimalFormat("#.00");

            displayEuro = format.format(temp) + " Pesos";
            return null;
        }
        @Override
        protected void onPostExecute(Void Result) {
            valoreuro.setText(displayEuro);
        }
        @Override
        protected void onPreExecute() {
            valoreuro.setText("Comenzó llamada inicial");
        }

    }

    private class CambioEuroWS extends AsyncTask<String,Void,Void> {

        @Override
        protected Void doInBackground(String... strings) {
            String tipomoneda;
            double temp;
            if (cambioP.isChecked() == true) {

                temp = WebService.CambioEuroWs("peso2euro", editText);

                tipomoneda =" Euros";

            } else {
                temp = WebService.CambioEuroWs("Euro2peso", editText);
                tipomoneda =" Pesos";

            }

            DecimalFormat format = new DecimalFormat("#.00");

            displayText = format.format(temp) + tipomoneda;

            return null;
        }
        @Override
        protected void onPostExecute(Void Result) {
            salida.setText(displayText);
        }
        @Override
        protected void onPreExecute() {
            salida.setText("Comenzó cambio");
        }
    }

    private class AsyncCallWS extends AsyncTask<String,Void,Void>{

        @Override
        protected Void doInBackground(String... strings) {
            displayText = WebService.HolaMundoWS(editText,"Hello");
           return null;
        }
        @Override
        protected void onPostExecute(Void Result) {
            salida.setText(displayText);
        }
        @Override
        protected void onPreExecute() {
            salida.setText("Ya viene");
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            salida.setText("Ya va llegando");
        }
    }
}