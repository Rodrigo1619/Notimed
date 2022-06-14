package com.mrroboto.notimed.views

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.mrroboto.notimed.NotiMedApplication
import com.mrroboto.notimed.R
import com.mrroboto.notimed.databinding.FragmentRegisterBinding
import com.mrroboto.notimed.data.models.User
import com.mrroboto.notimed.viewmodels.ViewModelFactory
import com.mrroboto.notimed.views.datePickers.DatePickerFragment
import com.mrroboto.notimed.viewmodels.UserViewModel
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
            it.findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        binding.registerButton.setOnClickListener {
            val email = binding.editEmail.editText?.text.toString()
            val password = binding.editPassword.editText?.text.toString()
            val name = binding.editName.editText?.text.toString()
            val lastName = binding.editLastName.editText?.text.toString()
            val birthday = binding.editBirthday.editText?.text.toString()

            // Validations when inputs are empty
            validationsInputs()



            val newUser = User(email, password, name, lastName, birthday)

        }
    }

    private fun validationsInputs() {
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

    override fun onPause() {
        super.onPause()
        val email = binding.editEmail.editText?.text
        val password = binding.editPassword.editText?.text
        val name = binding.editName.editText?.text
        val lastName = binding.editLastName.editText?.text
        val birthday = binding.editBirthday.editText?.text
        val genderContainer = binding.gender.editText?.text

        viewModel.currentEmail.value = email.toString()
        viewModel.currentPassword.value = password.toString()
        viewModel.currentBirthday.value = birthday.toString()
        viewModel.currentLastname.value = lastName.toString()
        viewModel.currentName.value = name.toString()
        viewModel.currentGender.value = genderContainer.toString()


        viewModel.currentEmail.observe(viewLifecycleOwner) {
            binding.editEmail.editText!!.setText(viewModel.currentEmail.value)
        }

        viewModel.currentPassword.observe(viewLifecycleOwner) {
            binding.editPassword.editText!!.setText(viewModel.currentPassword.value)
        }

        viewModel.currentBirthday.observe(viewLifecycleOwner) {
            binding.editBirthday.editText!!.setText(viewModel.currentBirthday.value)
        }

        viewModel.currentLastname.observe(viewLifecycleOwner) {
            binding.editLastName.editText!!.setText(viewModel.currentLastname.value)
        }

        viewModel.currentName.observe(viewLifecycleOwner) {
            binding.editName.editText!!.setText(viewModel.currentName.value)
        }

        viewModel.currentGender.observe(viewLifecycleOwner) {
            binding.gender.editText!!.setText(viewModel.currentGender.value)
        }
    }
}


