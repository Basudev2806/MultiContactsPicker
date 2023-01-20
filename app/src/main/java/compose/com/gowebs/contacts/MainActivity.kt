package compose.com.gowebs.contacts

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.vmadalin.easypermissions.EasyPermissions
import compose.com.gowebs.contacts.databinding.ActivityMainBinding
import pub.devrel.easypermissions.AppSettingsDialog

class MainActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks , ContactsListener {
    private lateinit var binding: ActivityMainBinding
    var arrayList: ArrayList<ContactModel> = arrayListOf()
    var rcvAdapter: RCVAdapter = RCVAdapter(arrayList,this@MainActivity)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (checkContactPermissions()) {
            binding.apply {
                rcvContact.apply {
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    adapter = RCVAdapter(arrayList,this@MainActivity)
                }
            }
        }
//        binding.toolbar.icSave.setOnClickListener {
//
//        }
        binding.toolbar.icBack.setOnClickListener {
            finish()
        }
    }

    @SuppressLint("Range")
    private fun getContacts() {
        arrayList.clear()
        val cursor = this.contentResolver
            .query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                arrayOf(
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                    ContactsContract.CommonDataKinds.Phone.NUMBER,

                    ), null, null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
            )
        while (cursor!!.moveToNext()) {
            val contactName =
                cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
            val contactNumber =
                cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
            val contactModel = ContactModel(contactName, contactNumber)
            arrayList.add(contactModel)
        }
        rcvAdapter.notifyDataSetChanged()
        cursor.close()
    }

    private fun checkContactPermissions(): Boolean {
        if (PermissionTracking.hasCOntactPermissions(this)) {
            getContacts()
            return true
        } else if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            EasyPermissions.requestPermissions(
                this,
                "You will need to accept the permission in order to run the application",
                100,
                android.Manifest.permission.READ_CONTACTS,
                android.Manifest.permission.WRITE_CONTACTS,
            )
            return true
        } else {
            return false
        }
    }

    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        TODO("Not yet implemented")
    }

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        } else {
            checkContactPermissions()
        }
    }

    override fun onChanged(arrayList: ArrayList<ContactModel>) {
        binding.toolbar.icSave.setOnClickListener {
//            Toast.makeText(this, arrayList.toString(), Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ShowActivity::class.java)
            intent.putExtra("contacts", Gson().toJson(arrayList))
            startActivity(intent)
        }
    }


}