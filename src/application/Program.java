package application;


import model.dao.DaoFactory;
import model.dao.UserDao;
import model.dao.impl.UserDaoJDBC;
import model.entities.User;

public class Program {
    public static void main(String[] args) {


        UserDao userDao = DaoFactory.createUserDao();
        //userDao.importToDB(userDao.readAllUsers());

        System.out.println(userDao.findAll());



        /*Connection conn = DB.getConnection();
        DB.closeConnection();*/

    }

}
