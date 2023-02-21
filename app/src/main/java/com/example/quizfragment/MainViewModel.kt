package com.example.quizfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


object Cheater{
    var cheats = false
}
class MainViewModel:ViewModel() {
    val questionList = arrayListOf(
        "1+1=2",
        "2+2=5",
        "13*13=189",
        "16*16=256"
    )

    val isAnswered = arrayListOf(
        false,false,false,false
    )

    private val answerList = arrayListOf(
        true,
        false,
        false,
        true
    )

    var scoreList = booleanArrayOf(
        false,false,false,false
    )

    val clickList = booleanArrayOf(false, false, false, true, false)

    var cheatStatus = Cheater
    var questionNumber = 0
    var currentAnswer = answerList[0]
    var score = 0
    val lastQuestionNumber = questionList.size
    val currentQuestion = MutableLiveData(questionList[questionNumber])
    fun checkAnswer(answer:Boolean):Boolean{
        return if ( !cheatStatus.cheats && answer == currentAnswer){
            scoreList[questionNumber] = true
            true
        } else false
    }

    fun nextQuestion() {
        questionNumber++
        currentQuestion.value = questionList[questionNumber]
        currentAnswer = answerList[questionNumber]
        cheatStatus.cheats = false
    }

    fun prevQuestion() {
        questionNumber-=1
        currentQuestion.value = questionList[questionNumber]
        currentAnswer = answerList[questionNumber]
        cheatStatus.cheats = false
    }

    fun calcScore(){
        for (i in scoreList){
            if (i) score++
        }
    }
}