package uz.bprodevelopment.base.auth.confirmation_token;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uz.bprodevelopment.base.user.User;
import uz.bprodevelopment.base.util.AppUtils;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {

    private final ConfirmationTokenRepo repo;

    @Override
    @Transactional
    public String createConfirmationToken(User user) {
        String token = AppUtils.generateRandomString(64);

        while (repo.existsByToken(token)) {
            token = AppUtils.generateRandomString(64);
        }

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );

        repo.save(confirmationToken);

        return token;
    }

    @Override
    public ConfirmationToken getToken(String token) {
        return repo.findByToken(token);
    }

}
