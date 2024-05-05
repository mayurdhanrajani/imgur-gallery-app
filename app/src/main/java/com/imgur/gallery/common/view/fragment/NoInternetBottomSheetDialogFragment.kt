package com.imgur.gallery.common.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.imgur.gallery.databinding.FragmentDialogBottomSheetNoInternetBinding

/** The bottom sheet dialog fragment shows the UI of when internet is disconnected **/
class NoInternetBottomSheetDialogFragment : BottomSheetDialogFragment() {

    /** This variable will store the instance of NoInternetBottomSheetDialogFragment layout file views **/
    private lateinit var binding: FragmentDialogBottomSheetNoInternetBinding

    /** This function will create the view of the layout file defined **/
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDialogBottomSheetNoInternetBinding.inflate(inflater, container, false)
        return binding.root
    }

    /** This function will be called after the view is created and used to initialize the views and values **/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews()
    }

    /** This function will start calling the functions to initialize views **/
    private fun initializeViews() {
        setListeners()
    }

    /** This function will set the listener for the views used **/
    private fun setListeners() {
        binding.btnOk.setOnClickListener {
            dismiss()
        }
    }

}