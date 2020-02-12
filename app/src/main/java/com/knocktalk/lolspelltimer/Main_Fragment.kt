package com.knocktalk.lolspelltimer


import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_main_.view.*


class Main_Fragment : AppCompatDialogFragment() {


    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main_, container, false)
        val apiKey: String = BuildConfig.ApiKey
        val myDataset = arrayOf("Top", "mid", "Bottom", "Support", "Jungle")
        val summonerCoolDonw = mapOf(
            "barrier" to 180L,
            "boost" to 210L,
            "dot" to 180L,
            "exhaust" to 210L,
            "flash" to 300L,
            "haste" to 180L,
            "heal" to 240L,
            "mana" to 240L,
            "smite" to 40L,
            "teleport" to 360L
        )
        val beep = MediaPlayer.create(activity, R.raw.beep)
            view.minimap_switch.setOnCheckedChangeListener { _, isChecked ->
                if(isChecked){
                    beep.start()
                }
            }

        view.findmygame_switch.text = apiKey

            recyclerView = view.recyclerview
            viewManager = LinearLayoutManager(activity)
            viewAdapter = MyAdapter(myDataset,context!!,summonerCoolDonw,this)

            recyclerView.apply {
                setHasFixedSize(true)
                layoutManager = viewManager
                adapter = viewAdapter
            }
            return view
        }
    }


