package com.target.targetcasestudy.ui.fragments.DealListFragment.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.target.targetcasestudy.R
import com.target.targetcasestudy.ui.adapters.DealListAdapter
import com.target.targetcasestudy.api.response.Product
import com.target.targetcasestudy.databinding.FragmentDealListBinding
import com.target.targetcasestudy.data.Result
import com.target.targetcasestudy.ui.vm.DealListViewModel
import com.target.targetcasestudy.ui.vm.DealListViewModelFactory
import com.target.targetcasestudy.utils.exceptions.NoConnectivityException
import kotlinx.android.synthetic.main.fragment_deal_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import org.kodein.di.generic.kcontext


class DealListFragment() : Fragment(R.layout.fragment_deal_list),KodeinAware {

  override val kodeinContext = kcontext<Fragment>(this)
  override val kodein by kodein()
  private lateinit var viewModel: DealListViewModel
  private val viewModelFactory: DealListViewModelFactory by instance()
  private lateinit var deals_adapter: DealListAdapter
  private lateinit var linearLayoutManager: LinearLayoutManager
  private var binding: FragmentDealListBinding ? = null

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel = ViewModelProvider(this,viewModelFactory).get(DealListViewModel::class.java)
    lifecycleScope.launch(Dispatchers.Main) {
      try {
        val prdts = viewModel.products
        prdts.observe(viewLifecycleOwner){result->
          when(result.status){
            Result.Status.SUCCESS->{
              deals_adapter = DealListAdapter(result.data?.products as ArrayList<Product>)
              linearLayoutManager = LinearLayoutManager(context)
              recycler_view.layoutManager = linearLayoutManager
              recycler_view.adapter = deals_adapter
              progress_bar.visibility = View.GONE
              loading_text.visibility = View.GONE
            }
            Result.Status.LOADING->{
              progress_bar.visibility = View.VISIBLE
              loading_text.visibility = View.VISIBLE
            }
            Result.Status.ERROR->{
              Toast.makeText(context,"Something went wrong please check your internet connection",
                Toast.LENGTH_LONG).show()
              progress_bar.visibility = View.GONE
              loading_text.visibility = View.GONE
            }
          }
        }
      }catch (e: NoConnectivityException){
        Toast.makeText(context,"Something went wrong please check your internet connection",Toast.LENGTH_LONG).show()
      }
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    linearLayoutManager = LinearLayoutManager(context)
    binding = FragmentDealListBinding.bind(view)
    binding.apply {
      recycler_view.apply {
        setHasFixedSize(true)
        layoutManager = linearLayoutManager
        addItemDecoration(DividerItemDecoration(requireActivity(),DividerItemDecoration.VERTICAL))
      }
    }
  }
}
