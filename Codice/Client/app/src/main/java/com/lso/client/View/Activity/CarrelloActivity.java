package com.lso.client.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.text.Layout;
import android.text.StaticLayout;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lso.client.Controller.BevandaController;
import com.lso.client.Controller.UtenteController;
import com.lso.client.Model.Bevanda;
import com.lso.client.Model.Utente;
import com.lso.client.R;
import com.lso.client.View.Adapter.CarrelloAdapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CarrelloActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AppCompatButton acquistaButton;

    private String emailCorrente;
    private Utente utenteCorrente;

    private TextView totaleCarrello;

    private ArrayList<Bevanda> bevandaArrayList;

    private CarrelloAdapter carrelloAdapter;

    private UtenteController utenteController = new UtenteController();
    private BevandaController bevandaController = new BevandaController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrello);

        recyclerView = findViewById(R.id.carrello_recyclerview);
        acquistaButton = findViewById(R.id.carrello_acquista_button);
        totaleCarrello = findViewById(R.id.totale_carrello_text);

        bevandaArrayList = new ArrayList<>();

        new Thread(()->{
            emailCorrente = getIntent().getExtras().getString("utenteEmail");
            utenteCorrente = utenteController.getUtenteByEmail(emailCorrente);

            bevandaArrayList = bevandaController.getCarrelloByUtente(utenteCorrente);

            runOnUiThread(()->{
                totaleCarrello.setText(String.valueOf(calcolaTotale(bevandaArrayList))+" €");
            });

            carrelloAdapter = new CarrelloAdapter(this, bevandaArrayList);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

            runOnUiThread(()->{
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(carrelloAdapter);
            });
        }).start();

        acquistaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bevandaArrayList.size() > 0){
                    new Thread(()->{
                        for(Bevanda bevanda : bevandaArrayList)
                            bevandaController.acquistaBevanda(utenteCorrente, bevanda);

                        stampaRicevutaInPDF(bevandaArrayList);

                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        intent.putExtra("utenteEmail", utenteCorrente.getEmail());
                        startActivity(intent);
                    }).start();
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

    public float calcolaTotale(ArrayList<Bevanda> bevandaArrayList){
        float result = 0;

        for(Bevanda bevanda : bevandaArrayList)
            result += bevanda.getPrezzo();

        return result;
    }

    public void stampaRicevutaInPDF(ArrayList<Bevanda> bevandaArrayList){
        // Creiamo un nuovo documento PDF
        PdfDocument document = new PdfDocument();

        // Otteniamo la data corrente
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(new Date());

        // Calcoliamo l'altezza totale dell'elenco delle bevande
        Paint paint = new Paint();
        paint.setTextSize(36);
        paint.setTypeface(Typeface.DEFAULT);
        int lineHeight = 36;
        int listHeight = bevandaArrayList.size() * lineHeight;

        // Calcoliamo l'altezza totale della pagina
        int pageHeight = 216 + 72 + listHeight + 72 + 72 + lineHeight;

        // Creiamo una nuova pagina
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(595, pageHeight, 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        // Disegniamo il titolo "RICEVUTA" in rosso al centro
        paint.setColor(Color.RED);
        paint.setTextSize(72);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        int x = pageInfo.getPageWidth() / 2;
        int y = 216;
        page.getCanvas().drawText("RICEVUTA", x, y, paint);

        // Disegniamo l'elenco delle bevande
        paint.setColor(Color.BLACK);
        paint.setTextSize(36);
        paint.setTypeface(Typeface.DEFAULT);
        paint.setTextAlign(Paint.Align.LEFT);
        y += 72;
        double total = 0.0;
        for (Bevanda bevanda : bevandaArrayList) {
            String line = " • "+bevanda.getNome().toUpperCase() + " - " + bevanda.getPrezzo() + " €";
            page.getCanvas().drawText(line, 72, y, paint);
            y += lineHeight;
            total += bevanda.getPrezzo();
        }

        // Disegniamo il totale delle bevande al centro in basso
        paint.setTextSize(48);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setTextAlign(Paint.Align.CENTER);
        y += 72;
        String totalLine = "TOTALE: " + total + " €";
        int totalWidth = Math.round(paint.measureText(totalLine));
        page.getCanvas().drawText(totalLine, x, y - lineHeight, paint);

        // Concludiamo il documento PDF
        document.finishPage(page);

        // Salviamo il documento PDF sul dispositivo
        String filename = "ricevuta_" + currentDate + ".pdf";
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), filename);
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            document.writeTo(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        document.close();


    }
}