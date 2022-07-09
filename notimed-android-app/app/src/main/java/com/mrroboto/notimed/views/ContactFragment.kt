package com.mrroboto.notimed.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mrroboto.notimed.NotiMedApplication
import com.mrroboto.notimed.R
import com.mrroboto.notimed.databinding.FragmentContactBinding
import com.mrroboto.notimed.network.ApiResponse
import com.mrroboto.notimed.viewmodels.ContactViewModel
import com.mrroboto.notimed.viewmodels.ViewModelFactory
import com.mrroboto.notimed.views.adapters.ContactAdapter

class ContactFragment : Fragment() {
    private lateinit var binding: FragmentContactBinding

    private val viewModelFactory by lazy{
        val app = requireActivity().application as NotiMedApplication
        ViewModelFactory(app.getContactRepository())
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
        contactListRecyclerView.apply {
            this.adapter = contactAdapter
        }

        val app = requireActivity().application as NotiMedApplication

        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.getContacts()

        viewModel.listResponse.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResponse.Loading -> {
                    binding.progressBarContacts.visibility = View.VISIBLE
                    binding.progressBarContacts.bringToFront()
                }
                is ApiResponse.Success -> {
                    binding.progressBarContacts.visibility = View.GONE
                    contactAdapter.setData(it.data)
                }
                is ApiResponse.Failure -> {
                    Toast.makeText(requireContext(), R.string.general_error, Toast.LENGTH_SHORT).show()
                }
            }
        }

        contactAdapter.getContactId {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(R.string.deleteContactTitle)
                .setNegativeButton(R.string.no_response) { dialog, _ ->
                    dialog.cancel()
                }
                .setPositiveButton(R.string.yes_response) { dialog, _ ->
                    viewModel.deleteContact(it)
                    dialog.cancel()

                    contactAdapter.getPosition {
                        contactAdapter.notifyItemRemoved(it)
                        contactAdapter.notifyItemChanged(it)
                    }
                }
                .show()
        }

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


        contactAdapter.getContactIdForUpdate {
            app.saveCardId(it)
        }

        contactAdapter.getPositionforUpdate {
            app.savePositionCard(it)
        }
    }
}