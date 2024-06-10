package com.example.tictactoe;

import android.graphics.Color;
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
    public TextView firstSquare;
    public TextView secondSquare;
    public TextView thirdSquare;
    public TextView fourthSquare;
    public TextView fifthSquare;
    public TextView sixthSquare;
    public TextView seventhSquare;
    public TextView eighthSquare;
    public TextView ninthSquare;

    public static TextView winnerField;

    static List<TextView> squareList = new ArrayList<>();
    static Button playAgain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        winnerField = findViewById(R.id.winnerField);
        playAgain = findViewById(R.id.playAgain);

        createSquares();
        createSquareList();
        setOnclickListeners();
    }
    public static void checkWinner(){

        String[][] ticTacToe = putSquaresToMatrix();
        checkVerticalAndHorizontalLines(ticTacToe);
        checkDiagonalLines(ticTacToe);
        checkDraw();
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
    public static void clearBlocks(){
        for(TextView tv : squareList){
            tv.setText("");
        }
    }
    public static void playAgain(){
        playAgain.setVisibility(View.VISIBLE);
        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearBlocks();
                winnerField.setText("");
                player = 1;
                setOnclickListeners();
                playAgain.setVisibility(View.INVISIBLE);
                playAgain.setOnClickListener(null);
            }
        });
    }
    public static void setOnclickListeners(){
        for(TextView tv : squareList){
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(player == 1){
                        tv.setTextColor(Color.RED);
                        tv.setText("x");
                        checkWinner();
                        player = 2;
                        tv.setOnClickListener(null);

                    }else{
                        tv.setTextColor(Color.BLUE);
                        tv.setText("o");
                        checkWinner();
                        player = 1;
                        tv.setOnClickListener(null);

                    }
                }
            });
        }
    }
    public static void declareWinner(){
        winnerField.setText("Winner is player " + player);
    }
    public static String[][] putSquaresToMatrix(){
        String[][] matrix = new String[3][3];
        int skaicius = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                matrix[i][j] = (String) squareList.get(skaicius).getText();
                skaicius++;
            }
        }
        return matrix;
    }
    public static void checkVerticalAndHorizontalLines(String[][] matrix){
        for(int i = 0; i < 3; i++) {
            if(Objects.equals(matrix[i][0], matrix[i][1])
                    && Objects.equals(matrix[i][2], matrix[i][0])
                    && !Objects.equals(matrix[i][0], ""))
            {
                declareWinner();
                stopAction();
                playAgain();
            } else if (Objects.equals(matrix[0][i], matrix[1][i])
                    && Objects.equals(matrix[2][i], matrix[0][i])
                    && !Objects.equals(matrix[0][i], ""))
            {
                declareWinner();
                stopAction();
                playAgain();
            }
        }
    }
    public static void checkDiagonalLines(String[][] matrix){
        if(Objects.equals(matrix[0][0], matrix[1][1])
                && Objects.equals(matrix[2][2], matrix[0][0])
                && !Objects.equals(matrix[0][0], "")){
            declareWinner();
            stopAction();
            playAgain();
        } else if (Objects.equals(matrix[0][2], matrix[1][1])
                && Objects.equals(matrix[2][0], matrix[0][2])
                && !Objects.equals(matrix[0][2], "")) {
            declareWinner();
            stopAction();
            playAgain();
        }
    }
    public static void checkDraw(){
        int draw = 0;
        for(TextView tv : squareList){
            if(!tv.getText().equals("")){
                draw++;
            }
        }
        if(draw == 9){
            winnerField.setText("Draw");
            stopAction();
            playAgain();
        }
    }
}