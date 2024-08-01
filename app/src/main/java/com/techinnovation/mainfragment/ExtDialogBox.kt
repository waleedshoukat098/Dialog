package com.techinnovation.mainfragment

import android.graphics.Color
import com.techinnovation.mainfragment.databinding.FragmentDialogBoxBinding
import com.techinnovation.mainfragment.extension.visible

enum class DIALOGTYPE {
   Ok,OKCANCEL,DELETE
}

fun FragmentDialogBoxBinding.createDialogBox(type : DIALOGTYPE) {
    when(type){
        DIALOGTYPE.Ok -> this.okDialogBox()
        DIALOGTYPE.OKCANCEL -> this.customizeOkCancelDialogBox()
        DIALOGTYPE.DELETE -> this.deleteDialogBoxText()
    }

}
fun FragmentDialogBoxBinding.okDialogBox(
    title: String = "Testing For Ok",
    message: String = "Message",
    buttonText: String = "Done",
    type: String? = null
) {
    this.apply {
        btnOk.text = buttonText
        btnOk.visible()
        tvTitle.text = title
        tvTitle.visible()
        tvMsg.text = message
        tvMsg.setTextColor(Color.BLUE)
        tvTitle.setTextColor(Color.MAGENTA)
    }
}

fun FragmentDialogBoxBinding.customizeOkCancelDialogBox(
    titleHeading: String = "Title",
    message: String = "Message",
    titleOK: String = "OK",
    titleCancel: String = "Cancel",
    okButtonColor: Int = Color.GREEN,
    cancelButtonColor: Int = Color.RED,
    alertIconResId: Int = R.drawable.ic_dialog_alert
) {
    this.apply {
        // Set text for title and message
        tvTitle.text = titleHeading
        tvMsg.text = message

        // Set button texts
        btnOk.text = titleOK
        btnCancel.text = titleCancel

        // Customize button colors
        btnOk.setTextColor(okButtonColor)
        btnCancel.setTextColor(cancelButtonColor)

        // Make buttons visible
        btnOk.visible()
        btnCancel.visible()

        // Set icon
        ivAlert.setImageResource(alertIconResId)

        // Additional styling for title and message
        tvTitle.setTextColor(Color.YELLOW)
        tvMsg.setTextColor(Color.BLUE)

    }
}

fun FragmentDialogBoxBinding.deleteDialogBoxText(
    title: String = "Are you sure you want\nto delete?",
    message: String = "Once removed, it will be permanently deleted.",
    deleteButtonText: String = "Delete",
    cancelButtonText: String = "Cancel",
    deleteButtonColor: Int = Color.RED,
    cancelButtonColor: Int = Color.GRAY,
    alertIconResId: Int = R.drawable.ic_dialog_alert
) {
    tvTitle.text = title
    tvMsg.text = message

    // Set button texts
    btnOk.text = deleteButtonText
    btnCancel.text = cancelButtonText

    // Customize button colors
    btnOk.setTextColor(deleteButtonColor)
    btnCancel.setTextColor(cancelButtonColor)

    // Make buttons visible
    btnCancel.visible()


    // Set icon
    ivAlert.setImageResource(alertIconResId)
    ivAlert.setImageResource(R.drawable.ic_dialog_attention)
    btnCancel.setBackgroundColor(Color.YELLOW)
    // Additional styling for title and message
    tvTitle.setTextColor(Color.BLACK)
    tvMsg.setTextColor(Color.DKGRAY)

}

