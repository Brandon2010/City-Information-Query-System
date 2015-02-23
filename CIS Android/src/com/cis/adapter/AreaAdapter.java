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
		all.add("ȫ������");
		all.add("ȫ������");
		data.add(all);

		ArrayList<String> heping = new ArrayList<String>();
		heping.add("ȫ������");
		heping.add("��ƽ��");
		heping.add("̫ԭ��");
		heping.add("�����");
		heping.add("�Ϻ���԰/���ý�");
		heping.add("����/��·��");
		heping.add("����");
		heping.add("�»��㳡");
		heping.add("����");
		heping.add("���г�");
		heping.add("ɰɽ");
		data.add(heping);

		ArrayList<String> shenhe = new ArrayList<String>();
		shenhe.add("ȫ������");
		shenhe.add("�����");
		shenhe.add("�н�/�ʹ�");
		shenhe.add("���������");
		shenhe.add("��վ/�и�");
		shenhe.add("����·/�Ļ�·");
		shenhe.add("�尮�г�");
		shenhe.add("�����");
		shenhe.add("����·");
		data.add(shenhe);

		ArrayList<String> tiexi = new ArrayList<String>();
		tiexi.add("ȫ������");
		tiexi.add("������");
		tiexi.add("�����㳡");
		tiexi.add("����С��");
		tiexi.add("�ع�������");
		tiexi.add("������");
		tiexi.add("����·");
		tiexi.add("�������");
		tiexi.add("�޷۵���");
		tiexi.add("�˹���");
		data.add(tiexi);

		ArrayList<String> dadong = new ArrayList<String>();
		dadong.add("ȫ������");
		dadong.add("�󶫹㳡");
		dadong.add("��վ");
		dadong.add("�����㳡");
		dadong.add("С��");
		dadong.add("���н�");
		data.add(dadong);

		ArrayList<String> huanggu = new ArrayList<String>();
		huanggu.add("ȫ������");
		huanggu.add("�ʹ���");
		huanggu.add("����/�ɴ�");
		huanggu.add("����");
		huanggu.add("����/С��¥");
		data.add(huanggu);

		ArrayList<String> yuhong = new ArrayList<String>();
		yuhong.add("ȫ������");
		yuhong.add("�ں���");
		yuhong.add("�ں�㳡");
		data.add(yuhong);

		ArrayList<String> dongling = new ArrayList<String>();
		dongling.add("ȫ������");
		dongling.add("������");
		dongling.add("��������");
		data.add(dongling);

		return data;
	}
}
