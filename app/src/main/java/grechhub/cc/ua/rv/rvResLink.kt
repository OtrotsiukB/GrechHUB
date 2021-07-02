package grechhub.cc.ua.rv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import grechhub.cc.ua.R
import grechhub.cc.ua.data.ResLink

class rvResLink(private val listener: OnItemClickListener): RecyclerView.Adapter<rvResLink.EmptyViewHolder>() {


    private var data = listOf<ResLink>()

    interface OnItemClickListener {
        fun onItemClick(data: ResLink)
    }
    fun setData(data: List<ResLink>) {
        this.data = data
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmptyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.holder_reslink,
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

        val icon = itemView.findViewById<ImageView>(R.id.i_resurs)
        val textTitle = itemView.findViewById<TextView>(R.id.tv_title)
        val textDescription = itemView.findViewById<TextView>(R.id.tv_description)
        fun onBind(data: ResLink, listener: OnItemClickListener) {

            textTitle.text=data.title
            textDescription.text=data.description
            icon.setImageResource(data.image)

            itemView.setOnClickListener { listener.onItemClick(data) }

        }
    }
}