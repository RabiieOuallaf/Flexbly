package ma.yc.flexbly.Jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {
//    @Value("${security.jwt.secret-key}")
    private final String secret_key = "mysecretkey";
//    @Value("${security.access_token-validity}")
    private long access_token_validity = 3600000;
    private final JwtParser jwtParser;
    private String TOKEN_HEADER = "AUTHORIZATION";
    private String TOKEN_PREFIX = "Bearer";

    public JwtUtil() {
        this.jwtParser = Jwts.parser().setSigningKey(secret_key);
    }

    public String generateToken(Object user) {
        Map<String, Object> claims = new HashMap<>();
        // Add any additional claims if needed

        return createToken(claims, ((UserDetails) user).getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + access_token_validity))
                .signWith(SignatureAlgorithm.HS256, secret_key)
                .compact();
    }

    public String getEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret_key).parseClaimsJws(token).getBody();
    }

    public String getRoles(String token)  {
        return extractClaim(token, claims -> claims.get("role", String.class));
    }
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String email = getEmail(token);
        return (email.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

}
