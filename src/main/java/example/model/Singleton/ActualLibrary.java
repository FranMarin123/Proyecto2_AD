package example.model.Singleton;

import example.model.DAO.LibraryDAO;
import example.model.entity.Library;
import example.model.entity.User;

public class ActualLibrary {
    private static ActualLibrary _instance;
    private Library currentlibrary;

    private ActualLibrary(){
        currentlibrary= LibraryDAO.findByOwner(UserSigned.getInstance().getCurrentUser());
    }

    public static ActualLibrary getInstance(){
        if (_instance==null){
            _instance=new ActualLibrary();
        }
        return _instance;
    }

    public static void close(){
        _instance=null;
    }

    public Library getCurrentLibrary(){
        return currentlibrary;
    }
}
