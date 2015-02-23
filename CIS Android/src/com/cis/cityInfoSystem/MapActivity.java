/**
 * 
 */
package com.cis.cityInfoSystem;

import android.app.Activity;
import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.ItemizedOverlay;
import com.baidu.mapapi.map.MKMapViewListener;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.OverlayItem;
import com.baidu.mapapi.map.PopupClickListener;
import com.baidu.mapapi.map.PopupOverlay;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.cis.tools.BMapUtil;
import com.neu.cityinfosystem.R;

/**
 * @author Brandon
 * 
 */
public class MapActivity extends Activity {

	private BMapManager mBMapMan = null;
	private MapView mMapView = null;
	private View viewCache;
	private TextView popupText, addressTag;
	private String place_name, place_address;
	private double latitude, longitude;
	private boolean isPopshow = false;

	/**
	 * 
	 */
	public MapActivity() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mBMapMan = new BMapManager(getApplicationContext());
		mBMapMan.init(new com.cis.tools.DemoApplication.MyGeneralListener());
		setContentView(R.layout.place_map);
		mMapView = (MapView) findViewById(R.id.bmapsView);
		mMapView.setBuiltInZoomControls(true);

		viewCache = getLayoutInflater()
				.inflate(R.layout.custom_text_view, null);
		popupText = (TextView) viewCache.findViewById(R.id.textcache);

		// pop demo
		// ����pop����ע�����¼������ӿ�
		/*
		 * PopupOverlay pop = new PopupOverlay(mMapView, new
		 * PopupClickListener() {
		 * 
		 * @Override public void onClickedPopup(int index) { //
		 * �ڴ˴���pop����¼���indexΪ�����������,����������������� } });
		 */

		Bundle bundle = this.getIntent().getExtras();
		place_name = bundle.getString("place_name");
		place_address = bundle.getString("place_address");
		latitude = bundle.getDouble("latitude");
		longitude = bundle.getDouble("longitude");

		System.out.println("latitude:" + latitude);
		System.out.println("longitude:" + longitude);

		Drawable mark = getResources().getDrawable(R.drawable.icon_gcoding);
		OverlayItem item1 = new OverlayItem(new GeoPoint(
				(int) (longitude * 1e6), (int) (latitude * 1e6)), "item1",
				"item1");
		item1.setMarker(mark);
		OverlayTest itemOverlay = new OverlayTest(mark, mMapView);
		mMapView.getOverlays().clear();
		mMapView.getOverlays().add(itemOverlay);
		itemOverlay.addItem(item1);

		addressTag = (TextView) findViewById(R.id.address_tag);
		addressTag.setText(place_address);
		/**
		 * ׼��pop������Դ������ʵ��������� ������������ͼƬ�Ĵ��ڣ����Դ�������ͼƬ������ͼƬ��һ��ͼƬ��
		 * �����Ĵ��ڣ������ͼƬ�Ĵ���˳����ϳ�һ��ͼƬ��ʾ. �������ͬ��ͼƬ��ʱ���ص������᷵�ص�ǰ�������ͼƬ����index
		 */
		/*
		 * popupText.setBackgroundResource(R.drawable.popup);
		 * popupText.setSingleLine(false); //
		 * popupText.setBackgroundColor(Color.RED);
		 * popupText.setText(place_name); // popupText.setAlpha((float) 0.4);
		 * pop.showPopup(BMapUtil.getBitmapFromView(popupText), new GeoPoint(
		 * (int) (longitude * 1e6), (int) (latitude * 1e6)), 8);
		 */
		// ��������λ��
		// GeoPoint ptTAM = new GeoPoint((int)(39.915 * 1E6), (int) (116.404 *
		// 1E6));
		// ����pop,����pop
		// pop.showPopup(bmps, ptTAM, 32);

		// �����������õ����ſؼ�
		MapController mMapController = mMapView.getController();
		// �õ�mMapView�Ŀ���Ȩ,�����������ƺ�����ƽ�ƺ�����
		// GeoPoint point = new GeoPoint((int) (39.915 * 1E6),
		// (int) (116.404 * 1E6));
		// �ø����ľ�γ�ȹ���һ��GeoPoint����λ��΢�� (�� * 1E6)
		mMapController.setCenter(new GeoPoint((int) (longitude * 1e6),
				(int) (latitude * 1e6)));// ���õ�ͼ���ĵ�
		mMapController.setZoom(16);// ���õ�ͼzoom����
		mMapView.refresh();
	}

	@Override
	protected void onDestroy() {
		mMapView.destroy();
		if (mBMapMan != null) {
			mBMapMan.destroy();
			mBMapMan = null;
		}
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		mMapView.onPause();
		if (mBMapMan != null) {
			mBMapMan.stop();
		}
		super.onPause();
	}

	@Override
	protected void onResume() {
		mMapView.onResume();
		if (mBMapMan != null) {
			mBMapMan.start();
		}
		super.onResume();
	}

	class OverlayTest extends ItemizedOverlay<OverlayItem> {
		// ��MapView����ItemizedOverlay
		public OverlayTest(Drawable mark, MapView mapView) {
			super(mark, mapView);
		}

		protected boolean onTap(int index) {
			PopupOverlay pop = new PopupOverlay(mMapView,
					new PopupClickListener() {
						@Override
						public void onClickedPopup(int index) {
							// �ڴ˴���pop����¼���indexΪ�����������,�����������������
						}
					});
			popupText.setBackgroundResource(R.drawable.popup);
			popupText.setSingleLine(false);
			// popupText.setBackgroundColor(Color.RED);
			popupText.setText(place_name);
			// popupText.setAlpha((float) 0.4);
			if (!isPopshow) {
				// �ڴ˴���item����¼�
				pop.showPopup(BMapUtil.getBitmapFromView(popupText),
						new GeoPoint((int) (longitude * 1e6),
								(int) (latitude * 1e6)), 62);
				isPopshow = true;
			} else {
				pop.hidePop();
				isPopshow = false;
			}
			return true;
		}

		public boolean onTap(GeoPoint pt, MapView mapView) {
			// �ڴ˴���MapView�ĵ���¼��������� trueʱ
			super.onTap(pt, mapView);
			return false;
		}
	}

}
