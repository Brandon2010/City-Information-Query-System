package com.cis.cityInfoSystem;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.cis.adapter.AreaAdapter;
import com.cis.adapter.CateAdapter;
import com.cis.adapter.SortAdapter;
import com.cis.tools.JsonTools;
import com.cis.widget.PoiListItem;
import com.neu.cityinfosystem.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends Activity implements ServerListener,
		OnClickListener {
	private List<Map<String, Object>> mData;
	private List<Map<String, Object>> filterData;
	private View loadingView;
	private ListView list;
	private TextView resultAmount;
	private boolean isEnd = false;
	private ServerProxy server;
	ListAdapter areaAdapter = null;
	ListAdapter resultAdapter = null;
	CateAdapter cateAdapter = null;
	SortAdapter sortAdapter = null;
	View btnArea, btnType, btnSort;

	private boolean isLoadingRemoved = false;
	private String currentArea = "全部地区";
	private String currentCate = "全部频道";

	private String[] areaNames = { "和平区", "沈河区", "铁西区", "大东区", "皇姑区", "于洪区",
			"东陵区" };
	private String[] cateNames = { "美食", "购物", "休闲娱乐", "运动健身", "丽人", "结婚",
			"酒店", "生活服务" };

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

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.searchresult);

		mData = PoiResultData.getData();
		filterData = mData;

		list = (ListView) findViewById(R.id.resultlist);
		// list.setOnItemClickListener(mOnClickListener);
		resultAdapter = new PoiResultAdapter(this);

		btnArea = findViewById(R.id.id_area);
		btnArea.setOnClickListener(this);

		btnType = findViewById(R.id.id_type);
		btnType.setOnClickListener(this);

		btnSort = findViewById(R.id.id_sort);
		btnSort.setOnClickListener(this);

		Bundle bundle = this.getIntent().getExtras();
		String url = bundle.getString("url");
		new GetListAsyncTask().execute(url);
		
		loadingView = LayoutInflater.from(this).inflate(R.layout.listfooter,
				null);

		list.addFooterView(loadingView);
		// loadingView.setVisibility(View.GONE);

		list.setAdapter(resultAdapter);
		list.setOnItemClickListener(mOnClickListener);
		try {
			currentArea = URLEncoder.encode(currentArea, "utf-8");
			currentCate = URLEncoder.encode(currentCate, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		resultAmount = (TextView) findViewById(R.id.id_resultamount);
	}

	private AdapterView.OnItemClickListener mOnClickListener = new AdapterView.OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View v, int position,
				long id) {
			Bundle bundle = new Bundle();
			Map<String, Object> select = filterData.get(position);
			bundle.putInt("placeId", (Integer) select.get("placeId"));
			bundle.putString("placeName", (String) select.get("name"));
			bundle.putDouble("priceNumber", (Double) select.get("priceNumber"));
			bundle.putString("placeAddress", (String) select.get("address"));
			bundle.putString("description", (String) select.get("description"));
			bundle.putDouble("latitude", (Double) select.get("latitude"));
			bundle.putDouble("longitude", (Double) select.get("longitude"));
			bundle.putString("phone", (String) select.get("phone"));
			bundle.putString("picture_address", (String) select.get("picture_address"));
			bundle.putDouble("rating", (Double) select.get("rating"));
			bundle.putInt("ratingAmount", (Integer) select.get("ratingAmount"));
			bundle.putString("recommendation", (String) select.get("recommendation"));
			bundle.putInt("star", (Integer) select.get("star"));
			bundle.putString("area", (String) select.get("area"));
			Intent intent = new Intent();
			intent.setClass(ResultActivity.this, DetailActivity.class);
			intent.putExtras(bundle);
			startActivity(intent);
			// ResultActivity.this.finish();
		}
	};

	public class PoiResultAdapter extends BaseAdapter {

		private LayoutInflater mInflater;

		public PoiResultAdapter(Context context) {
			this.mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return filterData.size();
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

			Log.v("ListViewLog", "DF" + position);

			convertView = mInflater.inflate(R.layout.resultitem, null);

			PoiListItem item = (PoiListItem) convertView;

			Map map = filterData.get(position);

			item.setPoiData(map.get("name").toString(), map.get("price")
					.toString(), map.get("addr").toString(), ((Integer) map
					.get("star")).intValue(), ((Boolean) map.get("tuan"))
					.booleanValue(),
					((Boolean) map.get("card")).booleanValue(), ((Boolean) map
							.get("promo")).booleanValue(), ((Boolean) map
							.get("checkin")).booleanValue());

			//item.setDistanceText(map.get("distance").toString());

			if (position == filterData.size() - 1 && !isEnd) {
				loadingView.setVisibility(View.VISIBLE);
				server.sendRequest(ResultActivity.this);
			}

			return convertView;
		}

	}

	@Override
	public void serverDataArrived(List list, boolean isEnd) {
		this.isEnd = isEnd;
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			mData.add((Map<String, Object>) iter.next());
		}
		Message localMessage = new Message();
		if (!isEnd) {
			localMessage.what = 1;
		} else {
			localMessage.what = 2;
		}

		this.handler.sendMessage(localMessage);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.id_area: {
			showDialogPopup(R.id.id_area);

			break;
		}
		case R.id.id_type: {
			showDialogPopup(R.id.id_type);
			break;
		}
		case R.id.id_sort: {
			showDialogPopup(R.id.id_sort);
			break;
		}
		}

	}

	protected void showDialogPopup(int viewId) {
		AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);

		switch (viewId) {
		case R.id.id_area: {

			if (areaAdapter == null) {
				areaAdapter = new AreaAdapter(this);
			}

			localBuilder.setAdapter(areaAdapter, new areaPopupListener(
					areaAdapter));

			break;
		}

		case R.id.id_type: {
			if (cateAdapter == null) {
				cateAdapter = new CateAdapter(this);
			}
			localBuilder.setAdapter(cateAdapter, new catePopupListener(
					cateAdapter));
			break;
		}

		case R.id.id_sort: {
			if (sortAdapter == null) {
				sortAdapter = new SortAdapter(this);
			}
			localBuilder.setAdapter(sortAdapter,
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							String url = "";
							sortAdapter.setSelected(which);
							switch (which) {
							case 0:
								url = getResources().getText(R.string.host) + "PlaceServlet?placeOperation=all";
								break;
							case 1:
								// url =
								// "http://10.0.2.2:8080//CityInformationQuerySystem/servlet/PlaceServlet?placeOperation=rangedPlaces";
								break;
							case 3:
								url = getResources().getText(R.string.host) + "PlaceServlet?placeOperation=ratingPlaces&leastRating=10";
								break;
							case 4:
								url = getResources().getText(R.string.host) + "PlaceServlet?placeOperation=ratingPlaces&leastRating=8";
								break;
							case 5:
								url = getResources().getText(R.string.host) + "PlaceServlet?placeOperation=ratingPlaces&leastRating=6";
								break;
							case 7:
								url = getResources().getText(R.string.host) + "PlaceServlet?placeOperation=hPricePlaces&highestPrice=20";
								break;
							case 8:
								url = getResources().getText(R.string.host) + "PlaceServlet?placeOperation=rangePrices&lowestPrice=21&highestPrice=50";
								break;
							case 9:
								url = getResources().getText(R.string.host) + "PlaceServlet?placeOperation=rangePrices&lowestPrice=51&highestPrice=80";
								break;
							case 10:
								url = getResources().getText(R.string.host) + "PlaceServlet?placeOperation=rangePrices&lowestPrice=81&highestPrice=120";
								break;
							case 11:
								url = getResources().getText(R.string.host) + "PlaceServlet?placeOperation=rangePrices&lowestPrice=121&highestPrice=200";
								break;
							case 12:
								url = getResources().getText(R.string.host) + "PlaceServlet?placeOperation=lPricePlaces&leastPrice=201";
								break;
							}

							if (isLoadingRemoved) {
								list.addFooterView(loadingView);
								loadingView.setVisibility(View.VISIBLE);
								isLoadingRemoved = false;
							} else {
								loadingView.setVisibility(View.VISIBLE);
							}
							new GetListAsyncTask().execute(url);
						}
					});
			break;
		}

		}

		AlertDialog localAlertDialog = localBuilder.create();
		localAlertDialog.show();
	}

	/**
	 * Area listener
	 * 
	 * @author Brandon
	 * @version 1.0 2014-05-20
	 */
	class areaPopupListener implements DialogInterface.OnClickListener {
		AreaAdapter mAdapter;

		public areaPopupListener(ListAdapter adapter) {
			mAdapter = (AreaAdapter) adapter;
		}

		@Override
		public void onClick(DialogInterface dialog, int which) {
			System.out.println(which);
			System.out.println(mAdapter.getItem(which));
			currentArea = mAdapter.getItem(which).toString();
			System.out.println("currentArea = " + currentArea);
			System.out.println("currentCate" + currentCate);
			try {
				System.out.println("all Cate utf" + URLEncoder.encode("全部频道",
						"utf-8"));
				System.out.println("currentCate= "
						+ URLDecoder.decode(currentCate, "utf-8"));
				System.out.println("currentAreaAdapterTopLevel="
						+ mAdapter.isTopLevel());
				currentArea = URLEncoder.encode(currentArea, "utf-8");
				String currentCateName = URLDecoder
						.decode(currentCate, "utf-8");
				boolean isCateFirstLevel = ifSuperCate(currentCateName);
				// btnArea.setText(mAdapter.getItem(which).toString());
				filterData.clear();
				mData.clear();
				String search_url = "";
				if (mAdapter.isTopLevel()) {
					System.out.println("TopLevel");
					((AreaAdapter) mAdapter).setTypeIndex(which);
					if (which == 0) {
						((AreaAdapter) mAdapter).setTypeIndex(which);
						// filterData = mData;
						if (currentCate.equals(URLEncoder.encode("全部频道",
								"utf-8"))) {
							search_url = getResources().getText(R.string.host) + "PlaceServlet?placeOperation=all";
						} else {
							if (isCateFirstLevel)
								search_url = getResources().getText(R.string.host) + "PlaceServlet?placeOperation=findPlaces&searchName=category_name&searchValue="
										+ currentCate;
							else
								search_url = getResources().getText(R.string.host) + "PlaceServlet?placeOperation=findPlaces&searchName=subcategory_name&searchValue="
										+ currentCate;
						}
					} else {
						if (currentCate.equals(URLEncoder.encode("全部频道",
								"utf-8"))) {
							search_url = getResources().getText(R.string.host) + "PlaceServlet?placeOperation=findPlaces&searchName=district_name&searchValue="
									+ currentArea;
						} else {
							if (isCateFirstLevel) {
								search_url = getResources().getText(R.string.host) + "PlaceServlet?placeOperation=doubleValues&searchName1=category_name&searchValue1="
										+ currentCate
										+ "&searchName2=district_name&searchValue2="
										+ currentArea;
							} else {
								search_url = getResources().getText(R.string.host) + "PlaceServlet?placeOperation=doubleValues&searchName1=subcategory_name&searchValue1="
										+ currentCate
										+ "&searchName2=district_name&searchValue2="
										+ currentArea;
							}
						}
					}
				} else {
					if (which == 0) {
						((AreaAdapter) mAdapter).setTypeIndex(which);
						// filterData = mData;
						if (currentCate.equals(URLEncoder.encode("全部频道",
								"utf-8"))) {
							search_url = getResources().getText(R.string.host) + "PlaceServlet?placeOperation=all";
						} else {
							if (isCateFirstLevel)
								search_url = getResources().getText(R.string.host) + "PlaceServlet?placeOperation=findPlaces&searchName=category_name&searchValue="
										+ currentCate;
							else
								search_url = getResources().getText(R.string.host) + "PlaceServlet?placeOperation=findPlaces&searchName=subcategory_name&searchValue="
										+ currentCate;
						}
					} else {
						if (currentCate.equals(URLEncoder.encode("全部频道",
								"utf-8"))) {
							search_url = getResources().getText(R.string.host) + "PlaceServlet?placeOperation=findPlaces&searchName=area_name&searchValue="
									+ currentArea;
						} else {
							if (isCateFirstLevel) {
								search_url = getResources().getText(R.string.host) + "PlaceServlet?placeOperation=doubleValues&searchName1=category_name&searchValue1="
										+ currentCate
										+ "&searchName2=area_name&searchValue2="
										+ currentArea;
							} else {
								search_url = getResources().getText(R.string.host) + "PlaceServlet?placeOperation=doubleValues&searchName1=subcategory_name&searchValue1="
										+ currentCate
										+ "&searchName2=area_name&searchValue2="
										+ currentArea;
							}
						}
					}
				}
				if (isLoadingRemoved) {
					list.addFooterView(loadingView);
					loadingView.setVisibility(View.VISIBLE);
					isLoadingRemoved = false;
				} else {
					loadingView.setVisibility(View.VISIBLE);
				}
				new GetListAsyncTask().execute(search_url);
				System.out.println(search_url);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		/**
		 * Check the category is the first level category
		 * 
		 * @param areaName
		 * @return
		 */
		private boolean ifSuperCate(String cateName) {
			for (int i = 0; i < cateNames.length; i++) {
				if (cateName.equals(cateNames[i])) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * Category listener
	 * 
	 * @author Brandon
	 * @version 1.0 2014-05-20
	 */
	class catePopupListener implements DialogInterface.OnClickListener {
		CateAdapter mAdapter;

		public catePopupListener(ListAdapter adapter) {
			mAdapter = (CateAdapter) adapter;
		}

		@Override
		public void onClick(DialogInterface dialog, int which) {
			System.out.println(which);
			System.out.println(mAdapter.getItem(which));
			try {
				String allArea = URLEncoder.encode("全部地区", "utf-8");
				currentCate = mAdapter.getItem(which).toString();
				System.out.println("currentCate = " + currentCate);
				System.out.println("currentCateAdpaterTopLevel"
						+ mAdapter.isTopLevel());
				currentCate = URLEncoder.encode(currentCate, "utf-8");
				String currentAreaName = URLDecoder
						.decode(currentArea, "utf-8");
				System.out.println("currentArea = " + currentAreaName);
				System.out.println("allArea utf:" + allArea);
				System.out.println("currentArea:" + currentArea);
				boolean isAreaFirstLevel = ifSuperArea(currentAreaName);
				filterData.clear();
				mData.clear();
				String search_url = "";
				// btnType.setText(mAdapter.getItem(which).toString());
				if (mAdapter.isTopLevel()) {
					((CateAdapter) mAdapter).setTypeIndex(which);
					if (which == 0) {
						// filterData = mData;
						if (currentArea.equals(allArea)) {
							search_url = getResources().getText(R.string.host) + "PlaceServlet?placeOperation=all";
						} else {
							if (isAreaFirstLevel)
								search_url = getResources().getText(R.string.host) + "PlaceServlet?placeOperation=findPlaces&searchName=district_name&searchValue="
										+ currentArea;
							else
								search_url = getResources().getText(R.string.host) + "PlaceServlet?placeOperation=findPlaces&searchName=area_name&searchValue="
										+ currentArea;
						}
					} else {
						if (currentArea.equals(allArea)) {
							search_url = getResources().getText(R.string.host) + "PlaceServlet?placeOperation=findPlaces&searchName=category_name&searchValue="
									+ currentCate;
						} else {
							if (isAreaFirstLevel) {
								search_url = getResources().getText(R.string.host) + "PlaceServlet?placeOperation=doubleValues&searchName1=category_name&searchValue1="
										+ currentCate
										+ "&searchName2=district_name&searchValue2="
										+ currentArea;
							} else {
								search_url = getResources().getText(R.string.host) + "PlaceServlet?placeOperation=doubleValues&searchName1=category_name&searchValue1="
										+ currentCate
										+ "&searchName2=area_name&searchValue2="
										+ currentArea;
							}
						}
					}

				} else {
					if (which == 0) {
						((CateAdapter) mAdapter).setTypeIndex(which);
						if (currentArea.equals(allArea)) {
							search_url = getResources().getText(R.string.host) + "PlaceServlet?placeOperation=all";
						} else {
							if (isAreaFirstLevel)
								search_url = getResources().getText(R.string.host) + "PlaceServlet?placeOperation=findPlaces&searchName=district_name&searchValue="
										+ currentArea;
							else
								search_url = getResources().getText(R.string.host) + "PlaceServlet?placeOperation=findPlaces&searchName=area_name&searchValue="
										+ currentArea;
						}
					} else {
						if (currentArea.equals(allArea)) {
							search_url = getResources().getText(R.string.host) + "PlaceServlet?placeOperation=findPlaces&searchName=subcategory_name&searchValue="
									+ currentCate;
						} else {
							if (isAreaFirstLevel) {
								search_url = getResources().getText(R.string.host)+ "PlaceServlet?placeOperation=doubleValues&searchName1=subcategory_name&searchValue1="
										+ currentCate
										+ "&searchName2=district_name&searchValue2="
										+ currentArea;
							} else {
								search_url = getResources().getText(R.string.host) + "PlaceServlet?placeOperation=doubleValues&searchName1=subcategory_name&searchValue1="
										+ currentCate
										+ "&searchName2=area_name&searchValue2="
										+ currentArea;
							}
						}

					}
				}
				if (isLoadingRemoved) {
					list.addFooterView(loadingView);
					loadingView.setVisibility(View.VISIBLE);
					isLoadingRemoved = false;
				} else {
					loadingView.setVisibility(View.VISIBLE);
				}
				new GetListAsyncTask().execute(search_url);
				System.out.println(search_url);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		/**
		 * Check the area is the first level area
		 * 
		 * @param areaName
		 * @return
		 */
		private boolean ifSuperArea(String areaName) {
			for (int i = 0; i < areaNames.length; i++) {
				if (areaName.equals(areaNames[i])) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * The specific thread to get list
	 * 
	 * @author Brandon
	 * @version 1.0 2014-05-20
	 */
	public class GetListAsyncTask extends
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
				Toast.makeText(ResultActivity.this, "Network Problem", Toast.LENGTH_LONG).show();
				return;
			}
			filterData = result;
			resultAmount.setText("【共" + result.size() + "家】");
			((PoiResultAdapter) resultAdapter).notifyDataSetChanged();
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

		@Override
		protected List<Map<String, Object>> doInBackground(String... arg0) {
			ArrayList<Map<String, Object>> places = null;

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
					places = (ArrayList<Map<String, Object>>) JsonTools
							.getPlaces("places", jsonString);
				}
				System.out.println(places.size() + "hits");
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return places;
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