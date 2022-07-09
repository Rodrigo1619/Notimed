package com.mrroboto.notimed.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat.CLOCK_12H
import com.mrroboto.notimed.NotiMedApplication
import com.mrroboto.notimed.R
import com.mrroboto.notimed.databinding.FragmentAddContactBinding
import com.mrroboto.notimed.network.ApiResponse
import com.mrroboto.notimed.viewmodels.ContactViewModel
import com.mrroboto.notimed.viewmodels.ViewModelFactory
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class AddContactFragment : Fragment() {
    private lateinit var binding: FragmentAddContactBinding

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_contact, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = viewModel

        // Handler controlador de los gestos/click al boton de regresar del dispositivo
        requireActivity().onBackPressedDispatcher.addCallback(binding.lifecycleOwner!!) {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(R.string.warning_title_contact)
                .setMessage(R.string.warning_body_contact)
                .setNegativeButton(R.string.no_response) { dialog, _ ->
                    dialog.cancel()
                }
                .setPositiveButton(R.string.yes_response) { dialog, _ ->
                    dialog.cancel()
                    findNavController()
                        .navigate(R.id.action_addContactFragment_to_contactFragment)
                }
                .show()
        }

        binding.topAppBar.setNavigationOnClickListener {
            it.findNavController().navigate(R.id.action_addContactFragment_to_contactFragment)
        }


        binding.startHour.editText?.setOnClickListener {
            val endhour = binding.endHour
            val timePicker = MaterialTimePicker.Builder()
                .setTimeFormat(CLOCK_12H)
                .setHour(12)
                .setMinute(0)
                .setTitleText(R.string.ErrorForHour)
                .build()

            timePicker.show(childFragmentManager, "Tag")

            timePicker.addOnPositiveButtonClickListener {
                if (endhour.editText?.text.toString().isNotEmpty()) {
                    val endHourParsed = LocalTime.parse(
                        endhour.editText!!.text.toString(),
                        DateTimeFormatter.ofPattern("HH:mm")
                    )

                    if (timePicker.hour > endHourParsed.hour || timePicker.minute > endHourParsed.minute) {
                        binding.startHour.error = getString(R.string.ErrorStartHourHigher)
                    } else {
                        binding.startHour.error = null
                    }
                }
                binding.startHour.editText!!.setText(
                    "%02d:%02d".format(
                        timePicker.hour,
                        timePicker.minute
                    )
                )

            }

        }

        binding.endHour.editText?.setOnClickListener {
            val timePicker = MaterialTimePicker.Builder()
                .setTimeFormat(CLOCK_12H)
                .setHour(12)
                .setMinute(0)
                .setTitleText(R.string.ErrorForHour)
                .build()

            val startHour = binding.startHour

            if (startHour.editText?.text.toString().isEmpty()) {
                startHour.error = getString(R.string.first_start)
            } else {
                startHour.error = null

                timePicker.show(childFragmentManager, "Tag")

                timePicker.addOnPositiveButtonClickListener {
                    val startHourParsed = LocalTime.parse(
                        startHour.editText!!.text.toString(),
                        DateTimeFormatter.ofPattern("HH:mm")
                    )
                    if (startHourParsed.hour > timePicker.hour) {
                        binding.endHour.error = getString(R.string.ErrorLessHour)
                    } else {
                        binding.endHour.error = null
                        binding.endHour.editText!!.setText(
                            "%02d:%02d".format(
                                timePicker.hour,
                                timePicker.minute
                            )
                        )
                    }

                }
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

            if (!(isValidDoctor() && isValidAddress() && isValidPhone() && isValidEndHour()
                        && isValidStartHour() && isValidSpecialization())
            ) {
                isValidDoctor()
                isValidAddress()
                isValidPhone()
                isValidEndHour()
                isValidStartHour()
                isValidSpecialization()
            } else {
                val doctor = binding.editDoctor.editText?.text.toString()
                val phone = binding.editPhone.editText?.text.toString()
                val address = binding.editAddress.editText?.text.toString()
                val specialization = binding.editSpecialization.editText?.text.toString()
                val startHour = binding.startHour.editText?.text.toString()
                val endHour = binding.endHour.editText?.text.toString()

                viewModel.createContact(doctor, phone, address, specialization, startHour, endHour)
            }
        }

        viewModel.apiResponse.observe(viewLifecycleOwner) {
            when(it) {
                is ApiResponse.Loading -> {
                    binding.progressBar5.visibility = View.VISIBLE
                    binding.progressBar5.bringToFront()
                }
                is ApiResponse.Success -> {
                    Toast.makeText(requireContext(), getString(R.string.contact_created), Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_addContactFragment_to_contactFragment)
                }
                is ApiResponse.Failure -> {
                    when(it.errorCode) {
                        400 -> Toast.makeText(requireContext(), getString(R.string.error_400_register), Toast.LENGTH_SHORT).show()
                        else -> Toast.makeText(requireContext(), getString(R.string.general_error), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()

        val name = binding.editDoctor.editText
        val address = binding.editAddress.editText
        val specialization = binding.editSpecialization.editText
        val startHour = binding.startHour.editText
        val endHour = binding.endHour.editText
        val phone = binding.editPhone.editText

        viewModel.currentName.value = name?.text.toString()
        viewModel.currentAdress.value = address?.text.toString()
        viewModel.currentNumber.value = phone?.text.toString()
        viewModel.currentEndHour.value = endHour?.text.toString()
        viewModel.currentStartHour.value = startHour?.text.toString()
        viewModel.currentSpecialization.value = specialization?.text.toString()

        viewModel.currentName.observe(viewLifecycleOwner) {
            name!!.setText(it)
        }

        viewModel.currentAdress.observe(viewLifecycleOwner) {
            address!!.setText(it)
        }
        viewModel.currentNumber.observe(viewLifecycleOwner) {
            phone!!.setText(it)
        }
        viewModel.currentEndHour.observe(viewLifecycleOwner) {
            endHour!!.setText(it)
        }
        viewModel.currentStartHour.observe(viewLifecycleOwner) {
            startHour!!.setText(it)
        }
        viewModel.currentSpecialization.observe(viewLifecycleOwner) {
            specialization!!.setText(it)
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

    private fun isValidPhone(): Boolean {
        val phone = binding.editPhone

        phone.editText!!.doOnTextChanged { _, _, _, _ ->
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

    private fun isValidSpecialization(): Boolean {
        val specialization = binding.editSpecialization

        specialization.editText!!.doOnTextChanged { _, _, _, _ ->
            specialization.error = null
        }

        return when {
            specialization.editText?.text.toString().isEmpty() -> {
                specialization.error =
                    getString(R.string.onErrorEmpty)
                false
            }

            specialization.editText?.text?.length!! < 3 -> {
                specialization.error =
                    getString(R.string.ErrorForMinumunChars)
                return false
            }
            else -> {
                specialization.error = null
                true
            }
        }

    }

    private fun isValidStartHour(): Boolean {
        val startHour = binding.startHour

        startHour.editText!!.doOnTextChanged { _, _, _, _ ->
            startHour.error = null
        }

        return if (startHour.editText?.text.toString().isEmpty()) {
            startHour.error = getString(R.string.ErrorForHour)
            false
        } else true
    }

    private fun isValidEndHour(): Boolean {
        val endHour = binding.endHour

        endHour.editText!!.doOnTextChanged { _, _, _, _ ->
            endHour.error = null
        }

        return if (endHour.editText?.text.toString().isEmpty()) {
            endHour.error = getString(R.string.ErrorForHour)
            false
        } else true
    }

}