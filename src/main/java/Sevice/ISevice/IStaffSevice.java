package Sevice.ISevice;

import java.sql.SQLException;
import java.util.List;

public interface IStaffSevice<E> {
    void add(E e) throws SQLException;
    void delete(int id) throws SQLException;
    void edit(int id , E e) throws SQLException;
    List <E> findAll() throws SQLException;
}
