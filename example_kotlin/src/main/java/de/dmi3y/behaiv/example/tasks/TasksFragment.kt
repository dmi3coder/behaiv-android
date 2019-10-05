package de.dmi3y.behaiv.example.tasks

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import de.dmi3y.behaiv.example.behaiv
import de.dmi3y.behaiv.example.data.Task
import de.dmi3y.behaiv.example.db
import kotlinx.android.synthetic.main.fragment_list.*
import java.util.*
import kotlin.concurrent.thread


class TasksFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(de.dmi3y.behaiv.example.R.layout.fragment_list, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val behaiv = context!!.applicationContext.behaiv()
        behaiv?.subscribe()!!.subscribe({
            //this will return on prediction
            Log.d("TASKSFRAGMENT", "predicted:" + it)
            val predictButton = when (it) {
                "add" -> AddButton
                "work" -> WorkButton
                "personal" -> PersonalButton
                "sport" -> SportButton
                else -> AddButton
            }
            predictButton.setBackgroundColor(Color.RED)
        }, {
            it.printStackTrace()
        })
        thread {
            behaiv.startCapturing(true)
        }


        val db = context!!.applicationContext.db()

        this.AddButton.setOnClickListener {
            //should be replaced with just label in next release
            behaiv.registerLabel("add")
            thread {

                db.taskDao().insertAll(
                    Task(
                        UUID.randomUUID().toString(),
                        "Random task" + Math.random(),
                        arrayListOf("work", "sport", "personal").shuffled().take(1)[0],
                        "false",
                        "",
                        ""
                    )
                )
            }

            thread {
                behaiv.stopCapturing(false)
            }
        }
        this.WorkButton.setOnClickListener {
            behaiv.registerLabel("work")
            loadTasks("work")
            thread {
                behaiv.stopCapturing(false)
            }
        }
        this.PersonalButton.setOnClickListener {
            behaiv.registerLabel("personal")
            loadTasks("personal")
            thread {
                behaiv.stopCapturing(false)
            }
        }
        this.SportButton.setOnClickListener {
            behaiv.registerLabel("sport")
            loadTasks("sport")
            thread {
                behaiv.stopCapturing(false)
            }
        }


        loadTasks(null)
        super.onViewCreated(view, savedInstanceState)
    }

    fun loadTasks(category: String?) {
        val db = context!!.applicationContext.db()
        thread {
            val tasks = if (category == null) {
                db.taskDao().getAll()
            } else {
                db.taskDao().getByCategory(category)
            }.map { it.name }

            this.activity?.runOnUiThread {
                list.adapter = ArrayAdapter<String>(
                    context!!,
                    android.R.layout.simple_list_item_checked,
                    tasks
                )
            }
        }
    }
}