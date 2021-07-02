package grechhub.cc.ua.data

import grechhub.cc.ua.R

object dataCategoryMain {
    fun getCategory():List<MainCategorry>{
        return listOf<MainCategorry>(
            MainCategorry(image =  R.drawable.achivka, nameCategory =  "Ачівки"),
            MainCategorry(image =  R.drawable.mobile_map_gps_position, nameCategory =  "Знайти ачівку!"),
            MainCategorry(image =  R.drawable.facebooklogo, nameCategory =  "Наші ресурси")
        )
    }

}