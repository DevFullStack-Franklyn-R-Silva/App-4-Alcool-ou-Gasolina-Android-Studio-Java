package com.hadesfranklyn.lcoolougasolina;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
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
            alertDialog();
            Toast.makeText(getApplicationContext(), "Preencha os preços primeiro!", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(getApplicationContext(), "Melhor utilizar Gasolina", Toast.LENGTH_SHORT).show();
        } else {
            textoResultado.setText("Melhor utilizar Álcool");
            Toast.makeText(getApplicationContext(), "Melhor utilizar Álcool", Toast.LENGTH_SHORT).show();
        }
    }

    public void toastPersonalizado() {
        ImageView imagem = new ImageView(getApplicationContext());
        imagem.setImageResource(android.R.drawable.stat_notify_error);

        TextView textView = new TextView(getApplicationContext());
        textView.setBackgroundResource(R.color.colorAccent);
        textView.setText("Preencha os preços primeiro!");

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(imagem);
//        toast.setView(textView);
        toast.show();
    }

    public void alertDialog() {

        //Instancia AlertDialog
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        //Configurar titulo e mensagem
        dialog.setTitle("Título da dialog");
        dialog.setMessage("Mensagem da dialog");

        //Configurar cancelamento
        dialog.setCancelable(false);

        //Configurar icone
        dialog.setIcon(android.R.drawable.ic_btn_speak_now);

        //Configura acoes para sim e nao
        dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "Executar ação ao clicar no botão Sim", Toast.LENGTH_LONG).show();
            }
        });
        dialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "Executar ação ao clicar no botão Não", Toast.LENGTH_LONG).show();
            }
        });

        //Cria e exibir AlertDialog
        dialog.create();
        dialog.show();
    }
}