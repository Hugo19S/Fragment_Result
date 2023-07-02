package com.example.fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SigIn extends Fragment {

    private String email; //recebe o valor do email guardado no ficheiro
    private String password; //recebe o valor do password guardado no ficheiro
    private String userName; //recebe o valor do usuario guardado no ficheiro

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sig_in, container, false);

        //Accção para o botão voltar
        Button back = view.findViewById(R.id.button3);
        back.setOnClickListener(v -> {
            back();
        });

        //Accção para o botão Criar conta
        Button signUp = view.findViewById(R.id.button2);
        signUp.setOnClickListener(v -> {
            SignUp signUpToLogin = new SignUp();

            FragmentTransaction fragment = requireActivity().getSupportFragmentManager().beginTransaction();
            fragment.replace(R.id.fragment_login, signUpToLogin);
            fragment.commit();

        });

        //Accção para o botão Entrar
        Button signIn = view.findViewById(R.id.button);
        signIn.setOnClickListener(v -> {
            readJson(view);
        });


        // Inflate the layout for this fragment
        return view;
    }

    public void back() {
        Intent intent = new Intent(getContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //tira a tela atual do historico
        startActivity(intent);
    }

    public void readJson(View view) {

        //recebe o valor introduzido no campo do email e password na tela de login
        EditText email_field = view.findViewById(R.id.editTextEmai);
        EditText pass_field = view.findViewById(R.id.editTextPassword);
        String email_in = email_field.getText().toString();
        String pass_in = pass_field.getText().toString();
        try {

            if (!email_in.equals("") && !pass_in.equals("")) {
                // Cria um objeto de arquivo com o caminho e nome do arquivo
                File file = new File(requireContext().getFilesDir(), "arquivo.json");

                // Lê o conteúdo do arquivo
                StringBuilder stringBuilder = new StringBuilder();
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                reader.close();

                String jsonString = stringBuilder.toString();

                // Processar a string JSON conforme necessário
                JSONObject jsonObject = new JSONObject(jsonString);
                this.userName = jsonObject.getString("userName");
                this.email = jsonObject.getString("email");
                this.password = jsonObject.getString("password");

                validateCredential(view, email_in, pass_in);
            } else {
                Toast.makeText(requireContext(), "Existem campos vazios!", Toast.LENGTH_SHORT).show();
            }


        } catch (IOException | JSONException e) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(requireContext());
            dialog.setTitle("Login");
            dialog.setMessage("Utilizador não encontrado!");
            dialog.setCancelable(false);
            dialog.setPositiveButton("Ok", (dialogInterface, i) -> {

            });
            dialog.create().show();
        }
    }

    public void validateCredential(View view, String email_in, String pass_in) {

        if (this.email.equals(email_in) && this.password.equals(pass_in)) {
            Toast.makeText(requireContext(), "Login feito com sucesso!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(requireContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } else {
            Toast.makeText(requireContext(), "Credenciais errados!", Toast.LENGTH_SHORT).show();
        }
    }

    private void dialogBox() {

    }
}