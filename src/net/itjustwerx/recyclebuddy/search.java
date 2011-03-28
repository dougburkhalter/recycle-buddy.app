package net.itjustwerx.recyclebuddy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.Bundle;
import android.os.Debug;
import android.text.Editable;
import android.view.View;
import android.view.Window;
import android.view.View.*;
import android.widget.*;



public class search extends Activity {
    protected static final Editable edBlank = null;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
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
        final TextView tvTips = (TextView) findViewById(R.id.tvTips);
        
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
        
        BufferedReader in = null;
        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI("http://recyclebuddy.itjustwerx.net/gettip.php"));
            HttpResponse response = client.execute(request);
            in = new BufferedReader
            (new InputStreamReader(response.getEntity().getContent()));
            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }
            in.close();
            String page = sb.toString();
            System.out.println(page);
            tvTips.setText(page);
            } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			} finally {
            if (in != null) {
                try {
                    in.close();
                    } catch (IOException e) {
                    e.printStackTrace();
                }
            }
		}
    }
}