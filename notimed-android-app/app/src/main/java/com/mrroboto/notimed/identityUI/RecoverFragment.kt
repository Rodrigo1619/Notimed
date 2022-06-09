package com.mrroboto.notimed.identityUI

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mrroboto.notimed.R

class RecoverFragment : Fragment() {

    companion object {
        fun newInstance() = RecoverFragment()
    }

    private lateinit var viewModel: RecoverViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recover, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RecoverViewModel::class.java)
        // TODO: Use the ViewModel
    }

}