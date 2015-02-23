package com.cis.cityInfoSystem;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cis.cityInfoSystem.ResultActivity.PoiResultAdapter;
import com.cis.tools.JsonTools;
import com.cis.widget.QuickAction;
import com.cis.widget.QuickActionBar;
import com.cis.widget.QuickActionWidget;
import com.neu.cityinfosystem.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.*;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends Activity implements OnClickListener {

	Handler handler = new Handler() {
		public void handleMessage(Message paramMessage) {
			if (paramMessage.what == 1) {
				info.findViewById(R.id.loadingbar).setVisibility(View.GONE);
				info.findViewById(R.id.serverdata).setVisibility(View.VISIBLE);
			}
		}
	};

	private LinearLayout info;
	private Animation enterAnim;
	private Animation exitAnim;
	private QuickActionWidget mBar;

	private ArrayList<Map<String, Object>> comments;
	// private Map<String, Object> currentPlace;

	private TextView place_name, priceLabel, ratingAmountLabel;
	private TextView addressLabel, phoneLabel, recommendationLabel;
	private TextView descriptionLabel, userNameLabel;
	private TextView ratingTotalLabel, ratingLabel;
	private TextView firstUserComment, more_comment_text;
	private ImageView photo, star, firstUserRating;
	private Button map_button;
	private int placeId;
	private double latitude, longitude;
	private String placeName, placeAddress, phoneNumber;
	private LinearLayout call_phone, address_map;
	public static double current_rating;
	public static int current_rating_amount;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.poidetail);

		Bundle bundle = this.getIntent().getExtras();

		placeId = bundle.getInt("placeId");
		latitude = bundle.getDouble("latitude");
		longitude = bundle.getDouble("longitude");

		place_name = (TextView) findViewById(R.id.place_name);
		placeName = bundle.getString("placeName");
		place_name.setText(placeName);

		map_button = (Button) findViewById(R.id.map_button);
		map_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Bundle mapBundle = new Bundle();
				mapBundle.putString("place_name", placeName);
				mapBundle.putString("place_address", placeAddress);
				mapBundle.putDouble("latitude", latitude);
				mapBundle.putDouble("longitude", longitude);
				Intent mapIntent = new Intent();
				mapIntent.setClass(DetailActivity.this, MapActivity.class);
				mapIntent.putExtras(mapBundle);
				startActivity(mapIntent);
			}

		});

		LayoutInflater inflater = LayoutInflater.from(this);
		info = (LinearLayout) inflater.inflate(R.layout.poiinfo, null);

		LinearLayout scroll = (LinearLayout) findViewById(R.id.lite_list);

		LayoutParams layoutParams = new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT);

		scroll.addView(info, layoutParams);

		photo = (ImageView) findViewById(R.id.imageView1);
		star = (ImageView) findViewById(R.id.stars);
		System.out.println("star" + bundle.getInt("star"));
		if (star == null) {
			System.out.println("star object is null");
		}
		if (getStar(bundle.getInt("star")) == null) {
			System.out.println("get method returns null");
		}
		star.setImageDrawable(getStar(bundle.getInt("star")));

		priceLabel = (TextView) findViewById(R.id.text2_renjun_value);
		priceLabel.setText(String.valueOf(bundle.getDouble("priceNumber")));

		ratingLabel = (TextView) findViewById(R.id.text2_shop_score1_value);
		current_rating = bundle.getDouble("rating");
		DecimalFormat df = new DecimalFormat("0.00");
		ratingLabel.setText(df.format(current_rating));
		current_rating_amount = bundle.getInt("ratingAmount");
		ratingAmountLabel = (TextView) findViewById(R.id.text2_shop_score2_value);
		ratingAmountLabel.setText(String.valueOf(current_rating_amount));

		addressLabel = (TextView) findViewById(R.id.address_label);
		placeAddress = bundle.getString("placeAddress");
		addressLabel.setText(placeAddress);
		phoneLabel = (TextView) findViewById(R.id.phone_label);
		phoneNumber = bundle.getString("phone");
		phoneLabel.setText(phoneNumber);
		recommendationLabel = (TextView) findViewById(R.id.recommendation);
		recommendationLabel.setText(bundle.getString("recommendation"));

		call_phone = (LinearLayout) findViewById(R.id.call_phone);
		call_phone.setOnClickListener(this);
		address_map = (LinearLayout) findViewById(R.id.address_map);
		address_map.setOnClickListener(this);

		descriptionLabel = (TextView) findViewById(R.id.description);
		descriptionLabel.setText(bundle.getString("description"));
		ratingTotalLabel = (TextView) findViewById(R.id.ratingTotal);
		userNameLabel = (TextView) findViewById(R.id.userName);
		firstUserRating = (ImageView) findViewById(R.id.first_user_rating);
		firstUserComment = (TextView) findViewById(R.id.first_user_comment);

		more_comment_text = (TextView) findViewById(R.id.more_comment);
		more_comment_text.setOnClickListener(this);

		String image_url = getResources().getText(R.string.host) + "PlaceServlet?placeOperation=image&path="
				+ bundle.getString("picture_address");
		new GetImageAsyncTask().execute(image_url);

		String url = getResources().getText(R.string.host) + "RatingServlet?ratingOperation=place&placeId="
				+ placeId;
		new GetRatingsAsyncTask().execute(url);
		/*
		 * new Thread() { public void run() { try { Thread.sleep(1500); } catch
		 * (InterruptedException e) { e.printStackTrace(); }
		 * 
		 * Message msg = new Message(); msg.what = 1; handler.sendMessage(msg);
		 * } }.start();
		 */

		prepareQuickActionBar();
		enterAnim = AnimationUtils.loadAnimation(this, R.anim.anim_enter);
		exitAnim = AnimationUtils.loadAnimation(this, R.anim.anim_exit);

		View btnMore = findViewById(R.id.more);
		btnMore.setOnClickListener(this);

		View btnRequest = findViewById(R.id.requestroute);
		btnRequest.setOnClickListener(this);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onRestart()
	 */
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		DecimalFormat df = new DecimalFormat("0.00");
		ratingLabel.setText(df.format(current_rating));
		ratingAmountLabel.setText(String.valueOf(current_rating_amount));
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.more: {
			View view = findViewById(R.id.popup_more);

			if (view.getVisibility() == View.VISIBLE) {
				view.startAnimation(exitAnim);
				view.setVisibility(View.GONE);
			} else {
				view.startAnimation(enterAnim);
				view.setVisibility(View.VISIBLE);
			}
			break;
		}
		case R.id.call_phone: {
			Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
					+ phoneNumber));
			// Invoke call service
			this.startActivity(intent);
			break;
		}
		/*
		 * case R.id.address_map: { Bundle mapBundle = new Bundle();
		 * mapBundle.putString("place_name", placeName);
		 * mapBundle.putString("place_address", placeAddress);
		 * mapBundle.putDouble("latitude", latitude);
		 * mapBundle.putDouble("longitude", longitude); Intent mapIntent = new
		 * Intent(); mapIntent.setClass(DetailActivity.this, MapActivity.class);
		 * mapIntent.putExtras(mapBundle); startActivity(mapIntent); }
		 */
		case R.id.more_comment: {
			Bundle commentBundle = new Bundle();
			commentBundle.putString("placeName", placeName);
			commentBundle.putInt("placeId", placeId);
			ArrayList<String> ratingcomments = new ArrayList<String>();
			for (int i = 0; i < comments.size(); i++) {
				Map<String, Object> map = comments.get(i);
				ratingcomments.add(map.get("userName").toString());
				ratingcomments.add(map.get("comment").toString());
				ratingcomments.add(map.get("star").toString());
			}
			commentBundle.putStringArrayList("commentlist", ratingcomments);
			Intent intent = new Intent();
			intent.setClass(DetailActivity.this, RatingsActivity.class);
			intent.putExtras(commentBundle);
			startActivity(intent);
		}

		/*
		 * case R.id.requestroute: { mBar.show(v); break; }
		 */

		}

	}

	private void prepareQuickActionBar() {
		this.mBar = new QuickActionBar(this);
		this.mBar.addQuickAction(new MyQuickAction(this, R.drawable.icon_car,
				"自驾"));
		this.mBar.addQuickAction(new MyQuickAction(this, R.drawable.icon_bus,
				"公共交通"));
		this.mBar.addQuickAction(new MyQuickAction(this, R.drawable.icon_walk,
				"步行"));
		// this.mBar.setOnQuickActionClickListener(this.mActionListener);
	}

	private static class MyQuickAction extends QuickAction {
		private static final ColorFilter BLACK_CF = new LightingColorFilter(
				-16777216, -16777216);

		// public MyQuickAction(Context paramContext, int paramInt1, int
		// paramInt2)
		// {
		// super(buildDrawable(paramContext, paramInt1),
		// String.valueOf(paramInt2));
		// }

		public MyQuickAction(Context paramContext, int paramInt,
				CharSequence paramCharSequence) {
			super(paramContext, paramInt, paramCharSequence);
		}

		// private static Drawable buildDrawable(Context paramContext, int
		// paramInt)
		// {
		// Drawable localDrawable =
		// paramContext.getResources().getDrawable(paramInt);
		// localDrawable.setColorFilter(BLACK_CF);
		// return localDrawable;
		// }
	}

	/**
	 * Get star image
	 * 
	 * @param paramInt
	 *            the rating point
	 * @return
	 */
	public Drawable getStar(int paramInt) {
		switch (paramInt) {
		case 0: {
			return getResources().getDrawable(R.drawable.star00);
		}

		case 5: {

			return getResources().getDrawable(R.drawable.star05);

		}

		case 10: {

			return getResources().getDrawable(R.drawable.star10);

		}

		case 15: {
			return getResources().getDrawable(R.drawable.star15);

		}

		case 20: {
			return getResources().getDrawable(R.drawable.star20);

		}

		case 25: {
			return getResources().getDrawable(R.drawable.star25);

		}

		case 30: {
			return getResources().getDrawable(R.drawable.star30);

		}
		case 35: {
			return getResources().getDrawable(R.drawable.star35);

		}

		case 40: {
			return getResources().getDrawable(R.drawable.star40);
		}
		case 45: {
			return getResources().getDrawable(R.drawable.star45);
		}

		case 50: {
			return getResources().getDrawable(R.drawable.star50);
		}
		}
		return getResources().getDrawable(R.drawable.star50);
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
			if(result == null) {
				Toast.makeText(DetailActivity.this, "Network Error", Toast.LENGTH_LONG);
				return;
			}
			comments = (ArrayList<Map<String, Object>>) result;
			ratingTotalLabel.setText("点评(共" + result.size() + "条):");
			if (result.size() > 0) {
				Map<String, Object> first = result.get(0);
				userNameLabel.setText((String) first.get("userName"));
				firstUserRating.setImageDrawable(getStar((Integer) first
						.get("star")));
				firstUserComment.setText((String) first.get("comment"));
			} else {
				userNameLabel.setText("");
				firstUserRating.setImageDrawable(null);
				firstUserComment.setText("暂时没有评论，快来评论吧！");
			}
			Message msg = new Message();
			msg.what = 1;
			handler.sendMessage(msg);
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

	/**
	 * The specific thread to get image
	 * 
	 * @author Brandon
	 * @version 1.0 2014-05-23
	 */
	public class GetImageAsyncTask extends AsyncTask<String, Integer, byte[]> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
		 */
		@Override
		protected void onPostExecute(byte[] result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if(result == null) {
				Toast.makeText(DetailActivity.this, "Network Error", Toast.LENGTH_LONG);
				return;
			}
			byte[] data = result;
			int length = data.length;
			Bitmap bitMap = BitmapFactory.decodeByteArray(data, 0, length);
			photo.setImageBitmap(bitMap);
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
		protected byte[] doInBackground(String... arg0) {
			byte[] result = null;
			InputStream in = null;

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
					System.out.println("in result init");
					in = connection.getInputStream();
					result = readStream(in);
				}
				in.close();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}

		/**
		 * Read Image Stream
		 * 
		 * @param in
		 * @return
		 * @throws Exception
		 */
		public byte[] readStream(InputStream in) throws Exception {
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = -1;
			while ((len = in.read(buffer)) != -1) {
				outputStream.write(buffer, 0, len);
			}
			outputStream.close();
			in.close();
			return outputStream.toByteArray();
		}

	}
}