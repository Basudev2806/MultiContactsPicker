package compose.com.gowebs.contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import compose.com.gowebs.contacts.databinding.ItemContactBinding

class RCVAdapter(
    private val contactList: ArrayList<ContactModel>, var listener: ContactsListener
) : RecyclerView.Adapter<RCVAdapter.MyViewHolder>() {
    private var arrayListSelected = ArrayList<ContactModel>()
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
                    holder.binding.nameTV.text = item.displayName
                    holder.binding.numberTV.text = item.number
            holder.binding.check.setOnClickListener { view: View? ->
                if (holder.binding.check.isChecked) {
//                    arrayListSelected.add(contactList!![position].toString())
                    arrayListSelected.add(ContactModel(contactList.get(position).displayName,contactList.get(position).number))
                } else {
                    arrayListSelected.remove(ContactModel(contactList.get(position).displayName,contactList.get(position).number))
                }
                listener.onChanged(arrayListSelected)
            }
        }


    }

    override fun getItemCount(): Int = contactList.size
}
