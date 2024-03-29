package ma.yc.flexbly.Jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import ma.yc.flexbly.Models.Entities.AdminEntity;
import ma.yc.flexbly.Models.Entities.JobSeekerEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;

@Service
public class JwtUtil {
//    @Value("${security.jwt.secret-key}")
    private final String secret_key = "mysecretkey";
//    @Value("${security.access_token-validity}")
    private long access_token_validity = 3600000;
    private long refresh_token_validity = 86400000;
    private final JwtParser jwtParser;
    private String TOKEN_HEADER = "AUTHORIZATION";
    private String TOKEN_PREFIX = "Bearer";

    public JwtUtil() {
        this.jwtParser = Jwts.parser().setSigningKey(secret_key);
    }


    public String generateJobSeekerAccessToken(JobSeekerEntity jobSeekerEntity) {
        Claims claims = Jwts.claims().setSubject(jobSeekerEntity.getEmail());
        claims.put("role", jobSeekerEntity.getRole());
        claims.put("email", jobSeekerEntity.getEmail());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + access_token_validity))
                .signWith(SignatureAlgorithm.HS256, secret_key)
                .compact();
    }

    public String generateJobSeekerRefreshToken(JobSeekerEntity jobSeekerEntity) {
        Claims claims = Jwts.claims().setSubject(jobSeekerEntity.getEmail());
        claims.put("role", jobSeekerEntity.getRole());
        claims.put("email", jobSeekerEntity.getEmail());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + refresh_token_validity))
                .signWith(SignatureAlgorithm.HS256, secret_key)
                .compact();
    }

    public String generateAdminAccessToken(AdminEntity adminEntity) {
        Claims claims = Jwts.claims().setSubject(adminEntity.getEmail());
        claims.put("role", adminEntity.getRole());
        claims.put("email", adminEntity.getEmail());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + access_token_validity))
                .signWith(SignatureAlgorithm.HS256, secret_key)
                .compact();

    }

    public String generateAdminRefreshToken(AdminEntity adminEntity) {
        Claims claims = Jwts.claims().setSubject(adminEntity.getEmail());
        claims.put("role", adminEntity.getRole());
        claims.put("email", adminEntity.getEmail());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + refresh_token_validity))
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
