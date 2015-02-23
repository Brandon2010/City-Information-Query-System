use cityinfosys;

INSERT INTO location_district values('001', '和平区');
INSERT INTO location_district values('002', '沈河区');
INSERT INTO location_district values('003', '铁西区');
INSERT INTO location_district values('004', '大东区');
INSERT INTO location_district values('005', '皇姑区');
INSERT INTO location_district values('006', '于洪区');
INSERT INTO location_district values('007', '东陵区');

INSERT INTO location_area values('a01', '太原街', '001');
INSERT INTO location_area values('a02', '五里河', '001');
INSERT INTO location_area values('a03', '南市/马路湾', '001');
INSERT INTO location_area values('a04', '西塔', '001');
INSERT INTO location_area values('a05', '新华广场', '001');
INSERT INTO location_area values('a06', '长白', '001');
INSERT INTO location_area values('a07', '北市场', '001');
INSERT INTO location_area values('a08', '砂山', '001');
INSERT INTO location_area values('a09', '南湖公园/三好街', '001');

INSERT INTO location_area values('b01', '中街/故宫', '002');
INSERT INTO location_area values('b02', '奉天街沿线', '002');
INSERT INTO location_area values('b03', '北站/市府', '002');
INSERT INTO location_area values('b04', '文艺路/文化路', '002');
INSERT INTO location_area values('b05', '五爱市场', '002');
INSERT INTO location_area values('b06', '长青街', '002');
INSERT INTO location_area values('b07', '东陵路', '002');

INSERT INTO location_area values('c01', '铁西广场', '003');
INSERT INTO location_area values('c02', '滑翔小区', '003');
INSERT INTO location_area values('c03', '重工街沿线', '003');
INSERT INTO location_area values('c04', '保工街', '003');
INSERT INTO location_area values('c05', '北二路', '003');
INSERT INTO location_area values('c06', '铁西万达', '003');
INSERT INTO location_area values('c07', '艳粉地区', '003');
INSERT INTO location_area values('c08', '兴工街', '003');

INSERT INTO location_area values('d01', '大东广场', '004');
INSERT INTO location_area values('d02', '东站', '004');
INSERT INTO location_area values('d03', '黎明广场', '004');
INSERT INTO location_area values('d04', '小北', '004');
INSERT INTO location_area values('d05', '东中街', '004');

INSERT INTO location_area values('e01', '北行/辽大', '005');
INSERT INTO location_area values('e02', '北陵', '005');
INSERT INTO location_area values('e03', '塔湾/小白楼', '005');

INSERT INTO location_area values('f01', '于洪广场', '006');

INSERT INTO location_area values('g01', '浑南新区', '007');

INSERT INTO place_category values('001', '美食');
INSERT INTO place_category values('002', '购物');
INSERT INTO place_category values('003', '休闲娱乐');
INSERT INTO place_category values('004', '运动健身');
INSERT INTO place_category values('005', '丽人');
INSERT INTO place_category values('006', '结婚');
INSERT INTO place_category values('007', '酒店');
INSERT INTO place_category values('008', '生活服务');

INSERT INTO place_subcategory values('A01', '小吃快餐', '001');
INSERT INTO place_subcategory values('A02', '东北菜', '001');
INSERT INTO place_subcategory values('A03', '西餐', '001');
INSERT INTO place_subcategory values('A04', '烧烤', '001');
INSERT INTO place_subcategory values('A05', '火锅', '001');
INSERT INTO place_subcategory values('A06', '朝韩料理', '001');
INSERT INTO place_subcategory values('A07', '川菜', '001');
INSERT INTO place_subcategory values('A08', '面包甜点', '001');
INSERT INTO place_subcategory values('A09', '日本料理', '001');
INSERT INTO place_subcategory values('A10', '自助餐', '001');
INSERT INTO place_subcategory values('A11', '海鲜', '001');
INSERT INTO place_subcategory values('A12', '粤菜', '001');
INSERT INTO place_subcategory values('A13', '江浙菜', '001');
INSERT INTO place_subcategory values('A14', '湘菜', '001');
INSERT INTO place_subcategory values('A15', '东南亚菜', '001');
INSERT INTO place_subcategory values('A16', '新疆菜', '001');
INSERT INTO place_subcategory values('A17', '清真菜', '001');
INSERT INTO place_subcategory values('A18', '云南菜', '001');
INSERT INTO place_subcategory values('A19', '更多美食', '001');

INSERT INTO place_subcategory values('B01', '综合商场', '002');
INSERT INTO place_subcategory values('B02', '超市/便利店', '002');
INSERT INTO place_subcategory values('B03', '服饰鞋包', '002');
INSERT INTO place_subcategory values('B04', '家具家居', '002');
INSERT INTO place_subcategory values('B05', '数码家电', '002');
INSERT INTO place_subcategory values('B06', '食品茶酒', '002');
INSERT INTO place_subcategory values('B07', '化妆品专柜', '002');
INSERT INTO place_subcategory values('B08', '药店', '002');
INSERT INTO place_subcategory values('B09', '运动户外', '002');
INSERT INTO place_subcategory values('B10', '书店', '002');
INSERT INTO place_subcategory values('B11', '珠宝首饰', '002');
INSERT INTO place_subcategory values('B12', '眼镜店', '002');
INSERT INTO place_subcategory values('B13', '母婴儿童', '002');
INSERT INTO place_subcategory values('B14', '花店', '002');
INSERT INTO place_subcategory values('B15', '更多购物场所', '002');

