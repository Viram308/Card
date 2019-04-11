package android.com.viram.cards;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuth.AuthStateListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static android.com.viram.cards.R.*;

public class login extends AppCompatActivity {
    String avail;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    private FirebaseAuth mFirebaseAuth;
    FirebaseDatabase database;
    private final int RC_SIGN_IN = 1;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    Button signout, cr, jr, ok, cancel;
    RelativeLayout edit, mainlogin;
    String username = "", c, ete;
    int randnum;
    String ss;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(layout.activity_login);
        signout = findViewById(id.signout);
        cr = findViewById(R.id.cr);
        jr = findViewById(R.id.jr);
        ok = findViewById(R.id.ok);

        edit = findViewById(id.edit);
        et = findViewById(R.id.et);
        mainlogin = findViewById(id.mainlogin);
        database = FirebaseDatabase.getInstance();

        mFirebaseAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d("Status", "Okkkkk");
                    username = user.getDisplayName();
                } else {
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder().setIsSmartLockEnabled(false)
                                    .setAvailableProviders(Arrays.asList(
                                            new AuthUI.IdpConfig.GoogleBuilder().build(),
                                            new AuthUI.IdpConfig.EmailBuilder().build()))
                                    .build(),
                            RC_SIGN_IN);

                }
            }
        };

        signout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthUI.getInstance().signOut(getApplicationContext());

                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                mDatabase.child("Code").child(username).removeValue();

            }
        });
        cr.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                randnum = ThreadLocalRandom.current().nextInt(1000, 9999 + 1);
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                Toast.makeText(getApplicationContext(),"Code is "+randnum,Toast.LENGTH_SHORT).show();


                mDatabase.child("Game").child("u0").child("u0").setValue(username);
                mDatabase.child("Game").child("u0").child("u1").setValue("Wait");
                mDatabase.child("Game").child("u0").child("Code").setValue(randnum);
                mDatabase.child("Game").child("u0").child("turn").setValue("0");
                mDatabase.child("Game").child("u0").child("isblind0").setValue("1");
                mDatabase.child("Game").child("u0").child("isblind1").setValue("1");
                mDatabase.child("Game").child("u0").child("issee0").setValue("0");
                mDatabase.child("Game").child("u0").child("issee1").setValue("0");
                mDatabase.child("Game").child("u0").child("ispack0").setValue("0");
                mDatabase.child("Game").child("u0").child("ispack1").setValue("0");
                mDatabase.child("Game").child("u0").child("isshow").setValue("0");
                mDatabase.child("Game").child("u0").child("p0").setValue("rk");
                mDatabase.child("Game").child("u0").child("p1").setValue("Rk");
                mDatabase.child("Game").child("u0").child("p2").setValue("bk");
                mDatabase.child("Game").child("u0").child("b0").setValue("B1");
                mDatabase.child("Game").child("u0").child("b1").setValue("R1");
                mDatabase.child("Game").child("u0").child("b2").setValue("r1");
                mDatabase.child("Game").child("u0").child("winner").setValue("0");
                Intent i = new Intent(getApplicationContext(), MainActivity.class);


//Create the bundle
                Bundle bundle = new Bundle();

//Add your data to bundle
                bundle.putString("cr", "0");

//Add the bundle to the intent
                i.putExtras(bundle);

//Fire that second activity
                startActivity(i);
                finish();

            }
        });
        jr.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                edit.setVisibility(View.VISIBLE);
                cr.getBackground().setAlpha(100);
                jr.getBackground().setAlpha(100);
                signout.getBackground().setAlpha(100);
                mainlogin.getBackground().setAlpha(100);
                jr.setEnabled(false);
                cr.setEnabled(false);
                signout.setEnabled(false);
                ok.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ete = et.getText().toString();
                        Toast.makeText(getApplicationContext(),""+ss,Toast.LENGTH_SHORT).show();



                            final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Game").child("u0");

                            ValueEventListener V = new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    avail = dataSnapshot.child("u1").getValue().toString();
                                    ss=dataSnapshot.child("Code").getValue().toString();

                                    if(ete.equals(ss)) {
                                        if (avail.equals("Wait")) {
                                            mDatabase.child("u1").setValue(username);
                                            Intent i = new Intent(getApplicationContext(), MainActivity.class);


//Create the bundle
                                            Bundle bundle = new Bundle();

//Add your data to bundle
                                            bundle.putString("cr", "1");

//Add the bundle to the intent
                                            i.putExtras(bundle);

//Fire that second activity
                                            startActivity(i);
                                            finish();

                                        }
                                    }
                                    else
                                    {
                                        edit.setVisibility(View.INVISIBLE);
                                        jr.setEnabled(true);
                                        cr.setEnabled(true);
                                        signout.setEnabled(true);
                                        cr.getBackground().setAlpha(255);
                                        jr.getBackground().setAlpha(255);
                                        signout.getBackground().setAlpha(255);
                                        mainlogin.getBackground().setAlpha(255);
                                        Toast.makeText(getApplicationContext(),"Sorry!! This Code Doesen't Cantains any room",Toast.LENGTH_SHORT).show();
                                    }
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            };
                            mDatabase.addValueEventListener(V);
                            mDatabase.child("turn").setValue("5");
                            mDatabase.child("turn").setValue("0");

                        }





                });
        }


        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == RC_SIGN_IN && resultCode == RESULT_OK) {
                Toast.makeText(getApplicationContext(), "Signed In", Toast.LENGTH_SHORT).show();
            } else if (requestCode == RC_SIGN_IN && resultCode == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "Signed out", Toast.LENGTH_SHORT).show();
                finish();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Signed OUT", Toast.LENGTH_SHORT).show();
            finish();

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.exit(0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
    }
}
