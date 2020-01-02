package dbpkg;

import java.sql.*;
import java.util.Vector;

public class Database {

    Connection con;
    ResultSet rs;

    public Database() {
        startConnection();
        getEmployees();
        endConnection();
    }

    private void startConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/java_db", "root", "Root123#");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private void getEmployees() {
        try {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery("select * from employees");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Employee getFirstEmp() {
        try {
            rs.first();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getEmp();
    }

    private Employee getEmp() {
        try {
            return new Employee(
                    rs.getInt("id"),
                    rs.getString("fName"),
                    rs.getString("mName"),
                    rs.getString("lName"),
                    rs.getString("email"),
                    rs.getString("phone")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Employee();
    }


    public Employee getLastEmp() {
        try {
            rs.last();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getEmp();
    }

    public Employee getNextEmp() {
        try {
            rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getEmp();
    }


    public Employee getPreviousEmp() {
        try {
            rs.previous();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getEmp();
    }

    public int insert(Employee emp) {
        int res = 0;
        try {
            String query = new String("INSERT INTO employees  (fName,mName,lName,email,phone) VALUES (?,?,?,?,?)");
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, emp.getFName());
            stmt.setString(2, emp.getMName());
            stmt.setString(3, emp.getLName());
            stmt.setString(4, emp.getEmail());
            stmt.setString(5, emp.getPhone());
            res = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public int update(Employee emp) {
        int res = 0;
        try {
            String query = new String("UPDATE  employees SET fName=? , mName=? , lName=? , email=? , phone=? WHERE id=?");
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, emp.getFName());
            stmt.setString(2, emp.getMName());
            stmt.setString(3, emp.getLName());
            stmt.setString(4, emp.getEmail());
            stmt.setString(5, emp.getPhone());
            stmt.setInt(6,emp.getId());
            res = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public int delete(int id) {
        int res = 0;
        try {
            String query = new String("DELETE FROM employees WHERE id=?");
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1,id);
            res = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    private void endConnection() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}