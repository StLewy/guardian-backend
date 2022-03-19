package com.sl.guardianbackend.Service;

import com.sl.guardianbackend.Model.Code;
import com.sl.guardianbackend.Repository.CodesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CodesServiceImpl implements CodesService {
  private final CodesRepository codesRepository;
  @Override
  public String generateCode() {
    Random random = new Random();
    char[] ll = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'r', 's', 'q', 'u', 'w', 'x', 'y', 'z'};
    char[] ul = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'R', 'S', 'T', 'U', 'W', 'X', 'Y', 'Z'};
    char[] number = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    char[] sign = {'!', '@', '#', '$'};

    char[][] pasValue = new char[][]{ll, ul, number, sign};
    int[] randomNumbers = new int[4];

    char[] code = new char[4];
    int randomNumber = 0;
    int randomNumberPlace = 0;

    for (int i = 0; i < 4; i++) {
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

      code[i] = pasValue[randomNumber][randomNumberPlace];
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
      code[0] = ll[random.nextInt(ll.length)];
    }
    if (!isUL) {
      code[1] = ul[random.nextInt(ul.length)];
    }
    if (!isNumber) {
      code[2] = number[random.nextInt(number.length)];
    }
    if (!isSign) {
      code[3] = sign[random.nextInt(sign.length)];
    }

    StringBuilder stringBuilder = new StringBuilder();
    for (char c : code) {
      stringBuilder.append(c);
    }

    return stringBuilder.toString();
  }

  @Override
  public boolean checkCode(String code) {
    boolean isCode = false;
    Optional<Code> codeDB = codesRepository.findByGenerateCode(code);
    if (codeDB.isPresent()){
      isCode = true;
    }
    return isCode;
  }

  @Override
  public String getCode() {
    String code;
    boolean isCode;
    do {
      code = generateCode();
      isCode = checkCode(code);
    }while (isCode);

    Code newCode = new Code();
    newCode.setGenerateCode(code);
    newCode.setRegistration("N");
    newCode.setCreation(LocalDate.now());

    codesRepository.save(newCode);
    return code;
  }

  public Optional<Code> findByGenerateCodeAndRegistration(String generateCode, String registration) {
    return codesRepository.findByGenerateCodeAndRegistration(generateCode, registration);
  }

  @Override
  public void updateCode(Code code) {
    code.setRegistration("Y");
    code.setUpdate(LocalDate.now());
    codesRepository.save(code);
  }
}
