package com.example.spareparts;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    TextView tp;
    EditText idtxt, nametxt, prictxt, quntitytxt;
    Button btinst, btupd, btdel, btviw, btclear, bthome;
    DBhelperSp DB;
    int a,qun,priccc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         tp = findViewById(R.id.txtTotalPrice);
        idtxt = (EditText) findViewById(R.id.sid);
        nametxt = findViewById(R.id.Prodname);
        prictxt = findViewById(R.id.prodPrice);
        quntitytxt = findViewById(R.id.prodQunt);
        btinst = findViewById(R.id.bbInsert);
        btupd = findViewById(R.id.bbUpdate);
        btdel = findViewById(R.id.bbDelete);
        btviw = findViewById(R.id.bbView);
        btclear = findViewById(R.id.bbClear);
        bthome = findViewById(R.id.bbHome);
        DB = new DBhelperSp(this);


        btinst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = nametxt.getText().toString();
                String pricTXT = prictxt.getText().toString();
                String quntityTXT = quntitytxt.getText().toString();
                Boolean checkinsertdata = DB.prodinsart(nameTXT, pricTXT,quntityTXT);
                if(checkinsertdata==true)
                    Toast.makeText(MainActivity.this, "New product Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Product Not Inserted", Toast.LENGTH_SHORT).show();
            }        });
        btupd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String IdTXT = idtxt.getText().toString();
                String nameTXT = nametxt.getText().toString();
                String pricTXT = prictxt.getText().toString();
                String quntityTXT = quntitytxt.getText().toString();
                Boolean updatedata = DB.updateprod(IdTXT,nameTXT, pricTXT,quntityTXT);
                qun =  Integer.parseInt(quntityTXT);
                priccc =  Integer.parseInt(pricTXT);
                if(updatedata==true)
                    Toast.makeText(MainActivity.this, "Product Updated and the total price : " +qun*priccc , Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, " Product Not Updated", Toast.LENGTH_SHORT).show();
            }        });

        btdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Boolean checkudeletedata = DB.deleteprod(idtxt.getText().toString());
                if(checkudeletedata==true)
                    Toast.makeText(MainActivity.this, "product Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "product Not Deleted", Toast.LENGTH_SHORT).show();
            }        });

        btviw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor res = DB.showproducts();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this, "No product Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("id :"+res.getString(0)+"\n");
                    buffer.append("product name :"+res.getString(1)+"\n");
                    buffer.append("Price :"+res.getString(2)+"\n\n");
                    buffer.append("Quntity :"+res.getString(3)+"\n\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Product entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }        });

        btclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idtxt.setText("");
                nametxt.setText("");
                quntitytxt.setText("");
                prictxt.setText("");

            }
        });
        bthome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inn = new Intent(MainActivity.this,MainActivity2Home.class);
                startActivity(inn);
            }
        });
    }
}