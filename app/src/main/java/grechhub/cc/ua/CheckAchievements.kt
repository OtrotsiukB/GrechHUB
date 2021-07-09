package grechhub.cc.ua

import android.location.Location
import grechhub.cc.ua.data.Achievement

object CheckAchievements {

    fun checkAchievements(locationtemp: Location):List<Achievement>
    {
        val listAchievement: MutableList<Achievement> = mutableListOf()
        //проверяем ачивки в список по селам
        listAchievement.addAll(AchivementInVilege.checkAchievements(locationtemp))

        return listAchievement.toList()
    }
}