package com.cherish.technologyassessment.view.ui.home

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.bumptech.glide.signature.ObjectKey
import com.cherish.technologyassessment.R
import com.cherish.technologyassessment.databinding.PopularItemsLayoutBinding
import com.cherish.technologyassessment.utils.LoadImageWithGlide
import de.hdodenhof.circleimageview.CircleImageView

class PopularArticleAdapter(val callback: (Int) -> Unit) : ListAdapter<PopularArticleData, PopularArticleAdapter.PopularArticleViewHolder>(PojoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularArticleViewHolder {
        val binding = PopularItemsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularArticleViewHolder(binding, callback)
    }

    override fun onBindViewHolder(holder: PopularArticleViewHolder, position: Int) {
        val popularArticle = getItem(position)
        with(holder){
            binding.title.text = popularArticle.title
            binding.author.text = popularArticle.author
            binding.date.text = popularArticle.date
           LoadImageWithGlide.loadImage(binding.image,popularArticle!!.image)
        }
    }

    fun getData(int: Int): PopularArticleData {
        return getItem(int)
    }

   inner class PopularArticleViewHolder(val binding: PopularItemsLayoutBinding,callback: (Int) -> Unit ) : RecyclerView.ViewHolder(binding.root){
        init {
            binding.root.setOnClickListener{
                callback(adapterPosition)
            }
        }

   }
    class PojoDiffCallback : DiffUtil.ItemCallback<PopularArticleData>() {
        override fun areItemsTheSame(
            oldItem: PopularArticleData,
            newItem: PopularArticleData
        ): Boolean {
            return oldItem.title == newItem.title
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: PopularArticleData,
            newItem: PopularArticleData
        ): Boolean {
            return oldItem == newItem
        }
    }



}