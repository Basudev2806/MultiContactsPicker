package compose.com.gowebs.contacts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import compose.com.gowebs.contacts.databinding.ItemContactBinding

class SHOWAdapter(
    private val contactList: ArrayList<ContactModel>
) : RecyclerView.Adapter<SHOWAdapter.MyViewHolder>() {
    inner class MyViewHolder(val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemContactBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = contactList[position]
//        holder.binding.nameTV.text = item.displayName
//        holder.binding.numberTV.text = item.number


        if (contactList != null && contactList!!.size > 0) {
            // holder.binding.check.text = contactList!![position].toString()
            holder.binding.nameTV.text = "" + item.displayName
            holder.binding.numberTV.text = "" + item.number

        }


    }

    override fun getItemCount(): Int = contactList.size
}
