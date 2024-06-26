package com.example.musicwiki

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val db = MainDB.getDB(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }, 2000)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        fillRoom(db)
    }


    private fun makeFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        supportActionBar?.hide()
    }

    fun fillRoom(db: MainDB) {
        val sharedPref = getSharedPreferences("MyPrefs", MODE_PRIVATE)

        val imageResourceNameWings = sharedPref.getString("image_resource_name", "wingsnautilus")
        val imageResourceIdWings = getResources().getIdentifier(imageResourceNameWings, "drawable", packageName)
        val audioResourceNameThirst = sharedPref.getString("audio_resource_name", "nautilus_thirst")
        val audioResourceThirst = getResources().getIdentifier(audioResourceNameThirst, "raw", packageName)
        val trackThirst = trackTB(null,
            "Жажда",
            "Nautilus Pompilius",
            "Крылья",
            "3:07",
            "В последнем месяце лета я встретил тебя\n" +
                    "В последнем месяце лета ты стала моей\n" +
                    "В последнем месяце лета речная вода\n" +
                    "Ещё хранила тепло июльских дождей\n\n" +
                    "И мы вошли в эту воду однажды\n" +
                    "В которую нельзя войти дважды\n" +
                    "С тех пор я бьюсь тысячи лет\n" +
                    "Но не смог утолить этой жажды\n\n" +
                    "Первая любовь была слепа\n" +
                    "Первая любовь была, как зверь\n" +
                    "Ломала свои хрупкие кости\n" +
                    "Когда ломилась с дуру в открытую дверь\n\n" +
                    "И мы вошли в эту воду однажды\n" +
                    "В которую нельзя войти дважды\n" +
                    "С тех пор я бьюсь тысячи лет\n" +
                    "Но не смог утолить этой жажды\n\n" +
                    "В последнем месяце мы распрощались с тобой\n" +
                    "В последнем месяце мы не сумели простить\n" +
                    "В последнем месяце лета жестокие дети\n" +
                    "Умеют влюбляться, не умеют любить\n\n" +
                    "И мы вошли в эту воду однажды\n" +
                    "В которую нельзя войти дважды\n" +
                    "С тех пор я бьюсь тысячи лет\n" +
                    "Но не смог утолить этой жажды\n\n" +
                    "И мы вошли в эту воду однажды\n" +
                    "В которую нельзя войти дважды\n" +
                    "С тех пор я бьюсь тысячи лет\n" +
                    "Но не смог утолить этой жажды",
            imageResourceIdWings,
            audioResourceThirst,
            false
        )

        val imageResourceNameTPOS = sharedPref.getString("image_resource_name", "tposnautilus")
        val imageResourceIdTPOS = getResources().getIdentifier(imageResourceNameTPOS, "drawable", packageName)
        val audioResourceNameShackled = sharedPref.getString("audio_resource_name", "nautilus_shackled")
        val audioResourceShackled = getResources().getIdentifier(audioResourceNameShackled, "raw", packageName)
        val trackShackled = trackTB(null,
            "Скованные одной цепью",
            "Nautilus Pompilius",
            "Князь тишины",
            "4:17",
            "Круговая порука мажет как копоть\n" +
                    "Я беру чью-то руку, а чувствую локоть\n" +
                    "Я ищу глаза, а чувствую взгляд\n" +
                    "Где выше голов находится зад\n" +
                    "За красным восходом розовый закат\n" +
                    "\n" +
                    "Скованные одной цепью\n" +
                    "Связанные одной целью\n" +
                    "Скованные одной цепью\n" +
                    "Связанные одной\n" +
                    "\n" +
                    "Здесь суставы вялы, а пространства огромны\n" +
                    "Здесь составы смяли, чтобы сделать колонны\n" +
                    "Одни слова для кухонь, другие для улиц\n" +
                    "Здесь сброшены орлы ради бройлерных куриц\n" +
                    "И я держу равнение, даже целуясь\n" +
                    "\n" +
                    "На скованных одной цепью\n" +
                    "Связанных одной целью\n" +
                    "Скованных одной цепью\n" +
                    "Связанных одной целью\n" +
                    "\n" +
                    "Можно верить и в отсутствие веры\n" +
                    "Можно делать и в отсутствие дела\n" +
                    "Нищие молятся, молятся на то\n" +
                    "Что их нищета гарантирована\n" +
                    "Здесь можно играть про себя на трубе\n" +
                    "Но как не играй, всё играешь отбой\n" +
                    "И если есть те, кто приходят к тебе\n" +
                    "Найдутся и те, кто придёт за тобой\n" +
                    "\n" +
                    "Так же скованные одной цепью\n" +
                    "Связанные одной целью\n" +
                    "Скованные одной цепью\n" +
                    "Связанные одной\n" +
                    "\n" +
                    "Здесь женщины ищут\n" +
                    "Но находят лишь старость\n" +
                    "Здесь мерилом работы\n" +
                    "Считают усталость\n" +
                    "Здесь нет негодяев\n" +
                    "В кабинетах из кожи\n" +
                    "Здесь первые на последних похожи\n" +
                    "И не меньше последних\n" +
                    "Устали, быть может\n" +
                    "\n" +
                    "Быть скованными одной цепью\n" +
                    "Связанными одной целью\n" +
                    "Скованными одной цепью\n" +
                    "Связанными одной целью\n" +
                    "Скованные одной цепью\n" +
                    "Связанные одной целью\n" +
                    "Скованные одной цепью\n" +
                    "Связанные одной целью\n" +
                    "Скованные",
            imageResourceIdTPOS,
            audioResourceShackled,
            false
        )

        val audioResourceNameHacks = sharedPref.getString("audio_resource_name", "nautilus_hacks")
        val audioResourceHacks = getResources().getIdentifier(audioResourceNameHacks, "raw", packageName)
        val trackHacks = trackTB(null,
            "Шар цвета хаки",
            "Nautilus Pompilius",
            "Князь тишины",
            "2:40",
            "Был бесцветным, был безупречно чистым\n" +
                    "Был прозрачным, стал абсолютно белым\n" +
                    "Видно кто-то решил, что зима, и покрыл меня мелом\n" +
                    "\n" +
                    "Был бы белым, но всё же был бы и чистым\n" +
                    "Пусть холодным, но всё же с ясным взором\n" +
                    "Но кто-то решил, что война, и покрыл меня чёрным\n" +
                    "\n" +
                    "Я вижу цвет, но я здесь не был\n" +
                    "Я слышу цвет, я чувствую цвет\n" +
                    "Я знать не хочу всех тех, кто уже красит небо\n" +
                    "\n" +
                    "Я вижу песню вдали, но я слышу лишь\n" +
                    "\n" +
                    "Марш, марш левой!\n" +
                    "Марш, марш правой!\n" +
                    "Я не видел толпы страшней, чем толпа цвета хаки\n" +
                    "\n" +
                    "Был бы чёрным, да будь хоть самым чёртом\n" +
                    "Но кто-то главный, кто вечно рвёт в атаку\n" +
                    "Приказал наступать на лето и втоптал меня в хаки\n" +
                    "\n" +
                    "Я вижу дым, но я здесь не был\n" +
                    "Я слышу гарь, я чувствую гарь\n" +
                    "Я знать не хочу ту тварь, кто спалит это небо\n" +
                    "\n" +
                    "Я вижу песню вдали, но я слышу лишь\n" +
                    "\n" +
                    "Марш, марш левой!\n" +
                    "Марш, марш правой!\n" +
                    "Я не видел картины дурней, чем шар цвета хаки\n" +
                    "\n" +
                    "Марш, марш левой!\n" +
                    "Марш, марш правой!\n" +
                    "Марш, марш левой!\n" +
                    "Марш, марш правой!",
            imageResourceIdTPOS,
            audioResourceHacks,
            false
        )

        val imageResourceName45 = sharedPref.getString("image_resource_name", "kino_45")
        val imageResourceId45 = getResources().getIdentifier(imageResourceName45, "drawable", packageName)
        val audioResourceNameTrolleybus = sharedPref.getString("audio_resource_name", "kino_trolleybus")
        val audioResourceTrolleybus = getResources().getIdentifier(audioResourceNameTrolleybus, "raw", packageName)
        val trackTrolleybus = trackTB(null,
            "Троллейбус",
            "Кино",
            "45",
            "2:53",
            "Моё место слева, и я должен там сесть\n" +
                    "Не пойму, почему мне так холодно здесь\n" +
                    "Я не знаком с соседом, хоть мы вместе уж год\n" +
                    "И мы тонем, хотя каждый знает, где брод\n" +
                    "\n" +
                    "И каждый с надеждой глядит в потолок\n" +
                    "\n" +
                    "Троллейбуса, который идёт на восток\n" +
                    "Троллейбуса, который идёт на восток\n" +
                    "Троллейбуса, который\n" +
                    "\n" +
                    "Все люди - братья, мы - седьмая вода\n" +
                    "И мы едем, не знаю зачем и куда\n" +
                    "Мой сосед не может, он хочет уйти\n" +
                    "Но не может уйти, он не знает пути\n" +
                    "\n" +
                    "И вот мы гадаем, какой может быть прок\n" +
                    "\n" +
                    "В троллейбусе, который идёт на восток\n" +
                    "В троллейбусе, который идёт на восток\n" +
                    "В троллейбусе, который\n" +
                    "\n" +
                    "В кабине нет шофёра, но троллейбус идёт\n" +
                    "И мотор заржавел, но мы едем вперёд\n" +
                    "Мы сидим не дыша, смотрим туда\n" +
                    "Где на долю секунды показалась звезда\n" +
                    "\n" +
                    "Мы молчим, но мы знаем, нам в этом помог\n" +
                    "\n" +
                    "Троллейбус, который идёт на восток\n" +
                    "Троллейбус, который идёт на восток\n" +
                    "Троллейбус, который",
                imageResourceId45,
            audioResourceTrolleybus,
            false
            )

        val audioResourceNameNoMoney = sharedPref.getString("audio_resource_name", "kino_nomoney")
        val audioResourceNoMoney = getResources().getIdentifier(audioResourceNameNoMoney, "raw", packageName)
        val trackNoMoney = trackTB(null,
            "Время есть, а денег нет",
            "Кино",
            "45",
            "4:08",
            "Дождь идёт с утра, будет, был и есть\n" +
                    "И карман мой пуст, на часах шесть\n" +
                    "Папирос нет и огня нет\n" +
                    "И в окне знакомом не горит свет\n" +
                    "\n" +
                    "Время есть, а денег нет\n" +
                    "И в гости некуда пойти\n" +
                    "Время есть, а денег нет\n" +
                    "И в гости некуда пойти\n" +
                    "Время есть, а денег нет\n" +
                    "И в гости некуда пойти\n" +
                    "Время есть, а денег нет\n" +
                    "И в гости некуда пойти\n" +
                    "\n" +
                    "И куда-то все подевались вдруг\n" +
                    "Я попал в какой-то не такой круг\n" +
                    "Я хочу пить, я хочу есть\n" +
                    "Я хочу просто где-нибудь сесть\n" +
                    "\n" +
                    "Время есть, а денег нет\n" +
                    "И в гости некуда пойти\n" +
                    "Время есть, а денег нет\n" +
                    "И в гости некуда пойти\n" +
                    "Время есть, а денег нет\n" +
                    "И в гости некуда пойти\n" +
                    "Время есть, а денег нет\n" +
                    "И в гости некуда пойти",
            imageResourceId45,
            audioResourceNoMoney,
            false
        )

        val imageResourceNameDreams = sharedPref.getString("image_resource_name", "dreamsgrob")
        val imageResourceIdDreams = getResources().getIdentifier(imageResourceNameDreams, "drawable", packageName)
        val audioResourceNameHurricane = sharedPref.getString("audio_resource_name", "grob_hurricane")
        val audioResourceHurricane = getResources().getIdentifier(audioResourceNameHurricane, "raw", packageName)
        val trackHurricane = trackTB(null,
            "Значит, Ураган",
            "Гражданская оборона",
            "Зачем снятся сны",
            "3:51",
            "Не с кем говорить, не с кем воевать\n" +
                    "Больше некому дарить, некому играть\n" +
                    "В сонной темноте вязнет немота\n" +
                    "Значит, ураган\n" +
                    "Значит, напролом\n" +
                    "Значит, наобум\n" +
                    "Значит, кувырком\n" +
                    "Значит, как всегда\n" +
                    "В пламени брода нет\n" +
                    "\n" +
                    "Тягостная новь, душное кольцо\n" +
                    "Леденящая любовь, чудо-колесо\n" +
                    "Шапка набекрень, годы в никуда\n" +
                    "Значит, наотрез\n" +
                    "Значит, наповал\n" +
                    "Значит, карабин\n" +
                    "Значит, ураган\n" +
                    "Значит, как всегда\n" +
                    "В пламени брода нет\n" +
                    "\n" +
                    "Некого смешить, некого ругать\n" +
                    "Больше некому грешить, некого пугать\n" +
                    "Долгий апогей сорванной резьбы\n" +
                    "Значит, на огонь\n" +
                    "Значит, ураган\n" +
                    "Значит, напролом\n" +
                    "Значит, насовсем\n" +
                    "Значит, как всегда\n" +
                    "В пламени брода нет\n" +
                    "\n" +
                    "Значит, наповал\n" +
                    "Значит, напролом\n" +
                    "Значит, насовсем\n" +
                    "Значит, ураган\n" +
                    "Значит, как всегда\n" +
                    "В пламени брода нет\n" +
                    "\n" +
                    "Значит, карабин\n" +
                    "Значит, ураган\n" +
                    "Значит, наобум\n" +
                    "Значит, кувырком\n" +
                    "Значит, как всегда\n" +
                    "В пламени брода нет",
            imageResourceIdDreams,
            audioResourceHurricane,
            false
        )

        val audioResourceNameShining = sharedPref.getString("audio_resource_name", "grob_shining")
        val audioResourceShining = getResources().getIdentifier(audioResourceNameShining, "raw", packageName)
        val trackShining = trackTB(null,
            "Сияние",
            "Гражданская оборона",
            "Зачем снятся сны",
            "2:30",
            "Спят леса и селения\n" +
                    "Небеса и сомнения\n" +
                    "Но сиянье обрушится вниз\n" +
                    "Станет твоей судьбой\n" +
                    "Но сиянье обрушится вниз\n" +
                    "Станет твоей судьбой\n" +
                    "\n" +
                    "Спят планеты и яблоки\n" +
                    "Спят тревоги и радуги\n" +
                    "Но сиянье обрушится вниз\n" +
                    "Станет твоей душой\n" +
                    "Но сиянье обрушится вниз\n" +
                    "Станет твоей душой\n" +
                    "\n" +
                    "Спят зверьки и растения\n" +
                    "Небеса и сомнения\n" +
                    "Но сиянье обрушится вниз\n" +
                    "Станет твоей землёй\n" +
                    "Но сиянье обрушится вниз\n" +
                    "Станет самим тобой",
            imageResourceIdDreams,
            audioResourceShining,
            false
        )

        val audioResourceNameAutumn = sharedPref.getString("audio_resource_name", "grob_autumn")
        val audioResourceAutumn = getResources().getIdentifier(audioResourceNameAutumn, "raw", packageName)
        val trackAutumn = trackTB(null,
            "Осень",
            "Гражданская оборона",
            "Зачем снятся сны",
            "4:29",
            "Хватит веселиться, хватит горевать\n" +
                    "Можно расходиться, можно забывать\n" +
                    "Кто бы что ни сделал, кем бы кто не стал\n" +
                    "Никто не проиграл\n" +
                    "Никто не проиграл\n" +
                    "\n" +
                    "Верные пожитки на своих местах\n" +
                    "Скверные улыбки тлеют на устах\n" +
                    "Тяжким коромыслом вечная ничья\n" +
                    "Никто не проиграл\n" +
                    "Никто не проиграл\n" +
                    "\n" +
                    "На крылечке по утрам\n" +
                    "Блюдце с молоком\n" +
                    "Камешки и пeсни в пустоту\n" +
                    "\n" +
                    "Что бы я ни сеял, о чём бы я ни пел\n" +
                    "Во что бы я не верил, чего б я ни хотел\n" +
                    "Куда бы я ни падал, с кем ни воевал\n" +
                    "Никто не проиграл\n" +
                    "Никто не проиграл\n" +
                    "Никто не проиграл\n" +
                    "Никто не проиграл\n" +
                    "\n" +
                    "Под ракитовым кустом\n" +
                    "Осень круглый год\n" +
                    "Сумерки и мысли ни о чём\n" +
                    "Камешки и пeсни в пустоту\n" +
                    "Сумерки и мысли ни о чём\n" +
                    "Камушки и пeсни в пустоту",
            imageResourceIdDreams,
            audioResourceAutumn,
            false
        )

        val imageResourceNameReanimation = sharedPref.getString("image_resource_name", "reanimationgrob")
        val imageResourceIdReanimation = getResources().getIdentifier(imageResourceNameReanimation, "drawable", packageName)
        val audioResourceNamePie = sharedPref.getString("audio_resource_name", "grob_pie")
        val audioResourcePie = getResources().getIdentifier(audioResourceNamePie, "raw", packageName)
        val trackPie = trackTB(null,
            "Беспонтовый пирожок",
            "Гражданская оборона",
            "Реанимация",
            "3:11",
            "Лишь одно в моём кармане\n" +
                    "Беспонтовый пирожок\n" +
                    "Каждый из нас\n" +
                    "Беспонтовый пирожок\n" +
                    "Каждый из нас\n" +
                    "Беспонтовый пирожок\n" +
                    "\n" +
                    "Каждый день свою я сумку\n" +
                    "Свою сумку охранял\n" +
                    "Всю свою жизнь\n" +
                    "Я сумку охранял\n" +
                    "Всю свою жизнь\n" +
                    "Я сумку охранял\n" +
                    "\n" +
                    "На Оке и на Кубани\n" +
                    "Крутят всякое говно\n" +
                    "Любит народ наш\n" +
                    "Всякое говно\n" +
                    "Любит народ наш\n" +
                    "Всякое говно\n" +
                    "\n" +
                    "Ни оконцев, ни дверцов\n" +
                    "А полна жопа огурцов\n" +
                    "Наша страна\n" +
                    "Полна жопа огурцов\n" +
                    "Всякая страна\n" +
                    "Полна жопа огурцов\n" +
                    "\n" +
                    "Лишь одно в моём кармане\n" +
                    "Беспонтовый пирожок\n" +
                    "Каждый из нас\n" +
                    "Беспонтовый пирожок\n" +
                    "Каждый из нас\n" +
                    "Беспонтовый пирожок\n" +
                    "\n" +
                    "Каждый из нас\n" +
                    "Беспонтовый пирожок\n" +
                    "\n" +
                    "Каждый из нас\n" +
                    "Беспонтовый пирожок\n" +
                    "\n" +
                    "Каждый из нас\n" +
                    "Беспонтовый пирожок\n" +
                    "\n" +
                    "Каждый из нас\n" +
                    "Беспонтовый пирожок\n" +
                    "\n" +
                    "Каждый из нас\n" +
                    "Беспонтовый пирожок\n" +
                    "\n" +
                    "Каждый из нас\n" +
                    "Беспонтовый пирожок\n" +
                    "\n" +
                    "Каждый из нас\n" +
                    "Беспонтовый пирожок  ",
            imageResourceIdReanimation,
            audioResourcePie,
            false
        )

        val imageResourceNameTraitor = sharedPref.getString("image_resource_name", "auktyonutraitor")
        val imageResourceIdTraitor = getResources().getIdentifier(imageResourceNameTraitor, "drawable", packageName)
        val audioResourceNameFragments = sharedPref.getString("audio_resource_name", "auktyonfragments")
        val audioResourceFragments = getResources().getIdentifier(audioResourceNameFragments, "raw", packageName)
        val trackFragments = trackTB(null,
            "Осколки",
            "АукцЫон",
            "Как я стал предателем",
            "3:47",
            "Сон, приснилось мне\n" +
                    "Что я воюю в чужой стране\n" +
                    "Враг, неравный бой\n" +
                    "Я ранен в голову\n" +
                    "Я герой\n" +
                    "Но, вот, чувствую я\n" +
                    "Это конец\n" +
                    "Но, вот, я ухожу\n" +
                    "Осколки девичьих сердец\n" +
                    "Хрустят у меня под ногами\n\n" +
                    "Взрыв, и все в огне\n" +
                    "Я в красной шапке, я на коне\n" +
                    "Сон, во сне борьба\n" +
                    "Судьба народа - моя судьба!\n" +
                    "Но, вот, чувствую я\n" +
                    "Это конец\n" +
                    "Но, вот, я ухожу\n" +
                    "Осколки девичьих сердец\n" +
                    "Хрустят у меня под ногами\n\n" +
                    "Но, вот, чувствую я\n" +
                    "Это конец\n" +
                    "Но, вот, я ухожу\n" +
                    "Осколки девичьих сердец\n" +
                    "Хрустят у меня под ногами",
            imageResourceIdTraitor,
            audioResourceFragments,
            false
        )

        val audioResourceNameNapman = sharedPref.getString("audio_resource_name", "auktyonnapman")
        val audioResourceNapman = getResources().getIdentifier(audioResourceNameNapman, "raw", packageName)
        val trackNapman = trackTB(null,
            "Нэпман",
            "АукцЫон",
            "Как я стал предателем",
            "4:14",
            "Меня теперь не обижают\n" +
                    "Любой чужой отныне свой\n" +
                    "Меня не трогают, и знают\n" +
                    "Что я плохой, плохой, плохой\n" +
                    "Полюстрово с лапшой, живём мы хорошо\n" +
                    "А можно и ещё, я нэпман!\n" +
                    "Я нэпман! Я нэпман! Я нэпман\n\n" +
                    "Теперь в милиции эстеты\n" +
                    "Что было раньше, то прошло\n" +
                    "Теперь на рынке пистолеты\n" +
                    "По 9, 70 — кило\n" +
                    "Я тоже стал эстет — хожу я на балет\n" +
                    "Верните партбилет, я нэпман!\n" +
                    "Я нэпман! Я нэпман! Я нэпман\n\n" +
                    "Отныне будет хорошо\n" +
                    "Я смена проклятого класса\n" +
                    "И снова в моде ананасы\n" +
                    "Последний день уже прошёл\n" +
                    "Я раньше был плохиш! Теперь я — Кибальчиш\n" +
                    "Зачем мне ваш Париж? Я нэпман!\n" +
                    "Я нэпман! Я нэпман! Я нэпман\n" +
                    "Я нэпман!\n" +
                    "Я нэпман!\n" +
                    "Я нэпман!\n" +
                    "Я нэпман! Я нэпман!",
            imageResourceIdTraitor,
            audioResourceNapman,
            false
        )

        val imageResourceNameNautilus = sharedPref.getString("image_resource_name", "nautiluspompilius")
        val imageResourceIdNautilus = getResources().getIdentifier(imageResourceNameNautilus, "drawable", packageName)
        val artistNautlilus = artistTB(null,
            "Nautilus Pompilius",
            "Группа Nautilus Pompilius - одна из наиболее значимых и популярных рок-групп в истории советской и российской музыкальной культуры. Основана она была в городе Свердловске (ныне Екатеринбург) в 1982 году. Группа получила свое название от латинского названия морского животного.\n" +
                    "\n" +
                    "С первых дней своего существования Nautilus Pompilius выделялась оригинальным звучанием и уникальным стилем, который сочетал в себе элементы пост-панка, новой волны, и рока. Особенностями их музыки были мелодичность, глубокие лирические тексты и нестандартный подход к аранжировке. Группа стала известной благодаря хитам таким, как \"Прогулки по воде\", \"Шар цвета хаки\", \"Титаник\", \"Живи\", и многим другим.\n" +
                    "\n" +
                    "Nautilus Pompilius добилась большого успеха в СССР и заслужила культовый статус среди многих поклонников. Однако, после распада Советского Союза, группа столкнулась с новыми вызовами и трудностями. В 1997 году, после выпуска последнего альбома \"Наугад\", группа объявила о своем распаде.\n" +
                    "\n" +
                    "Наutilus Pompilius оставила неизгладимый след в истории отечественной музыки, и их творчество остается вдохновением для многих современных музыкантов и поклонников российского рока.",
            imageResourceIdNautilus
            )

        val imageResourceGrob = sharedPref.getString("image_resource_name", "grob")
        val imageResourceIdGrob = getResources().getIdentifier(imageResourceGrob, "drawable", packageName)
        val artistGrob = artistTB(null,
                "Гражданская оборона",
                "Гражданская Оборона, также известная как \"ГрОб\" (сокращение от \"Гражданская Оборона\"), была одной из самых значительных и влиятельных российских андеграундных рок-групп в период с конца 1980-х до начала 2000-х годов.\n" +
                        "\n" +
                        "Группа была основана в 1984 году в Омске Егором Летовым, который был идеологом, вокалистом и главным композитором коллектива. Гражданская Оборона была известна своим неприкрытым и агрессивным стилем, а также зачастую провокационными текстами, отражающими социальные и политические проблемы советского и постсоветского общества.\n" +
                        "\n" +
                        "Группа не имела официального статуса, издавала свои альбомы самостоятельно на кассетах и пластинках, часто записанных в домашних условиях. Это делало их музыку доступной для аудиозаписи и распространения даже в условиях цензуры и ограничений на альтернативную культуру.\n" +
                        "\n" +
                        "В ходе своего существования Гражданская Оборона пережила множество изменений в составе и стилистике, но оставалась одним из символов андеграундной музыкальной сцены в России. Ее влияние на российскую и постсоветскую рок-культуру трудно переоценить, она вдохновила многих музыкантов и стала легендой в мире российской рок-музыки. В 2008 году Летов скончался, но его наследие продолжает жить в его музыке и влиянии на молодые поколения музыкантов.",
            imageResourceIdGrob
            )

        val imageResourceKino = sharedPref.getString("image_resource_name", "kino")
        val imageResourceIdKino = getResources().getIdentifier(imageResourceKino, "drawable", packageName)
        val artistKino = artistTB(null,
            "Кино",
            "Группа \"Кино\" - один из ключевых символов советской и российской рок-культуры. Она была основана в 1982 году в Ленинграде Виктором Цоем, который стал иконообразной фигурой советского рока. \"Кино\" начиналось как андеграундный проект, но вскоре приобрело широкую популярность благодаря своему оригинальному звучанию и текстам, отражающим дух времени и проблемы советского общества.\n" +
                    "\n" +
                    "Первые альбомы \"Кино\", такие как \"45\", \"46\", \"47\" и \"48\", были записаны на домашних студиях и изданы на неофициальных магнитоальбомах, но вскоре стали популярными среди молодежи, несмотря на ограниченную дистрибуцию и цензурные ограничения. Звук группы сочетал в себе элементы панка, пост-панка и новой волны, а тексты Цоя часто были проникнуты лиричностью и социальной актуальностью.\n" +
                    "\n" +
                    "С увеличением популярности \"Кино\" сталкивалось с ограничениями и преследованиями со стороны советских властей. Тем не менее, группа продолжала активно выступать и записывать новую музыку, приобретая все большую популярность. В 1986 году \"Кино\" выпустило свой первый официальный альбом \"45\", который стал моментом перелома в истории рок-музыки СССР.\n" +
                    "\n" +
                    "В 1990 году \"Кино\" выпустило свой последний альбом \"Звезда по имени Солнце\", после чего в августе 1990 года Цой погиб в автомобильной аварии. Смерть Цоя привела к прекращению существования группы, но ее наследие осталось навсегда в истории российской музыки. Виктор Цой и \"Кино\" остаются легендой, вдохновляя многих музыкантов и оставляя неизгладимый след в сердцах поклонников.",
            imageResourceIdKino
        )

        val imageResourceAuktyon = sharedPref.getString("image_resource_name", "auktyon")
        val imageResourceIdAuktyon = getResources().getIdentifier(imageResourceAuktyon, "drawable", packageName)
        val artistAuktyon = artistTB(null,
            "АукцЫон",
            "Группа \"АукцЫон\" - это российская музыкальная группа, основанная в Ленинграде (ныне Санкт-Петербург) в 1978 году. Она является одной из самых известных и влиятельных альтернативных групп в России. В её музыке смешаны различные стили, такие как рок, джаз, панк и фолк, что придает ей уникальный звук и стиль.\n" +
                    "\n" +
                    "Основателем и лидером группы является музыкант и композитор Леонид Фёдоров. Его экспериментальный подход к музыке, нестандартное звучание и тексты песен сделали \"АукцЫон\" узнаваемыми и популярными в российской музыкальной среде.\n" +
                    "\n" +
                    "Группа \"АукцЫон\" выпустила множество альбомов и синглов с момента своего основания. Их творчество часто отличается необычными мелодиями, сложными аранжировками и глубокими лирическими текстами. Они известны не только в России, но и за её пределами, благодаря своим выступлениям на различных музыкальных фестивалях и концертах по всему миру.\n" +
                    "\n" +
                    "Среди наиболее известных альбомов \"АукцЫона\" можно назвать \"Птица\" (1988), \"Бодун\" (1991), \"Спутник\" (1997), \"Особое мнение\" (2002), \"Бодун - Диск 2\" (2016) и многие другие.\n" +
                    "\n" +
                    "Группа \"АукцЫон\" продолжает активно выступать и записывать новую музыку, привлекая к себе внимание своим оригинальным и неординарным творчеством.",
            imageResourceIdAuktyon
        )

        Thread{
//            db.getDao().deleteAll()
//            db.getDaoArtist().deleteAll()
            var count = db.getDao().getRowCount()
            if (count == 0) {
                db.getDao().insertTrack(trackThirst)
                db.getDao().insertTrack(trackShackled)
                db.getDao().insertTrack(trackHacks)
                db.getDao().insertTrack(trackNoMoney)
                db.getDao().insertTrack(trackTrolleybus)
                db.getDao().insertTrack(trackHurricane)
                db.getDao().insertTrack(trackShining)
                db.getDao().insertTrack(trackAutumn)
                db.getDao().insertTrack(trackPie)
                db.getDao().insertTrack(trackFragments)
                db.getDao().insertTrack(trackNapman)
            }
            count = db.getDaoArtist().getRowCount()
            if (count == 0) {
                db.getDaoArtist().insertArtist(artistNautlilus)
                db.getDaoArtist().insertArtist(artistGrob)
                db.getDaoArtist().insertArtist(artistKino)
                db.getDaoArtist().insertArtist(artistAuktyon)
            }
        }.start()
    }
}