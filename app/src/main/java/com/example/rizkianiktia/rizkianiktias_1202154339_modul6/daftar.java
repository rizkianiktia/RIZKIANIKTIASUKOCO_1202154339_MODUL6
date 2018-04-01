package com.example.rizkianiktia.rizkianiktias_1202154339_modul6;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class daftar extends AppCompatActivity {
    EditText user; EditText pass; ProgressDialog dlg;
    FirebaseAuth auth; FirebaseAuth.AuthStateListener listener;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        //Deklarasi semua objek yang digunakan pada class ini
        user = findViewById(R.id.upuser); pass = findViewById(R.id.uppass);
        dlg = new ProgressDialog(this);
        auth = FirebaseAuth.getInstance();
        listener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = auth.getCurrentUser();
                if(user!=null){
                    Intent movehome = new Intent(daftar.this, home.class);
                    movehome.addFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
                    startActivity(movehome);
                }
            }
        };

    }

    //Method untuk activity ketika dimulai
    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(listener);
    }

    //Method ketika activity berakhir
    @Override
    protected void onStop() {
        super.onStop();
        auth.removeAuthStateListener(listener);
    }

    //Method untuk membuat akun
    public void daftarin(View view) {
        //Method untuk menampilkan messages
        dlg.setMessage("Creating account. . .");
        dlg.show();

        //Digunakan untuk membaca input dari user
        String inuser = user.getText().toString().trim();
        String inpass = pass.getText().toString().trim();

        //Apakah input user tersebut kosong?

        //Jika tidak :
        if(!TextUtils.isEmpty(inuser)||!TextUtils.isEmpty(inpass)){
            //Membuat user baru berdasarkan input user
            auth.createUserWithEmailAndPassword(inuser, inpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    //Kondisi jika pembuatan user berhasil
                    if(task.isSuccessful()){
                        Toast.makeText(daftar.this, "Account has been created!", Toast.LENGTH_SHORT).show();
                        Intent movehome = new Intent(daftar.this, login.class);
                        movehome.addFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
                        finish();

                    //Kondisi ketika pembuatan user baru gagal
                    }else{
                        Log.w("Firebase", task.getException());
                        Toast.makeText(daftar.this, "Account creation failed!", Toast.LENGTH_SHORT).show();
                        user.setText(null); pass.setText(null);
                    }
                    //Tutup dialog ketika kondisi berhasil atau pun tidak
                    dlg.dismiss();
                }
            });

        //Ketika input user kosong
        }else{
            Toast.makeText(this, "The field is empty", Toast.LENGTH_SHORT).show();
            user.setText(null); pass.setText(null);
        }

    }
}
