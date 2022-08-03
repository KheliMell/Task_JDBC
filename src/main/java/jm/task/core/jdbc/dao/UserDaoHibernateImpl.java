package jm.task.core.jdbc.dao;

//import com.mysql.cj.Query;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            String sql = "CREATE TABLE " + Util.getDBName() +
                    "(`id` INT NOT NULL AUTO_INCREMENT," +
                    " `name` VARCHAR(45) ," +
                    " `lastName` VARCHAR(45) ," +
                    " `age` TINYINT(0) UNSIGNED ," +
                    " PRIMARY KEY (`id`))\n" +
                    "ENGINE = InnoDB\n" +
                    "DEFAULT CHARACTER SET = utf8;";

            session.createSQLQuery(sql).addEntity(User.class).executeUpdate();

            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("Что-то пошло не так! [СОЗДАНИЕ ТАБЛИЦЫ]");
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            String sql = "DROP TABLE " + Util.getDBName();

            session.createSQLQuery(sql).addEntity(User.class).executeUpdate();

            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("Что-то пошло не так! [УДАЛЕНИЕ ТАБЛИЦЫ]");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

//            String sql = "DROP TABLE " + Util.getDBName();

//            session.createSQLQuery(sql).addEntity(User.class).executeUpdate();
            session.save(new User(name, lastName,age));
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("Что-то пошло не так! [УДАЛЕНИЕ ТАБЛИЦЫ]");
        }
    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }
}
