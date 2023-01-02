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

public class level6x6tek extends AppCompatActivity {
    int [] gryf = new int[] {0,1,10,11,12,13,14,15,16,17,18};
    int [] huff = new int[] {19,2,20,21,22,23,24,25,26,27,28};
    int [] sly = new int[]  {29,3,30,31,32,33,34,35,36,37,38};
    int [] rawen = new int[] {39,4,40,41,42,43,5,6,7,8,9};

    String ev1="" ,ev2="";
    List<String> str = new ArrayList<String>();


    double kartPuan=0.0, evPuan= 0.0,evpuan1 = 0.0,evpuan2 =0.0,puan =0.0,puan1=0.0,puan2=0.0,kalan =0.0;


    Button start,open,close,back;
    MediaPlayer prolog,tebrikler,cgr,surebitti;


    TextView score1,score2,time;

    ImageView cardv00,cardv01,cardv02,cardv03,cardv04,cardv05;
    ImageView cardv10,cardv11,cardv12,cardv13,cardv14,cardv15;
    ImageView cardv20,cardv21,cardv22,cardv23,cardv24,cardv25;
    ImageView cardv30,cardv31,cardv32,cardv33,cardv34,cardv35;
    ImageView cardv40,cardv41,cardv42,cardv43,cardv44,cardv45;
    ImageView cardv50,cardv51,cardv52,cardv53,cardv54,cardv55;

    Integer[] kartDizisi = {100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,
            116,117,200,201,202,203,204,205,206,207,208,209,210,211,212,213,214,215,216,217};

    int firstCard,secondCard;
    int clickedFirst,getClickedSecond;

    int cardNumber = 1;
    int turn = 1;

    double player1Points = 0.0, player2Points = 0;




    String cardb,cardn,cardp,cardep,carden; // bunu firestoredan al

    Random rand = new Random();

    List<Integer> numbers = new ArrayList<Integer>();
    FirebaseFirestore db= FirebaseFirestore.getInstance();



