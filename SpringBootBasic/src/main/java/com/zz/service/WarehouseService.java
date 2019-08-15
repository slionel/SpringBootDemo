package com.zz.service;

import com.zz.entity.Product;
import com.zz.entity.User;
import com.zz.utils.DBConnection;
import com.zz.utils.Keyutils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class WarehouseService {
    Connection conn;
    DBConnection jdbc = new DBConnection();


    /**
     * 将注册页面传过来的数据存入数据库
     * @param user
     * @return
     */
    public boolean register(User user){
        boolean flag = false;
        if(!login(user.getUname(),user.getPwd())){
            conn = jdbc.connect();
            String sql = "INSERT INTO wms_user(uname,pwd,utype,uid) VALUES(?,?,?,?);";
            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,user.getUname());
                pstmt.setString(2,user.getPwd());
                pstmt.setString(3,user.getUtype());
                pstmt.setString(4, Keyutils.genUniqueKey());
                int rs = pstmt.executeUpdate();
                if(rs >= 1){
                    flag = true;
                }
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }


    /**
     * 在后端查找是否存在前端登录页面传过来的用户名和密码，判断登录是否成功
     * @param uname
     * @param pwd
     * @return
     */
    public boolean login(String uname, String pwd){
        boolean flag = false;
        conn = jdbc.connect();
        String sql = "SELECT * FROM wms_user WHERE uname=? AND pwd=?;";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, uname);
            pstmt.setString(2, pwd);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                flag = true;
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }


    /**
     * 查找数据库中所有产品信息
     * @return
     */
    public List<Product> getAllProduct(){
        conn = jdbc.connect();
        List<Product> productList = new ArrayList<Product>();
        String sql = "SELECT * FROM wms_product;";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Product product = new Product();
                product.setPid(rs.getString("pid"));
                product.setCategory(rs.getString("category"));
                product.setPname(rs.getString("pname"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setProduceDate(rs.getTimestamp("producedate"));
                product.setStock(rs.getInt("stock"));
                product.setShelfLife(rs.getString("shelflife"));
                productList.add(product);
            }
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return productList;
    }


    /**
     * 查询注册的用户名是否重复
     * 如果存在返回true
     * 不存在返回false
     * @param username
     * @return
     */
    public boolean checkUserName(String username){
        boolean flag = true;
        conn = jdbc.connect();
        String sql = "SELECT * FROM wms_user WHERE uname=?;";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,username);
            ResultSet rs = pstmt.executeQuery();
            if(!rs.next()){
                flag = false;
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }


    /**
     * 根据id更新所有数据
     * @param product
     * @return
     */
    public boolean updateAll(Product product){
        conn = jdbc.connect();
        boolean flag = false;
        String sql = "UPDATE wms_product SET pname=? , category=? , price=? , producedate=? , shelflife=? , stock=? WHERE pid=?;";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,product.getPname());
            pstmt.setString(2,product.getCategory());
            pstmt.setBigDecimal(3,product.getPrice());
            pstmt.setTimestamp(4,new Timestamp(product.getProduceDate().getTime()));
            pstmt.setString(5,product.getShelfLife());
            pstmt.setInt(6,product.getStock());
            pstmt.setString(7,product.getPid());
            int rs = pstmt.executeUpdate();
            System.out.println(rs);
            if(rs >= 1){
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }


    /**
     * 删除库存中所有此商品
     * @param id
     * @return
     */
    public boolean deleteAllById(String id){
        boolean flag = false;
        conn = jdbc.connect();
        String sql = "DELETE FROM wms_product WHERE pid=?;";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,id);
            int rs = pstmt.executeUpdate();
            if(rs >= 1){
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }


    /**
     * 通过id确认库存中商品的库存
     * @param id
     * @return
     */
    public int findNumById(String id){
        conn = jdbc.connect();
        int num = 0;
        String sql = "SELECT stock FROM wms_product WHERE pid=?;";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                num = rs.getInt("stock");
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return num;
    }


    /**
     * 添加产品
     * @param product
     * @return
     */
    public boolean productAdd(Product product){
        boolean flag = false;
        conn = jdbc.connect();
        String sql = "INSERT INTO wms_product(pid,pname,stock,category,price,producedate,shelflife) VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,product.getPid());
            pstmt.setString(2,product.getPname());
            pstmt.setInt(3,product.getStock());
            pstmt.setString(4,product.getCategory());
            pstmt.setBigDecimal(5,new BigDecimal(product.getPrice().toString()));
            pstmt.setTimestamp(6,new Timestamp(product.getProduceDate().getTime()));
            pstmt.setString(7,product.getShelfLife());
            int rs = pstmt.executeUpdate();
            if(rs >= 1){
                flag = true;
            }
            pstmt.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }


    /**
     * 根据商品名查找
     * @param pname
     * @return
     */
    public List<Product> findByPname(String pname){
        List<Product> productList = new ArrayList<Product>();
        conn = jdbc.connect();
        String sql = "SELECT * FROM wms_product WHERE pname=?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,pname);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Product product = new Product();
                product.setCategory(rs.getString("category"));
                product.setPid(rs.getString("pid"));
                product.setPname(rs.getString("pname"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setProduceDate(rs.getTimestamp("producedate"));
                product.setStock(rs.getInt("stock"));
                product.setShelfLife(rs.getString("shelflife"));
                productList.add(product);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return productList;
    }
}
