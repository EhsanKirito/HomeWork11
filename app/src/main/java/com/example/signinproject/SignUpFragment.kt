package com.example.signinproject

import android.os.Bundle
import android.text.Editable
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
import com.example.signinproject.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {
    private val myVM: MainViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = DataBindingUtil.bind<FragmentSignUpBinding>(view)
        val info = arguments?.getStringArrayList("INFO")
        binding?.editTextTextPersonName?.setText(info?.get(0) ?: "")
        binding?.editTextNumberPassword?.setText(info?.get(1) ?: "")
        binding?.btnSignUp?.setOnClickListener {
            if (binding.editTextTextPersonName.text.isEmpty()) {
                Toast.makeText(context, "username blank", Toast.LENGTH_SHORT).show()
            } else if (binding.editTextNumberPassword.text.isEmpty()) {
                Toast.makeText(context, "password is blank", Toast.LENGTH_SHORT).show()
            } else {
                myVM.usernames = binding.editTextTextPersonName.text.toString()
                myVM.passwords = (binding.editTextNumberPassword.text.toString())
                if (myVM.usernames.contains(binding.editTextTextPersonName.text.toString())
                    && myVM.passwords.contains(binding.editTextNumberPassword.text.toString())
                ) {
                    Toast.makeText(context, "signup successful", Toast.LENGTH_SHORT).show()
                    parentFragmentManager.commit {
                        setReorderingAllowed(true)
                        replace<SignInFragment>(
                            R.id.boxFragment,
                            args = bundleOf(
                                "REGISTER_INFO" to arrayListOf(
                                    myVM.usernames,
                                    myVM.passwords
                                )
                            )
                        )
                    }
                } else {
                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()

                }
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }


}