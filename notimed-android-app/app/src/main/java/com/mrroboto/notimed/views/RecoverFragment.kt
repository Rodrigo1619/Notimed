package com.mrroboto.notimed.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mrroboto.notimed.NotiMedApplication
import com.mrroboto.notimed.R
import com.mrroboto.notimed.databinding.FragmentRecoverBinding
import com.mrroboto.notimed.network.ApiResponse
import com.mrroboto.notimed.viewmodels.UserViewModel
import com.mrroboto.notimed.viewmodels.ViewModelFactory

class RecoverFragment : Fragment() {

    private lateinit var binding: FragmentRecoverBinding

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recover, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        val spinner = ProgressBar(requireContext())

        binding.recoverButton.setOnClickListener {
            val email = binding.editEmail.editText

            viewModel.currentEmail.value = email?.text.toString()

            if(email!!.text.toString().isEmpty()) {
                binding.editEmail.error = getString(R.string.onErrorEmpty)
            } else {
                binding.editEmail.error = null

                viewModel.recoverpassword(spinner)

            }
        }

        viewModel.apiResponse.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResponse.Loading -> {
                    binding.progressBar2.visibility = View.VISIBLE
                    binding.progressBar2.bringToFront()
                }
                is ApiResponse.Success -> {
                    MaterialAlertDialogBuilder(requireContext())
                        .setTitle(getString(R.string.recover_title_success).plus(" ").plus(viewModel.currentEmail.value.toString()))
                        .setMessage(R.string.recover_body_success)
                        .setPositiveButton(R.string.ok_response) { dialog, _ ->
                            dialog.cancel()
                            binding.progressBar2.visibility = View.GONE
                            findNavController().navigate(R.id.action_recoverFragment_to_loginFragment)
                        }
                        .show()
                }
                
                is ApiResponse.Failure -> {
                    binding.progressBar2.visibility = View.GONE
                    Toast.makeText(requireContext(), it.errorBody, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }



    override fun onPause() {
        super.onPause()
        val email = binding.editEmail.editText?.text

        viewModel.currentEmail.value = email.toString()

        viewModel.currentEmail.observe(viewLifecycleOwner) {
            binding.editEmail.editText!!.setText(it)
        }
    }
}