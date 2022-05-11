package voteVar;

import DB.DBDAO;
import vote.voteDAO;
import vote.voteDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class voteVarDAO {
    private static voteVarDAO instance = null;

    private static DBDAO DB = DBDAO.getInstance();

    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    public static voteVarDAO getInstance() {
        if(instance == null)
            instance = new voteVar.voteVarDAO();
        return instance;
    }

    public void makeVoteVar(int voteVar_id, int vote_id, String content) {
        String query =
                "INSERT INTO `DB_ppick`.`voteVar`" +
                        "(" +
                        "`vote_id`," +
                        "`content`" +
                        ")" +
                        "VALUES" +
                        "(" +
                        "?," +
                        "?," +
                        ")";
        try {
            conn = DB.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, vote_id);
            pstmt.setString(2, content);
            pstmt.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) try {rs.close();}catch(SQLException ex ) {}
            if(pstmt != null) try {pstmt.close();}catch(SQLException ex) {}
            if(conn != null) try {conn.close();}catch(SQLException ex) {}
        }
    }

    public List<voteVarDTO> getVoteVarList(int vote_id) {
        List<voteVarDTO> voteVarList = null;
        String query =
                "SELECT " +
                        "`voteVar`.`voteVar_id`," +
                        "`voteVar`.`vote_id`," +
                        "`voteVar`.`content`" +
                        "FROM `DB_ppick`.`voteVar` WHERE vote_id = ?";
        try {
            conn = DB.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, vote_id);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                voteVarList = new ArrayList<voteVarDTO>();
                do {
                    voteVarDTO vote = new voteVarDTO();
                    vote.setVoteVar_id(rs.getInt("voteVar_id"));
                    vote.setVote_id(rs.getInt("vote_id"));
                    vote.setContent(rs.getString("content"));
                    voteVarList.add(vote);
                }while(rs.next());
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) try {rs.close();}catch(SQLException ex ) {}
            if(pstmt != null) try {pstmt.close();}catch(SQLException ex) {}
            if(conn != null) try {conn.close();}catch(SQLException ex) {}
        }
        return voteVarList;
    }
}