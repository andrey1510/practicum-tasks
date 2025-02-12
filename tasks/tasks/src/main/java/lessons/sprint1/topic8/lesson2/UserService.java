package lessons.sprint1.topic8.lesson2;

public class UserService {

    UserRepository userRepository = new UserRepository();

    public String getUserNameById(int id){
        return userRepository.findUserNameById(id);
    }

}
