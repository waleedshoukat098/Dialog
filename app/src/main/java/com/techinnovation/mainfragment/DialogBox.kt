package com.techinnovation.mainfragment

import android.content.DialogInterface
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.techinnovation.mainfragment.databinding.FragmentDialogBoxBinding
import com.techinnovation.mainfragment.extension.gone
import com.techinnovation.mainfragment.extension.invisible
import com.techinnovation.mainfragment.extension.setBackgroundTint
import com.techinnovation.mainfragment.extension.setSingleClickListener
import com.techinnovation.mainfragment.extension.visible
import com.ubldigital.shared.base.bottomsheets.BaseBottomSheetDialogFragment


class DialogBox(

    private var onClose: (() -> Unit)? = null,
    private var actionOK: ((View) -> Unit)? = null,
    private var actionCancel: ((View) -> Unit)? = null,
    private var actionNeutral: ((View) -> Unit)? = null,


    ) : BaseBottomSheetDialogFragment<FragmentDialogBoxBinding>(
    FragmentDialogBoxBinding::inflate
) {
    companion object {

//        val applicationContext = KoinJavaComponent.getKoin().get<Context>(named("androidContext"))

        val applicationContext = App.getInstance()

        private const val EXTRA_TITLE_OK = "button_ok"
        private const val EXTRA_TITLE_HEADING = "heading"
        private const val EXTRA_TITLE_CANCEL = "dialog_cancel"
        private const val EXTRA_TITLE_NEUTRAL = "button_neutral"
        private const val EXTRA_TYPE = "dialog_type"
        private const val EXTRA_MESSAGE = "dialog_message"
        private const val EXTRA_DOUBLE = "double_buttons"
        private const val EXTRA_NO_BUTTONS = "no_buttons"
        private const val EXTRA_IS_PICTURE_SELECTION = "is_picture_selection"
        private const val EXTRA_IS_FROM_PICTURE = "is_profile_picture"

        //Generic Dialog Types
        const val APP_ACTIVATED = "App Activated\nSuccessfully"
        const val ALERT = "Alert!"
        const val DELETE = "Delete"
        const val SUCCESS = "Completed Successfully!"
        const val FRAUD = "FRAUD"
        const val FEATURE_RESTRICTED = "featureRestricted"
        const val TAX_STATEMENT_SENT = "Tax Statement Sent"
        const val PORTFOLIO_STATEMENT_SENT = "Portfolio Statement Sent"
        const val ATTENTION = "Attention"
        const val DOWNLOAD_SUCCESSFUL = "Downloaded Successfully"
        const val INSTANT_QR_SIGNUP = "Instant QR Sign Up"
        const val COMING_SOON = "Coming Soon"
        const val SELECT_PIC_SOURCE = "Change Profile Picture"
        const val CARD_ADDED = ""
        const val QR_PAY = "QR Pay"
        const val DELETE_PAYEE = "Are you sure you want to delete this payee?"
        const val IN_PROCESS = "inProcess"


        // Callbacks
        fun get(
            msg: String,
            type: String = ALERT
        ): DialogBox {
            val bundle = Bundle().apply {
                putString(EXTRA_MESSAGE, msg)
                putString(EXTRA_TYPE, type)
                putBoolean(EXTRA_NO_BUTTONS, true)
            }
            return DialogBox().also { it.arguments = bundle }
        }

        fun ok(
            msg: String,
            type: String = ALERT,
            titleHeading: String = type,
            titleOK: String = "OK",
            actionOK: ((View) -> Unit)? = null,
            onClose: (() -> Unit)? = null,
        ): DialogBox {
            val bundle = Bundle().apply {
                putString(EXTRA_TITLE_HEADING, titleHeading)
                putString(EXTRA_TITLE_OK, titleOK)
                putString(EXTRA_TYPE, type)
                putString(EXTRA_MESSAGE, msg)
            }
            return DialogBox(
                onClose = onClose,
                actionOK = actionOK,
            ).also { it.arguments = bundle }
        }

        fun okCancel(
            type: String = ALERT,
            titleHeading: String = type,
            titleOK: String = if (type == SUCCESS) "Done" else "OK",
            titleCancel: String = "Cancel",
            msg: String = "",
            actionOK: ((View) -> Unit)? = null,
            actionCancel: ((View) -> Unit)? = null,
            onClose: (() -> Unit)? = null,
        ): DialogBox {
            val bundle = Bundle().apply {
                putString(EXTRA_TITLE_HEADING, titleHeading)
                putString(EXTRA_TITLE_OK, titleOK)
                putString(EXTRA_TITLE_CANCEL, titleCancel)
                putString(EXTRA_TYPE, type)
                putString(EXTRA_MESSAGE, msg)
                putBoolean(EXTRA_DOUBLE, true)
            }
            return DialogBox(
                onClose = onClose,
                actionOK = actionOK,
                actionCancel = actionCancel,
            ).also { it.arguments = bundle }
        }

        fun delete(
            title: String = "Are you sure you want\nto delete?",
            msg: String = "Once removed, it will be permanently deleted.",
            titleDeleteButton: String = "Delete",
            titleCancelButton: String = "Cancel",
            onCancel: ((View) -> Unit)? = null,
            onDelete: ((View) -> Unit)? = null,
        ): DialogBox {
            val bundle = Bundle().apply {
                putString(EXTRA_TYPE, DELETE)
                putString(EXTRA_TITLE_HEADING, title)
                putString(EXTRA_MESSAGE, msg)
                putString(EXTRA_TITLE_OK, titleDeleteButton)
                putString(EXTRA_TITLE_CANCEL, titleCancelButton)
                putBoolean(EXTRA_DOUBLE, true)
            }
            return DialogBox(
                actionOK = onDelete,
                actionCancel = onCancel,
            ).also { it.arguments = bundle }
        }

        fun getSelectPictureSourceDialog(
            isPicture: Boolean,
            titleHeading: String,
            actionOK: ((View) -> Unit),
            actionNeutral: ((View) -> Unit),
            actionCancel: ((View) -> Unit),
            onClose: (() -> Unit)? = null,
        ): DialogBox {
            val bundle = Bundle().apply {
                putString(
                    EXTRA_TITLE_OK,
                    applicationContext.getString(R.string.choose_from_photos)
                )
                putString(
                    EXTRA_TITLE_CANCEL,
                    applicationContext.getString(R.string.take_a_photo)
                )
                putString(
                    EXTRA_TITLE_NEUTRAL,
                    applicationContext.getString(R.string.remove_profile_picture)
                )
                putString(EXTRA_TITLE_HEADING, titleHeading)
                putString(EXTRA_TYPE, SELECT_PIC_SOURCE)
                putString(EXTRA_MESSAGE, "")
                putBoolean(EXTRA_IS_PICTURE_SELECTION, isPicture)
                putBoolean(EXTRA_IS_FROM_PICTURE, true)
            }
            return DialogBox(
                onClose = onClose,
                actionOK = actionOK,
                actionCancel = actionCancel,
                actionNeutral = actionNeutral,
            ).also { it.arguments = bundle }
        }
    }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            arguments?.let {
                val heading = it.getString(EXTRA_TITLE_HEADING)
                val noButtons = it.getBoolean(EXTRA_NO_BUTTONS)
                val type = it.getString(EXTRA_TYPE)
                val isOKCancel = it.getBoolean(EXTRA_DOUBLE)
                val isPicSelection = it.getBoolean(EXTRA_IS_PICTURE_SELECTION)
                val isFromPicture = it.getBoolean(EXTRA_IS_FROM_PICTURE)

                binding.apply {
                    tvTitle.text = heading
                    tvMsg.text = it.getString(EXTRA_MESSAGE)

                    if (onClose != null) {
                        btnClose.visible()
                        btnClose.setSingleClickListener {
                            onClose?.invoke()
                            dismiss()
                        }
                    } else {
                        btnClose.invisible()
                    }

                    ivAlert.apply {
                        when (type) {
                            TAX_STATEMENT_SENT,
                            PORTFOLIO_STATEMENT_SENT,
                            SUCCESS -> setImageResource(R.drawable.ic_dialog_success)

                            FRAUD -> setImageResource(R.drawable.ic_dialog_fraud)
                            FEATURE_RESTRICTED -> {
                                setImageResource(R.drawable.ic_dialog_alert)
                                btnOk.apply {
                                    setBackgroundTint(R.color.lochmara)
                                    setTextColor(
                                        ContextCompat.getColor(
                                            requireContext(),
                                            R.color.force_white
                                        )
                                    )
                                }
                            }

                            ALERT -> setImageResource(R.drawable.ic_dialog_alert)
                            IN_PROCESS -> setImageResource(R.drawable.ic_icon_in_process_card)
                            DELETE_PAYEE,
                            ATTENTION -> setImageResource(R.drawable.ic_dialog_attention)

                            DELETE -> {
                                setImageResource(R.drawable.ic_dialog_attention)
                                btnOk.apply {
                                    setBackgroundTint(R.color.coral)
                                    setTextColor(
                                        ContextCompat.getColor(
                                            requireContext(),
                                            R.color.force_white
                                        )
                                    )
                                }
                                btnCancel.apply {
                                    setBackgroundTint(R.color.catskill_white)
                                    setTextColor(
                                        ContextCompat.getColor(
                                            requireContext(),
                                            R.color.woodsmoke
                                        )
                                    )
                                }
                            }

                            DOWNLOAD_SUCCESSFUL -> setImageResource(R.drawable.ic_dialog_success)
                            CARD_ADDED -> setImageResource(R.drawable.ic_dialog_success)
                            COMING_SOON -> {
                                binding.apply {
                                    root.setBackgroundResource(R.drawable.bottom_dialogs_border_light_gray)
                                    ivAlert.visibility = View.GONE
                                    animationView.visibility = View.VISIBLE
                                    btnOk.text = getString(R.string.go_back)
                                }
                            }

                            INSTANT_QR_SIGNUP, QR_PAY -> {
                                setImageResource(R.drawable.ic_qr_circlebg)
                                binding.btnCancel.backgroundTintList =
                                    ColorStateList.valueOf(
                                        ContextCompat.getColor(
                                            requireContext(),
                                            R.color.catskill_white
                                        )
                                    )
                                binding.btnCancel.setTextColor(
                                    ContextCompat.getColor(
                                        requireContext(),
                                        R.color.woodsmoke
                                    )
                                )
                            }

                            SELECT_PIC_SOURCE -> {
                                setImageResource(R.drawable.ic_icon_action_sheet_camera_circle)
                            }
                        }
                    }

                if (noButtons) {
                    btnOk.visibility = View.GONE
                    mainContainer.setPadding(
                        0,
                        resources.getDimensionPixelSize(R.dimen.__30sdp),
                        0,
                        resources.getDimensionPixelSize(R.dimen.__70sdp)
                    )
                    return@let
                }

                btnOk.setSingleClickListener { view ->
                    actionOK?.invoke(view)
                    dismiss()
                }

                btnCancel.setSingleClickListener { view ->
                    actionCancel?.invoke(view)
                    dismiss()
                }

                btnNeutral.setSingleClickListener { view ->
                    actionNeutral?.invoke(view)
                    dismiss()
                }

                btnOk.text = it.getString(EXTRA_TITLE_OK)
                btnCancel.text = it.getString(EXTRA_TITLE_CANCEL)
                btnNeutral.text = it.getString(EXTRA_TITLE_NEUTRAL)

                if (isOKCancel) {
                    btnCancel.visibility = View.VISIBLE
                    btnCancel.text = it.getString(EXTRA_TITLE_CANCEL)
                }

                if (isFromPicture) {
                    btnNeutral.gone()
                    btnOk.visible()
                    btnCancel.visible()
                    btnCancel.setBackgroundTint(R.color.catskill_white)
                    btnCancel.setTextColor(applicationContext.getColor(R.color.woodsmoke))
                    btnCancel.typeface =
                        ResourcesCompat.getFont(requireContext(), R.font.aspira_medium)!!

                    tvMsg.gone()

                    if (isPicSelection) {
                        btnNeutral.visible()
                        btnNeutral.setBackgroundTint(R.color.coral)
                        btnNeutral.setTextColor(applicationContext.getColor(R.color.force_white))
                    }
                }
            }
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        onClose = null
        actionOK = null
        actionCancel = null
        actionNeutral = null

        super.onDismiss(dialog)
    }

}