package android.com.viram.cards;



import android.annotation.SuppressLint;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.ContentHandlerFactory;


public class MainActivity extends AppCompatActivity {
    int user = 0;
    int turn = 1;
    int cardSeen=0;
    String ispack0,ispack1,isblind0,isblind1,isshow,issee0,issee1;
    String p0,p1,p2,b0,b1,b2;
    TextView myC1;
    TextView oppC1,u0s,u1s,winners,u0photo,u1photo;
    Button see;
    Button pack,blind,show;

    @SuppressLint("WrongConstant")
    void changeTurn() {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        if (turn == 0) {
            turn = 1;

            ref.child("g1").child("u0").child("turn").setValue("1");
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
            ref.child("g1").child("u0").child("turn").setValue("0");


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        myC1 = findViewById(R.id.myC);
        oppC1 = findViewById(R.id.oppC);
        see = (Button) findViewById(R.id.see);
        pack = findViewById(R.id.pack);

        blind=findViewById(R.id.blind);
        show=findViewById(R.id.show);
        u0s=findViewById(R.id.u0s);
        u1s=findViewById(R.id.u1s);
        winners=findViewById(R.id.winners);
        u0photo=findViewById(R.id.u0photo);
        u1photo=findViewById(R.id.u1photo);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference().child("g1").child("u0");
        ValueEventListener V = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String t = dataSnapshot.child("turn").getValue().toString();

                 b0 = dataSnapshot.child("b0").getValue().toString();
                 b1 = dataSnapshot.child("b1").getValue().toString();
                 b2 = dataSnapshot.child("b2").getValue().toString();
                 p0 = dataSnapshot.child("p0").getValue().toString();
                 p1 = dataSnapshot.child("p1").getValue().toString();
                 p2 = dataSnapshot.child("p2").getValue().toString();
                 issee0=dataSnapshot.child("issee0").getValue().toString();
                issee1=dataSnapshot.child("issee1").getValue().toString();
                ispack0=dataSnapshot.child("ispack0").getValue().toString();
                ispack1=dataSnapshot.child("ispack1").getValue().toString();
                isblind0=dataSnapshot.child("isblind0").getValue().toString();
                isblind1=dataSnapshot.child("isblind1").getValue().toString();
                 isshow=dataSnapshot.child("isshow").getValue().toString();

                 if(user==1)
                 {
                     if(t.equals("1"))
                     {
                         u1photo.setBackgroundResource(R.drawable.border);
                         u0photo.setBackgroundResource(R.drawable.noborder);
                         pack.setVisibility(View.VISIBLE);

                         blind.setVisibility(View.VISIBLE);
                         show.setVisibility(View.VISIBLE);
                     }
                     else
                     {
                         u1photo.setBackgroundResource(R.drawable.noborder);
                         u0photo.setBackgroundResource(R.drawable.border);
                         pack.setVisibility(View.INVISIBLE);

                         blind.setVisibility(View.INVISIBLE);
                         show.setVisibility(View.INVISIBLE);
                     }
                 }
                 if(isblind0.equals("1"))
                 {
                     u0s.setText("Blind");
                 }
                 else
                 {
                     u0s.setText("Card Seen");
                 }
                 if(isblind1.equals("1"))
                 {
                     u1s.setText("Blind");
                 }
                 else
                 {
                     u1s.setText("Card Seen");
                 }
                 if( ispack0.equals("1"))
                 {
                     if(user==0)
                         winners.setText("You Loose");
                     else
                         winners.setText("You Won");
                 }
                 if(ispack1.equals("1"))
                 {
                     if(user==1)
                         winners.setText("You Loose");
                     else
                         winners.setText("You Won");
                 }
                 if(isshow.equals("1"))
                 {

                            myC1.setText(p0 + " " + p1 + " " + p2);
                            oppC1.setText(b0 + " " + b1 + " " + b2);

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

                     if(user==0)
                     {
                         myC1.setText(p0+" "+p1+" "+p2);
                         ref.child("issee0").setValue("1");
                         ref.child("isblind0").setValue("0");
                     }
                     else {
                         oppC1.setText(b0 + " " + b1 + " " + b2);
                         ref.child("issee1").setValue("1");

                         ref.child("isblind1").setValue("0");
                     }


    }
});
pack.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(user==0)
        {
            ref.child("ispack0").setValue("1");
        }
        else
        {
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
        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                game();
            }
        }, 1);

    }


}
