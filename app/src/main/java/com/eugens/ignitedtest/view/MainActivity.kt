package com.eugens.ignitedtest.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.Toast
import com.eugens.ignitedtest.MyApplication
import com.eugens.ignitedtest.R
import com.eugens.ignitedtest.datamodel.IDataModel
import com.eugens.ignitedtest.schedulers.ISchedulerProvider
import com.eugens.ignitedtest.viewmodel.MyViewModel
import com.eugens.ignitedtest.viewmodel.MyViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MyViewModel
    private lateinit var adapter: ArrayAdapter<String>
    private var dataList = ArrayList<String>()

    private val listObserver = Observer<List<String>> { values ->
        dataList.clear()
        if (values != null) {
            dataList.addAll(values)
            adapter.notifyDataSetChanged()
        }
    }
    private val lastDateObserver = Observer<String> { value ->
        if (value != null) {
            tvLastDate.text = value
        }
    }

    private val isOldDataObserver = Observer<Boolean> { value ->
        if (value != null && value == true) {
            Toast.makeText(this, "Data is outdated", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this, MyViewModelFactory(getDataModel(), getSchedulers()))
                .get(MyViewModel::class.java)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, dataList)
        lvLastResult.adapter = adapter

        subscribeOnChanges()

        btnGetValues.setOnClickListener { it ->
            viewModel.getActualData()
        }
    }

    private fun subscribeOnChanges() {
        viewModel.dataList.observe(this, listObserver)
        viewModel.lastDate.observe(this, lastDateObserver)
        viewModel.isOldData.observe(this, isOldDataObserver)
    }

    private fun getDataModel(): IDataModel {
        return (application as MyApplication).getDataModel()
    }

    private fun getSchedulers(): ISchedulerProvider {
        return (application as MyApplication).getSchedulerProvider()
    }

}
