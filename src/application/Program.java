/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;


import java.util.Date;
import java.util.List;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entites.Department;
import model.entites.Seller;

/**
 *
 * @author Usuário
 */
public class Program {
    
    public static void main(String[] args) {
        
        SellerDao sellerDao = DaoFactory.createSellerDao();
        
        System.out.println("=== TEST 1: seller findById =====");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);
        
        System.out.println("\n=== TEST 2: seller findByDepartment =====");
        Department department = new Department(2, null);
        List<Seller> list = sellerDao.findByDepartment(department);
        for(Seller obj : list){
            System.out.println(obj);
        }
        
        System.out.println("\n=== TEST 3: seller findAll =====");
        list = sellerDao.findAll();
        for(Seller obj : list){
            System.out.println(obj);
        }
        
        System.out.println("\n=== TEST 4: seller insert =====");
        Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, department);
        sellerDao.insert(newSeller);
        System.out.println("Inserted! New id = " + newSeller.getId());
    }
}
