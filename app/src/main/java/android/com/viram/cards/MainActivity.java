package android.com.viram.cards;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.ContentHandlerFactory;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    int user = 0;
    int turn = 1;
    int kkr=0;
    String avail;
    int cardSeen = 0;
    String ispack0, ispack1, isblind0, isblind1, isshow, issee0, issee1;
    String pp0, pp1, pp2, b0, b1, b2;
    TextView myC1;
    TextView oppC1, u0s, u1s, winners, u0photo, u1photo;
    Button see;
    Button pack, blind, show;
    LinearLayout mi,csk;

    @SuppressLint("WrongConstant")
    void changeTurn() {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        if (turn == 0) {
            turn = 1;

            ref.child("Game").child("u0").child("turn").setValue("1");
            u1photo.setBackgroundResource(R.drawable.border);
            u0photo.setBackgroundResource(R.drawable.noborder);
            if (turn != user) {
                pack.setVisibility(View.INVISIBLE);

                blind.setVisibility(View.INVISIBLE);
                show.setVisibility(View.INVISIBLE);
            }
            if (turn == user) {
                pack.setVisibility(View.VISIBLE);
                show.setVisibility(View.VISIBLE);
                blind.setVisibility(View.VISIBLE);
            }
        } else {
            turn = 0;
            u0photo.setBackgroundResource(R.drawable.border);
            u1photo.setBackgroundResource(R.drawable.noborder);
            ref.child("Game").child("u0").child("turn").setValue("0");


            if (turn != user) {
                pack.setVisibility(View.INVISIBLE);

                show.setVisibility(View.INVISIBLE);
                blind.setVisibility(View.INVISIBLE);
            }
            if (turn == user) {
                pack.setVisibility(View.VISIBLE);

                show.setVisibility(View.VISIBLE);
                blind.setVisibility(View.VISIBLE);
            }

        }


    }


    void u1change() {

    }

    public void game() {


        if (user == 0) {
            final Handler h1 = new Handler();
            h1.postDelayed(new Runnable() {
                @Override
                public void run() {
                    changeTurn();
                    h1.postDelayed(this, 5000);
                }
            }, 1);
        }


    }

    public static String IntToMain(int a)
    {
        String temp=new String();
        if(a > 9)
        {
            if(a == 10)
                temp="t";
            else if(a == 11)
                temp="j";
            else if(a == 12)
                temp="q";
            else if(a == 13)
                temp="k";
        }
        else
            temp=String.valueOf(a);
        return temp;
    }

    public static String Int_To_suits(int a)
    {
        String temp=new String();
        if(a == 0)
            temp="r";
        if(a == 1)
            temp="R";
        else if(a == 2)
            temp="b";
        else if(a == 3)
            temp="B";
        return temp;
    }
    public static int decode(String s)
    {
        char c=s.charAt(1);
        int i;
        if(c == 't') return 10;
        else if(c == 'j') return 11;
        else if(c == 'q') return 12;
        else if(c == 'k') return 13;
        else if(c == '1') return 15;
        else
        {
            i=Character.getNumericValue(c);
            return i;
        }
    }
    public static int decodei(String s)
    {
        char c=s.charAt(1);
        int i;
        if(c == 't') return 10;
        else if(c == 'j') return 11;
        else if(c == 'q') return 12;
        else if(c == 'k') return 13;
        else if(c == '1') return 15;
        else
        {
            i=Character.getNumericValue(c);
            return i;
        }
    }



    String [] p1=new String[3];
    String [] p2=new String[3];
    int f1=0;
    void createCards()
    {
        f1=0;
        Random rand = new Random();
        //type1
        HashSet<String> h = new HashSet<String>();
        int sagar;
        int card1,card2,card3,card4,card5,card6;
        int r,r1,tt1,s1=0,s2=0;
        String temp=new String();
        int [] hash=new int[4];
        int [] hash1=new int[13];
        String [] c1=new String[3];
        String [] c2=new String[3];
        String [] c3=new String[3];
        String [] c4=new String[3];
        String [] c5=new String[3];
        String [] c6=new String[3];

        int i=0;
        while(i < 2)
        {
            sagar=rand.nextInt(4);
            //sagar=rand.nextInt(10);
            System.out.println(sagar);
            if(sagar == 0)
            {
                card1 = rand.nextInt(13)+1;
                temp=IntToMain(card1);

                r=rand.nextInt(4);

                if(r == 0)
                {
                    c1[0]="R";
                    c1[1]="b";
                    c1[2]="B";
                    c1[0]=c1[0].concat(temp);
                    c1[1]=c1[1].concat(temp);
                    c1[2]=c1[2].concat(temp);
                }
                else if(r == 1)
                {
                    c1[0]="r";
                    c1[1]="b";
                    c1[2]="B";
                    c1[0]=c1[0].concat(temp);
                    c1[1]=c1[1].concat(temp);
                    c1[2]=c1[2].concat(temp);
                }
                else if(r == 2)
                {
                    c1[0]="r";
                    c1[1]="R";
                    c1[2]="B";
                    c1[0]=c1[0].concat(temp);
                    c1[1]=c1[1].concat(temp);
                    c1[2]=c1[2].concat(temp);
                }
                else if(r == 3)
                {
                    c1[0]="r";
                    c1[1]="R";
                    c1[2]="b";
                    c1[0]=c1[0].concat(temp);
                    c1[1]=c1[1].concat(temp);
                    c1[2]=c1[2].concat(temp);
                }
                if(i == 0)
                {
                    p1[0]=c1[0];
                    p1[1]=c1[1];
                    p1[2]=c1[2];
                    s1=1;
                }
                else
                {
                    p2[0]=c1[0];
                    p2[1]=c1[1];
                    p2[2]=c1[2];
                    s2=1;
                }

            }
            if(sagar == 1) //type2
            {
                card2 = rand.nextInt(12)+1;
                r=rand.nextInt(4);

                if(r == 0)
                {
                    c2[0]="r";
                    c2[1]="r";
                    c2[2]="r";
                    if(card2 == 12)
                    {
                        c2[0]=c2[0].concat("1");
                        c2[1]=c2[1].concat("k");
                        c2[2]=c2[2].concat("q");
                    }
                    else {
                        card2 = card2 + 1;
                        temp = IntToMain(card2);
                        c2[0] = c2[0].concat(temp);
                        card2++;

                        card2 = card2 + 1;
                        temp = IntToMain(card2);
                        c2[1] = c2[1].concat(temp);
                        card2++;

                        card2 = card2 + 1;
                        temp = IntToMain(card2);
                        c2[2] = c2[2].concat(temp);
                    }
                }
                else if(r == 1)
                {
                    c2[0]="R";
                    c2[1]="R";
                    c2[2]="R";
                    if(card2 == 12)
                    {
                        c2[0]=c2[0].concat("1");
                        c2[1]=c2[1].concat("k");
                        c2[2]=c2[2].concat("q");
                    }
                    else {
                        card2 = card2 + 1;
                        temp = IntToMain(card2);
                        c2[0] = c2[0].concat(temp);
                        card2++;

                        card2 = card2 + 1;
                        temp = IntToMain(card2);
                        c2[1] = c2[1].concat(temp);
                        card2++;

                        card2 = card2 + 1;
                        temp = IntToMain(card2);
                        c2[2] = c2[2].concat(temp);
                    }
                }
                else if(r == 2)
                {
                    c2[0]="b";
                    c2[1]="b";
                    c2[2]="b";
                    if(card2 == 12)
                    {
                        c2[0]=c2[0].concat("1");
                        c2[1]=c2[1].concat("k");
                        c2[2]=c2[2].concat("q");
                    }
                    else {
                        card2 = card2 + 1;
                        temp = IntToMain(card2);
                        c2[0] = c2[0].concat(temp);
                        card2++;

                        card2 = card2 + 1;
                        temp = IntToMain(card2);
                        c2[1] = c2[1].concat(temp);
                        card2++;

                        card2 = card2 + 1;
                        temp = IntToMain(card2);
                        c2[2] = c2[2].concat(temp);
                    }
                }
                else if(r == 3)
                {
                    c2[0]="B";
                    c2[1]="B";
                    c2[2]="B";
                    if(card2 == 12)
                    {
                        c2[0]=c2[0].concat("1");
                        c2[1]=c2[1].concat("k");
                        c2[2]=c2[2].concat("q");
                    }
                    else {
                        card2 = card2 + 1;
                        temp = IntToMain(card2);
                        c2[0] = c2[0].concat(temp);
                        card2++;

                        card2 = card2 + 1;
                        temp = IntToMain(card2);
                        c2[1] = c2[1].concat(temp);
                        card2++;

                        card2 = card2 + 1;
                        temp = IntToMain(card2);
                        c2[2] = c2[2].concat(temp);
                    }
                }
                if(i == 0)
                {
                    p1[0]=c2[0];
                    p1[1]=c2[1];
                    p1[2]=c2[2];
                    s1=2;
                }
                else
                {
                    p2[0]=c2[0];
                    p2[1]=c2[1];
                    p2[2]=c2[2];
                    s2=2;
                }
            }
            else if(sagar == 2) //type3
            {
                card3 = rand.nextInt(12)+1;
                hash[0]=0;
                hash[1]=0;
                hash[2]=0;
                hash[3]=0;

                r=rand.nextInt(4);
                hash[r]=1;
                c3[0] = Int_To_suits(r);
                r=rand.nextInt(4);
                while(hash[r] == 1)
                    r=rand.nextInt(4);
                hash[r]=1;
                c3[1] = Int_To_suits(r);
                r=rand.nextInt(4);
                while(hash[r] == 1)
                    r=rand.nextInt(4);
                c3[2] = Int_To_suits(r);
                if(card3 == 12)
                {
                    c3[0] = c3[0].concat("1");
                    c3[1] = c3[1].concat("k");
                    c3[2] = c3[2].concat("q");
                }
                else {

                    temp = IntToMain(card3);
                    c3[0] = c3[0].concat(temp);
                    card3++;
                    temp = IntToMain(card3);
                    c3[1] = c3[1].concat(temp);
                    card3++;
                    temp = IntToMain(card3);
                    c3[2] = c3[2].concat(temp);
                }
                if(i == 0)
                {
                    p1[0]=c3[0];
                    p1[1]=c3[1];
                    p1[2]=c3[2];
                    s1=3;
                }
                else
                {
                    p2[0]=c3[0];
                    p2[1]=c3[1];
                    p2[2]=c3[2];
                    s2=3;
                }

            }
            else if(sagar == 3)//type4
            {
                Arrays.fill(hash1,0);
                r=rand.nextInt(4);

                c4[0]=Int_To_suits(r);
                c4[1]=Int_To_suits(r);
                c4[2]=Int_To_suits(r);

                card4=rand.nextInt(13)+1;

                hash1[card4-1]=1;
                temp=IntToMain(card4);
                c4[0]=c4[0].concat(temp);

                card4=rand.nextInt(13)+1;
                while(hash1[card4-1] == 1)
                    card4=rand.nextInt(13)+1;
                hash1[card4-1]=1;
                temp=IntToMain(card4);
                c4[1]=c4[1].concat(temp);

                card4=rand.nextInt(13)+1;
                while(hash1[card4-1] == 1)
                    card4=rand.nextInt(13)+1;
                hash1[card4-1]=1;
                temp=IntToMain(card4);
                c4[2]=c4[2].concat(temp);

                if(i == 0)
                {
                    p1[0]=c4[0];
                    p1[1]=c4[1];
                    p1[2]=c4[2];
                    s1=4;
                }
                else
                {
                    p2[0]=c4[0];
                    p2[1]=c4[1];
                    p2[2]=c4[2];
                    s2=4;
                }


            }
            else if(sagar == 4)
            {//type5
                hash[0]=0;
                hash[1]=0;
                hash[2]=0;
                hash[3]=0;

                r=rand.nextInt(4);
                c5[0]=Int_To_suits(r);
                hash[r]=1;
                r=rand.nextInt(4);
                while(hash[r] == 1)
                    r=rand.nextInt(4);
                c5[1]=Int_To_suits(r);
                card5=rand.nextInt(13)+1;
                temp=IntToMain(card5);
                c5[0]=c5[0].concat(temp);
                c5[1]=c5[1].concat(temp);

                r1=rand.nextInt(13)+1;
                while(r1 == card5)
                    r1=rand.nextInt(13)+1;

                r=rand.nextInt(4);
                while(hash[r] == 1)
                    r=rand.nextInt(4);

                c5[2]=Int_To_suits(r);
                temp=IntToMain(r1);
                c5[2]=c5[2].concat(temp);

                if(i == 0)
                {
                    p1[0]=c5[0];
                    p1[1]=c5[1];
                    p1[2]=c5[2];
                    s1=5;
                }
                else
                {
                    p2[0]=c5[0];
                    p2[1]=c5[1];
                    p2[2]=c5[2];
                    s2=5;
                }



            }
            else if(sagar >= 5)
            {			//type 6
                hash[0]=0;
                hash[1]=0;
                hash[2]=0;
                hash[3]=0;



                Arrays.fill(hash1,0);

                card6=rand.nextInt(13)+1;
                tt1=card6;
                r=rand.nextInt(4);
                hash1[card6-1]=1;
                hash[r]=1;
                c6[0]=Int_To_suits(r);
                temp=IntToMain(card6);
                c6[0]=c6[0].concat(temp);

                card6=rand.nextInt(13)+1;
                r=rand.nextInt(4);
                while(hash1[card6-1] == 1 || hash[r] == 1 || ((card6 -1 -1 + 13) %13) + 1   == tt1 || ((card6 - 1 + 1) % 13) + 1 == tt1)
                {
                    card6=rand.nextInt(13)+1;
                    r=rand.nextInt(4);
                }
                tt1=card6;
                hash1[card6-1]=1;
                hash[r]=1;
                c6[1]=Int_To_suits(r);
                temp=IntToMain(card6);
                c6[1]=c6[1].concat(temp);

                card6=rand.nextInt(13)+1;
                r=rand.nextInt(4);
                while(hash1[card6-1] == 1 || hash[r] == 1 || ((card6 -1 -1 + 13) %13) + 1   == tt1 || ((card6 - 1 + 1) % 13) + 1 == tt1)
                {
                    card6=rand.nextInt(13)+1;
                    r=rand.nextInt(4);
                }
                hash1[card6-1]=1;
                hash[r]=1;
                c6[2]=Int_To_suits(r);
                temp=IntToMain(card6);
                c6[2]=c6[2].concat(temp);
                if(i == 0)
                {
                    p1[0]=c6[0];
                    p1[1]=c6[1];
                    p1[2]=c6[2];
                    s1=6;
                }
                else
                {
                    p2[0]=c6[0];
                    p2[1]=c6[1];
                    p2[2]=c6[2];
                    s2=6;
                }
            }
            if(i == 0)
            {
                h.add(p1[0]);
                h.add(p1[1]);
                h.add(p1[2]);
            }

            if(i==1 && !h.contains(p2[0]) && !h.contains(p2[1]) && !h.contains(p2[2]) )
            {
                i++;
            }
            if(i == 0)
            {
                i++;
            }

        }
        for(int k=0;k < 2;k++)
        {
            for(int j=0;j < 2-k;j++)
            {
                if(decodei(p1[j]) < decodei(p1[j+1]))
                {
                    temp=p1[j];
                    p1[j]=p1[j+1];
                    p1[j+1]=temp;
                }
            }
        }

        for(int k=0;k < 2;k++)
        {
            for(int j=0;j < 2-k;j++)
            {
                if(decodei(p2[j]) < decodei(p2[j+1]))
                {
                    temp=p2[j];
                    p2[j]=p2[j+1];
                    p2[j+1]=temp;
                }
            }
        }

        p1[0]+=String.valueOf(s1);
        p1[1]+=String.valueOf(s1);
        p1[2]+=String.valueOf(s1);

        p2[0]+=String.valueOf(s2);
        p2[1]+=String.valueOf(s2);
        p2[2]+=String.valueOf(s2);

        if( Character.getNumericValue(p1[0].charAt(2))  < Character.getNumericValue(p2[0].charAt(2)))
        {
            f1=0;
        }
        else if(Character.getNumericValue(p1[0].charAt(2))  > Character.getNumericValue(p2[0].charAt(2)))
        {
            f1=1;
        }
        else
        {
            if(decodei(p1[0]) < decodei(p2[0]))
                f1=1;
            else if(decodei(p1[0]) > decodei(p2[0]))
                f1=0;
            else
            {
                if(decodei(p1[1]) < decodei(p2[1]))
                    f1=1;
                else if(decodei(p1[1]) > decodei(p2[1]))
                    f1=0;
                else
                {
                    if(decodei(p1[1]) < decodei(p2[1]))
                        f1=1;
                    else if(decodei(p1[1]) > decodei(p2[1]))
                        f1=0;
                    else
                    {
                        f1=-1;
                    }
                }
            }
        }
    }

