package com.mrroboto.notimed.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.mrroboto.notimed.R
import com.mrroboto.notimed.databinding.FragmentAddReminderBinding

class AddReminderFragment : Fragment() {

    private lateinit var binding: FragmentAddReminderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_reminder, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val times = resources.getStringArray(R.array.times)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, times)

        binding.dropdownTimes.setAdapter(arrayAdapter)
    }

}