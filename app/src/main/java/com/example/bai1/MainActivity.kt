package com.example.bai1

import android.os.  Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.*
import android.view.View


class MainActivity : AppCompatActivity() {
    private lateinit var edtNumber: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var rbEven: RadioButton
    private lateinit var rbOdd: RadioButton
    private lateinit var rbSquare: RadioButton
    private lateinit var btnShow: Button
    private lateinit var listView: ListView
    private lateinit var tvError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ánh xạ các view
        edtNumber = findViewById(R.id.edtNumber)
        radioGroup = findViewById(R.id.radioGroup)
        rbEven = findViewById(R.id.rbEven)
        rbOdd = findViewById(R.id.rbOdd)
        rbSquare = findViewById(R.id.rbSquare)
        btnShow = findViewById(R.id.btnShow)
        listView = findViewById(R.id.listView)
        tvError = findViewById(R.id.tvError)

        // Xử lý sự kiện khi nhấn nút Show
        btnShow.setOnClickListener {
            val input = edtNumber.text.toString()

            // Kiểm tra dữ liệu nhập vào
            if (input.isEmpty() || !input.matches("\\d+".toRegex())) {
                tvError.text = "Please enter a valid positive integer."
                tvError.visibility = View.VISIBLE
                listView.adapter = null
                return@setOnClickListener
            }

            val n = input.toInt()
            val numbers = mutableListOf<String>()

            // Kiểm tra loại số được chọn
            when (radioGroup.checkedRadioButtonId) {
                R.id.rbEven -> {
                    for (i in 0..n step 2) {
                        numbers.add(i.toString())
                    }
                }

                R.id.rbOdd -> {
                    for (i in 1..n step 2) {
                        numbers.add(i.toString())
                    }
                }

                R.id.rbSquare -> {
                    var i = 0
                    while (i * i <= n) {
                        numbers.add((i * i).toString())
                        i++
                    }
                }
            }

            // Hiển thị kết quả lên ListView
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numbers)
            listView.adapter = adapter

            // Ẩn thông báo lỗi
            tvError.visibility = View.GONE
        }
    }
}