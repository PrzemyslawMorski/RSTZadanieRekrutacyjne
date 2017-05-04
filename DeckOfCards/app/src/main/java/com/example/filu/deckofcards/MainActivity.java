package com.example.filu.deckofcards;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.losujBtn)
    Button losujBtn;

    @BindView(R.id.ileTaliiBar)
    SeekBar ileTaliiBar;

    @BindView(R.id.ileTaliiText)
    TextView ileTaliiText;

    @OnClick(R.id.losujBtn)
    void onLosujClicked(View view) {
        Intent losujIntent = new Intent(this, DeckViewActivity.class);
        losujIntent.putExtra("ileTalii", ileTaliiBar.getProgress() + 1);
        startActivity(losujIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ileTaliiBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ileTaliiText.setText(Integer.toString(progress+1));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
    }
}
