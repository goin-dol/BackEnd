import user.userDAO;

import java.sql.SQLIntegrityConstraintViolationException;

public class Main {

    public static void main(String[] args) {

        userDAO userdao = userDAO.getInstance();

        String userId = "zxz4641";
        String userPassword = "3249";
        String nickName="";


        //회원가입
        //userdao.signUp(userId,userPassword,nickName);

        //로그인
        //userdao.login(userId, userPassword, "127.0.0.1");

        //로그아웃(프로그램 닫을시 호출)
        userdao.logout(userId);
    }
}
