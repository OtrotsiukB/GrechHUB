package grechhub.cc.ua.rv


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import grechhub.cc.ua.R
import grechhub.cc.ua.data.Achievement



class rvAchievement(private val listener: OnItemClickListener): RecyclerView.Adapter<rvAchievement.EmptyViewHolder>() {


    private var data = listOf<Achievement>()

    interface OnItemClickListener {
        fun onItemClick(data: Achievement)
    }
    fun setData(data: List<Achievement>) {
        this.data = data
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmptyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.holder_achievement,
            parent,
            false
        )
        return EmptyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: EmptyViewHolder, position: Int) {


        holder.onBind(data[position],listener)


    }

    override fun getItemCount(): Int {
        return data.count()
    }
    override fun getItemViewType(position: Int): Int {

        return 0
    }

    class EmptyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val icon = itemView.findViewById<ImageView>(R.id.i_achievement)
        val text = itemView.findViewById<TextView>(R.id.tv_nameAchievement)

        fun onBind(data: Achievement, listener: OnItemClickListener) {

            text.text=data.nameAchivement .toString()
            icon.setImageResource(data.image)

            itemView.setOnClickListener { listener.onItemClick(data) }

        }
    }
}