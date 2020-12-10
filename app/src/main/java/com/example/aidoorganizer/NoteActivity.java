package com.example.aidoorganizer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.evernote.client.android.EvernoteSession;
import com.evernote.client.android.EvernoteUtil;
import com.evernote.client.android.asyncclient.EvernoteCallback;
import com.evernote.client.android.asyncclient.EvernoteNoteStoreClient;
import com.evernote.edam.type.Note;
import com.evernote.edam.type.Notebook;

import java.util.ArrayList;
import java.util.List;

public class NoteActivity extends AppCompatActivity {

    EditText edittitle,editnote;
    TextView tview1,tview2,tview3;
    EvernoteNoteStoreClient noteStoreClient;
    List<Character> listData;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        noteStoreClient = EvernoteSession.getInstance().getEvernoteClientFactory().getNoteStoreClient();
//        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
//       getSupportActionBar().hide(); // hide the title bar
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

        getSupportActionBar().setTitle("");
        listData = new ArrayList<>();

        setContentView(R.layout.activity_note);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);

        //placing toolbar in place of actionbar
        setSupportActionBar(toolbar);

         edittitle = (EditText)findViewById(R.id.note_title);
         editnote = (EditText)findViewById  (R.id.note_content);
//         tview1 = (TextView)findViewById(R.id.text1);
         //tview2 = (TextView)findViewById(R.id.text2);
         tview3 = (TextView)findViewById(R.id.text2);
//        Button btn_save = (Button) findViewById(R.id.btn_save);

        tview3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }

    private void showAlertDialog() {
        if (!EvernoteSession.getInstance().isLoggedIn()) {
            return;
        }
        else {

//        EvernoteNoteStoreClient noteStoreClient = EvernoteSession.getInstance().getEvernoteClientFactory().getNoteStoreClient();
            noteStoreClient.listNotebooksAsync(new EvernoteCallback<List<Notebook>>() {
                @Override
                public void onSuccess(List<Notebook> result) {
                    List<String> namesList = new ArrayList<>(result.size());
                    for (Notebook notebook : result) {
                        namesList.add(notebook.getName());
                    }
                    String notebookNames = TextUtils.join(", ", namesList);
                    Toast.makeText(getApplicationContext(), notebookNames + " notebooks have been retrieved", Toast.LENGTH_LONG).show();
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(NoteActivity.this);
                    alertDialog.setTitle("Select Notebook");
                    Log.i("showAlertdialog", "called");
                    alertDialog.setItems(Integer.parseInt(notebookNames), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Log.i("user clicked", "item");
                        }
                    });
                    AlertDialog alert = alertDialog.create();
                    alert.setCanceledOnTouchOutside(false);
                    alert.show();
                }

                @Override
                public void onException(Exception exception) {
//                Log.e(LOGTAG, "Error retrieving notebooks", exception);
                }
            });
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//    Log.i("optionMenu",String.valueOf(item.getItemId()));
        switch(item.getItemId()){
            case R.id.menuAbout:
                String title = edittitle.getText().toString();
                String content = editnote.getText().toString();
//                Log.i("note creation","called");
                if (title.equals("") || content.equals("")){
//                    Log.i("note creation if","called");
                    Toast.makeText(getApplicationContext(), "Enter the details", Toast.LENGTH_SHORT).show();
                }
                else{
//                    Log.i("note creation else","called");

                    Note note = new Note();
                    note.setTitle(title);
                    note.setContent(EvernoteUtil.NOTE_PREFIX . concat(content) .concat(EvernoteUtil.NOTE_SUFFIX));

                    noteStoreClient.createNoteAsync(note, new EvernoteCallback<Note>() {
                        @Override
                        public void onSuccess(Note result) {
                            Toast.makeText(getApplicationContext(), result.getTitle() + " has been created", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onException(Exception exception) {
                            Log.i("Creating note","sucess");
                        }
                    });
                }
                break;
            case R.id.menuredo:

//                Toast.makeText(this, "You clicked settings", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menuundo:
                String note = editnote.getText().toString();
                int length = editnote.getText().length();
                if (length > 0) {
                    listData.add(editnote.getText().charAt(length-1));
                    editnote.getText().delete(length - 1, length);
                }
                break;

        }
        Log.i("note creation 1","called");
        return true;
    }

}