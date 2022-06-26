package com.mrroboto.notimed.views

import android.app.DatePickerDialog
import android.app.TimePickerDialog
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
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.mrroboto.notimed.R
import com.mrroboto.notimed.databinding.FragmentAddAppointmentBinding
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class AddAppointmentFragment : Fragment() {
    private lateinit var binding: FragmentAddAppointmentBinding

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
                .setTitle(R.string.warning_title_appointment)
                .setMessage(R.string.warning_body_appointment)
                .setNegativeButton(R.string.no_response) { dialog, _ ->
                    dialog.cancel()
                }
                .setPositiveButton(R.string.yes_response) { dialog, _ ->
                    dialog.cancel()
                    findNavController()
                        .navigate(R.id.action_addAppointmentFragment_to_appointmentFragment)
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
                it.findNavController().navigate(R.id.action_addAppointmentFragment_to_appointmentFragment)
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