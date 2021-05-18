package com.whosmyqueen.signdemo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.whosmyqueen.signpad.views.SignaturePad;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_clear)
    Button btn_clear;
    @BindView(R.id.btn_save)
    Button btn_save;
    @BindView(R.id.signature_pad)
    SignaturePad paintView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = paintView.getTransparentSignatureBitmap(true);
                if (bitmap == null)
                    return;
                try {
                    OutputStream outputStream = new FileOutputStream(new File(Environment.getExternalStorageDirectory
                            (), "1.png"));
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                    outputStream.flush();
                    outputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paintView.clear();
            }
        });
        //        getSignatureBitmap() - A signature bitmap with a white background.
        //        getTransparentSignatureBitmap() - A signature bitmap with a transparent background.
        //        getSignatureSvg() - A signature Scalable Vector Graphics document.
        paintView.setMinWidth(2.5f);
        paintView.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {

            }

            @Override
            public void onSigned() {

            }

            @Override
            public void onClear() {

            }
        });

    }
}
