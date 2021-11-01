package com.example.wallet.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wallet.R
import com.example.wallet.model.room.WalletEntity
import com.example.wallet.utils.Constant
import java.text.SimpleDateFormat

class WalletListAdapter(context: Context) : BaseListAdapter<WalletEntity, WalletListAdapter.Holder>(context) {
    var onItemClickListener: OnItemClickListener? = null
    private val mDateFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(getContext()).inflate(R.layout.wallet_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        bindContent(holder, getData(position))
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }

    private fun bindContent(holder: Holder, data: WalletEntity?) {
        if (data != null) {
            holder.name.text =
                if (data.type == Constant.PRODUCT_TYPE_DAY) (data.day.toString() + " DAY PASS") else (data.hour.toString() + " HOUR PASS")
            holder.insertDate.text = "Insertion date: "+ data?.let { formatDate(it.insertTime) }
            holder.activateDate.text = "Activate date: " + if(data?.activeTime == 0L) "" else formatDate(data?.activeTime)
            holder.expireDate.text = "Expire date: " + if (data?.expireTime == 0L) "" else formatDate(data?.expireTime)
            holder.activateBtn.text = if (data?.activeState) "Activated" else "Activate"
            if (!data?.activeState) {
                holder.activateBtn.isClickable = true
                holder.activateBtn.setOnClickListener {
                    onItemClickListener?.onClick(data)
                }
            } else {
                holder.activateBtn.isClickable = false
            }
        }
    }

    private fun formatDate(date: Long): String {
        return mDateFormatter.format(date)
    }

    override fun destroy() {
        super.destroy()
        onItemClickListener = null
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name = itemView.findViewById<TextView>(R.id.item_name)
        var insertDate = itemView.findViewById<TextView>(R.id.insert_date)
        var activateDate = itemView.findViewById<TextView>(R.id.activate_date)
        var expireDate = itemView.findViewById<TextView>(R.id.expire_date)
        var activateBtn = itemView.findViewById<Button>(R.id.btn_activate)
    }

    interface OnItemClickListener {
        fun onClick(item: WalletEntity)
    }
}