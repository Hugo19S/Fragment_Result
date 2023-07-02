package com.example.fragments;

import android.app.AlertDialog;
import android.app.Person;
import android.content.Context;
import android.os.Bundle;
import android.util.Xml;
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
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class SignUp extends Fragment {

    private String userName;
    private String password;
    private String email;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        Button register = view.findViewById(R.id.button4);
        register.setOnClickListener(v -> {
            escreverJSON(requireContext());
        });

        //Accção para o botão cancelar
        Button cancel = view.findViewById(R.id.button5);
        cancel.setOnClickListener(v -> {
            SigIn sigIn = new SigIn();
            FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_login, sigIn);
            transaction.commit();
        });

        // Inflate the layout for this fragment
        return view;
    }

    public void escreverJSON(Context context) {
        // Cria um objeto JSON
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("nome", "João");
            jsonObject.put("idade", 25);
            jsonObject.put("cidade", "São Paulo");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Converte o objeto JSON em uma string
        String jsonString = jsonObject.toString();

        // Cria um objeto de arquivo e especifica o caminho e nome do arquivo
        File file = new File(context.getFilesDir(), "arquivo.json");

        // Escreve os dados no arquivo
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(jsonString);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*public void create(View view) {

        if (validateCredential(view)) {
            Toast.makeText(requireContext(), "Existem campos vazios", Toast.LENGTH_SHORT).show();

            try {
                XmlSerializer xmlSerializer = Xml.newSerializer();
                FileOutputStream fileOutputStream = requireContext().openFileOutput("arquivo.xml", Context.MODE_PRIVATE);
                xmlSerializer.setOutput(fileOutputStream, "UTF-8");
                xmlSerializer.startDocument(null, true);
                xmlSerializer.startTag(null, "resources");

                xmlSerializer.startTag(null, "string");
                xmlSerializer.attribute(null, "name", "username");
                xmlSerializer.text(this.userName);
                xmlSerializer.endTag(null, "string");

                xmlSerializer.startTag(null, "string");
                xmlSerializer.attribute(null, "name", "email");
                xmlSerializer.text(this.email);
                xmlSerializer.endTag(null, "string");

                xmlSerializer.startTag(null, "string");
                xmlSerializer.attribute(null, "name", "password");
                xmlSerializer.text(this.password);
                xmlSerializer.endTag(null, "string");

                xmlSerializer.endTag(null, "resources");
                xmlSerializer.endDocument();
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            AlertDialog.Builder dialog = new AlertDialog.Builder(requireContext());
            dialog.setTitle("Confirmação");
            dialog.setMessage("A sua conta foi criado com sucesso!");
            dialog.setCancelable(false);
            dialog.setPositiveButton("Ok", (dialogInterface, i) -> {

                SigIn sigIn = new SigIn();
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_login, sigIn);
                transaction.commit();
            });
            dialog.create().show();

        }

    }

    private boolean validateCredential(View view) {
        EditText user_field = (EditText) view.findViewById(R.id.editPersonName);
        EditText email_field = (EditText) view.findViewById(R.id.EmailAddress);
        EditText password_field = (EditText) view.findViewById(R.id.Password);
        EditText passwordConfirm_field = (EditText) view.findViewById(R.id.editPassword2);

        String user = user_field.getText().toString();
        String e_mail = email_field.getText().toString();
        String passWord = password_field.getText().toString();
        String passWordConfirm = passwordConfirm_field.getText().toString();

        if (user.equals("") || e_mail.equals("") || passWord.equals("") || passWordConfirm.equals("")) {
            Toast.makeText(requireContext(), "Existe campo vazio!", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            if (passWord.equals(passWordConfirm)) {
                this.userName = user;
                this.email = e_mail;
                this.password = passWord;
                for (String s : Arrays.asList("user: " + user, "e_mail: " + e_mail, "passWord: " + passWord, "passWordConfirm: " + passWordConfirm)) {
                    System.out.println(s);
                }
                return true;
            }
            else {
                Toast.makeText(requireContext(), "Por favor confirma a sua senha!", Toast.LENGTH_SHORT).show();
                return false;
            }


        }

    }*/
}