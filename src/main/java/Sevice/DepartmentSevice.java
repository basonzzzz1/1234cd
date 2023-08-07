package Sevice;

import model.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentSevice {
    List<Department> departments = new ArrayList<>();
    Connection connection = ConnectionMySql.getConnection();
    public List<Department> findAll() throws SQLException {
        String query = "select class.id as id , name as name from class;";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        departments.clear();
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            Department department = new Department(id,name);
            departments.add(department);
        }
        return departments;
    }
}