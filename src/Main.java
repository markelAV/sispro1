import app.database.bl.HibernateUtil;
import app.database.entities.File3;
import app.database.entities.File7;
import app.database.servise.File3Service;
import app.database.servise.File7Service;

import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {
//        String[] adress ={"safari.com","vk.com","odnoklassniki.com","facebook.com","twitter.com","inst.com","ruus.com","youtube.com","prewq.com","hello.com"};
//        String[] acess ={"свободный","свободный","закрытый","свободный","закрытый","свободный","закрытый","свободный","закрытый","закрытый"};
//        String[] data={"03.09.2019","01.03.2019","02.03.2019","03.03.2019","04.03.2019","05.03.2019","07.03.2019","08.03.2019","03.03.2019","03.03.2019"};
//        String[] names={"wert.doc","artek.doc","maer.doc","diplom.doc","otchet.doc","wert.doc","qwer.doc","zxcv.doc","asdf.doc","wert.doc"};
//        int[] sizes={3,4,5,6,7,8,7,6,5,10};
//        File7Service fileService = new File7Service();
//        File7 file = new File7();
//        File3 file3 = new File3();
//        File3Service file3Service = new File3Service();
//        for (int i = 0; i <names.length ; i++) {
//            file3.setLocation(names[i]);
//            file3.setSize(sizes[i]);
//            file3.setEditDate(names[i]);
//
//            file3Service.add(file3);
//        }
        //Здесь происходит добавление записи (create)
//        for (int i = 0; i <adress.length ; i++) {
//            file.setAddress(adress[i]);
//            file.setAccess(acess[i]);
//            file.setData(data[i]);
//
//            fileService.add(file);
//        }



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