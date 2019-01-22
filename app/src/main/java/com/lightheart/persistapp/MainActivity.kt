package com.lightheart.persistapp

import android.animation.Animator
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hello.setOnClickListener {
            Toast.makeText(this, "xixi", Toast.LENGTH_SHORT).show()
        }
        test_view.animate()
                .translationX(300F)
                .setDuration(500L)
                .setListener(object : Animator.AnimatorListener {
                    override fun onAnimationRepeat(animation: Animator?) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onAnimationEnd(animation: Animator?) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onAnimationCancel(animation: Animator?) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onAnimationStart(animation: Animator?) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }
                })
                .setUpdateListener { }
                .withStartAction({})
                .withEndAction({})
                .start();
    }
}
