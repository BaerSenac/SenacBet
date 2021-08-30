package com.example.senacbet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import org.jetbrains.annotations.NotNull;

public class FormCadastro extends AppCompatActivity {

    private EditText nome_cadastro, email_cadastro, senha_cadastro;
    private Button button_cadastro_login;
    String[] mensagens = {"Preencha todos os campos", "Cadastro realizado com sucesso"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro);

        getSupportActionBar().hide();
        IniciarComponentes();

        button_cadastro_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome = nome_cadastro.getText().toString();
                String email = email_cadastro.getText().toString();
                String senha = senha_cadastro.getText().toString();

                if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(v,mensagens[0],Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                } else {
                    CadastrarUsuario(v);
                }

            }
        });
    }


    private void CadastrarUsuario(View v) {

        String email = email_cadastro.getText().toString();
        String senha = senha_cadastro.getText().toString();

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {

                if ((task.isSuccessful())) {
                    Snackbar snackbar = Snackbar.make(v, mensagens[1],Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();


                } else {
                    String erro;
                    try {
                        throw task.getException();

                    } catch (FirebaseAuthWeakPasswordException e) {
                        erro = "Digite uma senha com no minino 6 caracteres";
                    } catch (FirebaseAuthUserCollisionException e) {
                        erro = "esta conta ja esta cadastrada";
                    } catch (FirebaseAuthInvalidCredentialsException e){
                        erro = "E-mail invalido";
                    } catch (Exception e) {
                        erro = "Erro ao cadastrar o usuario";

                    }
                    Snackbar snackbar = Snackbar.make(v,erro,Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }
            }
        });



    }

    private void IniciarComponentes(){
        nome_cadastro = findViewById(R.id.nome_cadastro);
        email_cadastro = findViewById(R.id.email_cadastro);
        senha_cadastro = findViewById(R.id.senha_cadastro);
        button_cadastro_login = findViewById(R.id.button_cadastrar_login);
    }

}




