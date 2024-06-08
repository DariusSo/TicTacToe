package com.example.tictactoe;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;


import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    public static int player = 1;
    public static TextView firstSquare;
    public static TextView secondSquare;
    public static TextView thirdSquare;
    public static TextView fourthSquare;
    public static TextView fifthSquare;
    public static TextView sixthSquare;
    public static TextView seventhSquare;
    public static TextView eighthSquare;
    public static TextView ninthSquare;

    public static TextView winnerField;

    static List<TextView> squareList = new ArrayList<>();
    static Button playAgain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        winnerField = findViewById(R.id.winnerField);
        playAgain = findViewById(R.id.playAgain);
        playAgain.setVisibility(View.INVISIBLE);

        createSquares();
        createSquareList();
        for(TextView tv : squareList){
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(player == 1){
                        tv.setText("x");
                        checkWinner();
                        player = 2;
                        tv.setOnClickListener(null);

                    }else{
                        tv.setText("o");
                        checkWinner();
                        player = 1;
                        tv.setOnClickListener(null);

                    }
                }
            });
        }

    }
    public static void checkWinner(){
        //Pridedu mygtukus prie matricos
        String[][] matrix = new String[3][3];
        int skaicius = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                matrix[i][j] = (String) squareList.get(skaicius).getText();
                skaicius++;
            }
        }
        for(int i = 0; i < 3; i++) {
            if(Objects.equals(matrix[i][0], matrix[i][1]) && Objects.equals(matrix[i][2], matrix[i][0])
                    && !Objects.equals(matrix[i][0], "")){
                winnerField.setText("Winner is player " + player);
                stopAction();
                playAgain.setVisibility(View.VISIBLE);
            } else if (Objects.equals(matrix[0][i], matrix[1][i])
                    && Objects.equals(matrix[2][i], matrix[0][i]) && !Objects.equals(matrix[0][i], "")) {
                winnerField.setText("Winner is player " + player);
                stopAction();
                playAgain.setVisibility(View.VISIBLE);

            }

        }
        if(Objects.equals(matrix[0][0], matrix[1][1]) && Objects.equals(matrix[2][2], matrix[0][0]) &&
                !Objects.equals(matrix[0][0], "")){
            winnerField.setText("Winner is player " + player);
            stopAction();
            playAgain.setVisibility(View.VISIBLE);
        } else if (Objects.equals(matrix[0][2], matrix[1][1]) && Objects.equals(matrix[2][0], matrix[0][2]) &&
                !Objects.equals(matrix[0][2], "")) {
            winnerField.setText("Winner is player " + player);
            stopAction();
            playAgain.setVisibility(View.VISIBLE);
        }
        int draw = 0;
        for(TextView tv : squareList){
            if(!tv.getText().equals("")){
                draw++;
            }
        }
        if(draw == 9){
            winnerField.setText("Draw");
            playAgain.setVisibility(View.VISIBLE);
        }
    }
    public void createSquares(){
        firstSquare = findViewById(R.id.square1);
        secondSquare = findViewById(R.id.square2);
        thirdSquare = findViewById(R.id.square3);
        fourthSquare = findViewById(R.id.square4);
        fifthSquare = findViewById(R.id.square5);
        sixthSquare = findViewById(R.id.square6);
        seventhSquare = findViewById(R.id.square7);
        eighthSquare = findViewById(R.id.square8);
        ninthSquare = findViewById(R.id.square9);
    }
    public void createSquareList(){
        squareList.add(firstSquare);
        squareList.add(secondSquare);
        squareList.add(thirdSquare);
        squareList.add(fourthSquare);
        squareList.add(fifthSquare);
        squareList.add(sixthSquare);
        squareList.add(seventhSquare);
        squareList.add(eighthSquare);
        squareList.add(ninthSquare);
    }
    public static void stopAction(){
        for(TextView tv : squareList){
            tv.setOnClickListener(null);
        }
    }
    public void clearBlocks(){
        for(TextView tv : squareList){
            tv.setText("");
        }
    }
}