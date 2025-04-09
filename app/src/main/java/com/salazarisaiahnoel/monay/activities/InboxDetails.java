package com.salazarisaiahnoel.monay.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.salazarisaiahnoel.monay.R;

public class InboxDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox_details);

        ImageView backarrow = findViewById(R.id.back_arrow);
        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        TextView title = findViewById(R.id.inbox_details_title);
        TextView description = findViewById(R.id.inbox_details_description);
        TextView date = findViewById(R.id.inbox_details_date);

        title.setText(getIntent().getStringExtra("inbox_title"));
        description.setText(getIntent().getStringExtra("inbox_description"));
        date.setText(getIntent().getStringExtra("inbox_date"));
    }
}