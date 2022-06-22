package com.mrroboto.notimed.views

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isEmpty
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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

        binding.textInputDate.setOnClickListener {
            // Obtenemos la instancia del calendario.
            val c = Calendar.getInstance()
            // Para setear como fecha minima, obtenemos el dia de mes actual para el dia minimo que se muestre
            // en el datepicker
            val day = c.get(Calendar.DAY_OF_MONTH)

            // Creamos el datePickerDialog
            val datePickerDialog = DatePickerDialog(requireContext())

            // Seteamos el dia actual como el dia minimo para una cita.
            c.set(Calendar.DAY_OF_MONTH, day)
            datePickerDialog.datePicker.minDate = c.timeInMillis

            datePickerDialog.show()

            datePickerDialog.datePicker.setOnDateChangedListener { _, year, month, dayOfMonth ->
                Calendar.getInstance().set(Calendar.YEAR, year)
                Calendar.getInstance().set(Calendar.MONTH, month)
                Calendar.getInstance().set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val selected =
                    dayOfMonth.toString() + " / " + (month + 1).toString() + " / " + year.toString()

                binding.editDate.editText?.setText(selected)
            }


        }

        binding.textEditHour.setOnClickListener {
            val c = Calendar.getInstance()
            val hourOfDay = c.get(Calendar.HOUR_OF_DAY)
            val minute = c.get(Calendar.MINUTE)

            val timeDialog =
                TimePickerDialog(
                    requireContext(),
                    getTimePickerListener(),
                    hourOfDay,
                    minute,
                    false
                )

            timeDialog.show()
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
            val doctor = binding.editDoctor
            val date = binding.editDate
            val hour = binding.editHour
            val appointment = binding.editAppointmentName
            val address = binding.editAddress

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

    // Obtenemos y seteamos la hora en el editText de Hora, usando el formato de 24 horas
    private fun getTimePickerListener(): TimePickerDialog.OnTimeSetListener {
        return TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            binding.editHour.editText?.setText("%02d:%02d".format(hourOfDay, minute))
        }
    }

    private fun isValidAppointment(): Boolean {
        val appointment = binding.editAppointmentName

        appointment.editText!!.doOnTextChanged { text, start, before, count ->
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


        doctor.editText!!.doOnTextChanged { text, start, before, count ->
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

        date.editText!!.doOnTextChanged { text, start, before, count ->
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

        address.editText!!.doOnTextChanged { text, start, before, count ->
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