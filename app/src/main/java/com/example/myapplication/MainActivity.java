package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    double SALARIOBRUTO;
    double DEPENDENTES;
    double OUTROSDESCONTOS;

    double descontoIRFF;
    double descontoINSS;

    double IRFF;
    double INSS;
    double outrosDecontos;
    double salarioLiquido;
    double descontos;

    boolean resultado;


    EditText salarioBruto, dependentes, outrosDescontos;

    public final static String SALARIOBRUTOREAL = "com.example.myapplication.SALARIO";
    public final static String DESCONTOINSSREAL = "com.example.myapplication.INSS";
    public final static String DESCONTOIRFFREAl = "com.example.myapplication.IRFF";
    public final static String SALARIOLIQUIDOREAL = "com.example.myapplication.LIQUIDO";
    public final static String OUTROSDESCONTOSREAL = "com.example.myapplication.DECONTOS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        salarioBruto = (EditText)findViewById(R.id.salarioBruto);
        dependentes = (EditText)findViewById(R.id.dependentes);
        outrosDescontos = (EditText)findViewById(R.id.outrosDescontos);

        dependentes.setText("0");
        outrosDescontos.setText("00.00");

    }

     public void calcular (View view){

        //if(salarioBruto.getText().toString() > 0){
          processarDados();
        // }else{
            // Toast.makeText(this, "Insira um salário", Toast.LENGTH_LONG).show();
            // Toast.makeText(this, dependentes.getText().toString(), Toast.LENGTH_LONG).show();
        // };

     };



    public void processarDados () {
        SALARIOBRUTO = Double.parseDouble(salarioBruto.getText().toString());
        DEPENDENTES = Double.parseDouble(dependentes.getText().toString());
        OUTROSDESCONTOS = Double.parseDouble(outrosDescontos.getText().toString());

        Intent intent = new Intent(this, Results.class);
        //  Toast.makeText(this, SALARIOBRUTO, Toast.LENGTH_LONG).show();


        if(SALARIOBRUTO <= 1045.00){
            descontoINSS = 0;
        }else if((SALARIOBRUTO <= 1045.01) && (SALARIOBRUTO >=  2089.60) ){
            descontoINSS = ((SALARIOBRUTO * 7.5) / 100) - 15.67;
        }else if((SALARIOBRUTO <= 2089.61) && (SALARIOBRUTO >=  3134.40) ){
            descontoINSS = ((SALARIOBRUTO * 9) / 100) - 78.36;
        }else if((SALARIOBRUTO <= 3134.41) && (SALARIOBRUTO >=  6101.06) ){
            descontoINSS = ((SALARIOBRUTO * 12) / 100) - 141.05;
        }else {
            descontoINSS = (SALARIOBRUTO * 14) / 100;
        }

        if(descontoINSS > 713.10){
            descontoINSS = 713.10;
        }

        salarioLiquido = SALARIOBRUTO - descontoINSS;


        if(salarioLiquido <= 1903.00){
            descontoIRFF = 0;
        }else if((salarioLiquido >= 1903.01) && (salarioLiquido <=  2826.65) ){
            descontoIRFF = ((salarioLiquido * 7.5) / 100) + ( (DEPENDENTES * 189.59) - 142.80);
        }else if((salarioLiquido >= 2826.66) && (salarioLiquido <=  3751.05) ){
            descontoIRFF = ((salarioLiquido * 15) / 100) + ( (DEPENDENTES * 189.59) - 354.80);
        }else if((salarioLiquido >= 3751.06) && (salarioLiquido <=  4664.68) ){
            descontoIRFF = ((salarioLiquido * 22.5) / 100) + ( (DEPENDENTES * 189.59) - 636.13);
        }else {
            descontoIRFF = ((salarioLiquido * 27.5) / 100) + ( (DEPENDENTES * 189.59) - 869.36);
        }


        salarioLiquido = (salarioLiquido - descontoIRFF) - OUTROSDESCONTOS;

        intent.putExtra(SALARIOBRUTOREAL, SALARIOBRUTO);
        intent.putExtra(DESCONTOINSSREAL, descontoINSS);
        intent.putExtra(DESCONTOIRFFREAl, descontoIRFF);
        intent.putExtra(SALARIOLIQUIDOREAL, salarioLiquido);
        intent.putExtra(OUTROSDESCONTOSREAL, OUTROSDESCONTOS);

        startActivity(intent);
    }

}