package com.securitypractice.topic7;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AccountController {

    @GetMapping("/news")
    public String getNews() {
        return "Новости доступны всем пользователям.";
    }

    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public String getProfile(@AuthenticationPrincipal UserDetails userDetails) {
        return "Профиль пользователя: " + userDetails.getUsername();
    }

    @GetMapping("/settings")
    @PreAuthorize("hasRole('MANAGER')")
    public String getSettings(@AuthenticationPrincipal UserDetails userDetails) {
        return "Настройки для пользователя: " + userDetails.getUsername();
    }

    @GetMapping("/admin/reports")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAdminReports(@AuthenticationPrincipal UserDetails userDetails) {
        return "Административные отчёты. Доступ только для администраторов.";
    }
}
