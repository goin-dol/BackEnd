import user.userDAO;

public class Main {

    public static void main(String[] args) {

        String userId = "snowooden";
        String userPassword = "1234";
        String nickName="JungWoo";

        userDAO userdao = userDAO.getInstance();

        userdao.signUp(userId,userPassword,nickName);

    }
}
