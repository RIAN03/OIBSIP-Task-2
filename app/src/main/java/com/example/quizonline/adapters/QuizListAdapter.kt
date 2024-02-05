package com.example.quizonline.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quizonline.QuizActivity
import com.example.quizonline.databinding.QuizItemRecyclerRowBinding
import com.example.quizonline.models.QuizModel

class QuizListAdapter(private val quizModelList : List<QuizModel>) :RecyclerView.Adapter<QuizListAdapter.MyViewHolder>() {

    class MyViewHolder(private val binding: QuizItemRecyclerRowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(model: QuizModel){
            binding.apply {
                quizTitleText.text = model.title
                quizSubtitleText.text = model.subtitle
                quizTimeText.text = model.time+" min"
                root.setOnClickListener{
                    val intent = Intent(root.context,QuizActivity::class.java)
                    QuizActivity.questionModelList = model.questionList//in this line after the button is clicked all questions of that button are sent to QuizActivity
                    QuizActivity.time = model.time
                    root.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = QuizItemRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(quizModelList[position])
    }

    override fun getItemCount(): Int {
        return quizModelList.size
    }
}