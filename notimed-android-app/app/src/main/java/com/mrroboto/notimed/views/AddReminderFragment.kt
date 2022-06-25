package com.mrroboto.notimed.views

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.util.Pair
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialDatePicker.INPUT_MODE_TEXT
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat.CLOCK_12H
import com.mrroboto.notimed.R
import com.mrroboto.notimed.databinding.FragmentAddReminderBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class AddReminderFragment : Fragment() {

    private lateinit var binding: FragmentAddReminderBinding

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
                        .navigate(R.id.action_addReminderFragment_to_reminderFragment)
                }
                .show()
        }

        binding.topAppBar.setNavigationOnClickListener {
            it.findNavController().navigate(R.id.action_addReminderFragment_to_reminderFragment)
        }

        binding.dropdownTimes.setOnItemClickListener { parent, view, position, id ->

            val times = resources.getStringArray(R.array.times)
            val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, times)

            // Si es la position 4 (es el input custom) se setean textos de suffix y prefix para
            // que el usuario sepa que se le pide.
            if (position == 4) {
                binding.editTimesADay.suffixText = getString(R.string.hour_suffix)
                binding.editTimesADay.prefixText = getString(R.string.every_prefix)
                binding.editTimesADay.editText?.setText("")
                binding.editTimesADay.editText?.inputType = InputType.TYPE_CLASS_NUMBER
            }

            if (position != 4) {
                closeKeyboard()
                binding.editTimesADay.editText?.inputType = InputType.TYPE_NULL
                binding.editTimesADay.suffixText = null
                binding.editTimesADay.prefixText = null
                binding.dropdownTimes.setAdapter(arrayAdapter)
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
                        .navigate(R.id.action_addReminderFragment_to_reminderFragment)
                }
                .show()
        }

        binding.saveButton.setOnClickListener {
            if (!(isValidRepeat() && isValidDose() && isValidHour() && isValidOption()
                        && isValidMedicine() && isValidRangeDate())
            ) {
                isValidRepeat()
                isValidDose()
                isValidHour()
                isValidOption()
                isValidMedicine()
                isValidRangeDate()
            } else {
                it.findNavController().navigate(R.id.action_addReminderFragment_to_reminderFragment)
            }
        }

        binding.hourEdit.editText?.setOnClickListener {
            val timePicker = MaterialTimePicker.Builder()
                .setTimeFormat(CLOCK_12H)
                .setHour(12)
                .setMinute(0)
                .setTitleText(R.string.ErrorForHour)
                .build()

            timePicker.show(childFragmentManager, "Tag")

            timePicker.addOnPositiveButtonClickListener {
                timePicker.hour
                binding.hourEdit.editText!!.setText(
                    "%02d:%02d".format(
                        timePicker.hour,
                        timePicker.minute
                    )
                )
            }
        }

        binding.rangeDate.editText?.setOnClickListener {
            // Limitamos la fecha para poder elegir un rango de medicamentos
            val calendarConstraints =
                CalendarConstraints.Builder().setValidator(DateValidatorPointForward.now()).build()

            // Instanciamos el MaterialDatePicker
            val datePicker = MaterialDatePicker.Builder.dateRangePicker()
                .setTitleText(R.string.dateRange_title)
                .setCalendarConstraints(calendarConstraints)
                .build()

            datePicker.show(childFragmentManager, "Tag")

            datePicker.addOnPositiveButtonClickListener {
                binding.rangeDate.editText!!.setText(datePicker.headerText)
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

    private fun closeKeyboard() {
        view?.hideKeyboard()
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

    private fun isValidRangeDate(): Boolean {
        val date = binding.rangeDate

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

}
