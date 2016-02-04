package com.example.pjw527.notesarama;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    protected List<Note> notes;
    protected ListView notesListView;
    protected CustomListViewAdaptor notesListViewAdaptor;
    public static String noteContent = "noteContent";
    public static String position = "position";
    private int newNote = 0;
    private int editNote = 1;

    protected BoundWordCountService bwcs = null;
    protected ServiceConnection connection = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v7.app.ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        menu.setLogo(R.drawable.ic_home_black_24dp);
        menu.setDisplayUseLogoEnabled(true);

        setContentView(R.layout.activity_main);
        notesListView = (ListView) findViewById(R.id.notesListView);
        notes = new ArrayList<Note>();
        Note testNote1 = new Note("Tree", "desc", 1);
        notes.add(testNote1);

        registerForContextMenu(notesListView);
        notesListViewAdaptor = new CustomListViewAdaptor(this, notes);
        notesListView.setAdapter(notesListViewAdaptor);

        notesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg3) {
                //String text = notes.get(position);
                Intent editNoteIntent = new Intent(MainActivity.this, EditNoteActivity.class);
                // editNoteIntent.putExtra(MainActivity.noteContent, text);
                editNoteIntent.putExtra(MainActivity.position, position);
                startActivityForResult(editNoteIntent, editNote);
            }
        });


        NoteDbOpenHelper notesDbOpenHelper = new NoteDbOpenHelper(this);
        notesDbOpenHelper.getAllNotes();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.notesListView) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.long_click_menu, menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_Info) {

            connection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder binder) {
                    bwcs = ((BoundWordCountService.LocalBinder) binder).getService();}
                    @Override
                public void onServiceDisconnected(ComponentName name) {
                    bwcs = null;

                }
            };

            Intent intent = new Intent(MainActivity.this, BoundWordCountService.class);
            startService(intent);
            bindService(new Intent(this, BoundWordCountService.class), connection, Context.BIND_AUTO_CREATE);
            Toast.makeText(MainActivity.this, bwcs.countWords((ArrayList) notes) + " words.", Toast.LENGTH_LONG).show();

            return true;
        } else if (id == R.id.action_NewNote) {

            Intent newNoteIntent = new Intent(MainActivity.this, NewNoteActivity.class);
            startActivityForResult(newNoteIntent, newNote);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == 1) {
            if (requestCode == newNote) {
                //Note note = data.get(MainActivity.noteContent);

                notes.add( (Note)data.getSerializableExtra(MainActivity.noteContent));
                notesListViewAdaptor.notifyDataSetChanged();
            }
            if (requestCode == editNote) {
               // notes.set((data.getIntExtra(MainActivity.position, -1)), data.getStringExtra(MainActivity.noteContent));
                notesListViewAdaptor.notifyDataSetChanged();
            }
        }
    }

//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//        String text = notes.get(info.position);
//        switch (item.getItemId()) {
//            case R.id.edit:
//                text = notes.get(info.position);
//                Intent editNoteIntent = new Intent(MainActivity.this, EditNoteActivity.class);
//                editNoteIntent.putExtra(MainActivity.noteContent, text);
//                editNoteIntent.putExtra(MainActivity.position, info.position);
//                startActivityForResult(editNoteIntent, editNote);
//                return true;
//            case R.id.duplicate:
//                text = notes.get(info.position);
//                notes.add(info.position + 1, text);
//                notesListViewAdaptor.notifyDataSetChanged();
//                return true;
//            case R.id.delete:
//                notes.remove(info.position);
//                notesListViewAdaptor.notifyDataSetChanged();
//                Toast.makeText(this, "Message deleted", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.createNotification:
//                Notification notification = new Notification();
//                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this).setContentTitle(notes.get(info.position)).setContentText("some text here").setSmallIcon(R.drawable.ic_note_add_black_24dp);
//                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//                notificationManager.notify(0, builder.build());
//
//                return true;
//            default:
//                return super.onContextItemSelected(item);
//        }
//    }


}
