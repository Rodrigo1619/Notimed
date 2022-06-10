package com.mrroboto.notimed.identityUI

import android.app.DatePickerDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
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
                it.findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }
    }
}


