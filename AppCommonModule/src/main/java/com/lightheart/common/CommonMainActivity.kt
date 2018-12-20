package com.lightheart.common

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.lib_common_activity_main.*

class CommonMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lib_common_activity_main)
        test_view.setOnClickListener { Toast.makeText(this, "xixi", Toast.LENGTH_SHORT).show() }
    }
}
