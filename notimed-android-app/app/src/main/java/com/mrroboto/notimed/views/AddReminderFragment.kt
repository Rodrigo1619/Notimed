package com.mrroboto.notimed.views

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mrroboto.notimed.R
import com.mrroboto.notimed.databinding.FragmentAddReminderBinding
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Month
import java.time.Year
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.time.Duration.Companion.days

class AddReminderFragment : Fragment() {

    private lateinit var binding: FragmentAddReminderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_reminder, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val times = resources.getStringArray(R.array.times)
        val foodOption = resources.getStringArray(R.array.response_food)
        val arrayAdapterFood = ArrayAdapter(requireContext(), R.layout.dropdown_item, foodOption)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, times)

        binding.dropdownTimes.setAdapter(arrayAdapter)
        binding.dropdownOptions.setAdapter(arrayAdapterFood)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topAppBar.setNavigationOnClickListener {
            it.findNavController().navigate(R.id.action_addReminderFragment_to_reminderFragment)
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
                        .navigate(R.id.action_addReminderFragment_to_reminderFragment)
                }
                .show()
        }

        binding.saveButton.setOnClickListener {
            if (!(isValidRepeat() && isValidDose() && isValidHour() && isValidOption()
                        && isValidMedicine() && isValidEndDate() && isValidStartDate())
            ) {
                isValidRepeat()
                isValidDose()
                isValidHour()
                isValidOption()
                isValidMedicine()
                isValidStartDate()
                isValidEndDate()
            } else {
                it.findNavController().navigate(R.id.action_addReminderFragment_to_reminderFragment)
            }
        }

        binding.hourEdit.editText?.setOnClickListener {
            val c = Calendar.getInstance()
            val hourOfDay = c.get(Calendar.HOUR_OF_DAY)
            val minute = c.get(Calendar.MINUTE)

            val timeDialog = TimePickerDialog(
                requireContext(),
                getTimePickerListener(),
                hourOfDay,
                minute,
                false
            )

            timeDialog.show()
        }

        binding.startDate.editText?.setOnClickListener {
            // Obtenemos la instancia del calendario.
            val c = Calendar.getInstance()
            // Para setear como fecha minima, obtenemos el dia de mes actual para el dia minimo que se muestre
            // en el datepicker
            val day = c.get(Calendar.DAY_OF_MONTH)

            // Creamos el datePickerDialog
            val datePickerDialog = DatePickerDialog(requireContext())

            // Seteamos el dia actual como el dia minimo para un recordatorio.
            c.set(Calendar.DAY_OF_MONTH, day)
            datePickerDialog.datePicker.minDate = c.timeInMillis

            datePickerDialog.show()

            datePickerDialog.datePicker.setOnDateChangedListener { _, year, month, dayOfMonth ->
                Calendar.getInstance().set(Calendar.YEAR, year)
                Calendar.getInstance().set(Calendar.MONTH, month)
                Calendar.getInstance().set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val selected =
                    dayOfMonth.toString() + " / " + (month + 1).toString() + " / " + year.toString()

                binding.startDate.editText?.setText(selected)
            }
        }

        binding.endDate.editText?.setOnClickListener {
            // Obtenemos la instancia del calendario.
            val c = Calendar.getInstance()

            val minDate = binding.startDate.editText?.text.toString()

            if (minDate.isEmpty()) {
                binding.startDate.error = getString(R.string.enter_date)
            } else {

                binding.startDate.error = null

                val date = LocalDate.parse(minDate, DateTimeFormatter.ofPattern("d / M / yyyy"))

                // Creamos el datePickerDialog
                val datePickerDialog = DatePickerDialog(requireContext())

                // Seteamos el dia actual como el dia minimo para una recordatorio.
                c.set(Calendar.DAY_OF_MONTH, date.dayOfMonth)
                datePickerDialog.datePicker.minDate = c.timeInMillis

                datePickerDialog.show()

                datePickerDialog.datePicker.setOnDateChangedListener { _, year, month, dayOfMonth ->
                    Calendar.getInstance().set(Calendar.YEAR, year)
                    Calendar.getInstance().set(Calendar.MONTH, month)
                    Calendar.getInstance().set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    val selected =
                        dayOfMonth.toString() + " / " + (month + 1).toString() + " / " + year.toString()

                    binding.endDate.editText?.setText(selected)
                }

            }

        }

    }



    // FUNCIONES DE VALIDACIÓN DE INPUTS
    private fun isValidMedicine(): Boolean {
        val medicine = binding.editName

        medicine.editText!!.doOnTextChanged { text, start, before, count ->
            medicine.error = null
        }

        return if (medicine.editText?.text.toString().isEmpty()) {
            medicine.error = getString(R.string.onErrorEmpty)
            false
        } else true
    }

    private fun isValidRepeat(): Boolean {
        val times = binding.editTimesADay

        times.editText!!.doOnTextChanged { text, start, before, count ->
            times.error = null
        }

        return if (times.editText?.text.toString().isEmpty()) {
            times.error = getString(R.string.ErrorforDropdown)
            false
        } else true
    }

    private fun isValidHour(): Boolean {
        val hour = binding.hourEdit

        hour.editText!!.doOnTextChanged { text, start, before, count ->
            hour.error = null
        }

        return if (hour.editText?.text.toString().isEmpty()) {
            hour.error = getString(R.string.ErrorForHour)
            false
        } else true
    }

    private fun isValidDose(): Boolean {
        val dose = binding.doseEdit

        dose.editText!!.doOnTextChanged { text, start, before, count ->
            dose.error = null
        }

        return if (dose.editText?.text.toString().isEmpty()) {
            dose.error = getString(R.string.DoseEmpty)
            false
        } else if (dose.editText!!.text.toString().toFloat() <= 0) {
            dose.error = getString(R.string.DoseLessZero)
            false
        } else true
    }

    private fun isValidStartDate(): Boolean {
        val date = binding.startDate

        date.editText!!.doOnTextChanged { text, start, before, count ->
            date.error = null
        }

        return if (date.editText?.text.toString().isEmpty()) {
            date.error =
                getString(R.string.ErrorForDate)
            false
        } else {
            true
        }
    }

    private fun isValidEndDate(): Boolean {
        val date = binding.endDate

        date.editText!!.doOnTextChanged { text, start, before, count ->
            date.error = null
        }

        return if (date.editText?.text.toString().isEmpty()) {
            date.error =
                getString(R.string.ErrorForDate)
            false
        } else {
            true
        }
    }

    private fun isValidOption(): Boolean {
        val option = binding.editFoodOption

        option.editText!!.doOnTextChanged { text, start, before, count ->
            option.error = null
        }

        return if (option.editText?.text.toString().isEmpty()) {
            option.error = getString(R.string.ErrorforDropdown)
            false
        } else true
    }


    // Obtenemos y seteamos la hora en el editText de Hora, usando el formato de 24 horas
    private fun getTimePickerListener(): TimePickerDialog.OnTimeSetListener {
        return TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            binding.hourEdit.editText?.setText("%02d:%02d".format(hourOfDay, minute))
        }
    }

}
