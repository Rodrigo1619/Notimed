package com.mrroboto.notimed.identityUI

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.mrroboto.notimed.R
import com.mrroboto.notimed.databinding.FragmentRecoverBinding

class RecoverFragment : Fragment() {

    private lateinit var binding: FragmentRecoverBinding
    private val viewModel: RecoverViewModel by activityViewModels()

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
        binding.recoverViewModel = viewModel

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