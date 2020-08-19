package com.example.mytrdos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage;
import com.google.firebase.ml.naturallanguage.languageid.FirebaseLanguageIdentification;

public class MainActivity extends AppCompatActivity {

    TextView resulTv;
    EditText edt;
    Button identifity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resulTv = findViewById(R.id.tvResult);
        edt = findViewById(R.id.etName);
        identifity = findViewById(R.id.btnIdentity);

        identifity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseLanguageIdentification languageIdentifier =
                        FirebaseNaturalLanguage.getInstance().getLanguageIdentification();
                languageIdentifier.identifyLanguage(edt.getText().toString())
                        .addOnSuccessListener(
                                new OnSuccessListener<String>() {
                                    @Override
                                    public void onSuccess(@Nullable String languageCode) {
                                        if (languageCode != "und") {
                                            resulTv.setText("result: " + languageCode);
                                        } else {
                                        }
                                    }
                                })
                        .addOnFailureListener(
                                new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Model couldn’t be loaded or other internal error.
                                        // ...
                                    }
                                });
            }
        });
    }
}