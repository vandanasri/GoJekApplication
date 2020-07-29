package com.vandana.gojekgitapp.ui.trendingRepoFragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.facebook.shimmer.ShimmerFrameLayout
import com.vandana.gojekgitapp.R
import com.vandana.gojekgitapp.data.model.TrendingRepositoryData
import com.vandana.gojekgitapp.di.component.FragmentComponent
import com.vandana.gojekgitapp.ui.base.BaseFragment
import com.vandana.gojekgitapp.utils.common.Status
import com.vandana.gojekgitapp.utils.network.NetworkHelper
import com.vandana.gojekgitapp.utils.network.NetworkHelperImp
import kotlinx.android.synthetic.main.fragment_topgit_repo.*
import kotlinx.android.synthetic.main.layout_failure_retry.*
import kotlinx.android.synthetic.main.layout_loading_shimmer.*
import kotlinx.android.synthetic.main.layout_success_rv.*

class TrendingRepositoryFragment : BaseFragment<TrendingRepositoryViewModel>() , SwipeRefreshLayout.OnRefreshListener{

    private lateinit var mContext: Context
    private lateinit var mShimmerFrameLayout: ShimmerFrameLayout
    private lateinit var adapter: TrendingRepositoryAdapter
    private lateinit var trendingRepoList : List<TrendingRepositoryData>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun provideLayoutId(): Int = R.layout.fragment_topgit_repo

    override fun injectDependencies(fragmentComponent: FragmentComponent) = fragmentComponent.inject(this)

    override fun setupView(view: View) {

        swipeContainer.setColorSchemeResources(R.color.colorAccent)
        swipeContainer.setOnRefreshListener(this)

        val linearLayoutManager = LinearLayoutManager(mContext, RecyclerView.VERTICAL, false)
        rvHeadlinesList.layoutManager = linearLayoutManager
        rvHeadlinesList.addItemDecoration(DividerItemDecoration(mContext,RecyclerView.VERTICAL))

        viewModel.callTrendingRepositoryAPI()

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.option_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.menu_sort_by_stars -> {
                Log.v("TAG", "stars clicked")
                sortItemByStarsAndUpdateUI()
            }
            R.id.menu_sort_by_name -> {
                Log.v("TAG", "name clicked")
                sortItemByNameAndUpdateUI()
            }
            else -> { }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setupObservers() {

        viewModel.isLoading.observe(this, Observer {
            when(it.status){
                Status.LOADING -> showLoaderView()
                Status.ERROR -> showErrorView()
                Status.SUCCESS -> {
                    showSuccessView()
                }

                else -> showMessage("Unknown")
            }

        })

        viewModel.messageStringId.observe(this, Observer {
            if(it.data == R.string.network_connection_error)
                showErrorView()
        })


        viewModel.trendingRepositoryLiveData.observe(this, Observer {
            trendingRepoList = it
            adapter = TrendingRepositoryAdapter(it)
            rvHeadlinesList.adapter = adapter
        })

    }


    //to show loading view
    private fun showLoaderView()
    {
        successLayout?.visibility = View.GONE
        retryLayout?.visibility = View.GONE
        loadingLayout.visibility = View.VISIBLE
      shimmerFrameLayout.startShimmerAnimation()

    }

    //to show success view
    private fun showSuccessView()
    {
        successLayout?.visibility = View.VISIBLE
        retryLayout.visibility = View.GONE
        loadingLayout?.visibility = View.GONE
      shimmerFrameLayout.stopShimmerAnimation()
        swipeContainer.isRefreshing = false
    }

    //to show retry view
    private fun showErrorView()
    {
        successLayout?.visibility = View.GONE
        loadingLayout.visibility = View.GONE
       shimmerFrameLayout.stopShimmerAnimation()
        retryLayout?.visibility = View.VISIBLE
        btnRetry.setOnClickListener {
            if(!NetworkHelperImp(this.context!!).isNetworkConnected()) {
                showMessage(getString(R.string.network_connection_error))
            } else
                viewModel.callTrendingRepositoryAPI()
        }
    }

   
    override fun onRefresh() {
        swipeContainer.isRefreshing = true
        viewModel.callTrendingRepositoryAPI()

    }

    //Sort by Name
    private fun sortItemByNameAndUpdateUI()
    {
        val sortedTrendingRepoListByName = trendingRepoList.sortedWith(compareBy { it.name })
        adapter = TrendingRepositoryAdapter(sortedTrendingRepoListByName)
        rvHeadlinesList.adapter = adapter
    }

    //Sort by Stars
    private fun sortItemByStarsAndUpdateUI()
    {
        val sortedTrendingRepoListByStars = trendingRepoList.sortedWith(compareByDescending { it.stars })
        adapter = TrendingRepositoryAdapter(sortedTrendingRepoListByStars)
        rvHeadlinesList.adapter = adapter
    }


}