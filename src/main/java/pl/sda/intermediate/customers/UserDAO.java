package pl.sda.intermediate.customers;


import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserDAO {

    private static final String USERS_DATA_TXT = "c:/projects/users_data.txt";
    private Map<String, User> userMap = new HashMap<>();

    {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(USERS_DATA_TXT))) {
            userMap = (Map<String, User>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean userExist(String eMail) {
        return userMap.containsKey(eMail);
    }

    public void saveUser (User user){
        userMap.put(user.getEMail(),user);

        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(USERS_DATA_TXT))) {
            objectOutputStream.writeObject(userMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Optional<User> findUserByEmail (String eMail){
        return Optional.ofNullable(userMap.get(eMail));
    }
}
