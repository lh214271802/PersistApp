package com.lightheart.persistapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.lightheart.sdklib.IBaseUrl
import com.lightheart.sdklib.RetrofitManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hello.setOnClickListener {
            Toast.makeText(this, "xixi", Toast.LENGTH_SHORT).show()
            RetrofitManager.getInstance { "https://wwww.baidu.com" }.retrofit

        }
    }
}
