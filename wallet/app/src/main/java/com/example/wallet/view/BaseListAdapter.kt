package com.example.wallet.view

import android.content.Context
import androidx.recyclerview.widget.RecyclerView

abstract class BaseListAdapter<DATA, VIEW : RecyclerView.ViewHolder> : RecyclerView.Adapter<VIEW> {

    private var mContext: Context?
    private var mDataList: ArrayList<DATA>

    constructor(context: Context) {
        mContext = context
        mDataList = ArrayList()
    }

    fun getContext(): Context? {
        return mContext
    }

    fun getData(position: Int): DATA? {
        return if (mDataList.size > position){
            mDataList[position]
        } else {
            null
        }
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    open fun updateDataList(dataList: ArrayList<DATA>?) {
        mDataList.clear()
        dataList?.let {
            mDataList.addAll(it)
        }
        notifyDataSetChanged()
    }

    fun updateDataListWithoutNotify(dataList: ArrayList<DATA>?) {
        dataList?.let { mDataList.addAll(it) }
    }

    open fun destroy() {
        mDataList.clear()
        mContext = null
    }
}