package com.tclt.qrdolgozat;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.IOException;

public class MainActivity extends AppCompatActivity
{

    private Button btnScan, btnKiir;
    private TextView textSzoveg;
    private FajlbaIras fajlbaIras;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        btnScan.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("QR Code Scaning by app");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });

        btnKiir.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {
                    fajlbaIras.kiiras(textSzoveg.getText().toString());
                    Toast.makeText(MainActivity.this, "Sikeres fájlbaírás!", Toast.LENGTH_SHORT).show();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Sikertelen fájlbaírás!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void init()
    {
        btnScan = findViewById(R.id.btnScan);
        btnKiir = findViewById(R.id.btnKiir);
        textSzoveg = findViewById(R.id.textSzoveg);
        fajlbaIras = new FajlbaIras();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null)
        {
            if (result.getContents() == null)
            {
                Toast.makeText(this, "Kiléptél a scannelésből!", Toast.LENGTH_SHORT).show();
            }
            else
            {
                textSzoveg.setText(result.getContents());
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
