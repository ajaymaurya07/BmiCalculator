package com.example.bmi_calculator


import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.bmi_calculator.databinding.AboutBmiBinding

class SelectUnitActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    @SuppressLint("MissingInflatedId")
    lateinit var webbinding:AboutBmiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_unit)

        var list=ArrayList<String>()

        list.add("Select Unit..")
        list.add("Meter-Kilogram-Second(MKS) ")
        list.add("Foot-Pound-Second(FPS)")


        var spinner=findViewById<Spinner>(R.id.spinner)
        var adapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,list)
        spinner.adapter=adapter
        spinner.onItemSelectedListener=this



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.about_bmi->{

                webbinding= AboutBmiBinding.inflate(layoutInflater)
                setContentView(webbinding.root)
                webbinding.webView.loadUrl("https://en.wikipedia.org/wiki/Body_mass_index")

                var progressDialog= ProgressDialog(this@SelectUnitActivity)

                webbinding.webView.webViewClient=object : WebViewClient(){
                    override fun shouldOverrideUrlLoading(
                        view: WebView?,
                        request: WebResourceRequest?
                    ): Boolean {
                        return super.shouldOverrideUrlLoading(view, request)
                    }

                    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                        progressDialog.setMessage("loading.....")
                        progressDialog.show()
                        super.onPageStarted(view, url, favicon)
                    }

                    override fun onPageFinished(view: WebView?, url: String?) {
                        progressDialog.dismiss()
                        super.onPageFinished(view, url)
                    }

                }

            }
            R.id.bmi_chart->{
                var intent=Intent(this,BmiChartActivity::class.java)
                startActivity(intent)
            }
            R.id.about_developer->{
                var intent=Intent(this,AboutDeveloperActivity::class.java)
                startActivity(intent)
            }
            R.id.contact_us->{
                var intent=Intent(this,MainActivity2::class.java)
                startActivity(intent)
            }

        }

        return true
    }


    override fun onItemSelected(adapter: AdapterView<*>?, view: View?, position: Int, p3: Long) {
        when(position){
            1->{
                var intent=Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
            2->{
                var intent=Intent(this,MainActivityFps::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onBackPressed() {

        if (webbinding.webView.canGoBack()==true){
            webbinding.webView.goBack()
        }
        else{
            var builder= AlertDialog.Builder(this)
                .setTitle("Exit")
                .setMessage("Are u Sure to exit?")
                .setPositiveButton("yes", DialogInterface.OnClickListener { dialogInterface, i ->
                    finish()
                })
                .setNegativeButton("no", DialogInterface.OnClickListener { dialogInterface, i ->

                })
            builder.setCancelable(false)

            var dialog=builder.create()
            dialog.show()
            dialog.getButton(DialogInterface.BUTTON_POSITIVE).isAllCaps=false
            dialog.getButton(DialogInterface.BUTTON_NEGATIVE).isAllCaps=false

        }


    }

}