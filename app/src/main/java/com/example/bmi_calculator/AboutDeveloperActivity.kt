package com.example.bmi_calculator

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.bmi_calculator.databinding.ActivityAboutDeveloperBinding

class AboutDeveloperActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityAboutDeveloperBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding = ActivityAboutDeveloperBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.linkGithub.setOnClickListener(this)
        binding.linkLinkdin.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        var temp= when(view!!.id){
            binding.linkGithub.id->{
                "https://github.com/ajaymaurya07"
            }
            else->{
                "https://www.linkedin.com/in/ajay-maurya-93589b200/"
            }
        }

        var intent=Intent(Intent.ACTION_VIEW,Uri.parse(temp))
        startActivity(intent)
    }
}