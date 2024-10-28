package com.example.happybirthday

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.happybirthday.Email

class EmailAdapter(private val emailList: List<Email>) :
    RecyclerView.Adapter<EmailAdapter.EmailViewHolder>() {

    class EmailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val avatar: TextView = itemView.findViewById(R.id.avatar)
        val sender: TextView = itemView.findViewById(R.id.sender)
        val subject: TextView = itemView.findViewById(R.id.subject)
        val preview: TextView = itemView.findViewById(R.id.preview)
        val time: TextView = itemView.findViewById(R.id.time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_email, parent, false)
        return EmailViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmailViewHolder, position: Int) {
        val email = emailList[position]
        holder.avatar.text = email.sender.first().toString() // Lấy chữ cái đầu làm ảnh đại diện
        holder.sender.text = email.sender
        holder.subject.text = email.subject
        holder.preview.text = email.preview
        holder.time.text = email.time
    }

    override fun getItemCount() = emailList.size
}
