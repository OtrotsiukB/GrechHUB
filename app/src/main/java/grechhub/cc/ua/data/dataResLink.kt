package grechhub.cc.ua.data

import grechhub.cc.ua.R

object dataResLink {
    fun getResLInk():List<ResLink>{
        return listOf<ResLink>(
            ResLink("Гречаноподівська громада",
                    "Офіційна сторінка Гречаноподівської громади у мережі Facebook",
                "https://www.facebook.com/grechotg/",
                R.drawable.res_grechotg
            )

        )
    }
}