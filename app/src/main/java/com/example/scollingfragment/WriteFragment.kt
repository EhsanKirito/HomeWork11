package com.example.scollingfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.viewModels
import com.example.scollingfragment.databinding.FragmentWriteBinding

class WriteFragment : Fragment(R.layout.fragment_write) {
    val myVM:MainViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = DataBindingUtil.bind<FragmentWriteBinding>(view)

        var passedInfo = arguments?.getStringArrayList("INFO")
        if (passedInfo != null) {
            for (i in 0 until passedInfo.size){
                if (passedInfo[i]== null) passedInfo[i]=""
            }
        } else {
            passedInfo = arrayListOf("", "", "", "", "", "", "", "", "", "", "")
        }
        myVM.fullName.value = passedInfo[0]
        myVM.username.value = passedInfo[1]
        myVM.email.value = passedInfo[2]
        myVM.password.value = passedInfo[3]
        myVM.gender.value = passedInfo[5]
        binding?.fullName?.setText(passedInfo[6])
        binding?.username?.setText(passedInfo[7])
        binding?.email?.setText(passedInfo[8])
        binding?.password?.setText(passedInfo[9])
        binding?.rePassword?.setText(passedInfo[4])
        if (passedInfo[5] == "Male"){
            binding?.male?.isChecked  = true
        }else if (myVM.gender.value == "Female"){
            binding?.female?.isChecked = true
        }

        binding?.female?.setOnClickListener {
            binding.male?.isChecked = false
            myVM.gender.value = "Female"
        }

        binding?.male?.setOnClickListener {
            binding.female?.isChecked = false
            myVM.gender.value = "Male"
        }

        binding?.btnRegister?.setOnClickListener {
            if (binding.password?.text.toString() == binding?.rePassword?.text.toString()){
                myVM.fullName.value = binding.fullName.text.toString()
                myVM.username.value = binding.username.text.toString()
                myVM.email.value = binding.email.text.toString()
                myVM.password.value = binding.password.text.toString()
                Toast.makeText(context, "Registery is completed", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Given Passwords does not match. please consider it.", Toast.LENGTH_SHORT).show()
            }
        }

        binding?.btnShowInfo?.setOnClickListener {

            val info = bundleOf("INFO" to arrayListOf<String>(
                myVM.fullName.value.toString(), myVM.username.value.toString(),
                myVM.email.value.toString(), myVM.password.value.toString(),
                binding.rePassword.text.toString(), myVM.gender.value.toString(),
                binding.fullName.text.toString(), binding.username.text.toString(),
                binding.email.text.toString(), binding.password.text.toString()))

            parentFragmentManager.commit {
                setReorderingAllowed(true)
                addToBackStack(null)
                replace<ScrollingFragment>(R.id.fragmentHolder, args = info)
            }
        }
    }
}