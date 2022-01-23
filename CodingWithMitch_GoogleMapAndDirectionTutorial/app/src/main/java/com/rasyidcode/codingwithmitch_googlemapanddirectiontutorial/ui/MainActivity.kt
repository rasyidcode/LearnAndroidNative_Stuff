package com.rasyidcode.codingwithmitch_googlemapanddirectiontutorial.ui

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.*
import com.rasyidcode.codingwithmitch_googlemapanddirectiontutorial.R
import com.rasyidcode.codingwithmitch_googlemapanddirectiontutorial.adapters.ChatroomRecyclerViewAdapter
import com.rasyidcode.codingwithmitch_googlemapanddirectiontutorial.databinding.ActivityMainBinding
import com.rasyidcode.codingwithmitch_googlemapanddirectiontutorial.models.Chatroom

class MainActivity : AppCompatActivity(), View.OnClickListener,
    ChatroomRecyclerViewAdapter.ChatroomRecyclerViewItemClickListener {

    companion object {
        const val TAG = "MainActivity"
    }

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    private val firestoreDb by lazy {
        FirebaseFirestore.getInstance()
    }

    private val firestoreSettings by lazy {
        FirebaseFirestoreSettings.Builder()
            .build()
    }

    private val chatrooms by lazy {
        arrayListOf<Chatroom>()
    }

    private val chatroomIds by lazy {
        HashSet<String?>()
    }

    private lateinit var chatroomRecyclerViewAdapter: ChatroomRecyclerViewAdapter

    private lateinit var chatroomEventListener: ListenerRegistration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initSupportActionBar()
        initFirestore()
        initChatroomRecyclerView()
    }

    private fun initSupportActionBar() {
        title = "Chatrooms"
    }

    private fun initFirestore() {
        firestoreDb.firestoreSettings = firestoreSettings
    }


    private fun initChatroomRecyclerView() {
        chatroomRecyclerViewAdapter = ChatroomRecyclerViewAdapter(chatrooms, this)

        binding.chatroomsRecyclerView.also {
            it.adapter = chatroomRecyclerViewAdapter
            it.layoutManager = LinearLayoutManager(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (chatroomEventListener != null) {
            chatroomEventListener.remove()
        }
    }

    override fun onResume() {
        super.onResume()
        getChatrooms()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getChatrooms() {
        val chatroomCollection = firestoreDb
            .collection(getString(R.string.collection_chatrooms))

        chatroomEventListener = chatroomCollection.addSnapshotListener { value, error ->
            Log.d(TAG, "onEvent: called.")

            if (error != null) {
                Log.e(TAG, "onEvent: Listen failed.", error)
                return@addSnapshotListener
            }

            if (value != null) {
                for (doc in value) {
                    val chatroom = doc.toObject(Chatroom::class.java)
                    if (!chatroomIds.contains(chatroom.chatroomId)) {
                        chatroomIds.add(chatroom.chatroomId)
                        chatrooms.add(chatroom)
                    }
                }
                Log.d(TAG, "onEvent: number of chatrooms: " + chatrooms.size)
                chatroomRecyclerViewAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.fab_create_chatroom -> {
                newChatroomDialog()
            }
        }
    }

    private fun newChatroomDialog() {
        val builder = AlertDialog.Builder(this)
            .apply { setTitle("Enter a chatroom name") }
        val input = EditText(this)
            .apply { inputType = InputType.TYPE_CLASS_TEXT }
        builder.also {
            it.setView(input)
            it.setPositiveButton(
                "CREATE"
            ) { p0, p1 ->
                if (input.text.toString() != "") {
                    buildNewChatroom(input.text.toString())
                }
            }
        }
    }

    private fun buildNewChatroom(chatroomName: String) {
        val chatroom = Chatroom()
        chatroom.title = chatroomName

        val newChatroomRef = firestoreDb
            .collection(getString(R.string.collection_chatrooms))
            .document()
        chatroom.chatroomId = newChatroomRef.id

        newChatroomRef.set(chatroom).addOnCompleteListener {
            binding.progressCircular.visibility = View.VISIBLE

            if (it.isSuccessful) {
                navigateToChatroomActivity(chatroom)
            } else {
                Snackbar.make(binding.root, "Something went wrong", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigateToChatroomActivity(chatroom: Chatroom) {
        startActivity(Intent(this@MainActivity, ChatroomActivity::class.java).apply {
            putExtra(
                getString(R.string.intent_chatroom),
                chatroom
            )
        })
    }

    override fun onChatroomSelected(position: Int) {
        TODO("Not yet implemented")
    }
}