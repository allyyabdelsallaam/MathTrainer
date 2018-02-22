package com.example.lenovo.mathtrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startbutton;
    TextView resulttextview;
    TextView pointstextview;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView sumtextview;
    TextView timertextview;
    Button playagainbutton;
    boolean gameisactive = true;
    RelativeLayout gamerelativelayout;

    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationofcorrectanswer;
    int score = 0;
    int numberofquestions = 0;


    public void playagain(final View view)
    {
        score = 0;
        numberofquestions = 0;
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);

        timertextview.setText("30s");
        pointstextview.setText("0/0");
        resulttextview.setText("");
        playagainbutton.setVisibility(view.INVISIBLE);

        gameisactive = true;

        generatequestion();


        new CountDownTimer(30100 , 1000)
        {

            @Override
            public void onTick(long millisUntilFinished) {
                timertextview.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {

                playagainbutton.setVisibility(view.VISIBLE);
                timertextview.setText("0s");
                resulttextview.setText("Your Score" + Integer.toString(score) + "/" + Integer.toString(numberofquestions));

                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);

            }
        }.start();
    }

    public void generatequestion()
    {
        Random rand = new Random();
         int a = rand.nextInt(25);
         int b = rand.nextInt(25);

        sumtextview.setText(Integer.toString(a) + " + " + Integer.toString(b));
        locationofcorrectanswer = rand.nextInt(4);
        answers.clear();
        int incorrectanswer;

        for(int i = 0; i <4; i++)
        {
            if (i == locationofcorrectanswer && gameisactive)
            {
                answers.add(a + b);
            }
            else
            {
                incorrectanswer = rand.nextInt(41);
                while(incorrectanswer == a + b)
                {
                    incorrectanswer = rand.nextInt(41);
                }

                answers.add(incorrectanswer);
            }
        }


        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));



    }

    public void chooseanswer(View view)
    {
        if(view.getTag().toString().equals(Integer.toString(locationofcorrectanswer)))
        {
            score++;
            resulttextview.setText("Correct");
        }
        else
        {
            resulttextview.setText("Wrong");
        }

        numberofquestions++;
        pointstextview.setText(Integer.toString(score) + "/" + Integer.toString(numberofquestions));
        generatequestion();
    }

    public void start(View view)
    {
        startbutton.setVisibility(view.INVISIBLE);
        gamerelativelayout.setVisibility(view.VISIBLE);

        playagain(findViewById(R.id.playagainbutton));
    }







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startbutton = (Button) findViewById(R.id.startbutton);
        sumtextview = (TextView) findViewById(R.id.sumtextView);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        resulttextview = (TextView) findViewById(R.id.resulttextView);
        pointstextview = (TextView) findViewById(R.id.pointstextView);
        timertextview = (TextView) findViewById(R.id.timertextView);
        playagainbutton = (Button) findViewById(R.id.playagainbutton);
        gamerelativelayout = (RelativeLayout) findViewById(R.id.gamerelativelayout);






        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
