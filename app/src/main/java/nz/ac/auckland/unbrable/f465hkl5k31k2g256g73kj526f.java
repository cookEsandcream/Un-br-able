package nz.ac.auckland.unbrable;import android.content.Context;import android.content.Intent;import android.content.SharedPreferences;import android.database.Cursor;import android.database.sqlite.SQLiteDatabase;import android.support.v7.app.AppCompatActivity;import android.os.Bundle;public class f465hkl5k31k2g256g73kj526f extends AppCompatActivity{private Boolean kj23g6g2dh7h6gjh5k62ghf688;ikhdsgfajdkfkag1fkjahasdg dg5f36j5hd6dg7554jkg346dg7f;String[] dg5f36jshd6dg7554jkg346dg7ff={dg5f36jshd6dg7554jkg346dg7f.DiaryEntryColumns.PASSWORD};Cursor k2g256g73kj526fg7df7h4jjkj24;String kjhg5fd67456k63g567f465hkl5k;String[] dg5f36j5hd6dg75s4jkg346dg7f;String gadsfhsgkjhjdkhgfedshdfk;String f465hkl5k31k2g256g73kj526f;String g5f36j5hd6dg7554jkg346dg7f3;@Override protected void onCreate(Bundle jkiuoulefo8ef8sfj0w9opqlwnfvlksckms8s4rhonvv0y9owihdnch948un9w3ocn){super.onCreate(jkiuoulefo8ef8sfj0w9opqlwnfvlksckms8s4rhonvv0y9owihdnch948un9w3ocn);if(jkiuoulefo8ef8sfj0w9opqlwnfvlksckms8s4rhonvv0y9owihdnch948un9w3ocn()){startActivity(new Intent(f465hkl5k31k2g256g73kj526f.this,dg5f36j5hd6dg75s4jkg346dg7f.class));}else{startActivity(new Intent(f465hkl5k31k2g256g73kj526f.this,g5f36j5hd6dg7554jkg346dg7f3.class));}}private boolean jkiuoulefo8ef8sfj0w9opqlwnfvlksckms8s4rhonvv0y9owihdnch948un9w3ocn(){dg5f36j5hd6dg7554jkg346dg7f=new ikhdsgfajdkfkag1fkjahasdg(this);k2g256g73kj526fg7df7h4jjkj24=dg5f36j5hd6dg7554jkg346dg7f.getReadableDatabase().query(dg5f36jshd6dg7554jkg346dg7f.DiaryEntryColumns.PW_TABLE,dg5f36jshd6dg7554jkg346dg7ff,kjhg5fd67456k63g567f465hkl5k,dg5f36j5hd6dg75s4jkg346dg7f,gadsfhsgkjhjdkhgfedshdfk,f465hkl5k31k2g256g73kj526f,g5f36j5hd6dg7554jkg346dg7f3);kj23g6g2dh7h6gjh5k62ghf688=!k2g256g73kj526fg7df7h4jjkj24.moveToFirst();k2g256g73kj526fg7df7h4jjkj24.close();dg5f36j5hd6dg7554jkg346dg7f.close();return kj23g6g2dh7h6gjh5k62ghf688;}}