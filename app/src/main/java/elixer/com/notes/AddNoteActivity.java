package elixer.com.notes;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class AddNoteActivity extends AppCompatActivity {

    public static final String EXTRA_TITLE = "elixer.com.notes.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "elixer.com.notes.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "elixer.com.notes.EXTRA_PRIORITY";

    @BindView(R.id.editText_title)
    EditText title;

    @BindView(R.id.editText_description)
    EditText description;

    @BindView(R.id.editText_priority)
    EditText priority;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        // bind the view using butterknife
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button)
    public void onButtonClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        Log.e("MAIN ACTIVITY", title.getText().toString());
        Log.e("MAIN ACTIVITY", description.getText().toString());
        Log.e("MAIN ACTIVITY", priority.getText().toString());
        intent.putExtra(EXTRA_TITLE, title.getText().toString());
        intent.putExtra(EXTRA_DESCRIPTION, description.getText().toString());
        intent.putExtra(EXTRA_PRIORITY, Integer.parseInt(priority.getText().toString()));

        setResult(RESULT_OK, intent);
        finish();

    }

}
