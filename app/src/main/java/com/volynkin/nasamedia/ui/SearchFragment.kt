package com.volynkin.nasamedia.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.volynkin.nasamedia.R
import com.volynkin.nasamedia.databinding.FragmentSearchBinding
import timber.log.Timber

class SearchFragment : Fragment() {

    companion object {
        private const val TAG = "SearchFragment"
    }

    private lateinit var binding: FragmentSearchBinding
    private val itemsViewModel: NASAMediaItemsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Timber.tag(TAG).e("onCreateView")
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
    }

    private fun initToolbar() {
        val searchItem = binding.toolbar.menu.findItem(R.id.search)
        (searchItem.actionView as SearchView).queryHint = "Search in NASA media Library"
        (searchItem.actionView as SearchView).maxWidth = Int.MAX_VALUE
        (searchItem.actionView as SearchView).setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Timber.tag(TAG).e("query = $query")
                if (query != null) {
                    itemsViewModel.getNasaMediaItems(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }
}