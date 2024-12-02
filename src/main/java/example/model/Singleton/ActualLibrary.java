package example.model.Singleton;

import example.model.DAO.LibraryDAO;
import example.model.DAO.LibraryVideoDAO;
import example.model.entity.Library;
import example.model.entity.User;

public class ActualLibrary {
    private static ActualLibrary _instance;
    private Library currentlibrary;

    private ActualLibrary() {
        Library library = LibraryDAO.findByOwner(UserSigned.getInstance().getCurrentUser());
        if (library == null) {
            library = new Library(UserSigned.getInstance().getCurrentUser());
            LibraryDAO save = new LibraryDAO(library);
            save.save();
            library = LibraryDAO.findByOwner(UserSigned.getInstance().getCurrentUser());
        }
        library.setLibraryVideo(LibraryVideoDAO.findAllVideosForLibrary(library));
        currentlibrary = library;
    }

    public static ActualLibrary getInstance() {
        if (_instance == null) {
            _instance = new ActualLibrary();
        }
        return _instance;
    }

    public static void close() {
        _instance = null;
    }

    public Library getCurrentLibrary() {
        return currentlibrary;
    }
}