    @SuppressLint("MissingInflatedId")


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level6x6tek);
        do
        {
            int next = rand.nextInt(11);
            if (!numbers.contains(next))
            {
                numbers.add(next);

            }
        } while (numbers.size() < 5);

        prolog = MediaPlayer.create(level6x6tek.this,R.raw.prolog);
        tebrikler = MediaPlayer.create(level6x6tek.this,R.raw.tebrikler);
        cgr = MediaPlayer.create(level6x6tek.this,R.raw.cgr1);
        surebitti = MediaPlayer.create(level6x6tek.this,R.raw.surebitti);


        score1 = (TextView) findViewById(R.id.score1);
        score2 = (TextView) findViewById(R.id.score2);
        time = (TextView) findViewById(R.id.time);

        start = (Button) findViewById(R.id.start);
        back = (Button) findViewById(R.id.back);
        open = (Button)  findViewById(R.id.open);
        close = (Button)  findViewById(R.id.close);


        cardv00 = (ImageView) findViewById(R.id.card00);
        cardv01 = (ImageView) findViewById(R.id.card01);
        cardv02 = (ImageView) findViewById(R.id.card02);
        cardv03 = (ImageView) findViewById(R.id.card03);
        cardv04 = (ImageView) findViewById(R.id.card04);
        cardv05 = (ImageView) findViewById(R.id.card05);

        cardv10 = (ImageView) findViewById(R.id.card10);
        cardv11 = (ImageView) findViewById(R.id.card11);
        cardv12 = (ImageView) findViewById(R.id.card12);
        cardv13 = (ImageView) findViewById(R.id.card13);
        cardv14 = (ImageView) findViewById(R.id.card14);
        cardv15 = (ImageView) findViewById(R.id.card15);

        cardv20 = (ImageView) findViewById(R.id.card20);
        cardv21 = (ImageView) findViewById(R.id.card21);
        cardv22 = (ImageView) findViewById(R.id.card22);
        cardv23 = (ImageView) findViewById(R.id.card23);
        cardv24 = (ImageView) findViewById(R.id.card24);
        cardv25 = (ImageView) findViewById(R.id.card25);

        cardv30 = (ImageView) findViewById(R.id.card30);
        cardv31 = (ImageView) findViewById(R.id.card31);
        cardv32 = (ImageView) findViewById(R.id.card32);
        cardv33 = (ImageView) findViewById(R.id.card33);
        cardv34 = (ImageView) findViewById(R.id.card34);
        cardv35 = (ImageView) findViewById(R.id.card35);

        cardv40 = (ImageView) findViewById(R.id.card40);
        cardv41 = (ImageView) findViewById(R.id.card41);
        cardv42 = (ImageView) findViewById(R.id.card42);
        cardv43 = (ImageView) findViewById(R.id.card43);
        cardv44 = (ImageView) findViewById(R.id.card44);
        cardv45 = (ImageView) findViewById(R.id.card45);

        cardv50 = (ImageView) findViewById(R.id.card50);
        cardv51 = (ImageView) findViewById(R.id.card51);
        cardv52 = (ImageView) findViewById(R.id.card52);
        cardv53 = (ImageView) findViewById(R.id.card53);
        cardv54 = (ImageView) findViewById(R.id.card54);
        cardv55 = (ImageView) findViewById(R.id.card55);



        cardv00.setTag("0");
        cardv01.setTag("1");
        cardv02.setTag("2");
        cardv03.setTag("3");
        cardv04.setTag("4");
        cardv05.setTag("5");


        cardv10.setTag("6");
        cardv11.setTag("7");
        cardv12.setTag("8");
        cardv13.setTag("9");
        cardv14.setTag("10");
        cardv15.setTag("11");


        cardv20.setTag("12");
        cardv21.setTag("13");
        cardv22.setTag("14");
        cardv23.setTag("15");
        cardv24.setTag("16");
        cardv25.setTag("17");

        cardv30.setTag("18");
        cardv31.setTag("19");
        cardv32.setTag("20");
        cardv33.setTag("21");
        cardv34.setTag("22");
        cardv35.setTag("23");

        cardv40.setTag("24");
        cardv41.setTag("25");
        cardv42.setTag("26");
        cardv43.setTag("27");
        cardv44.setTag("28");
        cardv45.setTag("29");

        cardv50.setTag("30");
        cardv51.setTag("31");
        cardv52.setTag("32");
        cardv53.setTag("33");
        cardv54.setTag("34");
        cardv55.setTag("35");

        cardv00.setEnabled(false);
        cardv01.setEnabled(false);
        cardv02.setEnabled(false);
        cardv03.setEnabled(false);
        cardv04.setEnabled(false);
        cardv05.setEnabled(false);

        cardv10.setEnabled(false);
        cardv11.setEnabled(false);
        cardv12.setEnabled(false);
        cardv13.setEnabled(false);
        cardv14.setEnabled(false);
        cardv15.setEnabled(false);

        cardv20.setEnabled(false);
        cardv21.setEnabled(false);
        cardv22.setEnabled(false);
        cardv23.setEnabled(false);
        cardv24.setEnabled(false);
        cardv25.setEnabled(false);

        cardv30.setEnabled(false);
        cardv31.setEnabled(false);
        cardv32.setEnabled(false);
        cardv33.setEnabled(false);
        cardv34.setEnabled(false);
        cardv35.setEnabled(false);

        cardv40.setEnabled(false);
        cardv41.setEnabled(false);
        cardv42.setEnabled(false);
        cardv43.setEnabled(false);
        cardv44.setEnabled(false);
        cardv45.setEnabled(false);



        cardv50.setEnabled(false);
        cardv51.setEnabled(false);
        cardv52.setEnabled(false);
        cardv53.setEnabled(false);
        cardv54.setEnabled(false);
        cardv55.setEnabled(false);



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
                cardv04.setEnabled(true);
                cardv05.setEnabled(true);

                cardv10.setEnabled(true);
                cardv11.setEnabled(true);
                cardv12.setEnabled(true);
                cardv13.setEnabled(true);
                cardv14.setEnabled(true);
                cardv15.setEnabled(true);

                cardv20.setEnabled(true);
                cardv21.setEnabled(true);
                cardv22.setEnabled(true);
                cardv23.setEnabled(true);
                cardv24.setEnabled(true);
                cardv25.setEnabled(true);

                cardv30.setEnabled(true);
                cardv31.setEnabled(true);
                cardv32.setEnabled(true);
                cardv33.setEnabled(true);
                cardv34.setEnabled(true);
                cardv35.setEnabled(true);

                cardv40.setEnabled(true);
                cardv41.setEnabled(true);
                cardv42.setEnabled(true);
                cardv43.setEnabled(true);
                cardv44.setEnabled(true);
                cardv45.setEnabled(true);



                cardv50.setEnabled(true);
                cardv51.setEnabled(true);
                cardv52.setEnabled(true);
                cardv53.setEnabled(true);
                cardv54.setEnabled(true);
                cardv55.setEnabled(true);

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
                prolog.stop();
                startActivity(new Intent(level6x6tek.this,level4x4tek.class));
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
        cardv04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tag = Integer.parseInt((String) view.getTag());
                sonuc(cardv04,tag);
            }
        });
        cardv05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tag = Integer.parseInt((String) view.getTag());
                sonuc(cardv05,tag);

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
        cardv14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tag = Integer.parseInt((String) view.getTag());
                sonuc(cardv14,tag);
            }
        });
        cardv15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tag = Integer.parseInt((String) view.getTag());
                sonuc(cardv15,tag);

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
        cardv24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tag = Integer.parseInt((String) view.getTag());
                sonuc(cardv24,tag);
            }
        });
        cardv25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tag = Integer.parseInt((String) view.getTag());
                sonuc(cardv25,tag);

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
        cardv34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tag = Integer.parseInt((String) view.getTag());
                sonuc(cardv34,tag);
            }
        });
        cardv35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tag = Integer.parseInt((String) view.getTag());
                sonuc(cardv35,tag);

            }
        });


        cardv40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tag = Integer.parseInt((String) view.getTag());
                sonuc(cardv40,tag);
            }
        });
        cardv41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tag = Integer.parseInt((String) view.getTag());
                sonuc(cardv41,tag);

            }
        });
        cardv42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tag = Integer.parseInt((String) view.getTag());
                sonuc(cardv42,tag);
            }
        });
        cardv43.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tag = Integer.parseInt((String) view.getTag());
                sonuc(cardv43,tag);

            }
        });
        cardv44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tag = Integer.parseInt((String) view.getTag());
                sonuc(cardv44,tag);
            }
        });
        cardv45.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tag = Integer.parseInt((String) view.getTag());
                sonuc(cardv45,tag);

            }
        });



        cardv50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tag = Integer.parseInt((String) view.getTag());
                sonuc(cardv50,tag);
            }
        });
        cardv51.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tag = Integer.parseInt((String) view.getTag());
                sonuc(cardv51,tag);

            }
        });
        cardv52.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tag = Integer.parseInt((String) view.getTag());
                sonuc(cardv52,tag);
            }
        });
        cardv53.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tag = Integer.parseInt((String) view.getTag());
                sonuc(cardv53,tag);

            }
        });
        cardv54.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tag = Integer.parseInt((String) view.getTag());
                sonuc(cardv54,tag);
            }
        });
        cardv55.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tag = Integer.parseInt((String) view.getTag());
                sonuc(cardv55,tag);

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
            card04(card);
            //card.setImageBitmap(bitmapCard(card01()));
        }
        else if (kartDizisi[tag]==105){
            card05(card);
            //card.setImageBitmap(bitmapCard(card01()));
        }
        else if (kartDizisi[tag]==106){
            card10(card);
            //card.setImageBitmap(bitmapCard(card01()));
        }
        else if (kartDizisi[tag]==107){
            card11(card);
            //card.setImageBitmap(bitmapCard(card01()));
        }
        else if (kartDizisi[tag]==108){
            card12(card);
            //card.setImageBitmap(bitmapCard(card01()));
        }
        else if (kartDizisi[tag]==109){
            card13(card);
            //card.setImageBitmap(bitmapCard(card01()));
        }
        else if (kartDizisi[tag]==110){
            card14(card);
            //card.setImageBitmap(bitmapCard(card01()));
        }
        else if (kartDizisi[tag]==111){
            card15(card);
            //card.setImageBitmap(bitmapCard(card01()));
        }
        else if (kartDizisi[tag]==112){
            card20(card);
            //card.setImageBitmap(bitmapCard(card01()));
        }
        else if (kartDizisi[tag]==113){
            card21(card);
            //card.setImageBitmap(bitmapCard(card01()));
        }
        else if (kartDizisi[tag]==114){
            card22(card);

        }
        else if (kartDizisi[tag]==115){
            card23(card);
            //card.setImageBitmap(bitmapCard(card01()));
        }
        else if (kartDizisi[tag]==116){
            card24(card);
            //card.setImageBitmap(bitmapCard(card01()));
        }
        else if (kartDizisi[tag]==117){
            card25(card);
            //card.setImageBitmap(bitmapCard(card01()));
        }

        else if (kartDizisi[tag]==200){
            card30(card);
            //card.setImageBitmap(bitmapCard(card01()));
        }
        else if (kartDizisi[tag]==201){
            card31(card);
            //card.setImageBitmap(bitmapCard(card01()));
        }
        else if (kartDizisi[tag]==202){
            card32(card);
            //card.setImageBitmap(bitmapCard(card01()));
        }
        else if (kartDizisi[tag]==203){
            card33(card);
            //card.setImageBitmap(bitmapCard(card01()));
        }
        else if (kartDizisi[tag]==204){
            card34(card);
            //card.setImageBitmap(bitmapCard(card01()));
        }
        else if (kartDizisi[tag]==205){
            card35(card);
            //card.setImageBitmap(bitmapCard(card01()));
        }
        else if (kartDizisi[tag]==206){
            card40(card);
            //card.setImageBitmap(bitmapCard(card01()));
        }
        else if (kartDizisi[tag]==207){
            card41(card);
            //card.setImageBitmap(bitmapCard(card01()));
        }
        else if (kartDizisi[tag]==208){
            card42(card);
            //card.setImageBitmap(bitmapCard(card01()));
        }
        else if (kartDizisi[tag]==209){
            card43(card);
            //card.setImageBitmap(bitmapCard(card01()));
        }
        else if (kartDizisi[tag]==210){
            card44(card);
            //card.setImageBitmap(bitmapCard(card01()));
        }
        else if (kartDizisi[tag]==211){
            card45(card);
            //card.setImageBitmap(bitmapCard(card01()));
        }
        else if (kartDizisi[tag]==212){
            card50(card);
            //card.setImageBitmap(bitmapCard(card01()));
        }
        else if (kartDizisi[tag]==213){
            card51(card);
            //card.setImageBitmap(bitmapCard(card01()));
        }
        else if (kartDizisi[tag]==214){
            card52(card);
            //card.setImageBitmap(bitmapCard(card01()));
        }
        else if (kartDizisi[tag]==215){
            card53(card);
            //card.setImageBitmap(bitmapCard(card01()));
        }
        else if (kartDizisi[tag]==216){
            card54(card);
            //card.setImageBitmap(bitmapCard(card01()));
        }
        else if (kartDizisi[tag]==217){
            card55(card);
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
            cardv04.setEnabled(false);
            cardv05.setEnabled(false);

            cardv10.setEnabled(false);
            cardv11.setEnabled(false);
            cardv12.setEnabled(false);
            cardv13.setEnabled(false);
            cardv14.setEnabled(false);
            cardv15.setEnabled(false);

            cardv20.setEnabled(false);
            cardv21.setEnabled(false);
            cardv22.setEnabled(false);
            cardv23.setEnabled(false);
            cardv24.setEnabled(false);
            cardv25.setEnabled(false);

            cardv30.setEnabled(false);
            cardv31.setEnabled(false);
            cardv32.setEnabled(false);
            cardv33.setEnabled(false);
            cardv34.setEnabled(false);
            cardv35.setEnabled(false);

            cardv40.setEnabled(false);
            cardv41.setEnabled(false);
            cardv42.setEnabled(false);
            cardv43.setEnabled(false);
            cardv44.setEnabled(false);
            cardv45.setEnabled(false);



            cardv50.setEnabled(false);
            cardv51.setEnabled(false);
            cardv52.setEnabled(false);
            cardv53.setEnabled(false);
            cardv54.setEnabled(false);
            cardv55.setEnabled(false);



            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    esitlik();
                }
            },1000);
        }}



    private void esitlik(){
        if (firstCard==secondCard) {
            if (firstCard==secondCard){
                cgr.start();
            }
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
                cardv04.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 5){
                cardv05.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 6){
                cardv10.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 7){
                cardv11.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 8){
                cardv12.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 9){
                cardv13.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 10){
                cardv14.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 11){
                cardv15.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 12){
                cardv20.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 13){
                cardv21.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 14){
                cardv22.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 15){
                cardv23.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 16){
                cardv24.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 17){
                cardv25.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 18){
                cardv30.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 19){
                cardv31.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 20){
                cardv32.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 21){
                cardv33.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 22){
                cardv34.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 23){
                cardv35.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 24){
                cardv40.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 25){
                cardv41.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 26){
                cardv42.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 27){
                cardv43.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 28){
                cardv44.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 29){
                cardv45.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 30){
                cardv50.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 31){
                cardv51.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 32){
                cardv52.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 33){
                cardv53.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 34){
                cardv54.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 35){
                cardv55.setVisibility(View.INVISIBLE);
            }


            if(getClickedSecond== 0){
                cardv00.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond== 1){
                cardv01.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond== 2){
                cardv02.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond== 3){
                cardv03.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond== 4){
                cardv04.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond== 5){
                cardv05.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond== 6){
                cardv10.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond== 7){
                cardv11.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond== 8){
                cardv12.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond== 9){
                cardv13.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond== 10){
                cardv14.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond== 11){
                cardv15.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond== 12){
                cardv20.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond== 13){
                cardv21.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond== 14){
                cardv22.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond== 15){
                cardv23.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond== 16){
                cardv24.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond== 17){
                cardv25.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond== 18){
                cardv30.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond== 19){
                cardv31.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond== 20){
                cardv32.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond== 21){
                cardv33.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond== 22){
                cardv34.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond== 23){
                cardv35.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond== 24){
                cardv40.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond== 25){
                cardv41.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond== 26){
                cardv42.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond== 27){
                cardv43.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond== 28){
                cardv44.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond== 29){
                cardv45.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond== 30){
                cardv50.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond== 31){
                cardv51.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond== 32){
                cardv52.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond== 33){
                cardv53.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond== 34){
                cardv54.setVisibility(View.INVISIBLE);
            }
            else if(getClickedSecond== 35){
                cardv55.setVisibility(View.INVISIBLE);
            }
            player1Points += puan;

            score1.setText("Player1: "+player1Points);
        }
        else{
            cardv00.setImageResource(R.drawable.back);
            cardv01.setImageResource(R.drawable.back);
            cardv02.setImageResource(R.drawable.back);
            cardv03.setImageResource(R.drawable.back);
            cardv04.setImageResource(R.drawable.back);
            cardv05.setImageResource(R.drawable.back);

            cardv10.setImageResource(R.drawable.back);
            cardv11.setImageResource(R.drawable.back);
            cardv12.setImageResource(R.drawable.back);
            cardv13.setImageResource(R.drawable.back);
            cardv14.setImageResource(R.drawable.back);
            cardv15.setImageResource(R.drawable.back);

            cardv20.setImageResource(R.drawable.back);
            cardv21.setImageResource(R.drawable.back);
            cardv22.setImageResource(R.drawable.back);
            cardv23.setImageResource(R.drawable.back);
            cardv24.setImageResource(R.drawable.back);
            cardv25.setImageResource(R.drawable.back);

            cardv30.setImageResource(R.drawable.back);
            cardv31.setImageResource(R.drawable.back);
            cardv32.setImageResource(R.drawable.back);
            cardv33.setImageResource(R.drawable.back);
            cardv34.setImageResource(R.drawable.back);
            cardv35.setImageResource(R.drawable.back);


            cardv40.setImageResource(R.drawable.back);
            cardv41.setImageResource(R.drawable.back);
            cardv42.setImageResource(R.drawable.back);
            cardv43.setImageResource(R.drawable.back);
            cardv44.setImageResource(R.drawable.back);
            cardv45.setImageResource(R.drawable.back);

            cardv50.setImageResource(R.drawable.back);
            cardv51.setImageResource(R.drawable.back);
            cardv52.setImageResource(R.drawable.back);
            cardv53.setImageResource(R.drawable.back);
            cardv54.setImageResource(R.drawable.back);
            cardv55.setImageResource(R.drawable.back);

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
        cardv04.setEnabled(true);
        cardv05.setEnabled(true);

        cardv10.setEnabled(true);
        cardv11.setEnabled(true);
        cardv12.setEnabled(true);
        cardv13.setEnabled(true);
        cardv14.setEnabled(true);
        cardv15.setEnabled(true);

        cardv20.setEnabled(true);
        cardv21.setEnabled(true);
        cardv22.setEnabled(true);
        cardv23.setEnabled(true);
        cardv24.setEnabled(true);
        cardv25.setEnabled(true);

        cardv30.setEnabled(true);
        cardv31.setEnabled(true);
        cardv32.setEnabled(true);
        cardv33.setEnabled(true);
        cardv34.setEnabled(true);
        cardv35.setEnabled(true);

        cardv40.setEnabled(true);
        cardv41.setEnabled(true);
        cardv42.setEnabled(true);
        cardv43.setEnabled(true);
        cardv44.setEnabled(true);
        cardv45.setEnabled(true);



        cardv50.setEnabled(true);
        cardv51.setEnabled(true);
        cardv52.setEnabled(true);
        cardv53.setEnabled(true);
        cardv54.setEnabled(true);
        cardv55.setEnabled(true);



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
                cardv10.getVisibility() == View.INVISIBLE&&
                cardv11.getVisibility() == View.INVISIBLE){

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
        AlertDialog.Builder builder = new AlertDialog.Builder(level6x6tek.this);
        builder
                .setMessage("GAME OVER\nP1:"+player1Points)
                .setCancelable(false)
                .setPositiveButton("NEW", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(level6x6tek.this,level6x6tek.class));
                    }
                }).setNegativeButton("EXIT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        tebrikler.stop();
                        prolog.stop();
                        cgr.stop();
                        surebitti.stop();
                        startActivity(new Intent(level6x6tek.this,preference.class));
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
        DocumentReference docRef = db.document("cards/"+gryf[numbers.get(2)]);
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
        DocumentReference docRef = db.document("cards/"+gryf[numbers.get(3)]);
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
    private void card04(ImageView card) {
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
                writeToFile("card04  "+str.toString());
                str.clear();
            }
        });
    }
    private void card05(ImageView card) {
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
                writeToFile("card05  "+str.toString());
                str.clear();
            }
        });
    }
    private void card10(ImageView card) {
        DocumentReference docRef = db.document("cards/"+huff[numbers.get(2)]);
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
        DocumentReference docRef = db.document("cards/"+huff[numbers.get(3)]);
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
                writeToFile("card12  "+str.toString());
                str.clear();
            }
        });
    }
    private void card13(ImageView card) {
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
                writeToFile("card13  "+str.toString());
                str.clear();
            }
        });

    }
    private void card14(ImageView card) {
        DocumentReference docRef = db.document("cards/"+sly[numbers.get(2)]);
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
                writeToFile("card14  "+str.toString());
                str.clear();
            }
        });
    }
    private void card15(ImageView card) {
        DocumentReference docRef = db.document("cards/"+sly[numbers.get(3)]);
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
                writeToFile("card15  "+str.toString());
                str.clear();
            }
        });
    }

    private void card20(ImageView card) {
        DocumentReference docRef = db.document("cards/"+sly[numbers.get(4)]);
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
                writeToFile("card21  "+str.toString());
                str.clear();
            }
        });
    }
    private void card22(ImageView card) {
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
                writeToFile("card22  "+str.toString());
                str.clear();
            }
        });
    }
    private void card23(ImageView card) {
        DocumentReference docRef = db.document("cards/"+rawen[numbers.get(2)]);
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
    private void card24(ImageView card) {
        DocumentReference docRef = db.document("cards/"+rawen[numbers.get(3)]);
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
                writeToFile("card24  "+str.toString());
                str.clear();
            }
        });
    }
    private void card25(ImageView card) {
        DocumentReference docRef = db.document("cards/"+rawen[numbers.get(4)]);
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
                writeToFile("card25  "+str.toString());
                str.clear();
            }
        });
    }




    private void card30(ImageView card) {
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
                writeToFile("card30  "+str.toString());
                str.clear();
            }
        });
    }

    private void card31(ImageView card) {
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
                writeToFile("card31  "+str.toString());
                str.clear();
            }
        });
    }
    private void card32(ImageView card) {
        DocumentReference docRef = db.document("cards/"+gryf[numbers.get(2)]);
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
        DocumentReference docRef = db.document("cards/"+gryf[numbers.get(3)]);
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
    private void card34(ImageView card) {
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
                writeToFile("card34  "+str.toString());
                str.clear();
            }
        });

    }
    private void card35(ImageView card) {
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
                writeToFile("card35  "+str.toString());
                str.clear();
            }
        });
    }


    private void card40(ImageView card) {
        DocumentReference docRef = db.document("cards/"+huff[numbers.get(2)]);
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
                writeToFile("card40  "+str.toString());
                str.clear();
            }
        });
    }
    private void card41(ImageView card) {
        DocumentReference docRef = db.document("cards/"+huff[numbers.get(3)]);
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
                writeToFile("card41  "+str.toString());
                str.clear();
            }
        });

    }
    private void card42(ImageView card) {
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
                writeToFile("card42  "+str.toString());
                str.clear();
            }
        });
    }
    private void card43(ImageView card) {
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
                writeToFile("card43  "+str.toString());
                str.clear();
            }
        });

    }
    private void card44(ImageView card) {
        DocumentReference docRef = db.document("cards/"+sly[numbers.get(2)]);
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
                writeToFile("card44  "+str.toString());
                str.clear();
            }
        });
    }
    private void card45(ImageView card) {
        DocumentReference docRef = db.document("cards/"+sly[numbers.get(3)]);
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
                writeToFile("card45  "+str.toString());
                str.clear();
            }
        });

    }


    private void card50(ImageView card) {
        DocumentReference docRef = db.document("cards/"+sly[numbers.get(4)]);
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
                writeToFile("card50  "+str.toString());
                str.clear();
            }
        });

    }
    private void card51(ImageView card) {
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
                writeToFile("card51  "+str.toString());
                str.clear();
            }
        });
    }
    private void card52(ImageView card) {
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
                writeToFile("card52  "+str.toString());
                str.clear();
            }
        });

    }
    private void card53(ImageView card) {
        DocumentReference docRef = db.document("cards/"+rawen[numbers.get(2)]);
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
                writeToFile("card53  "+str.toString());
                str.clear();
            }
        });
    }
    private void card54(ImageView card) {
        DocumentReference docRef = db.document("cards/"+rawen[numbers.get(3)]);
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
                writeToFile("card54  "+str.toString());
                str.clear();
            }
        });
    }
    private void card55(ImageView card) {
        DocumentReference docRef = db.document("cards/"+rawen[numbers.get(4)]);
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
                writeToFile("card55  "+str.toString());
                str.clear();
            }

        });
    }


}