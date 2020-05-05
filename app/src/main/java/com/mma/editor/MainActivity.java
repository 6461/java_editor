package com.mma.editor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    Context context = null;
    EditText filename = null;
    EditText text = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;
        filename = findViewById(R.id.editFilename);
        text = findViewById(R.id.editText);
    }

    private String getFilename() {
        String filename = this.filename.getText().toString();

        return filename;
    }

    public void readFile(View view) {
        try {
            InputStream is = context.openFileInput(getFilename());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            text.getText().clear();

            while ((line = br.readLine()) != null) {
                System.out.println(line);
                text.append(line + "\n");
            }
            is.close();

        } catch (IOException e) {
            Log.e("IOException", "Virhe");
        }
    }

    public void writeFile(View view) {
        try {
            OutputStream os = context.openFileOutput(getFilename(), Context.MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(os);

            String data = text.getText().toString();
            osw.write(data);
            osw.close();
        } catch (IOException e) {
            Log.e("IOException", "Virhe");
        }
    }
}
