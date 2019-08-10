package info.androidhive.materialdesign.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class Contact {

	@SerializedName("contacts")
	private List<ContactsItem> contacts;

	public void setContacts(List<ContactsItem> contacts){
		this.contacts = contacts;
	}

	public List<ContactsItem> getContacts(){
		return contacts;
	}

	@Override
 	public String toString(){
		return 
			"Contact{" +
			"contacts = '" + contacts + '\'' + 
			"}";
		}
}