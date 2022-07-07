package com.mrroboto.notimed.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.mrroboto.notimed.NotiMedApplication
import com.mrroboto.notimed.R
import com.mrroboto.notimed.databinding.FragmentEditProfileBinding
import com.mrroboto.notimed.network.ApiResponse
import com.mrroboto.notimed.viewmodels.UserViewModel
import com.mrroboto.notimed.viewmodels.ViewModelFactory
import java.util.*

class EditProfileFragment : Fragment() {

    private lateinit var binding: FragmentEditProfileBinding

    private val viewModelFactory by lazy {
        val app = requireActivity().application as NotiMedApplication
        ViewModelFactory(app.getUserRepository())
    }

    private val viewModel: UserViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_edit_profile, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val genders = resources.getStringArray(R.array.genders)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, genders)

        binding.dropdownGender.setAdapter(arrayAdapter)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.whoami(isLoading = true)

        val application = requireActivity().application as NotiMedApplication

        viewModel.whoamiResponse.observe(viewLifecycleOwner) {
            when(it) {
                is ApiResponse.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.progressBar.bringToFront()
                }
                is ApiResponse.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.editName.editText?.setText(it.data.content.name)
                    binding.editLastName.editText?.setText(it.data.content.lastName)
                    binding.editBirthday.editText?.setText(it.data.content.birthday)
                    binding.dropdownGender.setText(it.data.content.gender)
                }
                is ApiResponse.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), R.string.general_error, Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.textInputBirthday.setOnClickListener {
            // Declaramos la fecha minima a la cual se puede acceder
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.YEAR, 1900)

            // Declaramos las restricciones de las fechas
            val calendarConstraints =
                CalendarConstraints.Builder()
                    .setValidator(DateValidatorPointForward.from(calendar.timeInMillis))
                    .setValidator(DateValidatorPointBackward.now())
                    .build()

            // Instanciamos el MaterialDatePicker
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText(R.string.titleBirthday)
                .setCalendarConstraints(calendarConstraints)
                .build()

            datePicker.show(childFragmentManager, "Tag")

            datePicker.addOnPositiveButtonClickListener {
                binding.editBirthday.editText!!.setText(datePicker.headerText)
            }

            datePicker.addOnCancelListener {
                binding.editBirthday.editText!!.setText("")
            }
        }

        binding.saveButton.setOnClickListener {
            val name = binding.editName.editText
            val lastName = binding.editLastName.editText
            val birthday = binding.editBirthday.editText
            val genderContainer = binding.gender.editText

            viewModel.currentBirthday.value = birthday?.text.toString()
            viewModel.currentLastname.value = lastName?.text.toString()
            viewModel.currentName.value = name?.text.toString()
            viewModel.currentGender.value = genderContainer?.text.toString()

            viewModel.updateUser(application.getId())
        }

        viewModel.apiResponse.observe(viewLifecycleOwner) {

            when (it) {
                is ApiResponse.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.progressBar.bringToFront()
                }
                is ApiResponse.Success -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.success_user_update),
                        Toast.LENGTH_SHORT
                    ).show()
                    findNavController().navigate(R.id.action_editProfileFragment_to_profileFragment)
                }
                is ApiResponse.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), R.string.general_error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        val name = binding.editName.editText
        val lastName = binding.editLastName.editText
        val birthday = binding.editBirthday.editText
        val genderContainer = binding.gender.editText

        viewModel.currentBirthday.value = birthday?.text.toString()
        viewModel.currentLastname.value = lastName?.text.toString()
        viewModel.currentName.value = name?.text.toString()
        viewModel.currentGender.value = genderContainer?.text.toString()


        viewModel.currentBirthday.observe(viewLifecycleOwner) {
            birthday!!.setText(it)
        }

        viewModel.currentLastname.observe(viewLifecycleOwner) {
            lastName!!.setText(it)
        }

        viewModel.currentName.observe(viewLifecycleOwner) {
            name!!.setText(it)
        }

        viewModel.currentGender.observe(viewLifecycleOwner) {
            genderContainer!!.setText(it)
        }
    }
}