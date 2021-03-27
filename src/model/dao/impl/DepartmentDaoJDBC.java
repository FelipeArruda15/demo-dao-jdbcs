/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.impl;

import db.DB;
import db.DbException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import model.dao.DepartmentDao;
import model.entites.Department;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Usuário
 */
public class DepartmentDaoJDBC implements DepartmentDao{
    
    private Connection conn;

    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }
    

    @Override
    public void insert(Department obj) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("INSERT INTO department(Name) "
                    + "VALUES"
                    + "(?)", Statement.RETURN_GENERATED_KEYS) ;
            
           st.setString(1, obj.getNome());
            
           int rowsAffected = st.executeUpdate();
            
           if(rowsAffected > 0){
               ResultSet rs = st.getGeneratedKeys();
               if(rs.next()){
                   int id = rs.getInt(1);
                   obj.setId(id);
               }
           }else{
               throw new DbException("Unexpected error! No rows affected");
        } 
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }finally{
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Department obj) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("UPDATE department SET Name = (?) WHERE Id = ?");
           
            st.setString(1, obj.getNome());
            st.setInt(2, obj.getId());
            
            st.executeUpdate();
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try{
           st = conn.prepareStatement("DELETE FROM department WHERE Id = ? ");
           
           st.executeUpdate();
           
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }finally{
            DB.closeStatement(st);
        }
    }

    @Override
    public Department findById(Integer id) {
       PreparedStatement st = null;
       ResultSet rs = null;
       try{
           st = conn.prepareStatement("SELECT * FROM WHERE Id = ?");
           
           st.setInt(1, id);
           
           rs = st.executeQuery();
          
           
           while(rs.next()){
               Department dep = instantiateDepartment(rs);
               return dep;
           }
           return null;
       }catch(SQLException e){
           throw new DbException(e.getMessage());
       }finally{
           DB.closeStatement(st);
           DB.closeResultSet(rs);
    }
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement("SELECT * FROM department");
            
            rs = st.executeQuery();
            
            List<Department> lista = new ArrayList<>();
            while(rs.next()){
               Department dep = instantiateDepartment(rs);
               lista.add(dep);
            }
            return lista;
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
    
      private Department instantiateDepartment(ResultSet rs) throws SQLException {
       Department dep = new Department();
       dep.setId(rs.getInt("DepartmentId"));
       dep.setNome(rs.getString("DepName"));
       return dep;
    }
}
