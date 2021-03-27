/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.util.ArrayList;
import java.util.List;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entites.Department;

/**
 *
 * @author Usuário
 */
public class Program2 {
    
    public static void main(String[] args) {
        
    DepartmentDao depDao = DaoFactory.createDepartmentDao();
    
    Department dep = new Department(null, "Felipe");
    
       System.out.println("=== TEST 1: Department insert ===");
       depDao.insert(dep);
       
       System.out.println("=== TEST 2: Department findById ===");
       Department dep1 = depDao.findById(1);
       System.out.println(dep1);
       
        System.out.println("TEST 3: Department findAll ===");
        List<Department> list = new ArrayList<>();
        list = depDao.findAll();
        for(Department de : list){
            System.out.println(de);
        }
        
        System.out.println("=== TEST 4: Department update ===");
        Department dep3 = depDao.findById(3);
        dep.setNome("Computadores");
        depDao.update(dep);
        
        System.out.println("=== TEST 5: Department delete ===");
        depDao.deleteById(3);
        System.out.println("Delete completed");
        
    } 
}
