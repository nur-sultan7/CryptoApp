package com.nursultan.cryptoapp.presentation.utils

import android.animation.ValueAnimator
import android.view.View
import android.view.ViewGroup

abstract class ViewResizeAnimator {
    companion object {
        fun changeHeight(view: View, startHeight: Int, endHeight: Int, duration: Int) {
            val rvAnimator = ValueAnimator.ofInt(
                startHeight,
                endHeight
            )
                .setDuration(duration.toLong())
            rvAnimator.addUpdateListener {
                view.layoutParams.height = it.animatedValue as Int
            }
            rvAnimator.start()
        }

        fun changeWidth(view: View, startWidth: Int, endWidth: Int, duration: Int) {
            val rvAnimator = ValueAnimator.ofInt(
                startWidth,
                endWidth
            )
                .setDuration(duration.toLong())
            rvAnimator.addUpdateListener {
                view.layoutParams.width = it.animatedValue as Int
            }
            rvAnimator.start()
        }
    }
}