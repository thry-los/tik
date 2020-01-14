package com.jan.tik;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;
    // 0-x
    //1-o
    int activeplayer = 0;
    int[] gamestate ={2,2,2,2,2,2,2,2,2};
    // state meanings
//    0-x
//    1-o
//    2-null
    int[][] winpositions={{0,1,2}, {3,4,5},{6,7,8},{0,4,8},{2,4,6},{0,3,6},{1,4,7},{2,5,8}};
    public void playertab(View view){
        ImageView img = (ImageView) view;
        int tappedimage = Integer.parseInt(img.getTag().toString());
        if(!gameActive){
            gameReset(view);
        }
        if(gamestate[tappedimage] == 2) {
            gamestate[tappedimage] = activeplayer;
            img.setTranslationY(-1000f);
            if (activeplayer == 0) {
                img.setImageResource(R.drawable.o);
                activeplayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("O's Turn -Tap to play");
            } else {
                img.setImageResource(R.drawable.c);
                activeplayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn -Tap to play");

            }

            img.animate().translationYBy(1000f).setDuration(300);
        }
        // check if any player has won
        for(int[] winPosition:winpositions){
            if(gamestate[winPosition[0]]== gamestate[winPosition[1]] &&
                    gamestate[winPosition[1]]== gamestate[winPosition[2]]&&
                    gamestate[winPosition[0]]!=2){
                // somebody has won! - find out who!
                String winnerstr;
                gameActive = false;
                if(gamestate[winPosition[0]]==0){
                    winnerstr = "x has won";
                }
                else {
                    winnerstr = "o has won";
                }
                // update the status bar for winner announcement
                TextView status = findViewById(R.id.status);
                status.setText(winnerstr);
            }
        }
    }
    public void gameReset(View view) {
        gameActive = true;
        activeplayer = 0;
        //int[] gamestate = {2, 2, 2, 2, 2, 2, 2, 2, 2};
        for(int i=0;i<gamestate.length;i++){
            gamestate[i]=2;
        }
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView9)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView10)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("X's Turn -Tap to play");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
