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
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.mrroboto.notimed.NotiMedApplication
import com.mrroboto.notimed.R
import com.mrroboto.notimed.databinding.FragmentUpdateContactBinding
import com.mrroboto.notimed.network.ApiResponse
import com.mrroboto.notimed.viewmodels.ContactViewModel
import com.mrroboto.notimed.viewmodels.ViewModelFactory
import com.mrroboto.notimed.views.adapters.ContactAdapter
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class UpdateContact : Fragment() {


    private lateinit var binding: FragmentUpdateContactBinding

    private val viewModelFactory by lazy {
        val app = requireActivity().application as NotiMedApplication
        ViewModelFactory(app.getContactRepository())
    }
    private val viewModel: ContactViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_update_contact, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val app = requireActivity().application as NotiMedApplication

        val contactAdapter = ContactAdapter()
        binding.viewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner


        viewModel.getOneContact(app.getCardId())

        viewModel.response.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResponse.Loading -> {
                    binding.progressBar5.visibility = View.VISIBLE
                    binding.progressBar5.bringToFront()
                }
                is ApiResponse.Success -> {
                    binding.progressBar5.visibility = View.GONE
                    it.data.contact.forEach { it ->
                        binding.editDoctor.editText?.setText(it.name)
                        binding.editAddress.editText?.setText(it.address)
                        binding.editSpecialization.editText?.setText(it.specialization)
                        binding.startHour.editText?.setText(it.startHour)
                        binding.endHour.editText?.setText(it.endHour)
                        binding.editPhone.editText?.setText(it.phoneNumber)
                    }
                }
                is ApiResponse.Failure -> {
                    when(it.errorCode) {
                        404 -> Toast.makeText(requireContext(), getString(R.string.contact_not_found), Toast.LENGTH_SHORT).show()
                        else -> Toast.makeText(requireContext(), getString(R.string.general_error), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

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
                        .navigate(R.id.action_updateContact_to_contactFragment)
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
                        .navigate(R.id.action_updateContact_to_contactFragment)
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
                        .navigate(R.id.action_updateContact_to_contactFragment)
                }
                .show()
        }

        binding.saveButton.setOnClickListener {
            val name = binding.editDoctor.editText?.text.toString()
            val address = binding.editAddress.editText?.text.toString()
            val specialization = binding.editSpecialization.editText?.text.toString()
            val startHour = binding.startHour.editText?.text.toString()
            val endHour = binding.endHour.editText?.text.toString()
            val phone = binding.editPhone.editText?.text.toString()

            viewModel.updateContact(app.getCardId(), name, phone, address,specialization, startHour, endHour)
        }



        viewModel.apiResponse.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResponse.Loading -> {
                    binding.progressBar5.visibility = View.VISIBLE
                    binding.progressBar5.bringToFront()
                }

                is ApiResponse.Success -> {
                    binding.progressBar5.visibility = View.GONE
                    Toast.makeText(requireContext(), R.string.reminder_updated, Toast.LENGTH_SHORT)
                        .show()
                    contactAdapter.notifyDataSetChanged()
                    findNavController().navigate(R.id.action_updateContact_to_contactFragment)
                    app.deleteCardId()
                }
                is ApiResponse.Failure -> {
                    binding.progressBar5.visibility = View.GONE
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

        binding.startHour.editText?.setOnClickListener {
            val endhour = binding.endHour
            val timePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
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
                .setTimeFormat(TimeFormat.CLOCK_12H)
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

}