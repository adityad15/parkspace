package com.example.app4.admin_files;

import android.annotation.SuppressLint;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.app4.R;

import java.io.IOException;
import java.util.List;

public class AdminRegistration extends AppCompatActivity {
    private EditText editText;
    private TextView textView;
    private Button button;

    double lat, longi;

    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_registration);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editText = findViewById(R.id.adminAddr);
        textView = findViewById(R.id.tv);
        button = findViewById(R.id.submitBtn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Geocoder geocoder = new Geocoder(AdminRegistration.this);
                List<Address> addressList;
                try {
                    addressList = geocoder.getFromLocationName(editText.getText().toString(), 1);
                    if (addressList != null){
                        lat = addressList.get(0).getLatitude();
                         longi = addressList.get(0).getLongitude();
                        textView.setText("lat: "+lat+"long: "+longi);
                    }

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });
    }
}