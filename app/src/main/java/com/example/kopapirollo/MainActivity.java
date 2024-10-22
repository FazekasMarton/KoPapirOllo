package com.example.kopapirollo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView player;
    private ImageView computer;
    private TextView humanCounter;
    private TextView computerCounter;
    private Button rock;
    private Button paper;
    private Button scissors;
    private int humanWins;
    private int computerWins;
    private Random random;
    private int computerPick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play(0);
            }
        });
        paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play(1);
            }
        });
        scissors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play(2);
            }
        });
    }

    public void play(int pick){
        computerPick = random.nextInt(3);
        player.setImageResource(setImage(pick));
        computer.setImageResource(setImage(computerPick));
        compare(pick, computerPick);
        checkWin();
    }

    public void checkWin(){
        if(humanWins == 3 || computerWins == 3){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            if(humanWins > computerWins){
                builder.setTitle("Győzelem");
            } else {
                builder.setTitle("Vereség");
            }
            builder.setMessage("Szeretne új játékot játszani?");
            builder.setPositiveButton("Igen", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    reset();
                }
            });
            builder.setNegativeButton("Nem", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.show();
        }
    }

    public void reset(){
        init();
        computerCounter.setText(String.valueOf(computerWins));
        humanCounter.setText(String.valueOf(humanWins));
        player.setImageResource(setImage(0));
        computer.setImageResource(setImage(0));
    }

    public void compare(int hpick, int cPick){
        if(hpick == cPick - 1 || hpick - 2 == cPick){
            computerWins++;
            computerCounter.setText(String.valueOf(computerWins));
        } else if (hpick - 1 == cPick || hpick == cPick - 2) {
            humanWins++;
            humanCounter.setText(String.valueOf(humanWins));
        }
    }

    public int setImage(int pick){
        int path = 0;
        switch (pick){
            case 0:
                path = R.drawable.rock;
                break;
            case 1:
                path = R.drawable.paper;
                break;
            case 2:
                path = R.drawable.scissors;
        }
        return path;
    }

    public void init(){
        player = findViewById(R.id.player);
        computer = findViewById(R.id.computer);
        humanCounter = findViewById(R.id.humanCounter);
        computerCounter = findViewById(R.id.computerCounter);
        rock = findViewById(R.id.rock);
        paper = findViewById(R.id.paper);
        scissors = findViewById(R.id.scissors);
        humanWins = 0;
        computerWins = 0;
        random = new Random();
    }
}