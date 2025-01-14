package com.mygdx.assets;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.model.jsonModel.StringFile;
import com.mygdx.util.JsonParser;

public class StaticAssets {
	
	public static Skin skin = null;//new Skin(Gdx.files.internal("skin/uiskin.json"));//
	
	public static String Usedchars = "";
	
	public static HashSet<Character> set = new HashSet<Character>();

	// https://netjs.blogspot.com/2017/04/reading-all-files-in-folder-java-program.html
	public static void listAllFiles(File folder) {
		// System.out.println("In listAllfiles(File) method");
		File[] fileNames = folder.listFiles();
		for (File file : fileNames) {
			// if directory call the same method again
			if (file.isDirectory()) {
				listAllFiles(file);
			} else {
				readContent(file);
			}
		}
	}

	public static void readContent(File file) {
		if (file.isFile() && file.getName().endsWith(".json")) {
			try {
				Scanner sc = new Scanner(file);
				while (sc.hasNext()) {
					String str = sc.nextLine();
					char[] ch = str.toCharArray();
					for (int z = 0; ch != null && z < ch.length; z++) {
						set.add(ch[z]);
					}
					// System.out.println(str);
				}
				sc.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	static {
		if (true) {

			File folder = new File("../android/assets");
			listAllFiles(folder);

			char[] cha = FreeTypeFontGenerator.DEFAULT_CHARS.toCharArray();
			for (int z = 0; cha != null && z < cha.length; z++) {
				set.add(cha[z]);
			}

			Character[] objs = set.toArray(new Character[set.size()]);
			StringBuilder sb = new StringBuilder();
			for (Character ch : objs) {
				sb.append(ch);
			}
			com.mygdx.assets.StaticAssets.Usedchars = sb.toString();
		}
	}
	
	static{
		skin = new Skin();//Gdx.files.internal("skin/uiskin.json"));
		
		//http://badlogicgames.com/forum/viewtopic.php?f=11&t=8485&p=38863&hilit=skin+font#p38863[link]
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("freetypefonts/NotoSansCJKtc-Medium.otf"));
		//FreeTypeFontParameter parameter = com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontParameter;
		//parameter.size = 14;
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
	    parameter.size = 22;//StaticAssets.Usedchars;//
	    parameter.characters = StaticAssets.Usedchars;//FreeTypeFontGenerator.DEFAULT_CHARS + "ㅏㅑㅓㅕㅗㅛㅜㅠㅡㅣㄱ가갸거겨고교구규그기ㄴ나냐너녀노뇨누뉴느니ㄷ다댜더뎌도됴두듀드디ㄹ라랴러려로료루류르리ㅁ마먀머며모묘무뮤므미ㅂ바뱌버벼보뵤부뷰브비ㅅ사샤서셔소쇼수슈스시ㅇ아야어여오요우유으이ㅈ자쟈저져조죠주쥬즈지ㅊ차챠처쳐초쵸추츄츠치ㅋ카캬커켜코쿄쿠큐크키ㅌ타탸터텨토툐투튜트티ㅍ파퍄퍼펴포표푸퓨프피ㅎ하햐허혀호효후휴흐히ㄲ까꺄꺼껴꼬꾜꾸뀨끄끼ㄸ따땨떠뗘또뚀뚜뜌뜨띠ㅃ빠뺘뻐뼈뽀뾰뿌쀼쁘삐ㅆ싸쌰써쎠쏘쑈쑤쓔쓰씨ㅉ짜쨔쩌쪄쪼쬬쭈쮸쯔찌ㅐㅒㅔㅖㅘㅙㅚㅝㅞㅟㅢ개걔게계과괘괴궈궤귀긔내냬네녜놔놰뇌눠눼뉘늬대댸데뎨돠돼되둬뒈뒤듸래럐레례롸뢔뢰뤄뤠뤼릐매먜메몌뫄뫠뫼뭐뭬뮈믜배뱨베볘봐봬뵈붜붸뷔븨새섀세셰솨쇄쇠숴쉐쉬싀애얘에예와왜외워웨위의재쟤제졔좌좨죄줘줴쥐즤채챼체쳬촤쵀최춰췌취츼캐컈케켸콰쾌쾨쿼퀘퀴킈태턔테톄톼퇘퇴퉈퉤튀틔패퍠페폐퐈퐤푀풔풰퓌픠해햬헤혜화홰회훠훼휘희깨꺠께꼐꽈꽤꾀꿔꿰뀌끠때떄떼뗴똬뙈뙤뚸뛔뛰띄빼뺴뻬뼤뽜뽸뾔뿨쀄쀠쁴쌔썌쎄쎼쏴쐐쐬쒀쒜쒸씌째쨰쩨쪠쫘쫴쬐쭤쮀쮜쯰각간갇갈감갑강낙난낟날남납낭닥단닫달담답당락란랃랄람랍랑막만맏말맘맙망박반받발밤밥방삭산삳살삼삽상악안앋알암압앙작잔잗잘잠잡장착찬찯찰참찹창칵칸칻칼캄캅캉탁탄탇탈탐탑탕팍판팓팔팜팝팡학한핟할함합항" +"我";
	    //parameter.characters = FreeTypeFontGenerator.DEFAULT_CHARS + "我";//= "的一是在不了有和人這中大為上個國我以要他時來用們生到作地于出就分對成會可主發年動同工也能下過子說產種面而方後多定行學法所民得經十三之進著等部度家 電力裡如水化高自二理起小物現實加量都兩體制機當使點從業本去把性好應開它合還因由其些然前外天政四日那社義事平形相全表間樣與關各重新線內數正心反你明 看原又麼利比或但質氣第向道命此變條只沒結解問意建月公無系軍很情者最立代想已通並提直題黨程展五果料象員革位入常文總次品式活設及管特件長求老頭基資邊 流路級少圖山統接知較長將組見計別她手角期根論運農指幾九區強放決西被幹做必戰先回則任取據處隊南給色光門即保治北造百規熱領七海地口東導器壓志世金增爭 濟階油思術極交受聯什認六共權收證改清已美再採轉更單風切打白教速花帶安場身車例真務具萬每目至達走積示議聲報鬥完類八離華名確才科張信馬節話米整空元況 今集溫傳土許步群廣石記需段研界拉林律叫且究觀越織裝影算低持音眾書布復容兒須際商非驗連斷深難近礦千周委素技備半辦青省列習響約支般史感勞便團往酸歷市 克何除消構府稱太準精值號率族維劃選標寫存候毛親快效斯院查江型眼王按格養易置派層片始卻專狀育廠京識適屬圓包火住調滿縣局照參紅細引聽該鐵價嚴首底液官 德調隨病蘇失爾死講配女黃推顯談罪神藝呢席含企望密批營項防舉球英氧勢告李台落木幫輪破亞師圍注遠字材排供河態封另施減樹溶怎止案言士均武固葉魚波視僅費 緊愛左章早朝害續輕服試食充兵源判護司足某練差致板田降黑犯負擊範繼興似餘堅曲輸修的故城夫夠送筆船佔右財吃富春職覺漢畫功巴跟雖雜飛檢吸助昇陽互初創抗 考投壞策古徑換未跑留鋼曾端責站簡述錢副盡帝射草衝承獨令限阿宣環雙請超微讓控州良軸找否紀益依優頂礎載倒房突坐粉敵略客袁冷勝絕析塊劑測絲協重訴念陳仍 羅鹽友洋錯苦夜刑移頻逐靠混母短皮終聚汽村雲哪既距衛停烈央察燒行迅境若印洲刻括激孔搞甚室待核校散侵吧甲遊久菜味舊模湖貨損預阻毫普穩乙媽植息擴銀語揮 酒守拿序紙醫缺雨嗎針劉啊急唱誤訓願審附獲茶鮮糧斤孩脫硫肥善龍演父漸血歡械掌歌沙著剛攻謂盾討晚粒亂燃矛乎殺藥寧魯貴鐘煤讀班伯香介迫句豐培握蘭擔弦蛋 沉假穿執答樂誰順煙縮徵臉喜松腳困異免背星福買染井概慢怕磁倍祖皇促靜補評翻肉踐尼衣寬揚棉希傷操垂秋宜氫套筆督振架亮末憲慶編牛觸映雷銷詩座居抓裂胞呼 娘景威綠晶厚盟衡雞孫延危膠還屋鄉臨陸顧掉呀燈歲措束耐劇玉趙跳哥季課凱胡額款紹卷齊偉蒸殖永宗苗川爐岩弱零楊奏沿露桿探滑鎮飯濃航懷趕庫奪伊靈稅了途滅 賽歸召鼓播盤裁險康唯錄菌純藉糖蓋橫符私努堂域槍潤幅哈竟熟蟲澤腦壤碳歐遍側寨敢徹慮斜薄庭都納彈飼伸折麥濕暗荷瓦塞床築惡戶訪塔奇透梁刀旋跡卡氯遇份毒 泥退洗擺灰彩賣耗夏擇忙銅獻硬予繁圈雪函亦抽篇陣陰丁尺追堆雄迎泛爸樓避謀噸野豬旗累偏典館索秦脂潮爺豆忽托驚塑遺愈朱替纖粗傾尚痛楚謝奮購磨君池旁碎骨 監捕弟暴割貫殊釋詞亡壁頓寶午塵聞揭砲殘冬橋婦警綜招吳付浮遭徐您搖谷贊箱隔訂男吹樂園紛唐敗宋玻巨耕坦榮閉灣鍵凡駐鍋救恩剝凝鹼齒截煉麻紡禁廢盛版緩淨 睛昌婚涉筒嘴插岸朗莊街藏姑貿腐奴啦慣乘夥恢勻紗扎辯耳彪臣億璃抵脈秀薩俄網舞店噴縱寸汗掛洪著賀閃柬爆烯津稻牆軟勇像滾釐蒙芳肯坡柱盪腿儀旅尾軋冰貢登 黎削鑽勒逃障氨郭峰幣港伏軌畝畢擦莫刺浪秘援株健售股島甘泡睡童鑄湯閥休匯舍牧繞炸哲磷績朋淡尖啟陷柴呈徒顏淚稍忘泵藍拖洞授鏡辛壯鋒貧虛彎摩泰幼廷尊窗 綱弄隸疑氏宮姐震瑞怪尤琴循描膜違夾腰緣珠窮森枝竹溝催繩憶邦剩幸漿欄擁牙貯禮濾鈉紋彈罷拍咱喊袖埃勤罰焦潛伍墨欲縫姓刊飽仿獎鋁鬼麗跨默挖鏈掃喝袋炭污幕諸弧勵梅奶潔災舟鑑苯訟抱毀率懂寒智埔寄屆躍渡挑丹艱貝碰拔爹戴碼夢芽熔赤漁哭敬顆奔藏鉛熟仲虎稀妹乏珍申桌遵允隆螺倉魏銳曉氮兼隱礙赫撥忠肅缸牽搶博 巧殼兄杜訊誠碧祥柯頁巡矩悲灌齡倫票尋桂鋪聖恐恰鄭趣抬荒騰貼柔滴猛闊輛妻填撤儲簽鬧擾紫砂遞戲吊陶伐餵療瓶婆撫臂摸忍蝦蠟鄰胸鞏擠偶棄槽勁乳鄧吉仁爛磚 租烏艦伴瓜淺丙暫燥橡柳迷暖牌纖秧膽詳簧踏瓷譜呆賓糊洛輝憤競隙怒粘乃緒肩籍敏塗熙皆偵懸掘享糾醒狂鎖澱恨牲霸爬賞逆玩陵祝秒浙貌役彼悉鴨著趨鳳晨畜輩秩 卵署梯炎灘棋驅篩峽冒啥壽譯浸泉帽遲硅疆貸漏稿冠嫩脅芯牢叛蝕奧鳴嶺羊憑串塘繪酵融盆錫廟籌凍輔攝襲筋拒僚旱鉀鳥漆沈眉疏添棒穗硝韓逼扭僑涼挺碗栽炒杯患 餾勸豪遼勃鴻旦吏拜狗埋輥掩飲搬罵辭勾扣估蔣絨霧丈朵姆擬宇輯陝雕償蓄崇剪倡廳咬駛薯刷斥番賦奉佛澆漫曼扇鈣桃扶仔返俗虧腔鞋稜覆框悄叔撞騙勘旺沸孤粘吐 孟渠屈疾妙惜仰狠脹諧拋霉桑崗嘛衰盜滲臟賴湧甜曹閱肌哩厲烴緯毅昨偽症煮嘆釘搭莖籠酷偷弓錐恆傑坑鼻翼綸敘獄逮罐絡棚抑膨蔬寺驟穆冶枯冊屍凸紳坯犧焰轟欣 晉瘦禦錠錦喪旬鍛壟搜佛撲邀亭酯邁舒脆　閒憂酚頑羽漲卸仗陪薄闢懲杭姚肚捉飄漂昆欺吾郎烷汁呵飾蕭雅郵遷燕撒姻赴宴煩削債帳斑鈴旨醇董餅雛姿拌傅腹妥揉賢 拆歪葡胺丟浩徽昂墊擋覽貪慰繳汪慌馮諾姜誼兇劣誣耀昏躺盈騎喬溪叢盧抹易悶咨刮駕纜悟摘鉺擲頗幻柄惠慘佳仇臘窩滌劍瞧堡潑蔥罩霍撈胎蒼濱倆捅湘砍霞邵萄瘋 淮遂熊糞烘宿檔戈駁嫂裕徙箭捐腸撐曬辨殿蓮攤攪醬屏疫哀蔡堵沫皺暢疊閣萊敲轄鉤痕壩巷餓禍丘玄溜曰邏彭嘗卿妨艇吞韋怨矮歇郊祿捻漠粹顛宏冤肪飢呵仙押挨醛 娃拾沒佩勿嚇訛侯戀夕鋅篡戚淋蓬豈釉兆泊魂拘亡槓摧氟頌渾凌鈾誘犁譴頒舶扯嘉萌猶滋焊舌匹媳肺掠釀烹疲馳鴉窄辱狹樸遣菲姦韌辣拳稈臥醉竭茅墓矣哎艷敦輿締 雇尿葬履契禽渣襯躲賠咸溉賊醋堤抖妃褲廉晴挽掀茫醜亥攔悠闡慧佐奇豎孝櫃麟繡遙逝愁肖昭芬逢窯捷圜盲閘宙輻披賬狼幽綢蜂慎餐酬誓惟叉彌址幟芝砌欸僕濤臭翠 盒劫慨炳闔寂椒倘拓畏喉巾頸墾拚獸蔽蘆乾爽竊譚掙崩模褐傳翅儒傘晃謬胚剖湊眠濁霜礁蔑抄闖灑碑蓉耶猜蹲壺喚澳鋸郡玲綿紐梳掏籲錘鼠穴椅殷遮吵萍厭畜俱誇呂 囊捧雌閩饒瞬鬱哨鑿朝俺滸茂肝勳盯籽恥菊濫稼戒奈帥鞭蠶鎂詢跌烤壇宅笛鄂蠻顫棍睜鼎岌降侍藩嚷匪岳糟纏迪洩卑氛堪蘿盛碘縛悅澄甫攀屠溢拱晰攜朽吟菱謙凹俊 芒盼嬸艘　趁唇挫羞浴疼萎餚愚腫刨絞樞嫁慕艙鏟蘋豫諭迭潘頃翁榜匠欠茬疇胃沾蹤弊哼鵬歧桐沃悼惑潰蔗薦潭孢露診庸聰嫌廚龐祁鉗肆梭贈崖籃穎甸藻搗且撕詔貞 賜慈炕胖茲差瓊鏽汛卓棵饋撓灶嬰蒂膚衫瀝崙勉滬逸蜜浦嗓暈膏祭贏艾扮鵝憐蒲兔孕嚦蘗挪淑謠懼廊緬俘驕膀陡宰誕峻惱腺獵渦夷愉魔銨葛賈似蔭喲脊鈔苛錳橢鑲杏 溴倚滯會氓捏斬傲匆僵滷燙衍榨攏裸屑咽坊舅渴翔邪拄窖貓砌欽媒脾勺柏柵噪晝耿扁辰秤得販糕梁曇衷宦扔哇詐囑藤卜岡悔廓皂拐氰杉瑪矢寓瓣罕垮筍淘銜稱恭喇帕 桉秉簾銘蛇摔齋叭帆裸儉瘤篷砸肢闢脖瞪暑卜竿殲笙酮蘊嘩瞎喀刃楔喘枚嵌撾廂粵甩拴膝懇腕娓熄錨忌愧哦荊圃騷丸蒜毯弗俯鹿梢屯衙轎賤壘諒踢啞滔渥餉泳棕熬擱 　梨吻櫻奠捆姨柏聘惕鄆綁冀裹酥寡彥稠啡鈍汝擅汰　埔敞嘿遜棟謹咖鯉雀傭庵葫賄鱗拼搏謊塌忉膩戊怖墳禾剎嘻桔坎拇煽獅癢曾梗寇鷹燭哄莽雯胳龜亟糠泌坪傻什 喻淵蚌跪巷涅釗譬蕊膛侮奕枕辮況扼郝寥淒廈腥鈞耦蹄戥屁誦匈樁釣涵倦袍抒嶼蹈忿敷虹聊嗣尉燦糙蹬嗯姬狡笨辜僧茨諷翰枉岐棗嶄焚咕猴攬澇耍趟洶咋傍鍍給爵虜 劈璋踩瞅迄昔汞呱詭魄祺嘲惶贓癌咐歉扳鄙廬聶便芡軀貶煌擰隋襄淤寵炊滇謇懶栓佑憾駱裙猖兜孵痼盥曝泣絮韻眷曠噢參棲盞鰲濺煎校榴暮琪淆陛巢噠吼槐唧其沛乞 蜀蜇賺捍鉸冪堯咒耽叮褂煥煞雹搓釜鉻揀募淹瑰鰱茄灼鄒躬覺嬌焉彰鶴琳淪畔惹庶斃皖邢禹漬繃竄翹淫簞陌膊韃咳玫巫拂蕉瀾贖綏鋤囪賭頰縷寅躁稚庚苟氦魁珊蛻蛭酌逗閨蔓撇豌朕緝襟鎳桅熒姪卒佃瞿娶飪聳乍靶痴靖摃筐韶囂崔蓿岔氘娥剿霖喃搪雍裳撰豹駿慷";
		BitmapFont font = generator.generateFont(parameter);//generateFont(14);

//		Skin skinSet = new Skin(Gdx.files.internal("skin/uiskin.json"));//		
//		skinSet.remove("default-fontX", BitmapFont.class);
//		skinSet.find(resource)
		
		//skin.remove("default-fontX", BitmapFont.class);
		//skin = new MySkin();
		skin.add("default-font", font, BitmapFont.class);
		

		
//		skin.add("small-font", font, BitmapFont.class);
//		skin.add("smooth-font", font, BitmapFont.class);
		FileHandle fileHandle = Gdx.files.internal("skin/uiskin.json");
		//FileHandle atlasFile = fileHandle.sibling("skin/uiskin.atlas");
		FileHandle atlasFile = Gdx.files.internal("skin/uiskin.atlas");
		if (atlasFile.exists()) {
			skin.addRegions(new TextureAtlas(atlasFile));
		}
		skin.load(fileHandle);
		int a = 0;
		a = a+1;
	}
	
	//public static Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
	public static Map<String, StringFile> filePathMap;

	public static final float BASE_WINDOW_WIDTH = 1920;
	public static final float BASE_WINDOW_HEIGHT = 1080;
	public static float windowWidth;
	public static float windowHeight;
	public static float resolutionFactor;

	public static void loadAll() {
		filePathMap = JsonParser
				.parseMap(StringFile.class, Gdx.files.internal("data/load/file_path.json").readString());
		loadSize(new Stage());
		{
			Pixmap pixmap = new Pixmap(1, 22, Format.RGBA8888);
			pixmap.setColor(Color.WHITE);
			pixmap.fill();
			skin.add("WHITE", new Texture(pixmap));
		}
		
		//FileHandle f = Gdx.files.internal("freetypefonts/NotoSansCJKtc-Medium.otf");
		
		{
			Pixmap pixmap = new Pixmap(1, 22, Format.RGBA8888);
			pixmap.setColor(Color.RED);
			pixmap.fill();
			skin.add("RED", new Texture(pixmap));
		}
		{
			Pixmap pixmap = new Pixmap(1, 22, Format.RGBA8888);
			pixmap.setColor(Color.GREEN);
			pixmap.fill();
			skin.add("GREEN", new Texture(pixmap));
		}
	}

	public static void loadSize(Stage stage) {
		Viewport vp = stage.getViewport();
		windowWidth = vp.getWorldWidth();
		windowHeight = vp.getWorldHeight();
		resolutionFactor = windowWidth / BASE_WINDOW_WIDTH;
	}
}
