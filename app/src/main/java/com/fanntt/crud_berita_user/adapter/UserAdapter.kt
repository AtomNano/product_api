package com.fanntt.crud_berita_user.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fanntt.crud_berita_user.DetailUserActivity
import com.fanntt.crud_berita_user.R
import com.fanntt.crud_berita_user.model.modeluser

class UserAdapter(private val userList: List<modeluser>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    // ViewHolder untuk memegang elemen tampilan
    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvUsername: TextView = itemView.findViewById(R.id.tvUsername)
        val tvFullName: TextView = itemView.findViewById(R.id.tvFullName)
        val tvEmail: TextView = itemView.findViewById(R.id.tvEmail)
        val tvTanggalDaftar: TextView = itemView.findViewById(R.id.tvTanggalDaftar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.tvUsername.text = user.username
        holder.tvFullName.text = user.fullname
        holder.tvEmail.text = user.email
        holder.tvTanggalDaftar.text = user.tgl_daftar

        // Tambahkan listener untuk klik item
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailUserActivity::class.java)
            intent.putExtra("id", user.id) // Kirim id
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}
