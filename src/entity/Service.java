package entity;

public class Service {

    private int id;
    private String name;
    private String descr;
    private int prix;
    private String file;
    private String cat;

    public Service() {
    }

    public Service(int id, String name, String descr, int prix, String file, String cat) {
        this.id = id;
        this.name = name;
        this.descr = descr;
        this.prix = prix;
        this.file = file;
        this.cat = cat;
    }

    public Service(String name, String descr, int prix, String file, String cat) {
        this.name = name;
        this.descr = descr;
        this.prix = prix;
        this.file = file;
        this.cat = cat;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return descr;
    }

    public int getPrix() {
        return prix;
    }

    public String getFile() {
        return file;
    }

    public String getCat() {
        return cat;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String descr) {
        this.descr = descr;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }
}
