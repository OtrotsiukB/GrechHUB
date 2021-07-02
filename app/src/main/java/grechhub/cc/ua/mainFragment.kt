package grechhub.cc.ua

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import grechhub.cc.ua.data.MainCategorry
import grechhub.cc.ua.data.dataCategoryMain
import grechhub.cc.ua.databinding.FragmentMainBinding
import grechhub.cc.ua.rv.rvListMain


class mainFragment : Fragment(),rvListMain.OnItemClickListener {
    private var _binding:FragmentMainBinding?=null
    private val binding get() = _binding!!
    private val categorryAdapter: rvListMain = rvListMain(this)

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
       // return inflater.inflate(R.layout.fragment_main, container, false)
        _binding= FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mLayoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL,true)
        binding.rvMainCategory.layoutManager = mLayoutManager
        categorryAdapter.setData(dataCategoryMain.getCategory())
        binding.rvMainCategory.adapter=categorryAdapter
    }

    override fun onItemClick(data: MainCategorry) {
        when(data.nameCategory){
            "Наші ресурси"      ->{ findNavController().navigate(R.id.action_mainFragment_to_resLinkFragment) }
            "Знайти ачівку!"    ->{ findNavController().navigate(R.id.action_mainFragment_to_getAchievementsFragment) }
            "Ачівки"            ->{ findNavController().navigate(R.id.action_mainFragment_to_showAchievementsFragment) }

        }
    }


}