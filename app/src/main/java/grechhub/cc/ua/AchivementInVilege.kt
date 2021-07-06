package grechhub.cc.ua

import android.location.Location
import grechhub.cc.ua.data.Achievement
import grechhub.cc.ua.data.ResLink

object AchivementInVilege {

    fun checkAchievements(locationtemp: Location):List<Achievement>
    {
        var listAchievement: MutableList<Achievement> = mutableListOf()

        listAchievement.addAll(checkGrechPody(locationtemp))
        return listAchievement.toList()
    }

    fun checkGrechPody(locationtemp: Location):List<Achievement>
    {
        var listAchievement: MutableList<Achievement> = mutableListOf()
        if (locationtemp.latitude<47.792067/*верхний левый*/){
            if (locationtemp.latitude>47.779345/*нижний правый*/){
                if (locationtemp.longitude>33.454806/*верхний левый*/){
                    if (locationtemp.longitude<33.471086/*нижний левый*/){
                        return listOf<Achievement>(Achievement(
                            "Гречані Поди",
                            R.drawable.res_sputnic_step_red,
                            R.drawable.res_sputnic_step_green,
                            "Гречані Поди головне село громади"

                        ))
                    }
                }
            }
        }

        return listAchievement.toList()
    }
}