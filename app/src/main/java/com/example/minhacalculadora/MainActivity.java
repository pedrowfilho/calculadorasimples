package com.example.minhacalculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    EditText num1, num2;
    TextView result;
    Spinner oper;
    Button botaoCalcular;
    int posicao = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = findViewById(R.id.editTextNumero1);
        num2 = findViewById(R.id.editTextNumero2);
        result = findViewById(R.id.textViewResultado);
        oper = (Spinner) findViewById(R.id.spinnerOperacao);
        botaoCalcular = (Button) findViewById(R.id.btnCalcular);

        botaoCalcular.setOnClickListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.operacoes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        oper.setAdapter(adapter);
        oper.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View view) {
        if(num1.getText().toString().equals("") || num2.getText().toString().equals("")){
            Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();
        }else {
            double n1 = Double.parseDouble(num1.getText().toString());
            double n2 = Double.parseDouble(num2.getText().toString());
            double r = 0;
            switch (posicao) {
                case 0:
                    r = n1 + n2;
                    break;
                case 1:
                    r = n1 - n2;
                    break;
                case 2:
                    r = n1 * n2;
                    break;
                case 3:
                    if (n2 == 0) {
                        Toast.makeText(this, "Não foi possível efetuar a operação!\nDivisão por zero.", Toast.LENGTH_SHORT).show();
                        result.setText("0");
                    } else {
                        r = n1 / n2;
                    }
            }
            result.setText("" + r);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        posicao = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}