package com.snapp.interview.ui.browse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.snapp.interview.databinding.FragmentBrowseBinding
import com.snapp.interview.ui.common.BaseFragment
import com.snapp.interview.ui.widget.empty.EmptyListener
import com.snapp.interview.ui.widget.error.ErrorListener
import com.snapp.presentation.VehicleViewModel
import com.snapp.presentation.data.ResourceState
import com.snapp.presentation.model.VehicleView
import kotlinx.android.synthetic.main.fragment_browse.*
import javax.inject.Inject

class BrowseFragment : BaseFragment() {

    @Inject
    lateinit var browseAdapter: BrowseAdapter
    private lateinit var viewModel: VehicleViewModel

    private val emptyListener = object : EmptyListener {
        override fun onCheckAgainClicked() {
            viewModel.fetchVehicles()
        }
    }

    private val errorListener = object : ErrorListener {
        override fun onTryAgainClicked() {
            viewModel.fetchVehicles()
        }
    }

    companion object {
        fun newInstance(): BrowseFragment {
            return BrowseFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentBrowseBinding.inflate(inflater, container, false).root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = getViewModel(VehicleViewModel::class.java)

        setupRecyclerView()
        setupViewListeners()
    }

    override fun onStart() {
        super.onStart()
        observeData()
    }

    private fun setupRecyclerView() {
        view_list.layoutManager = LinearLayoutManager(activity)
        view_list.addItemDecoration(
            DividerItemDecoration(
                view_list.context, DividerItemDecoration.VERTICAL
            )
        )
        view_list.adapter = browseAdapter
    }

    private fun setupViewListeners() {
        view_empty.emptyListener = emptyListener
        view_error.errorListener = errorListener
    }

    private fun observeData() {
        viewModel.getVehicles().observe(this, Observer {
            it?.let {
                this.handleDataState(it.status, it.data, it.message)
            }
        })
    }

    private fun handleDataState(
        resourceState: ResourceState, data: List<VehicleView>?,
        message: String?
    ) {
        when (resourceState) {
            ResourceState.LOADING -> setupScreenForLoadingState()
            ResourceState.SUCCESS -> setupScreenForSuccess(data)
            ResourceState.ERROR -> setupScreenForError(message)
        }
    }

    private fun setupScreenForLoadingState() {
        view_list.visibility = View.GONE
        view_empty.visibility = View.GONE
        view_error.visibility = View.GONE
        view_loading.visibility = View.VISIBLE
    }

    private fun setupScreenForSuccess(data: List<VehicleView>?) {
        view_error.visibility = View.GONE
        view_loading.visibility = View.GONE

        if (data != null && data.isNotEmpty()) {
            browseAdapter.submitList(data)
            view_list.visibility = View.VISIBLE
        } else {
            view_empty.visibility = View.VISIBLE
        }
    }

    private fun setupScreenForError(message: String?) {
        view_loading.visibility = View.GONE
        view_list.visibility = View.GONE
        view_empty.visibility = View.GONE
        view_error.visibility = View.VISIBLE
    }
}