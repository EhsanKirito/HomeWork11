package com.example.quizfragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.*
import com.example.scollingfragment.R
import com.example.scollingfragment.databinding.FragmentQuizBinding

class QuizFragment : Fragment(R.layout.fragment_quiz) {
    val quiz: MainViewModel by viewModels()
    lateinit var binding:FragmentQuizBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!
        binding.lifecycleOwner = this
        binding.question = quiz
//        quiz.cheatStatus = arguments?.getBoolean("CHEAT") ?: false

        fun answerLock() {
            binding.btnTrue.isEnabled  = false
            quiz.clickList[0] = false
            binding.btnFalse.isEnabled = false
            quiz.clickList[1] = false
        }
        fun answerUnlock() {
            binding.btnTrue.isEnabled = true
            quiz.clickList[0] = true
            binding.btnFalse.isEnabled = true
            quiz.clickList[1] = true
        }
        fun scoreShowFalse() {
            quiz.calcScore()
            Toast.makeText(context, "It is Incorrect and Your score is ${quiz.score}", Toast.LENGTH_LONG).show()
            quiz.score = 0
        }
        fun scoreShowTrue() {
            quiz.calcScore()
            Toast.makeText(context, "It is Correct and Your score is ${quiz.score}", Toast.LENGTH_LONG).show()
            quiz.score = 0
        }
        fun isAnswered(questionNumber:Int){
            quiz.isAnswered[questionNumber] = true
        }
        fun lockIfAnswered(questionNumber: Int){
            if (quiz.isAnswered[questionNumber] && quiz.questionNumber>=1) {
                binding.btnTrue.isEnabled = false
                binding.btnFalse.isEnabled = false
                binding.btnCheat.isEnabled = false
            }
        }

        binding.btnNext.setOnClickListener {
            quiz.nextQuestion()
            answerUnlock()
            if (quiz.questionNumber > 0) {
                binding.btnPrev.isEnabled = true
                quiz.clickList[4] = true
            }
            binding.btnCheat.isEnabled = true
            quiz.clickList[2] = true
            if (quiz.questionNumber == quiz.lastQuestionNumber-1) {
                binding.btnNext.isEnabled = false
                quiz.clickList[3] = false
            }
            lockIfAnswered(quiz.questionNumber)
        }

        binding.btnPrev.setOnClickListener {
            binding.btnCheat.isEnabled = true
            quiz.clickList[2] = true
            quiz.prevQuestion()
            answerUnlock()
            binding.btnNext.isEnabled = true
            quiz.clickList[3] = true
            if (quiz.questionNumber == 0) {
                binding.btnPrev.isEnabled = false
                quiz.clickList[4] = false
            }
            lockIfAnswered(quiz.questionNumber)
        }

        binding.btnTrue.setOnClickListener {
            binding.btnCheat.isEnabled = false
            quiz.clickList[2] = false
            if (quiz.checkAnswer(true)) {
                scoreShowTrue()
            } else if (quiz.cheatStatus.cheats){
                Toast.makeText(context, "Cheating is wrong", Toast.LENGTH_LONG).show()
            } else scoreShowFalse()
            answerLock()
            isAnswered(quiz.questionNumber)
        }

        binding.btnFalse.setOnClickListener {
            binding.btnCheat.isEnabled = false
            quiz.clickList[2] = false
            if (quiz.checkAnswer(false)) {
                scoreShowTrue()
            }else if (quiz.cheatStatus.cheats){
                Toast.makeText(context, "Cheating is wrong", Toast.LENGTH_LONG).show()
            } else scoreShowFalse()
            answerLock()
            isAnswered(quiz.questionNumber)
        }

        binding.btnCheat.setOnClickListener {
            binding.btnCheat.isEnabled = false
            quiz.clickList[2] = false
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace<CheatFragment>(R.id.fragmentHolder,
                    args = bundleOf("ANSWER" to quiz.currentAnswer))
                addToBackStack(null)
            }
        }

    }




}