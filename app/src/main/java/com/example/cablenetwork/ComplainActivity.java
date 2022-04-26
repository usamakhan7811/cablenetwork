package com.example.cablenetwork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.lang.reflect.Member;

public class ComplainActivity extends AppCompatActivity {
    TextView tvLoginHere;
    EditText txtname, txtemail, txtphone, txtcomplain;
    Button btnsave;
    DatabaseReference reff;
    Complain complain;
    long maxid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_complain);
        tvLoginHere = findViewById(R.id.tvLoginHere);
        txtname = (EditText) findViewById(R.id.txtname);
        txtemail = (EditText) findViewById(R.id.txtemail);
        txtphone = (EditText) findViewById(R.id.txtphone);
        txtcomplain = (EditText) findViewById(R.id.txtcomplain);
        btnsave = (Button) findViewById(R.id.btnsave);
        complain = new Complain();
        tvLoginHere.setOnClickListener(view -> {
            startActivity(new Intent(ComplainActivity.this, DashboardActivity.class));
        });
        reff = FirebaseDatabase.getInstance().getReference().child("Complain");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                    maxid = (snapshot.getChildrenCount());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Long phn = Long.parseLong(txtphone.getText().toString().trim());
                complain.setName(txtname.getText().toString().trim());
                complain.setEmail(txtemail.getText().toString().trim());
                complain.setComplain(txtcomplain.getText().toString().trim());
                complain.setPh(phn);
                reff.child(String.valueOf(maxid + 1)).setValue(complain);

                Toast.makeText(ComplainActivity.this, "Complain Submit,Contact you within 24 Hours", Toast.LENGTH_LONG).show();

            }
        });
    }
}