package ma.yc.flexbly.Jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {
    @Value("${secret_key}")
    private String secret_key;
    @Value("${access_token_validity}")
    private long access_token_validity;

    private final JwtParser jwtParser;
    private String TOKEN_HEADER = "AUTHORIZATION";
    private String TOKEN_PREFIX = "Bearer";

    public JwtUtil() {
        this.jwtParser = Jwts.parser().setSigningKey(secret_key);
    }

    public String createToken(Object user) {
        return null;
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
