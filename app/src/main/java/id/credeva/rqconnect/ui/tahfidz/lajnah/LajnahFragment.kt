package id.credeva.rqconnect.ui.tahfidz.lajnah

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import id.credeva.rqconnect.R
import id.credeva.rqconnect.data.adapter.LajnahAdapter
import kotlinx.android.synthetic.main.fragment_lajnah.*
import org.kodein.di.android.x.kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import java.lang.Exception

/**
 * A simple [Fragment] subclass.
 * by Sandy Priyatna
 */

class LajnahFragment : Fragment(), KodeinAware {

    override val kodein by kodein()
    private val factory: LajnahViewModelFactory by instance()
    private lateinit var viewModel: LajnahViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lajnah, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(LajnahViewModel::class.java)

        viewModel.getLajnah()
        viewModel.lajnah.observe(viewLifecycleOwner, Observer { lajnah ->
            try {
                rv_lajnah.also {
                    it.layoutManager = LinearLayoutManager(requireContext())
                    it.setHasFixedSize(true)
                    it.adapter = LajnahAdapter(lajnah)
                }
            } catch (e: Exception) {
                Log.v("errorLajnah: ", e.message)
            }
        })
    }

}
