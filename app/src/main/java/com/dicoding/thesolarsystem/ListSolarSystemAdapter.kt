package com.dicoding.thesolarsystem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ListSolarSystemAdapter(private val listSolarSystems: ArrayList<SolarSystem>) :
    RecyclerView.Adapter<ListSolarSystemAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row_solar_system, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val solarSystem = listSolarSystems[position]
        Glide.with(holder.itemView.context)
            .load(solarSystem.photo)
            .apply(RequestOptions().override(65, 65))
            .into(holder.imgPhoto)
        holder.tvName.text = solarSystem.name
        holder.tvDetail.text = solarSystem.detail
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listSolarSystems[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int {
        return listSolarSystems.size
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvDetail: TextView = itemView.findViewById(R.id.tv_item_detail)
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: SolarSystem)
    }

}