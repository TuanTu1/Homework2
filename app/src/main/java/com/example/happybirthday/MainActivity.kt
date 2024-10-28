package com.example.happybirthday

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.happybirthday.Email
import com.example.happybirthday.EmailAdapter


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Dữ liệu mẫu
        val emailList = listOf(
            Email("Alice", "Meeting Update", "Please review the agenda...", "9:00 AM"),
            Email("Bob", "Promotion Offer", "Get 20% off on...", "Yesterday"),
            Email("Charlie", "Project Deadline", "Deadline is approaching...", "2 days ago"),
            Email("David", "Weekend Plans", "How about a hiking trip...", "3 days ago")
        )

        // Thiết lập adapter
        val adapter = EmailAdapter(emailList)
        recyclerView.adapter = adapter
    }
}
