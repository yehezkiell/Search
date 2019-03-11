package com.example.yehezkiel.cermati

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.widget.Toast
import com.example.yehezkiel.cermati.Model.Item
import com.example.yehezkiel.cermati.Model.UsersX
import com.example.yehezkiel.cermati.Network.NetworkRepository
import com.example.yehezkiel.cermati.Network.RetrofitInstance
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody
import android.view.MenuInflater
import android.content.Context.SEARCH_SERVICE
import android.app.SearchManager
import android.content.Context
import android.support.v4.view.MenuItemCompat.getActionView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import com.example.yehezkiel.cermati.Adapter.SearchAdapter
import android.support.v4.view.MenuItemCompat.setOnActionExpandListener
import android.text.TextUtils
import android.view.View


class MainActivity : AppCompatActivity() {


    internal lateinit var jsonAPI: RetrofitInstance
    internal var compositeDisposable = CompositeDisposable()
    private var mAdapter: SearchAdapter? = null
    var userList: ArrayList<Item> = ArrayList();
    var count = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Make a Retrofit Connection
        jsonAPI = NetworkRepository.create();

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.layoutManager = LinearLayoutManager(this)

    }

    // GENERATE ALL OF THE SEARCH VIEW HERE//
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.dashboard, menu);
        val searchItem = menu?.findItem(R.id.action_search);

        if (searchItem != null) {
            val searchView = searchItem.actionView as SearchView

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                //Fired when using submit button
                override fun onQueryTextSubmit(query: String?): Boolean {
                    fetchData(query!!)
                    return true;
                }

                //Fired when users type for more than 3 characther
                override fun onQueryTextChange(newText: String?): Boolean {

                    if (count == 10) {
                        // Handle Limit Search ,refer for Github API
                        Toast.makeText(applicationContext, "Limit search, please wait for 1 minutes!", Toast.LENGTH_SHORT).show()
                    } else {
                        if (newText != null && TextUtils.getTrimmedLength(newText)>3) {
                            mRecyclerView.visibility = View.VISIBLE
                            fetchData(newText)
                            count++;
                        } else if(TextUtils.getTrimmedLength(newText)<3) {
                            //Clear the recyclerview when characters less than 3.
                            mRecyclerView.visibility = View.GONE
                        }
                    }
                    return true
                }



            })
            return true;

        }else{
            return false;
        }


        return super.onCreateOptionsMenu(menu)
    }


    // Rx Java observer
    private fun fetchData(query: String) {
        compositeDisposable?.add(jsonAPI.getUser(query, 100)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->

                    fetchrecyclerview(result);
//                    Log.e("retrofitnya", "" + result);
                    Toast.makeText(this, "Query  ${query}", Toast.LENGTH_LONG).show()


                }, { error ->
                    Toast.makeText(this, "Error ${error.localizedMessage}", Toast.LENGTH_SHORT).show()
                }

                ))
    }

    //Fetch Data to the RecyclerView
    private fun fetchrecyclerview(userList : UsersX){
        mAdapter = SearchAdapter(this, userList)
        mRecyclerView.adapter = mAdapter
        mAdapter?.notifyDataSetChanged()
    }



}
