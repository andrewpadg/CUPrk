package com.example.cuprkv3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView


class LotAdapter(private val mContext: Context, private val layoutResId: Int, private val lotList: List<Lot>)
    : ArrayAdapter<Lot>(mContext, layoutResId, lotList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mContext)

        val view: View
        view = if (convertView == null) {
            layoutInflater.inflate(layoutResId, parent, false)
        } else {
            layoutInflater.inflate(layoutResId, null)
        }

        val lotName = view.findViewById<TextView>(R.id.lotName)
        val lotCapacity = view.findViewById<TextView>(R.id.lotCapacity)

        val lot = lotList[position]

        val capacity = "Open Spots: " + lot.capacity

        lotName.text = lot.name
        lotCapacity.text = capacity

        return view
    }
}