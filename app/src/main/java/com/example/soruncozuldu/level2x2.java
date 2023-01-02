package com.example.soruncozuldu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class level2x2 extends AppCompatActivity {

    String ev1="" ,ev2="";

    double kartPuan=0.0, evPuan= 0.0,evpuan1 = 0.0,evpuan2 =0.0,puan =0.0,puan1=0.0,puan2=0.0,kalan =0.0;


    Button start,open,close,next;
    MediaPlayer prolog,tebrikler,cgr,surebitti;



    TextView score1,score2,time;
    ImageView cardv00,cardv01,cardv10,cardv11;

    Integer[] kartDizisi = {100,101,200,201};

    int firstCard,secondCard;
    int clickedFirst,getClickedSecond;

    int cardNumber = 1;
    int turn = 1;

    double player1Points = 0, player2Points = 0;


    String cardb,cardn,cardp,cardep,carden; // bunu firestoredan al




//firebase kısmı

    //random ssecim icin

    Random rand = new Random();

    List<Integer> numbers = new ArrayList<Integer>();
    List<String> str = new ArrayList<String>();
    int [] sayi = new int[] {0,1,10,11,12,13,14,15,16,17,18};





    FirebaseFirestore db= FirebaseFirestore.getInstance();


    // Writing into the file


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2x2);
        //al
        //farklı random sayılar
        do
        {
            int next = rand.nextInt(44);
            if (!numbers.contains(next))
            {
                numbers.add(next);

            }
        } while (numbers.size() < 2);







        prolog = MediaPlayer.create(level2x2.this,R.raw.prolog);
        tebrikler = MediaPlayer.create(level2x2.this,R.raw.tebrikler);
        cgr = MediaPlayer.create(level2x2.this,R.raw.cgr1);
        surebitti = MediaPlayer.create(level2x2.this,R.raw.surebitti);


        cardv00 = (ImageView) findViewById(R.id.card00);
        cardv01 = (ImageView) findViewById(R.id.card01);
        cardv10 = (ImageView) findViewById(R.id.card10);
        cardv11 = (ImageView) findViewById(R.id.card11);

        score1 = (TextView) findViewById(R.id.score1);
        score2 = (TextView) findViewById(R.id.score2);

        time = (TextView) findViewById(R.id.time);


        start = (Button)  findViewById(R.id.start);
        next = (Button)  findViewById(R.id.next);
        open = (Button)  findViewById(R.id.open);
        close = (Button)  findViewById(R.id.close);

        cardv00.setTag("0");
        cardv01.setTag("1");
        cardv10.setTag("2");
        cardv11.setTag("3");

        cardv00.setEnabled(false);
        cardv01.setEnabled(false);
        cardv10.setEnabled(false);
        cardv11.setEnabled(false);

        Collections.shuffle(Arrays.asList(kartDizisi));









        start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                cardv00.setEnabled(true);
                cardv01.setEnabled(true);
                cardv10.setEnabled(true);
                cardv11.setEnabled(true);
                start.setEnabled(false);
                prolog.start();
                timer().start();

            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prolog.pause();
            }
        });

        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prolog.start();
            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            ///degistirilecek seyler var!!
            @Override
            public void onClick(View view) {
                prolog.stop();
                startActivity(new Intent(level2x2.this,level4x4.class));



            }
        });
        cardv00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tag = Integer.parseInt((String) view.getTag());
                sonuc(cardv00,tag);

            }
        });
        cardv01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tag = Integer.parseInt((String) view.getTag());
                sonuc(cardv01,tag);

            }
        });
        cardv10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tag = Integer.parseInt((String) view.getTag());
                sonuc(cardv10,tag);
            }
        });
        cardv11.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int tag = Integer.parseInt((String) view.getTag());
                sonuc(cardv11,tag);


            }
        });
    }
    private void sonuc(ImageView card,int tag) {

        if (kartDizisi[tag]==100){
            card00(card);
        }
        else if (kartDizisi[tag]==101){
            card01(card);
            //card.setImageBitmap(bitmapCard(card01()));
        }
        else if (kartDizisi[tag]==200){
            card10(card);
            //card.setImageBitmap(bitmapCard(card10()));
        }
        else if (kartDizisi[tag]==201){
            card11(card);
            //card.setImageBitmap());
        }
        if (cardNumber==1){
            firstCard =kartDizisi[tag];
            if (firstCard>199){
                firstCard= firstCard-100;}
            cardNumber=2;

            clickedFirst =tag;
            card.setEnabled(false);
        }
        else if (cardNumber==2){
            secondCard =kartDizisi[tag];

            if (secondCard>199){
                secondCard= secondCard-100;}


            cardNumber=1;

            getClickedSecond =tag;
            cardv00.setEnabled(false);
            cardv01.setEnabled(false);
            cardv10.setEnabled(false);
            cardv11.setEnabled(false);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    esitlik();
                }
            },1000);
        }
    }
    private    CountDownTimer timer(){

        int a = 60000;
        CountDownTimer  ct=  new CountDownTimer(a, 1000) {

            public void onTick(long millisUntilFinished) {
                kalan = (double) (millisUntilFinished / 1000);
                time.setText( "TIME: " + kalan);
                if (oyunsonu() == true){
                    cancel();
                }
            }
            public void onFinish() {
                sonagel();
                surebitti.start();
                time.setText("SURE BITTI!");
                start.setEnabled(true);
            }
        };
        return ct;
    }



    private void sonagel(){

        AlertDialog.Builder builder = new AlertDialog.Builder(level2x2.this);
        builder
                .setMessage("GAME OVER\nP1:"+player1Points+"\nP2: " +player2Points)
                .setCancelable(false)
                .setPositiveButton("NEW", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(level2x2.this,level2x2.class));                    }
                }).setNegativeButton("EXIT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        tebrikler.stop();
                        prolog.stop();
                        cgr.stop();
                        surebitti.stop();
                        startActivity(new Intent(level2x2.this,preference.class));
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        start.setEnabled(true);

    }




    private boolean oyunsonu(){
        if(cardv00.getVisibility() == View.INVISIBLE&&
                cardv01.getVisibility() == View.INVISIBLE&&
                cardv10.getVisibility() == View.INVISIBLE&&
                cardv11.getVisibility() == View.INVISIBLE){

            prolog.stop();
            tebrikler.start();
            sonagel();

            return true;
        }
        return false;
    }

    private void esitlik(){
        if (firstCard == secondCard){
            cgr.start();
            if(clickedFirst == 0){
                cardv00.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 1){
                cardv01.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 2){
                cardv10.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 3){
                cardv11.setVisibility(View.INVISIBLE);
            }

            if(getClickedSecond == 0){
                cardv00.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond == 1){

                cardv01.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond == 2){

                cardv10.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond == 3){
                cardv11.setVisibility(View.INVISIBLE);
            }

            if (turn == 1) {
                player1Points +=puan;
                score1.setText("Player1: " + player1Points);
            }
            else if (turn == 2) {
                player2Points +=puan;
                score2.setText("Player2: " + player2Points);
            }
        }
        else{
            cardv00.setImageResource(R.drawable.back);
            cardv01.setImageResource(R.drawable.back);
            cardv10.setImageResource(R.drawable.back);
            cardv11.setImageResource(R.drawable.back);
            //turn lefted other player

            if (turn == 1){

                if(ev1.equalsIgnoreCase(ev2)){
                    player1Points -= (puan1+puan2)/evpuan1;
                    score1.setText("Player1: " + player1Points);
                }
                else{
                    player1Points -=evpuan1*evpuan2*((puan1+puan2)/2);
                    score1.setText("Player1: " + player1Points);
                }
                score1.setTextColor(Color.BLACK);
                score2.setTextColor(Color.WHITE);
                turn = 2;
            }
            else if(turn ==2){

                if(ev1.equalsIgnoreCase(ev2)){
                    player2Points -= ((puan1+puan2)/evpuan1);
                    score2.setText("Player2: " + player2Points);
                }
                else{
                    player2Points -=evpuan1*evpuan2*((puan1+puan2)/2);
                    score2.setText("Player2: " + player2Points);
                }
                score1.setTextColor(Color.WHITE);
                score2.setTextColor(Color.BLACK);
                turn =1;
            }
        }

        cardv00.setEnabled(true);
        cardv01.setEnabled(true);
        cardv10.setEnabled(true);
        cardv11.setEnabled(true);
        oyunsonu();
    }

    private void writeToFile(String data) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("kartBilgileri.txt", Context.MODE_APPEND));
            outputStreamWriter.append("\n\r"+data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }




    /*private void setImages(){

        cardv01.setImageBitmap(bitmapCard(card00()));
        cardv01.setImageBitmap(bitmapCard(card00()));
        cardv01.setImageBitmap(bitmapCard(card00()));
        cardv01.setImageBitmap(bitmapCard(card00()));
    }*/



    private Bitmap bitmapCard(String card) {
        byte[]  based= Base64.decode(card,Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(based, 0, based.length);
        return decodedImage;
    }

    // card00
    private String card00(ImageView card) {
        DocumentReference docRef = db.document("cards/"+numbers.get(0));
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                cardp = documentSnapshot.getString("kartpuani");
                cardep = documentSnapshot.getString("evpuani");
                carden = documentSnapshot.getString("evadi");

                kartPuan = Double.parseDouble(cardp);
                evPuan = Double.parseDouble(cardep);

                puan = kartPuan*evPuan*2;
                cardb = documentSnapshot.getString("base64");
                card.setImageBitmap(bitmapCard(cardb));

                if(cardNumber == 1 ){
                    ev1 = carden;
                    puan1 = kartPuan;
                    evpuan1 = evPuan;
                }

                if(cardNumber ==2  ){
                    ev2 = carden;
                    puan2 = kartPuan;
                    evpuan2 = evPuan;
                }

                str.add("kart puani "+cardp+" kart ev puani "+cardep+" kart ev adi" +carden);
                writeToFile("card00   "+str.toString());
                str.clear();
            }
        });
        return carden;
    }

    // card01
    private String card01(ImageView card) {
        DocumentReference docRef = db.document("cards/"+numbers.get(1));
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                cardp = documentSnapshot.getString("kartpuani");
                cardep = documentSnapshot.getString("evpuani");
                carden = documentSnapshot.getString("evadi");

                kartPuan = Double.parseDouble(cardp);
                evPuan = Double.parseDouble(cardep);

                puan = kartPuan*evPuan*2;
                cardb = documentSnapshot.getString("base64");
                card.setImageBitmap(bitmapCard(cardb));

                if(cardNumber == 1 ){
                    ev1 = carden;
                    puan1 = kartPuan;
                    evpuan1 = evPuan;
                }

                if(cardNumber == 2  ){
                    ev2 = carden;
                    puan2 = kartPuan;
                    evpuan2 = evPuan;
                }
                str.add("kart puani "+cardp+" kart ev puani "+cardep+" kart ev adi" +carden);
                writeToFile("card01  "+str.toString());
                str.clear();



            }
        });
        return carden;

    }



    private String card10(ImageView card) {
        ///java this. muhabbeti..

        DocumentReference docRef = db.document("cards/"+numbers.get(0));
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                cardp = documentSnapshot.getString("kartpuani");
                cardep = documentSnapshot.getString("evpuani");
                carden = documentSnapshot.getString("evadi");

                kartPuan = Double.parseDouble(cardp);
                evPuan = Double.parseDouble(cardep);

                puan = kartPuan*evPuan*2;

                cardb = documentSnapshot.getString("base64");
                card.setImageBitmap(bitmapCard(cardb));

                if(cardNumber ==1 ){
                    ev1 = carden;
                    puan1 = kartPuan;
                    evpuan1 = evPuan;

                }

                if(cardNumber ==2 ){
                    ev2 = carden;
                    puan2 = kartPuan;
                    evpuan2 = evPuan;

                }

                str.add("kart puani "+cardp+" kart ev puani "+cardep+" kart ev adi" +carden);
                writeToFile("card10  "+str.toString());
                str.clear();


            }
        });

        return carden;


    }


    private String card11(ImageView card) {
        DocumentReference docRef = db.document("cards/"+numbers.get(1));
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                cardp = documentSnapshot.getString("kartpuani");
                cardep = documentSnapshot.getString("evpuani");
                carden = documentSnapshot.getString("evadi");


                kartPuan = Double.parseDouble(cardp);
                evPuan = Double.parseDouble(cardep);

                puan = kartPuan*evPuan*2;

                if(cardNumber ==1 ){
                    ev1 = carden;
                    puan1 = kartPuan;
                    evpuan1 = evPuan;
                }

                if(cardNumber ==2 ){
                    ev2 = carden;
                    puan2 = kartPuan;
                    evpuan2 = evPuan;
                }
                str.add("kart puani "+cardp+" kart ev puani "+cardep+" kart ev adi" +carden);
                writeToFile("card11  "+str.toString());
                str.clear();



                cardb = documentSnapshot.getString("base64");
                card.setImageBitmap(bitmapCard(cardb));
            }
        });
        return carden;
    }
}
