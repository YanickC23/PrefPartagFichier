package com.example.prefpartagfichier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Connexion extends AppCompatActivity {

    boolean utilConnect;
    Button btnConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        btnConnect = findViewById(R.id.btnConnect);



        Connect_Deconnect();



        SharedPreferences prefPartagConnect = getSharedPreferences("ConnexionUtilisateur", MODE_PRIVATE);
        utilConnect = prefPartagConnect.getBoolean("Connecte", false);



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


    public void ChoixDate(View view) {
        //Toast.makeText(this, "ERREUR", Toast.LENGTH_LONG).show();
        DialogFragment dialogFragment = new ChoixDate();
        dialogFragment.show(getSupportFragmentManager(), "choix_date");
    }

    public void ChoixHeure(View view) {
        //Toast.makeText(this, "ERREUR", Toast.LENGTH_LONG).show();
        DialogFragment dialogFragment = new ChoixHeure();
        dialogFragment.show(getSupportFragmentManager(), "choix_heure");
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

    private void Connect_Deconnect(){

        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(utilConnect == false){
                    utilConnect = true;
                }
                else{
                    utilConnect = false;
                }
                SharedPreferences prefPartagConnect = getSharedPreferences("ConnexionUtilisateur", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefPartagConnect.edit();
                editor.putBoolean("Connecte", utilConnect);
                editor.commit();
            }
        });

    }







}
