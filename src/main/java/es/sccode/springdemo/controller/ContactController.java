package es.sccode.springdemo.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import es.sccode.springdemo.entity.Contact;
import es.sccode.springdemo.model.ContactModel;
import es.sccode.springdemo.service.ContactService;

@Controller
@RequestMapping("/contacts")
public class ContactController {
	
	private static final Log LOG = LogFactory.getLog(ContactController.class);
	
	public static final String CONTACT_FORM="contactform";
	public static final String CONTACTS="contacts";
	
	
	@Autowired
	@Qualifier("contactService")
	ContactService contactService;
	
//	@GetMapping("/contactsform")
//	public ModelAndView redirectContactForm(){
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("contactModel",new ContactModel());
//		return mv;
//	}
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@GetMapping("/contactsform")
	public String redirectContactForm(Model model,
			@RequestParam(name="id",required=true,defaultValue="0")int id){
		ContactModel c = new ContactModel();
		if(id>0)
		c = contactService.getContactModelById(id);
		model.addAttribute("contactModel",c);
		return CONTACT_FORM;
	}
	
	@GetMapping("/cancel")
	public String cancel(){
		return "redirect:/contacts/showcontacts";
	}
	
	@PostMapping("/addcontact")
	public String addContact(@ModelAttribute(name="contactModel") ContactModel contactModel,
			Model model){
		LOG.info(contactModel.toString());
		model.addAttribute("result",contactService.addContact(contactModel)!=null?1:0);
		return "redirect:/contacts/showcontacts";
	}
	
	@GetMapping("/showcontacts")
	public ModelAndView showContacts(){
		ModelAndView mv = new ModelAndView(CONTACTS);
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		mv.addObject("username",user.getUsername());
		mv.addObject("contacts",contactService.listAllContacts());
		return mv;
	}
	
//	@DeleteMapping TODO implementarlo con deletemaping
	@GetMapping("removecontact")
	public ModelAndView removeContact(@RequestParam(name="id",required=true)int id){
		ModelAndView mv = new ModelAndView();
		contactService.removeContact(id);
		return showContacts();
	}
	

}
