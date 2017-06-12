package es.sccode.springdemo.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import es.sccode.springdemo.entity.UserRole;
import es.sccode.springdemo.repository.UserRepository;

@Service("userService")
public class  UserService implements UserDetailsService{
	
	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		es.sccode.springdemo.entity.User user = userRepository.findByUsername(username);
		List<GrantedAuthority> authorities = user.getUserRole().stream().map(UserService::roleToAutority).collect(Collectors.toList());
		return buildUser(user, authorities);
	}
	
	private User buildUser(es.sccode.springdemo.entity.User user,List<GrantedAuthority> authorities){
		return new User(user.getUsername(), user.getPassword(), user.isEnabled(), 
				true, true, true, authorities);
	}
	
	
	private static GrantedAuthority roleToAutority(UserRole userRole){
		return new SimpleGrantedAuthority(userRole.getRole());
	}
	
	

}
