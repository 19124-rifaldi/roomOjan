package com.binar.challange22.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.binar.challange22.R
import com.binar.challange22.Student
import com.binar.challange22.databinding.ActivityEditBinding
import com.binar.challange22.room.StudentDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding
    var mDb: StudentDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mDb = StudentDatabase.getInstance(this)

        val objectStudent = intent.getParcelableExtra<Student>("student")

        binding.etNamaStudent.setText(objectStudent?.nama)
        binding.etEmailStudent.setText(objectStudent?.email)

        binding.btnSave.setOnClickListener {
            objectStudent?.nama = binding.etNamaStudent.text.toString()
            objectStudent?.email = binding.etEmailStudent.text.toString()

            GlobalScope.async {
                val result = mDb?.studentDao()?.updateStudent(objectStudent!!)

                runOnUiThread {
                    if(result!=0){
                        Toast.makeText(this@EditActivity,"Sukses mengubah ${objectStudent?.nama}", Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this@EditActivity,"Gagal mengubah ${objectStudent?.nama}", Toast.LENGTH_LONG).show()
                    }

                    finish()
                }
            }
        }

    }
}