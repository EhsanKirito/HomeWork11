package com.example.quizfragment

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.viewModels
import com.example.scollingfragment.R
import com.example.scollingfragment.databinding.FragmentCheatBinding

class CheatFragment : Fragment(R.layout.fragment_cheat) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = DataBindingUtil.bind<FragmentCheatBinding>(view)
        val answer = arguments?.getBoolean("ANSWER", false)
        var cheatStatus = Cheater
        binding?.btnShow?.setOnClickListener {
            binding.txtCheat.text = answer.toString()
            cheatStatus.cheats = true
        }
//        binding?.btnBack?.setOnClickListener {
//            parentFragmentManager.commit {
//                setReorderingAllowed(true)
//                replace<QuizFragment>(R.id.fragmentHolder,
//                    args = bundleOf("CHEAT" to cheatStatus)
//                )
//
//            }
//        }
    }

}