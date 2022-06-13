package com.mrroboto.notimed.identityUI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.mrroboto.notimed.R
import com.mrroboto.notimed.databinding.FragmentLoginBinding
import java.util.Observer


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginViewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        binding.loginButton.setOnClickListener {
            val email = binding.editEmail.editText?.text
            val password = binding.editPassword.editText?.text

            if(email.toString().isEmpty()) {
                binding.editEmail.error = getString(R.string.onErrorEmpty)
            } else {
                binding.editEmail.error = null
            }

            if(password.toString().isEmpty()) {
                binding.editPassword.error = getString(R.string.onErrorEmpty)
            } else {
                binding.editPassword.error = null
            }

            Toast.makeText(requireActivity(), "click: $email", Toast.LENGTH_SHORT).show()
            Toast.makeText(requireActivity(), "click: $password", Toast.LENGTH_SHORT).show()

            it.findNavController()


        }


        binding.registerButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.ForgetPassword.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginFragment_to_recoverFragment)
        }
    }

    override fun onPause() {
        super.onPause()
        val email = binding.editEmail.editText?.text
        val password = binding.editPassword.editText?.text

        viewModel.currentEmail.value = email.toString()
        viewModel.currentPassword.value = password.toString()

        viewModel.currentEmail.observe(viewLifecycleOwner) {
            binding.editEmail.editText!!.setText(viewModel.currentEmail.value)
            Toast.makeText(requireActivity(), "itEmail: $it", Toast.LENGTH_SHORT).show()
        }

        viewModel.currentPassword.observe(viewLifecycleOwner) {
            binding.editPassword.editText!!.setText(viewModel.currentPassword.value)
            Toast.makeText(requireActivity(), "itPass: $it", Toast.LENGTH_SHORT).show()
        }
    }

}
