package com.example.rizkianiktia.rizkianiktias_1202154339_modul6;

import com.google.firebase.database.IgnoreExtraProperties;

//Enkapsulasi data comment
@IgnoreExtraProperties
public class DBcommand {
    String yangkomen, komentar, foto;

    //Dibutuhkan kosong untuk membaca data
    public DBcommand(){

    }

    //Constructor dari class ini
    public DBcommand(String yangkomen, String komennya, String fotoyangdikomen) {
        this.yangkomen = yangkomen;
        this.komentar = komennya;
        this.foto = fotoyangdikomen;
    }

    //Sisany getter untuk variabel dari class ini
    public String getFotoyangdikomen() {
        return foto;
    }

    public String getYangkomen() {
        return yangkomen;
    }

    public String getKomennya() {
        return komentar;
    }

}
