package com.techinnovation.mainfragment.dialogboxtesting

import android.os.Bundle
import android.view.View
import com.techinnovation.mainfragment.DialogBox.Companion.SUCCESS
import com.techinnovation.mainfragment.customizeOkCancelDialogBox
import com.techinnovation.mainfragment.databinding.FragmentDialogBoxBinding
import com.techinnovation.mainfragment.deleteDialogBoxText
import com.techinnovation.mainfragment.extension.setSingleClickListener
import com.techinnovation.mainfragment.okDialogBox
import com.ubldigital.shared.base.bottomsheets.BaseBottomSheetDialogFragment

class DialogBoxText(
    private val onBind: ((FragmentDialogBoxBinding) -> Unit)? = null,
    private var onClose: (() -> Unit)? = null,
    private var actionOk: ((View) -> Unit)? = null,
    private var actionOKCancel: ((View) -> Unit)? = null,
    private var actionNeutral: (() -> Unit)? = null
) : BaseBottomSheetDialogFragment<FragmentDialogBoxBinding>(FragmentDialogBoxBinding::inflate) {

    companion object {
        fun ok(
            title: String = "Title",
            message: String = "Message",
            buttonText: String = "OK",
            actionOK: ((View) -> Unit)? = null,
            onClose: (() -> Unit)? = null,
            onBind: ((FragmentDialogBoxBinding) -> Unit)? = null
        ): DialogBoxText {
            return DialogBoxText(
                onBind = { binding ->
                    binding.okDialogBox(title, message, buttonText)
                    onBind?.invoke(binding)
                },
                onClose = onClose,
                actionOk = actionOK
            )
        }

        fun okCancel(
            type: String = "Alert",
            titleHeading: String = type,
            titleOK: String = if (type == SUCCESS) "Done" else "OK",
            titleCancel: String = "Cancel",
            msg: String = "Any one ",
            actionOK: ((View) -> Unit)? = null,
            actionCancel: ((View) -> Unit)? = null,
            onClose: (() -> Unit)? = null,
            onBind: ((FragmentDialogBoxBinding) -> Unit)? = null
        ): DialogBoxText {
            return DialogBoxText(
                onBind = { binding ->
                    binding.customizeOkCancelDialogBox(
                        titleHeading = titleHeading,
                        message = msg,
                        titleOK = titleOK,
                        titleCancel = titleCancel
                    )
                    onBind?.invoke(binding)
                },
                onClose = onClose,
                actionOk = actionOK,

            )
        }

        fun delete(
            title: String = "Are you sure you want\nto delete?",
            msg: String = "Once removed, it will be permanently deleted.",
            titleDeleteButton: String = "Delete",
            titleCancelButton: String = "Cancel",
            onDelete: ((View) -> Unit)? = null,
            onCancel: ((View) -> Unit)? = null,
            onClose: (() -> Unit)? = null,
            onBind: ((FragmentDialogBoxBinding) -> Unit)? = null
        ): DialogBoxText {
            return DialogBoxText(
                onBind = { binding ->
                    binding.deleteDialogBoxText(
                        title = title,
                        message = msg,
                        deleteButtonText = titleDeleteButton,
                        cancelButtonText = titleCancelButton
                    )
                    onBind?.invoke(binding)
                },
                onClose = onClose,
                actionOk = onDelete,
                actionOKCancel = onCancel
            )
        }
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnOk.setSingleClickListener {
                actionOk?.invoke(it)
                dismiss()
            }

            btnClose.setSingleClickListener {
                onClose?.invoke()
                dismiss()
            }
            btnCancel.setSingleClickListener(){
                actionOKCancel?.invoke(it)
                dismiss()
            }
        }
        // Call the onBind callback after setting up the view
        onBind?.invoke(binding)
    }
}
