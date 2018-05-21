package com.maeun.random_group_chatting

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v7.widget.LinearLayoutManager
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var chat : ArrayList<Message>
    lateinit var adapter : Adapter
    lateinit var mChild : ChildEventListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chat = ArrayList()

        val databasereference = FirebaseDatabase.getInstance().getReference()

        btn.setOnClickListener{
            databasereference.child("chat").push().setValue(chat_edittext.getText().toString())
            chat_edittext.setText("")
        }

//        fun chatConversation(dataSnapshot: DataSnapshot){
//            var i = dataSnapshot.children.iterator()
//
//            while(i.hasNext()){
//                val msg : String = i.next().getValue().toString()
//                chat.add(Message(msg))
//            }
//            adapter.notifyDataSetChanged()
//        }

        databasereference.child("chat").addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                chat.clear()
                for(snapshot : DataSnapshot in dataSnapshot.children){
                    val msg : String = snapshot.getValue().toString()
                    chat.add(Message(msg))
                }
                adapter.notifyDataSetChanged()
                chat_room.scrollToPosition(chat.size-1)
            }

        })

//        databasereference.addChildEventListener(object : ChildEventListener{
//            override fun onCancelled(p0: DatabaseError?) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//            override fun onChildMoved(p0: DataSnapshot?, p1: String?) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//            override fun onChildChanged(dataSnapshot: DataSnapshot, p1: String?) {
//                System.out.println("changed")
//                chatConversation(dataSnapshot)
//            }
//
//            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
//
//            }
//
//            override fun onChildRemoved(p0: DataSnapshot?) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//        })


//        object : ChildEventListener {
//            override fun onCancelled(p0: DatabaseError?) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//            override fun onChildMoved(p0: DataSnapshot?, p1: String?) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//            override fun onChildChanged(dataSnapshot: DataSnapshot, p1: String?) {
//                System.out.println("added")
//                chatConversation(dataSnapshot)
//            }
//
//            override fun onChildAdded(dataSnapshot: DataSnapshot, p1: String?) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//            override fun onChildRemoved(p0: DataSnapshot?) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//        }

        adapter = Adapter(chat)
        chat_room.layoutManager = LinearLayoutManager(this)
        chat_room.adapter = adapter

    }
}
