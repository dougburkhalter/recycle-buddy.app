package net.itjustwerx.recyclebuddy;

import android.app.Activity;
import android.os.Bundle;
import android.os.Debug;
import android.text.Editable;
import android.view.View;
import android.view.View.*;
import android.widget.*;



public class search extends Activity {
    protected static final Editable edBlank = null;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //Initialize needed widgets
		final CheckBox cbAnything = (CheckBox) findViewById(R.id.cbAny);
        final CheckBox cbPlastic = (CheckBox) findViewById(R.id.cbPlastic);
        final CheckBox cbGlass = (CheckBox) findViewById(R.id.cbGlass);
        final CheckBox cbCardboard = (CheckBox) findViewById(R.id.cbCardboard);
        final CheckBox cbPaper = (CheckBox) findViewById(R.id.cbPaper);
        final CheckBox cbAluminum = (CheckBox) findViewById(R.id.cbAluminum);
        final EditText etCityState = (EditText) findViewById(R.id.etCityState);
        final EditText etZipCode = (EditText) findViewById(R.id.etZipCode);
        
        OnClickListener oclOtherCheckBoxes = new OnClickListener() {
        	public void onClick(View v) {
        		cbAnything.setChecked(false);
        	}
        };
        cbAnything.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		cbPlastic.setChecked(false);
                cbGlass.setChecked(false);
                cbCardboard.setChecked(false);
                cbPaper.setChecked(false);
                cbAluminum.setChecked(false);
        	}
        });
        cbPlastic.setOnClickListener(oclOtherCheckBoxes);
        cbGlass.setOnClickListener(oclOtherCheckBoxes);
        cbCardboard.setOnClickListener(oclOtherCheckBoxes);
        cbPaper.setOnClickListener(oclOtherCheckBoxes);
        cbAluminum.setOnClickListener(oclOtherCheckBoxes);
    }
}