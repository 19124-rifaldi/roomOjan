package com.binar.challange22.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.binar.challange22.R
import com.binar.challange22.Student
import com.binar.challange22.databinding.ActivityAddBinding
import com.binar.challange22.room.StudentDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class AddActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddBinding
    var mDb: StudentDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mDb = StudentDatabase.getInstance(this)

        binding.btnSave.setOnClickListener {
            val objectStudent = Student(
                null,
                binding.etNamaStudent.text.toString(),
                binding.etEmailStudent.text.toString()
            )

            GlobalScope.async {
                val result = mDb?.studentDao()?.insertStudent(objectStudent)
                runOnUiThread {
                    if(result != 0.toLong() ){
                        //sukses
                        Toast.makeText(this@AddActivity,"Sukses menambahkan ${objectStudent.nama}",
                            Toast.LENGTH_LONG).show()
                    }else{
                        //gagal
                        Toast.makeText(this@AddActivity,"Gagal menambahkan ${objectStudent.nama}",
                            Toast.LENGTH_LONG).show()
                    }
                    finish()
                }
            }
        }
    }
}