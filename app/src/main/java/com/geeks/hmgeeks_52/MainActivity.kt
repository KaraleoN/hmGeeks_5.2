package com.geeks.hmgeeks_52

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var etFirstName: EditText
    private lateinit var etSecondName: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etFirstName = findViewById(R.id.etFirstName)
        etSecondName = findViewById(R.id.etSecondName)
        btnCalculate = findViewById(R.id.btnCalculate)
        tvResult = findViewById(R.id.tvResult)

        btnCalculate.setOnClickListener {
            val firstName = etFirstName.text.toString()
            val secondName = etSecondName.text.toString()

            if (firstName.isNotEmpty() && secondName.isNotEmpty()) {
                fetchLovePercentage(firstName, secondName)
            } else {
                Toast.makeText(this, "Please enter both names", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun fetchLovePercentage(firstName: String, secondName: String) {
        RetrofitInstance.api.getLovePercentage(firstName, secondName)
            .enqueue(object : Callback<LoveResult> {
                override fun onResponse(call: Call<LoveResult>, response: Response<LoveResult>) {
                    if (response.isSuccessful) {
                        val result = response.body()
                        tvResult.text = "${result?.fname} ❤ ${result?.sname} = ${result?.percentage}%\n${result?.result}"
                    } else {
                        tvResult.text = "Failed to get result"
                    }
                }

                override fun onFailure(call: Call<LoveResult>, t: Throwable) {
                    tvResult.text = "Error: ${t.message}"
                }
            })
    }
}