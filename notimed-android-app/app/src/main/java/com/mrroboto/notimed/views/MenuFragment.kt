package com.mrroboto.notimed.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.mrroboto.notimed.NotiMedApplication
import com.mrroboto.notimed.R
import com.mrroboto.notimed.databinding.FragmentMenuBinding
import com.mrroboto.notimed.network.ApiResponse
import com.mrroboto.notimed.viewmodels.UserViewModel
import com.mrroboto.notimed.viewmodels.ViewModelFactory

class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenuBinding
    private lateinit var application: NotiMedApplication

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_menu, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.whoami(isLoading = true)

        application = requireActivity().application as NotiMedApplication

        binding.profileButton.setOnClickListener {
             it.findNavController().navigate(R.id.action_menuFragment_to_profileFragment)
        }
        binding.reminderButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_menuFragment_to_reminderFragment)
        }

        binding.contactButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_menuFragment_to_contactFragment)
        }

        binding.appointmentButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_menuFragment_to_appointmentFragment)
        }


        viewModel.whoamiResponse.observe(viewLifecycleOwner) {
            when(it) {
                is ApiResponse.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.progressBar.bringToFront()
                }
                is ApiResponse.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.menuUsername.text = it.data.content.name
                    application.saveID(it.data.content._id)
                }
                is ApiResponse.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), R.string.general_error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}