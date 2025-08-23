package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView initialText;
    TextView timer;
    TextView score;
    TextView question;
    GridLayout options;
    Button opt1;
    Button opt2;
    Button opt3;
    Button opt4;
    Button playAgain;
    CountDownTimer countTimer;

    boolean vis=true;
    Random x;
    int num1,num2,ans,choose;
    int asked=0,correct=0;


    public void playAgain(View view){
        opt1.setEnabled(true);
        opt2.setEnabled(true);
        opt3.setEnabled(true);
        opt4.setEnabled(true);
        playAgain.setVisibility(View.INVISIBLE);
        correct=0;
        asked=0;
        startTimer();
        question();
    }
    public void option1(View view){
        if(choose==1){
            correct++;
            score.setText(Integer.toString(correct)+'/'+Integer.toString(asked));
        }
        question();
    }
    public void option2(View view){
        if(choose==2){
            correct++;
            score.setText(Integer.toString(correct)+'/'+Integer.toString(asked));
        }
        question();
    }
    public void option3(View view){
        if(choose==3){
            correct++;
            score.setText(Integer.toString(correct)+'/'+Integer.toString(asked));
        }
        question();
    }
    public void option4(View view){
        if(choose==4){
            correct++;
            score.setText(Integer.toString(correct)+'/'+Integer.toString(asked));
        }
        question();
    }

    public void options(){
        //x=new Random();
        choose=x.nextInt(3-0)+1;
        if(choose==1){
            opt1.setText(String.valueOf(ans));
            opt2.setText(String.valueOf(x.nextInt(99-1)));
            opt3.setText(String.valueOf(x.nextInt(99-1)));
            opt4.setText(String.valueOf(x.nextInt(99-1)));
        }
        else if(choose==2){
            opt2.setText(String.valueOf(ans));
            opt1.setText(String.valueOf(x.nextInt(99-1)));
            opt3.setText(String.valueOf(x.nextInt(99-1)));
            opt4.setText(String.valueOf(x.nextInt(99-1)));
        }
        else if(choose==3){
            opt3.setText(String.valueOf(ans));
            opt1.setText(String.valueOf(x.nextInt(99-1)));
            opt2.setText(String.valueOf(x.nextInt(99-1)));
            opt4.setText(String.valueOf(x.nextInt(99-1)));
        }
        else if(choose==4){
            opt4.setText(String.valueOf(ans));
            opt1.setText(String.valueOf(x.nextInt(99-1)));
            opt3.setText(String.valueOf(x.nextInt(99-1)));
            opt2.setText(String.valueOf(x.nextInt(99-1)));
        }

    }
    public void question(){

        num1= x.nextInt(49-1)+1;
        num2= x.nextInt(49-1)+1;
        question.setText(Integer.toString(num1)+" + "+Integer.toString(num2));
        ans=num1+num2;
        asked++;
        score.setText(Integer.toString(correct)+'/'+Integer.toString(asked));
        options();
    }
    public void startTimer(){

        countTimer = new CountDownTimer(31000,1000) {
            @Override
            public void onTick(long l) {
                timer.setText(String.valueOf(l/1000)+"s");
            }

            @Override
            public void onFinish() {
                opt1.setEnabled(false);
                opt2.setEnabled(false);
                opt3.setEnabled(false);
                opt4.setEnabled(false);
                playAgain.setVisibility(View.VISIBLE);
            }
        }.start();
    }
    public void initialText(View view){
        initialText.setVisibility(View.GONE);
        invertVisibility();
        startTimer();
        question();
    }
    public void invertVisibility(){
        if(vis){
            timer.setVisibility(View.GONE);
            score.setVisibility(View.GONE);
            question.setVisibility(View.GONE);
            options.setVisibility(View.GONE);
            vis=false;
        }
        else {
            timer.setVisibility(View.VISIBLE);
            score.setVisibility(View.VISIBLE);
            question.setVisibility(View.VISIBLE);
            options.setVisibility(View.VISIBLE);
            vis=true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialText= (TextView)findViewById(R.id.initialTextView);
        timer=(TextView)findViewById(R.id.timer);
        score=(TextView)findViewById(R.id.score);
        question=(TextView)findViewById(R.id.question);
        options=(GridLayout) findViewById(R.id.gridView);
        opt1=findViewById(R.id.option1);
        opt2=findViewById(R.id.option2);
        opt3=findViewById(R.id.option3);
        opt4=findViewById(R.id.option4);
        playAgain=findViewById(R.id.playAgain);
        invertVisibility();
        x=new Random();

    }
}
