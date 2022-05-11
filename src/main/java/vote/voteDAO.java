package vote;

import DB.DBDAO;
import chatRoom.chatRoomDTO;
import user.userDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class voteDAO {
    private static voteDAO instance = null;

    private static DBDAO DB = DBDAO.getInstance();

    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    public static voteDAO getInstance() {
        if(instance == null)
            instance = new vote.voteDAO();
        return instance;
    }

    public void makeVote(int chatRoom_id, String title, boolean isAnonymous, boolean isOverLap) {
        String query =
                "INSERT INTO `DB_ppick`.`vote`" +
                        "(" +
                        "`chatRoom_id`," +
                        "`title`," +
                        "`isAnonymous`," +
                        "`isOveLap`" +
                        ")" +
                        "VALUES" +
                        "(" +
                        "?," +
                        "?," +
                        "?," +
                        "?" +
                        ")";
        try {
            conn = DB.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, chatRoom_id);
            pstmt.setString(2, title);
            pstmt.setBoolean(3,false);
            pstmt.setBoolean(4, false);

            pstmt.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) try {rs.close();}catch(SQLException ex ) {}
            if(pstmt != null) try {pstmt.close();}catch(SQLException ex) {}
            if(conn != null) try {conn.close();}catch(SQLException ex) {}
        }
    }

    public List<voteDTO> getVoteList(int chatRoom_id) {
        List<voteDTO> voteList = null;
        String query =
                "SELECT " +
                        "`vote`.`vote_id`," +
                        "`vote`.`chatRoom_id`," +
                        "`vote`.`title`," +
                        "`vote`.`isAnonymous`," +
                        "`vote`.`isOverLap`" +
                        "FROM `DB_ppick`.`vote` WHERE chatRoom_id = ?";
        try {
            conn = DB.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, chatRoom_id);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                voteList = new ArrayList<voteDTO>();
                do {
                    voteDTO vote = new voteDTO();
                    vote.setVote_id(rs.getInt("vote_id"));
                    vote.setChatRoom_id(rs.getInt("chatRoom_id"));
                    vote.setTitle(rs.getString("title"));
                    vote.setAnonymous(rs.getBoolean("isAnonymous"));
                    vote.setOverLap(rs.getBoolean("isOverLap"));
                    voteList.add(vote);
                }while(rs.next());
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) try {rs.close();}catch(SQLException ex ) {}
            if(pstmt != null) try {pstmt.close();}catch(SQLException ex) {}
            if(conn != null) try {conn.close();}catch(SQLException ex) {}
        }
        return voteList;
    }
}