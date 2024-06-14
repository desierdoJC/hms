package io.nichan.hms.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import io.nichan.hms.security.CustomUserDetails;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute
    public void addUserDetailsToModel(Model model){
        //Save current user details
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof CustomUserDetails) {
                CustomUserDetails userDetails = (CustomUserDetails) principal;
                model.addAttribute("currentUser", userDetails);
            }
        }
    }
}
