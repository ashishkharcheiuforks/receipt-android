package com.receipttracker.ui

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.receipttracker.model.*
import com.receipttracker.remote.ServiceBuilder
import kotlinx.android.synthetic.main.activity_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.StringBuilder

class ListActivity : AppCompatActivity() {

    companion object{
        val receiptList = mutableListOf<SavedReceipt>()
    }

    lateinit var viewModel: ReceiptViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)



        listActivity_bottomAppBar_fab.setOnClickListener {
            startActivity(Intent(this, AddReceiptActivity::class.java))
        }

        Log.i("onResponse", LoginActivity.token)
        val call:Call<ListReceipts> = ServiceBuilder.create().getUserReceiptsByID(LoginActivity.token, LoginActivity.userId)

        call.enqueue(object: Callback<ListReceipts>{
            override fun onFailure(call: Call<ListReceipts>, t: Throwable) {
                Log.i("onFailure", t?.message)
            }

            override fun onResponse(
                call: Call<ListReceipts>,
                response: Response<ListReceipts>
            ) {
                val currentReceipt = response.body()?.receipts?.receipts

                currentReceipt?.forEach {
                    receiptList.add(SavedReceipt(it.id, it.purchase_date, it.merchant, it.amount.toFloat(), it.notes, it.created_at, it.updated_at, it.user_id))
                }

                rv_receipts.apply {
                    hasFixedSize()
                    layoutManager = LinearLayoutManager(this@ListActivity)
                    adapter = ReceiptRecyclerViewAdapter(receiptList)
                }

            }

        })


    }

    override fun onResume() {

        super.onResume()
        rv_receipts.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(this@ListActivity)
            adapter = ReceiptRecyclerViewAdapter(receiptList)
        }
    }

    override fun onPostResume() {

        super.onPostResume()
        rv_receipts.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(this@ListActivity)
            adapter = ReceiptRecyclerViewAdapter(receiptList)
        }
    }



    private fun getReceiptByBusiness(receiptName: String) {
        viewModel.receipts

    }

    class CreateAsyncTask(viewModel: ReceiptViewModel) : AsyncTask<SavedReceipt, Void, Unit>() {
        private val viewModel = WeakReference(viewModel)
        override fun doInBackground(vararg p0: SavedReceipt?) {
            if (p0.isNotEmpty()) {
                p0[0]?.let {
                    //viewModel.get()?.createReceipt(it)
                }

            }

        }
    }
}