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
            "Nautlius Pompilius",
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
            "Nautlius Pompilius",
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
            "Nautlius Pompilius",
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

        Thread{
 //           db.getDao().deleteAll()
            val count = db.getDao().getRowCount()
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
            }
        }.start()
    }
}