package com.imgur.gallery.image.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.imgur.gallery.R
import com.imgur.gallery.databinding.ItemGridImageBinding
import com.imgur.gallery.databinding.ItemListImageBinding
import com.imgur.gallery.image.model.datamodel.Album
import com.imgur.gallery.image.model.enums.LayoutToggle
import com.imgur.gallery.image.model.enums.LayoutToggle.LIST
import com.imgur.gallery.utils.imagePlaceHolder
import com.imgur.gallery.utils.toDate
import com.imgur.gallery.utils.toGridThumbNailLink
import com.imgur.gallery.utils.toListThumbNailLink

/** The class sets the Album list paging data with the recyclerview to bind the data with the UI **/
class ImageAdapter(private val context: Context, private val selectedLayoutToggle: LayoutToggle) :
    PagingDataAdapter<Album, RecyclerView.ViewHolder>(ImageDiffCallback()) {

    /** The function is used to create the ViewHolder based on the layoutToggle selected by the user **/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (selectedLayoutToggle == LIST) ImageListViewHolder.from(
            parent,
            context
        ) else ImageGridViewHolder.from(parent, context)


    /** The function is used to bind the items with the ViewHolder based on the layoutToggle selected by the user **/
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ImageListViewHolder) {
            getItem(position)?.let { holder.bind(it) }
        }

        if (holder is ImageGridViewHolder) {
            getItem(position)?.let { holder.bind(it) }
        }
    }

    /** This class uses the album item and the position to update the UI and assign value to the view when List is selected **/
    class ImageListViewHolder(
        private val binding: ItemListImageBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(album: Album) {
            val image = album.images[0]

            Glide.with(context)
                .load(image.link)
                .centerCrop()
                .placeholder(context.imagePlaceHolder())
                .transition(DrawableTransitionOptions.withCrossFade())
                .thumbnail(
                    Glide.with(context)
                        .load(image.link.toListThumbNailLink())
                        .centerCrop()
                )
                .error(R.drawable.ic_image_error)
                .into(binding.ivImage)

            binding.tvTitle.text = album.title

            binding.tvDate.text = image.datetime.toDate()

            binding.tvImagesCount.text = album.imagesCount.toString()
        }

        companion object {
            fun from(
                parent: ViewGroup,
                context: Context
            ): ImageListViewHolder =
                ImageListViewHolder(
                    ItemListImageBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ), context
                )
        }

    }

    /** This class uses the album item and the position to update the UI and assign value to the view when Grid is selected **/
    class ImageGridViewHolder(
        private val binding: ItemGridImageBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(album: Album) {
            val image = album.images[0]

            Glide.with(context)
                .load(image.link)
                .centerCrop()
                .placeholder(context.imagePlaceHolder())
                .transition(DrawableTransitionOptions.withCrossFade())
                .thumbnail(
                    Glide.with(context)
                        .load(image.link.toGridThumbNailLink())
                        .centerCrop()
                )
                .error(R.drawable.ic_image_error)
                .into(binding.ivImage)

            binding.tvTitle.text = album.title

            binding.tvDate.text = image.datetime.toDate()

            binding.tvImagesCount.text = album.imagesCount.toString()
        }

        companion object {
            fun from(
                parent: ViewGroup,
                context: Context
            ): ImageGridViewHolder =
                ImageGridViewHolder(
                    ItemGridImageBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ), context
                )
        }

    }

    /** This class is used to calculate the changes in the Album items using DiffUtil **/
    class ImageDiffCallback : DiffUtil.ItemCallback<Album>() {
        override fun areItemsTheSame(oldItem: Album, newItem: Album) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Album, newItem: Album) =
            oldItem == newItem
    }

}