package pl.sda.intermediate.customers;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserValidationService {

    public Map<String,String> validateUser (UserRegistrationDTO userRegistrationDTO){
        Map<String,String> errorMap = new HashMap<>();

        if(!userRegistrationDTO.getFirstName().matches("[A-Z][a-z]{2,}")){
            errorMap.put("firstNameValRes","Blędna wartość. Conajmniej 3 litery, w tym pierwsza wielka.");
        }
        if(!userRegistrationDTO.getLastName().matches("[A-Z][a-z]{2,}(-[A-Z][a-z]{2,})?")){
            errorMap.put("lastNameValRes","Blędna wartość. Conajmniej 3 litery, w tym pierwsza wielka.");
        }
        if(!userRegistrationDTO.getCity().matches("[A-Z][a-z]{2,}( [A-Z][a-z]{2,})?")){
            errorMap.put("cityValRes","Blędna wartość. Conajmniej 3 litery, w tym pierwsza wielka.");
        }
        if(!userRegistrationDTO.getZipCode().matches("[0-9]{2}-[0-9]{3}")){
            errorMap.put("zipCodeValRes","Błędny kod pocztowy.");
        }
        if(!userRegistrationDTO.getBirthDate().matches("(19|20)[0-9]{2}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])")){
            errorMap.put("birthDateValRes","Błędna data urodzin.");
        }
        if (!userRegistrationDTO.getEMail().matches("^[\\w\\.]+@([A-Za-z0-9]+\\.){1,2}[A-Za-z]{2,4}$")){
            errorMap.put("emailValRes","Błędny adres email.");
        }
        if (!userRegistrationDTO.getPhone().matches("^(\\+[0-9]{1,3} )?([0-9]{9}|[0-9]{3}-[0-9]{3}-[0-9]{3})$")){
            errorMap.put("phoneValRes","Błędny numer telefonu.");
        }
        return errorMap;
    }
}