INSERT INTO place_subcategory values('C01', '洗浴', '003');
INSERT INTO place_subcategory values('C02', '电影院', '003');
INSERT INTO place_subcategory values('C03', '咖啡厅', '003');
INSERT INTO place_subcategory values('C04', 'KTV', '003');
INSERT INTO place_subcategory values('C05', '酒吧', '003');
INSERT INTO place_subcategory values('C06', '景点郊游', '003');
INSERT INTO place_subcategory values('C07', '足疗按摩', '003');
INSERT INTO place_subcategory values('C08', '游乐游艺', '003');
INSERT INTO place_subcategory values('C09', '文化艺术', '003');
INSERT INTO place_subcategory values('C10', '公园', '003');
INSERT INTO place_subcategory values('C11', '温泉', '003');
INSERT INTO place_subcategory values('C12', '茶馆', '003');
INSERT INTO place_subcategory values('C13', '密室', '003');
INSERT INTO place_subcategory values('C14', '台球馆', '003');
INSERT INTO place_subcategory values('C15', '休闲网吧', '003');
INSERT INTO place_subcategory values('C16', '桌面游戏', '003');
INSERT INTO place_subcategory values('C17', 'DIY手工坊', '003');
INSERT INTO place_subcategory values('C18', '采摘/农家乐', '003');
INSERT INTO place_subcategory values('C19', '棋牌室', '003');
INSERT INTO place_subcategory values('C20', '更多休闲娱乐', '003');

INSERT INTO place_subcategory values('D01', '健身中心', '004');
INSERT INTO place_subcategory values('D02', '游泳馆', '004');
INSERT INTO place_subcategory values('D03', '瑜伽', '004');
INSERT INTO place_subcategory values('D04', '舞蹈', '004');
INSERT INTO place_subcategory values('D05', '体育场馆', '004');
INSERT INTO place_subcategory values('D06', '篮球场', '004');
INSERT INTO place_subcategory values('D07', '乒乓球馆', '004');
INSERT INTO place_subcategory values('D08', '更多运动场馆', '004');

INSERT INTO place_subcategory values('E01', '美发', '005');
INSERT INTO place_subcategory values('E02', '美容/spa', '005');
INSERT INTO place_subcategory values('E03', '美甲', '005');
INSERT INTO place_subcategory values('E04', '个性写真', '005');
INSERT INTO place_subcategory values('E05', '瘦身纤体', '005');
INSERT INTO place_subcategory values('E06', '整形', '005');

INSERT INTO place_subcategory values('F01', '婚纱摄影', '006');
INSERT INTO place_subcategory values('F02', '彩妆造型', '006');
INSERT INTO place_subcategory values('F03', '婚宴酒店', '006');
INSERT INTO place_subcategory values('F04', '更多婚礼服务', '006');

INSERT INTO place_subcategory values('G01', '经济型酒店', '007');
INSERT INTO place_subcategory values('G02', '公寓式酒店', '007');
INSERT INTO place_subcategory values('G03', '四星级酒店', '007');
INSERT INTO place_subcategory values('G04', '三星级酒店', '007');
INSERT INTO place_subcategory values('G05', '更多酒店住宿', '007');

INSERT INTO place_subcategory values('H01', '医院', '008');
INSERT INTO place_subcategory values('H02', '汽车服务', '008');
INSERT INTO place_subcategory values('H03', '培训', '008');
INSERT INTO place_subcategory values('H04', '儿童摄影', '008');
INSERT INTO place_subcategory values('H05', '银行', '008');
INSERT INTO place_subcategory values('H06', '学校', '008');
INSERT INTO place_subcategory values('H07', '齿科', '008');
INSERT INTO place_subcategory values('H08', '旅行社', '008');
INSERT INTO place_subcategory values('H09', '快照/冲印', '008');
INSERT INTO place_subcategory values('H10', '室内装潢', '008');
INSERT INTO place_subcategory values('H11', '宠物', '008');
INSERT INTO place_subcategory values('H12', '家政', '008');
INSERT INTO place_subcategory values('H13', '停车场', '008');
INSERT INTO place_subcategory values('H14', '洗衣店', '008');
INSERT INTO place_subcategory values('H15', '更多生活服务', '008');

INSERT INTO user values(1, '18809891038', '123', '0');
INSERT INTO user values(2, '18970663288', '123', '0');
INSERT INTO user values(3, '13590907123', '123', '0');
INSERT INTO user values(4, '15555215554', '123', '0');
INSERT INTO user values(5, '18809891151', '123', '0');
INSERT INTO user values(6, '18658822017', '123', '0');

