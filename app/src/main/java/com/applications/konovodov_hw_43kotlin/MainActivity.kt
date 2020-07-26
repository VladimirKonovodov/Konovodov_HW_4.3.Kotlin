package com.applications.konovodov_hw_43kotlin

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // play ("WhWc3b3KhnY")
        val timestamp = 12825216
        val location = Location(55.204300, 61.354800)


        val post = Post(
            1,
            "Konovodov V.A.",
            "23 August 2020",
            "First post in your network!",
            timestamp,
            5,
            1,
            true,
            "WhWc3b3KhnY"
        )
        val event = Event(
            post,
            "Chelyabinsk",
            location
        )
        if (post.videoContent == true) {
            videoPart.setOnClickListener() {
                play(post.videoSrc)
            }
        }
        locationImage.setOnClickListener() {
            val lat = event.coordinates.lat
            val lng = event.coordinates.lon
            val data = Uri.parse("geo:$lat,$lng")
            showMap(data)
        }

        contentTv.text = post.content

        startDateTv.text = post.publishedAgo()
        postAuthor.text = post.author
        likeCounter.text = post.likeCounter.toString()
        if (post.likeCounter != 0) {
            likeImageBtn.setImageResource(R.drawable.ic_favorite_active)
            likeCounter.setTextColor(getColor(R.color.likeColorActive))
        } else {
            likeImageBtn.setImageResource(R.drawable.ic_favorite_disabled)
            likeCounter.setTextColor(getColor(R.color.likeColorPassive))
        }

        likeImageBtn.setOnClickListener() {
            if (post.likeCounter == 0) {
                likeImageBtn.setImageResource(R.drawable.ic_favorite_active)
                likeCounter.setTextColor(getColor(R.color.likeColorActive))
                post.likeCounter = post.likeCounter + 1
                likeCounter.text = post.likeCounter.toString()
            } else {
                post.likeCounter = post.likeCounter - 1
                if (post.likeCounter == 0) {
                    likeImageBtn.setImageResource(R.drawable.ic_favorite_disabled)
                    likeCounter.setTextColor(getColor(R.color.likeColorPassive))
                    likeCounter.text = " "
                } else {
                    likeCounter.text = post.likeCounter.toString()
                }
            }
        }
    }

    fun play(video: String) {
        val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$video"))
        val webIntent =
            Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=$video"))
        try {
            startActivity(appIntent)
        } catch (ex: ActivityNotFoundException) {
            startActivity(webIntent)
        }


    }

    fun showMap(geoLocation: Uri) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = geoLocation
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}