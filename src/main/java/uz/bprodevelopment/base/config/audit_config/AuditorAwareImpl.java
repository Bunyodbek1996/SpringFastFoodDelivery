package uz.bprodevelopment.base.config.audit_config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import uz.bprodevelopment.base.user.User;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<Long> {

    @SuppressWarnings("NullableProblems")
    @Override
    public Optional<Long> getCurrentAuditor() {
        UsernamePasswordAuthenticationToken authentication = null;
        if (!( SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)){
            authentication = (UsernamePasswordAuthenticationToken)
                    SecurityContextHolder.getContext().getAuthentication();
        }
        Long userId = null;
        if(authentication != null) {
            userId = ((User) authentication.getPrincipal()).getId();
        }
        return Optional.ofNullable(userId);
    }

}