INSERT INTO location values(1, '中华路88号', 'a01', 123.409223, 41.79779);
INSERT INTO place_information values(1, '斗牛士牛排餐厅', 'A03', 1, 
'小蜜牛排很适合女生吃，主厨推荐还是有点硬，【环境好，正宗西餐】赞哈哈。强烈推荐的是他家的甜品！单子上的甜品每一个都超级好吃超级正宗！主推焦糖布丁和冰淇淋！其次是黑森林～提拉米苏因为太正宗吃两口就腻…汉堡肉意大利面好吃也很大，想吃意面的单点一分这个就OK了…总之五星推荐！'
, 9, 2, '中华路88号新世界百货6楼', 
'焦糖布丁 土豆泥沙拉 提拉米苏 罗宋汤 香蒜面包 起司蛋糕 水果沙拉 西瓜汁 鲜榨柠檬汁 黑森林蛋糕 铁盘雪花牛排 菲力牛排 招牌牛排', 
'024-23878855', 95, 'G:/CityInfoPictures/1.jpg', '0');
INSERT INTO user_rating_list values(1, 10, '贵在小吃免费 咖啡和提拉米苏很喜欢～经常去吃 很不错 还会再去的', 1, 1, '0');
INSERT INTO user_rating_list values(2, 8, '服务超级棒，上餐很快的，东西也很好吃，团购超级划算，下回还回去！', 1, 2, '0');

/*-----------------First Insert------------*/
INSERT INTO location values(2, '太原南街201号', 'a01', 123.400745, 41.788701);
INSERT INTO place_information values(2, '福太生煎', 'A01', 2, 
'生煎包超级好吃，满满一包汤汁，新鲜出炉，虽然很烫，但是爱不释口啊！', 8, 1, '太原南街201号(金都饭店南行100米南五南六之间)', 
'生煎包 鸭血粉丝汤 牛杂汤 口水鸡 白粥 牛肉泡馍', 
'13940180190', 12, 'G:/CityInfoPictures/2.jpg', '0');
INSERT INTO user_rating_list values(3, 8, '品质佳，满意。最喜欢口水鸡，肉质软烂，酱汁鲜香。', 2, 1, '0');

INSERT INTO location values(3, '太原南街2号', 'a01', 123.406239, 41.798092);
INSERT INTO place_information values(3, '黄记煌(和平万达店)', 'A02', 3, 
'酱汁很好味，特色江团鱼 、鲟鱼， 无刺的江团鱼， 鲟鱼外表皮有非常坚硬的刺，鲟鱼的软骨也别有风味', 
9, 2, ' 太原南街2号万达广场四楼黄记煌(沈阳站)', 
'江团鱼 牛蛙 鸡翅 扯面 组合锅 捞拌什锦 牛肉 牛蛙锅 牛蛙焖锅 红薯块 酸梅汁 鸡肉 吊炉饼 鲜虾锅 草鱼锅 杏鲍菇 麻辣牛肉 什锦捞拌 牛柳锅 虾锅 肥牛 脆皮香蕉 香蜜鹅肝冻 猪排骨', 
'024-23580683', 71, 'G:/CityInfoPictures/3.jpg', '0');
INSERT INTO user_rating_list values(4, 10, '味道不错，，，，，，，很有特色的酱料，还会去的，人真是多啊', 3, 2, '0');
INSERT INTO user_rating_list values(5, 8, '铁西的跟和平的都去过，可见我是真的很喜欢这家店。就是每次吃完都太罪恶了。而且两个人吃一锅实在有些费劲，量比较大的。里美的红薯很好吃喔', 3, 3, '0');

/*------------------Above 5/3 Insert--------------*/
INSERT INTO location values(4, '南京北街268号', 'a01', 123.41326, 41.797924);
INSERT INTO place_information values(4, '比尔森健康烤肉', 'A04', 4, 
'环境很好，地方挺大，菜式挺多，注意两小时用餐，时间超过会另收费', 
8, 1, ' 南京北街268号海润国际大厦A座4楼(欧亚联营北侧)', 
'烤肉 三文鱼 烤鸭 水果 鸡脆骨 西冷牛排 沙拉 西点 蛋挞 蛋糕 蜜汁梅肉 冰虾 披萨 烤鸡翅 酸奶 培根 黄蚬子 烤梅肉 鸡翅 寿司 梅肉 水饺 鱿鱼 香蕉沙拉 薯片', 
'024-31315777', 53, 'G:/CityInfoPictures/4.jpg', '0');
INSERT INTO user_rating_list values(6, 8, '海鲜种类少，还要自己煮，排队的人不少，烤肉味道一般吧，没有想象的好，鱿鱼圈很好吃，吃了好几盘。吃的时候还有驻唱，还可以。', 4, 3, '0');

