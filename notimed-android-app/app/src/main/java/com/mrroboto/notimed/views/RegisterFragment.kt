package com.mrroboto.notimed.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.mrroboto.notimed.NotiMedApplication
import com.mrroboto.notimed.R
import com.mrroboto.notimed.databinding.FragmentRegisterBinding
import com.mrroboto.notimed.network.ApiResponse
import com.mrroboto.notimed.viewmodels.UserViewModel
import com.mrroboto.notimed.viewmodels.ViewModelFactory
import java.util.*

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
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

        binding.labelLogin.setOnClickListener {
            it.findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        binding.registerButton.setOnClickListener {
            val email = binding.editEmail.editText
            val password = binding.editPassword.editText
            val name = binding.editName.editText
            val lastName = binding.editLastName.editText
            val birthday = binding.editBirthday.editText
            val genderContainer = binding.gender.editText

            viewModel.currentEmail.value = email?.text.toString()
            viewModel.currentPassword.value = password?.text.toString()
            viewModel.currentBirthday.value = birthday?.text.toString()
            viewModel.currentLastname.value = lastName?.text.toString()
            viewModel.currentName.value = name?.text.toString()
            viewModel.currentGender.value = genderContainer?.text.toString()
            if (!(isValidName() && isValidLastname() && isValidEmail() && isValidBirthday() && isValidPassword() && isValidGender())) {
                isValidName()
                isValidLastname()
                isValidEmail()
                isValidBirthday()
                isValidPassword()
                isValidGender()
            } else {
                viewModel.register(isLoading = true)
            }
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
                        getString(R.string.success_create_user),
                        Toast.LENGTH_SHORT
                    ).show()
                    findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                }
                is ApiResponse.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    if (it.errorCode == 409) {
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.error_existing_user),
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if (it.errorCode == 400) {
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.error_400_register),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        val email = binding.editEmail.editText
        val password = binding.editPassword.editText
        val name = binding.editName.editText
        val lastName = binding.editLastName.editText
        val birthday = binding.editBirthday.editText
        val genderContainer = binding.gender.editText

        viewModel.currentEmail.value = email?.text.toString()
        viewModel.currentPassword.value = password?.text.toString()
        viewModel.currentBirthday.value = birthday?.text.toString()
        viewModel.currentLastname.value = lastName?.text.toString()
        viewModel.currentName.value = name?.text.toString()
        viewModel.currentGender.value = genderContainer?.text.toString()


        viewModel.currentEmail.observe(viewLifecycleOwner) {
            email!!.setText(it)
        }

        viewModel.currentPassword.observe(viewLifecycleOwner) {
            password!!.setText(it)
        }

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

    private fun isValidName(): Boolean {
        val name = binding.editName

        name.editText!!.doOnTextChanged { _, _, _, _ ->
            name.error = null
        }

        return when {
            name.editText?.text.toString().isEmpty() -> {
                name.error =
                    getString(R.string.onErrorEmpty)
                false
            }
            name.editText?.text.toString().length <= 2 -> {
                name.error =
                    getString(R.string.ErrorforName)
                false
            }
            else -> {
                name.error = null
                true
            }
        }
    }

    private fun isValidLastname(): Boolean {
        val lastName = binding.editLastName

        lastName.editText!!.doOnTextChanged { _, _, _, _ ->
            lastName.error = null
        }

        return when {
            lastName.editText?.text.toString().isEmpty() -> {
                lastName.error =
                    getString(R.string.onErrorEmpty)
                false
            }
            lastName.editText?.text.toString().length <= 2 -> {
                lastName.error =
                    getString(R.string.ErrorforLastName)
                false
            }
            else -> {
                lastName.error = null
                true
            }
        }
    }

    private fun isValidEmail(): Boolean {
        val email = binding.editEmail

        email.editText!!.doOnTextChanged { _, _, _, _ ->
            email.error = null
        }

        when {
            email.editText?.text.toString().isEmpty() -> {
                email.error =
                    getString(R.string.onErrorEmpty)
                return false
            }
            email.editText?.text.toString().length <= 2 -> {
                email.error =
                    getString(R.string.ErrorforEmail)
                return false
            }
            else -> {
                email.error = null
                return true
            }
        }
    }

    private fun isValidBirthday(): Boolean {
        val date = binding.editBirthday

        date.editText!!.doOnTextChanged { _, _, _, _ ->
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

    private fun isValidPassword(): Boolean {
        val password = binding.editPassword

        password.editText!!.doOnTextChanged { _, _, _, _ ->
            password.error = null
        }

        return when {
            password.editText?.text.toString().isEmpty() -> {
                password.error =
                    getString(R.string.onErrorEmpty)
                false
            }
            password.editText?.text.toString().length < 8 -> {
                password.error =
                    getString(R.string.minimunChars)
                false
            }
            else -> {
                password.error = null
                true
            }
        }

    }

    private fun isValidGender(): Boolean {
        val gender = binding.gender

        gender.editText!!.doOnTextChanged { _, _, _, _ ->
            gender.error = null
        }

        return if (gender.editText?.text.toString().isEmpty()) {
            gender.error = getString(R.string.ErrorforDropdown)
            false
        } else true
    }
}


