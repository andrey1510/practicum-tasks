package com.securitypractice.topic3;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Map;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;

public class Application {

    static class User implements UserDetails {
        private final String login;
        private final String password;

        public User(String login, String password) {
            this.login = login;
            this.password = password;
        }

        @Override
        public String getUsername() {
            return login;
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public java.util.Collection<? extends GrantedAuthority> getAuthorities() {
            return Collections.emptyList();
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

    static class InMemoryUserDetailsService implements UserDetailsService {

        private final Map<String, User> users;

        public InMemoryUserDetailsService(Map<String, User> users) {
            this.users = users;
        }

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            User user = users.get(username);
            if (user == null) {
                throw new UsernameNotFoundException("User not found: " + username);
            }
            return user;
        }
    }

    public static void main(String[] args) {
        Map<String, User> users = Map.of(
            "anonymous", new User("anonymous", "lkjf4525"),
            "admin", new User("admin", "x92a6u17"),
            "manager", new User("manager", "s7i69ldz")
        );
        UserDetailsService userDetailsService = new InMemoryUserDetailsService(users);

        for (String username : users.keySet()) {
            UserDetails user = userDetailsService.loadUserByUsername(username);
            System.out.println("Пользователь " + username + " найден!");
        }

        try {
            userDetailsService.loadUserByUsername("unknown");
        } catch (UsernameNotFoundException e) {
            System.out.println("Пользователь unknown не найден");
        }
    }
}