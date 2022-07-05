package com.mrroboto.notimed.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.mrroboto.notimed.views.adapters.ContactAdapter
import com.mrroboto.notimed.R
import com.mrroboto.notimed.databinding.FragmentContactBinding
import com.mrroboto.notimed.repositories.ContactRepository
import com.mrroboto.notimed.viewmodels.ContactViewModel
import com.mrroboto.notimed.viewmodels.ViewModelFactory

class ContactFragment : Fragment() {
    private lateinit var binding: FragmentContactBinding

    private val viewModelFactory by lazy{
        val repository = ContactRepository()
        ViewModelFactory(repository)
    }
    private val viewModel: ContactViewModel by viewModels{
        viewModelFactory
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
        val contactListRecyclerView = binding.listContact
        val contactAdapter = ContactAdapter()
        contactListRecyclerView.apply{
            adapter = contactAdapter }

        viewModel.contacts.observe(viewLifecycleOwner){data->
            contactAdapter.setData(data)
        }


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