package com.example.bmi_calculator

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.bmi_calculator.ViewModel.MainActivityViewModel
import com.example.bmi_calculator.databinding.ActivityMainFpsBinding


class MainActivityFps : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityMainFpsBinding
    lateinit var viewModel:MainActivityViewModel
    var flag=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main_fps)

        viewModel= ViewModelProvider(this)[MainActivityViewModel::class.java]

        viewModel.resData.observe(this, Observer {
            binding.value.setText(it.toString())
        })
        viewModel.restext.observe(this, Observer {
            binding.valueMsg.setText(it)
        })


        binding.calculateBmi.setOnClickListener(this)
        binding.fName.addTextChangedListener {
            binding.fName.error=null
        }
        binding.lName!!.addTextChangedListener {
            binding.lName.error=null
        }

    }

    fun hidekeyboard(view:View){
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view!!.windowToken, 0)
    }

    override fun onClick(it: View?) {
        var first_val = binding.fName.text.toString()

        var sec_val = binding.lName.text.toString()

        if (it?.id == R.id.calculate_bmi && flag==0) {

            if (first_val.isEmpty() && sec_val.isEmpty()) {
                binding.fName.error = "required"
                binding.lName.error = "required"
            } else if (first_val.isEmpty()) {
                binding.fName.error = "required"
            } else if (sec_val.isEmpty()) {
                binding.lName.error = "required"
            } else {
                viewModel.bmiFPSUnitRes(binding.fName.text.toString().toInt(),binding.lName.text.toString().toInt())
                binding.value.visibility = View.VISIBLE
                binding.valueMsg.visibility = View.VISIBLE
//
                viewModel.checkstatus()
                flag=1
                binding.calculateBmi.setText("reset")
            }
        }


        else if (it?.id == R.id.calculate_bmi && flag==1){
            binding.fName.text.clear()
            binding.lName.text.clear()
                binding.value.visibility= View.GONE
                binding.valueMsg.visibility= View.GONE
            flag=0



            binding.calculateBmi.setText("Calculate BMI")

        }
        hidekeyboard(it!!)

    }



}

