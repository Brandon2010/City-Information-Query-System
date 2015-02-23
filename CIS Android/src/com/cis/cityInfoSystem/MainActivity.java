package com.cis.cityInfoSystem;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.neu.cityinfosystem.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.app.ProgressDialog;

public class MainActivity extends Activity {
	private GridView grid;
	private DisplayMetrics localDisplayMetrics;
	private View view;
	private ProgressDialog progressdialog;

	// public LocationClient mLocationClient = null;
	// public BDLocationListener myListener = new MyLocationListener();

	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		view = this.getLayoutInflater().inflate(R.layout.main, null);
		setContentView(view);

		localDisplayMetrics = getResources().getDisplayMetrics();

		grid = (GridView) view.findViewById(R.id.my_grid);
		ListAdapter adapter = new GridAdapter(this);
		grid.setAdapter(adapter);
		grid.setOnItemClickListener(mOnClickListener);

		/*
		 * TelephonyManager telephonyManager = (TelephonyManager) this
		 * .getSystemService(Context.TELEPHONY_SERVICE); String
		 * NativePhoneNumber = null; NativePhoneNumber =
		 * telephonyManager.getLine1Number(); System.out.println("PhoneNumber" +
		 * NativePhoneNumber);
		 */
		/*
		 * mLocationClient = new LocationClient(getApplicationContext());
		 * //����LocationClient�� mLocationClient.registerLocationListener(
		 * myListener ); LocationClientOption option = new
		 * LocationClientOption();
		 * option.setLocationMode(LocationMode.Hight_Accuracy);//���ö�λģʽ
		 * option.setCoorType("bd09ll");//���صĶ�λ����ǰٶȾ�γ�ȣ�Ĭ��ֵgcj02
		 * option.setScanType(5000);//���÷���λ����ļ��ʱ��Ϊ5000ms
		 * option.setIsNeedAddress(true);//���صĶ�λ���������ַ��Ϣ
		 * option.setNeedDeviceDirect(true);//���صĶ�λ��������ֻ���ͷ�ķ���
		 * mLocClient.setLocOption(option);
		 */
	}

	private AdapterView.OnItemClickListener mOnClickListener = new AdapterView.OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View v, int position,
				long id) {
			switch (position) {
			case 0:
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, CategoryActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.main_enter, R.anim.main_exit);
				// ResultActivity.this.finish();
				break;
			case 1:
				final EditText userInput = new EditText(MainActivity.this);
				userInput.setHint("�����볡������");
				AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
						.setTitle("������").setIcon(R.drawable.hint)
						.setView(userInput)
						.setPositiveButton("ȷ��", new OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								String input = userInput.getText().toString();
								Bundle bundle = new Bundle();
								String url = "";
								try {
									url = getResources().getText(R.string.host)
											+ "PlaceServlet?placeOperation=inputPlaces&userInput="
											+ URLEncoder.encode(input, "utf-8");
								} catch (UnsupportedEncodingException e) {
									e.printStackTrace();
								}
								bundle.putString("url", url);
								Intent intent = new Intent();
								intent.setClass(MainActivity.this,
										ResultActivity.class);
								intent.putExtras(bundle);
								startActivity(intent);
							}
						}).setNegativeButton("ȡ��", new OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						}).show();
				break;
			case 2:
				//mLocClient.requestLocation();
				Toast toast = Toast.makeText(getApplicationContext(),
						"����ǰ��λ�ã� ������ѧ�ۺϿƼ���¥ γ�ȣ�41.770263 ���ȣ�123.425724,",
						Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER, 0, 0);
				LinearLayout toastView = (LinearLayout) toast.getView();
				ImageView imageCodeProject = new ImageView(
						getApplicationContext());
				imageCodeProject.setImageResource(R.drawable.hint);
				toastView.addView(imageCodeProject, 0);
				toast.show();
				break;
			case 3:
				Bundle bundle = new Bundle();
				String url = getResources().getText(R.string.host)
						+ "PlaceServlet?placeOperation=orderPlaces";
				bundle.putString("url", url);
				Intent resultIntent = new Intent();
				resultIntent.setClass(MainActivity.this, ResultActivity.class);
				resultIntent.putExtras(bundle);
				startActivity(resultIntent);
				break;
			case 4:
				/*
				 * LayoutInflater factory =
				 * LayoutInflater.from(MainActivity.this); final View dialogView
				 * = factory.inflate(R.layout.login_dialog, null); AlertDialog
				 * loginDialog = new AlertDialog.Builder(
				 * MainActivity.this).setTitle("��¼��").setView(dialogView)
				 * .setPositiveButton("ȷ��", new OnClickListener() {
				 * 
				 * @Override public void onClick(DialogInterface dialog, int
				 * which) { progressdialog = ProgressDialog.show(
				 * MainActivity.this, "���Ե�...", "���ڵ�¼...", true); }
				 * }).setNegativeButton("ȡ��", new OnClickListener() {
				 * 
				 * @Override public void onClick(DialogInterface dialog, int
				 * which) { dialog.dismiss(); } }).create(); loginDialog.show();
				 */
				Intent userIntent = new Intent();
				userIntent.setClass(MainActivity.this, UserActivity.class);
				startActivity(userIntent);
				break;
			}
		}
	};

	public class GridAdapter extends BaseAdapter {
		private LayoutInflater inflater;

		public GridAdapter(Context context) {
			inflater = LayoutInflater.from(context);
		}

		public final int getCount() {
			return 6;
		}

		public final Object getItem(int paramInt) {
			return null;
		}

		public final long getItemId(int paramInt) {
			return paramInt;
		}

		public View getView(int paramInt, View paramView,
				ViewGroup paramViewGroup) {
			paramView = inflater.inflate(R.layout.activity_label_item, null);
			TextView text = (TextView) paramView
					.findViewById(R.id.activity_name);

			switch (paramInt) {
			case 0: {
				text.setText("����");
				Drawable draw = getResources().getDrawable(
						R.drawable.home_button_local);
				draw.setBounds(0, 0, draw.getIntrinsicWidth(),
						draw.getIntrinsicHeight());
				text.setCompoundDrawables(null, draw, null, null);
				break;
			}

			case 1: {
				text.setText("����");
				Drawable draw = getResources().getDrawable(
						R.drawable.home_button_search);
				draw.setBounds(0, 0, draw.getIntrinsicWidth(),
						draw.getIntrinsicHeight());
				text.setCompoundDrawables(null, draw, null, null);
				break;
			}

			case 2: {
				text.setText("ǩ��");
				Drawable draw = getResources().getDrawable(
						R.drawable.home_button_checkin);
				draw.setBounds(0, 0, draw.getIntrinsicWidth(),
						draw.getIntrinsicHeight());
				text.setCompoundDrawables(null, draw, null, null);
				break;
			}

			/*
			 * case 3: { text.setText("�Ż݄�"); Drawable draw =
			 * getResources().getDrawable(R.drawable.home_button_promo);
			 * draw.setBounds(0, 0, draw.getIntrinsicWidth(),
			 * draw.getIntrinsicHeight()); text.setCompoundDrawables(null, draw,
			 * null, null); break; }
			 * 
			 * case 4: { text.setText("�����Ź�"); Drawable draw =
			 * getResources().getDrawable(R.drawable.home_button_tuan);
			 * draw.setBounds(0, 0, draw.getIntrinsicWidth(),
			 * draw.getIntrinsicHeight()); text.setCompoundDrawables(null, draw,
			 * null, null); break; }
			 */

			case 3: {
				text.setText("���а�");
				Drawable draw = getResources().getDrawable(
						R.drawable.home_button_rank);
				draw.setBounds(0, 0, draw.getIntrinsicWidth(),
						draw.getIntrinsicHeight());
				text.setCompoundDrawables(null, draw, null, null);
				break;
			}

			/*
			 * case 6: { text.setText("������"); Drawable draw =
			 * getResources().getDrawable(R.drawable.home_button_history);
			 * draw.setBounds(0, 0, draw.getIntrinsicWidth(),
			 * draw.getIntrinsicHeight()); text.setCompoundDrawables(null, draw,
			 * null, null); break; }
			 */

			case 4: {
				text.setText("��������");
				Drawable draw = getResources().getDrawable(
						R.drawable.home_button_myzone);
				draw.setBounds(0, 0, draw.getIntrinsicWidth(),
						draw.getIntrinsicHeight());
				text.setCompoundDrawables(null, draw, null, null);
				break;
			}
			case 5: {
				text.setText("����");
				Drawable draw = getResources().getDrawable(
						R.drawable.home_button_more);
				draw.setBounds(0, 0, draw.getIntrinsicWidth(),
						draw.getIntrinsicHeight());
				text.setCompoundDrawables(null, draw, null, null);
				break;
			}
			}

			paramView
					.setMinimumHeight((int) (96.0F * localDisplayMetrics.density));
			paramView
					.setMinimumWidth(((-12 + localDisplayMetrics.widthPixels) / 3));

			return paramView;
		}
	}

	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.finish();
		}
		return super.onKeyUp(keyCode, event);
	}

}