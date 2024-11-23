package com.example.birthdaycardgenerator

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val generateCardBtn = findViewById<android.view.View>(R.id.generate_card_btn)
        val cardContainer = findViewById<LinearLayout>(R.id.card_container)

        // Arrays for backgrounds and cake images
        val colors = arrayOf(
            intArrayOf(android.R.color.holo_blue_light, android.R.color.holo_green_light),
            intArrayOf(android.R.color.holo_orange_light, android.R.color.holo_red_light),
            intArrayOf(android.R.color.holo_purple, android.R.color.holo_blue_dark)
        )

        val cakeImages = arrayOf(
            R.drawable.cake_image, // Add three different cake images to drawable folder
            R.drawable.cake_image1,
            R.drawable.cake_image2
        )

        generateCardBtn.setOnClickListener {
            // Clear previous cards
            cardContainer.removeAllViews()

            // Generate 3 cards with different colors and cake images
            for (i in colors.indices) {
                val materialCard = MaterialCardView(this).apply {
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    ).apply {
                        setMargins(16, 16, 16, 16)
                    }
                    radius = 16f // Rounded corners
                    elevation = 8f

                    // Set gradient background
                    val gradientDrawable = GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        intArrayOf(
                            resources.getColor(colors[i][0], null),
                            resources.getColor(colors[i][1], null)
                        )
                    )
                    gradientDrawable.cornerRadius = 16f
                    background = gradientDrawable
                    setPadding(32, 32, 32, 32)
                }

                // Card Content Layout
                val cardContentLayout = LinearLayout(this).apply {
                    orientation = LinearLayout.VERTICAL
                    gravity = android.view.Gravity.CENTER
                    setPadding(16, 16, 16, 16)
                }

                // "Happy Birthday" Title
                val titleTextView = TextView(this).apply {
                    text = "Happy Birthday"
                    textSize = 32f
                    setTypeface(null, android.graphics.Typeface.BOLD)
                    gravity = android.view.Gravity.CENTER
                    setTextColor(resources.getColor(android.R.color.black, null))
                }

                // Editable Name Input
                val nameEditText = EditText(this).apply {
                    hint = "Enter Name"
                    textSize = 32f
                    setTypeface(null, android.graphics.Typeface.BOLD)
                    gravity = android.view.Gravity.CENTER
                    setTextColor(resources.getColor(android.R.color.black, null))
                }

                // Editable Wishes
                val wishesEditText = EditText(this).apply {
                    setText(
                        "Wishing you a day filled with love, laughter, and endless joy. " +
                                "May all your dreams come true on your special day!"
                    )
                    textSize = 18f
                    gravity = android.view.Gravity.CENTER
                    setTextColor(resources.getColor(android.R.color.black, null))
                    setPadding(8, 16, 8, 16)
                }

                // Cake Image
                val cakeImageView = ImageView(this).apply {
                    setImageResource(cakeImages[i]) // Use corresponding cake image
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    ).apply {
                        gravity = android.view.Gravity.CENTER
                        topMargin = 16
                    }
                }

                // Add Views to Content Layout
                cardContentLayout.addView(titleTextView)
                cardContentLayout.addView(nameEditText)
                cardContentLayout.addView(wishesEditText)
                cardContentLayout.addView(cakeImageView)

                // Add Content Layout to Card
                materialCard.addView(cardContentLayout)

                // Add Card to Container
                cardContainer.addView(materialCard)
            }
        }
    }
}
