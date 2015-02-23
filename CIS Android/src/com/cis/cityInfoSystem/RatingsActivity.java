/**
 * 
 */
package com.cis.cityInfoSystem;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.cis.tools.JsonTools;
import com.cis.widget.PoiListItem;
import com.cis.widget.PoiStar;
import com.neu.cityinfosystem.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.telephony.TelephonyManager;

;

/**
 * The ratings activity to display all comments from users
 * 
 * @author Brandon
 * @version 1.0 2014-05-25
 */
public class RatingsActivity extends Activity implements ServerListener {

	private View loadingView;
	private ListView list;
	private boolean isLoadingRemoved = false;
	private boolean isEnd = false;
	private TextView place_name, rating_amount;
	private RatingBar ratingBar;
	private EditText inputComment;
	private ArrayList<Map<String, Object>> ratingList;
	private ListAdapter resultAdapter = null;
	private Button addButton;
	private int placeId, currentRating;
	private RatingBar inputRating;

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
	public RatingsActivity() {
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
		setContentView(R.layout.rating_result_list);
		Bundle bundle = this.getIntent().getExtras();
		ratingList = new ArrayList<Map<String, Object>>();
		ArrayList<String> transferedStrings = bundle
				.getStringArrayList("commentlist");
		for (int i = 0; i < transferedStrings.size(); i = i + 3) {
			Map<String, Object> singleLine = new HashMap<String, Object>();
			singleLine.put("userName", transferedStrings.get(i));
			singleLine.put("comment", transferedStrings.get(i + 1));
			singleLine.put("star",
					Integer.parseInt(transferedStrings.get(i + 2)));
			ratingList.add(singleLine);
		}
		list = (ListView) findViewById(R.id.ratinglist);
		resultAdapter = new RatingResultAdapter(this);
		place_name = (TextView) findViewById(R.id.rating_place_name);
		rating_amount = (TextView) findViewById(R.id.id_ratingamount);
		ratingBar = (RatingBar) findViewById(R.id.ratingBar1);
		inputComment = (EditText) findViewById(R.id.inputComment);
		addButton = (Button) findViewById(R.id.add_button);

		placeId = bundle.getInt("placeId");
		place_name.setText(bundle.getString("placeName"));
		rating_amount.setText("【共" + ratingList.size() + "条】");

		loadingView = LayoutInflater.from(this).inflate(R.layout.listfooter,
				null);
		list.addFooterView(loadingView);
		list.setAdapter(resultAdapter);

		inputRating = (RatingBar) findViewById(R.id.ratingBar1);
		inputRating
				.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {

					@Override
					public void onRatingChanged(RatingBar ratingBar,
							float rating, boolean fromUser) {
						// TODO Auto-generated method stub
						System.out.println("floteRating" + rating);
						currentRating = (int) (rating * 2);
						System.out.println("rating" + currentRating);
					}
				});

		Message localMessage = new Message();
		localMessage.what = 2;
		this.handler.sendMessage(localMessage);

		addButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				String userInputString = inputComment.getText().toString();
				try {
					if (isLoadingRemoved) {
						list.addFooterView(loadingView);
						loadingView.setVisibility(View.VISIBLE);
						isLoadingRemoved = false;
					} else {
						loadingView.setVisibility(View.VISIBLE);
					}
					new GetRatingsAsyncTask().execute(URLEncoder.encode(
							userInputString, "utf-8"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				/*
				 * Message localMessage = new Message(); localMessage.what = 3;
				 * handler.sendMessage(localMessage);
				 */
			}
		});

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

			convertView = mInflater.inflate(R.layout.comment_item, null);

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
			TextView user_name = (TextView) convertView
					.findViewById(R.id.rating_user_name);
			PoiStar stars = (PoiStar) convertView
					.findViewById(R.id.rating_star);
			TextView user_comment = (TextView) convertView
					.findViewById(R.id.ucomment);

			if (map == null) {
				System.out.println("map is null!!!!");
			} else if (user_name == null) {
				System.out.println("user_name is null!!!!");
			}
			user_name.setText(String.valueOf(map.get("userName")));
			user_comment.setText((String.valueOf(map.get("comment"))));
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
				Toast.makeText(RatingsActivity.this, "Network Error",
						Toast.LENGTH_LONG).show();
				return;
			}
			ratingList = (ArrayList<Map<String, Object>>) result;
			rating_amount.setText("【共" + result.size() + "条】");
			((RatingResultAdapter) resultAdapter).notifyDataSetChanged();
			DetailActivity.current_rating_amount = result.size();
			int totalRating = 0;
			for (int i = 0; i < result.size(); i++) {
				totalRating += (Integer) result.get(i).get("rating");
			}
			DetailActivity.current_rating = (totalRating * 1.00)
					/ result.size();
			inputRating.setRating(0);
			inputComment.setText("");
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
				URL url = new URL(getResources().getText(R.string.host)
						+ "RatingServlet");
				HttpURLConnection connection = (HttpURLConnection) url
						.openConnection();
				connection.setConnectTimeout(3000);
				connection.setRequestMethod("POST");
				connection.setDoInput(true);
				connection.setDoOutput(true);
				StringBuffer params = new StringBuffer();
				params.append("ratingOperation=add&rating=")
						.append(currentRating).append("&comment=")
						.append(arg0[0]).append("&placeId=").append(placeId)
						.append("&userId=6");
				byte[] bypes = params.toString().getBytes();
				connection.getOutputStream().write(bypes);
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
