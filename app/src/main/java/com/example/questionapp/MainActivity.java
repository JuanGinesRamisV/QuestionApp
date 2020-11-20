package com.example.questionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import Data.Question;

public class MainActivity extends AppCompatActivity {
    //buttons
    Button previousButton;
    Button nextButton;

    private List<Question> questions;
    private Question currentQuestion;
    private int indexCurrentQuestion;

    //<editor-fold desc="Getters and setters">
    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(Question currentQuestion) {
        this.currentQuestion = currentQuestion;
        TextView textView = findViewById(R.id.textView);
        textView.setText(getCurrentQuestion().getQuestion());
    }

    public Button getPreviousButton() {
        return previousButton;
    }

    public void setPreviousButton(Button previousButton) {
        this.previousButton = previousButton;
    }

    public int getIndexCurrentQuestion() {
        return indexCurrentQuestion;
    }

    public void setIndexCurrentQuestion(int indexCurrentQuestion) {
        this.indexCurrentQuestion = indexCurrentQuestion;
    }

    public Button getNextButton() {
        return nextButton;
    }

    public void setNextButton(Button nextButton) {
        this.nextButton = nextButton;
    }

    //</editor-fold>


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setQuestions(generarPreguntas());
        setIndexCurrentQuestion(0);
        setCurrentQuestion(getQuestions().get(getIndexCurrentQuestion()));
        añadirFuncionesBotones();
    }

    private List<Question> generarPreguntas(){
        ArrayList<Question> preguntas = new ArrayList<Question>();

        Question question1 = new Question("Washington, D.C. is the capital of the United States of America.",true);
        Question question2 = new Question("Chocolate is bad for dogs",true);
        Question question3 = new Question("Japan has left-hand traffic.",true);
        Question question4 = new Question("Prince Harry is taller than Prince William",false);
        Question question5 = new Question("M&M stands for Mars and Moordale",false);
        Question question6 = new Question("There are two parts of the body that can't heal themselves",false);
        preguntas.add(question1);
        preguntas.add(question2);
        preguntas.add(question3);
        preguntas.add(question4);
        preguntas.add(question5);
        preguntas.add(question6);
        return preguntas;
    }

    private void showCorrection(boolean correct){
        if (correct==true){
            Toast.makeText(getApplicationContext(),"Correct",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),"Incorrect",Toast.LENGTH_SHORT).show();
        }
    }

    private void changeToNextQuestion(){
        if(getIndexCurrentQuestion()+1<getQuestions().size()) {
            setIndexCurrentQuestion(getIndexCurrentQuestion() + 1);
            setCurrentQuestion(getQuestions().get(indexCurrentQuestion));
            if (getIndexCurrentQuestion()==getQuestions().size()-1) {
                setButtonClickable(false, getNextButton());
            }
            if(getIndexCurrentQuestion()!=0){
                setButtonClickable(true,getPreviousButton());
            }
        }
    }

    private void changeToPreviousQuestion(){
        if(getIndexCurrentQuestion()-1>=0) {
            setIndexCurrentQuestion(getIndexCurrentQuestion() - 1);
            setCurrentQuestion(getQuestions().get(indexCurrentQuestion));
            if (getIndexCurrentQuestion()==0){
                setButtonClickable(false,getPreviousButton());
            }
            if(getIndexCurrentQuestion()!=getQuestions().size()-1){
                setButtonClickable(true,getNextButton());
            }
        }
    }

    private void setButtonClickable(boolean clickable, Button aux){
        if (clickable){
            aux.setBackgroundColor(getResources().getColor(R.color.purple_700));
            aux.setClickable(true);
        }else{
            aux.setClickable(false);
            aux.setBackgroundColor(getResources().getColor(R.color.black));
        }
    }

    private void añadirFuncionesBotones(){
        Button aux =(Button) findViewById(R.id.button);
        aux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean aux = Question.comprobarRespuesta(getCurrentQuestion(),false);
                showCorrection(aux);
            }
        });

        aux=(Button) findViewById(R.id.trueButton);
        aux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean aux = Question.comprobarRespuesta(getCurrentQuestion(),true);
                showCorrection(aux);
            }
        });

        previousButton= findViewById(R.id.previousButton);
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeToPreviousQuestion();
            }
        });

        setNextButton(findViewById(R.id.nextButton));
        getNextButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeToNextQuestion();
            }
        });
    }

}