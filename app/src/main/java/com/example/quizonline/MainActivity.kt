package com.example.quizonline

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizonline.adapters.QuizListAdapter
import com.example.quizonline.databinding.ActivityMainBinding
import com.example.quizonline.models.QuestionModel
import com.example.quizonline.models.QuizModel
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var quizModelList : MutableList<QuizModel>
    lateinit var adapter: QuizListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        quizModelList = mutableListOf()
        getDataFromFirebase()
    }

    private  fun getDataFromFirebase(){
        //dummy data
        //val listQuestionModel = mutableListOf<QuestionModel>()
        //listQuestionModel.add(QuestionModel("What is android", mutableListOf("Language","OS","Product","None"),"OS"))
        //listQuestionModel.add(QuestionModel("What is android", mutableListOf("Language","OS","Product","None"),"OS"))
        //listQuestionModel.add(QuestionModel("What is android", mutableListOf("Language","OS","Product","None"),"OS"))

        //quizModelList.add(QuizModel("1","Programming","All the basic programming","10",listQuestionModel))
        //quizModelList.add(QuizModel("2","Computer","All the computer questions","10",listQuestionModel))
        //quizModelList.add(QuizModel("3","Geography","Boost your geographic knowledge","10",listQuestionModel))
        //till this dummy data

        //get data from firebase
        binding.progressBar.visibility = View.VISIBLE
        FirebaseDatabase.getInstance().reference//this returns the reference of firebase (json) database
            .get()
            .addOnSuccessListener { dataSnapshot->
                if(dataSnapshot.exists()){
                    for(snapshot in dataSnapshot.children){//as there are more than one question model int firebase database so iterate over all using for loop
                        val quizModel = snapshot.getValue(QuizModel::class.java)
                        if (quizModel != null) {//it can be null so surround with null check
                            quizModelList.add(quizModel)
                        }
                    }
                }
                setupRecyclerView()//once the onsuccesslistener is success it will set recyclerView
            }
        //setupRecyclerView()//after we set the data in the above then we should set recyclerview but it shouldn't be outside so taken inside and commenting this one out
    }

    private fun setupRecyclerView() {
        binding.progressBar.visibility = View.GONE
        adapter = QuizListAdapter(quizModelList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }
}