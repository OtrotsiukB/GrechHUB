package grechhub.cc.ua.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "achievementstable")
data class Achievement( @PrimaryKey(autoGenerate = true)
                        var id:Long?=null,
                        val nameAchivement:String,
                        val image:Int,
                        val imageDescriptoin: Int,
                        val descriptoin: String)
