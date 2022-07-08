package com.mrroboto.notimed.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.view.isEmpty
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.mrroboto.notimed.NotiMedApplication
import com.mrroboto.notimed.R
import com.mrroboto.notimed.databinding.FragmentAddAppointmentBinding
import com.mrroboto.notimed.network.ApiResponse
import com.mrroboto.notimed.viewmodels.AppointmentViewModel
import com.mrroboto.notimed.viewmodels.ViewModelFactory
import java.util.*

class AddAppointmentFragment : Fragment() {
    private lateinit var binding: FragmentAddAppointmentBinding

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
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_appointment, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner

        // Handler controlador de los gestos/click al boton de regresar del dispositivo
        requireActivity().onBackPressedDispatcher.addCallback(binding.lifecycleOwner!!) {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(R.string.warning_title_reminder)
                .setMessage(R.string.warning_body_reminder)
                .setNegativeButton(R.string.no_response) { dialog, _ ->
                    dialog.cancel()
                }
                .setPositiveButton(R.string.yes_response) { dialog, _ ->
                    dialog.cancel()
                    findNavController()
                        .navigate(R.id.action_addReminderFragment_to_reminderFragment)
                }
                .show()
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

        binding.topAppBar.setNavigationOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(R.string.warning_title_appointment)
                .setMessage(R.string.warning_body_appointment)
                .setNegativeButton(R.string.no_response) { dialog, _ ->
                    dialog.cancel()
                }
                .setPositiveButton(R.string.yes_response) { dialog, _ ->
                    dialog.cancel()
                    it.findNavController()
                        .navigate(R.id.action_addAppointmentFragment_to_appointmentFragment)
                }
                .show()
        }

        binding.saveButton.setOnClickListener {

            if((isValidAppointment() && isValidHour() && isValidDoctor() && isValidDate() && isValidAddress()) == false) {
                isValidAppointment()
                isValidHour()
                isValidDoctor()
                isValidDate()
                isValidAddress()
            } else {

                val name = binding.editAppointmentName.editText?.text.toString()
                val doctor = binding.editDoctor.editText?.text.toString()
                val date = binding.editDate.editText?.text.toString()
                val hour = binding.textEditHour.text.toString()
                val address = binding.editAddress.editText?.text.toString()
                val notes = binding.editNotes.editText?.text.toString()
                viewModel.createAppointment(true, name, doctor, date, hour, address,notes)
            }
        }

        viewModel.apiResponse.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResponse.Loading -> {
                    binding.progressBar4.visibility = View.VISIBLE
                    binding.progressBar4.bringToFront()
                }

                is ApiResponse.Success -> {
                    binding.progressBar4.visibility = View.GONE
                    Toast.makeText(requireContext(), R.string.reminder_created, Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_addAppointmentFragment_to_appointmentFragment)
                }
                is ApiResponse.Failure -> {
                    binding.progressBar4.visibility = View.GONE
                    when(it.errorCode) {
                        400 -> Toast.makeText(requireContext(), R.string.general_error, Toast.LENGTH_SHORT).show()
                        409 -> Toast.makeText(requireContext(), R.string.appointment_exists, Toast.LENGTH_SHORT).show()
                        else  -> Toast.makeText(requireContext(), R.string.general_error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        binding.cancelButton.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(R.string.warning_title_appointment)
                .setMessage(R.string.warning_body_appointment)
                .setNegativeButton(R.string.no_response) { dialog, _ ->
                    dialog.cancel()
                }
                .setPositiveButton(R.string.yes_response) { dialog, _ ->
                    dialog.cancel()
                    it.findNavController()
                        .navigate(R.id.action_addAppointmentFragment_to_appointmentFragment)
                }
                .show()
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


    private fun isValidAppointment(): Boolean {
        val appointment = binding.editAppointmentName

        appointment.editText!!.doOnTextChanged { _, _, _, _ ->
            appointment.error = null
        }

        when {
            appointment.editText?.text.toString().isEmpty() -> {
                appointment.error =
                    getString(R.string.onErrorEmpty)
                return false
            }
            appointment.editText?.text?.length!! < 2 -> {
                appointment.error =
                    getString(R.string.ErrorForMinumunChars)
                return false
            }
            else -> {
                appointment.error = null
                return true
            }
        }

    }

    private fun isValidDoctor(): Boolean {
        val doctor = binding.editDoctor


        doctor.editText!!.doOnTextChanged { _, _, _, _ ->
            doctor.error = null
        }

        when {
            doctor.editText?.text.toString().isEmpty() -> {
                doctor.error =
                    getString(R.string.onErrorEmpty)
                return false
            }
            doctor.editText?.text?.length!! < 2 -> {
                doctor.error =
                    getString(R.string.ErrorForMinumunChars)
                return false
            }
            else -> {
                doctor.error = null
                return true
            }
        }
    }

    private fun isValidDate(): Boolean {
        val date = binding.editDate

        date.editText!!.doOnTextChanged { _, _, _, _ ->
            date.error = null
        }

        if (date.editText?.text.toString().isEmpty()) {
            date.error =
                getString(R.string.ErrorForDate)
            return false
        } else {
            return true
        }
    }

    private fun isValidHour(): Boolean {
        val hour = binding.editHour

        hour.editText!!.doOnTextChanged { _, _, _, _ ->
            hour.error = null
        }

        if (hour.editText?.text.toString().isEmpty()) {
            hour.error =
                getString(R.string.ErrorForHour)
            return false
        } else return true
    }

    private fun isValidAddress(): Boolean {
        val address = binding.editAddress

        address.editText!!.doOnTextChanged { _, _, _, _ ->
            address.error = null
        }

        when {
            address.editText?.text?.length!! < 8 -> {
                address.error =
                    getString(R.string.minimunChars)
                return false
            }
            address.editText?.text.toString().isEmpty() -> {
                address.error =
                    getString(R.string.onErrorEmpty)
                return false
            }

            else -> {
                address.error = null
                return true
            }
        }

    }
}