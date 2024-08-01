package com.ubldigital.shared.base.bottomsheets

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import android.widget.FrameLayout
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.techinnovation.mainfragment.R
import com.techinnovation.mainfragment.extension.calculateDuration
import com.ubldigital.shared.base.fragment.Inflate

abstract class BaseBottomSheetDialogFragment<VB : ViewBinding>(
    private val inflate: Inflate<VB>
) : BottomSheetDialogFragment() {

    override fun getTheme() = R.style.DialogBox

    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (dialog != null && dialog?.window != null)
//            dialog?.window?.attributes?.windowAnimations = R.style.DialogBoxAnim
            isCancelable = false
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        bottomSheetDialog.setOnShowListener {
            val bottomSheet =
                bottomSheetDialog.findViewById<FrameLayout>(
                    com.google.android.material.R.id.design_bottom_sheet
                )
            val behavior = BottomSheetBehavior.from(bottomSheet!!)
            behavior.skipCollapsed = true
            behavior.isDraggable = false
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
        return bottomSheetDialog
    }

    override fun getEnterTransition(): Any {
        val enterAnim = TranslateAnimation(
            0f,
            0f,
            50f,
            0f
        )
        enterAnim.duration = calculateDuration(400L)
        return enterAnim
    }

    override fun getExitTransition(): Any {
        val exitAnim = TranslateAnimation(
            0f,
            0f,
            0f,
            100f
        )
        exitAnim.duration = calculateDuration(400L)
        return exitAnim
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
