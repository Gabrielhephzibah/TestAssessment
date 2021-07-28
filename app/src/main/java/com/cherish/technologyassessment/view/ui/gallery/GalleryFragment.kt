package com.cherish.technologyassessment.view.ui.gallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cherish.technologyassessment.databinding.FragmentGalleryBinding
import com.cherish.technologyassessment.utils.LoadImageWithGlide
import dagger.hilt.android.AndroidEntryPoint
import de.hdodenhof.circleimageview.CircleImageView

@AndroidEntryPoint
class GalleryFragment : Fragment() {
    private var _binding: FragmentGalleryBinding? = null
    var title: TextView? = null
    var abstract: TextView? = null
    var source: TextView? = null
    var author: TextView? = null
    var image: ImageView? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root
        title = binding.detailTitle
        abstract = binding.detailAbstract
        source = binding.detailSource
        image = binding.detailsImage
        author = binding.detailAuthor
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        title?.text = GalleryFragmentArgs.fromBundle(requireArguments()).title
        abstract?.text = GalleryFragmentArgs.fromBundle(requireArguments()).abstract
        source?.text = "Source: ${GalleryFragmentArgs.fromBundle(requireArguments()).source}"
        author?.text = GalleryFragmentArgs.fromBundle(requireArguments()).author
        LoadImageWithGlide.loadImage(image!!, GalleryFragmentArgs.fromBundle(requireArguments()).imageUrl)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}