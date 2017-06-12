package es.sccode.springdemo.component;

import org.springframework.stereotype.Component;

import es.sccode.springdemo.entity.Contact;
import es.sccode.springdemo.model.ContactModel;

@Component("contactConverter")
public class ContactConverter {

	
	public static Contact ModelToEntity(ContactModel m){
		Contact c = new Contact();
		c.setCity(m.getCity());
		c.setFirstname(m.getFirstname());
		c.setId(m.getId());
		c.setLastname(m.getLastname());
		c.setTelephone(m.getTelephone());
		return c;
	}
	
	public static ContactModel EntityToModel(Contact e){
		ContactModel m = new ContactModel();
		m.setCity(e.getCity());
		m.setFirstname(e.getFirstname());
		m.setId(e.getId());
		m.setLastname(e.getLastname());
		m.setTelephone(e.getTelephone());
		return m;
	}
}
