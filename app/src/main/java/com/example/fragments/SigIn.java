package com.example.fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

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

        /*//Accção para o botão Entrar
        Button signIn = view.findViewById(R.id.button);
        signIn.setOnClickListener(v -> {
            logIn(v);
        });*/


        // Inflate the layout for this fragment
        return view;
    }

    public void back() {
        Intent intent = new Intent(getContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //tira a tela atual do historico
        startActivity(intent);
    }

    public void logIn(View view) { //faz o login

        //pega os valores criados no arquivo xml da class createAccount
        try {
            // Cria um FileInputStream para ler o arquivo de dados do aplicativo
            FileInputStream fileInputStream = requireContext().openFileInput("arquivo.xml");

            // Cria uma instância da classe XmlPullParserFactory para criar um objeto XmlPullParser
            XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();

            // Cria um objeto XmlPullParser a partir da fábrica e define o arquivo de entrada
            XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();
            xmlPullParser.setInput(fileInputStream, null);

            // Obtém o tipo de evento atual do parser
            int eventType = xmlPullParser.getEventType();

            // Processa o arquivo XML enquanto não chegar ao fim do documento
            while (eventType != XmlPullParser.END_DOCUMENT) {
                // Verifica se o tipo de evento é uma tag de abertura
                if (eventType == XmlPullParser.START_TAG) {
                    // Obtém o nome da tag atual
                    String tagName = xmlPullParser.getName();

                    // Verifica se o nome da tag é "string"
                    if (tagName.equals("string")) {
                        // Obtém o valor do atributo "name" da tag "string"
                        String name = xmlPullParser.getAttributeValue(null, "name");
                        // Obtém o valor do elemento de texto da tag "string"
                        String value = xmlPullParser.nextText();
                        // Exibe o nome e valor da string no LogCat
                        switch (name){
                            case "username":
                                this.userName = value;

                            case "email":
                                this.email = value;

                            case "password":
                                this.password = value;

                        }
                    }
                }

                // Obtém o próximo evento do parser
                eventType = xmlPullParser.next();
            }

            // Fecha o FileInputStream
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        //recebe o valor introduzido no campo do email e password na tela de login
        EditText email_field = (EditText) view.findViewById(R.id.editTextEmai);
        EditText pass_field = (EditText) view.findViewById(R.id.editTextPassword);
        String email_in = email_field.getText().toString();
        String pass_in = pass_field.getText().toString();

        if(email_in.equals("") || pass_in.equals("")){
            Toast.makeText(requireContext(), "Existem campos vazios!", Toast.LENGTH_SHORT).show();
        }
        else if(this.email.equals(email_in) && this.password.equals(pass_in)){
            Toast.makeText(requireContext(), "Login feito com sucesso!", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(requireContext(), "Credenciais errados!", Toast.LENGTH_SHORT).show();
        }
    }
}