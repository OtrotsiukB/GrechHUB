package grechhub.cc.ua

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import grechhub.cc.ua.data.ResLink
import grechhub.cc.ua.data.dataResLink
import grechhub.cc.ua.databinding.FragmentResLinkBinding
import grechhub.cc.ua.rv.rvResLink

class
resLinkFragment : Fragment(),rvResLink.OnItemClickListener {
    private var _binding: FragmentResLinkBinding?=null
    private val binding get() = _binding!!
    private val categorryAdapter: rvResLink = rvResLink(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding= FragmentResLinkBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)
        binding.rvResurs.layoutManager = mLayoutManager
        categorryAdapter.setData(dataResLink.getResLInk())
        binding.rvResurs.adapter=categorryAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    override fun onItemClick(data: ResLink) {
        AlertDialog.Builder(activity)
            .setTitle("Увага")
            .setMessage("Перейти до сторінки: "+data.title+"?")
            .setPositiveButton("Звичайно!") { _, _ ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(data.link))
                activity?.startActivity(intent)
            }
            .setNegativeButton("Ні") { _, _ ->
                //Toast.makeText(context, "you called cancel", Toast.LENGTH_SHORT).show()
            }
            .show()
    }

}