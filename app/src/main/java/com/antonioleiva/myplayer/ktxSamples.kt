@file:Suppress("UNUSED_VARIABLE", "unused")

package com.antonioleiva.myplayer

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.app.NotificationManager
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Point
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.location.Location
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.transition.Transition
import android.util.SparseIntArray
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.animation.addListener
import androidx.core.animation.doOnEnd
import androidx.core.content.ContextCompat
import androidx.core.content.edit
import androidx.core.content.getSystemService
import androidx.core.graphics.*
import androidx.core.graphics.drawable.toDrawable
import androidx.core.location.component1
import androidx.core.location.component2
import androidx.core.net.toUri
import androidx.core.os.bundleOf
import androidx.core.os.postDelayed
import androidx.core.text.*
import androidx.core.transition.addListener
import androidx.core.transition.doOnEnd
import androidx.core.util.*
import androidx.core.view.*


fun animatorListeners(animator: ValueAnimator) {

    animator.addListener(object : Animator.AnimatorListener {

        override fun onAnimationStart(animation: Animator?) {
            print("Start")
        }

        override fun onAnimationRepeat(animation: Animator?) {
            print("Repeat")
        }

        override fun onAnimationEnd(animation: Animator?) {
            print("End")
        }

        override fun onAnimationCancel(animation: Animator?) {
            print("Cancel")
        }
    })

    // ---

    animator.addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator?) {
            print("End")
        }
    })
}

fun animatorListenersKTX(animator: ValueAnimator) {

    animator.addListener(
        onStart = { print("Start") },
        onRepeat = { print("Repeat") },
        onEnd = { print("End") },
        onCancel = { print("Cancel") }
    )

    // ---

    animator.doOnEnd {
        print("End")
    }
}


@RequiresApi(Build.VERSION_CODES.KITKAT)
fun transitionListeners(transition: Transition) {

    transition.addListener(object : Transition.TransitionListener {

        override fun onTransitionStart(transition: Transition?) {
        }

        override fun onTransitionResume(transition: Transition?) {
        }

        override fun onTransitionPause(transition: Transition?) {
        }

        override fun onTransitionEnd(transition: Transition?) {
            print("End")
        }

        override fun onTransitionCancel(transition: Transition?) {
        }

    })

}

@RequiresApi(Build.VERSION_CODES.KITKAT)
fun transitionListenersKTX(transition: Transition) {

    transition.doOnEnd { print("End") }

    transition.addListener(
        onStart = { print("Start") },
        onEnd = { print("End") }
    )

}


fun sharedPreferences(prefs: SharedPreferences) {

    // Regular
    prefs.edit()
        .putString("key1", "value1")
        .putInt("key2", 2)
        .apply()

    // KTX
    prefs.edit {
        putString("key1", "value1")
        putInt("key2", 2)
    }

}


fun systemService(context: Context) {

    // Regular
    val notificationsManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    val notificationsManager2 = ContextCompat.getSystemService(context, NotificationManager::class.java)

    // KTX
    val notificationsManager3 = context.getSystemService<NotificationManager>()
}


/***
 *
 * Bitmaps & Drawables
 *
 ***/

fun bitmapToDrawable(context: Context, bitmap: Bitmap) {

    val d1 = BitmapDrawable(context.resources, bitmap)
    val d2 = bitmap.toDrawable(context.resources)

}

@RequiresApi(Build.VERSION_CODES.O)
fun colorToDrawable(intColor: Int, color: Color) {

    // Regular
    val fromInt1 = ColorDrawable(intColor)
    val fromColor1 = ColorDrawable(color.toArgb())

    // KTX
    val fromInt2 = intColor.toDrawable()
    val fromColor2 = color.toDrawable()

}

fun bitmapAndCanvas() {

    val bitmap = createBitmap(200, 200)

    bitmap.applyCanvas {
        // Draw on the bitmap
    }

    val scaled = bitmap.scale(100, 100)
}


fun rectangles(rect1: Rect, rect2: Rect) {

    val (l, t, r, b) = rect1

    val add = rect1 + rect2
    val sub = rect1 - rect2
    val or = rect1 or rect2
    val and = rect1 and rect2
    val xor = rect1 xor rect2
}


fun points(p1: Point, p2: Point) {

    val (x, y) = p1
    val sum = p1 + p2
    val invP = -p1

}


fun stringToUri(uriStr: String) {

    // Regular
    val uri1 = Uri.parse(uriStr)

    // KTX
    val uri2 = uriStr.toUri()

}


fun bundle() {

    // Regular
    val bundle1 = Bundle().apply {
        putInt("key1", 2)
        putString("key2", "value2")
    }

    // KTX
    val bundle2 = bundleOf("key1" to 2, "key2" to "value2")

}


fun postDelayed() {

    val handler = Handler()

    // Regular
    handler.postDelayed({
        print("Hi!")
    }, 2000)

    // KTX
    handler.postDelayed(2000) {
        print("Hi!")
    }

}


fun location(location: Location) {

    // Regular
    val lat1 = location.latitude
    val lon1 = location.longitude

    // KTX
    val (lat2, lon2) = location

}


fun spannable(name: String) {

    val spannedString = buildSpannedString {

        append("Hello ")

        backgroundColor(R.color.colorPrimary) {

            color(R.color.colorAccent) {

                bold { append(name) }

            }

        }

        italic { append(". How are you?") }

    }

}


fun pairs() {

    val kotlinPair = 1 to "a"
    val androidPair = kotlinPair.toAndroidPair()

    val (x, y) = androidPair
}


fun sparseArray(array: SparseIntArray) {

    array.forEach { key, value ->
        print("key: $key, value: $value")
    }

    val value = array.getOrDefault(1, 3)

    array.remove(1, 3)

    if (array.isNotEmpty()) {
        print("Not empty")
    }
}


fun menu(menu: Menu) {

    val children = menu.children

    val size = menu.size

    menu.forEach {
        print(it.itemId)
    }

    for (menuItem in menu) {
        print(menuItem.itemId)
    }

    val empty = menu.isEmpty()
    val notEmpty = menu.isNotEmpty()
}


fun viewGroup(viewGroup: ViewGroup) {

    val children = viewGroup.children

    val size = viewGroup.size

    viewGroup.forEach {
        it.visibility = View.GONE
    }

    for (view in viewGroup) {
        view.visibility = View.GONE
    }

    val empty = viewGroup.isEmpty()
    val notEmpty = viewGroup.isNotEmpty()
}


@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
fun view(view: View) {

    val visible = view.isVisible
    val invisible = view.isInvisible
    val gone = view.isGone

    val marginTop = view.marginTop
    val marginBottom = view.marginBottom
    val marginStart = view.marginStart
    val marginEnd = view.marginEnd

    view.doOnPreDraw {
        // ...
    }

    view.doOnLayout {
        // ...
    }

    val bitmap = view.drawToBitmap()

    view.setPadding(20)

    view.updateLayoutParams<ViewGroup.MarginLayoutParams> {
        setMarginStart(20)
        setMarginEnd(30)
    }
}