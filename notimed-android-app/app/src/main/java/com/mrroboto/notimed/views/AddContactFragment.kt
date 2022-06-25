package com.mrroboto.notimed.views

import android.app.TimePickerDialog
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.view.isEmpty
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.dialog.MaterialDialogs
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat.CLOCK_12H
import com.mrroboto.notimed.R
import com.mrroboto.notimed.databinding.FragmentAddContactBinding
import java.util.*

class AddContactFragment : Fragment() {
    private lateinit var binding: FragmentAddContactBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_contact, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val specializations = resources.getStringArray(R.array.specializations)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, specializations)

        binding.dropdownSpecializations.setAdapter(arrayAdapter)
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
            it.findNavController().navigate(R.id.action_addContactFragment_to_contactFragment)
        }

        binding.dropdownSpecializations.setOnItemClickListener { parent, view1, position, id ->
            DialogFragment.STYLE_NORMAL
        }

        val timePicker = MaterialTimePicker.Builder()
            .setTimeFormat(CLOCK_12H)
            .setHour(12)
            .setMinute(0)
            .setTitleText(R.string.ErrorForHour)
            .build()

        binding.startHour.editText?.setOnClickListener {

            timePicker.show(childFragmentManager, "Tag")

            timePicker.addOnPositiveButtonClickListener {
                timePicker.hour
                binding.startHour.editText!!.setText("%02d:%02d".format(timePicker.hour, timePicker.minute))
            }

        }

        binding.endHour.editText?.setOnClickListener {
            timePicker.show(childFragmentManager, "Tag")

            timePicker.addOnPositiveButtonClickListener {
                timePicker.hour
                binding.startHour.editText!!.setText("%02d:%02d".format(timePicker.hour, timePicker.minute))
            }
        }

        binding.cancelButton.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(R.string.warning_title_contact)
                .setMessage(R.string.warning_body_contact)
                .setNegativeButton(R.string.no_response) { dialog, _ ->
                    dialog.cancel()
                }
                .setPositiveButton(R.string.yes_response) { dialog, _ ->
                    dialog.cancel()
                    it.findNavController()
                        .navigate(R.id.action_addContactFragment_to_contactFragment)
                }
                .show()
        }

        binding.saveButton.setOnClickListener {

            if (!(isValidDoctor() && isValidAddress() && isValidPhone() && isValidEndHour() && isValidStartHour() && isValidSpecialization())) {
                isValidDoctor()
                isValidAddress()
                isValidPhone()
                isValidEndHour()
                isValidStartHour()
                isValidSpecialization()
            } else {

                Toast.makeText(requireContext(), "Se puede navegar", Toast.LENGTH_SHORT).show()

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

    private fun isValidPhone(): Boolean {
        val phone = binding.editPhone

        phone.editText!!.doOnTextChanged { text, start, before, count ->
            phone.error = null
        }

        return when {
            phone.editText?.text.toString().isEmpty() -> {
                phone.error =
                    getString(R.string.onErrorEmpty)
                false
            }
            phone.editText?.text?.length!! < 8 -> {
                phone.error =
                    getString(R.string.ErrorPhone)
                false
            }
            else -> {
                phone.error = null
                true
            }
        }
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

    private fun isValidSpecialization(): Boolean {
        val specialization = binding.editSpecialization

        specialization.editText!!.doOnTextChanged { text, start, before, count ->
            specialization.error = null
        }

        return when {
            specialization.editText?.text.toString().isEmpty() -> {
                specialization.error =
                    getString(R.string.onErrorEmpty)
                false
            }

            else -> {
                specialization.error = null
                true
            }
        }

    }

    private fun isValidStartHour(): Boolean {
        val startHour = binding.startHour

        startHour.editText!!.doOnTextChanged { text, start, before, count ->
            startHour.error = null
        }

        return if (startHour.editText?.text.toString().isEmpty()) {
            startHour.error = getString(R.string.ErrorForHour)
            false
        } else true
    }

    private fun isValidEndHour(): Boolean {
        val endHour = binding.endHour

        endHour.editText!!.doOnTextChanged { text, start, before, count ->
            endHour.error = null
        }

        return if (endHour.editText?.text.toString().isEmpty()) {
            endHour.error = getString(R.string.ErrorForHour)
            false
        } else true
    }

    private fun getTimePickerListener(editText: EditText): TimePickerDialog.OnTimeSetListener {

        return TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            editText.setText("%02d:%02d".format(hourOfDay, minute))
        }
    }
}