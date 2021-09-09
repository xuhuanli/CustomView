package com.gome.customview.myviews

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.gome.customview.R
import kotlin.math.min

/**
 *  @author xuhuanli2017@gmail.com
 *
 */

class QQStepView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var mCurrentStep: Int = 0
    private var mStepMax: Int = 0
    private var mOuterColor: Int
    private var mInnerColor: Int
    private var mBorderWidth: Int
    private var mStepTextSize: Int
    private var mStepTextColor: Int
    private var mOutPaint: Paint
    private var mInnerPaint: Paint
    private var mTextPaint: Paint
    private val mRectF = RectF()
    private val mTextBounds = Rect()

    init {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.QQStepView)
        mOuterColor = ta.getColor(R.styleable.QQStepView_outerColor, Color.WHITE)
        mInnerColor = ta.getColor(R.styleable.QQStepView_innerColor, Color.WHITE)
        mStepTextColor = ta.getColor(R.styleable.QQStepView_stepTextColor, Color.WHITE)
        mBorderWidth =
            ta.getDimensionPixelSize(R.styleable.QQStepView_borderWidth, dp2px(context, 6))
        mStepTextSize =
            ta.getDimensionPixelSize(R.styleable.QQStepView_stepTextSize, sp2px(context, 16))
        ta.recycle()

        mOutPaint = Paint().apply {
            isAntiAlias = true
            strokeWidth = mBorderWidth.toFloat()
            color = mOuterColor
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
        }
        mInnerPaint = Paint().apply {
            isAntiAlias = true
            strokeWidth = mBorderWidth.toFloat()
            color = mInnerColor
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
        }
        mTextPaint = Paint().apply {
            isAntiAlias = true
            color = mStepTextColor
            textSize = mStepTextSize.toFloat()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val wMode = MeasureSpec.getMode(widthMeasureSpec)
        val hMode = MeasureSpec.getMode(heightMeasureSpec)
        var wSize = MeasureSpec.getSize(widthMeasureSpec)
        var hSize = MeasureSpec.getSize(heightMeasureSpec)
        if (wMode == MeasureSpec.AT_MOST) {
        }
        if (hMode == MeasureSpec.AT_MOST) {
        }
        val minSize = min(wSize, hSize)
        setMeasuredDimension(minSize, minSize)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 1. 画外圆弧
        val center = width / 2
        val r = center - mBorderWidth / 2
        // 需要算到内切圆环的正方形
        mRectF.set((center - r).toFloat(), (center - r).toFloat(), (center + r).toFloat(),
            (center + r).toFloat()
        )
        if (mStepMax == 0) return
        canvas.drawArc(mRectF, 135f, 270f, false, mOutPaint)
        // 2. 画内圆弧
        canvas.drawArc(mRectF, 135f, (mCurrentStep/mStepMax.toFloat()) * 270f, false, mInnerPaint)
        // 3. 画文字
        val stepText = mCurrentStep.toString()
        mTextPaint.getTextBounds(stepText, 0, stepText.length, mTextBounds)
        val xOffset = width/2 - mTextBounds.width()/2
        val fontMetrics = mTextPaint.fontMetrics
        val baseLine = height/2 + (fontMetrics.bottom - fontMetrics.top)/2
        canvas.drawText(stepText, xOffset.toFloat(), baseLine.toFloat(), mTextPaint)
    }

    fun setStepMax(stepMax: Int) {
        this.mStepMax = stepMax
    }

    fun setCurrentStep(currentStep: Int) {
        this.mCurrentStep = currentStep
        invalidate()
    }
}