package com.neelasurya.myapplication.feature.post

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.neelasurya.myapplication.R
import com.neelasurya.myapplication.base.BaseViewModel
import com.neelasurya.myapplication.model.Order
import com.neelasurya.myapplication.network.PostApi
import io.reactivex.disposables.CompositeDisposable
import java.net.UnknownHostException
import javax.inject.Inject


class PostListViewModel(private val postDao: DatabaseReference) : BaseViewModel() {
    @Inject
    lateinit var postApi: PostApi
    val postListAdapter: PostListAdapter = PostListAdapter(postDao)
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val postList: ArrayList<Order> = ArrayList<Order>()
    val errorClickListener = View.OnClickListener {
        loadPosts(postList)
    }

    init {
        loadPosts(postList)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    /**
     * We will make the call to the api if it throws error or not able to connect through internet
     * we will show data from the database.
     */

    private fun loadPosts(postList: ArrayList<Order>) {
        postList.clear()
        postDao.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                //clearing the previous artist list
                //clearing the previous artist list
                //iterating through all the nodes
                //iterating through all the nodes
                try
                {
                    var postSnapshot=dataSnapshot.children

                    postSnapshot.forEach {
                        val order: Order? = it.getValue(Order::class.java)
                        //adding artist to the list
                        order?.let{postList.add(order)}

                    }
                    onRetrievePostListFinish()
                    postListAdapter.updatePostList(postList)
                } catch(e:Exception){
                    }
            }
            override fun onCancelled(databaseError: DatabaseError) {
                onRetrievePostListError(databaseError.toException())
            }
        })


    }

    private fun onRetrievePostListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrievePostListFinish() {
        loadingVisibility.value = View.GONE
    }




    private fun onRetrievePostListError(exception: Throwable) {
        if (exception is UnknownHostException) {
            errorMessage.value = R.string.server_error
        } else {
            errorMessage.value = R.string.post_error
        }
    }
}
