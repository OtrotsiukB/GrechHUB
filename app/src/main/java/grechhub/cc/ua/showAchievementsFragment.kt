package grechhub.cc.ua

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import grechhub.cc.ua.data.Achievement
import grechhub.cc.ua.data.dataCategoryMain
import grechhub.cc.ua.databinding.FragmentMainBinding
import grechhub.cc.ua.databinding.FragmentShowAchievementsBinding
import grechhub.cc.ua.dbroom.DatabaseR
import grechhub.cc.ua.rv.rvAchievement
import grechhub.cc.ua.rv.rvListMain


class showAchievementsFragment : Fragment(),rvAchievement.OnItemClickListener {
    private var _binding: FragmentShowAchievementsBinding?=null
    private val binding get() = _binding!!
    private val categorryAdapter: rvAchievement = rvAchievement(this)

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
        _binding= FragmentShowAchievementsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val locationsDb = DatabaseR.create(view.context.applicationContext)
        var achivenmentsFromDB = locationsDb.DaoInDB().getAllAchievements()
        if(achivenmentsFromDB.size>0){
            binding.rvShowAchievements.visibility= View.VISIBLE
            binding.tvNorFound.visibility=View.GONE
            categorryAdapter.setData(achivenmentsFromDB)
            binding.rvShowAchievements.adapter=categorryAdapter
        }else{
            binding.rvShowAchievements.visibility= View.GONE
            binding.tvNorFound.visibility=View.VISIBLE
        }

    }

    override fun onItemClick(data: Achievement) {
        var bundle = bundleOf("Achievement" to data)
        findNavController().navigate(R.id.action_showAchievementsFragment_to_achievementDetallFragment,bundle)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}