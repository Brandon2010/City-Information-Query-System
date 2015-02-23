package com.cis.adapter;

import java.util.ArrayList;

import com.neu.cityinfosystem.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CateAdapter extends BaseAdapter {
	private ArrayList<ArrayList<String>> data;
	Context mContext;
	private LayoutInflater mInflater;

	private boolean isTopLevel = true;
	private int typeIndex = 0;

	/**
	 * Constructor of category adapter
	 * 
	 * @param context
	 */
	public CateAdapter(Context context) {
		mContext = context;
		data = getData();
		this.mInflater = LayoutInflater.from(context);
	}

	/**
	 * Get area data list
	 * 
	 * @return area data list
	 */
	public ArrayList<ArrayList<String>> getDataList() {
		return data;
	}

	/**
	 * Get top level name
	 * 
	 * @return top level name
	 */
	public String getSelect() {
		return data.get(typeIndex).get(1);
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
		if (isTopLevel) {
			return data.size();
		} else {
			// System.out.println(mData.get(typeIndex).size()-1);
			return data.get(typeIndex).size();
		}
	}

	/**
	 * (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		if (isTopLevel) {
			return data.get(position).get(1);
		} else {
			// System.out.println(mData.get(typeIndex).size()-1);
			return data.get(typeIndex).get(position);
		}
	}

	/**
	 * (non-Javadoc)
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
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
			View view = new View(mContext);
			LayoutParams param = new LayoutParams(30, 30);
			view.setLayoutParams(param);

			String cate = data.get(position).get(1);
			((TextView) convertView.findViewById(R.id.id_area)).setText(cate);

			if (position == 0) {
				convertView.findViewById(R.id.ic_checked).setVisibility(
						View.VISIBLE);
			} else {
				((LinearLayout) convertView).addView(view, 0);
			}

		} else {

			String area = data.get(typeIndex).get(position);
			((TextView) convertView.findViewById(R.id.id_area)).setText(area);

			if (position == 1) {
				View view = new View(mContext);
				LayoutParams param = new LayoutParams(30, 30);
				view.setLayoutParams(param);
				convertView.findViewById(R.id.ic_checked).setVisibility(
						View.VISIBLE);
				((LinearLayout) convertView).addView(view, 0);
			} else if (position > 1) {
				View view = new View(mContext);
				LayoutParams param = new LayoutParams(60, 30);
				view.setLayoutParams(param);
				((LinearLayout) convertView).addView(view, 0);
			}

		}

		return convertView;
	}

	/**
	 * Get the list of all category data
	 * 
	 * @return all category data
	 */
	private ArrayList<ArrayList<String>> getData() {
		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();

		ArrayList<String> all = new ArrayList<String>();
		all.add("ȫ��Ƶ��");
		all.add("ȫ��Ƶ��");
		data.add(all);

		ArrayList<String> food = new ArrayList<String>();
		food.add("ȫ��Ƶ��");
		food.add("��ʳ");
		food.add("С�Կ��");
		food.add("������");
		food.add("����");
		food.add("�տ�");
		food.add("���");
		food.add("��������");
		food.add("����");
		food.add("������");
		food.add("�ձ�����");
		food.add("������");
		food.add("����");
		food.add("����");
		food.add("�����");
		food.add("���");
		food.add("�����ǲ�");
		food.add("�½���");
		food.add("�����");
		food.add("���ϲ�");
		data.add(food);

		ArrayList<String> shopping = new ArrayList<String>();
		shopping.add("ȫ��Ƶ��");
		shopping.add("����");
		shopping.add("�ۺ��̳�");
		shopping.add("����/������");
		shopping.add("����Ь��");
		shopping.add("�Ҿ߼Ҿ�");
		shopping.add("����ҵ�");
		shopping.add("ʳƷ���");
		shopping.add("��ױƷר��");
		shopping.add("ҩ��");
		shopping.add("�˶�����");
		shopping.add("���");
		shopping.add("�鱦����");
		shopping.add("�۾���");
		shopping.add("ĸӤ��ͯ");
		shopping.add("����");
		data.add(shopping);

		ArrayList<String> entertainment = new ArrayList<String>();
		entertainment.add("ȫ��Ƶ��");
		entertainment.add("��������");
		entertainment.add("ϴԡ");
		entertainment.add("��ӰԺ");
		entertainment.add("������");
		entertainment.add("KTV");
		entertainment.add("�ư�");
		entertainment.add("���㽼��");
		entertainment.add("���ư�Ħ");
		entertainment.add("��������");
		entertainment.add("�Ļ�����");
		entertainment.add("��԰");
		entertainment.add("��Ȫ");
		entertainment.add("���");
		entertainment.add("����");
		entertainment.add("̨���");
		entertainment.add("��������");
		entertainment.add("������Ϸ");
		entertainment.add("DIY�ֹ���");
		entertainment.add("��ժ/ũ����");
		entertainment.add("������");
		data.add(entertainment);

		ArrayList<String> sports = new ArrayList<String>();
		sports.add("ȫ��Ƶ��");
		sports.add("�˶�����");
		sports.add("��������");
		sports.add("��Ӿ��");
		sports.add("�٤");
		sports.add("�赸");
		sports.add("��������");
		sports.add("����");
		sports.add("ƹ�����");
		data.add(sports);

		ArrayList<String> ladies = new ArrayList<String>();
		ladies.add("ȫ��Ƶ��");
		ladies.add("����");
		ladies.add("����");
		ladies.add("����/spa");
		ladies.add("����");
		ladies.add("����д��");
		ladies.add("��������");
		ladies.add("����");
		data.add(ladies);

		ArrayList<String> marriage = new ArrayList<String>();
		marriage.add("ȫ��Ƶ��");
		marriage.add("���");
		marriage.add("��ɴ��Ӱ");
		marriage.add("��ױ����");
		marriage.add("����Ƶ�");
		data.add(marriage);

		ArrayList<String> hotels = new ArrayList<String>();
		hotels.add("ȫ��Ƶ��");
		hotels.add("�Ƶ�");
		hotels.add("�����;Ƶ�");
		hotels.add("��Ԣʽ�Ƶ�");
		hotels.add("���Ǽ��Ƶ�");
		hotels.add("���Ǽ��Ƶ�");
		data.add(hotels);

		ArrayList<String> life = new ArrayList<String>();
		life.add("ȫ��Ƶ��");
		life.add("�������");
		life.add("ҽԺ");
		life.add("��������");
		life.add("��ѵ");
		life.add("��ͯ��Ӱ");
		life.add("����");
		life.add("ѧУ");
		life.add("�ݿ�");
		life.add("������");
		life.add("����/��ӡ");
		life.add("����װ��");
		life.add("����");
		life.add("����");
		life.add("ͣ����");
		life.add("ϴ�µ�");
		data.add(life);

		return data;
	}
}