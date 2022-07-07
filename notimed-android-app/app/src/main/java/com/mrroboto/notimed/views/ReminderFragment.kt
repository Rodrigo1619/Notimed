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
import com.mrroboto.notimed.databinding.FragmentReminderBinding
import com.mrroboto.notimed.network.ApiResponse
import com.mrroboto.notimed.viewmodels.ReminderViewModel
import com.mrroboto.notimed.viewmodels.ViewModelFactory
import com.mrroboto.notimed.views.adapters.ReminderAdapter

class ReminderFragment : Fragment() {
    private lateinit var binding: FragmentReminderBinding

    private val viewModelFactory by lazy {
        val app = requireActivity().application as NotiMedApplication
        ViewModelFactory(app.getReminderRepository())
    }
    private val viewModel: ReminderViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_reminder, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val reminderListRecyclerView = binding.listReminder
        val reminderAdapter = ReminderAdapter()
        reminderListRecyclerView.apply {
            adapter = reminderAdapter
        }

        val app = requireActivity().application as NotiMedApplication

        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.getReminders(isLoading = true)

        viewModel.listResponse.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResponse.Loading -> {
                    binding.progressReminders.visibility = View.VISIBLE
                    binding.progressReminders.bringToFront()
                }

                is ApiResponse.Success -> {
                    binding.progressReminders.visibility = View.GONE
                    reminderAdapter.setData(it.data)
                }
                is ApiResponse.Failure -> {
                    Toast.makeText(requireContext(), R.string.general_error, Toast.LENGTH_SHORT).show()
                }
            }
        }

        reminderAdapter.getReminderId {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(R.string.deleteReminderTitle)
                .setNegativeButton(R.string.no_response) { dialog, _ ->
                    dialog.cancel()
                }
                .setPositiveButton(R.string.yes_response) { dialog, _ ->
                    viewModel.deleteReminder(it)
                    dialog.cancel()

                    reminderAdapter.getPosition {
                        reminderAdapter.notifyItemRemoved(it)
                        reminderAdapter.notifyItemChanged(it)
                    }
                }
                .show()
        }

        viewModel.apiResponse.observe(viewLifecycleOwner) { it ->
            when (it) {
                is ApiResponse.Loading -> {
                    binding.progressReminders.visibility = View.VISIBLE
                    binding.progressReminders.bringToFront()
                }

                is ApiResponse.Success -> {
                    binding.progressReminders.visibility = View.GONE
                    viewModel.getReminders(isLoading = true)
                }
                is ApiResponse.Failure -> {
                    binding.progressReminders.visibility = View.GONE
                    Toast.makeText(requireContext(), "${it.errorCode}", Toast.LENGTH_SHORT).show()
                }
            }
        }


        // Handler controlador de los gestos/click al boton de regresar del dispositivo
        requireActivity().onBackPressedDispatcher.addCallback(binding.lifecycleOwner!!) {
            app.deleteCardId()
            findNavController()
                .navigate(R.id.action_reminderFragment_to_menuFragment)
        }

        binding.topAppBar.setNavigationOnClickListener {
            app.deleteCardId()
            it.findNavController().navigate(R.id.action_reminderFragment_to_menuFragment)
        }

        binding.addReminderFab.setOnClickListener {
            it.findNavController().navigate(R.id.action_reminderFragment_to_addReminderFragment)
        }

        reminderAdapter.getReminderIdforUpdate {
            app.saveCardId(it)
        }

        reminderAdapter.getPositionforUpdate {
            app.savePositionCard(it)
        }
    }
}