INSERT INTO location values(5, '太原北街1号', 'a01', 123.415931, 41.807839);
INSERT INTO place_information values(5, '皇城老妈(太原北街店)', 'A05', 5, 
'一家挺高档的火锅店，挺多食材都是空运到沈阳的，所以价位偏高，不过环境和服务都不错，服务员都是一口的四川口音，确实很正宗。锅底是鸳鸯锅，辣锅是现场剪开的袋，闻起来就很香。他家的老妈牛肉和鸡肉评价颇高，吃了之后感觉名副其实啊，肉很嫩，一点不硬，一大块放在辣锅里然后沾着酱料吃香辣过瘾，甜香可口。鲍鱼仔嘛一般，两个原价竟然要80，要是单点的话点这个真是有点大头了，海鲜市场现在鲍鱼才10块钱一个。酸梅汤很好喝，解辣。', 
10, 1, ' 太原北街1号(金城宾馆南门西侧)', 
'老妈牛肉 虾滑 老妈羊肉 老妈鸡肉 豌豆尖 苕粉 芒果沙冰 肥牛 虾丸 毛肚 鴛鴦鍋 花生沙冰 鹅肠 滑类拼盘 豆制品拼盘 青笋 鳝鱼 麻圆 鲍鱼 菇類拼盤 百叶 鸭掌 鮑魚 张飞牛肉 冻豆腐', 
'024-84181818', 113, 'G:/CityInfoPictures/5.jpg', '0');
INSERT INTO user_rating_list values(7, 10, '非常好！菜品都很新鲜，口味还可以，环境很到位服务很周到。虾滑不错，老妈牛肉很好', 5, 1, '0');

INSERT INTO location values(6, '五里河街8号', 'a02', 123.438212, 41.775246);
INSERT INTO place_information values(6, '国华烤肉(五里河街店)', 'A06', 6, 
'韩式烧烤，小料很另类，是麻酱的，偏甜口，不同于蜜汁或者辣酱。推荐拌饭，很美味。', 
6, 1, ' 五里河街8号(辽台西门斜对面)', 
'牛肉 花菜 烤鸡脆骨 石锅拌饭 五花肉 牛排 羊肉串 风干肠 LA牛排 冷面 肉筋 烤鳗鱼 拌饭 肥牛 烤肉 牛舌 国华大肉串 地瓜饼 拌墨斗 拌花菜 西冷牛排 烤鸡翅 凤梨卷 墨斗 拌板筋', 
'024-23929848', 53, 'G:/CityInfoPictures/6.jpg', '0');
INSERT INTO user_rating_list values(8, 6, '烤肉尚可，羊肉串应该是真的，因为吃完感觉好膻。推荐炒米条、拌冷面、拌花菜、拌墨斗……', 6, 2, '0');

INSERT INTO location values(7, '青年大街茂业百货', 'a02', 123.442593, 41.770175);
INSERT INTO place_information values(7, '川人百味(茂业百货店)', 'A07', 7, 
'最爱川菜，也很习惯川人百味的味道，沈阳这家做的还挺有规模的。', 
7, 2, '青年大街茂业百货B1楼(近三好街)', 
'水煮鱼 口水鸡 麻辣香锅 酸菜鱼 川北凉粉 干锅土豆片 凉拌面 干锅鸡 火爆大头菜 拌面 香酥鸭 干锅排骨 担担面 皮蛋豆腐 干煸豆角 干锅娃娃菜 干锅双素 宫保鸡丁 蕨根粉 鱼香茄子 酸菜豆花鱼 口水肥牛 紫薯泥', 
'15710530897', 38, 'G:/CityInfoPictures/7.jpg', '0');
INSERT INTO user_rating_list values(9, 8, '从开业就很喜欢吃，经常会去，但是现在感觉味道不如从前了。上菜速度一直都不快', 7, 1, '0');
INSERT INTO user_rating_list values(10, 6, '中规中矩的川菜连锁 总体说来味道还可以 但是水煮肉片很失望 还没有东北菜馆的好吃 皮蛋豆腐不错', 7, 2, '0');

INSERT INTO location values(8, '十一纬路40-4号', 'a03', 123.424091, 41.794751);
INSERT INTO place_information values(8, '辣尚瘾', 'A07', 8, 
'性价比很高的餐厅，环境也不错。必点烤鱼或者麻辣香锅。经常有团购，一份烤鱼可以三到四个人吃，点三四种配菜，再加几个小菜，刚好。#麻辣香锅# #香辣烤鱼# #口水鸡# 。', 
8, 1, '十一纬路40-4号(近鹿鸣春)', 
'烤鱼 蓝莓山药 麻辣香锅 香辣烤鱼 黑椒鲶鱼 大拌菜 陈醋菠菜花生 口水鸡 麻辣烤鱼 香辣鸡翅 原味奶茶 黑胡椒烤鱼 上瘾瓜条 梭边鱼 酸菜烤鱼 鲶鱼', 
'024-22842000', 51, 'G:/CityInfoPictures/8.jpg', '0');
INSERT INTO user_rating_list values(11, 8, '小伙伴推荐了他家黑胡椒烤鱼，听说不错，就过来尝尝，团购超级合适，黑胡椒烤鱼味道很浓厚，吃着特别有牛排的感觉超级喜欢，但是川菜就一般了。', 8, 3, '0');

