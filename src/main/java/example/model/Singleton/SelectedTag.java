package example.model.Singleton;

import example.model.entity.Tag;

public class SelectedTag {
    private static SelectedTag _instance;
    private Tag selectedTag;

    private SelectedTag(Tag tag){
        selectedTag=tag;
    }

    public static SelectedTag getInstance(){
        return _instance;
    }

    public static SelectedTag getInstance(Tag tag){
        if (_instance==null){
            _instance=new SelectedTag(tag);
        }
        return _instance;
    }

    public static void close(){
        _instance=null;
    }

    public Tag getSelectedTag() {
        return selectedTag;
    }
}
