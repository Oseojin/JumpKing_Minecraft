package org.osj.jumpking.db;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.osj.jumpking.user.PlayerScoreBoard;
import org.osj.jumpking.user.management.controller.UserManagementController;
import org.osj.jumpking.user.management.entity.User;

import java.sql.*;
import java.util.UUID;

public class db_connect
{
    private static db_connect instance;

    public static db_connect getInstance()
    {
        if(instance == null)
        {
            synchronized (db_connect.class)
            {
                instance = new db_connect();
            }
        }

        return instance;
    }

    private Connection connection;

    private String host = "localhost";
    private int port = 3306;
    private String database = "mc_jumpking";
    private String table = "userdata";
    private String username = "root";
    private String password = "cAtQus3Wexia7HU*qVoU";

    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    public Connection open_Connection()
    {
        try
        {
            if (connection != null && !connection.isClosed())
            {
                return null;
            }

            synchronized (this)
            {
                if (connection != null && !connection.isClosed())
                {
                    return null;
                }
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://" + this.host+ ":" + this.port + "/" + this.database + "?autoReconnect=true", this.username, this.password);
                if(connection == null)
                {
                    Bukkit.shutdown();
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        return connection;
    }

    public void SetDisplayName(Player player, String name)
    {
        String coloumName = "u_name";
        SetData(player, coloumName, name);
    }
    public void SetJumpingCoin(Player player, Long coin)
    {
        String coloumName = "u_jumpingcoin";
        SetData(player, coloumName, coin);
    }

    public void SetGold(Player player, Long gold)
    {
        String coloumName = "u_gold";
        SetData(player, coloumName, gold);
    }

    public void SetMaxHeight(Player player, Integer maxHeight)
    {
        String coloumName = "u_maxheight";
        SetData(player, coloumName, maxHeight);
    }

    public int insertMember(Player player)
    {
        // 예외 처리 안해주고 오류 발생하면 서버 터짐
        Connection conn = null;
        try
        {
            conn = this.open_Connection();
            // 사용자의 정보가 있는지 없는지 확인하는 질의문을 생성한다.
            String sql = "select count(*) from " + table + " u where u.u_uuid = ?";
            // PreparedStatement에 질의어를 넣고
            pstmt = conn.prepareStatement(sql);
            // 질의어에 ?라고 적혀 있는 값을 정의 해준다.
            pstmt.setString(1, player.getUniqueId().toString());
            // 질의어 실행
            rs = pstmt.executeQuery();
            // 검색된 레코드를 넘겨줌 (필수)
            rs.next();
            // 해당하는 유저의 정보가 없는 경우
            if (rs.getInt(1) == 0)
            {
                try
                {
                    // PreparedStatement 초기화 (재사용을 위해)
                    pstmt.clearParameters();
                    sql = "INSERT INTO " + table.toUpperCase() + " (u_uuid, u_name, u_jumpingcoin, u_gold, u_maxheight) VALUES (?, ?, ?, ?, ?)";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, player.getUniqueId().toString()); // uuid
                    pstmt.setString(2, player.getName()); // name
                    pstmt.setLong(3, 0);
                    pstmt.setLong(4, 0);
                    pstmt.setInt(5, 0);
                    pstmt.executeUpdate();
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            return 0;

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 1;
        }
        finally
        {
            try
            {
                conn.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
                return 2;
            }
        }
    }

    public User db_PlayerInfo(Player player)
    {
        UUID player_UUID = player.getUniqueId();
        User user = UserManagementController.getUserManager().getUserData(player);
        Connection conn = null;
        // 예외 처리 안해주면 서버 터짐
        try
        {
            conn = this.open_Connection();
            // 사용자의 정보가 있는지 없는지 확인하는 질의문을 생성한다.
            String sql = "select u.u_no, u.u_uuid, u.u_name, u.u_jumpingcoin, u.u_gold, u.u_maxheight from " + table + " u where u.u_uuid = ?";
            // PreparedStatement에 질의어를 넣고
            pstmt = conn.prepareStatement(sql);
            // 질의어에 ?라고 적혀 있는 값을 정의 해준다.
            pstmt.setString(1, player_UUID.toString());
            // 질의어 실행
            rs = pstmt.executeQuery();
            // 검색된 레코드를 넘겨줌 (필수임 ㅇ)
            rs.next();

            user.setUuid(UUID.fromString(rs.getString("u_uuid")));
            user.loadDisplayName(rs.getString("u_name"));
            user.loadJumpingCoin(rs.getLong("u_jumpingcoin"));
            user.loadGold(rs.getLong("u_gold"));
            user.loadMaxHeight(rs.getInt("u_maxheight"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            try
            {
                // 연결 된 DB 연결을 종료 해준다. 안해주면 여러개 쌓이면 DB 검색 안됨 (즉, commit과 같은 용도)
                conn.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return user;
    }

    private int SetData(Player player, String coloum, Integer content)
    {
        // 예외 처리 안해주고 오류 발생하면 서버 터짐
        Connection conn = null;
        try
        {
            conn = this.open_Connection();
            // 사용자의 정보가 있는지 없는지 확인하는 질의문을 생성한다.
            String sql = "select count(*) from " + table + " u where u.u_uuid = ?";
            // PreparedStatement에 질의어를 넣고
            pstmt = conn.prepareStatement(sql);
            // 질의어에 ?라고 적혀 있는 값을 정의 해준다.
            pstmt.setString(1, player.getUniqueId().toString());
            // 질의어 실행
            rs = pstmt.executeQuery();
            // 검색된 레코드를 넘겨줌 (필수)
            rs.next();
            // 해당하는 유저의 정보가 없는 경우
            try
            {
                pstmt.clearParameters();
                sql = "UPDATE " + table + " SET " + coloum +  " = " + content + " WHERE u_uuid = \"" + player.getUniqueId() + "\"";
                pstmt = conn.prepareStatement(sql);
                pstmt.executeUpdate();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return 0;

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 1;
        }
        finally
        {
            try
            {
                conn.close();
                db_PlayerInfo(player);
                PlayerScoreBoard.setScoreboard(player);
            }
            catch (SQLException e)
            {
                e.printStackTrace();
                return 2;
            }
        }
    }

    private int SetData(Player player, String coloum, Long content)
    {
        // 예외 처리 안해주고 오류 발생하면 서버 터짐
        Connection conn = null;
        try
        {
            conn = this.open_Connection();
            // 사용자의 정보가 있는지 없는지 확인하는 질의문을 생성한다.
            String sql = "select count(*) from " + table + " u where u.u_uuid = ?";
            // PreparedStatement에 질의어를 넣고
            pstmt = conn.prepareStatement(sql);
            // 질의어에 ?라고 적혀 있는 값을 정의 해준다.
            pstmt.setString(1, player.getUniqueId().toString());
            // 질의어 실행
            rs = pstmt.executeQuery();
            // 검색된 레코드를 넘겨줌 (필수)
            rs.next();
            // 해당하는 유저의 정보가 없는 경우
            try
            {
                pstmt.clearParameters();
                sql = "UPDATE " + table + " SET " + coloum +  " = " + content + " WHERE u_uuid = \"" + player.getUniqueId() + "\"";
                pstmt = conn.prepareStatement(sql);
                pstmt.executeUpdate();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return 0;

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 1;
        }
        finally
        {
            try
            {
                conn.close();
                db_PlayerInfo(player);
                PlayerScoreBoard.setScoreboard(player);
            }
            catch (SQLException e)
            {
                e.printStackTrace();
                return 2;
            }
        }
    }

    private int SetData(Player player, String coloum, String content)
    {
        // 예외 처리 안해주고 오류 발생하면 서버 터짐
        Connection conn = null;
        try
        {
            conn = this.open_Connection();
            // 사용자의 정보가 있는지 없는지 확인하는 질의문을 생성한다.
            String sql = "select count(*) from " + table + " u where u.u_uuid = ?";
            // PreparedStatement에 질의어를 넣고
            pstmt = conn.prepareStatement(sql);
            // 질의어에 ?라고 적혀 있는 값을 정의 해준다.
            pstmt.setString(1, player.getUniqueId().toString());
            // 질의어 실행
            rs = pstmt.executeQuery();
            // 검색된 레코드를 넘겨줌 (필수)
            rs.next();
            // 해당하는 유저의 정보가 없는 경우
            try
            {
                pstmt.clearParameters();
                sql = "UPDATE " + table + " SET " + coloum +  " = \"" + content + "\" WHERE u_uuid = \"" + player.getUniqueId() + "\"";
                pstmt = conn.prepareStatement(sql);
                pstmt.executeUpdate();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return 0;

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 1;
        }
        finally
        {
            try
            {
                conn.close();
                db_PlayerInfo(player);
                PlayerScoreBoard.setScoreboard(player);
            }
            catch (SQLException e)
            {
                e.printStackTrace();
                return 2;
            }
        }
    }
}