INSERT INTO location values(9, '南京北街28号', 'a04', 123.426213, 41.815131);
INSERT INTO place_information values(9, '西贝莜面村(皇寺广场店)', 'F03', 9, 
'店员超级热情，西北风味，很有特色，很赞，很敞亮，还有小游戏，特别有意思', 
9, 2, '南京北街28号(近皇寺广场)', 
'功夫鱼 羊肉串 莜面窝窝 莜面鱼鱼 酸奶 大拌菜 烤羊背 炒莜面 拌面筋 烧羊棒 烤羊腿 烤羊排 锡锅酱香羊羔肉 酸菜炒莜面 黄馍馍 炝炒牛心菜 面筋 羊骨棒 牛大骨 黄米凉糕 蘑菇炒莜面 沙棘汁 土豆炖牛肉 拔丝奶皮 大盘鸡', 
'024-23493382', 76, 'G:/CityInfoPictures/9.jpg', '0');
INSERT INTO user_rating_list values(12, 10, '喜欢大羊腿！很入味，烤串更是爽歪歪，莜面鱼鱼这名字真是卡通，我喜欢！', 9, 2, '0');
INSERT INTO user_rating_list values(13, 8, '烤羊腿不错，酸奶很赞！上菜一级快！服务非常好！来和走服务员都很热情！', 9, 3, '0');

INSERT INTO location values(10, '和平南大街93号', 'a05', 123.405964, 41.776235);
INSERT INTO place_information values(10, '盛世桃源商务会馆', 'C01', 10, 
'盛世桃园，听着让人很舒服。洗浴98.装修大气，态度很好，设施较新。洗浴用品也可以，资生堂的妮维雅的还有忘记了。护肤的是强生和高斯的。很舒适。
大厅也很好，有免费的水果，应季的，很新鲜。
自助餐198一位，海鲜的，喜欢海鲜是不错的选择。', 
10, 1, '和平南大街南八马路93号(近体育系)', 
'洗浴, 海鲜自助火锅, 韩式自助烤肉', 
'024-25949999', 182, 'G:/CityInfoPictures/10.jpg', '0');
INSERT INTO user_rating_list values(14, 10, '这里的环境比较土豪，自助餐还不错，感觉人会很多，但是我去的那天还可以，服务员很热情', 10, 1, '0');

INSERT INTO location values(11, '长白西路3-2号滨河美食街216门', 'a06', 123.396541, 41.756044);
INSERT INTO place_information values(11, '黑松白鹿', 'A09', 11, 
'味道很赞，三文鱼厚切特别香糯口感超赞！最近168一位，很划算！', 
10, 1, '长白西路3-2号滨河美食街216门(近万科城)', 
'三文鱼 木瓜沙拉 黑松白鹿卷 牛肉刺身 土瓶蒸 盐烤虾 鳗鱼 炸虾天妇罗 牛舌 烤银杏 黑松白露卷 美乃滋扇贝 韩风烹蜗牛 刺身拼盘 原汁蘑 炙燎三文鱼 木瓜虾仁沙拉 芒果鲑鱼沙拉 甜虾 各种刺身 三文鱼刺身 扇贝 牛肉刺身沙拉 风卷残云 北极贝裙边沙拉', 
'024-83737760', 198, 'G:/CityInfoPictures/11.jpg', '0');
INSERT INTO user_rating_list values(15, 10, '推荐自助，能尝到很多样好吃，个人喜欢她们家的日料觉得非常好、', 11, 1, '0');

INSERT INTO location values(12, '长白二街3-2号万科城浑河天地A-B座', 'a06', 123.395332, 41.756488);
INSERT INTO place_information values(12, '金逸国际影城(沈阳长白店)', 'C02', 12, 
'音效很好，人少，比较清净。装修略简单些', 
6, 1, '长白二街3-2号万科城浑河天地A-B座(万科城北侧)', 
'电影院', 
'024-83737779', 28, 'G:/CityInfoPictures/12.jpg', '0');
INSERT INTO user_rating_list values(16, 6, '环境不是我喜欢的，但是价格很便宜，性价比高！有些影厅的设计不怎么合理，过道竟然在影厅最中间。座位也不是很舒服。属于大众电影院', 12, 2, '0');

INSERT INTO location values(13, '哈尔滨路58号', 'a07', 123.431719, 41.817933);
INSERT INTO place_information values(13, '垂香楼', 'C07', 13, 
'是个不错的休闲的地方，人少，手法也不错，和总店比起来会便宜一下，地方不大很清静！', 
6, 1, '哈尔滨路58号(新洪记对面)', 
'足疗按摩', 
'024-22503270', 99, 'G:/CityInfoPictures/13.jpg', '0');
INSERT INTO user_rating_list values(17, 6, '环境很好，技师手法不错，价位偏高，茶建议还是选花茶吧60元一壶，可以无限加水。其他的茶太贵。足疗建议选足疗和头疗，或者只选足疗就好了，身上的什么的 感觉没有足疗实惠。', 13, 2, '0');

INSERT INTO location values(14, '南京南街201号', 'a08', 123.406256, 41.788741);
INSERT INTO place_information values(14, '宝岛眼镜(长白大润发)', 'B12', 14, 
'宝岛连锁眼镜店', 
0, 0, '南京南街201号大润发超市1楼', 
'眼镜', 
'024-24684006',398, 'G:/CityInfoPictures/14.jpg', '0');

