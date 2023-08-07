package Sevice;

import Sevice.ISevice.IStaffSevice;
import model.Department;
import model.Staff;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffSevice implements IStaffSevice<Staff> {
    List<Staff> staffList = new ArrayList<>();
    Connection connection = ConnectionMySql.getConnection();
    @Override
    public void add(Staff staff) throws SQLException {
     String query = "insert into staff( name, email,  salary, address, phonenumber,idDepartment) values (?, ?, ?, ?, ?, ?);";
     PreparedStatement statement = connection.prepareStatement(query);
     statement.setString(1 ,staff.getName());
     statement.setString(2,staff.getEmail());
     statement.setFloat(3,staff.getSalary());
     statement.setString(4 ,staff.getAddress());
     statement.setInt(5,staff.getPhoneNumber());
     statement.setInt(6,staff.getDepartment().getId());
     statement.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
     String query = "delete from staff where id = ?;";
     PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    @Override
    public void edit(int id, Staff staff) throws SQLException {
        String query = "UPDATE staff SET name = ?, email = ?, salary = ?, address = ?, phonenumber = ?, idDepartment = ? WHERE id = ?;";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, staff.getName());
        statement.setString(2, staff.getEmail());
        statement.setFloat(3,staff.getSalary());
        statement.setString(4, staff.getAddress());
        statement.setInt(5, staff.getPhoneNumber());
        statement.setInt(6, staff.getDepartment().getId());
        statement.setInt(7, id);
        statement.executeUpdate();
    }
    @Override
    public List<Staff> findAll() throws SQLException {
        String query = "select staff.id as id , staff.name as name , email as email , salary as salary, address as address , phonenumber as phone , idDepartment as idDepartment , department.name as departmentName from staff join department on department.id = staff.idDepartment;";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        staffList.clear();
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            float salary = resultSet.getFloat("salary");
            String address = resultSet.getString("address");
            int phone = resultSet.getInt("phone");
            int idDepartment = resultSet.getInt("idDepartment");
            String departmentName = resultSet.getString("departmentName");
            Department classRoom = new Department(idDepartment , departmentName);
            Staff staff = new Staff(id , name , salary , email , address , phone , classRoom);
            staffList.add(staff);
        }
        return staffList;
    }
    public Staff findById(int id) throws SQLException {
        String query = "select staff.id as id , staff.name as name , email as email , salary as salary, address as address , phonenumber as phone , idDepartment as idDepartment , department.name as departmentName from staff join department on department.id = staff.idDepartment where staff.id = ?;";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            int studentId = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            float salary = resultSet.getFloat("salary");
            String address = resultSet.getString("address");
            int phone = resultSet.getInt("phone");
            int idDepartment = resultSet.getInt("idDepartment");
            String departmentName= resultSet.getString("departmentName");
            Department department = new Department(idDepartment, departmentName);
            return new Staff(studentId, name, salary, email, address, phone, department);
        }

        return null;
    }
    public List<Staff> findByName(String name) throws SQLException {
        List<Staff> staffList = findAll();
        List<Staff> searchList = new ArrayList<>();
        for (int i = 0; i < staffList.size(); i++) {
            if (staffList.get(i).getName().toLowerCase().contains(name.toLowerCase())) {
                System.out.println(staffList.get(i).toString());
                searchList.add(staffList.get(i));
            }
        }
        return searchList;
    }

}