package com.example.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

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
        try {

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

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        validateCredential(view);
    }

    public void validateCredential(View view) {
        //recebe o valor introduzido no campo do email e password na tela de login
        EditText email_field = view.findViewById(R.id.editTextEmai);
        EditText pass_field = view.findViewById(R.id.editTextPassword);
        String email_in = email_field.getText().toString();
        String pass_in = pass_field.getText().toString();

        if (email_in.equals("") || pass_in.equals("")) {
            Toast.makeText(requireContext(), "Existem campos vazios!", Toast.LENGTH_SHORT).show();
        } else if (this.email.equals(email_in) && this.password.equals(pass_in)) {
            Toast.makeText(requireContext(), "Login feito com sucesso!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(requireContext(), "Credenciais errados!", Toast.LENGTH_SHORT).show();
        }
    }
}