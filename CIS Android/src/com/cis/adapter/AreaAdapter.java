package com.cis.adapter;

import java.util.ArrayList;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cis.cityInfoSystem.ResultActivity;
import com.cis.widget.PoiListItem;
import com.neu.cityinfosystem.R;

public class AreaAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private ArrayList<ArrayList<String>> mData;
	private boolean isTopLevel = true;

	private int typeIndex = 0;

	private Context context;

	/**
	 * Constructor of area adapter
	 * 
	 * @param context
	 */
	public AreaAdapter(Context context) {
		this.context = context;
		this.mInflater = LayoutInflater.from(context);
		mData = getData();
	}

	/**
	 * Get area data list
	 * 
	 * @return area data list
	 */
	public ArrayList<ArrayList<String>> getDataList() {
		return mData;
	}

	/**
	 * Get top level name
	 * 
	 * @return top level name
	 */
	public String getSelect() {
		return mData.get(typeIndex).get(1);
	}

	/**
	 * Check whether current is in the top level
	 * 
	 * @return whether current is in the top level
	 */
	public boolean isTopLevel() {
		return isTopLevel;
	}

	/**
	 * Set current level
	 * 
	 * @param index
	 */
	public void setTypeIndex(int index) {
		typeIndex = index;
		if (index > 0) {
			isTopLevel = false;
		} else {
			isTopLevel = true;
		}
	}

	/**
	 * (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (isTopLevel) {
			return mData.size();
		} else {
			// System.out.println(mData.get(typeIndex).size()-1);
			return mData.get(typeIndex).size();
		}

	}

	/**
	 * (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int arg0) {
		if (isTopLevel) {
			return mData.get(arg0).get(1);
		} else {
			// System.out.println(mData.get(typeIndex).size()-1);
			return mData.get(typeIndex).get(arg0);
		}
	}

	/**
	 * (non-Javadoc)
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		convertView = mInflater.inflate(R.layout.dialog_list_item, null);

		if (isTopLevel) {
			View view = new View(context);
			LayoutParams param = new LayoutParams(30, 30);
			view.setLayoutParams(param);

			String area = mData.get(position).get(1);
			((TextView) convertView.findViewById(R.id.id_area)).setText(area);

			if (position == 0) {
				convertView.findViewById(R.id.ic_checked).setVisibility(
						View.VISIBLE);
			} else {
				((LinearLayout) convertView).addView(view, 0);
			}

		} else {

			String area = mData.get(typeIndex).get(position);
			((TextView) convertView.findViewById(R.id.id_area)).setText(area);

			if (position == 1) {
				View view = new View(context);
				LayoutParams param = new LayoutParams(30, 30);
				view.setLayoutParams(param);
				convertView.findViewById(R.id.ic_checked).setVisibility(
						View.VISIBLE);
				((LinearLayout) convertView).addView(view, 0);
			} else if (position > 1) {
				View view = new View(context);
				LayoutParams param = new LayoutParams(60, 30);
				view.setLayoutParams(param);
				((LinearLayout) convertView).addView(view, 0);
			}

		}

		return convertView;
	}

	/**
	 * Get the list of all area data
	 * 
	 * @return all area data
	 */
	private ArrayList<ArrayList<String>> getData() {
		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();

		ArrayList<String> all = new ArrayList<String>();
		all.add("全部地区");
		all.add("全部地区");
		data.add(all);

		ArrayList<String> heping = new ArrayList<String>();
		heping.add("全部地区");
		heping.add("和平区");
		heping.add("太原街");
		heping.add("五里河");
		heping.add("南湖公园/三好街");
		heping.add("南市/马路湾");
		heping.add("西塔");
		heping.add("新华广场");
		heping.add("长白");
		heping.add("北市场");
		heping.add("砂山");
		data.add(heping);

		ArrayList<String> shenhe = new ArrayList<String>();
		shenhe.add("全部地区");
		shenhe.add("沈河区");
		shenhe.add("中街/故宫");
		shenhe.add("奉天街沿线");
		shenhe.add("北站/市府");
		shenhe.add("文艺路/文化路");
		shenhe.add("五爱市场");
		shenhe.add("长青街");
		shenhe.add("东陵路");
		data.add(shenhe);

		ArrayList<String> tiexi = new ArrayList<String>();
		tiexi.add("全部地区");
		tiexi.add("铁西区");
		tiexi.add("铁西广场");
		tiexi.add("滑翔小区");
		tiexi.add("重工街沿线");
		tiexi.add("保工街");
		tiexi.add("北二路");
		tiexi.add("铁西万达");
		tiexi.add("艳粉地区");
		tiexi.add("兴工街");
		data.add(tiexi);

		ArrayList<String> dadong = new ArrayList<String>();
		dadong.add("全部地区");
		dadong.add("大东广场");
		dadong.add("东站");
		dadong.add("黎明广场");
		dadong.add("小北");
		dadong.add("东中街");
		data.add(dadong);

		ArrayList<String> huanggu = new ArrayList<String>();
		huanggu.add("全部地区");
		huanggu.add("皇姑区");
		huanggu.add("北行/辽大");
		huanggu.add("北陵");
		huanggu.add("塔湾/小白楼");
		data.add(huanggu);

		ArrayList<String> yuhong = new ArrayList<String>();
		yuhong.add("全部地区");
		yuhong.add("于洪区");
		yuhong.add("于洪广场");
		data.add(yuhong);

		ArrayList<String> dongling = new ArrayList<String>();
		dongling.add("全部地区");
		dongling.add("东陵区");
		dongling.add("浑南新区");
		data.add(dongling);

		return data;
	}
}
