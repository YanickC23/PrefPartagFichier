package com.example.prefpartagfichier;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;



public class StockageExterne extends AppCompatActivity {

    Button btnEnrFichier;
    EditText txtTexteFichier;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stockage_externe);

        btnEnrFichier = findViewById(R.id.btnFichierExt);
        txtTexteFichier = findViewById(R.id.txtFichierExt);



        btnEnrFichier.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                SauvegardeFichier();
            }

        });


    }






    public void SauvegardeFichier(){

        String etatStockage = Environment.getExternalStorageState();
        if(etatStockage.equals(Environment.MEDIA_MOUNTED)|| etatStockage.equals(Environment.MEDIA_MOUNTED_READ_ONLY)){

            Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TITLE, "fichierStock.txt");
            startActivityForResult(intent, 55);
        }
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










    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            if(requestCode == 55){

                uri = data.getData();

                try{
                    String contenu = txtTexteFichier.getText().toString();
                    OutputStream os = getContentResolver().openOutputStream(uri, "w");
                    os.write(contenu.getBytes());
                    os.close();

                }
                catch(FileNotFoundException e){
                    e.printStackTrace();
                }
                catch (IOException e){
                    e.printStackTrace();
                }

            }
        }
    }




}
