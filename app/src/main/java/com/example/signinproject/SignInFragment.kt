package com.example.signinproject

import android.os.Bundle
import android.provider.ContactsContract.Contacts.Data
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.*
import com.example.signinproject.databinding.FragmentSignInBinding

class SignInFragment : Fragment(R.layout.fragment_sign_in) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = DataBindingUtil.bind<FragmentSignInBinding>(view)
        val userPass = arguments?.getStringArrayList("REGISTER_INFO")
        binding?.editTextTextPersonName?.setText(userPass?.get(0) ?: "")
        binding?.editTextNumberPassword?.setText(userPass?.get(1) ?: "")
        binding?.btnSignIn?.setOnClickListener {
            if (userPass != null) {
                if (userPass[1] == binding?.editTextNumberPassword?.text.toString() &&
                    userPass[0] == binding?.editTextTextPersonName?.text.toString()
                ) {
                    Toast.makeText(context, "sign in successful", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Wrong info passed. try again.", Toast.LENGTH_SHORT).show()
            }
        }
        binding?.btnSignUp?.setOnClickListener {
            parentFragmentManager.commit {
                val b = bundleOf(
                    "INFO" to arrayListOf
                        (
                        binding.editTextTextPersonName.text.toString(),
                        binding.editTextNumberPassword.text.toString()
                    )
                )
                setReorderingAllowed(true)
                addToBackStack(null)
                replace<SignUpFragment>(
                    R.id.boxFragment,
                    args = b
                )
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }
}