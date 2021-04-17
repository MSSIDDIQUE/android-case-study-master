package com.target.targetcasestudy.ui.fragments.DealListFragment.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.target.targetcasestudy.data.Result
import com.target.targetcasestudy.R
import com.target.targetcasestudy.api.response.Product
import com.target.targetcasestudy.databinding.FragmentDealItemBinding
import com.target.targetcasestudy.ui.vm.DealListViewModel
import com.target.targetcasestudy.ui.vm.DealListViewModelFactory
import kotlinx.coroutines.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import org.kodein.di.generic.kcontext


class DealItemFragment : Fragment(R.layout.fragment_deal_item),KodeinAware {

  override val kodeinContext = kcontext<Fragment>(this)
  override val kodein by kodein()
  private lateinit var viewModel: DealListViewModel
  private val viewModelFactory: DealListViewModelFactory by instance()
  private val args: DealItemFragmentArgs by navArgs()
  private var binding: FragmentDealItemBinding? = null

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_deal_item, container, false)
  }

  override fun onStart() {
    super.onStart()
    lifecycleScope.launch {
      val deal = withContext(Dispatchers.IO){
        viewModel.fetchProduct(args.prdtId)
      }
      when(deal.status){
        Result.Status.SUCCESS->{
          binding?.apply {
            prdtData  = deal.data as Product
            progressBar.visibility = View.GONE
            loadingText.visibility = View.GONE
          }
        }
        Result.Status.LOADING->{
          binding?.apply {
            progressBar.visibility = View.VISIBLE
            loadingText.visibility = View.VISIBLE
          }
        }
        Result.Status.ERROR->{
          Toast.makeText(requireContext(),"Something went wrong",Toast.LENGTH_LONG).show()
          binding?.apply {
            progressBar.visibility = View.GONE
            loadingText.visibility = View.GONE
          }
        }
      }
    }
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(DealListViewModel::class.java)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding = FragmentDealItemBinding.bind(view)
  }
}
