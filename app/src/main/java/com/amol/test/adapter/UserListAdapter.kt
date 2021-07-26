package com.amol.test.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.amol.test.R
import com.amol.test.databinding.UserListNewRowBinding
import com.amol.test.model.UserEntity

class UserListAdapter(private val clickListener: (UserEntity) -> Unit) :
    RecyclerView.Adapter<UserViewHolder>() {

    val userList = ArrayList<UserEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: UserListNewRowBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.user_list_new_row, parent, false)
        return UserViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(userList[position], clickListener)

    }

    fun setData(newUserList: List<UserEntity>) {
        userList.clear()
        userList.addAll(newUserList)

    }

}

class UserViewHolder(val binding: UserListNewRowBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        userObject: UserEntity,
        clickListener: (UserEntity) -> Unit
    ) {
        binding.userName.text = userObject.name
        binding.userEmail.text = userObject.email
        binding.userId.text = userObject.id.toString()
        binding.userCity.text = userObject.address.street
        binding.userCompany.text = userObject.company.name

        binding.listItemLayout.setOnClickListener {
            clickListener(userObject)
        }

    }

}