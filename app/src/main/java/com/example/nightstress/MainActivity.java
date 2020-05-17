package com.example.nightstress;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private static final String FILE_NAME = "Data.csv";
    public Button get;
    public Button write;
    public EditText titl;
    public EditText descr;

    String title;
    String desc;
    String combined;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        write = findViewById(R.id.b_write);
        get = findViewById(R.id.b_get);
        titl = findViewById(R.id.subject);
        descr = findViewById(R.id.desc);

        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            FileOutputStream fos = null;
            String beginning = "Title,Description,Date,Mood" + "\n";

            title = titl.getText().toString();
            desc = descr.getText().toString();
            combined = title + "," +desc;

                try {
                    fos = openFileOutput(FILE_NAME,MODE_PRIVATE);
                    fos.write(beginning.getBytes());
                    fos.write(combined.getBytes());
                    toast("saved to " + getFilesDir());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (fos != null)
                    {
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }



            }
        });

        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            FileInputStream fis = null;

                try {
                    fis = openFileInput(FILE_NAME);
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader br = new BufferedReader(isr);
                    StringBuilder sb = new StringBuilder();
                    String text;

                    while( (text = br.readLine())!=null){
                        sb.append(text).append("\n");
                    }

                    toast(sb.toString());

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(fis !=null)
                    {
                        try{
                            fis.close();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }


            }
        });

    }
    public void toast(String written)
    {
        Toast t = Toast.makeText(getApplicationContext(),written,Toast.LENGTH_SHORT);
        t.show();
    }
}
