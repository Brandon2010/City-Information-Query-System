/**
 * 
 */
package com.cis.cityInfoSystem;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cis.cityInfoSystem.RatingsActivity.RatingResultAdapter;
import com.cis.tools.JsonTools;
import com.cis.widget.PoiStar;
import com.neu.cityinfosystem.R;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * The user main page activity
 * 
 * @author Brandon
 * @version 1.0
 */
public class UserActivity extends Activity implements ServerListener {

	private View loadingView;
	private ListView list;
	private TextView user_name_text, user_rating_amount;
	private ArrayList<Map<String, Object>> ratingList;
	private boolean isLoadingRemoved = false;
	private boolean isEnd = false;
	private RatingResultAdapter resultAdapter;
	private int userId = 6;

	Handler handler = new Handler() {
		public void handleMessage(Message paramMessage) {
			if (paramMessage.what == 1) {
				loadingView.setVisibility(View.GONE);
			} else if (paramMessage.what == 2) {
				list.removeFooterView(loadingView);
				isLoadingRemoved = true;
			} else if (paramMessage.what == 3) {
				list.addFooterView(loadingView);
				loadingView.setVisibility(View.VISIBLE);
				isLoadingRemoved = false;
			} else if (paramMessage.what == 4) {
				loadingView.setVisibility(View.VISIBLE);
			}
		}
	};

	/**
	 * 
	 */
	public UserActivity() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.user_main_page);
		ratingList = new ArrayList<Map<String, Object>>();
		user_name_text = (TextView) findViewById(R.id.user_name_text_label);
		user_rating_amount = (TextView) findViewById(R.id.id_placeratingamount);

		list = (ListView) findViewById(R.id.placeratinglist);
		loadingView = LayoutInflater.from(this).inflate(R.layout.listfooter,
				null);
		list.addFooterView(loadingView);

		resultAdapter = new RatingResultAdapter(this);
		list.setAdapter(resultAdapter);

		String url = getResources().getText(R.string.host)
				+ "RatingServlet?ratingOperation=user&userId=" + userId;
		new GetRatingsAsyncTask().execute(url);
		
		TelephonyManager telephonyManager = (TelephonyManager) this
				.getSystemService(Context.TELEPHONY_SERVICE);
		String nativePhoneNumber = telephonyManager.getLine1Number();
		//user_name_text.setText(nativePhoneNumber);
		user_name_text.setText("18658822017");
		System.out.println("phone:" + nativePhoneNumber);
	}

	public class RatingResultAdapter extends BaseAdapter {

		private LayoutInflater mInflater;

		public RatingResultAdapter(Context context) {
			this.mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return ratingList.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			if (convertView == null) {
				Log.v("is NULL", "DF2" + position);
			}

			// Log.v("ListViewLog", "DF" + position);

			convertView = mInflater.inflate(R.layout.user_comment_item, null);

			// PoiListItem item = (PoiListItem) convertView;

			Map<String, Object> map = ratingList.get(position);

			/*
			 * item.setPoiData(map.get("name").toString(), map.get("price")
			 * .toString(), map.get("addr").toString(), ((Integer) map
			 * .get("star")).intValue(), ((Boolean) map.get("tuan"))
			 * .booleanValue(), ((Boolean) map.get("card")).booleanValue(),
			 * ((Boolean) map .get("promo")).booleanValue(), ((Boolean) map
			 * .get("checkin")).booleanValue());
			 */

			// item.setDistanceText(map.get("distance").toString());
			TextView place_name = (TextView) convertView
					.findViewById(R.id.rating_place_name);
			PoiStar stars = (PoiStar) convertView
					.findViewById(R.id.place_rating_star);
			TextView place_comment = (TextView) convertView
					.findViewById(R.id.pcomment);

			if (map == null) {
				System.out.println("map is null!!!!");
			} else if (place_name == null) {
				System.out.println("user_name is null!!!!");
			}
			place_name.setText(String.valueOf(map.get("placeName")));
			place_comment.setText((String.valueOf(map.get("comment"))));
			stars.setStar((Integer) map.get("star"));

			if (position == ratingList.size() - 1 && !isEnd) {
				loadingView.setVisibility(View.VISIBLE);
				// server.sendRequest(ResultActivity.this);
			}

			return convertView;
		}

	}

	@Override
	public void serverDataArrived(List list, boolean isEnd) {
		this.isEnd = isEnd;
		/*
		 * Iterator iter = list.iterator(); while (iter.hasNext()) {
		 * ratingList.add((Map<String, Object>) iter.next()); }
		 */
		Message localMessage = new Message();
		if (!isEnd) {
			localMessage.what = 1;
		} else {
			localMessage.what = 2;
		}

		this.handler.sendMessage(localMessage);
	}

	/**
	 * The specific thread to get ratings
	 * 
	 * @author Brandon
	 * @version 1.0 2014-05-23
	 */
	public class GetRatingsAsyncTask extends
			AsyncTask<String, Integer, List<Map<String, Object>>> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
		 */
		@Override
		protected void onPostExecute(List<Map<String, Object>> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			if (result == null) {
				Toast.makeText(UserActivity.this, "Network Error",
						Toast.LENGTH_LONG);
				return;
			}
			ratingList = (ArrayList<Map<String, Object>>) result;
			user_rating_amount.setText("¡¾¹²" + result.size() + "Ìõ¡¿:");
			((RatingResultAdapter) resultAdapter).notifyDataSetChanged();
			serverDataArrived(result, true);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.AsyncTask#onPreExecute()
		 */
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.AsyncTask#doInBackground(Params[])
		 */
		@Override
		protected List<Map<String, Object>> doInBackground(String... arg0) {
			ArrayList<Map<String, Object>> ratings = null;

			System.out.println("In AsncTask!!");
			try {
				URL url = new URL(arg0[0]);
				HttpURLConnection connection = (HttpURLConnection) url
						.openConnection();
				connection.setConnectTimeout(3000);
				connection.setRequestMethod("GET");
				connection.setDoInput(true);
				int code = connection.getResponseCode();
				if (code == 200) {
					String jsonString = ChangeInputStream(connection
							.getInputStream());
					ratings = (ArrayList<Map<String, Object>>) JsonTools
							.getRatings("ratings", jsonString);
				}
				System.out.println(ratings.size() + "hits");
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return ratings;
		}

		/**
		 * Get json string
		 * 
		 * @param inputStream
		 * @return
		 */
		public String ChangeInputStream(InputStream inputStream) {
			String jsonString = "";
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			int len = 0;
			byte[] data = new byte[1024];

			try {
				while ((len = inputStream.read(data)) != -1) {
					outputStream.write(data, 0, len);
				}
				jsonString = new String(outputStream.toByteArray());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return jsonString;
		}

	}

}
