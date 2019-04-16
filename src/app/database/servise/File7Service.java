package app.database.servise;

import app.database.bl.SessionUtil;
import app.database.dao.File7DAO;
import app.database.entities.File7;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;


public class File7Service extends SessionUtil implements File7DAO {
    @Override
    public void add(File7 file) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        Session session = getSession();
        session.save(file);

        //close session with a transaction
        closeTransactionSesstion();
    }

    @Override
    public List<File7> getAll() throws SQLException {
        //open session with a transaction
        openTransactionSession();

        String sql = "SELECT * FROM FILE7";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(File7.class);
        List<File7> list = query.list();

        //close session with a transaction
        closeTransactionSesstion();

        return list;
    }

    @Override
    public File7 getById(String id) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        String sql = "SELECT * FROM FILE7 WHERE ID = :id";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(File7.class);
        query.setParameter("id", id);

        File7 file= (File7) query.getSingleResult();

        //close session with a transaction
        closeTransactionSesstion();

        return file;
    }

    @Override
    public void update(File7 file) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        Session session = getSession();
        session.update(file);

        //close session with a transaction
        closeTransactionSesstion();
    }

    @Override
    public void remove(File7 file) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        Session session = getSession();
        session.remove(file);

        //close session with a transaction
        closeTransactionSesstion();
    }
}
