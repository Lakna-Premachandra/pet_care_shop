package com.example.pet_care_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CustomerActivity2 extends AppCompatActivity {
    EditText EditTextCustomerName,EditTextCustomerAddress,EditTextCustomerPhone,EditTextCustomerNIC,
    EditTextProperty,EditTextBeRooms,EditTextBaRooms,EditTextTimes;
    Button ButtonPostAd;

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer2);

        dbHelper=new DBHelper(this);
        dbHelper.OpenDB();

        EditTextCustomerName=(EditText) findViewById(R.id.txt_CstmrName);
        EditTextCustomerAddress=(EditText) findViewById(R.id.txt_CstmrAddress);
        EditTextCustomerPhone=(EditText) findViewById(R.id.txt_CstmrPhone);
        EditTextCustomerNIC=(EditText) findViewById(R.id.txt_CstmrNIC);
        EditTextProperty=(EditText)findViewById(R.id.txt_PropType);
        EditTextBeRooms=(EditText)findViewById(R.id.txt_BeRooms );
        EditTextBaRooms=(EditText)findViewById(R.id.txt_BaRooms);
        EditTextTimes=(EditText)findViewById(R.id.txt_times);


        ButtonPostAd=(Button) findViewById(R.id.btn_Post);


        ButtonPostAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (EditTextCustomerName.getText().toString().isEmpty() ||
                        EditTextCustomerAddress.getText().toString().isEmpty()||
                        EditTextCustomerPhone.getText().toString().isEmpty()||
                        EditTextCustomerNIC.getText().toString().isEmpty()||
                        EditTextProperty.getText().toString().isEmpty()||
                        EditTextBeRooms.getText().toString().isEmpty()||
                        EditTextBaRooms.getText().toString().isEmpty()||
                        EditTextTimes.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Fields can't be empty",Toast.LENGTH_LONG).show();
                }
                else {
                    CustomerAdInfo customerAdInfo=new CustomerAdInfo(EditTextCustomerName.getText().toString(),
                            EditTextCustomerAddress.getText().toString(),
                            EditTextCustomerPhone.getText().toString(),
                            EditTextCustomerNIC.getText().toString(),
                            EditTextProperty.getText().toString(),
                            EditTextBeRooms.getText().toString(),
                            EditTextBaRooms.getText().toString(),
                            EditTextTimes.getText().toString());
                    if (dbHelper.SaveCustomerAdInfo(customerAdInfo))
                    {
                        Toast.makeText(getApplicationContext(),"Ad Posted Successfully",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Failed to Post Ad",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }
}