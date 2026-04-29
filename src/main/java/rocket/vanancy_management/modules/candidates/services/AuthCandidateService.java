package rocket.vanancy_management.modules.candidates.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rocket.vanancy_management.modules.candidates.CandidateRepository;
import rocket.vanancy_management.modules.candidates.dto.AuthCandidateReqDTO;
import rocket.vanancy_management.modules.candidates.dto.AuthCandidateResponseDTO;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthCandidateService {

    @Value("${security.token.secret.candidate}")
    private String secretKey;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCandidateResponseDTO execute(AuthCandidateReqDTO authCandidateReqDTO) throws AuthenticationException {

        var candidate = this.candidateRepository.findByUsername(authCandidateReqDTO.username())
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("Username/password invalid.");
                });

        var passwordMatches = this.passwordEncoder.matches(authCandidateReqDTO.password(), candidate.getPassword());

        if(!passwordMatches) {
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var token = JWT.create()
                .withIssuer("javagas")
                .withExpiresAt(Instant.now().plus(Duration.ofMinutes(10)))
                .withClaim("roles", Arrays.asList("candidate"))
                .withSubject(candidate.getId().toString())
                .sign(algorithm);

        var authCandidateResponse = AuthCandidateResponseDTO.builder()
                .access_token(token)
                .build();

        return authCandidateResponse;
    }
}
