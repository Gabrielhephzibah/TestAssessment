package com.cherish.technologyassessment.view.ui.home

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cherish.technologyassessment.R
import com.cherish.technologyassessment.data.model.Media
import com.cherish.technologyassessment.data.model.PopularArticleItem
import com.cherish.technologyassessment.databinding.FragmentHomeBinding
import com.cherish.technologyassessment.utils.ResultManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private  val homeViewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private var mAdapter: PopularArticleAdapter? = null
   private var recyclerView: RecyclerView? = null
    private var progressBar: ProgressBar? = null
    private val TAG = "HomeFragment"
    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        recyclerView  = binding.recyclerView
        progressBar = binding.progressBar
        recyclerView?.layoutManager = LinearLayoutManager(requireActivity())
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter = PopularArticleAdapter(callback ={
            val item = mAdapter!!.getData(it)
            val action = HomeFragmentDirections.actionNavHomeToNavGallery(item.title!!,item.abstract!!,item.image!!,item.source!!,item.author!!)
            view.findNavController().navigate(action)
        })
        recyclerView?.adapter = mAdapter
        homeViewModel.getAllPopularArticles().observe(viewLifecycleOwner){result ->
            when(result){
                is ResultManager.Failure -> onError(result.error.localizedMessage)
                is ResultManager.Loading -> {when(result.state){
                    true -> { progressBar!!.visibility = View.VISIBLE }
                    false->{ progressBar!!.visibility = View.GONE } }
                }
                is ResultManager.Success -> onSuccess(result.data)
            }
        }


    }

    private fun onError(msg: String?) {
        Toast.makeText(requireActivity(), "UNABLE TO LOAD DATA, TRY AGAIN AFTER SOME TIME", Toast.LENGTH_LONG).show()
    }
    private fun onSuccess(response : PopularArticleItem){
        val result = response.results
        val listOfRecyclerList = result?.map{PopularArticleData(it.title,  it.media?.firstOrNull()?.mediaMetadata?.firstOrNull()?.url,it.byline, it.publishedDate,it.abstract,it.source) }
        mAdapter?.submitList(listOfRecyclerList)



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}