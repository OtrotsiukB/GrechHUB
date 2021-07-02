package grechhub.cc.ua.rv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import grechhub.cc.ua.R
import grechhub.cc.ua.data.MainCategorry


class rvListMain(private val listener: OnItemClickListener): RecyclerView.Adapter<rvListMain.EmptyViewHolder>() {


    private var data = listOf<MainCategorry>()

    interface OnItemClickListener {
        fun onItemClick(data: MainCategorry)
    }
    fun setData(data: List<MainCategorry>) {
        this.data = data
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmptyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.holder_mainlist,
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

        val icon = itemView.findViewById<ImageView>(R.id.ic_iconCategory)
        val text = itemView.findViewById<TextView>(R.id.tv_titleCategory)

        fun onBind(data: MainCategorry, listener: OnItemClickListener) {

            text.text=data.nameCategory.toString()
            icon.setImageResource(data.image)

            itemView.setOnClickListener { listener.onItemClick(data) }

        }
    }
}