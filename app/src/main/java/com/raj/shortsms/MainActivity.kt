package com.raj.shortsms

import android.Manifest
import android.Manifest.permission.*
import android.annotation.SuppressLint
import android.app.Application
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Telephony
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mApplication: Application


    lateinit var viwModl:SmsViewModel
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize a new linear layout manager
        var linearLayoutManager : LinearLayoutManager = LinearLayoutManager(
            this, // Context
            LinearLayout.VERTICAL, // Orientation
            false // Reverse layout
        )

        list.layoutManager=linearLayoutManager


        viwModl = ViewModelProvider(this).get(SmsViewModel::class.java)
        viwModl.opop.observe(this, androidx.lifecycle.Observer {
            println("DEBUG: $it")
            var a=it
            list.adapter=SmsAdapter(a);
        })


        btn.setOnClickListener {
          //  get(applicationContext)
            viwModl.getValSms(mApplication)
        }



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                ActivityCompat.checkSelfPermission(mApplication!!,READ_SMS
                ) != PackageManager.PERMISSION_GRANTED
        ) { // Needs permission

            ActivityCompat.requestPermissions(this, arrayOf(READ_SMS
            , SEND_SMS, RECEIVE_SMS), 123)

        } else { // Permission has already been granted

        }




    }


}

class Conversation(val number: String, val message: List<Message>)
class Message(val number: String, val body: String, val date: Date)