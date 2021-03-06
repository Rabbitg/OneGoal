package com.hour.onegoal

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.auth.User
import com.hour.onegoal.Util.loadImage
import de.hdodenhof.circleimageview.CircleImageView

//TODO: 참가자애들 연결해주는 거

class ParticipantsAdapter(val context: Context, val userList: ArrayList<com.hour.onegoal.Data.User>,
                          var itemClick: (com.hour.onegoal.Data.User) -> Unit)
    : RecyclerView.Adapter<ParticipantsAdapter.Holder>() {

    inner class Holder(itemView: View?, itemClick: (com.hour.onegoal.Data.User) -> Unit) : RecyclerView.ViewHolder(itemView!!){
        val userPhoto = itemView?.findViewById<CircleImageView>(R.id.teamUserProfile)
        val userName = itemView?.findViewById<TextView>(R.id.teamUserName)

        fun bind(user: com.hour.onegoal.Data.User, context: Context){
            userName?.text = user.username.toString()
            userPhoto?.loadImage(user.photoUrl)
        }
        /** 만약에 유저 아이디 클릭했을 경우 이벤트 발생시키려면 사용
        init {
            itemView?.setOnClickListener {
                val room = workoutList[adapterPosition]
                //TODO: 아이템뷰 클릭했을 때 intent
                val intent = Intent(context,WorkOutRoomActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                intent.putExtra("roomId",room.roomId)
                intent.putExtra("title",room.title)
                intent.putExtra("summary",room.summary)
                intent.putExtra("description",room.description)
                intent.putExtra("photoUrl",room.photoUrl)
                intent.putExtra("teamHead",room.teamHead)
                context.startActivity(intent)
            }
        }
        **/
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        // 화면을 최초 로딩하여 만들어진 View 가 없는 경우,
        // xml 파일을 inflate 하여 ViewHolder 를 생성한다.
        val view = LayoutInflater.from(context).inflate(R.layout.participant_team_item,parent,false)
        return Holder(view,itemClick)
    }

    override fun getItemCount(): Int {
        //RecyclerView 로 만들어지는 item 의 총 개수를 반환한다.
        return userList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        // 위의 onCreateViewHolder 에서 만든 view 와 실제 입력되는 각각의 데이터를 연결한다.
        holder.bind(userList[position],context)
    }

}