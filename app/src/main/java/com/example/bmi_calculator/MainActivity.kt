package com.example.bmi_calculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import java.math.RoundingMode
import java.text.DecimalFormat

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var calcuBmi:Button
    lateinit var fName:TextInputLayout
    lateinit var lName:TextInputLayout
    lateinit var txtDisplayVal:TextView
    lateinit var txtDisplayMsg:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("NumberGenerated", "Function has generated oncrete.");

        calcuBmi=findViewById(R.id.calculate_bmi)
        fName=findViewById(R.id.f_name)
        lName=findViewById(R.id.l_name)
        txtDisplayVal=findViewById(R.id.value)
        txtDisplayMsg=findViewById(R.id.value_msg)

        calcuBmi.setOnClickListener(this)


    }

    override fun onStart() {
        super.onStart()
        Log.i("NumberGenerated", "Function has generated onstart.");

    }

    override fun onResume() {

        super.onResume()
        Log.i("NumberGenerated", "Function has generated onresume.");

    }

    override fun onPause() {
        super.onPause()
        Log.i("NumberGenerated", "Function has generated onpause.");
    }

    override fun onStop() {
        super.onStop()
        Log.i("NumberGenerated", "Function has generated onstap.");

    }

    override fun onRestart() {
        super.onRestart()
        Log.i("NumberGenerated", "Function has generated onrestert.");
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("NumberGenerated", "Function has generated ondestroy.");
    }

    fun roundOffDecimal(number: Double): Double? {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.FLOOR
        return df.format(number).toDouble()
    }

    override fun onClick(view: View?) {
        if(view?.id==R.id.calculate_bmi){

            var first_val=fName.editText?.text.toString()

            var sec_val=lName.editText?.text.toString()

            if(first_val.isEmpty() && sec_val.isEmpty()){
                fName.error="required"
                lName.error="required"
            }
            else if(first_val.isEmpty() ){
                fName.error="required"
            }
            else if(sec_val.isEmpty()){
                lName.error="required"
            }
            else {
                var cal=(first_val.toDouble()/100)*(first_val.toDouble()/100)
                var result=sec_val.toDouble()/cal
                var res=roundOffDecimal(result)
                txtDisplayVal.visibility=View.VISIBLE
                txtDisplayVal.text="BMI Value is - $res"
                txtDisplayMsg.visibility=View.VISIBLE
                var txt= if(result <=18.5){
                    "severely underweight"
                }
                else if(result>18.5 && result<25){
                    "Normal (healthy weight)"
                }
                else{
                    "Overweight"
                }
                txtDisplayMsg.text=txt
            }
        }
    }
}