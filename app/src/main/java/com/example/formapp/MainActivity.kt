package com.example.formapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private var contactNameEditText: TextInputEditText? = null
    private var contactNumberEditText: TextInputEditText? = null
    private var displayNameEditText: TextInputEditText? = null
    private var startDateEditText: TextInputEditText? = null
    private var juniorCheckBox: CheckBox? = null
    private var immediateStartCheckBox: CheckBox? = null
    private var jobTitleSpinner: Spinner? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }


        contactNameEditText = findViewById(R.id.edit_text_name)
        contactNumberEditText = findViewById(R.id.edit_text_number)
        displayNameEditText = findViewById(R.id.edit_text_display_name)
        startDateEditText = findViewById(R.id.edit_text_start_date)
        juniorCheckBox = findViewById(R.id.check_box_junior)
        immediateStartCheckBox = findViewById(R.id.check_box_imm_start)
        jobTitleSpinner = findViewById(R.id.spinner_job_title)
        val spinnerValues: Array<String> = arrayOf(
            "Android Developer",
            "Backend Developer",
            "Frontend Developer",
            "Mobile Engineer"
        )

        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerValues)
        jobTitleSpinner?.adapter = spinnerAdapter
        val previewButton: Button = findViewById(R.id.button_preview)

        previewButton.setOnClickListener {
            onPreviewClicked()
        }


    }

    private fun onPreviewClicked() {
        // ? may be null
        // !! will not be null

        val contactName = contactNameEditText?.text.toString()
        val contactNumber = contactNumberEditText?.text.toString()
        val displayName = displayNameEditText?.text.toString()
        val includeJunior = juniorCheckBox?.isChecked
        val jobTitle: String = jobTitleSpinner?.selectedItem.toString()
        val immediateStart = immediateStartCheckBox?.isChecked
        val startDate = startDateEditText?.text.toString()

        val message = Message(contactName, contactNumber, displayName, includeJunior!!, jobTitle, immediateStart!!, startDate)

//        Toast.makeText(this, testString, Toast.LENGTH_SHORT).show()

        val previewActivityIntent = Intent(this, PreviewActivity::class.java)
//        previewActivityIntent.putExtra("contactName", contactName)
//        previewActivityIntent.putExtra("contactNumber", contactNumber)
//        previewActivityIntent.putExtra("displayName", displayName)
//        previewActivityIntent.putExtra("includeJunior", includeJunior)
//        previewActivityIntent.putExtra("jobTitle", jobTitle)
//        previewActivityIntent.putExtra("immediateStart", immediateStart)
//        previewActivityIntent.putExtra("startDate", startDate)
        previewActivityIntent.putExtra("message", message)
        startActivity(previewActivityIntent)
    }
}
