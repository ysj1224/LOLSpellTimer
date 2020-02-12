package com.knocktalk.lolspelltimer

import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.os.CountDownTimer
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_dialog_.view.*
import kotlinx.android.synthetic.main.fragment_main_.*
import kotlinx.android.synthetic.main.list_card.view.*


class MyAdapter(private val myDataset: Array<String>, val context: Context, val summonerCoolDonw: Map<String,Long>, val activity: Fragment) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {



    class MyViewHolder(val myView: View) : RecyclerView.ViewHolder(myView) {
        val dialogbuilder = AlertDialog.Builder(myView.context)
        val dialogview = LayoutInflater.from(myView.context).inflate(R.layout.fragment_dialog_,myView.list_layout,false)
        val dialog = dialogbuilder.setView(dialogview).setNegativeButton("cancel",
            DialogInterface.OnClickListener { dialog, _ ->
                dialog.dismiss()
            })
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_card, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int ) {

        holder.myView.linenername_text.text = myDataset[position]
        holder.myView.spell1_image.setImageResource(R.drawable.normal)
        holder.myView.spell2_image.setImageResource(R.drawable.normal)


        var activate1 = false
        var istime1 = true
        var activate2 = false
        var istime2 = true
        var time1 = holder.myView.spell1timer_text.text.toString().toLong()
        var originalTime1 = holder.myView.spell1timer_text.text.toString()
        var time2 = holder.myView.spell2timer_text.text.toString().toLong()
        var originalTime2 = holder.myView.spell2timer_text.text.toString()
        val vib_on = activity.vibrate_switch.isChecked
        fun vib() {
            if(vib_on){
                val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                if (Build.VERSION.SDK_INT >= 26) {
                    vibrator.vibrate(
                        VibrationEffect.createOneShot(
                            500,
                            VibrationEffect.DEFAULT_AMPLITUDE
                        )
                    )
                } else {
                    vibrator.vibrate(500)
                }
            }
        }

        fun timer1(time: Long, spelltext: TextView): CountDownTimer {
            return object :CountDownTimer(time * 1000, 1000){
                override fun onTick(millisUntilFinished: Long) {
                    spelltext.setText("${millisUntilFinished / 1000}")
                    if(!istime1) {
                        spelltext.setText(originalTime1)
                        cancel()
                    }
                }
                override fun onFinish() {
                    vib()
                    holder.myView.spell1_image.setColorFilter(null)
                    spelltext.setText(originalTime1)
                }
            }
        }
        fun timer2(time: Long, spelltext: TextView): CountDownTimer {
            return object :CountDownTimer(time * 1000, 1000){
                override fun onTick(millisUntilFinished: Long) {
                    spelltext.setText("${millisUntilFinished / 1000}")
                    if(!istime2) {
                        spelltext.setText(originalTime2)
                        cancel()
                    }
                }
                override fun onFinish() {
                    vib()
                    holder.myView.spell2_image.setColorFilter(null)
                    spelltext.setText(originalTime2)
                }
            }
        }

        holder.myView.spell1_image.setOnLongClickListener {
            if (holder.dialogview.parent != null) {
                val parent = holder.dialogview.parent as ViewGroup
                parent.removeView(holder.dialogview)
            }
            val dialogs = holder.dialog.show()
            if(!activate1) {
                holder.dialogview.barrier_image.setOnClickListener {
                    holder.myView.spell1_image.setImageResource(R.drawable.summoner_barrier)
                    holder.myView.spell1timer_text.text = summonerCoolDonw["barrier"].toString()
                    time1 = holder.myView.spell1timer_text.text.toString().toLong()
                    originalTime1 = holder.myView.spell1timer_text.text.toString()
                    dialogs.dismiss()
                }
                holder.dialogview.boost_image.setOnClickListener {
                    holder.myView.spell1_image.setImageResource(R.drawable.summoner_boost)
                    holder.myView.spell1timer_text.text = summonerCoolDonw["boost"].toString()
                    time1 = holder.myView.spell1timer_text.text.toString().toLong()
                    originalTime1 = holder.myView.spell1timer_text.text.toString()
                    dialogs.dismiss()
                }
                holder.dialogview.dot_image.setOnClickListener {
                    holder.myView.spell1_image.setImageResource(R.drawable.summoner_dot)
                    holder.myView.spell1timer_text.text = summonerCoolDonw["dot"].toString()
                    time1 = holder.myView.spell1timer_text.text.toString().toLong()
                    originalTime1 = holder.myView.spell1timer_text.text.toString()
                    dialogs.dismiss()
                }
                holder.dialogview.exhaust_image.setOnClickListener {
                    holder.myView.spell1_image.setImageResource(R.drawable.summoner_exhaust)
                    holder.myView.spell1timer_text.text = summonerCoolDonw["exhaust"].toString()
                    time1 = holder.myView.spell1timer_text.text.toString().toLong()
                    originalTime1 = holder.myView.spell1timer_text.text.toString()
                    dialogs.dismiss()
                }
                holder.dialogview.flash_image.setOnClickListener {
                    holder.myView.spell1_image.setImageResource(R.drawable.summoner_flash)
                    holder.myView.spell1timer_text.text = summonerCoolDonw["flash"].toString()
                    time1 = holder.myView.spell1timer_text.text.toString().toLong()
                    originalTime1 = holder.myView.spell1timer_text.text.toString()
                    dialogs.dismiss()
                }
                holder.dialogview.haste_image.setOnClickListener {
                    holder.myView.spell1_image.setImageResource(R.drawable.summoner_haste)
                    holder.myView.spell1timer_text.text = summonerCoolDonw["haste"].toString()
                    time1 = holder.myView.spell1timer_text.text.toString().toLong()
                    originalTime1 = holder.myView.spell1timer_text.text.toString()
                    dialogs.dismiss()
                }
                holder.dialogview.heal_image.setOnClickListener {
                    holder.myView.spell1_image.setImageResource(R.drawable.summoner_heal)
                    holder.myView.spell1timer_text.text = summonerCoolDonw["heal"].toString()
                    time1 = holder.myView.spell1timer_text.text.toString().toLong()
                    originalTime1 = holder.myView.spell1timer_text.text.toString()
                    dialogs.dismiss()
                }
                holder.dialogview.mana_image.setOnClickListener {
                    holder.myView.spell1_image.setImageResource(R.drawable.summoner_mana)
                    holder.myView.spell1timer_text.text = summonerCoolDonw["mana"].toString()
                    time1 = holder.myView.spell1timer_text.text.toString().toLong()
                    originalTime1 = holder.myView.spell1timer_text.text.toString()
                    dialogs.dismiss()
                }
                holder.dialogview.smite_image.setOnClickListener {
                    holder.myView.spell1_image.setImageResource(R.drawable.summoner_smite)
                    holder.myView.spell1timer_text.text = summonerCoolDonw["smite"].toString()
                    time1 = holder.myView.spell1timer_text.text.toString().toLong()
                    originalTime1 = holder.myView.spell1timer_text.text.toString()
                    dialogs.dismiss()
                }
                holder.dialogview.teleport_image.setOnClickListener {
                    holder.myView.spell1_image.setImageResource(R.drawable.summoner_teleport)
                    holder.myView.spell1timer_text.text = summonerCoolDonw["teleport"].toString()
                    time1 = holder.myView.spell1timer_text.text.toString().toLong()
                    originalTime1 = holder.myView.spell1timer_text.text.toString()
                    dialogs.dismiss()
                }
            }
            return@setOnLongClickListener true
        }
        holder.myView.spell2_image.setOnLongClickListener {
            if (holder.dialogview.parent != null) {
                val parent = holder.dialogview.parent as ViewGroup
                parent.removeView(holder.dialogview)
            }
            if(!activate2) {
                val dialogs = holder.dialog.show()
                holder.dialogview.barrier_image.setOnClickListener {
                    holder.myView.spell2_image.setImageResource(R.drawable.summoner_barrier)
                    holder.myView.spell2timer_text.text = summonerCoolDonw["barrier"].toString()
                    time2 = holder.myView.spell2timer_text.text.toString().toLong()
                    originalTime2 = holder.myView.spell2timer_text.text.toString()
                    dialogs.dismiss()
                }
                holder.dialogview.boost_image.setOnClickListener {
                    holder.myView.spell2_image.setImageResource(R.drawable.summoner_boost)
                    holder.myView.spell2timer_text.text = summonerCoolDonw["boost"].toString()
                    time2 = holder.myView.spell2timer_text.text.toString().toLong()
                    originalTime2 = holder.myView.spell2timer_text.text.toString()
                    dialogs.dismiss()
                }
                holder.dialogview.dot_image.setOnClickListener {
                    holder.myView.spell2_image.setImageResource(R.drawable.summoner_dot)
                    holder.myView.spell2timer_text.text = summonerCoolDonw["dot"].toString()
                    time2 = holder.myView.spell2timer_text.text.toString().toLong()
                    originalTime2 = holder.myView.spell2timer_text.text.toString()
                    dialogs.dismiss()
                }
                holder.dialogview.exhaust_image.setOnClickListener {
                    holder.myView.spell2_image.setImageResource(R.drawable.summoner_exhaust)
                    holder.myView.spell2timer_text.text = summonerCoolDonw["exhaust"].toString()
                    time2 = holder.myView.spell2timer_text.text.toString().toLong()
                    originalTime2 = holder.myView.spell2timer_text.text.toString()
                    dialogs.dismiss()
                }
                holder.dialogview.flash_image.setOnClickListener {
                    holder.myView.spell2_image.setImageResource(R.drawable.summoner_flash)
                    holder.myView.spell2timer_text.text = summonerCoolDonw["flash"].toString()
                    time2 = holder.myView.spell2timer_text.text.toString().toLong()
                    originalTime2 = holder.myView.spell2timer_text.text.toString()
                    dialogs.dismiss()
                }
                holder.dialogview.haste_image.setOnClickListener {
                    holder.myView.spell2_image.setImageResource(R.drawable.summoner_haste)
                    holder.myView.spell2timer_text.text = summonerCoolDonw["haste"].toString()
                    time2 = holder.myView.spell2timer_text.text.toString().toLong()
                    originalTime2 = holder.myView.spell2timer_text.text.toString()
                    dialogs.dismiss()
                }
                holder.dialogview.heal_image.setOnClickListener {
                    holder.myView.spell2_image.setImageResource(R.drawable.summoner_heal)
                    holder.myView.spell2timer_text.text = summonerCoolDonw["heal"].toString()
                    time2 = holder.myView.spell2timer_text.text.toString().toLong()
                    originalTime2 = holder.myView.spell2timer_text.text.toString()
                    dialogs.dismiss()
                }
                holder.dialogview.mana_image.setOnClickListener {
                    holder.myView.spell2_image.setImageResource(R.drawable.summoner_mana)
                    holder.myView.spell2timer_text.text = summonerCoolDonw["mana"].toString()
                    time2 = holder.myView.spell2timer_text.text.toString().toLong()
                    originalTime2 = holder.myView.spell2timer_text.text.toString()
                    dialogs.dismiss()
                }
                holder.dialogview.smite_image.setOnClickListener {
                    holder.myView.spell2_image.setImageResource(R.drawable.summoner_smite)
                    holder.myView.spell2timer_text.text = summonerCoolDonw["smite"].toString()
                    time2 = holder.myView.spell2timer_text.text.toString().toLong()
                    originalTime2 = holder.myView.spell2timer_text.text.toString()
                    dialogs.dismiss()
                }
                holder.dialogview.teleport_image.setOnClickListener {
                    holder.myView.spell2_image.setImageResource(R.drawable.summoner_teleport)
                    holder.myView.spell2timer_text.text = summonerCoolDonw["teleport"].toString()
                    time2 = holder.myView.spell2timer_text.text.toString().toLong()
                    originalTime2 = holder.myView.spell2timer_text.text.toString()
                    dialogs.dismiss()
                }
            }
            return@setOnLongClickListener true
        }

        holder.myView.spell1_image.setOnClickListener {
            if (holder.myView.spell1_image.drawable.constantState == ContextCompat.getDrawable(
                    context,
                    R.drawable.normal
                )!!.constantState
            ) {
                Toast.makeText(context, "set the spell", Toast.LENGTH_SHORT).show()
            } else {
                if (!activate1) {
                    holder.myView.spell1_image.setColorFilter(
                        Color.parseColor("#B3000000"),
                        PorterDuff.Mode.DARKEN
                    )
                    istime1 = true
                    timer1(time1, holder.myView.spell1timer_text).start()
                    activate1 = true

                } else {
                    holder.myView.spell1_image.setColorFilter(null)
                    istime1 = false
                    activate1 = false
                }
            }
        }
        holder.myView.spell2_image.setOnClickListener {
            if (holder.myView.spell2_image.drawable.constantState == ContextCompat.getDrawable(
                    context,
                    R.drawable.normal
                )!!.constantState
            ) {
                Toast.makeText(context, "set the spell", Toast.LENGTH_SHORT).show()
            } else {
                if (!activate2) {
                    holder.myView.spell2_image.setColorFilter(
                        Color.parseColor("#B3000000"),
                        PorterDuff.Mode.DARKEN
                    )
                    istime2 = true
                    timer2(time2, holder.myView.spell2timer_text).start()
                    activate2 = true
                } else {
                    holder.myView.spell2_image.setColorFilter(null)
                    istime2 = false
                    activate2 = false
                }
            }
        }
    }
    override fun getItemCount() = myDataset.size
}
