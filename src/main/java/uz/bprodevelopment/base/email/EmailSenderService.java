package uz.bprodevelopment.base.email;

public interface EmailSenderService {


    void sendConfirmationLink(String toEmail, String receiverName, String confirmationLink);

}
