package application;


import com.mysql.cj.xdevapi.Statement;
import model.dao.DaoFactory;
import model.dao.UserDao;
import model.dao.impl.UserDaoJDBC;
import model.entities.User;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        UserDao userDao = DaoFactory.createUserDao();


        System.out.println("Data transfer from .csv file, to Data Base. \nOptions:");
        System.out.println();
        System.out.println("1. Read all users from .csv file.\n2. Read all users from DataBase.\n3. Transfer data from .csv file to Data Base.");
        int answer = sc.nextInt();
        sc.nextLine();

        switch (answer){
            case 1 -> System.out.println(userDao.readAllUsers());
            case 2 -> System.out.println(userDao.findAll());
            case 3 -> userDao.importToDB(userDao.readAllUsers());
        }


    }

}
