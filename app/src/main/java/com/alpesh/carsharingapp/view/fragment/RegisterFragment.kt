package com.alpesh.carsharingapp.view.fragment

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
import com.alpesh.carsharingapp.data.model.User
import com.alpesh.carsharingapp.databinding.FragmentRegisterBinding
import com.alpesh.carsharingapp.utils.Resource
import com.alpesh.carsharingapp.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val registerViewModel: RegisterViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_RegisterFragment_to_LoginFragment)
        }

        binding.btnRegister.setOnClickListener {
            val response = registerViewModel.addUser(
                User(
                    "",
                    binding.edtFirstName.text.toString(),
                    binding.edtLastName.text.toString(),
                    binding.edtMobileNo.text.toString(),
                    binding.edtEmailId.text.toString(),
                    binding.edtAadharNo.text.toString(),
                    binding.edtPassword.text.toString()
                )
            )

            registerViewModel.userRegistrationStatus.observe(viewLifecycleOwner, Observer {
                when (it) {
                    is Resource.Loading -> {
                        //TODO Need to implement Progressbar
                    }
                    is Resource.Success -> {
                        Toast.makeText(activity, it.data?.message ?: "User registered successfully", Toast.LENGTH_SHORT)
                            .show()
                    }
                    is Resource.Error ->{
                        Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            })

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}