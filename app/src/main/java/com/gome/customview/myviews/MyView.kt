package com.gome.customview.myviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import com.gome.customview.R

/**
 *  @author xuhuanli2017@gmail.com
 *
 */

//class MyView :
//    TextInputEditText {
//    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
//        context,
//        attrs,
//        defStyleAttr
//    )
//    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
//    constructor(context: Context) : super(context)
//}

//class MyView @JvmOverloads constructor(
//    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
//) : View(context, attrs, defStyleAttr) {
//}

class MyTextView :
    View {
    private val TAG = "MyTextView"

    private var mLabel: String
    private var mLabelColor: Int = Color.BLACK
    private var mMaxLength = Int.MAX_VALUE
    private var mTextSize: Int = sp2px(context, 15)
    private var mPaint: Paint

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.MyTextView)
        mLabel = ta.getString(R.styleable.MyTextView_label) ?: ""
        mLabelColor = ta.getColor(R.styleable.MyTextView_mock_labelColor, Color.BLACK)
        mMaxLength = ta.getInt(R.styleable.MyTextView_label_max_length, Int.MAX_VALUE)
        mTextSize = ta.getDimensionPixelSize(R.styleable.MyTextView_label_size, sp2px(context, 15))
        ta.recycle()

        mPaint = Paint().apply {
            isAntiAlias = true
            textSize = mTextSize.toFloat()
            color = mLabelColor
        }
    }


    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context) : this(context, null)

    private val bounds: Rect = Rect()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        var widthSize = MeasureSpec.getSize(widthMeasureSpec)
        var heightSize = MeasureSpec.getSize(heightMeasureSpec)
        Log.d(TAG, "onMeasure: withMode: $widthMode heightMode: $heightMode")
        if (widthMode == MeasureSpec.AT_MOST) {
            // wrap_content??????????????? ??????????????????
            mPaint.getTextBounds(mLabel, 0, mLabel.length, bounds)
            widthSize = bounds.width() + paddingLeft + paddingRight
        }
        if (heightMode == MeasureSpec.AT_MOST) {
            // wrap_content??????????????? ??????????????????
            mPaint.getTextBounds(mLabel, 0, mLabel.length, bounds)
            heightSize = bounds.height() + paddingTop + paddingBottom
        }
        setMeasuredDimension(widthSize, heightSize)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val fontMetrics = mPaint.fontMetrics
        // ???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????baseline??????????????????fontMetrics.bottom/fontMetrics.top???????????????????????????baseline???
        fontMetrics.top
        fontMetrics.bottom
        fontMetrics.ascent
        fontMetrics.descent
        fontMetrics.descent
        val dis = fontMetrics.bottom - fontMetrics.top
        val h = height
        val baseline =
            height / 2 + ((fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom)
        // drawText?????????????????????padding
        canvas.drawText(mLabel, paddingLeft.toFloat(), baseline, mPaint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.d(TAG, "onTouchEvent: ??????")
            }
            MotionEvent.ACTION_UP -> {
                Log.d(TAG, "onTouchEvent: ??????")
            }
            MotionEvent.ACTION_MOVE -> {
                Log.d(TAG, "onTouchEvent: ??????")
            }
            else -> {
                Log.d(TAG, "onTouchEvent: ??????")
            }
        }

        invalidate()
        return super.onTouchEvent(event)
    }

}