package com.lso.client.View.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;
import com.lso.client.Controller.UtenteController;
import com.lso.client.Model.Utente;
import com.lso.client.R;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class SignupActivity extends AppCompatActivity {

    private EditText usernameText;
    private EditText passwordText;
    private EditText confirmPasswordText;
    private EditText nomeText;
    private EditText cognomeText;

    private Button registraVoltoButton;

    private Button registraButton;
    private Context context;

    private UtenteController utenteController = new UtenteController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        context = this;

        usernameText = findViewById(R.id.username_text_signup);
        passwordText = findViewById(R.id.password_text_signup);
        confirmPasswordText = findViewById(R.id.confirm_password_text_signup);
        nomeText = findViewById(R.id.nome_text_signup);
        cognomeText = findViewById(R.id.cognome_text_signup);

        registraVoltoButton = findViewById(R.id.registra_volto_button_signup);

        registraButton = findViewById(R.id.registra_button_signup);

        registraVoltoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        registraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameText.getText().toString();
                String password = passwordText.getText().toString();
                String confirmPassword = confirmPasswordText.getText().toString();
                String nome = nomeText.getText().toString();
                String cognome = cognomeText.getText().toString();

                if(!username.isEmpty() && !password.isEmpty() && !confirmPassword.isEmpty()){
                    if(password.equals(confirmPassword)){

                        new Thread(()->{
                            Utente utente = new Utente(username, password, nome, cognome);
                            utenteController.registraUtente(utente);



                            runOnUiThread(()->{
                                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                                alertDialog.setTitle("COMPLIMENTI");
                                alertDialog.setMessage("Ti sei registrato correttamente ! ");
                                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Ok!",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                alertDialog.dismiss();
                                                Intent intent = new Intent(context, LoginActivity.class);
                                                startActivity(intent);
                                            }
                                        });
                                alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                                    @Override
                                    public void onShow(DialogInterface dialogInterface) {
                                        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
                                    }
                                });
                                alertDialog.show();
                            });
                        }).start();

                    }
                }
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        //This is used to hide/show 'Status Bar' & 'System Bar'. Swip bar to get it as visible.
        View decorView = getWindow().getDecorView();
        if (hasFocus) {
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
}