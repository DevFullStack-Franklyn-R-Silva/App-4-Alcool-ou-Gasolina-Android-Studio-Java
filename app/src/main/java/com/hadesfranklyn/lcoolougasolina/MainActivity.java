package com.hadesfranklyn.lcoolougasolina;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText editPrecoAlcool, editPrecoGasolina;
    private TextView textoResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editPrecoAlcool = findViewById(R.id.editPrecoAlcool);
        editPrecoGasolina = findViewById(R.id.editPrecoGasolina);
        textoResultado = findViewById(R.id.textoResultado);
    }

    public void calcularPreco(View view) {

        //recuperar valores digitados
        String precoAlcool = editPrecoAlcool.getText().toString();
        String precoGasolina = editPrecoGasolina.getText().toString();

        //validar os campos digitados
        Boolean camposValidados = validarCampos(precoAlcool, precoGasolina);

        if (camposValidados) {
            calcular(precoAlcool, precoGasolina);

        } else {
            Toast.makeText(getApplicationContext(),"Preencha os preços primeiro!",Toast.LENGTH_SHORT).show();
            textoResultado.setText("Preencha os preços primeiro!");
        }
    }

    public Boolean validarCampos(String pAlcool, String pGasolina) {

        Boolean camposValidados = true;

        if (pAlcool == null || pAlcool.equals("")) {
            camposValidados = false;
        } else if (pGasolina == null || pGasolina.equals("")) {
            camposValidados = false;
        }
        return camposValidados;
    }

    public void calcular(String precoAlcool, String precoGasolina) {
        //Convertendo string para numeros
        Double valorAlcool = Double.parseDouble(precoAlcool);
        Double valorGasolina = Double.parseDouble(precoGasolina);

        /**
         * Fazer calculo de menor preco
         * Se (valorAlcool / valorGasolina) >= 0.7 é melhor utilizar gasolina
         * Senao e melhor utilizar alcool
         */
        Double resultado = valorAlcool / valorGasolina;
        if (resultado >= 0.7) {
            textoResultado.setText("Melhor utilizar Gasolina");
            Toast.makeText(getApplicationContext(),"Melhor utilizar Gasolina",Toast.LENGTH_SHORT).show();
        } else {
             textoResultado.setText("Melhor utilizar Álcool");
            Toast.makeText(getApplicationContext(),"Melhor utilizar Álcool",Toast.LENGTH_SHORT).show();
        }
    }

}