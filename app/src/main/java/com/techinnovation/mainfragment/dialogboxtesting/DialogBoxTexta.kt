package com.techinnovation.mainfragment.dialogboxtesting

import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.techinnovation.mainfragment.R
import com.techinnovation.mainfragment.customizeOkCancelDialogBox
import com.techinnovation.mainfragment.databinding.FragmentDialogBoxBinding
import com.techinnovation.mainfragment.extension.setSingleClickListener
import com.techinnovation.mainfragment.extension.visible
import com.techinnovation.mainfragment.okDialogBox
import com.ubldigital.shared.base.bottomsheets.BaseBottomSheetDialogFragment

class DialogBoxTexta(
    private var onDelete: ((View) -> Unit)? = null,
    private var onCancel: ((View) -> Unit)? = null,
    private var actionOK: ((View) -> Unit)? = null,
    private var actionCancel: ((View) -> Unit)? = null,
    private var actionNeutral: ((View) -> Unit)? = null,
    private var type: String? = null,
    private var onClose: (() -> Unit)? = null,
    var onBind: ((FragmentDialogBoxBinding) -> Unit)? = null // Added onBind parameter

) : BaseBottomSheetDialogFragment<FragmentDialogBoxBinding>(FragmentDialogBoxBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnOk.setSingleClickListener {
                actionOK?.invoke(it)
                dismiss()
            }

            btnClose.setSingleClickListener() {
                onClose?.invoke()
                dismiss()
            }
            btnCancel.setSingleClickListener() {
                actionCancel?.invoke(it)
                dismiss()
            }
            }


        // Call the onBind callback after setting up the view
        onBind?.invoke(binding)/*
        setupDeleteDialog(
            binding = binding,
            title = "Are you sure you want\nto delete?",
            message = "Once removed, it will be permanently deleted.",
            deleteButtonText = "Delete",
            cancelButtonText = "Cancel",
        )*/
    }

 /*   private fun setupDeleteDialog(
        binding: FragmentDialogBoxBinding,
        title: String = "Are you sure you want\nto delete?",
        message: String = "Once removed, it will be permanently deleted.",
        deleteButtonText: String = "Delete",
        cancelButtonText: String = "Cancel",
        deleteButtonColor: Int = Color.RED,
        cancelButtonColor: Int = Color.GRAY,
        alertIconResId: Int = R.drawable.ic_dialog_alert,
    ) {
        binding.apply {
            tvTitle.text = title
            tvMsg.text = message
            // Set button texts
            btnOk.text = deleteButtonText
            btnCancel.text = cancelButtonText

            // Customize button colors
            btnOk.setTextColor(deleteButtonColor)
            btnCancel.setTextColor(cancelButtonColor)

            // Make buttons visible
            btnOk.visible()
            btnCancel.visible()
            // Set icon
            ivAlert.setImageResource(alertIconResId)
            ivAlert.setImageResource(R.drawable.ic_dialog_fraud)
            // Additional styling
            tvTitle.setTextColor(Color.BLACK)
            tvMsg.setTextColor(Color.DKGRAY)
        }

    }

 private fun setupOkDialog(
        binding: FragmentDialogBoxBinding,
        title: String = "Operation Successful!",
        message: String = "Hello to the",
        okButtonText: String = "OK",
        okButtonColor: Int = Color.GREEN,
        alertIconResId: Int = R.drawable.ic_dialog_success,
    ) {
        binding.apply {
            tvTitle.text = title
            tvMsg.text = message
            btnOk.text = okButtonText
            btnOk.setTextColor(okButtonColor)
            btnOk.visible()
            btnCancel.visible()
            ivAlert.setImageResource(alertIconResId)
            tvTitle.setTextColor(Color.BLACK)
            tvMsg.setTextColor(Color.DKGRAY)
        }
    }*/

    override fun onDismiss(dialog: DialogInterface) {
        onClose = null
        onDelete = null
        actionOK = null
        onCancel = null
        actionCancel = null
        actionNeutral = null


        super.onDismiss(dialog)
    }
}
