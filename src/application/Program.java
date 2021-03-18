/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;


import model.dao.DaoFactory;
import model.dao.SellerDao;
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
    }
}
