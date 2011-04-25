package net.itjustwerx.recyclebuddy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class Results extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.results);

		//Initialize needed widgets
		final TextView tvTips = (TextView) findViewById(R.id.tvTips);
		final TextView tvSample = (TextView) findViewById(R.id.tvSample);
		
		//Set tips text to random tip from site.
		try {tvTips.setText( new BufferedReader(new InputStreamReader(new DefaultHttpClient().execute(new HttpGet(new URI("http://recyclebuddy.itjustwerx.net/gettip.php"))).getEntity().getContent())).readLine());} catch (IllegalStateException e1) {} catch (ClientProtocolException e) {} catch (IOException e) {} catch (URISyntaxException e) {}
		tvTips.setSelected(true);

		
		//Get Data Passed to Activity
		String CityState, ZipCode, Materials;
		Bundle extras = getIntent().getExtras(); 
		if(extras !=null) {
		    CityState = extras.getString("CityState");
		    ZipCode = extras.getString("ZipCode");
		    Materials = extras.getString("Materials");
		    ProgressDialog searchDialog = ProgressDialog.show(Results.this, "", "Searching. Please wait...", true);
		    //Retrieve results
		    BufferedReader in = null;
	        try {
	            HttpClient client = new DefaultHttpClient();
	            HttpPost request = new HttpPost();
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
	            tvSample.setText(page);
	            } catch (IOException e) {
					e.printStackTrace();
				} catch (URISyntaxException e) {

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
		    
		    //Here's where we do the searching....
            searchDialog.dismiss();
		} else {
			new AlertDialog.Builder(this)
				.setMessage("Nothing to search for!")
			       .setCancelable(false)
			       .setPositiveButton("Sorry!", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			                Results.this.finish();
			           }
			       }).show();
		}

	}
}
