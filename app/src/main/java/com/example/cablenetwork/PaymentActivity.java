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

public class PaymentActivity extends AppCompatActivity {
    TextView tvLoginHere;
    EditText txtname,txtemail,txtphone,txtamount,txtpaymentmethod,txttransactionid;
    Button btnsave;
    DatabaseReference reff;
    Payment payment;
    long maxid=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_payment);
        txtname=(EditText) findViewById(R.id.txtname);
        txtemail=(EditText) findViewById(R.id.txtemail);
        txtphone=(EditText) findViewById(R.id.txtphone);
        txtamount=(EditText) findViewById(R.id.txtamount);
        txtpaymentmethod=(EditText) findViewById(R.id.txtpaymentmethod);
        txttransactionid=(EditText) findViewById(R.id.txttransactionid);
        btnsave=(Button) findViewById(R.id.btnsave);
        payment = new Payment();
        tvLoginHere = findViewById(R.id.tvLoginHere);

        tvLoginHere.setOnClickListener(view ->{
            startActivity(new Intent(PaymentActivity.this, DashboardActivity.class));
        });

        reff = FirebaseDatabase.getInstance().getReference().child("Payments");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                    maxid=(snapshot.getChildrenCount());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Long phn =Long.parseLong(txtphone.getText().toString().trim());
                Long amt = Long.parseLong(txtamount.getText().toString().trim());
                Long trnid = Long.parseLong(txttransactionid.getText().toString().trim());

                payment.setName(txtname.getText().toString().trim());
                payment.setEmail(txtemail.getText().toString().trim());
                payment.setPmethod(txtpaymentmethod.getText().toString().trim());
                payment.setPh(phn);
                payment.setTranid(trnid);
                payment.setAmount(amt);
                reff.child(String.valueOf(maxid+1)).setValue(payment);



                Toast.makeText(PaymentActivity.this,"Contact you within 24 Hours",Toast.LENGTH_LONG).show();

            }
        });
    }
}