package pl.mzuchnik.springsecurityhomework6.config.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Collections;

@Component
public class JwtFilter extends OncePerRequestFilter {

    /*Metoda przechwytuje każde rządanie */
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //Pobieramy token'u
        String authorization = httpServletRequest.getHeader("Authorization");

        //Pobierz klucz publiczny
        String encodedPublicKey = httpServletRequest.getHeader("Certification");
        Base64.Decoder decoder = Base64.getDecoder();

        //Dekodowanie klucza
        RSAPublicKey publicKey = null;
        try {
            KeyFactory kf = KeyFactory.getInstance("RSA");
            publicKey = (RSAPublicKey) kf.generatePublic(new X509EncodedKeySpec(decoder.decode(encodedPublicKey)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Dekodowanie token'u
        JWTVerifier jwtVerifier = JWT.require(Algorithm.RSA512(publicKey,null)).build();
        DecodedJWT decode = jwtVerifier.verify(authorization.substring(7));

        //Wyciąganie wartości o użytkowniku
        Claim name = decode.getClaim("name");
        Claim role = decode.getClaim("role");

        //Tworze sesje użytkownika na podstawie JWT
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        name.asString(),
                        "",
                        Collections.singleton(new SimpleGrantedAuthority(
                                "ROLE_"+role.asString()))));

        System.out.println(name.asString() + " z rolą "+role.asString() +" prosi o dostęp do zasobu "+ httpServletRequest.getRequestURL());

        //Przekieruj rządanie dalej
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
