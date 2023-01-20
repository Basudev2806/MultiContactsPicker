package compose.com.gowebs.contacts

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import compose.com.gowebs.contacts.databinding.ActivityMainBinding
import compose.com.gowebs.contacts.databinding.ActivityShowBinding
import java.util.Collections.addAll

class ShowActivity : AppCompatActivity(){
    private lateinit var binding: ActivityShowBinding
    var arrayList: ArrayList<ContactModel> = arrayListOf()
    var contactList: ArrayList<ContactModel> = arrayListOf()
    var showAdapter: SHOWAdapter = SHOWAdapter(arrayList)
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (intent.hasExtra("contacts")){
            arrayList.addAll( Gson().fromJson(intent.getStringExtra("contacts"),Array<ContactModel>::class.java))
            binding.apply {
                showContact.apply {
                    layoutManager = LinearLayoutManager(this@ShowActivity)
                    adapter = SHOWAdapter(arrayList)
                    showAdapter.notifyDataSetChanged()
                }
            }
//            Toast.makeText(this@ShowActivity, arrayList.toString(), Toast.LENGTH_SHORT).show()
        }

    }
}