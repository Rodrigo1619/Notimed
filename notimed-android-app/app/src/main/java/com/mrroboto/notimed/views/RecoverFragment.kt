package com.mrroboto.notimed.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.mrroboto.notimed.NotiMedApplication
import com.mrroboto.notimed.R
import com.mrroboto.notimed.databinding.FragmentRecoverBinding
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        binding.recoverButton.setOnClickListener {
            val email = binding.editEmail.editText

            if(email!!.text.toString().isEmpty()) {
                binding.editEmail.error = getString(R.string.onErrorEmpty)
            } else {
                binding.editEmail.error = null
                it.findNavController().navigate(R.id.action_recoverFragment_to_loginFragment)
            }

        }
    }

    override fun onPause() {
        super.onPause()
        val email = binding.editEmail.editText?.text

        viewModel.currentEmail.value = email.toString()

        viewModel.currentEmail.observe(viewLifecycleOwner) {
            binding.editEmail.editText!!.setText(viewModel.currentEmail.value)
        }


    }
}