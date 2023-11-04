package uz.bprodevelopment.base.auth.confirmation_token;

import uz.bprodevelopment.base.user.User;


public interface ConfirmationTokenService {

    String createConfirmationToken(User user);

    ConfirmationToken getToken(String token);

}
