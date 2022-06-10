package com.mrroboto.notimed.identityUI

import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat.getColorStateList
import androidx.core.graphics.toColor
import androidx.core.view.get
import androidx.core.view.isEmpty
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.mrroboto.notimed.R
import com.mrroboto.notimed.databinding.FragmentRegisterBinding
import com.mrroboto.notimed.datePickers.DatePickerFragment
import java.util.*


class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private val viewModel: RegisterViewModel by activityViewModels()


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
        binding.loginViewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        binding.textInputBirthday.setOnClickListener {
            val supportFragmentManager = requireActivity().supportFragmentManager
            val newFragment = DatePickerFragment.newInstance(DatePickerDialog.OnDateSetListener { _, year, month, day ->
                Calendar.getInstance().set(Calendar.YEAR, year)
                Calendar.getInstance().set(Calendar.MONTH, month)
                Calendar.getInstance().set(Calendar.DAY_OF_MONTH, day)
                val selectedDate = day.toString() + " / " + (month + 1).toString() + " / " + year.toString()
                binding.textInputBirthday.setText(selectedDate)
            })

            newFragment.show(supportFragmentManager, "datePicker")

        }

        binding.labelLogin.setOnClickListener {

        }

        binding.registerButton.setOnClickListener {
            val email = binding.editEmail
            val password = binding.editPassword
            val name = binding.editName
            val lastName = binding.editLastName
            val birthday = binding.editBirthday

            // Validations when inputs are empty
            validations()

        }
    }

    private fun validations() {
        val email = binding.editEmail
        val password = binding.editPassword
        val name = binding.editName
        val lastName = binding.editLastName
        val birthday = binding.editBirthday
        val genderContainer = binding.gender

        when {
            email.editText?.text.toString().isEmpty() -> email.error = getString(R.string.onErrorEmpty)
            email.editText?.text.toString().length < 3 -> email.error = getString(R.string.ErrorforEmail)
            else -> email.error = null
        }

        when {
            name.editText?.text.toString().isEmpty() -> name.error = getString(R.string.onErrorEmpty)
            name.editText?.text.toString().length <= 2 -> name.error = getString(R.string.ErrorforName)
            else -> name.error = null
        }

        when {
            lastName.editText?.text.toString().isEmpty() -> lastName.error = getString(R.string.onErrorEmpty)
            lastName.editText?.text.toString().length <= 2 -> lastName.error = getString(R.string.ErrorforLastName)
            else -> lastName.error = null
        }


        when {
            password.editText?.text.toString().isEmpty() -> password.error = getString(R.string.onErrorEmpty)
            password.editText?.text.toString().length < 8 -> password.error = getString(R.string.minimunChars)
            else -> password.error = null
        }

        when {
            birthday.editText?.text.toString().isEmpty() -> birthday.error = getString(R.string.onErrorEmpty)
            else -> birthday.error = null
        }


        when {
            genderContainer.editText?.text.toString().isEmpty() -> genderContainer.error = getString(R.string.ErrorforDropdown)
            else -> genderContainer.error = null
        }

    }
}


