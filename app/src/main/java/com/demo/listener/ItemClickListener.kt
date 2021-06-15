package com.demo.listener

import android.view.View


interface ItemClickListener {
    fun setClickedInfo(data: Any?)
    fun setClickedInfo(position:Int, view: View?, obj:Any){
    }
}