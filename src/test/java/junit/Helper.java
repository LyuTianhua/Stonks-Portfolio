package junit;

import csci310.servlets.Database;
import org.mockito.Mockito;

import java.sql.*;

public class Helper extends Mockito {

    static Database db;
    static Connection con;
    static PreparedStatement ps;

    public static void insert_stock_company_user_shares(int company_id, int user_id, double shares) {
        db = new Database();
        con = db.getConn();
        try {
            if (user_id == 92) {
                ps = con.prepareStatement("insert into Stock (company_id, user_id, shares, purchased, sold) values (?, ?, ?, ?, ?)");
                ps.setInt(1, company_id);
                ps.setInt(2, user_id);
                ps.setDouble(3, shares);
                ps.setLong(4, 1592400600L);
                ps.setLong(5, 1603978200L);            	
            } else {
                ps = con.prepareStatement("insert into Stock (company_id, user_id, shares) values (?, ?, ?)");
                ps.setInt(1, company_id);
                ps.setInt(2, user_id);
                ps.setDouble(3, shares);
            }
            ps.execute();
        } catch (SQLException ignored) {}
        db.closeCon();
    }

    public static void insert_historical_stock_company_user_shares(int company_id, int user_id) {
        db = new Database();
        con = db.getConn();
        try {
            ps = con.prepareStatement("insert into historicalStock (company_id, user_id, shares, purchased, sold) values (?, ?, ?, ?, ?)");
            ps.setInt(1, company_id);
            ps.setInt(2, user_id);
            ps.setDouble(3, 10d);
            ps.setLong(4, 1592400600L);
            ps.setLong(5, 1603978200L);  
            ps.execute();
        } catch (SQLException ignored) {}
        db.closeCon();
    }

    public static void insert_user_name_password(String user, String password) {
        db = new Database();
        con = db.getConn();
        try {
            ps = con.prepareStatement("insert into base_user (email, password) values (?, ?)");
            ps.setString(1, user);
            ps.setString(2, password);
            ps.execute();
        } catch (SQLException ignored) {}
        db.closeCon();
    }

