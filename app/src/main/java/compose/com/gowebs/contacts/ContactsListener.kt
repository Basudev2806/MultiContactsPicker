package compose.com.gowebs.contacts

interface ContactsListener {
    fun onChanged(arrayList: ArrayList<ContactModel>)
}