package com.gome.customview

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.DecelerateInterpolator
import com.gome.customview.myviews.QQStepView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val qqStepView = findViewById<QQStepView>(R.id.qq_sv)
        qqStepView.setStepMax(4000)
        ObjectAnimator.ofInt(0, 3000).apply {
            duration = 1000
            interpolator = DecelerateInterpolator()
            addUpdateListener {
                val value = it.animatedValue as Int
                qqStepView.setCurrentStep(value)
            }
            start()
        }
    }
}