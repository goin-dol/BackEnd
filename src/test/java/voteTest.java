import chatRoom.chatRoomDAO;
import vote.voteDAO;
import voteVar.voteVarDAO;
import voteResult.voteResultDAO;

import java.util.ArrayList;
import java.util.List;

public class voteTest {
    static vote.voteDAO voteDAO = vote.voteDAO.getInstance();
    public static void main(String[] args) {


        String title = "Vote1";
        boolean isAnonymous = false;
        boolean isOverLap = false;

        voteDAO.makeVote(title,isAnonymous,isOverLap);

    }
}
