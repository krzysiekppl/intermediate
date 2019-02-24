package pl.sda.intermediate.customers;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {

    @Autowired
    private UserDAO userDAO;

    public void registerUser(UserRegistrationDTO userRegistrationDTO) {
        if (userDAO.userExist(userRegistrationDTO.getEMail())) {
            throw new UserExistsException("User exist");
        }
        User user = buildUser(userRegistrationDTO);
        userDAO.saveUser(user);

    }

    private User buildUser(UserRegistrationDTO userRegistrationDTO) {
        User user = new User();
        user.setFirstName(userRegistrationDTO.getFirstName());
        user.setLastName(userRegistrationDTO.getLastName());
        user.setBirthDate(userRegistrationDTO.getBirthDate());
        user.setEMail(userRegistrationDTO.getEMail());
        user.setPesel(userRegistrationDTO.getPesel());
        user.setPhone(userRegistrationDTO.getPhone());
        user.setPasswordHash(DigestUtils.sha512Hex(userRegistrationDTO.getPassword()));
        UserAddress userAddress = new UserAddress();
        userAddress.setCity(userRegistrationDTO.getCity());
        userAddress.setCountry(userRegistrationDTO.getCountry());
        userAddress.setZipCode(userRegistrationDTO.getZipCode());
        userAddress.setStreet(userRegistrationDTO.getStreet());
        user.setUserAddress(userAddress);
        return user;
    }
}
