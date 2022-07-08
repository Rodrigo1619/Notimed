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
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.mrroboto.notimed.NotiMedApplication
import com.mrroboto.notimed.R
import com.mrroboto.notimed.databinding.FragmentUpdateAppointmentBinding
import com.mrroboto.notimed.network.ApiResponse
import com.mrroboto.notimed.viewmodels.AppointmentViewModel
import com.mrroboto.notimed.viewmodels.ViewModelFactory
import com.mrroboto.notimed.views.adapters.ReminderAdapter

class UpdateAppointment : Fragment() {

    private lateinit var binding: FragmentUpdateAppointmentBinding

    private val viewModelFactory by lazy {
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
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_update_appointment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val app = requireActivity().application as NotiMedApplication

        val reminderAdapter = ReminderAdapter()
        binding.viewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        // Handler controlador de los gestos/click al boton de regresar del dispositivo
        requireActivity().onBackPressedDispatcher.addCallback(binding.lifecycleOwner!!) {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(R.string.warning_title_appointment)
                .setMessage(R.string.warning_body_appointment)
                .setNegativeButton(R.string.no_response) { dialog, _ ->
                    dialog.cancel()
                }
                .setPositiveButton(R.string.yes_response) { dialog, _ ->
                    app.deleteCardId()
                    dialog.cancel()
                    findNavController()
                        .navigate(R.id.action_updateAppointment_to_appointmentFragment)
                }
                .show()
        }

        binding.topAppBar.setNavigationOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(R.string.warning_title_appointment)
                .setMessage(R.string.warning_body_appointment)
                .setNegativeButton(R.string.no_response) { dialog, _ ->
                    dialog.cancel()
                }
                .setPositiveButton(R.string.yes_response) { dialog, _ ->
                    app.deleteCardId()
                    dialog.cancel()
                    findNavController()
                        .navigate(R.id.action_updateAppointment_to_appointmentFragment)
                }
                .show()
        }

        binding.cancelButton.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(R.string.warning_title_appointment)
                .setMessage(R.string.warning_body_appointment)
                .setNegativeButton(R.string.no_response) { dialog, _ ->
                    dialog.cancel()
                }
                .setPositiveButton(R.string.yes_response) { dialog, _ ->
                    app.deleteCardId()
                    dialog.cancel()
                    findNavController()
                        .navigate(R.id.action_updateAppointment_to_appointmentFragment)
                }
                .show()
        }

        viewModel.getOneAppointment(app.getCardId())

        viewModel.response.observe(viewLifecycleOwner) { it ->
            when (it) {
                is ApiResponse.Loading -> {
                    binding.progressUpdateAppointment.visibility = View.VISIBLE
                    binding.progressUpdateAppointment.bringToFront()
                }
                is ApiResponse.Success -> {
                    binding.progressUpdateAppointment.visibility = View.GONE
                    it.data.appointment.forEach {
                        binding.editAppointmentName.editText?.setText(it.appointmentName)
                        binding.editDoctor.editText?.setText(it.doctorName)
                        binding.editAddress.editText?.setText(it.address)
                        binding.editHour.editText?.setText(it.appointmentHour)
                        binding.editDate.editText?.setText(it.appointmentDate)
                        binding.editNotes.editText?.setText(it.additionalNotes)
                    }
                }
                is ApiResponse.Failure -> {
                    when(it.errorCode) {
                        404 -> Toast.makeText(requireContext(), getString(R.string.not_found_appointment), Toast.LENGTH_SHORT).show()
                        else -> Toast.makeText(requireContext(), getString(R.string.general_error), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }



        binding.saveButton.setOnClickListener {
            val name = binding.editAppointmentName.editText?.text.toString()
            val doctor = binding.editDoctor.editText?.text.toString()
            val address = binding.editAddress.editText?.text.toString()
            val hour = binding.editHour.editText?.text.toString()
            val date = binding.editDate.editText?.text.toString()
            val notes = binding.editNotes.editText?.text.toString()

            viewModel.updateAppointment(app.getCardId(), name, doctor, date, hour, address, notes)
        }



        viewModel.apiResponse.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResponse.Loading -> {
                    binding.progressUpdateAppointment.visibility = View.VISIBLE
                    binding.progressUpdateAppointment.bringToFront()
                }

                is ApiResponse.Success -> {
                    binding.progressUpdateAppointment.visibility = View.GONE
                    Toast.makeText(requireContext(), R.string.reminder_updated, Toast.LENGTH_SHORT)
                        .show()
                    reminderAdapter.notifyDataSetChanged()
                    findNavController().navigate(R.id.action_updateAppointment_to_appointmentFragment)
                    app.deleteCardId()
                }
                is ApiResponse.Failure -> {
                    binding.progressUpdateAppointment.visibility = View.GONE
                    when (it.errorCode) {
                        400 -> Toast.makeText(
                            requireContext(),
                            R.string.general_error,
                            Toast.LENGTH_SHORT
                        ).show()
                        else -> Toast.makeText(
                            requireContext(),
                            R.string.general_error,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

        binding.textEditHour.setOnClickListener {
            val timePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(0)
                .setTitleText(R.string.ErrorForHour)
                .build()

            timePicker.show(childFragmentManager, "Tag")

            timePicker.addOnPositiveButtonClickListener {
                timePicker.hour
                binding.editHour.editText!!.setText(
                    "%02d:%02d".format(
                        timePicker.hour,
                        timePicker.minute
                    )
                )
            }
        }

        binding.textInputDate.setOnClickListener {
            // Limitamos la fecha para poder elegir un rango de medicamentos
            val calendarConstraints =
                CalendarConstraints.Builder().setValidator(DateValidatorPointForward.now()).build()

            // Instanciamos el MaterialDatePicker
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText(R.string.dateRange_title)
                .setCalendarConstraints(calendarConstraints)
                .build()

            datePicker.show(childFragmentManager, "Tag")

            datePicker.addOnPositiveButtonClickListener {
                binding.editDate.editText!!.setText(datePicker.headerText)
            }
        }

    }

    override fun onPause() {
        super.onPause()

        val name = binding.editAppointmentName.editText
        val doctor = binding.editDoctor.editText
        val date = binding.editDate.editText
        val hour = binding.textEditHour
        val address = binding.editAddress.editText
        val notes = binding.editNotes.editText

        viewModel.currentName.value = name?.text.toString()
        viewModel.currentMedic.value = doctor?.text.toString()
        viewModel.currentDate.value = date?.text.toString()
        viewModel.currentHour.value = hour.text.toString()
        viewModel.currentLocalization.value = address?.text.toString()
        viewModel.currentConsiderations.value = notes?.text.toString()

        viewModel.currentName.observe(viewLifecycleOwner) {
            name?.setText(it)
        }

        viewModel.currentMedic.observe(viewLifecycleOwner) {
            doctor?.setText(it)
        }

        viewModel.currentDate.observe(viewLifecycleOwner) {
            date?.setText(it)
        }

        viewModel.currentHour.observe(viewLifecycleOwner) {
            hour.setText(it)
        }

        viewModel.currentLocalization.observe(viewLifecycleOwner) {
            address?.setText(it)
        }

        viewModel.currentConsiderations.observe(viewLifecycleOwner) {
            notes?.setText(it)
        }
    }
}