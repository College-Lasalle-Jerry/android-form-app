package com.example.formapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PreviewActivity : AppCompatActivity() {

    private lateinit var textview_message: TextView;
    private lateinit var button_send_message: Button;

    private lateinit var message: Message
    private lateinit var messagePreviewText: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_preview)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        displayMessage()
        sendMessageButton()


//        val contactName = intent.getStringExtra("contactName")
//        val contactNumber = intent.getStringExtra("contactNumber")
//        val displayName = intent.getStringExtra("displayName")
//        val includeJunior = intent.getBooleanExtra("includeJunior", false)
//        val jobTitle = intent.getStringExtra("jobTitle")
//        val immediateStart = intent.getBooleanExtra("immediateStart", false)
//        val startDate = intent.getStringExtra("startDate")
//
//        val testString: String = "Contact name $contactName, Contact Number $contactNumber, Display Name $displayName, Include Junior $includeJunior, Job Title $jobTitle, Immediate Start $immediateStart, Start Date $startDate"


    }

    private fun displayMessage() {
        message = intent.getSerializableExtra("message", Message::class.java)!!

        messagePreviewText = """
                Hi ${message.contactName}, 
                My name is ${message.displayName} and I am ${message?.getFullJobDescription()}.
                I have a portfolio of apps to demonstrate my technical skills that I can show on request.
                I am able to start a new position ${message.getAvailablity()}.
                Please get in touch if you have any suitable roles for me.
                
                Thanks and best regards.
                
            """.trimIndent()

        textview_message = findViewById(R.id.textview_view_message)
        textview_message.text = messagePreviewText
    }

    private fun sendMessageButton() {
        button_send_message = findViewById(R.id.button_send_message)
        button_send_message.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("smsto: ${message.contactNumber}")  // Only SMS apps respond to this.
                putExtra("sms_body", messagePreviewText)
            }

            // equivalent to
            //   val intent = Intent(Intent.ACTION_SENDTO)
            // intent.data = Uri.parse("smsto:")
            // intent.putExtra("sms_body", message)
            // we use this {} to make it more readable as well as to minimize excessive coding.
            startActivity(intent)

        }

    }
}