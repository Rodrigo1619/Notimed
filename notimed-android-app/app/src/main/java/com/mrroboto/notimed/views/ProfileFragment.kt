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
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mrroboto.notimed.NotiMedApplication
import com.mrroboto.notimed.R
import com.mrroboto.notimed.databinding.FragmentProfileBinding
import com.mrroboto.notimed.network.ApiResponse
import com.mrroboto.notimed.viewmodels.UserViewModel
import com.mrroboto.notimed.viewmodels.ViewModelFactory

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.whoami(isLoading = true)

        val app = requireActivity().application as NotiMedApplication

        viewModel.whoamiResponse.observe(viewLifecycleOwner) {
            when(it) {
                is ApiResponse.Loading -> {
                    binding.progressBar4.visibility = View.VISIBLE
                    binding.progressBar4.bringToFront()
                }
                is ApiResponse.Success -> {
                    binding.progressBar4.visibility = View.GONE
                    binding.labelName.text = "${it.data.content.name} ${it.data.content.lastName}"
                    binding.labelEmail.text = it.data.content.email
                    binding.textBirthdayLabel.text = it.data.content.birthday
                }
                is ApiResponse.Failure -> {
                    binding.progressBar4.visibility = View.GONE
                    Toast.makeText(requireContext(), R.string.general_error, Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.lifecycleOwner = viewLifecycleOwner

        requireActivity().onBackPressedDispatcher.addCallback(binding.lifecycleOwner!!) {
            findNavController()
                .navigate(R.id.action_profileFragment_to_menuFragment)
        }

        binding.topAppBar.setNavigationOnClickListener {
            it.findNavController().navigate(R.id.action_profileFragment_to_menuFragment)
        }

        binding.topAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.editProfileButton -> {
                    findNavController().navigate(R.id.action_profileFragment_to_editProfileFragment)
                    true
                }

                R.id.closeSession -> {
                    MaterialAlertDialogBuilder(requireContext())
                        .setTitle(R.string.logout_title)
                        .setMessage(R.string.logout_message)
                        .setNegativeButton(R.string.no_response) { dialog, _ ->
                            dialog.cancel()
                        }
                        .setPositiveButton(R.string.yes_response) { dialog, _ ->
                            dialog.cancel()
                            app.deleteAll()
                            viewModel.deleteAllReminders()
                            findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
                        }
                        .show()
                    true
                }
                else -> false
            }
        }
    }
}