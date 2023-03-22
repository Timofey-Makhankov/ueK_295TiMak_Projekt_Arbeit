package ch.timofey.sb.domain.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Log4j2
public class UserService implements UserDetailsService {
    UserRepository repository;

    public UserService(UserRepository userRepository){
        repository = userRepository;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findUserByUsername(username).map(UserDetailsImpl::new).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public record UserDetailsImpl(User user) implements UserDetails {
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return user.getRuleSet().stream().flatMap(r -> r.getAuthoritySet().stream())
                    .map(a -> new SimpleGrantedAuthority(a.getName()))
                    .toList();
        }

        @Override
        public String getPassword() {
            return user.getPassword();
        }

        @Override
        public String getUsername() {
            return user.getUsername();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}
