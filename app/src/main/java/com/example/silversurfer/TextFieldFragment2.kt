package com.example.silversurfer

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"



/**
 * A simple [Fragment] subclass.
 * Use the [TextFieldFragment2.newInstance] factory method to
 * create an instance of this fragment.
 */
class TextFieldFragment2 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var editTextPassword1: EditText
    private lateinit var textViewPasswordError1: TextView
    private lateinit var textViewPasswordError2: TextView
    private lateinit var textViewPasswordError3: TextView
    private lateinit var editTextPassword2: EditText
    private lateinit var textViewPasswordError4: TextView
    private lateinit var buttonSubmit: Button

    private var isChecked = false // 비밀번호 입력 여부를 나타내는 변수

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_text_field2, container, false)
        editTextPassword1 = view.findViewById(R.id.editText_Password1)
        textViewPasswordError1 = view.findViewById(R.id.textView_Password1)
        textViewPasswordError2 = view.findViewById(R.id.textView_Password2)
        textViewPasswordError3 = view.findViewById(R.id.textView_Password3)
        editTextPassword2 = view.findViewById(R.id.editText_Password2)
        textViewPasswordError4 = view.findViewById(R.id.textView_Password2_1)
        buttonSubmit = view.findViewById(R.id.button_Submit)

        // 비밀번호를 입력한 경우
        editTextPassword1.addTextChangedListener {
            val password = editTextPassword1.text.toString()
            val engPattern = ".*[a-zA-Z].*".toRegex()
            val numPattern = ".*[0-9].*".toRegex()

            var isEng = false;
            var isNum = false;
            var isNotShort = false;

            // 영문 포함 검사
            isEng = if (password.matches(engPattern)) {
                textViewPasswordError1.setTextColor(ContextCompat.getColor(view.context, R.color.main_blue))
                textViewPasswordError1.setTypeface(null, Typeface.BOLD)
                true;
            } else {
                textViewPasswordError1.setTextColor(ContextCompat.getColor(view.context, R.color.text_dark))
                textViewPasswordError1.setTypeface(null, Typeface.NORMAL)
                false;
            }

            // 숫자 포함 검사
            isNum = if (password.matches(numPattern)) {
                textViewPasswordError2.setTextColor(ContextCompat.getColor(view.context, R.color.main_blue))
                textViewPasswordError2.setTypeface(null, Typeface.BOLD)
                true;
            } else {
                textViewPasswordError2.setTextColor(ContextCompat.getColor(view.context, R.color.text_dark))
                textViewPasswordError2.setTypeface(null, Typeface.NORMAL)
                false;
            }

            // 길이 검사
            isNotShort = if (password.length >= 8) {
                textViewPasswordError3.setTextColor(ContextCompat.getColor(view.context, R.color.main_blue))
                textViewPasswordError3.setTypeface(null, Typeface.BOLD)
                true;
            } else {
                textViewPasswordError3.setTextColor(ContextCompat.getColor(view.context, R.color.text_dark))
                textViewPasswordError3.setTypeface(null, Typeface.NORMAL)
                false;
            }

            isChecked = isEng && isNum && isNotShort
        }

        // 비밀번호를 재입력한 경우
        editTextPassword2.addTextChangedListener {
            val password2 = editTextPassword2.text.toString()

            if (isChecked && password2 == editTextPassword1.text.toString()) {
                // 안내 문구 강조
                textViewPasswordError4.setTextColor(ContextCompat.getColor(view.context, R.color.main_blue))
                textViewPasswordError4.setTypeface(null, Typeface.BOLD)

                // '다음' 버튼 강조
                buttonSubmit.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(view.context, R.color.main_blue))
                buttonSubmit.isEnabled = true
            } else {
                // 안내 문구 강조
                textViewPasswordError4.setTextColor(ContextCompat.getColor(view.context, R.color.text_dark))
                textViewPasswordError4.setTypeface(null, Typeface.NORMAL)

                // '다음' 버튼 접근 불가하도록 설정
                buttonSubmit.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(view.context, R.color.button_dark))
                buttonSubmit.isEnabled = false
            }
        }

        return view
    }

    private fun setLayout(editText: EditText, textView: TextView, text: String) {
        // 1. 해당 입력창 강조
        editText.backgroundTintList = ColorStateList.valueOf(Color.RED)
        // 2. 안내 문구 출력
        textView.text = text
        textView.setTextColor(Color.RED)
        textView.visibility = View.VISIBLE
        // 3. focus 이동
        editText.isFocusableInTouchMode = true
        editText.requestFocus()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TextFieldFragment2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TextFieldFragment2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}