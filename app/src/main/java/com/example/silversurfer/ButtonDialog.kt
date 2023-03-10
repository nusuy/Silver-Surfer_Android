package com.example.silversurfer

import android.app.Dialog
import android.content.Context
import android.view.WindowManager

class ButtonDialog(context: Context) {
    private val dialog = Dialog(context)

    fun myDig() {
        // Set Custom Dialog Layout
        dialog.setContentView(R.layout.button_sample)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        // Set Brightness outside the dialog -80%
        dialog.window!!.setDimAmount(0.8f)

        dialog.show()
    }
}