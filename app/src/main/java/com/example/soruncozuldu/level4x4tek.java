package com.example.soruncozuldu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class level4x4tek extends AppCompatActivity {

    int [] gryf = new int[] {0,1,10,11,12,13,14,15,16,17,18};
    int [] huff = new int[] {19,2,20,21,22,23,24,25,26,27,28};
    int [] sly = new int[]  {29,3,30,31,32,33,34,35,36,37,38};
    int [] rawen = new int[] {39,4,40,41,42,43,5,6,7,8,9};

    String ev1="" ,ev2="";

    double kartPuan=0.0, evPuan= 0.0,evpuan1 = 0.0,evpuan2 =0.0,puan =0.0,puan1=0.0,puan2=0.0,kalan =0.0;


    Button start,open,close,next,back;
    MediaPlayer prolog,tebrikler,cgr,surebitti;



    TextView score1,score2,time;

    ImageView cardv00,cardv01,cardv02,cardv03;
    ImageView cardv10,cardv11,cardv12,cardv13;
    ImageView cardv20,cardv21,cardv22,cardv23;
    ImageView cardv30,cardv31,cardv32,cardv33;

    List<String> str = new ArrayList<String>();

    Integer[] kartDizisi = {100,101,102,103,104,105,106,107,200,201,202,203,204,205,206,207};

    int firstCard,secondCard;
    int clickedFirst,getClickedSecond;

    int cardNumber = 1;
    int turn = 1;

    double player1Points = 0.0;


    String cardb,cardn,cardp,cardep,carden; // bunu firestoredan al

    Random rand = new Random();

    List<Integer> numbers = new ArrayList<Integer>();
    FirebaseFirestore db= FirebaseFirestore.getInstance();


    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level4x4tek);
        do
        {
            int next = rand.nextInt(11);
            if (!numbers.contains(next))
            {
                numbers.add(next);

            }
        } while (numbers.size() < 2);



        prolog = MediaPlayer.create(level4x4tek.this,R.raw.prolog);
        tebrikler = MediaPlayer.create(level4x4tek.this,R.raw.tebrikler);
        cgr = MediaPlayer.create(level4x4tek.this,R.raw.cgr1);
        surebitti = MediaPlayer.create(level4x4tek.this,R.raw.surebitti);


        cardv00 = (ImageView) findViewById(R.id.card00);
        cardv01 = (ImageView) findViewById(R.id.card01);
        cardv02 = (ImageView) findViewById(R.id.card02);
        cardv03 = (ImageView) findViewById(R.id.card03);

        cardv10 = (ImageView) findViewById(R.id.card10);
        cardv11 = (ImageView) findViewById(R.id.card11);
        cardv12 = (ImageView) findViewById(R.id.card12);
        cardv13 = (ImageView) findViewById(R.id.card13);

        cardv20 = (ImageView) findViewById(R.id.card20);
        cardv21 = (ImageView) findViewById(R.id.card21);
        cardv22 = (ImageView) findViewById(R.id.card22);
        cardv23 = (ImageView) findViewById(R.id.card23);

        cardv30 = (ImageView) findViewById(R.id.card30);
        cardv31 = (ImageView) findViewById(R.id.card31);
        cardv32 = (ImageView) findViewById(R.id.card32);
        cardv33 = (ImageView) findViewById(R.id.card33);


        score1 = (TextView) findViewById(R.id.score1);
        score2 = (TextView) findViewById(R.id.score2);
        time = (TextView) findViewById(R.id.time);



        start = (Button)  findViewById(R.id.start);
        next = (Button)  findViewById(R.id.next);
        back = (Button)  findViewById(R.id.back);
        open = (Button)  findViewById(R.id.open);
        close = (Button)  findViewById(R.id.close);

        cardv00.setTag("0");
        cardv01.setTag("1");
        cardv02.setTag("2");
        cardv03.setTag("3");

        cardv10.setTag("4");
        cardv11.setTag("5");
        cardv12.setTag("6");
        cardv13.setTag("7");

        cardv20.setTag("8");
        cardv21.setTag("9");
        cardv22.setTag("10");
        cardv23.setTag("11");

        cardv30.setTag("12");
        cardv31.setTag("13");
        cardv32.setTag("14");
        cardv33.setTag("15");

        cardv00.setEnabled(false);
        cardv01.setEnabled(false);
        cardv02.setEnabled(false);
        cardv03.setEnabled(false);

        cardv10.setEnabled(false);
        cardv11.setEnabled(false);
        cardv12.setEnabled(false);
        cardv13.setEnabled(false);

        cardv20.setEnabled(false);
        cardv21.setEnabled(false);
        cardv22.setEnabled(false);
        cardv23.setEnabled(false);

        cardv30.setEnabled(false);
        cardv31.setEnabled(false);
        cardv32.setEnabled(false);
        cardv33.setEnabled(false);


        Collections.shuffle(Arrays.asList(kartDizisi));

        Collections.shuffle(Arrays.asList(gryf));
        Collections.shuffle(Arrays.asList(huff));
        Collections.shuffle(Arrays.asList(sly));
        Collections.shuffle(Arrays.asList(rawen));


        start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                cardv00.setEnabled(true);
                cardv01.setEnabled(true);
                cardv02.setEnabled(true);
                cardv03.setEnabled(true);

                cardv10.setEnabled(true);
                cardv11.setEnabled(true);
                cardv12.setEnabled(true);
                cardv13.setEnabled(true);

                cardv20.setEnabled(true);
                cardv21.setEnabled(true);
                cardv22.setEnabled(true);
                cardv23.setEnabled(true);

                cardv30.setEnabled(true);
                cardv31.setEnabled(true);
                cardv32.setEnabled(true);
                cardv33.setEnabled(true);

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


        back.setOnClickListener(new View.OnClickListener() {
            ///degistirilecek seyler var!!
            @Override
            public void onClick(View view) {
                startActivity(new Intent(level4x4tek.this,level2x2tek.class));
            }
        });



        next.setOnClickListener(new View.OnClickListener() {
            ///degistirilecek seyler var!!
            @Override
            public void onClick(View view) {
                prolog.stop();
                startActivity(new Intent(level4x4tek.this,level6x6tek.class));


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
        cardv02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tag = Integer.parseInt((String) view.getTag());
                sonuc(cardv02,tag);
            }
        });
        cardv03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tag = Integer.parseInt((String) view.getTag());
                sonuc(cardv03,tag);

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
        cardv12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tag = Integer.parseInt((String) view.getTag());
                sonuc(cardv12,tag);
            }
        });
        cardv13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tag = Integer.parseInt((String) view.getTag());
                sonuc(cardv13,tag);

            }
        });





        cardv20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tag = Integer.parseInt((String) view.getTag());
                sonuc(cardv20,tag);
            }
        });
        cardv21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tag = Integer.parseInt((String) view.getTag());
                sonuc(cardv21,tag);

            }
        });
        cardv22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tag = Integer.parseInt((String) view.getTag());
                sonuc(cardv22,tag);
            }
        });
        cardv23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tag = Integer.parseInt((String) view.getTag());
                sonuc(cardv23,tag);

            }
        });



        cardv30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tag = Integer.parseInt((String) view.getTag());
                sonuc(cardv30,tag);
            }
        });
        cardv31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tag = Integer.parseInt((String) view.getTag());
                sonuc(cardv31,tag);

            }
        });
        cardv32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tag = Integer.parseInt((String) view.getTag());
                sonuc(cardv32,tag);
            }
        });
        cardv33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tag = Integer.parseInt((String) view.getTag());
                sonuc(cardv33,tag);

            }
        });

    }
    private void sonuc(ImageView card,int tag){
        if (kartDizisi[tag]==100){
            card00(card);
        }
        else if (kartDizisi[tag]==101){
            card01(card);
            //card.setImageBitmap(bitmapCard(card01()));
        }
        else if (kartDizisi[tag]==102){
            card02(card);
            //card.setImageBitmap(bitmapCard(card01()));
        }
        else if (kartDizisi[tag]==103){
            card03(card);
            //card.setImageBitmap(bitmapCard(card01()));
        }
        else if (kartDizisi[tag]==104){
            card10(card);
            //card.setImageBitmap(bitmapCard(card01()));
        }
        else if (kartDizisi[tag]==105){
            card11(card);
            //card.setImageBitmap(bitmapCard(card01()));
        }
        else if (kartDizisi[tag]==106){
            card12(card);
            //card.setImageBitmap(bitmapCard(card01()));
        }
        else if (kartDizisi[tag]==107){
            card13(card);
            //card.setImageBitmap(bitmapCard(card01()));
        }
        else if (kartDizisi[tag]==200){
            card20(card);
            //card.setImageBitmap(bitmapCard(card01()));
        }
        else if (kartDizisi[tag]==201){
            card21(card);
            //card.setImageBitmap(bitmapCard(card01()));
        }
        else if (kartDizisi[tag]==202){
            card22(card);
            //card.setImageBitmap(bitmapCard(card01()));
        }
        else if (kartDizisi[tag]==203){
            card23(card);
            //card.setImageBitmap(bitmapCard(card01()));
        }
        else if (kartDizisi[tag]==204){
            card30(card);
            //card.setImageBitmap(bitmapCard(card01()));
        }
        else if (kartDizisi[tag]==205){
            card31(card);
            //card.setImageBitmap(bitmapCard(card01()));
        }
        else if (kartDizisi[tag]==206){
            card32(card);
            //card.setImageBitmap(bitmapCard(card01()));
        }
        else if (kartDizisi[tag]==207){
            card33(card);
            //card.setImageBitmap(bitmapCard(card01()));
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
            cardv02.setEnabled(false);
            cardv03.setEnabled(false);

            cardv10.setEnabled(false);
            cardv11.setEnabled(false);
            cardv12.setEnabled(false);
            cardv13.setEnabled(false);

            cardv20.setEnabled(false);
            cardv21.setEnabled(false);
            cardv22.setEnabled(false);
            cardv23.setEnabled(false);

            cardv30.setEnabled(false);
            cardv31.setEnabled(false);
            cardv32.setEnabled(false);
            cardv33.setEnabled(false);



            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    esitlik();
                }
            },1000);
        }
    }
    private void esitlik(){
        if (firstCard==secondCard){

            cgr.start();


            if(clickedFirst == 0){
                cardv00.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 1){
                cardv01.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 2){
                cardv02.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 3){
                cardv03.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 4){
                cardv10.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 5){
                cardv11.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 6){
                cardv12.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 7){
                cardv13.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 8){
                cardv20.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 9){
                cardv21.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 10){
                cardv22.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 11){
                cardv23.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 12){
                cardv30.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 13){
                cardv31.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 14){
                cardv32.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 15){
                cardv33.setVisibility(View.INVISIBLE);
            }




            if(getClickedSecond == 0){
                cardv00.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond == 1){
                cardv01.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond == 2){
                cardv02.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond == 3){
                cardv03.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond == 4){
                cardv10.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond == 5){
                cardv11.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond == 6){
                cardv12.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond ==7){
                cardv13.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond == 8){
                cardv20.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond == 9){
                cardv21.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond == 10){
                cardv22.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond == 11){
                cardv23.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond == 12){
                cardv30.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond == 13){
                cardv31.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond == 14){
                cardv32.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond == 15){
                cardv33.setVisibility(View.INVISIBLE);
            }

            player1Points += puan;

            score1.setText("Player1: "+player1Points);



        }
        else{
            cardv00.setImageResource(R.drawable.back);
            cardv01.setImageResource(R.drawable.back);
            cardv02.setImageResource(R.drawable.back);
            cardv03.setImageResource(R.drawable.back);

            cardv10.setImageResource(R.drawable.back);
            cardv11.setImageResource(R.drawable.back);
            cardv12.setImageResource(R.drawable.back);
            cardv13.setImageResource(R.drawable.back);

            cardv20.setImageResource(R.drawable.back);
            cardv21.setImageResource(R.drawable.back);
            cardv22.setImageResource(R.drawable.back);
            cardv23.setImageResource(R.drawable.back);

            cardv30.setImageResource(R.drawable.back);
            cardv31.setImageResource(R.drawable.back);
            cardv32.setImageResource(R.drawable.back);
            cardv33.setImageResource(R.drawable.back);



            if(ev1.equalsIgnoreCase(ev2)){
                player1Points -= ((puan1+puan2)/evpuan1)*((45-kalan)/10);
                score1.setText("Player1: " + player1Points);
            }
            else{
                player1Points -=evpuan1*evpuan2*((puan1+puan2)/2)*((45-kalan)/10);
                score1.setText("Player1: " + player1Points);
            }

        }

        cardv00.setEnabled(true);
        cardv01.setEnabled(true);
        cardv02.setEnabled(true);
        cardv03.setEnabled(true);

        cardv10.setEnabled(true);
        cardv11.setEnabled(true);
        cardv12.setEnabled(true);
        cardv13.setEnabled(true);

        cardv20.setEnabled(true);
        cardv21.setEnabled(true);
        cardv22.setEnabled(true);
        cardv23.setEnabled(true);

        cardv30.setEnabled(true);
        cardv31.setEnabled(true);
        cardv32.setEnabled(true);
        cardv33.setEnabled(true);


    }

    private CountDownTimer timer(){
        int a = 45000;
        CountDownTimer  ct=  new CountDownTimer(a, 1000) {



            public void onTick(long millisUntilFinished) {
                kalan = (double) (millisUntilFinished / 1000);


                time.setText( "TIME: " + kalan);

                if (oyunsonu()==true){
                    cancel();
                }
            }
            public void onFinish() {
                sonagel();
                surebitti.start();
                prolog.stop();
                time.setText("SURE BITTI!");
                start.setEnabled(true);
            }


        };
        return ct;
    }

    private boolean oyunsonu(){
        if(cardv00.getVisibility() == View.INVISIBLE&&
                cardv01.getVisibility() == View.INVISIBLE&&
                cardv02.getVisibility() == View.INVISIBLE&&
                cardv03.getVisibility() == View.INVISIBLE&&

                cardv10.getVisibility() == View.INVISIBLE&&
                cardv11.getVisibility() == View.INVISIBLE&&
                cardv12.getVisibility() == View.INVISIBLE&&
                cardv13.getVisibility() == View.INVISIBLE&&

                cardv20.getVisibility() == View.INVISIBLE&&
                cardv21.getVisibility() == View.INVISIBLE&&
                cardv22.getVisibility() == View.INVISIBLE&&
                cardv23.getVisibility() == View.INVISIBLE&&
                cardv30.getVisibility() == View.INVISIBLE&&
                cardv31.getVisibility() == View.INVISIBLE&&
                cardv32.getVisibility() == View.INVISIBLE&&
                cardv33.getVisibility() == View.INVISIBLE){


            prolog.stop();
            tebrikler.start();
            sonagel();
            return true;
        }
        return false;
    }
    private void writeToFile(String data) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("config.txt", Context.MODE_APPEND));
            outputStreamWriter.append("\n\r"+data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
    private void sonagel(){
        AlertDialog.Builder builder = new AlertDialog.Builder(level4x4tek.this);
        builder
                .setMessage("GAME OVER\nP1:"+player1Points)
                .setCancelable(false)
                .setPositiveButton("NEW", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(level4x4tek.this,level4x4tek.class));

                    }
                }).setNegativeButton("EXIT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        tebrikler.stop();
                        prolog.stop();
                        cgr.stop();
                        surebitti.stop();
                        startActivity(new Intent(level4x4tek.this,preference.class));
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        start.setEnabled(true);

    }




    private Bitmap bitmapCard(String card) {
        byte[]  based= Base64.decode(card,Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(based, 0, based.length);
        return decodedImage;
    }




    private void card00(ImageView card) {
        DocumentReference docRef = db.document("cards/"+gryf[numbers.get(0)]);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                cardp = documentSnapshot.getString("kartpuani");
                cardep = documentSnapshot.getString("evpuani");
                carden = documentSnapshot.getString("evadi");

                kartPuan = Double.parseDouble(cardp);
                evPuan = Double.parseDouble(cardep);

                puan = kartPuan*evPuan*2*(kalan/10);
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
                writeToFile("card00  "+str.toString());
                str.clear();
            }
        });
    }
    private void card01(ImageView card) {
        DocumentReference docRef = db.document("cards/"+gryf[numbers.get(1)]);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                cardp = documentSnapshot.getString("kartpuani");
                cardep = documentSnapshot.getString("evpuani");
                carden = documentSnapshot.getString("evadi");

                kartPuan = Double.parseDouble(cardp);
                evPuan = Double.parseDouble(cardep);

                puan = kartPuan*evPuan*2*(kalan/10);
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
                writeToFile("card01  "+str.toString());
                str.clear();
            }
        });
    }
    private void card02(ImageView card) {
        DocumentReference docRef = db.document("cards/"+sly[numbers.get(0)]);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                cardp = documentSnapshot.getString("kartpuani");
                cardep = documentSnapshot.getString("evpuani");
                carden = documentSnapshot.getString("evadi");

                kartPuan = Double.parseDouble(cardp);
                evPuan = Double.parseDouble(cardep);

                puan = kartPuan*evPuan*2*(kalan/10);
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
                writeToFile("card02  "+str.toString());
                str.clear();
            }
        });
    }
    private void card03(ImageView card) {
        DocumentReference docRef = db.document("cards/"+sly[numbers.get(1)]);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                cardp = documentSnapshot.getString("kartpuani");
                cardep = documentSnapshot.getString("evpuani");
                carden = documentSnapshot.getString("evadi");

                kartPuan = Double.parseDouble(cardp);
                evPuan = Double.parseDouble(cardep);

                puan = kartPuan*evPuan*2*(kalan/10);
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
                writeToFile("card03  "+str.toString());
                str.clear();
            }
        });
    }
    private void card10(ImageView card) {
        DocumentReference docRef = db.document("cards/"+huff[numbers.get(0)]);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                cardp = documentSnapshot.getString("kartpuani");
                cardep = documentSnapshot.getString("evpuani");
                carden = documentSnapshot.getString("evadi");

                kartPuan = Double.parseDouble(cardp);
                evPuan = Double.parseDouble(cardep);

                puan = kartPuan*evPuan*2*(kalan/10);
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
                writeToFile("card10  "+str.toString());
                str.clear();
            }
        });
    }
    private void card11(ImageView card) {
        DocumentReference docRef = db.document("cards/"+huff[numbers.get(1)]);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                cardp = documentSnapshot.getString("kartpuani");
                cardep = documentSnapshot.getString("evpuani");
                carden = documentSnapshot.getString("evadi");

                kartPuan = Double.parseDouble(cardp);
                evPuan = Double.parseDouble(cardep);

                puan = kartPuan*evPuan*2*(kalan/10);
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
                writeToFile("card11  "+str.toString());
                str.clear();
            }
        });

    }
    private void card12(ImageView card) {
        DocumentReference docRef = db.document("cards/"+rawen[numbers.get(0)]);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                cardp = documentSnapshot.getString("kartpuani");
                cardep = documentSnapshot.getString("evpuani");
                carden = documentSnapshot.getString("evadi");

                kartPuan = Double.parseDouble(cardp);
                evPuan = Double.parseDouble(cardep);

                puan = kartPuan*evPuan*2*(kalan/10);
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
                writeToFile("card12  "+str.toString());
                str.clear();
            }
        });
    }
    private void card13(ImageView card) {
        DocumentReference docRef = db.document("cards/"+rawen[numbers.get(1)]);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                cardp = documentSnapshot.getString("kartpuani");
                cardep = documentSnapshot.getString("evpuani");
                carden = documentSnapshot.getString("evadi");

                kartPuan = Double.parseDouble(cardp);
                evPuan = Double.parseDouble(cardep);

                puan = kartPuan*evPuan*2*(kalan/10);
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
                writeToFile("card13  "+str.toString());
                str.clear();
            }
        });

    }
    private void card20(ImageView card) {
        DocumentReference docRef = db.document("cards/"+gryf[numbers.get(0)]);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                cardp = documentSnapshot.getString("kartpuani");
                cardep = documentSnapshot.getString("evpuani");
                carden = documentSnapshot.getString("evadi");

                kartPuan = Double.parseDouble(cardp);
                evPuan = Double.parseDouble(cardep);

                puan = kartPuan*evPuan*2*(kalan/10);
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
                writeToFile("card20  "+str.toString());
                str.clear();
            }
        });

    }
    private void card21(ImageView card) {
        DocumentReference docRef = db.document("cards/"+gryf[numbers.get(1)]);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                cardp = documentSnapshot.getString("kartpuani");
                cardep = documentSnapshot.getString("evpuani");
                carden = documentSnapshot.getString("evadi");

                kartPuan = Double.parseDouble(cardp);
                evPuan = Double.parseDouble(cardep);

                puan = kartPuan*evPuan*2*(kalan/10);
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
                writeToFile("card21  "+str.toString());
                str.clear();
            }
        });

    }
    private void card22(ImageView card) {
        DocumentReference docRef = db.document("cards/"+sly[numbers.get(0)]);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                cardp = documentSnapshot.getString("kartpuani");
                cardep = documentSnapshot.getString("evpuani");
                carden = documentSnapshot.getString("evadi");

                kartPuan = Double.parseDouble(cardp);
                evPuan = Double.parseDouble(cardep);

                puan = kartPuan*evPuan*2*(kalan/10);
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
                writeToFile("card22  "+str.toString());
                str.clear();
            }
        });
    }
    private void card23(ImageView card) {
        DocumentReference docRef = db.document("cards/"+sly[numbers.get(1)]);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                cardp = documentSnapshot.getString("kartpuani");
                cardep = documentSnapshot.getString("evpuani");
                carden = documentSnapshot.getString("evadi");

                kartPuan = Double.parseDouble(cardp);
                evPuan = Double.parseDouble(cardep);

                puan = kartPuan*evPuan*2*(kalan/10);
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
                writeToFile("card23  "+str.toString());
                str.clear();
            }
        });
    }
    private void card30(ImageView card) {
        DocumentReference docRef = db.document("cards/"+huff[numbers.get(0)]);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                cardp = documentSnapshot.getString("kartpuani");
                cardep = documentSnapshot.getString("evpuani");
                carden = documentSnapshot.getString("evadi");

                kartPuan = Double.parseDouble(cardp);
                evPuan = Double.parseDouble(cardep);

                puan = kartPuan*evPuan*2*(kalan/10);
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
                writeToFile("card30  "+str.toString());
                str.clear();
            }

        });

    }
    private void card31(ImageView card) {
        DocumentReference docRef = db.document("cards/"+huff[numbers.get(1)]);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                cardp = documentSnapshot.getString("kartpuani");
                cardep = documentSnapshot.getString("evpuani");
                carden = documentSnapshot.getString("evadi");

                kartPuan = Double.parseDouble(cardp);
                evPuan = Double.parseDouble(cardep);

                puan = kartPuan*evPuan*2*(kalan/10);
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
                writeToFile("card31  "+str.toString());
                str.clear();
            }
        });

    }
    private void card32(ImageView card) {
        DocumentReference docRef = db.document("cards/"+rawen[numbers.get(0)]);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                cardp = documentSnapshot.getString("kartpuani");
                cardep = documentSnapshot.getString("evpuani");
                carden = documentSnapshot.getString("evadi");

                kartPuan = Double.parseDouble(cardp);
                evPuan = Double.parseDouble(cardep);

                puan = kartPuan*evPuan*2*(kalan/10);
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
                writeToFile("card32  "+str.toString());
                str.clear();
            }
        });
    }
    private void card33(ImageView card) {
        DocumentReference docRef = db.document("cards/"+rawen[numbers.get(1)]);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                cardp = documentSnapshot.getString("kartpuani");
                cardep = documentSnapshot.getString("evpuani");
                carden = documentSnapshot.getString("evadi");

                kartPuan = Double.parseDouble(cardp);
                evPuan = Double.parseDouble(cardep);

                puan = kartPuan*evPuan*2*(kalan/10);
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
                writeToFile("card33  "+str.toString());
                str.clear();
            }

        });
    }
}