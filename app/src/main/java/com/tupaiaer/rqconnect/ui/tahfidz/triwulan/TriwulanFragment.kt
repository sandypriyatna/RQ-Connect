package com.tupaiaer.rqconnect.ui.tahfidz.triwulan

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.tupaiaer.rqconnect.R
import com.tupaiaer.rqconnect.data.adapter.PekanAdapter
import com.tupaiaer.rqconnect.data.adapter.TriwulanAdapter
import kotlinx.android.synthetic.main.fragment_pekan.*
import kotlinx.android.synthetic.main.fragment_triwulan.*
import org.kodein.di.android.x.kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import java.lang.Exception

/**
 * A simple [Fragment] subclass.
 * by Sandy Priyatna
 */

class TriwulanFragment : Fragment(), KodeinAware {

    override val kodein by kodein()
    private val factory: TriwulanViewModelFactory by instance()
    private lateinit var viewModel: TriwulanViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_triwulan, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(TriwulanViewModel::class.java)

        viewModel.getTriwulan()
        viewModel.triwulan.observe(viewLifecycleOwner, Observer { triwulan ->
            try {
                rv_triwulan.also {
                    it.layoutManager = LinearLayoutManager(requireContext())
                    it.setHasFixedSize(true)
                    it.adapter = TriwulanAdapter(triwulan)
                }
            } catch (e: Exception) {
                Log.v("errorTriwulan: ", e.message)
            }
        })
    }

}