String w;
    int rcb=0,srh=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        createCards();
        String f=""+f1;
        Bundle bundle = getIntent().getExtras();

//Extract the data…
        String s12 = bundle.getString("cr");
        if (s12.equals("0")) {
            user = 0;
        } else {
            user = 1;
        }
        csk=findViewById(R.id.csk);
        mi = findViewById(R.id.mi);
        myC1 = findViewById(R.id.myC);
        oppC1 = findViewById(R.id.oppC);
        see = (Button) findViewById(R.id.see);
        pack = findViewById(R.id.pack);

        blind = findViewById(R.id.blind);
        show = findViewById(R.id.show);
        u0s = findViewById(R.id.u0s);
        u1s = findViewById(R.id.u1s);
        winners = findViewById(R.id.winners);
        u0photo = findViewById(R.id.u0photo);
        u1photo = findViewById(R.id.u1photo);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference().child("Game").child("u0");
        if(user==0) {
            ref.child("p0").setValue(p1[0]);
            ref.child("p1").setValue(p1[1]);

            ref.child("p2").setValue(p1[2]);

            ref.child("b0").setValue(p2[0]);

            ref.child("b1").setValue(p2[1]);

            ref.child("b2").setValue(p2[2]);
            ref.child("winner").setValue(f);


        }final ValueEventListener V = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String t = dataSnapshot.child("turn").getValue().toString();

                b0 = dataSnapshot.child("b0").getValue().toString();
                b1 = dataSnapshot.child("b1").getValue().toString();
                b2 = dataSnapshot.child("b2").getValue().toString();
                pp0 = dataSnapshot.child("p0").getValue().toString();
                pp1 = dataSnapshot.child("p1").getValue().toString();
                pp2 = dataSnapshot.child("p2").getValue().toString();
                issee0 = dataSnapshot.child("issee0").getValue().toString();
                issee1 = dataSnapshot.child("issee1").getValue().toString();
                ispack0 = dataSnapshot.child("ispack0").getValue().toString();
                ispack1 = dataSnapshot.child("ispack1").getValue().toString();
                isblind0 = dataSnapshot.child("isblind0").getValue().toString();
                isblind1 = dataSnapshot.child("isblind1").getValue().toString();
                isshow = dataSnapshot.child("isshow").getValue().toString();
                avail = dataSnapshot.child("u1").getValue().toString();
                w=dataSnapshot.child("winner").getValue().toString();
                Toast.makeText(getApplicationContext(), ""+user, Toast.LENGTH_SHORT).show();
                if (user == 0) {
                    if (avail.equals("Wait")) {
                        csk.setVisibility(View.VISIBLE);
                        mi.setVisibility(View.INVISIBLE);
                    } else {
                        mi.setVisibility(View.VISIBLE);
                        csk.setVisibility(View.INVISIBLE);
                    }
                }
                else if(user==1)
                {
                    csk.setVisibility(View.INVISIBLE);
                    mi.setVisibility(View.VISIBLE);
                }
                if (user == 1) {
                    if (t.equals("1")) {
                        u1photo.setBackgroundResource(R.drawable.border);
                        u0photo.setBackgroundResource(R.drawable.noborder);
                        pack.setVisibility(View.VISIBLE);

                        blind.setVisibility(View.VISIBLE);
                        show.setVisibility(View.VISIBLE);
                    } else {
                        u1photo.setBackgroundResource(R.drawable.noborder);
                        u0photo.setBackgroundResource(R.drawable.border);
                        pack.setVisibility(View.INVISIBLE);

                        blind.setVisibility(View.INVISIBLE);
                        show.setVisibility(View.INVISIBLE);
                    }
                }
                if (isblind0.equals("1")) {
                    u0s.setText("Blind");
                } else {
                    u0s.setText("Card Seen");
                }
                if (isblind1.equals("1")) {
                    u1s.setText("Blind");
                } else {
                    u1s.setText("Card Seen");
                }
                if (ispack0.equals("1")) {
                    kkr=1;
                    if (user == 0)
                        winners.setText("You Loose");
                    else
                        winners.setText("You Won");

                    if(srh==0) {
                        srh=1;
                        Handler hh = new Handler();
                        Runnable r = new Runnable() {
                            @Override
                            public void run() {
                                Intent i = new Intent(getApplicationContext(), MainActivity.class);

                                Bundle bundle = new Bundle();

                                if (user == 1)
                                    bundle.putString("cr", "1");
                                else
                                    bundle.putString("cr", "0");
//Add the bundle to the intent
                                i.putExtras(bundle);

//Fire that second activity
                                startActivity(i);

                                finish();
                            }
                        };
                        hh.postDelayed(r, 3000);
                    }
                }

                if (ispack1.equals("1")) {
                    kkr=1;
                    if (user == 1)
                        winners.setText("You Loose");
                    else
                        winners.setText("You Won");

                    if(srh==0) {
                     srh=1;
                        Handler hh = new Handler();
                        Runnable r = new Runnable() {
                            @Override
                            public void run() {
                                Intent i = new Intent(getApplicationContext(), MainActivity.class);

                                Bundle bundle = new Bundle();

                                if (user == 1)
                                    bundle.putString("cr", "1");
                                else
                                    bundle.putString("cr", "0");
//Add the bundle to the intent
                                i.putExtras(bundle);


                                startActivity(i);
                                finish();
                            }
                        };
                        hh.postDelayed(r, 3000);
                    }
                }
                if (isshow.equals("1")) {
                    if(kkr!=1) {
                        if (user == 0 && w.equals("0"))
                            winners.setText("You Won");
                        else if (user == 0 && w.equals("1"))
                            winners.setText("You Loose");
                        else if (user == 1 && w.equals("0"))
                            winners.setText("You Loose");
                        else if (user == 1 && w.equals("1"))
                            winners.setText("You Won");
                        myC1.setText(pp0 + " " + pp1 + " " + pp2);
                        oppC1.setText(b0 + " " + b1 + " " + b2);
                        if(srh==0) {
                         srh=1;
                            Handler hh = new Handler();
                            Runnable r = new Runnable() {
                                @Override
                                public void run() {
                                    Intent i = new Intent(getApplicationContext(), MainActivity.class);

                                    Bundle bundle = new Bundle();

                                    if (user == 1)
                                        bundle.putString("cr", "1");
                                    else
                                        bundle.putString("cr", "0");
//Add the bundle to the intent
                                    i.putExtras(bundle);

                                    startActivity(i);
                                    finish();
                                }
                            };
                            hh.postDelayed(r, 3000);
                        }

                    }
                }
                if(user==0) {
                    Handler hu = new Handler();
                    if (!avail.equals("Wait") && rcb==0) {
                        hu.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                game();
                            }
                        }, 1);
                    rcb=1;
                    }
                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        ref.addValueEventListener(V);

        ref.child("turn").setValue("1");
        ref.child("turn").setValue("0");

        ref.child("seen").setValue("0");
        ref.child("issee0").setValue("0");

        ref.child("issee1").setValue("0");
        ref.child("isblind0").setValue("1");
        ref.child("isblind1").setValue("1");
        ref.child("isshow").setValue("0");
        ref.child("ispack0").setValue("0");
        ref.child("ispack1").setValue("0");


        see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (user == 0) {
                    myC1.setText(pp0 + " " + pp1 + " " + pp2);
                    ref.child("issee0").setValue("1");
                    ref.child("isblind0").setValue("0");
                } else {
                    oppC1.setText(b0 + " " + b1 + " " + b2);
                    ref.child("issee1").setValue("1");

                    ref.child("isblind1").setValue("0");
                }


            }
        });
        pack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user == 0) {
                    ref.child("ispack0").setValue("1");
                } else {
                    ref.child("ispack1").setValue("1");
                }

            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.child("isshow").setValue("1");
            }
        });

    }


}
