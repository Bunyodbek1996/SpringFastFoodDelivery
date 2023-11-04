package uz.bprodevelopment.base.util;


import org.springframework.security.core.context.SecurityContextHolder;
import uz.bprodevelopment.base.user.User;

import java.util.Locale;
import java.util.Random;
import java.util.regex.Pattern;

import static uz.bprodevelopment.base.constant.Constants.ROLE_ADMIN;
import static uz.bprodevelopment.base.constant.Constants.ROLE_WAITER;

public final class AppUtils {

    public static User getCurrentUser(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static String getCurrentUsername(){
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

    public static Long getCurrentUserId(){
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }

    public static Boolean isAdmin(){
        return ((User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal())
                .getRoles().stream()
                .anyMatch(role -> role.getName().equals(ROLE_ADMIN));
    }

    public static Boolean isWaiter(){
        return ((User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal())
                .getRoles().stream()
                .anyMatch(role -> role.getName().equals(ROLE_WAITER));
    }


    public static String generateRandomString(Integer length) {
        final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String lower = upper.toLowerCase(Locale.ROOT);
        final String digits = "0123456789";
        final String alphaNum = upper + lower + digits;
        final Random random = new Random();

        StringBuilder randomString = new StringBuilder();

        for (int i = 0; i < length; i++) {
            randomString.append(alphaNum.charAt(random.nextInt(alphaNum.length())));
        }
        return randomString.toString();
    }

    public static boolean patternMatches(String arg, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(arg)
                .matches();
    }


    public static double distanceBetweenLocations(double lat1, double lon1, double lat2, double lon2) {

        final int R = 6371;

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // convert to kilo meters

    }

}
