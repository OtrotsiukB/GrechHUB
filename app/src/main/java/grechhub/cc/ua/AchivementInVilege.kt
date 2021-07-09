package grechhub.cc.ua

import android.location.Location
import grechhub.cc.ua.data.Achievement


object AchivementInVilege {

    fun checkAchievements(locationtemp: Location):List<Achievement>
    {
        val listAchievement: MutableList<Achievement> = mutableListOf()

        listAchievement.addAll(checkGrechPody(locationtemp))
        return listAchievement.toList()
    }

    fun checkGrechPody(locationtemp: Location):List<Achievement>
    {
        val info =Achievement(
            null,
            "Гречані Поди",
            R.drawable.achivement_villege,
            R.drawable.villege_grechpodu,
            "Найстарішими поселеннями в сільській раді є села Свистуново та Мар'їнфельд, або Підгорне. Мар'їнфельд в перекладі з німецького Мар'їна земля, було німецьким поселенням, воно поступово зникло, а жителі переселились в с. Свистунові. Перші хати в селі Свистуново за однією з версій були збудовані в 1894 році німецькими поселенцями. Нащадки цих переселенців заснували в 1915 році нове поселення - село Роза Люксембург (Гречані Поди).\nТут же розташована і центральна садиба колективного сільськогосподарського підприємства «Калинівський».(1997р)\nГордістю КСП «Калинівський» є конеферма. Багато зусиль для відродження конярства доклав Микола Іванович Якимюк. Тепер радгосп має право брати участь у виставках, аукціонах, продавати коней.У 1951 році завезли 2 кобили угорсько-пруської породи з криничанського району і почалася підготовка до початку племінної справи.\nЯкщо згадати нашу історію аж до часів Січі, то на користь калинівцям буде твердження,   що   наші   предки,   легендарні   козаки-запорожці, вважалися неперевершеними майстрами верхової їзди і вважали коня мало не святим створінням.\nКрім української верхової породи, є англійська, орловська, торійська. Один з ентузіастів конярства Олександр Дзятко похвалився, що за жеребця Хусейна на всеукраїнській сільгоспвиставці в м. Дніпропетровську пропону¬вали автомобіль «Вольво». Жеребець Цілиноград брав участь у змаганнях різних видів в СНГ (Москва, Дніпропетровськ).\n(Джерело: Широківшина В.Ганенко, Л.Бай) "

        )

        val listAchievement: MutableList<Achievement> = mutableListOf()
        val latitudeUpLeft =47.792067;    val longlatitudeUpLeft =33.454806
        val latitudeDownLeft =47.779345;  val longlatitudeDownLeft =33.471086
        if (locationtemp.latitude<latitudeUpLeft/*верхний левый*/){
            if (locationtemp.latitude>latitudeDownLeft/*нижний правый*/){
                if (locationtemp.longitude>longlatitudeUpLeft/*верхний левый*/){
                    if (locationtemp.longitude<longlatitudeDownLeft/*нижний левый*/){
                        return listOf(info)
                    }
                }
            }
        }

        return listAchievement.toList()
    }
}