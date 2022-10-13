package com.ptrkcsak.coinflip;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int win, lose, korokSzama = 0;
    private Button btnFej, btnIras;
    private TextView textOutWin, textOutLose;
    private ImageView imgCoin;
    private AlertDialog.Builder builderJatekVege;
    Random rnd = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            init();
            ujJatek();
            kiir();

                btnFej.setOnClickListener(view -> {
                    if (gepValaszt()) {
                        win++;
                    } else {
                        lose++;
                    }
                    kiir();
                    korokSzama++;
                    ellenorzes();
                });

                btnIras.setOnClickListener(view -> {
                    if (!gepValaszt()) {
                        win++;
                    } else {
                        lose++;
                    }
                    kiir();
                    korokSzama++;
                    ellenorzes();
                });
    }

    private void ellenorzes() {
        if (korokSzama == 5 || win >= 3 || lose >= 3){
            if (win>lose){
                builderJatekVege.setTitle("Nyertél").create();
                builderJatekVege.show();
            }else{
                builderJatekVege.setTitle("Vesztettél").create();
                builderJatekVege.show();
            }
        }
    }

    private void ujJatek() {
        win = 0;
        lose = 0;
        korokSzama = 0;
    }

    public void init() {
        btnFej = (Button)findViewById(R.id.btnFej);
        btnIras = (Button)findViewById(R.id.btnIras);

        textOutLose = (TextView)findViewById(R.id.textOutLose);
        textOutWin = (TextView)findViewById(R.id.textOutWin);

        imgCoin = (ImageView)findViewById(R.id.imgCoin);

        builderJatekVege = new AlertDialog.Builder(MainActivity.this);
        builderJatekVege.setCancelable(false)
                .setTitle("Nyert / Vesztet")
                .setMessage("Szeretne új játékot játszani?")
                .setNegativeButton("Nem", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setPositiveButton("Igen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ujJatek();
                        kiir();
                    }
                })
                .create();
    }

    public boolean gepValaszt() {
        boolean valaszt = rnd.nextBoolean();
        if (valaszt) {
            imgCoin.setBackgroundResource(R.drawable.heads);
            return valaszt;
        } else {
            imgCoin.setBackgroundResource(R.drawable.tails);
            return valaszt;
        }
    }

    public void kiir() {
        textOutWin.setText("Win: "+Integer.toString(win));
        textOutLose.setText("Lose: "+Integer.toString(lose));
    }

}