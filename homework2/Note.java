package cs320stu10.homework2.model;

/**
 * Created by davidkrystall on 12/10/15.
 */
public class Note {
    private String body;
    private String title;
    private int id;

    public Note(String body, String title, int id) {
        super();
        this.body = body;
        this.title = title;
        this.id = id;
    }
    public Note(){

    }
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
