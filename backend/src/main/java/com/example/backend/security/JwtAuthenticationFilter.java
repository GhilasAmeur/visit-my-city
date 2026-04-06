package com.example.backend.security;

import com.example.backend.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
// filtre pour valider les requètes entrantes
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserLoader userLoader;

    public JwtAuthenticationFilter(JwtService jwtService, UserLoader userLoader) {
        this.jwtService = jwtService;
        this.userLoader = userLoader;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        // 1. Lire le header Authorization
        String authHeader = request.getHeader("Authorization");
        System.out.println("HEADER = " + authHeader);


        // 2. Si pas de token → on laisse passer
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 3. Extraire le token (supprimer "Bearer ")
        String token = authHeader.substring(7);

        // 4. Extraire username depuis le token
        String username = jwtService.extractUsername(token);

        // 5. Charger utilisateur depuis la base
        var userDetails = userLoader.loadUserByUsername(username);

        // 6. Vérifier si token valide
        if (jwtService.isTokenValid(token, userDetails)) {

            // 7. Créer authentification Spring
            var authentication =
                    new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities());

            // 8. Sauvegarder utilisateur connecté
            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);
        }

        // 9. Continuer vers controller
        filterChain.doFilter(request, response);

    }
}