    public static void insert_user_id_name_password(int id, String name, String password) {
        db = new Database();
        con = db.getConn();
        try {
            if (id == 92) {
                ps = con.prepareStatement("insert into base_user (id, email, password, data) values (?, ?, ?, ?)");
                ps.setInt(1, id);
                ps.setString(2, name);
                ps.setString(3, password);
                ps.setString(4, "1572964200 1573050600 1573137000 1573223400 1573482600 1573569000 1573655400 1573741800 1573828200 1574087400 1574173800 1574260200 1574346600 1574433000 1574692200 1574778600 1574865000 1575037800 1575297000 1575383400 1575469800 1575556200 1575642600 1575901800 1575988200 1576074600 1576161000 1576247400 1576506600 1576593000 1576679400 1576765800 1576852200 1577111400 1577197800 1577370600 1577457000 1577716200 1577802600 1577975400 1578061800 1578321000 1578407400 1578493800 1578580200 1578666600 1578925800 1579012200 1579098600 1579185000 1579271400 1579617000 1579703400 1579789800 1579876200 1580135400 1580221800 1580308200 1580394600 1580481000 1580740200 1580826600 1580913000 1580999400 1581085800 1581345000 1581431400 1581517800 1581604200 1581690600 1582036200 1582122600 1582209000 1582295400 1582554600 1582641000 1582727400 1582813800 1582900200 1583159400 1583245800 1583332200 1583418600 1583505000 1583760600 1583847000 1583933400 1584019800 1584106200 1584365400 1584451800 1584538200 1584624600 1584711000 1584970200 1585056600 1585143000 1585229400 1585315800 1585575000 1585661400 1585747800 1585834200 1585920600 1586179800 1586266200 1586352600 1586439000 1586784600 1586871000 1586957400 1587043800 1587130200 1587389400 1587475800 1587562200 1587648600 1587735000 1587994200 1588080600 1588167000 1588253400 1588339800 1588599000 1588685400 1588771800 1588858200 1588944600 1589203800 1589290200 1589376600 1589463000 1589549400 1589808600 1589895000 1589981400 1590067800 1590154200 1590499800 1590586200 1590672600 1590759000 1591018200 1591104600 1591191000 1591277400 1591363800 1591623000 1591709400 1591795800 1591882200 1591968600 1592227800 1592314200 1592400600 1592487000 1592573400 1592832600 1592919000 1593005400 1593091800 1593178200 1593437400 1593523800 1593610200 1593696600 1594042200 1594128600 1594215000 1594301400 1594387800 1594647000 1594733400 1594819800 1594906200 1594992600 1595251800 1595338200 1595424600 1595511000 1595597400 1595856600 1595943000 1596029400 1596115800 1596202200 1596461400 1596547800 1596634200 1596720600 1596807000 1597066200 1597152600 1597239000 1597325400 1597411800 1597671000 1597757400 1597843800 1597930200 1598016600 1598275800 1598362200 1598448600 1598535000 1598621400 1598880600 1598967000 1599053400 1599139800 1599226200 1599571800 1599658200 1599744600 1599831000 1600090200 1600176600 1600263000 1600349400 1600435800 1600695000 1600781400 1600867800 1600954200 1601040600 1601299800 1601386200 1601472600 1601559000 1601645400 1601904600 1601991000 1602077400 1602163800 1602250200 1602509400 1602595800 1602682200 1602768600 1602855000 1603114200 1603200600 1603287000 1603373400 1603459800 1603719000 1603805400 1603891800 1603978200 1604064600 1604327400 1604413800 1604500200");     	       
            } else {
                ps = con.prepareStatement("insert into base_user (id, email, password) values (?, ?, ?)");
                ps.setInt(1, id);
                ps.setString(2, name);
                ps.setString(3, password);
            }
            ps.execute();
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        db.closeCon();
    }

    public static void insert_company_id_ticker(int id, String ticker) {
        db = new Database();
        con = db.getConn();
        try {
            if (id == 93) {
                ps = con.prepareStatement("insert into Company (id, ticker, data, timestamps) values (?, ?, ?, ?)");
                ps.setInt(1, id);
                ps.setString(2, ticker);
            	ps.setString(3, "61.3 62.1 61.5 61.7 61.2 61.7 62.1 61.8 62.4 63.1 63.0 63.1 62.4 62.2 62.1 63.2 63.3 63.4 63.7 63.9 64.0 64.0 64.4 64.8 64.9 64.4 64.4 64.5 64.5 64.3 64.7 65.0 66.8 66.9 67.1 66.8 67.3 66.9 67.3 65.8 66.2 66.1 67.0 67.0 67.3 67.4 67.8 68.1 68.4 68.9 69.0 68.7 68.4 68.6 67.5 67.2 67.1 66.6 67.0 66.4 67.0 66.8 67.5 61.8 62.5 63.6 64.5 64.4 65.3 65.9 65.4 64.5 63.9 63.8 62.8 61.7 61.1 59.4 58.8 62.0 61.6 63.4 62.5 62.1 61.3 61.8 60.6 57.2 62.5 61.5 68.0 67.8 60.4 54.8 52.6 55.3 55.2 57.7 58.4 59.8 58.9 60.0 60.2 62.5 62.4 61.5 60.4 61.6 61.7 62.5 61.8 63.0 63.9 65.1 64.4 63.5 63.4 63.0 64.0 64.6 63.8 64.4 63.7 62.6 62.1 63.0 61.6 62.6 62.2 62.4 63.0 62.5 62.9 62.6 61.3 61.4 60.3 61.0 61.0 63.5 64.3 64.7 65.5 66.2 64.9 64.1 65.0 66.6 65.6 65.9 63.2 63.0 64.9 66.1 66.5 67.2 67.2 66.1 65.7 64.3 64.9 63.3 64.5 65.5 65.3 65.7 65.6 66.0 66.0 65.3 66.6 66.4 67.8 67.5 67.6 68.0 66.8 67.1 66.9 67.8 68.1 69.4 70.3 70.3 70.0 68.4 68.7 69.1 68.7 68.6 68.9 68.4 67.5 68.7 68.4 68.5 69.1 69.5 68.2 68.1 68.3 69.0 68.9 69.8 69.8 69.9 70.9 69.2 70.4 69.9 69.2 66.3 66.5 6 65.4 65.4 65.6 63.9 63.5 62.9 62.7 62.6 61.1 62.6 63.0 63.3 63.4 64.5 64.3 64.7 65.4 65.1 64.8 65.2 65.8 66.3 66.8 66.8 67.1 67.0 66.6 66.2 65.9 66.3 66.7 65.6 64.9 63.4 63.0 62.8 63.9 64.3 63.5 ");
            	ps.setString(4, "1572964200 1573050600 1573137000 1573223400 1573482600 1573569000 1573655400 1573741800 1573828200 1574087400 1574173800 1574260200 1574346600 1574433000 1574692200 1574778600 1574865000 1575037800 1575297000 1575383400 1575469800 1575556200 1575642600 1575901800 1575988200 1576074600 1576161000 1576247400 1576506600 1576593000 1576679400 1576765800 1576852200 1577111400 1577197800 1577370600 1577457000 1577716200 1577802600 1577975400 1578061800 1578321000 1578407400 1578493800 1578580200 1578666600 1578925800 1579012200 1579098600 1579185000 1579271400 1579617000 1579703400 1579789800 1579876200 1580135400 1580221800 1580308200 1580394600 1580481000 1580740200 1580826600 1580913000 1580999400 1581085800 1581345000 1581431400 1581517800 1581604200 1581690600 1582036200 1582122600 1582209000 1582295400 1582554600 1582641000 1582727400 1582813800 1582900200 1583159400 1583245800 1583332200 1583418600 1583505000 1583760600 1583847000 1583933400 1584019800 1584106200 1584365400 1584451800 1584538200 1584624600 1584711000 1584970200 1585056600 1585143000 1585229400 1585315800 1585575000 1585661400 1585747800 1585834200 1585920600 1586179800 1586266200 1586352600 1586439000 1586784600 1586871000 1586957400 1587043800 1587130200 1587389400 1587475800 1587562200 1587648600 1587735000 1587994200 1588080600 1588167000 1588253400 1588339800 1588599000 1588685400 1588771800 1588858200 1588944600 1589203800 1589290200 1589376600 1589463000 1589549400 1589808600 1589895000 1589981400 1590067800 1590154200 1590499800 1590586200 1590672600 1590759000 1591018200 1591104600 1591191000 1591277400 1591363800 1591623000 1591709400 1591795800 1591882200 1591968600 1592227800 1592314200 1592400600 1592487000 1592573400 1592832600 1592919000 1593005400 1593091800 1593178200 1593437400 1593523800 1593610200 1593696600 1594042200 1594128600 1594215000 1594301400 1594387800 1594647000 1594733400 1594819800 1594906200 1594992600 1595251800 1595338200 1595424600 1595511000 1595597400 1595856600 1595943000 1596029400 1596115800 1596202200 1596461400 1596547800 1596634200 1596720600 1596807000 1597066200 1597152600 1597239000 1597325400 1597411800 1597671000 1597757400 1597843800 1597930200 1598016600 1598275800 1598362200 1598448600 1598535000 1598621400 1598880600 1598967000 1599053400 1599139800 1599226200 1599571800 1599658200 1599744600 1599831000 1600090200 1600176600 1600263000 1600349400 1600435800 1600695000 1600781400 1600867800 1600954200 1601040600 1601299800 1601386200 1601472600 1601559000 1601645400 1601904600 1601991000 1602077400 1602163800 1602250200 1602509400 1602595800 1602682200 1602768600 1602855000 1603114200 1603200600 1603287000 1603373400 1603459800 1603719000 1603805400 1603891800 1603978200 1604064600 1604327400 1604413800 1604500200");     	       
            } else {
                ps = con.prepareStatement("insert into Company (id, ticker) values (?, ?)");
                ps.setInt(1, id);
                ps.setString(2, ticker);         	
            }
            ps.execute();
        } catch (SQLException ignored) {ignored.printStackTrace();}
        db.closeCon();
    }

    public static void delete_user_where_name(String name) {
        db = new Database();
        con = db.getConn();
        try {
            ps = con.prepareStatement("delete from base_user where email=?");
            ps.setString(1, name);
            ps.execute();
        } catch (SQLException ignored) {}
        db.closeCon();
    }

    public static void delete_user_where_id(int id) {
        db = new Database();
        con = db.getConn();
        try {
            ps = con.prepareStatement("delete from base_user where id=?");
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException ignored) {}
        db.closeCon();
    }

    public static void delete_from_stock_user_company(int user_id, int company_id) {
        db = new Database();
        con = db.getConn();
        try {
            ps = con.prepareStatement("delete from Stock where user_id=? and company_id=?");
            ps.setInt(1, user_id);
            ps.setInt(2, company_id);
            ps.execute();
        } catch (SQLException ignored) {}
        db.closeCon();
    }

    public static void delete_from_historical_stock_user(int user_id) {
        db = new Database();
        con = db.getConn();
        try {
            ps = con.prepareStatement("delete from historicalStock where user_id=?");
            ps.setInt(1, user_id);
            ps.execute();
        } catch (SQLException ignored) {}
        db.closeCon();
    }

    public static void delete_from_stock_where_id(int user_id) {
        db = new Database();
        con = db.getConn();
        try {
            ps = con.prepareStatement("delete from Stock where user_id=?");
            ps.setInt(1, user_id);
            ps.execute();
        } catch (SQLException ignored) {}
        db.closeCon();
    }

    public static void delete_company_where_id(int id) {
        db = new Database();
        con = db.getConn();
        try {
            ps = con.prepareStatement("delete from Company where id=?");
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException ignored) {}
        db.closeCon();
    }

    public static void delete_company_where_ticker(String ticker) {
        db = new Database();
        con = db.getConn();
        try {
            ps = con.prepareStatement("delete from Company where ticker=?");
            ps.setString(1, ticker);
            ps.execute();
        } catch (SQLException ignored) {}
        db.closeCon();
    }
    
    public static String get_stock_id(int company_id, int user_id) {
        db = new Database();
        con = db.getConn();
        ResultSet rs;
        String stock_id = "";
        try {          	
        	ps = con.prepareStatement("select * from Stock where company_id = ? and user_id = ?");
            ps.setInt(1, company_id);
            ps.setInt(2, user_id);
            rs = ps.executeQuery();
            stock_id = rs.getString("id");
        } catch (SQLException ignored) {}
        db.closeCon();
        return stock_id;
    }
}
