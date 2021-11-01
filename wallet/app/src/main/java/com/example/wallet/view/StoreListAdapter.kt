package com.example.wallet.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wallet.R
import com.example.wallet.model.ProductItem
import com.example.wallet.utils.Constant

class StoreListAdapter(context: Context) :
    BaseListAdapter<ProductItem, StoreListAdapter.Holder>(context) {
    var onItemClickListener: OnItemClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(getContext()).inflate(R.layout.product_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        bindContent(holder, getData(position))
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }

    private fun bindContent(holder: Holder, data: ProductItem?) {
        if (data != null) {
            holder.name.text =
                if (data.type == Constant.PRODUCT_TYPE_DAY) (data.day.toString() + " DAY PASS") else (data.hour.toString() + " HOUR PASS")
            holder.price.text = "Rp: " + data.price.toString()

            holder.buyBtn.setOnClickListener {
                onItemClickListener?.onClick(data)
            }
        }
    }

    override fun destroy() {
        super.destroy()
        onItemClickListener = null
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name = itemView.findViewById<TextView>(R.id.item_name)
        var price = itemView.findViewById<TextView>(R.id.item_price)
        var buyBtn = itemView.findViewById<Button>(R.id.btn_buy)
    }

    interface OnItemClickListener {
        fun onClick(item: ProductItem)
    }
}