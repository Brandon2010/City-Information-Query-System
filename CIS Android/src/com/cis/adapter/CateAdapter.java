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
		all.add("全部频道");
		all.add("全部频道");
		data.add(all);

		ArrayList<String> food = new ArrayList<String>();
		food.add("全部频道");
		food.add("美食");
		food.add("小吃快餐");
		food.add("东北菜");
		food.add("西餐");
		food.add("烧烤");
		food.add("火锅");
		food.add("朝韩料理");
		food.add("川菜");
		food.add("面包甜点");
		food.add("日本料理");
		food.add("自助餐");
		food.add("海鲜");
		food.add("粤菜");
		food.add("江浙菜");
		food.add("湘菜");
		food.add("东南亚菜");
		food.add("新疆菜");
		food.add("清真菜");
		food.add("云南菜");
		data.add(food);

		ArrayList<String> shopping = new ArrayList<String>();
		shopping.add("全部频道");
		shopping.add("购物");
		shopping.add("综合商场");
		shopping.add("超市/便利店");
		shopping.add("服饰鞋包");
		shopping.add("家具家居");
		shopping.add("数码家电");
		shopping.add("食品茶酒");
		shopping.add("化妆品专柜");
		shopping.add("药店");
		shopping.add("运动户外");
		shopping.add("书店");
		shopping.add("珠宝首饰");
		shopping.add("眼镜店");
		shopping.add("母婴儿童");
		shopping.add("花店");
		data.add(shopping);

		ArrayList<String> entertainment = new ArrayList<String>();
		entertainment.add("全部频道");
		entertainment.add("休闲娱乐");
		entertainment.add("洗浴");
		entertainment.add("电影院");
		entertainment.add("咖啡厅");
		entertainment.add("KTV");
		entertainment.add("酒吧");
		entertainment.add("景点郊游");
		entertainment.add("足疗按摩");
		entertainment.add("游乐游艺");
		entertainment.add("文化艺术");
		entertainment.add("公园");
		entertainment.add("温泉");
		entertainment.add("茶馆");
		entertainment.add("密室");
		entertainment.add("台球馆");
		entertainment.add("休闲网吧");
		entertainment.add("桌面游戏");
		entertainment.add("DIY手工坊");
		entertainment.add("采摘/农家乐");
		entertainment.add("棋牌室");
		data.add(entertainment);

		ArrayList<String> sports = new ArrayList<String>();
		sports.add("全部频道");
		sports.add("运动健身");
		sports.add("健身中心");
		sports.add("游泳馆");
		sports.add("瑜伽");
		sports.add("舞蹈");
		sports.add("体育场馆");
		sports.add("篮球场");
		sports.add("乒乓球馆");
		data.add(sports);

		ArrayList<String> ladies = new ArrayList<String>();
		ladies.add("全部频道");
		ladies.add("丽人");
		ladies.add("美发");
		ladies.add("美容/spa");
		ladies.add("美甲");
		ladies.add("个性写真");
		ladies.add("瘦身纤体");
		ladies.add("整形");
		data.add(ladies);

		ArrayList<String> marriage = new ArrayList<String>();
		marriage.add("全部频道");
		marriage.add("结婚");
		marriage.add("婚纱摄影");
		marriage.add("彩妆造型");
		marriage.add("婚宴酒店");
		data.add(marriage);

		ArrayList<String> hotels = new ArrayList<String>();
		hotels.add("全部频道");
		hotels.add("酒店");
		hotels.add("经济型酒店");
		hotels.add("公寓式酒店");
		hotels.add("四星级酒店");
		hotels.add("三星级酒店");
		data.add(hotels);

		ArrayList<String> life = new ArrayList<String>();
		life.add("全部频道");
		life.add("生活服务");
		life.add("医院");
		life.add("汽车服务");
		life.add("培训");
		life.add("儿童摄影");
		life.add("银行");
		life.add("学校");
		life.add("齿科");
		life.add("旅行社");
		life.add("快照/冲印");
		life.add("室内装潢");
		life.add("宠物");
		life.add("家政");
		life.add("停车场");
		life.add("洗衣店");
		data.add(life);

		return data;
	}
}