package com.example.common;

import com.example.domain.Administrator;
import com.example.repository.AdministratorRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private AdministratorRepository administratorRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        String mailAddress = authentication.getName(); // ログインID（mailAddress）
        Administrator admin = administratorRepository.findByMailAddress(mailAddress);

        HttpSession session = request.getSession();
        if (admin != null) {
            session.setAttribute("administratorName", admin.getName());
        }

        // デフォルトの遷移先へリダイレクト
        getRedirectStrategy().sendRedirect(request, response, "/employee/showList");
    }
}
