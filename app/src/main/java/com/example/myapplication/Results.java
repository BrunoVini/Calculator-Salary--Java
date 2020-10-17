package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Results extends AppCompatActivity {

    double TOTAL_DESCONTOS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        DecimalFormat df = new DecimalFormat("#.##");

        Intent intent = getIntent();
        double SALARIO_BRUTO = intent.getDoubleExtra(MainActivity.SALARIOBRUTOREAL, 0);
        double DECONTO_INSS = intent.getDoubleExtra(MainActivity.DESCONTOINSSREAL, 0);
        double DESCONTO_IRFF = intent.getDoubleExtra(MainActivity.DESCONTOIRFFREAl, 0);
        double SALARIO_LIQUIDO = intent.getDoubleExtra(MainActivity.SALARIOLIQUIDOREAL, 0);
        double DESCONTOS = intent.getDoubleExtra(MainActivity.OUTROSDESCONTOSREAL, 0);

        TOTAL_DESCONTOS = ( 100 * (SALARIO_BRUTO - SALARIO_LIQUIDO) ) / SALARIO_BRUTO;


        TextView salariobruto = findViewById(R.id.salarioBruto);
        TextView descontoinss = findViewById(R.id.descontoINSS);
        TextView descontoirff = findViewById(R.id.descontoIRFF);
        TextView outrosDescontos = findViewById(R.id.outrosDescontos);
        TextView salarioliquido = findViewById(R.id.salarioLiquido);
        TextView totaldescontos = findViewById(R.id.totalDecontos);

        salariobruto.setText(df.format(SALARIO_BRUTO) + "R$");
        descontoinss.setText('-' + df.format(DECONTO_INSS) + "R$");
        descontoirff.setText('-' + df.format(DESCONTO_IRFF) + "R$");
        outrosDescontos.setText('-' + df.format(DESCONTOS) + "R$");
        salarioliquido.setText(df.format(SALARIO_LIQUIDO) + "R$");
        totaldescontos.setText(df.format(TOTAL_DESCONTOS) + '%');

    }

    public void voltar (View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}