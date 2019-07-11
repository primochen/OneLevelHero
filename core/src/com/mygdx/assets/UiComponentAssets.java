package com.mygdx.assets;

import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeBitmapFontData;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.model.jsonModel.StringFile;

public class UiComponentAssets implements FileAssetsInitializable {
	private Skin skin;
	private TextureRegionDrawable[] chatButton;
	private TextureRegionDrawable eventButton;
	private Image chatLineImage;
	private Texture splash, stayButton;
	private Image logo;
	private BitmapFont font;

	public void set(Map<String, StringFile> filePathMap) {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("freetypefonts/NotoSansCJKtc-Medium.otf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.characters = StaticAssets.Usedchars;//FreeTypeFontGenerator.DEFAULT_CHARS + "ㅏㅑㅓㅕㅗㅛㅜㅠㅡㅣㄱ가갸거겨고교구규그기ㄴ나냐너녀노뇨누뉴느니ㄷ다댜더뎌도됴두듀드디ㄹ라랴러려로료루류르리ㅁ마먀머며모묘무뮤므미ㅂ바뱌버벼보뵤부뷰브비ㅅ사샤서셔소쇼수슈스시ㅇ아야어여오요우유으이ㅈ자쟈저져조죠주쥬즈지ㅊ차챠처쳐초쵸추츄츠치ㅋ카캬커켜코쿄쿠큐크키ㅌ타탸터텨토툐투튜트티ㅍ파퍄퍼펴포표푸퓨프피ㅎ하햐허혀호효후휴흐히ㄲ까꺄꺼껴꼬꾜꾸뀨끄끼ㄸ따땨떠뗘또뚀뚜뜌뜨띠ㅃ빠뺘뻐뼈뽀뾰뿌쀼쁘삐ㅆ싸쌰써쎠쏘쑈쑤쓔쓰씨ㅉ짜쨔쩌쪄쪼쬬쭈쮸쯔찌ㅐㅒㅔㅖㅘㅙㅚㅝㅞㅟㅢ개걔게계과괘괴궈궤귀긔내냬네녜놔놰뇌눠눼뉘늬대댸데뎨돠돼되둬뒈뒤듸래럐레례롸뢔뢰뤄뤠뤼릐매먜메몌뫄뫠뫼뭐뭬뮈믜배뱨베볘봐봬뵈붜붸뷔븨새섀세셰솨쇄쇠숴쉐쉬싀애얘에예와왜외워웨위의재쟤제졔좌좨죄줘줴쥐즤채챼체쳬촤쵀최춰췌취츼캐컈케켸콰쾌쾨쿼퀘퀴킈태턔테톄톼퇘퇴퉈퉤튀틔패퍠페폐퐈퐤푀풔풰퓌픠해햬헤혜화홰회훠훼휘희깨꺠께꼐꽈꽤꾀꿔꿰뀌끠때떄떼뗴똬뙈뙤뚸뛔뛰띄빼뺴뻬뼤뽜뽸뾔뿨쀄쀠쁴쌔썌쎄쎼쏴쐐쐬쒀쒜쒸씌째쨰쩨쪠쫘쫴쬐쭤쮀쮜쯰각간갇갈감갑강낙난낟날남납낭닥단닫달담답당락란랃랄람랍랑막만맏말맘맙망박반받발밤밥방삭산삳살삼삽상악안앋알암압앙작잔잗잘잠잡장착찬찯찰참찹창칵칸칻칼캄캅캉탁탄탇탈탐탑탕팍판팓팔팜팝팡학한핟할함합항" +"我";
		parameter.size = 30;//StaticAssets.Usedchars;//
		//FreeTypeBitmapFontData fontData = generator.generateData(parameter);
		font = generator.generateFont(parameter);//new BitmapFont(fontData, fontData.getTextureRegion(), false);
		//font = new BitmapFont(Gdx.files.internal("skin/hangeul2.fnt"));
		TextureAtlas textureAtlas = new TextureAtlas("skin/chatbutton.pack");
		chatButton = new TextureRegionDrawable[6];
		for (int i = 0; i < 6; i++) {
			chatButton[i] = new TextureRegionDrawable(textureAtlas.findRegion("chatbutton" + (i + 1)));
		}
		setStayButton(new Texture(Gdx.files.internal("texture/ui/dungeon_entrance/stay_button_stay.png")));
		//skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		skin = new Skin();
		skin.add("default-fontX", font, BitmapFont.class);
		FileHandle fileHandle = Gdx.files.internal("skin/uiskin.json");
		//FileHandle atlasFile = fileHandle.sibling("skin/uiskin.atlas");
		FileHandle atlasFile = Gdx.files.internal("skin/uiskin.atlas");
		if (atlasFile.exists()) {
			skin.addRegions(new TextureAtlas(atlasFile));
		}
		skin.load(fileHandle);
		splash = new Texture(Gdx.files.internal("texture/splash.png"));
		setEventButton(new TextureRegionDrawable(new TextureRegion(new Texture(
				Gdx.files.internal("texture/ui/chat/talk_select.png")))));
		setScriptButton(new Image(new Texture(Gdx.files.internal("texture/ui/chat/talkui_window.png"))));
	}
	public Skin getSkin() {
		return skin;
	}

	public void setSkin(Skin skin) {
		this.skin = skin;
	}

	public TextureRegionDrawable[] getChatButton() {
		return chatButton;
	}

	public void setChatButton(TextureRegionDrawable[] chatButton) {
		this.chatButton = chatButton;
	}

	public Texture getSplash() {
		return splash;
	}

	public void setSplash(Texture splash) {
		this.splash = splash;
	}

	public Image getLogo() {
		return logo;
	}

	public void setLogo(Image logo) {
		this.logo = logo;
	}

	public BitmapFont getFont() {
		return font;
	}

	public void setFont(BitmapFont font) {
		this.font = font;
	}

	public TextureRegionDrawable getEventButton() {
		return eventButton;
	}

	public void setEventButton(TextureRegionDrawable eventButton) {
		this.eventButton = eventButton;
	}

	public Image getChatLineImage() {
		return chatLineImage;
	}

	public void setScriptButton(Image image) {
		this.chatLineImage = image;
	}
	public Texture getStayButton() {
		return stayButton;
	}
	public void setStayButton(Texture stayButton) {
		this.stayButton = stayButton;
	}
}
