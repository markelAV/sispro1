import app.database.bl.HibernateUtil;
import app.database.entities.File7;
import app.database.servise.File7Service;

import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {
        File7Service fileService = new File7Service();
       // File7 file = new File7();

        //Здесь происходит добавление записи (create)
//        file.setAddress("safari.com");
//        file.setAccess("свободно");
//        file.setData("03.03.2019");

        //fileService.add(file);


        //Здесь происходит выбор всех записей (read)
       // List<File7> list = fileService.getAll();
        //System.out.println(list.get(0).getData());


        //Здесь происходит обновление записи (update)
        /*File7 fileUpdate = fileService.getById("1"); //сначала по ID вытаскиваем запись, которую хотим удалить

        fileUpdate.setAddress("lol.com");             //затем указываем поле, которое хотим изменить

        fileService.update(fileUpdate);*/            //далее обновляем запись




        //Здесь происходит удаление файла (delete )
        /*File7 fileRemove = fileService.getById("1"); //сначала по ID вытаскиваем запись, которую хотим удалить

        fileService.remove(fileRemove);*/            //затем удаляем запись

        //HibernateUtil.shutdown();
    }
}


/*    private static final SessionFactory ourSessionFactory;

static {
        try {
        Configuration configuration = new Configuration();
        configuration.configure();

        ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
        throw new ExceptionInInitializerError(ex);
        }
        }

public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
        }

public static void main(final String[] args) throws Exception {
final Session session = getSession();
        try {
        System.out.println("querying all the managed entities...");
final Metamodel metamodel = session.getSessionFactory().getMetamodel();
        for (EntityType<?> entityType : metamodel.getEntities()) {
final String entityName = entityType.getName();
final Query query = session.createQuery("from " + entityName);
        System.out.println("executing: " + query.getQueryString());
        for (Object o : query.list()) {
        System.out.println("  " + o);
        }
        }
        } finally {
        session.close();
        }
        }*/