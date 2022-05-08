import user.userDAO;

public class Main {

    public static void main(String[] args) {

        String userId = "abc";
        String userPassword = "abc";
        String nickName="CP6";

        userDAO userdao = userDAO.getInstance();

        userdao.signUp(userId,userPassword,nickName);

    }
}
