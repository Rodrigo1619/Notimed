package com.mrroboto.notimed.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mrroboto.notimed.NotiMedApplication
import com.mrroboto.notimed.R
import com.mrroboto.notimed.databinding.FragmentContactBinding
import com.mrroboto.notimed.databinding.FragmentLoginBinding
import com.mrroboto.notimed.databinding.FragmentMenuBinding
import com.mrroboto.notimed.viewmodels.UserViewModel
import com.mrroboto.notimed.viewmodels.ViewModelFactory

class ContactFragment : Fragment() {
    private lateinit var binding: FragmentContactBinding

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_contact, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner

        // Handler controlador de los gestos/click al boton de regresar del dispositivo
        requireActivity().onBackPressedDispatcher.addCallback(binding.lifecycleOwner!!) {
            findNavController()
                .navigate(R.id.action_contactFragment_to_menuFragment)
        }

        binding.topAppBar.setNavigationOnClickListener {
            it.findNavController().navigate(R.id.action_contactFragment_to_menuFragment)
        }

        binding.addContactFab.setOnClickListener {
            it.findNavController().navigate(R.id.action_contactFragment_to_addContactFragment)
        }

    }
}