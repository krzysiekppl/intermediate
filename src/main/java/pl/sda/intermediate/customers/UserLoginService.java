package pl.sda.intermediate.customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.apache.commons.codec.digest.DigestUtils.sha512Hex;

@Service
public class UserLoginService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserContextHolder userContextHolder;

    public boolean loginUser(UserLoginDTO userLoginDTO) {

        boolean isAbleToLogIn = userDAO.findUserByEmail(userLoginDTO.getLogin())
                .map(user -> compareHashes(userLoginDTO, user))
                .orElse(false);
        if(isAbleToLogIn){
            userContextHolder.logUserIn(userLoginDTO.getLogin());
        }
        return isAbleToLogIn;
    }

    private boolean compareHashes(UserLoginDTO userLoginDTO, User user) {
        return sha512Hex(userLoginDTO.getPassword()).equals(user.getPasswordHash());
    }
}
