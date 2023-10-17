package com.example.bmi_calculator.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.math.RoundingMode
import java.text.DecimalFormat

class MainActivityViewModel:ViewModel() {

    var result: Double=0.00
    var resData=MutableLiveData<Double>()
    var restext=MutableLiveData<String>()

    fun bmiMksUnitRes(height:Int,weight:Int){
        var cal = (height.toDouble() / 100) * (height.toDouble() / 100)
        result = weight.toDouble() / cal
        var res = roundOffDecimal(result)
        resData.value=res
    }

    fun bmiFPSUnitRes(height:Int,weight:Int){
        var cal = (height.toDouble() ) * (height.toDouble() )
        result = (weight.toDouble() / cal )* 703
        var res = roundOffDecimal(result)
        resData.value=res
    }


    fun checkstatus(){
        var txt = if (result <= 18.5) {
            "severely underweight"
        } else if (result > 18.5 && result < 25) {
            "Normal (healthy weight)"
        } else {
            "Overweight"
        }
        restext.value=txt
    }


    fun roundOffDecimal(number: Double): Double? {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.FLOOR
        return df.format(number).toDouble()
    }


}