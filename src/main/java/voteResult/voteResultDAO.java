package voteResult;

import DB.DBDAO;
import vote.voteDAO;
import vote.voteDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class voteResultDAO {
    private static voteResultDAO instance = null;

    private static DBDAO DB = DBDAO.getInstance();

    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    public static voteResultDAO getInstance() {
        if(instance == null)
            instance = new voteResult.voteResultDAO();
        return instance;
    }

    public void showVoteResult(int voteResult_id, int vote_id, int voteVar_id, String content, String nickName) {
        String query =
                "INSERT INTO `DB_ppick`.`voteResult`" +
                        "(" +
                        "`vote_id`," +
                        "`voteVar_id`," +
                        "`content`," +
                        "`nickName`" +
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
            pstmt.setInt(1, vote_id);
            pstmt.setInt(2, voteVar_id);
            pstmt.setString(3, content);
            pstmt.setString(4, nickName);
            pstmt.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) try {rs.close();}catch(SQLException ex ) {}
            if(pstmt != null) try {pstmt.close();}catch(SQLException ex) {}
            if(conn != null) try {conn.close();}catch(SQLException ex) {}
        }
    }
    public List<voteResultDTO> getVoteResultList(int vote_id) {
        List<voteResultDTO> voteResultList = null;
        String query =
                "SELECT " +
                        "`voteResult`.`voteResult_id`," +
                        "`voteResult`.`vote_id`," +
                        "`voteResult`.`voteVar_id`," +
                        "`voteResult`.`content`," +
                        "`voteResult`.`nickName`" +
                        "FROM `DB_ppick`.`voteResultDAO` WHERE vote_id = ?";
        try {
            conn = DB.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, vote_id);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                voteResultList = new ArrayList<voteResultDTO>();
                do {
                    voteResultDTO vote = new voteResultDTO();
                    vote.setVoteResult_id(rs.getInt("voteResult_id"));
                    vote.setVote_id(rs.getInt("vote_id"));
                    vote.setVoteVar_id(rs.getInt("voteVar_id"));
                    vote.setContent(rs.getString("content"));
                    vote.setNickName(rs.getString("nickName"));
                    voteResultList.add(vote);
                }while(rs.next());
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) try {rs.close();}catch(SQLException ex ) {}
            if(pstmt != null) try {pstmt.close();}catch(SQLException ex) {}
            if(conn != null) try {conn.close();}catch(SQLException ex) {}
        }
        return voteResultList;
    }
}
