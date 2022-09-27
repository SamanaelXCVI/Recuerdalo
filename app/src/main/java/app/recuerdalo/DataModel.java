package app.recuerdalo;

public class DataModel {
    String name;
    String hint;
    int id_;
    int image;

    public DataModel(String name, String hint, int id_, int image) {
        this.name = name;
        this.hint = hint;
        this.id_ = id_;
        this.image = image;
    }

    public String getName() {
        return name;
    }
    public String getHint() { return hint;
    }

    public int getImage() { return image;
    }

    public int getId() { return id_;
    }

}