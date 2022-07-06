package com.mrroboto.notimed.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.mrroboto.notimed.NotiMedApplication
import com.mrroboto.notimed.R
import com.mrroboto.notimed.databinding.FragmentLoginBinding
import com.mrroboto.notimed.network.ApiResponse
import com.mrroboto.notimed.viewmodels.UserViewModel
import com.mrroboto.notimed.viewmodels.ViewModelFactory


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var application: NotiMedApplication

    private val viewModelFactory by lazy {
        val app = requireActivity().application as NotiMedApplication
        ViewModelFactory(app.getUserRepository())
    }

    private val viewModel: UserViewModel by viewModels {
        viewModelFactory
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
        binding.viewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        application = requireActivity().application as NotiMedApplication

        binding.loginButton.setOnClickListener {
            val email = binding.editEmail.editText?.text
            val password = binding.editPassword.editText?.text

            if (email.toString().isEmpty()) {
                binding.editEmail.error = getString(R.string.onErrorEmpty)
            } else if (password.toString().isEmpty()) {
                binding.editPassword.error = getString(R.string.onErrorEmpty)
            } else {
                binding.editPassword.error = null
                binding.editEmail.error = null

                viewModel.onLogin(email.toString(), password.toString(), isLoading = true)
            }
        }

        viewModel.apiResponse.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResponse.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.progressBar.bringToFront()
                }
                is ApiResponse.Success -> {
                    binding.progressBar.visibility = View.GONE
                    findNavController().navigate(R.id.action_loginFragment_to_menuFragment)
                    // Guardamos el token del usuario que recien inicia sesión
                    application.saveAuthToken(it.data as String)
                }
                is ApiResponse.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    if (it.errorCode == 404) {
                        Toast.makeText(requireContext(), getString(R.string.not_found_user), Toast.LENGTH_SHORT).show()
                    } else if (it.errorCode == 403) {
                        Toast.makeText(requireContext(), getString(R.string.credentials_error), Toast.LENGTH_SHORT).show()
                    } else if (it.errorCode == 400) {
                        Toast.makeText(requireContext(), getString(R.string.general_error), Toast.LENGTH_SHORT).show()
                    }
                }
            }
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
            binding.editEmail.editText!!.setText(it)
        }

        viewModel.currentPassword.observe(viewLifecycleOwner) {
            binding.editPassword.editText!!.setText(it)
        }
    }

}
