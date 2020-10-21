package com.example.prefpartagfichier;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    TextView txtZoneTexte, txtFichTexte;
    EditText txtEdit;
    Button btnEnregistrer;
    String Texte;
    boolean utilConnect;
    File fichier;
    String nomFichier = "FichierTexte";
    Uri uri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = this;
        Intent intent = getIntent();

        txtZoneTexte = findViewById(R.id.txtTexte);
        txtEdit = findViewById(R.id.etxZoneText);
        btnEnregistrer = findViewById(R.id.btnEnregistrer);
        txtFichTexte = findViewById(R.id.txtFichierExt);

        SharedPreferences prefPartagConnect = getSharedPreferences("ConnexionUtilisateur", MODE_PRIVATE);
        SharedPreferences prefZoneTexte = getSharedPreferences("TexteUtilisateur", MODE_PRIVATE);
        Texte = prefZoneTexte.getString("TexteZone", "VIDE");


        //fichier = new File(getApplicationContext().getFilesDir(), "FichierTexte");


        utilConnect = prefPartagConnect.getBoolean("Connecte", false);

        EntrerTexte();


        if(utilConnect == true){
            try {
                FileInputStream fileInputStream = openFileInput("FichierTexte");
                BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
                StringBuffer sb = new StringBuffer();
                String ligne = reader.readLine();

                while (ligne != null){
                    sb.append(ligne);
                    ligne = reader.readLine();
                }
                txtZoneTexte.setText(sb.toString());

            }
            catch(Exception e){
                e.printStackTrace();
            }



        }
        else{

        }


        OuvrirFichExt();


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){

            if(requestCode == 88){


                uri = data.getData();

                try {
                    StringBuilder stringBuilder = new StringBuilder();

                    InputStream inputStream = getContentResolver().openInputStream(uri);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)));

                    String ligne;

                    while((ligne = reader.readLine()) != null){
                        stringBuilder.append(ligne);
                    }
                    txtFichTexte.setText(stringBuilder.toString());
                }
                catch (FileNotFoundException e){
                    e.printStackTrace();
                }
                catch(IOException e){
                    e.printStackTrace();
                }



            }
        }



    }





    private void OuvrirFichExt(){
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/plain");
        startActivityForResult(intent, 88);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mn_connexion, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.mnConnect).setVisible(true);

        return super.onPrepareOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {

        switch (menuItem.getItemId()) {

            case R.id.mnConnect:
                Activite_Connexion();
                return true;

            case R.id.mnStockExt:
                Activite_StockExterne();
                return true;

            case R.id.mnActPrinc:
                Activite_Principale();
                return true;

            default:
                return super.onOptionsItemSelected(menuItem);
        }


    }



    private void Activite_Connexion() {
        Intent intent = new Intent(getApplicationContext(), Connexion.class);
        startActivityForResult(intent, 11);

    }

    private void Activite_StockExterne() {
        Intent intent = new Intent(getApplicationContext(), StockageExterne.class);
        startActivityForResult(intent, 11);

    }

    private void Activite_Principale() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(intent, 11);

    }

    private void EntrerTexte(){

        btnEnregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Texte = txtEdit.getText().toString();
                Toast.makeText(getApplicationContext(), Texte, Toast.LENGTH_LONG).show();

                FileOutputStream outputStream;
                try {
                    outputStream = openFileOutput(nomFichier, Context.MODE_PRIVATE);
                    outputStream.write(Texte.getBytes());
                    outputStream.close();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }








}
