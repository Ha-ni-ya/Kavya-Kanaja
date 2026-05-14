package com.example.kavyakanaja.ui.theme.screens

import android.media.MediaPlayer
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kavyakanaja.data.PoemRepository
import com.example.kavyakanaja.utils.FavouriteManager

@Composable
fun PoemDetailScreen(
    poemId: Int,
    navController: NavController
) {

    val context = LocalContext.current

    val poems = remember {
        PoemRepository.loadPoems(context)
    }

    val poem = poems.find { it.id == poemId }

    if (poem == null) {

        Text(
            text = "Poem not found",
            modifier = Modifier.padding(20.dp)
        )

        return
    }

    var showDialog by remember {
        mutableStateOf(false)
    }

    var selectedMeaning by remember {
        mutableStateOf("")
    }

    var isFavourite by remember {
        mutableStateOf(
            FavouriteManager.isFav(
                context,
                poem.id
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = poem.title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = poem.poet,
            style = MaterialTheme.typography.titleMedium,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = "📖 Poem",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(12.dp))

                Column {

                    poem.text.split(" ").forEach { word ->

                        Text(
                            text = word,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier
                                .padding(bottom = 6.dp)
                                .clickable {

                                    val difficultWord =
                                        poem.difficultWords.find {

                                            it.word.contains(
                                                word.replace(",", "")
                                            )
                                        }

                                    if (difficultWord != null) {

                                        selectedMeaning =
                                            "${difficultWord.word} = ${difficultWord.meaning}"

                                        showDialog = true
                                    }
                                }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = "✨ Bhavartha",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = poem.bhavartha,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {

                FavouriteManager.toggle(
                    context,
                    poem.id
                )

                isFavourite =
                    FavouriteManager.isFav(
                        context,
                        poem.id
                    )
            },

            modifier = Modifier.fillMaxWidth()
        ) {

            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = null,
                tint =
                    if (isFavourite)
                        Color.Red
                    else
                        Color.White
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                if (isFavourite)
                    "Remove Favourite"
                else
                    "Add to Favourites"
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {

                val resId =
                    context.resources.getIdentifier(
                        poem.audioFile,
                        "raw",
                        context.packageName
                    )

                if (resId != 0) {

                    MediaPlayer.create(
                        context,
                        resId
                    ).start()
                }
            },

            modifier = Modifier.fillMaxWidth()
        ) {

            Text("🎵 Play Audio")
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            onClick = {
                navController.popBackStack()
            },

            modifier = Modifier.fillMaxWidth()
        ) {

            Text("⬅ Back")
        }
    }

    if (showDialog) {

        AlertDialog(
            onDismissRequest = {
                showDialog = false
            },

            confirmButton = {

                Button(
                    onClick = {
                        showDialog = false
                    }
                ) {

                    Text("Close")
                }
            },

            title = {

                Text("📘 Word Meaning")
            },

            text = {

                Text(selectedMeaning)
            }
        )
    }
}