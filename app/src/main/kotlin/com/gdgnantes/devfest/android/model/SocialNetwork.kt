package com.gdgnantes.devfest.android.model

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v7.content.res.AppCompatResources
import com.gdgnantes.devfest.android.R

enum class SocialNetwork(
        val apiValue: String,
        private val icon: Int,
        private val networkName: Int) {

    Twitter(
            "twitter",
            R.drawable.ic_network_twitter,
            R.string.network_twitter),
    GooglePlus(
            "google_plus",
            R.drawable.ic_network_google_plus,
            R.string.network_google_plus),
    Website(
            "website",
            R.drawable.ic_network_web,
            R.string.network_website),
    GitHub(
            "github",
            R.drawable.ic_network_github,
            R.string.network_github);

    companion object {
        private const val DEFAULT_ICON = R.drawable.ic_network_web
        private const val DEFAULT_NAME = R.string.network_website

        fun get(apiValue: String): SocialNetwork? {
            return values().firstOrNull { apiValue == it.apiValue }
        }

        fun getIcon(self: SocialNetwork?, context: Context): Drawable {
            return AppCompatResources.getDrawable(context, self?.icon ?: SocialNetwork.DEFAULT_ICON)!!
        }

        fun getName(self: SocialNetwork?, context: Context): String {
            return context.getString(self?.networkName ?: SocialNetwork.DEFAULT_NAME)
        }
    }

}



