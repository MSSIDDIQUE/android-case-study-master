package com.target.targetcasestudy.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.target.targetcasestudy.api.response.Product
import com.target.targetcasestudy.databinding.DealListItemBinding
import com.target.targetcasestudy.ui.fragments.DealListFragment.ui.DealListFragmentDirections

class DealListAdapter(private val data:List<Product>):RecyclerView.Adapter<DealListAdapter.DealListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealListViewHolder {
        val binding = DealListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DealListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DealListViewHolder, position: Int) {
        val deal = data[position]
        deal.let{
            holder.bind(createOnClickListener(deal.id),it)
        }
    }

    private fun createOnClickListener(id:Int): View.OnClickListener {
        return View.OnClickListener {
            val direction = DealListFragmentDirections.actionDealListFragmentToDealItemFragment(id)
            it.findNavController().navigate(direction)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }


    class DealListViewHolder(private val binding: DealListItemBinding):RecyclerView.ViewHolder(binding.root){
        companion object{
            private val deal_key = "deal"
        }
        fun bind(listener: View.OnClickListener, data:Product) {
            binding.apply {
                clickListener = listener
                prdt = data
            }
        }
    }

}