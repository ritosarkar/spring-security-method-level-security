package com.springSecurity.config;

import com.springSecurity.model.Customers;
import com.springSecurity.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SecurityDbUserDetailsService implements UserDetailsService {


    private final CustomerRepository customerRepository;
    /**
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Customers customers= customerRepository.findByEmail(username)
               .orElseThrow(()->new UsernameNotFoundException(username+" not found!!"));
      /*
      Here we have to convert our customer object into corresponding user object which is an implementation of the
      UserDetails interface.
      Details that we have to populate a) userName, b) password, c) List of granted authorities.
      */

       List<GrantedAuthority> grantedAuthorityList= customers.getAuthorities()
                                                         .stream()
                                                         .map(auth->new SimpleGrantedAuthority(auth.getName()))
                                                         .collect(Collectors.toUnmodifiableList());
       return  new User(customers.getEmail(),customers.getPwd(),grantedAuthorityList);
    }
}
