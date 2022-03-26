package com.sl.guardianbackend.security.service;

import com.sl.guardianbackend.security.model.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@AllArgsConstructor
@Data
@Service
public class PassServiceImpl implements PassService {

    private final AppUsersServiceImpl appUsersService;
    private final PasswordEncoder passwordEncoder;

    public String generatePassword() {
        Random random = new Random();
        char[] ll = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'r', 's', 'q', 'u', 'w', 'x', 'y', 'z'};
        char[] ul = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'R', 'S', 'T', 'U', 'W', 'X', 'Y', 'Z'};
        char[] number = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        char[] sign = {'!', '@', '#', '$'};
        char[][] pasValue = new char[][]{ll, ul, number, sign};
        int[] randomNumbers = new int[8];

        char[] password = new char[8];
        int randomNumber = 0;
        int randomNumberPlace = 0;

        for (int i = 0; i < 8; i++) {
            randomNumber = random.nextInt(pasValue.length);
            if (randomNumber == 0) {
                randomNumberPlace = random.nextInt(ll.length);
                randomNumbers[i] = randomNumber;
            } else if (randomNumber == 1) {
                randomNumberPlace = random.nextInt(ul.length);
                randomNumbers[i] = randomNumber;
            } else if (randomNumber == 2) {
                randomNumberPlace = random.nextInt(number.length);
                randomNumbers[i] = randomNumber;
            } else {
                randomNumberPlace = random.nextInt(sign.length);
                randomNumbers[i] = randomNumber;
            }

            password[i] = pasValue[randomNumber][randomNumberPlace];
        }
        boolean isLL = false;
        boolean isUL = false;
        boolean isNumber = false;
        boolean isSign = false;

        for (int j : randomNumbers) {
            if (j == 0) {
                isLL = true;
            } else if (j == 1) {
                isUL = true;
            } else if (j == 2) {
                isNumber = true;
            } else {
                isSign = true;
            }
        }
        if (!isLL) {
            password[2] = ll[random.nextInt(ll.length)];
        }
        if (!isUL) {
            password[4] = ul[random.nextInt(ul.length)];
        }
        if (!isNumber) {
            password[6] = number[random.nextInt(number.length)];
        }
        if (!isSign) {
            password[7] = sign[random.nextInt(sign.length)];
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (char c : password) {
            stringBuilder.append(c);
        }

        return stringBuilder.toString();
    }

    public String generateAccount(String login, String role) {
        AppUser appUser = new AppUser();
        String password = generatePassword();
        appUser.setUsername(login);
        appUser.setPassword(password);
        appUser.setNoEncodingPassword(password);

        appUsersService.saveUser(appUser);
        appUsersService.addRoleToAppUser(login, role);

        return password;
    }

    @Override
    public String changePassword(String username, String oldPassword, String newPassword) {
        String status;
        if (oldPassword.equals("")) {
            status = "Old password cannot by empty";
        } else if (newPassword.equals("")) {
            status = "New password cannot by empty";
        } else if (newPassword.length() < 8) {
            status = "the new password must be more than 8 characters long";
        }else if (!checkPassword(newPassword)) {
            status = "the new password must contain upper and lower case letters, a number and a special character(!,@,#,$)";
        } else if (oldPassword.equals(newPassword)) {
            status = "Old and new password cannot by the same";
        } else {
            AppUser appUser = appUsersService.getUser(username);
            checkPassword(newPassword);
            if (passwordEncoder.matches(oldPassword, appUser.getPassword())) {
                appUser.setPassword(newPassword);
                appUser.setNoEncodingPassword(newPassword);
                appUsersService.saveUser(appUser);
                status = "Password has been changed";
            } else {
                status = "The password cannot be changed. The old password is incorrect ";
            }
        }
        return status;
    }

    boolean checkPassword(String password) {

        char[] ll = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'r', 's', 'q', 'u', 'w', 'x', 'y', 'z'};
        char[] ul = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'R', 'S', 'T', 'U', 'W', 'X', 'Y', 'Z'};
        char[] number = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        char[] specialSign = {'!', '@', '#', '$'};

        boolean isUppercase = false;
        boolean isLowercase = false;
        boolean isNuber = false;
        boolean isSpecialChar = false;

        boolean isPasswordCorrectly = false;

        for (int i = 0; i < password.length(); i++) {
            char sign = password.charAt(i);

            for (char c : ll) {
                if (sign == c) {
                    isLowercase = true;
                    break;
                }
            }
            for (char c : ul) {
                if (sign == c) {
                    isUppercase = true;
                    break;
                }
            }
            for (char c : number) {
                if (sign == c) {
                    isNuber = true;
                    break;
                }
            }
            for (char c : specialSign) {
                if (sign == c) {
                    isSpecialChar = true;
                    break;
                }
            }
        }
        if (isLowercase && isUppercase && isNuber && isSpecialChar) {
            isPasswordCorrectly = true;
        }
        return isPasswordCorrectly;
    }
}
