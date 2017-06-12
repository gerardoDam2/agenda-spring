package es.sccode.springdemo.service;

import java.util.List;

import es.sccode.springdemo.entity.Contact;
import es.sccode.springdemo.model.ContactModel;

public interface ContactService {

	public abstract ContactModel addContact(ContactModel contactModel);
	public abstract List<ContactModel> listAllContacts();
	public abstract Contact findContactById(int id);
	public abstract ContactModel getContactModelById(int id);
	public abstract Contact removeContact(int id);
}
