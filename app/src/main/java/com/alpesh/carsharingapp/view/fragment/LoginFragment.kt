package com.alpesh.carsharingapp.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.alpesh.carsharingapp.R
import com.alpesh.carsharingapp.databinding.FragmentLoginBinding
import com.alpesh.carsharingapp.utils.Resource
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

        binding.edtEmailId.setText("alpesh.devit@gmail.com")
        binding.edtPassword.setText("123")

        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_LoginFragment_to_RegisterFragment)
        }
        binding.btnLogin.setOnClickListener {
            loginViewModel.checkLogin(
                binding.edtEmailId.text.toString(),
                binding.edtPassword.text.toString()
            )
        }

        loginViewModel.userLoginStatus.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    //TODO Need to implement Progressbar
                }
                is Resource.Success -> {
                    Toast.makeText(
                        activity,
                        it.data?.message ?: "User Login successful",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(Intent(activity, MainActivity::class.java))
                }
                is Resource.Error -> {
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}