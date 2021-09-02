package com.example.nasaappproject

import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.photo_cell.view.*
import java.util.*
import kotlin.collections.ArrayList


class MarsAdapter(var photos: ArrayList<Photo>, val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    var itemList = ArrayList<Photo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.photo_cell, parent, false)
        itemList = photos
        return PhotoHolder(v)}

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)  {
        when(holder){
            is PhotoHolder ->{
                holder.bind(photos[position])
            }
        }


        holder.itemView.setOnClickListener{
            val model = photos[position]
            val gDate: String = model.earth_date
            val gCamera: String = model.camera.name
            val gRover: String = model.rover.name
            val gStatus: String = model.rover.status
            val gLaunch: String = model.rover.launch_date
            val gLanding: String = model.rover.landing_date
            val gImageView: String = photos[position].img_src.replace("http","https")

            val intent = Intent(context, CardDetails::class.java)


            intent.putExtra("Date", gDate)
            intent.putExtra("Camera", gCamera)
            intent.putExtra("Rover", gRover)
            intent.putExtra("Status", gStatus)
            intent.putExtra("Launch", gLaunch)
            intent.putExtra("Landing", gLanding)
            intent.putExtra("URL", gImageView)

            context.startActivity(intent)

        }

    }

    class PhotoHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind(photo : Photo){
            val image = photo.img_src.replace("http","https")
            Glide.with(itemView.context).load(image).into(itemView.roverImage)

            itemView.earthDate.text = photo.earth_date
            itemView.roverName.text = photo.rover.name
            itemView.cameraName.text = photo.camera.name

        }
    }


    override fun getFilter(): Filter {
        return object: Filter(){
            override fun performFiltering(p0: CharSequence?): FilterResults {
                var filterResults = FilterResults()
                if (p0 == null || p0.isEmpty()){
                    filterResults.count = photos.size
                    filterResults.values = photos
                }
                else{
                    var searchChr: String = p0.toString().lowercase(Locale.getDefault())
                    var photoModal = ArrayList<Photo>()
                    for (items in photos){
                        if (items.camera.name.lowercase(Locale.getDefault()).contains(searchChr)){
                            photoModal.add(items)
                        }

                    }

                    filterResults.count = photoModal.size
                    filterResults.values = photoModal
                }
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {

                if (p0 == null || p0.isEmpty()){
                    photos = itemList
                    notifyDataSetChanged()
                }
                else{
                    photos = p1!!.values as ArrayList<Photo>
                    notifyDataSetChanged()
                }

            }

        }
    }

}