INSERT INTO location values(15, '和平区砂川街33号', 'a08', 123.389629, 41.778447);
INSERT INTO place_information values(15, '格林豪泰酒店(砂川街店)', 'G01', 15, 
'格林豪泰（沈阳胜利南街店）位于和平区砂川街，在沈阳火车站的南侧，仅10分钟的路程。毗邻沈阳最大的商业街----太原街，街内拥有沈阳新世界百货，中兴商厦，千盛百货，联营公司，沃尔玛超市等大型购物中心。店内房型多样，拥有标准间、大床房、豪华大床房、家庭房、商务套房。酒店提供：24小时热水淋浴、免费瓶装矿泉水两瓶、挂式空调、壁挂式液晶电视、光纤网络、院内停车场、中西自助式早餐、大堂便捷式电脑查询、多功能会议室等。', 
8, 1, '和平区砂川街33号(农机大市场对面)', 
'大床房，标准房', 
'024-62119699', 189, 'G:/CityInfoPictures/15.jpg', '0');
INSERT INTO user_rating_list values(18, 8, '不错的经济连锁酒店，价格又不高…
干净，给人的感觉，很踏实…
周围商区比较少，没啥特别的…
安静，嗯，反正整体还不错…', 15, 3, '0');


INSERT INTO location values(16, '三好街63号诚大数码国际广场', 'a09', 123.43804, 41.771547);
INSERT INTO place_information values(16, '华臣影城(三好街店)', 'C02', 16, 
'环境好，人少，拿票免费停3小时车，放映效果好，总体不错，看3D有时候需要加钱', 
8, 1, '三好街63号诚大数码国际广场5楼(KFC北面)', 
'传统电影院', 
'024-28208666', 22, 'G:/CityInfoPictures/16.jpg', '0');
INSERT INTO user_rating_list values(19, 8, '团购去过一次，离我家有点远。在三好街那边。影院一般，没什么出彩的地方，也没什么不好的，可以。', 16, 3, '0');

INSERT INTO location values(17, '南三好街19号甲', 'a09', 123.4347,41.766658);
INSERT INTO place_information values(17, '广乐宫(三好街店)', 'F03', 17, 
'环境和服务都不错，火锅的味道也很好，金牌酱料（调好的麻酱球）很经典', 
10, 1, '和平区 南三好街19号甲沈阳电台东侧(近文体路)', 
'金牌酱料 手切鲜羊肉 虾滑 酸菜 酸梅汤 炭火锅 冻豆腐 手切鲜牛肉 羊肉串 麻酱烧饼 一品羊肉 新民血肠 烤羊肉串 海蛎子 手切羊肋 冬瓜 手切羊肉 烧饼 蘑菇 芝麻饼 基围虾 没有 油麦菜 海鲜 火锅', 
'024-23921888', 59, 'G:/CityInfoPictures/17.jpg', '0');
INSERT INTO user_rating_list values(20, 10, '吃过很多次了，没什么太多好说的，怀念炭火锅味道的朋友可以来吃吃。团购还是很合适的，东西挺多。这里环境没有以前好了，不过也算可以了，物美价廉。', 17, 1, '0');

INSERT INTO location values(18, '三好街华强广场D座', 'a09', 123.433547, 41.767902);
INSERT INTO place_information values(18, '玩美假期', 'H08', 18, 
'旅行社，环境好，项目多，适合聚会游戏', 
6, 1, '三好街华强广场D座1206室', 
'草莓采摘 大石湖的枫叶', 
'024-31585880', 69, 'G:/CityInfoPictures/18.jpg', '0');
INSERT INTO user_rating_list values(21, 6, '导游很好，很风趣，自助很差，但是便宜能有自助也很划算了，要知足常乐。', 18, 3, '0');

INSERT INTO location values(19, '望湖路南湖公园', 'a09', 123.41946, 41.776719);
INSERT INTO place_information values(19, '南湖公园', 'C10', 19, 
' 南湖公园，是以绿地，园林，花草，小桥，湖水，为一体的园林式公园，景观有 邻芳园、绮芳园、群芳园、鱼跃荷香园……
    园内有儿童游乐场，美食一条街，假山，雕塑，人造瀑布……
    游乐场设施比较全，可供儿童玩耍项目比较多，如 阿凡提太空城、欢乐水母、蓝色星球、欢乐旅游、弹跳机、花果山漂流……
    摩天轮最为雄伟、刺激，在园内任一角都可以看见其高大滴身影…
    高云淡风清，南湖遍地绿，湖畔走千米，春光无限美。
    假日的南湖，友人如织，三口之家，热恋情侣，夕阳之恋，祖孙三代……络绎不绝的游客，手拿单反的拍客，来自他乡的旅客… ', 
10, 1, '望湖路(近东北大学)', 
'公园 花展 游乐场', 
'13591645671', 37, 'G:/CityInfoPictures/19.jpg', '0');
INSERT INTO user_rating_list values(22, 10, '周末，节假日都是小朋友去玩，游乐设施很多，很好玩，还可以讲价，周一至周五都是父母帮孩子找对象的多，环境好，没有门票，是沈阳比较不错的公园', 19, 2, '0');

INSERT INTO location values(20, '青年大街288号', 'a09', 123.44202, 41.780747);
INSERT INTO place_information values(20, '六千馆', 'A13', 20, 
'适合口味清淡养生类型的一家杭帮菜', 
8, 1, '青年大街288号万象城B1楼B135号(近辽宁工业展览馆)', 
'骨头煲 桂花小圆子 东坡肉 松鼠鱼 千张骨头煲 绿茶饼 骨头汤 蓝莓淮山 鲍鱼蒸土鸡蛋 干锅牛蛙 蓝莓山药 麻酱油麦菜 古法豆腐 神户猪手 蟹黄豆腐 非主流水煮鱼 柠檬茶 香茅豆腐 琥珀番茄 糯米藕 鸡蛋煎饺 鲍鱼蒸蛋 爆炒牛肚 云吞鲈鱼 锅贴豆腐', 
'024-23986000', 62, 'G:/CityInfoPictures/20.jpg', '0');
INSERT INTO user_rating_list values(23, 8, '一个人去刷的骨头煲 很不错 还点了些涮的青菜 就是茶位费4元之后还要加收调料5元 有点坑 人均立马过百', 20, 2, '0');

INSERT INTO location values(21, '三好街63号诚大数码国际广场', 'a09', 123.436122, 41.771299);
INSERT INTO place_information values(21, 'FINDME真人密室', 'C13', 21, 
'沈阳最大密室逃脱', 
10, 1, '诚大数码广场5F-018(商场五楼中心区)', 
'密室逃脱', 
'024-62775266', 50, 'G:/CityInfoPictures/21.jpg', '0');
INSERT INTO user_rating_list values(24, 10, '很好玩的地方，以前对密室都没有什么概念 但是去了以后发现真的很有意思 各种谜题在面前环环相扣 很遗憾最后没有出去 下次再来挑战', 21, 3, '0');

INSERT INTO location values(22, '南三好街95号', 'a09', 123.432848, 41.764916);
INSERT INTO place_information values(22, '阿瓦山寨(三好街店)', 'A14', 22, 
'喜欢吃这个味道，鱼头里加面很不错，鱼炖的时间越久味道越好。整体环境好', 
8, 1, '南三好街95号(近大二环)', 
'剁椒鱼头 面片 山寨鱼头王 丰收茶树菇 鱼头 铁板土豆 鱼头拌面 萝卜干拌花生米 蟹黄豆花 豆花 剁椒鱼头和面 山寨鱼头王 干过土豆片 炝炒莲白 木瓜紫薯泥 干锅千叶豆腐 拌木瓜丝 串烧辣牛蛙 剁椒鱼头拌面 厥菜粉 拌面 有机胖头鱼 万绿从中一点肉 土家迷你鸭 爽口裙带菜', 
'024-23898833', 54 , 'G:/CityInfoPictures/22.jpg', '0');
INSERT INTO user_rating_list values(25, 8, '剁椒鱼头味道比较纯正，沈阳这几个阿瓦山寨味道都还不错，可惜位置都有点偏。', 22, 3, '0');

INSERT INTO location values(22, '南三好街95号', 'a09', 123.432848, 41.764916);
INSERT INTO place_information values(22, '阿瓦山寨(三好街店)', 'A14', 22, 
'喜欢吃这个味道，鱼头里加面很不错，鱼炖的时间越久味道越好。整体环境好', 
8, 1, '南三好街95号(近大二环)', 
'剁椒鱼头 面片 山寨鱼头王 丰收茶树菇 鱼头 铁板土豆 鱼头拌面 萝卜干拌花生米 蟹黄豆花 豆花 剁椒鱼头和面 山寨鱼头王 干过土豆片 炝炒莲白 木瓜紫薯泥 干锅千叶豆腐 拌木瓜丝 串烧辣牛蛙 剁椒鱼头拌面 厥菜粉 拌面 有机胖头鱼 万绿从中一点肉 土家迷你鸭 爽口裙带菜', 
'024-23898833', 54 , 'G:/CityInfoPictures/22.jpg', '0');
INSERT INTO user_rating_list values(25, 8, '剁椒鱼头味道比较纯正，沈阳这几个阿瓦山寨味道都还不错，可惜位置都有点偏。', 22, 3, '0');

INSERT INTO location values(23, '三好街文萃路4号新诚大数码国际广场', 'a09', 123.436634, 41.771285);
INSERT INTO place_information values(23, '金汉斯(三好街店)', 'A04', 23, 
'适合聚会，比其他店便宜，吃完还可以周边唱唱歌，看看电影，购购物，玩一玩什么的，方便', 
6, 1, '三好街文萃路4号新诚大数码国际广场5楼(近五里河街)', 
'蜜汁梅肉 鸡翅 烤鸡翅 烤羊腿 培根卷 烤鸡心 烤虾 香肠 牛舌 披萨 大虾 烤肉 啤酒 冰淇淋 奶茶 巴蜀肥牛 梅肉 烟熏五花肉 烤鸡腿 爆米花 冰激凌 蜀香肥牛 各种糕点 柳橙汁', 
'024-83912868', 41, 'G:/CityInfoPictures/23.jpg', '0');
INSERT INTO user_rating_list values(26, 6, '刚开始还可以，后来就腻了，而且感觉全是一个味道。黑啤感觉就是啤酒加咖啡。爆米花不错，他家的奶油蛋糕是老实那种甜腻腻的.不会再去了。', 23, 3, '0');

INSERT INTO location values(24, '南五马路252-3号', 'a09', 123.42103, 41.781641);
INSERT INTO place_information values(24, '韵夏咖啡馆(南五马路店)', 'C03', 24, 
'环境清新，幽静，咖啡好喝，可以作为谈事的地方', 
6, 1, '南五马路252-3号(近城中花园南门)', 
'黑椒牛排 咖啡 黑胡椒牛排 果茶 烤三文鱼沙拉 比萨 水果沙拉 黑森林 杏鲍菇炒虾球 光喝过咖啡 各种饮品 批萨饼 玫瑰丽人茶 珍珠奶茶 葡砾干红', 
'024-23293185', 54, 'G:/CityInfoPictures/24.jpg', '0');
INSERT INTO user_rating_list values(27, 6, '还好啦，就是屋子里面味道大了点，服务一般，以后不会再去了吧，就是离得比较近吧。', 24, 2, '0');

INSERT INTO location values(25, '文萃路8号', 'a09', 123.438499, 41.773767);
INSERT INTO place_information values(25, '面包味道(文萃店)', 'A08', 25, 
'非常好吃的蛋糕，奶油不错，不腻人', 
6, 1, '文萃路8号(近五里河街)', 
'椰蓉吐司 瑞士黑裸麦核桃包 高钙蔓越莓切片 柠檬芝士 吞拿鱼三文治 面包味道 味道皇后 醇熟 蜂糖鲜奶角 法式巧克力 大理石芝士 欧克全麦 混搭切片 法式千层 牛乳拿铁小点 台式焦糖菠萝包 轻乳酪 蛋卷 古典巧克力 肉松卷 红豆地带 酒香提子包', 
'024-23881060', 17, 'G:/CityInfoPictures/25.jpg', '0');
INSERT INTO user_rating_list values(28, 6, '团了一个面包，当早餐吃还不错。不过比较小块。没惊喜没差错的感觉。现在的面包都比较迷你了。', 25, 2, '0');

INSERT INTO location values(26, '和平区文安路5号', 'a09', 123.442142, 41.761264);
INSERT INTO place_information values(26, '弘记茶餐厅', 'A12', 26, 
'环境很好 味道正中价钱公道 服务也OK 尤其港式烧腊特别好吃', 
8, 1, '和平区文安路5号(万豪酒店后身150米)', 
'烧鹅 鲜虾云吞面 煲仔饭 上海生煎包 云吞面 灌汤小笼包 虾饺皇 双拼煲仔饭 海鲜粥 烧味拼盘 虾酱有机菜花 奶茶 馄饨面 蜜汁叉烧 冰火菠萝油 奶香画卷 油甘鱼 法国鹅肝 炭烧牛小排 铁板煎大连鲍 银血鱼 香薰乳鸽 鲍汁扣50头刺参', 
'024-88663388 ', 136, 'G:/CityInfoPictures/26.jpg', '0');
INSERT INTO user_rating_list values(29, 8, '这里算是沈城不错的茶餐厅了，口味和价位步调一致。在单位附近，经常光顾，服务不错。几个推荐菜还是比较地道。', 26, 1, '0');

INSERT INTO location values(27, '文安路42号', 'a09', 123.4381, 41.76152);
INSERT INTO place_information values(27, '大阪府日本料理', 'A09', 27, 
'推荐菜： 牛肉 鹅肝 菌菇 三文鱼寿司
餐厅氛围： 朋友聚餐 家庭聚会 商务宴请', 
6, 1, '文安路42号(近万豪饭店)', 
'鹅肝 生鱼刺身 牛肉火锅 辣白菜 树莓汁 北极贝 夏威夷贝 海带焗活鲍鱼 雪花牛肉粒 法国鹅肝 海鲜锅 土豆沙拉 澳洲小牛排 深海大明虾 三文鱼寿司 可以吃骨头的鱼 水果沙拉 海胆 牛肉 牛肉饭 生鱼船 神户牛 神户牛肉 菌菇', 
'024-23890676', 136, 'G:/CityInfoPictures/27.jpg', '0');
INSERT INTO user_rating_list values(30, 8, '装修比较有感觉，但是有些陈旧了，服务还算可以，上菜速度有些慢。', 27, 2, '0');

INSERT INTO location values(28, '文体路7号世茂百货商厦', 'a09', 123.437696,41.76606);
INSERT INTO place_information values(28, '世茂百货', 'B01', 28, 
'环境非常好,还有免费停车,楼顶的耀莱很红火,1楼的roadmate是沈阳最大的,', 
6, 1, '文体路7号世茂百货商厦(近五里河体育场)', 
'大型商场', 
'024-83890888', 230, 'G:/CityInfoPictures/28.jpg', '0');
INSERT INTO user_rating_list values(31, 6, '去了几次，各方面都挺好的。就是人少。也不知道是什么原因。估计过不了多长时间就得停业的。希望商场老板整改一下。这么大的商场黄了，可惜了。', 28, 1, '0');

