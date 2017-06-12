package es.sccode.springdemo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import es.sccode.springdemo.component.ContactConverter;
import es.sccode.springdemo.entity.Contact;
import es.sccode.springdemo.model.ContactModel;
import es.sccode.springdemo.repository.ContactRepository;
import es.sccode.springdemo.service.ContactService;

@Service("contactService")
public class ContactServiceImpl implements ContactService{

	@Autowired
	@Qualifier("contactRepository")
	ContactRepository contactRepository;
	
	
	
	@Override
	public ContactModel addContact(ContactModel contactModel) {
		return ContactConverter.EntityToModel(contactRepository.save(ContactConverter.ModelToEntity(contactModel)));
	}

	@Override
	public List<ContactModel> listAllContacts() {
		return contactRepository.findAll().stream().map(ContactConverter::EntityToModel).collect(Collectors.toList());
	}

	
	@Override
	public Contact findContactById(int id) {
		return contactRepository.findById(id);
	}
	
	//TODO Cutrería.
	@Override
	public ContactModel getContactModelById(int id){
		return ContactConverter.EntityToModel(findContactById(id));
	}

	@Override
	public Contact removeContact(int id) {
		Contact c = this.findContactById(id);
		if(c!=null)contactRepository.delete(c);
		return c;
	}


}
