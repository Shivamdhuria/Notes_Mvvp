package elixer.com.notes.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int mId;
    private String mTitle;
    private String mDescription;
    private  int mPriority;

    public Note(String mTitle, String mDescription, int mPriority) {
        this.mTitle = mTitle;
        this.mDescription = mDescription;
        this.mPriority = mPriority;
    }

    public void setId(int id) {
        this.mId = mId;
    }

    public int getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public int getPriority() {
        return mPriority;
    }
}
