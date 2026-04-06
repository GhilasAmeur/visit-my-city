package com.example.backend.services;

import com.example.backend.entities.AppUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


    @Service
    public class JwtService {

        private static final String CLE_SECRET = "12KDHFKUNF87ezfcvfyfpnc8888887dkhdkdjncjdhnZchdhdkzpp";





        // =========================
        // 1. Créer le token
        // =========================
        public String generateToken(UserDetails userDetails) {


            String username = userDetails.getUsername();
            String role = "";

            for (GrantedAuthority auth : userDetails.getAuthorities()) {
                role = auth.getAuthority();
            }

            Date now = new Date();

            long thirtyDaysInMillis = 1000L * 60 * 60 * 24 * 30;
            Date expiration = new Date(System.currentTimeMillis() + thirtyDaysInMillis); // 30 jours

            return Jwts.builder()
                    .setSubject(username)
                    .claim("role", role)
                    .setIssuedAt(now)
                    .setExpiration(expiration)
                    .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                    .compact();
        }


        // =========================
        // 2. Récupérer username
        // =========================
        public String extractUsername(String token) {

            Claims claims = parseToken(token);

            return claims.getSubject();
        }


        // =========================
        // 3. Vérifier expiration
        // =========================
        public boolean isTokenExpired(String token) {

            Claims claims = parseToken(token);
            System.out.println(claims); // voici le résultat : {sub=ghilas@expert.com, role=ROLE_EXPERT, iat=1770805665, exp=1770809265}
            Date expiration = claims.getExpiration();

            return expiration.before(new Date());
        }


        // =========================
        // 4. Vérifier validité token
        // =========================
        public boolean isTokenValid(String token, UserDetails userDetails) {

            String username = extractUsername(token);

            boolean sameUser = username.equals(userDetails.getUsername());

            boolean expired = isTokenExpired(token);

            return sameUser && !expired;
        }


        // =========================
        // 5. Parser le token (méthode simple)
        // =========================
        private Claims parseToken(String token) {

            return Jwts.parserBuilder()
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }


        // =========================
        // 6. Clé secrète
        // =========================
        private Key getSignInKey() {

            return Keys.hmacShaKeyFor(CLE_SECRET.getBytes());
        }
    }




