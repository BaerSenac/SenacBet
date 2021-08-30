package com.example.senacbetapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CadastroContatoDao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_contato_dao);

        Button buttonCadastrar = findViewById(R.id.activity_principal_button_cadastrar_contato);
        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contato contato = new Contato();

                EditText nome = findViewById(R.id.activity_principal_nome_apostador);
                if (nome != null && nome.getText() != null && !nome.getText().toString().equals("")) {
                    contato.setNome(nome.getText().toString());                                         // o botão escuta o comando e cria o contato buscando o campoText criado
                    nome.setText("");

                    EditText saldo = findViewById(R.id.activity_principal_saldo_apostador);
                    contato.setSaldo(saldo.getText().toString());
                    saldo.setText("");

                    EditText email = findViewById(R.id.activity_principal_email_apostador);
                    contato.setEmail(email.getText().toString());
                    email.setText("");
                }
            }
        });
        }
}