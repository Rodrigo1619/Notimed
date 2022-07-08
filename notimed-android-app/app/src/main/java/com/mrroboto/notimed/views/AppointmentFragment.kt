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
import com.mrroboto.notimed.databinding.FragmentAppointmentBinding
import com.mrroboto.notimed.network.ApiResponse
import com.mrroboto.notimed.repositories.AppointmentRepository
import com.mrroboto.notimed.viewmodels.AppointmentViewModel
import com.mrroboto.notimed.viewmodels.ViewModelFactory
import com.mrroboto.notimed.views.adapters.AppointmentAdapter


class AppointmentFragment : Fragment() {
    private lateinit var binding: FragmentAppointmentBinding

    private val viewModelFactory by lazy{
        val app = requireActivity().application as NotiMedApplication
        ViewModelFactory(app.getAppointmentRepository())
    }
    private val viewModel: AppointmentViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_appointment, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val appointmentListRecyclerView = binding.listReminder
        val appointmentAdapter = AppointmentAdapter()
        appointmentListRecyclerView.apply {
            adapter = appointmentAdapter
        }

        val app = requireActivity().application as NotiMedApplication

        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.getAppointments(isLoading = true)

        viewModel.listResponse.observe(viewLifecycleOwner){
            when (it){
                is ApiResponse.Loading ->{
                    binding.progressAppointments?.visibility  = View.VISIBLE
                    binding.progressAppointments?.bringToFront()
                }
                is ApiResponse.Success ->{
                    binding.progressAppointments?.visibility = View.GONE
                    appointmentAdapter.setData(it.data)
                }
                is ApiResponse.Failure ->{
                    Toast.makeText(requireContext(),R.string.general_error, Toast.LENGTH_SHORT).show()
                }
            }
        }
        appointmentAdapter.getAppointmentId {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(R.string.deleteAppointmentTitle)
                .setNegativeButton(R.string.no_response) { dialog, _ ->
                    dialog.cancel()
                }
                .setPositiveButton(R.string.yes_response){ dialog, _ ->
                    viewModel.deleteAppointments(it)
                    dialog.cancel()

                    appointmentAdapter.getPosition {
                        appointmentAdapter.notifyItemRemoved(it)
                        appointmentAdapter.notifyItemChanged(it)
                    }
                }
                .show()
        }

        viewModel.apiResponse.observe(viewLifecycleOwner) { it ->
            when (it){
                is ApiResponse.Loading -> {
                    binding.progressAppointments?.visibility = View.VISIBLE
                    binding.progressAppointments?.bringToFront()
                }
                is ApiResponse.Success -> {
                    binding.progressAppointments?.visibility = View.GONE
                    viewModel.getAppointments(isLoading = true)
                }
                is ApiResponse.Failure -> {
                    binding.progressAppointments?.visibility = View.GONE
                    Toast.makeText(requireContext(), "${it.errorCode}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Handler controlador de los gestos/click al boton de regresar del dispositivo
        requireActivity().onBackPressedDispatcher.addCallback(binding.lifecycleOwner!!) {
            findNavController()
                .navigate(R.id.action_appointmentFragment_to_menuFragment)
        }

        binding.topAppBar.setNavigationOnClickListener {
            it.findNavController().navigate(R.id.action_appointmentFragment_to_menuFragment)
        }


        binding.addAppointmentFab.setOnClickListener {
            it.findNavController().navigate(R.id.action_appointmentFragment_to_addAppointmentFragment)
        }

        appointmentAdapter.getAppointmentIdforUpdate {
            app.saveCardId(it)
        }
        appointmentAdapter.getPositionforUpdate {
            app.savePositionCard(it)
        }

    }
}