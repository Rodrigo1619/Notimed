package com.mrroboto.notimed.identityUI

import android.app.DatePickerDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.core.view.isEmpty
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.navigation.findNavController
import com.mrroboto.notimed.R
import com.mrroboto.notimed.databinding.FragmentRegisterBinding
import com.mrroboto.notimed.datePickers.DatePickerFragment
import java.text.SimpleDateFormat
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
                val email = binding.editEmail.editText?.text.toString()
                val password = binding.editPassword.editText?.text.toString()
                val name = binding.editName.editText?.text.toString()
                val lastName = binding.editLastName.editText?.text.toString()
                val birthday = binding.editBirthday.editText?.text.toString()
                val radioFemale = binding.radioFemale
                val radioMale = binding.radioMale
                val radioGender = binding.radioGender
                

                if(email.isEmpty() || password.isEmpty() || name.isEmpty() || lastName.isEmpty() ||
                        birthday.isEmpty() || !radioMale.isChecked || !radioFemale.isChecked) {
                    binding.editEmail.error = getString(R.string.onErrorEmpty)
                    binding.editPassword.error = getString(R.string.onErrorEmpty)
                    binding.editBirthday.error = getString(R.string.onErrorEmpty)
                    binding.editName.error = getString(R.string.onErrorEmpty)
                    binding.editLastName.error = getString(R.string.onErrorEmpty)
                    Toast.makeText(requireActivity(), R.string.onErrorEmpty, Toast.LENGTH_SHORT).show()

                } else {
                    binding.editEmail.error = null
                    binding.editPassword.error = null
                    binding.editBirthday.error = null
                    binding.editName.error = null
                    binding.editLastName.error = null
                }
            }
    }
}


