package com.tclt.qrdolgozat;

import android.os.Environment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FajlbaIras
{

    public void kiiras(String szoveg) throws IOException
    {
        String allapot;
        File file;
        String szovegesAdat;
        Date date = Calendar.getInstance().getTime();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");

        String formatedDate = dateFormat.format(date);

        szovegesAdat = szoveg + "," + formatedDate + "," + "\r\n";

        allapot = Environment.getExternalStorageState();
        if (allapot.equals(Environment.MEDIA_MOUNTED))
        {
            file = new File(Environment.getExternalStorageDirectory(), "scannedCodes.csv");
            try
            {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true), 1024);
                bufferedWriter.append(szovegesAdat);
                bufferedWriter.close();
            }
            catch (Exception e)
            {
                System.out.println("Hiba: " + e);
            }
        }
    }
}