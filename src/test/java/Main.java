import user.userDAO;
import vote.voteDAO;
import voteResult.voteResultDAO;
import voteVar.voteVarDAO;

import java.sql.SQLIntegrityConstraintViolationException;

public class Main {

    public static void main(String[] args) {

        String userId = "snowooden";
        String userPassword = "1234";
        String nickName="JungWoo";

        userDAO userdao = userDAO.getInstance();


        //회원가입
        //userdao.signUp(userId,userPassword,nickName);

        //로그인
        //userdao.login(userId, userPassword, "127.0.0.1");

        //로그아웃(프로그램 닫을시 호출)
        userdao.logout(userId);
    }
}
