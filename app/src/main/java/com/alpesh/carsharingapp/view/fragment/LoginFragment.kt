package com.alpesh.carsharingapp.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alpesh.carsharingapp.R
import com.alpesh.carsharingapp.databinding.FragmentLoginBinding
import com.alpesh.carsharingapp.view.activity.MainActivity
import com.alpesh.carsharingapp.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val loginViewModel: LoginViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        binding.btnLogin.setOnClickListener {
            val response = loginViewModel.checkLogin(
                binding.edtEmailId.text.toString(),
                binding.edtPassword.text.toString()
            )
            if (response.code == 200) {
                //TODO Sucees Flow
                Toast.makeText(activity, response.message, Toast.LENGTH_SHORT).show()
                startActivity(Intent(activity,MainActivity::class.java))
            } else {
                //TODO Failure Flow
                startActivity(Intent(activity,MainActivity::class.java))
                Toast.makeText(activity, response.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}