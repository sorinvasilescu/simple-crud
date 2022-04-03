package com.sorinvasilescu.simplecrud.model.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class CNPValidator implements ConstraintValidator<CNP, String> {
    @Override
    public boolean isValid(String cnp, ConstraintValidatorContext constraintValidatorContext) {
        // first things first, check the length
        if (cnp.length() != 13) return false;
        // check that the following 6 digits are a date
        LocalDate birthDate;
        try {
            birthDate = LocalDate.parse(cnp.substring(1,7));
        } catch (DateTimeParseException e) {
            return false;
        }
        // check the first digit
        switch (cnp.charAt(0)) {
            case 1:
            case 2:
                if (birthDate.getYear() > 1999 || birthDate.getYear() < 1900) return false;
                break;
            case 3:
            case 4:
                if (birthDate.getYear() > 1899) return false;
            case 5:
            case 6:
                if (birthDate.getYear() < 2000) return false;
            default:
                // do nothing, there's nothing to check for 7,8 and 9
        }
        // calculate and check the control digit
        String controlKey = "279146358279";
        int result = 0;
        for (int i=0; i<13; i++) {
            result += Integer.parseInt(cnp.substring(i, i+1)) * Integer.parseInt(controlKey.substring(i, i+1));
        }
        int controlDigit = result % 11;
        if (controlDigit == 10) controlDigit = 1;
        return controlDigit == cnp.charAt(12);
    }
}
