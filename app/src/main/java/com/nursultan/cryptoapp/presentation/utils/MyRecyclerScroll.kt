package com.nursultan.cryptoapp.presentation.utils

import androidx.recyclerview.widget.RecyclerView

abstract class MyRecyclerScroll : RecyclerView.OnScrollListener() {
    var scrollDist = 0
    var isVisible = true
    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (isVisible && scrollDist > MINIMUM) {
            hide();
            scrollDist = 0;
            isVisible = false;
        } else if (!isVisible && scrollDist < -MINIMUM) {
            show();
            scrollDist = 0;
            isVisible = true;
        }

        if ((isVisible && dy > 0) || (!isVisible && dy < 0)) {
            scrollDist += dy;
        }
    }

    companion object {
        const val MINIMUM = 120
    }

    abstract fun hide()
    abstract fun show()
}